/**
 * Arquivo principal da aplicação.
 *
 * @author    David Buzatto
 * @copyright (c) 2012, by David Buzatto
 * @date      03 de Julho de 2012
 *
 */

Ext.ns( "Aplicacao" );

/**
 * Inicia a aplicação.
 */
Aplicacao.iniciar = function( opcoes ) {
    
    Aplicacao.contexto = opcoes.contexto;
    
    Principal.criarAmbiente( opcoes );
    Uteis.evitarTimeout( opcoes );
    
    Usuario.criarJanelaConsulta( opcoes );
    
};
