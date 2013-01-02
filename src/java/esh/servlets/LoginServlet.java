/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package esh.servlets;

import esh.dao.Dao;
import esh.dao.DaoFactory;
import esh.entidades.Usuario;
import esh.uteis.Uteis;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;

/**
 * Servlet de login.
 * 
 * @author David Buzatto
 */
@WebServlet( name = "LoginServlet", urlPatterns = { "/login" } )
public class LoginServlet extends HttpServlet {

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
            
            String email = request.getParameter( "email" );
            String senha = request.getParameter( "senha" );
            
            Query query = daoUsuario.createNamedQuery( "Usuario.findByEmail" );
            query.setParameter( "email", email );
            
            List<Usuario> lista = query.getResultList();
            Usuario usuario = null;
            
            if ( lista.size() > 0 ) {
                usuario = lista.get( 0 );
            }
            
            StringBuilder sb = new StringBuilder();
            sb.append( "{ \"success\": " );
            
            if ( usuario != null && 
                    usuario.getSenha().equals( senha ) && 
                    usuario.getAtivo() ) {
                
                sb.append( "true }" );
                
                // se o usuário existe:
                // abre a sessão
                HttpSession sessao = request.getSession( true );

                // coloca o usuário na sessão
                sessao.setAttribute( "usuario", usuario );
                
            } else {
                sb.append( "false }" );
            }
            
            out.print( sb.toString() );
            
            daoUsuario.fecharEntityManager();
            
        } catch ( HibernateException | PersistenceException exc ) {
            
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
