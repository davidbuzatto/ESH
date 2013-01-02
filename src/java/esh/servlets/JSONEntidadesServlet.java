/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package esh.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import esh.dao.Dao;
import esh.dao.DaoFactory;
import esh.gson.EstrategiaExclusaoJSON;
import esh.uteis.ParametroConsulta;
import esh.uteis.Uteis;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;

/**
 * Servlet de consultas.
 * 
 * Sempre há a necessidade de enviar a entidade que ser quer consultar e também
 * é preciso definir o tipo de consulta que será executada para que se saiba 
 * os parâmetros a serem utilizados.
 * 
 * Parâmetros:
 * entidade: nome da entidade (igual ao da classe)
 * 
 * tipoConsulta: tipo da consulta
 *     valores: unica | completa | especifica
 * 
 * aplicarLimite: se deve ou não aplicar o limite de consulta. é aplicável nas 
 * consultas completa e específica
 *     valores: true | false
 * 
 * start: início do limite (caso aplicarLimite seja verdadeiro)
 * limit: quantidade a retornar (caso aplicarLimite seja verdadeiro)
 * 
 * Tipos de consulta:
 * 
 * unica:
 *     id: o identificador da entidade
 *     tipo: o tipo do identificador da entidade
 *           se "numero" faz cast para Long
 *           se "string" não faz cast
 * 
 * completa: basta a definição da entidade (obrigatório)
 * 
 * especifica: 
 *     parametros: os parâmetros da consulta específica que devem ser enviados 
 *     usando um objeto javascript no formato
 * 
 *     [{ 
 *         campo: "", 
 *         valor: "", 
 *         tipo: ""
 *     }, { 
 *         campo: "", 
 *         valor: "", 
 *         tipo: ""
 *     }, ... ]
 * 
 * Este objeto será convertido em uma lista de objetos da classe 
 * esh.uteis.ParametroConsulta. O atributo campo representa o campo da 
 * entidade, o atributo valor é o valor esperado e o tipo é o tipo do campo 
 * (string ou numero). O método de resolução dos parâmetros usa a ordem dos 
 * parâmetros passados para obter uma NamedQuery da entidade em questão no 
 * formato: "<NomeEntidade>.findBy<Campo1><Campo2><CampoN>" além de que os 
 * parâmetros da query devem estar na mesma ordem e ter os mesmos nomes dos 
 * campos.
 * 
 * Este Servlet retorna um JSON com o seguinte formato:
 * {
 *     success: true,
 *     total: X,
 *     itens: [{
 *         campo1: ...,
 *         campo2: ...,
 *         campoN: ...
 *     }, {
 *         campo1: ...,
 *         campo2: ...,
 *         campoN: ...
 *     }, ... ]
 * }
 * 
 * Onde success diz que consulta foi bem sucedida, total representa a quantidade
 * total de itens (resultados da consulta) obtidos e o array itens contém
 * cada instância da entidade obtida na consulta. Mesmo para a consulta unica
 * é retornado um array.
 * 
 * @author David Buzatto
 */
@WebServlet( name = "JSONEntidadesServlet", urlPatterns = { "/servlets/jsonEntidades" } )
public class JSONEntidadesServlet extends HttpServlet {

    private static final String PACOTE_ENTIDADES = "esh.entidades.";
    
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        
        response.setContentType( "text/json;charset=UTF-8" );
        PrintWriter out = response.getWriter();
        
        Dao dao = DaoFactory.getDao();
        
