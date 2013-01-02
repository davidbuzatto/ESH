/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package esh.testes;

import esh.dao.Dao;
import esh.dao.DaoFactory;
import esh.enumeracoes.TipoUsuario;
import esh.entidades.Usuario;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.hibernate4.encryptor.HibernatePBEEncryptorRegistry;

/**
 *
 * @author David
 */
public class TestesUsuarios {
    
    public static void main( String[] args ) {
        
        StandardPBEStringEncryptor strongEncryptor = new StandardPBEStringEncryptor();
        strongEncryptor.setPassword( "sr*uo+hlo&ohc#sys@ae" );
        HibernatePBEEncryptorRegistry registry = HibernatePBEEncryptorRegistry.getInstance();
        registry.registerPBEStringEncryptor( "strongHibernateStringEncryptor", strongEncryptor );
        
        Dao<Usuario> dao = DaoFactory.<Usuario>getDao();
        
        Usuario u = new Usuario();
        u.setEmail( "davidbuzatto@gmail.comxjyzyhf" );
        u.setSenha( "123456789" );
        u.setNome( "David" );
        u.setSobrenome( "Buzatto" );
        u.setAtivo( Boolean.TRUE );
        u.setTipo( TipoUsuario.SUPER );
        
        dao.salvar( u );
        
        /*Usuario c = dao.carregar( Usuario.class, 6L );
        System.out.println( c.getSenha() );*/
        
        System.out.println( u.getSenha() );
        dao.fecharEntityManager();
        
    }
    
}
