/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package esh.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * Estratégia de exclusão de campos para o Gson usando a anotação @ExcluirJSON.
 * 
 * @author David Buzatto
 */
public class EstrategiaExclusaoJSON implements ExclusionStrategy {

    @Override
    public boolean shouldSkipField( FieldAttributes fa ) {
        // verifica se tem a anotação da exclusão do JSON
        return fa.getAnnotation( ExcluirJSON.class ) != null;
    }

    @Override
    public boolean shouldSkipClass( Class<?> type ) {
        // aceita qualquer classe
        return false;
    }
    
}
