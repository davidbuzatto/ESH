/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package esh.testes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import esh.uteis.ParametroConsulta;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author David
 */
public class TestesJSON {
    
    public static void main( String[] args ) {
        
        String s = "[{ \"campo\": \"x\", \"valor\": \"y\", \"tipo\": \"z\" }, { \"campo\": \"a\", \"valor\": \"b\", \"tipo\": \"c\" }]";
        Gson gson = new Gson();
        
        Type tipoLista = new TypeToken<List<ParametroConsulta>>(){}.getType();
        List<ParametroConsulta> t = gson.fromJson( s, tipoLista );
        
        for ( ParametroConsulta ti : t ) {
            System.out.println( ti );
        }
        
        System.out.println( t );
        
    }
    
}
