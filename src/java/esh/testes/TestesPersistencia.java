/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package esh.testes;

import esh.dao.Dao;
import esh.dao.DaoFactory;
import esh.entidades.Pais;

/**
 *
 * @author David
 */
public class TestesPersistencia {
    
    public static void main( String[] args ) {
        
        testeSalvarPaises();
        
    }
    
    public static void testeSalvarPaises() {
        
        Dao<Pais> dao = DaoFactory.<Pais>getDao();
        
        Pais e1 = new Pais();
        e1.setNome( "Brasil" );
        e1.setSigla( "BR" );
        
        Pais e2 = new Pais();
        e2.setNome( "Egito" );
        e2.setSigla( "EJ" );
        
        dao.salvar( e1 );
        dao.salvar( e2 );
        
        dao.fecharEntityManager();
        
    }
    
}
