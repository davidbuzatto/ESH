/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package esh.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.hibernate4.encryptor.HibernatePBEEncryptorRegistry;

/**
 * Listener de configuração e de carga de configurações.
 *
 * @author David Buzatto
 */
@WebListener()
public class ConfiguracoesListener implements ServletContextListener {

    @Override
    public void contextInitialized( ServletContextEvent sce ) {
        
        // cria o encriptador do Jasypt e registra no hibernate
        StandardPBEStringEncryptor strongEncryptor = new StandardPBEStringEncryptor();
        strongEncryptor.setPassword( "sr*uo+hlo&ohc#sys@ae" );
        HibernatePBEEncryptorRegistry registry = HibernatePBEEncryptorRegistry.getInstance();
        registry.registerPBEStringEncryptor( "strongHibernateStringEncryptor", strongEncryptor );
        
    }

    @Override
    public void contextDestroyed( ServletContextEvent sce ) {
    }
    
}
