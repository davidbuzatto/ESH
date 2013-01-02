/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package esh.uteis;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Classe com métodos utilitários estáticos.
 * 
 * @author David Buzatto
 */
public class Uteis {
    
    /**
     * Gera o JSON que representa o retorno de uma operação.
     * 
     * @param exc Excessão para ser usada na geração. Se null, representa que
     * deve ser gerado um JSON de sucesso "{ success: true }", enquanto se 
     * houver alguma excessão será gerado um JSON de erro no formato:
     * 
     * {
     *     success: false,
     *     mensagem: Mensagem da excessão,
     *     stackTrace: [ "trace1", "trace2", ..., "traceN" ]
     * }
     * 
     * @return JSON da operação.
     */
    public static String gerarJSONRetornoOperacao( Exception exc ) {
        
        StringBuilder sb = new StringBuilder();
        
        if ( exc == null ) {
            sb.append( "{ \"success\": true }" );
        } else {
            
            sb.append( "{ \"success\": false, \"erro\": {" );
            
            sb.append( "\"mensagem\": \"" );
            sb.append( exc.getMessage() );
            
            sb.append( "\", \"stackTrace\": [" );
            
            boolean primeiro = true;
            
            for ( StackTraceElement e : exc.getStackTrace() ) {
                
                if ( primeiro ) {
                    primeiro = false;
                } else {
                    sb.append( ", " );
                }
                
                sb.append( "\"" ).append( e ).append( "\"" );
            }
            
            sb.append( "]}}" );
            
        }
        
        return sb.toString();
        
    }
    
    /**
     * Método para andar pela árvore de objetos e configurar os valores padrão.
     * 
     * @param alvo Objeto a ser verificado.
     */
    public static void atravessarConfigurandoValorPadrao( Object alvo ) {
        
        try {
            
            for ( Field f : alvo.getClass().getDeclaredFields() ) {
                
                // pode mudar...
                f.setAccessible( true );
                
                // é null? então cria alguma coisa
                if ( f.get( alvo ) == null ) {
                    
                    // nova instância para o campo atual
                    Object novaInstancia = null;
                    
                    // precisa ir para o próximo nível?
                    boolean deveAtravessar = false;
                    
                    switch ( f.getType().getSimpleName() ) {
                        
                        case "Byte":
                        case "Short":
                        case "Integer":
                            novaInstancia = 0;
                            break;
                            
                        case "Long":
                            novaInstancia = 0L;
                            break;
                            
                        case "Float":
                            novaInstancia = 0F;
                            break;
                            
                        case "Double":
                            novaInstancia = 0D;
                            break;
                        
                        case "Character":
                            novaInstancia = '\0';
                            break;
                            
                        case "Boolean":
                            novaInstancia = Boolean.FALSE;
                            break;
                            
                        case "String":
                            novaInstancia = "";
                            break;
                            
                        case "List":
                            novaInstancia = new ArrayList();
                            break;
                            
                        case "Set":
                            novaInstancia = new HashSet();
                            break;
                            
                        default:
                            // chamando o construtor padrão para os tipos padrão
                            novaInstancia = f.getType().newInstance();
                            deveAtravessar = true;
                            break;
                        
                    }
                    
                    f.set( alvo, novaInstancia );
                    
                    if ( deveAtravessar ) {
                        atravessarConfigurandoValorPadrao( novaInstancia );
                    }
                    
                }

            }
            
        } catch ( IllegalAccessException | InstantiationException exc ) {
            exc.printStackTrace();
        }
        
    }
    
}
