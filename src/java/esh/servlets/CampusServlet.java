/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package esh.servlets;

import esh.dao.Dao;
import esh.dao.DaoFactory;
import esh.entidades.Campus;
import esh.entidades.Cidade;
import esh.entidades.Instituicao;
import esh.uteis.Uteis;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;

/**
 * Servlet para gerenciamento do cadastro de Campi.
 * 
 * @author David Buzatto
 */
@WebServlet( name = "CampusServlet", urlPatterns = { "/servlets/campus" } )
public class CampusServlet extends HttpServlet {

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
        
        Dao<Campus> daoCampus = DaoFactory.<Campus>getDao();
        
        try {
            
            String acao = request.getParameter( "acao" );
            String id = request.getParameter( "id" );
            
            Campus obj;
            
            switch ( acao ) {
                
                case "salvar":
                    
                    String nome = request.getParameter( "nome" );
                    Instituicao instituicao = ( Instituicao ) daoCampus.carregarSemGenerics( Instituicao.class, 
                            Long.valueOf( request.getParameter( "idInstituicao" ) ) );
                    Cidade cidade = ( Cidade ) daoCampus.carregarSemGenerics( Cidade.class, 
                            Long.valueOf( request.getParameter( "idCidade" ) ) );
                    
                    if ( id.equals( "null" ) ) {
                        obj = new Campus();
                    } else {
                        obj = daoCampus.carregar( Campus.class, Long.parseLong( id ) );
                    }
                    
                    obj.setNome( nome );
                    obj.setInstituicao( instituicao );
                    obj.setCidade( cidade );
                    
                    daoCampus.salvar( obj );
                
                    break;
                    
                case "excluir":
                    
                    obj = daoCampus.carregar( Campus.class, Long.parseLong( id ) );
                    daoCampus.excluir( obj );
                    
                    break;
                
            }
            
            out.print( Uteis.gerarJSONRetornoOperacao( null ) );
            
            daoCampus.fecharEntityManager();
            
        } catch ( HibernateException | PersistenceException exc ) {
            
            daoCampus.rollback();
            daoCampus.fecharEntityManager();
            
            out.print( Uteis.gerarJSONRetornoOperacao( exc ) );
            
        } finally {            
            out.close();
        }
        
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