        try {
            
            String tipoConsulta = request.getParameter( "tipoConsulta" );
            List<Object> dados = new ArrayList();
            
            String entidade = request.getParameter( "entidade" );
            Class classeEntidade = Class.forName( PACOTE_ENTIDADES + entidade );
            
            int inicio = 0;
            int quantidade = 0;
            int total = 0;
            boolean aplicarLimite = Boolean.parseBoolean( request.getParameter( "aplicarLimite" ) );
            
            if ( aplicarLimite ) {
                inicio = Integer.parseInt( request.getParameter( "start" ) );
                quantidade = Integer.parseInt( request.getParameter( "limit" ) );
            }
            
            String id;
            String tipo;
            Object resultado;
            List<Object> resultados;
            
            Gson gson = new GsonBuilder().setExclusionStrategies( new EstrategiaExclusaoJSON() ).serializeNulls().create();
                        
            switch ( tipoConsulta ) {
                
                case "unica":
                    
                    id = request.getParameter( "id" );
                    tipo = request.getParameter( "tipo" );
                    
                    resultado = consultaUnica( dao, entidade, id, tipo );
                    
                    if ( resultado != null ) {
                        total = 1;
                        dados.add( resultado );
                    }
                    
                    break;
                    
                case "completa":
                    
                    total = dao.obterQuantidadeDeRegistros( classeEntidade );
                    
                    if ( aplicarLimite ) {
                        resultados = consultaCompletaComIntervalo( dao, classeEntidade, inicio, quantidade );
                    } else {
                        resultados = consultaCompleta( dao, classeEntidade );
                    }
                    
                    dados.addAll( resultados );
                    break;
                    
                case "especifica":
                    
                    Type tipoLista = new TypeToken<List<ParametroConsulta>>(){}.getType();
                    List<ParametroConsulta> parametros = gson.fromJson( request.getParameter( "parametros" ), tipoLista );
                    
                    if ( parametros.size() == 1 && parametros.get(0).getCampo().equals( "id" ) ) {
                        
                        ParametroConsulta t = parametros.get( 0 );
                        resultado = consultaUnica( dao, entidade, t.getValor(), t.getTipo() );
                        
                        if ( resultado != null ) {
                            total = 1;
                            dados.add( resultado );
                        }
                        
                    } else {
                        
                        if ( aplicarLimite ) {
                            resultados = consultaEspecificaComIntervalo( dao, classeEntidade, parametros, inicio, quantidade );
                            total = consultaEspecifica( dao, classeEntidade, parametros ).size();
                        } else {
                            resultados = consultaEspecifica( dao, classeEntidade, parametros );
                            total = resultados.size();
                        }
                        
                        dados.addAll( resultados );
                        
                    }
                    
                    break;
                    
                default:
                    dados = new ArrayList();
                    break;
                
            }
            
            StringBuilder sb = new StringBuilder();
            
            for ( Object o : dados ) {
                Uteis.atravessarConfigurandoValorPadrao( o );
            }
            
            sb.append( "{ \"success\": true, " );
            sb.append( "\"total\": " );
            sb.append( total );
            sb.append( ", " );
            sb.append( "\"itens\": " );
            sb.append( gson.toJson( dados ) );
            sb.append( "}" );
            
            out.print( sb.toString() );
            
            dao.fecharEntityManager();
            
        } catch ( ClassNotFoundException | 
                HibernateException | 
                PersistenceException exc ) {
            
            dao.fecharEntityManager();
            
            out.print( Uteis.gerarJSONRetornoOperacao( exc ) );
            
        } finally {            
            out.close();
        }
        
    }
    
    private Object consultaUnica( Dao dao, String entidade, String id, String tipo ) 
            throws ClassNotFoundException {

        Class klass = Class.forName( PACOTE_ENTIDADES + entidade );

        Object resultado = null;

        if ( tipo.equals( "numero" ) ) {
            try {
                resultado = dao.carregar( klass, Long.valueOf( id ) );
            } catch ( NumberFormatException exc ) {}
        } else {
            resultado = dao.carregar( klass, id );
        }

        return resultado;
        
    }
    
    private List<Object> consultaEspecifica( Dao dao, Class entidade, List<ParametroConsulta> params ) {
        
        Query query = criarQueryEspecifica( dao, entidade, params );
        return query.getResultList();
        
    }
    
    private List<Object> consultaEspecificaComIntervalo( Dao dao, Class entidade, List<ParametroConsulta> params, int inicio, int quantidade ) {
        
        Query query = criarQueryEspecifica( dao, entidade, params );
        query.setFirstResult( inicio );
        query.setMaxResults( quantidade );
        
        return query.getResultList();
        
    }
    
    private Query criarQueryEspecifica( Dao dao, Class entidade, List<ParametroConsulta> params ) {
        
        String nomeQuery = entidade.getSimpleName() + ".findBy";
        
        for ( ParametroConsulta t : params ) {
            nomeQuery += ( String.valueOf( t.getCampo().charAt( 0 ) ).toUpperCase() + t.getCampo().substring( 1 ) );
        }
        
        Query query = dao.createNamedQuery( nomeQuery );
        
        for ( ParametroConsulta t : params ) {
            if ( t.getTipo().equals( "numero" ) ) {
                try {
                    query.setParameter( t.getCampo(), Long.valueOf( t.getValor() ) );
                } catch ( NumberFormatException exc ) {
                    query.setParameter( t.getCampo(), 0L );
                }
            } else {
                query.setParameter( t.getCampo(), t.getValor() );
            }
        }
        
        return query;
        
    }
    
    private List<Object> consultaCompleta( Dao dao, Class entidade ) 
            throws ClassNotFoundException {
        
        return dao.carregarTodos( entidade );
        
    }
    
    private List<Object> consultaCompletaComIntervalo( Dao dao, Class entidade, 
            int inicio, int quantidade ) 
            throws ClassNotFoundException {
                    
        return dao.carregarTodosNoIntervalo( entidade, inicio, quantidade );
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        processRequest( request, response );
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        processRequest( request, response );
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
