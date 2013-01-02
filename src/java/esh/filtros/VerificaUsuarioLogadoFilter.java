/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package esh.filtros;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Filtro que monitora os servlets mapeados para não deixar que pessoas não 
 * logadas possam obter valores dos servlets. Apenas o servlet de login está
 * mapeado sem o prefixo "servlets".
 * 
 * @author David Buzatto
 */
@WebFilter( filterName = "VerificaUsuarioLogadoFilter", urlPatterns = { "/servlets/*" } )
public class VerificaUsuarioLogadoFilter implements Filter {
    
    private FilterConfig filterConfig = null;  

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter( ServletRequest request, ServletResponse response,
            FilterChain chain )
            throws IOException, ServletException {
            
        HttpServletRequest httpRequest = ( HttpServletRequest ) request;
        HttpServletResponse httpResponse = ( HttpServletResponse ) response;
        HttpSession sessao = httpRequest.getSession( false );
        
        // verifica se a sessaão não existe ou se existir, se ela não contém um usuário
        if ( sessao == null || sessao.getAttribute( "usuario" ) == null ) {
            httpResponse.sendError( 403, "Você não tem permissão para acessar este recurso!" );
        }

        chain.doFilter( request, response );
        
    }

    @Override
    public void init( FilterConfig filterConfig ) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
    }
    
}
