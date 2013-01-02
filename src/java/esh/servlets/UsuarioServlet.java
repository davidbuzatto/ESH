/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package esh.servlets;

import esh.dao.Dao;
import esh.dao.DaoFactory;
import esh.entidades.Area;
import esh.entidades.Campus;
import esh.entidades.Curso;
import esh.entidades.Professor;
import esh.entidades.Usuario;
import esh.enumeracoes.TipoUsuario;
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
 * Servlet para gerenciamento do cadastro de Usuários.
 * 
 * @author David Buzatto
 */
@WebServlet( name = "UsuarioServlet", urlPatterns = { "/servlets/usuario" } )
public class UsuarioServlet extends HttpServlet {

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
        
        Dao<Usuario> daoUsuario = DaoFactory.<Usuario>getDao();
        
        try {
            
            String acao = request.getParameter( "acao" );
            String id = request.getParameter( "id" );
            
            Usuario obj;
            
            switch ( acao ) {
                
                case "salvar":
                    
                    String email = request.getParameter( "email" );
                    String senha = request.getParameter( "senha" );
                    String nome = request.getParameter( "nome" );
                    String sobrenome = request.getParameter( "sobrenome" );
                    String ativo = request.getParameter( "ativo" );
                    String tipo = request.getParameter( "tipo" );
                    String idCampus = request.getParameter( "idCampus" );
                    String idArea = request.getParameter( "idArea" );
                    String idCurso = request.getParameter( "idCurso" );
                    String idProfessor = request.getParameter( "idProfessor" );
                    
                    Campus campus = null;
                    Area area = null;
                    Curso curso = null;
                    Professor professor = null;
                    
                    if ( idCampus != null ) {
                        campus = ( Campus ) daoUsuario.carregarSemGenerics( 
                                Campus.class, Long.valueOf( idCampus ) );
                    } else if ( idArea != null ) {
                        area = ( Area ) daoUsuario.carregarSemGenerics( 
                                Area.class, Long.valueOf( idArea ) );
                    } else if ( idCurso != null ) {
                        curso = ( Curso ) daoUsuario.carregarSemGenerics( 
                                Curso.class, Long.valueOf( idCurso ) );
                    } else if ( idProfessor != null ) {
                        professor = ( Professor ) daoUsuario.carregarSemGenerics( 
                                Professor.class, Long.valueOf( idProfessor ) );
                    }
                    
                    if ( id.equals( "null" ) ) {
                        obj = new Usuario();
                    } else {
                        obj = daoUsuario.carregar( Usuario.class, Long.parseLong( id ) );
                    }
                    
                    obj.setEmail( email );
                    obj.setSenha( senha );
                    obj.setNome( nome );
                    obj.setSobrenome( sobrenome );
                    obj.setAtivo( ativo == null ? Boolean.FALSE : Boolean.TRUE );
                    obj.setTipo( TipoUsuario.valueOf( tipo ) );
                    obj.setCampus( campus );
                    obj.setArea( area );
                    obj.setCurso( curso );
                    obj.setProfessor( professor );
                    
                    daoUsuario.salvar( obj );
                
                    break;
                    
                case "excluir":
                    
                    obj = daoUsuario.carregar( Usuario.class, Long.parseLong( id ) );
                    daoUsuario.excluir( obj );
                    
                    break;
                
            }
            
            out.print( Uteis.gerarJSONRetornoOperacao( null ) );
            
            daoUsuario.fecharEntityManager();
            
        } catch ( HibernateException | PersistenceException exc ) {
            
            daoUsuario.rollback();
            daoUsuario.fecharEntityManager();
            
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
