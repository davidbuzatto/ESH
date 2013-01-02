/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package esh.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Fábrica de DAOs.
 * 
 * @author David Buzatto
 */
public class DaoFactory {
    
    private static EntityManagerFactory factory;
    
    static  {
        factory = Persistence.createEntityManagerFactory( "ESHPU" );
    }
    
    public static <T> Dao<T> getDao() {
        return new Dao<T>( factory.createEntityManager() );
    }
    
}
