/**
 * Funções para o index.
 *
 * @author    David Buzatto
 * @copyright (c) 2012, by David Buzatto
 * @date      07 de Julho de 2012
 *
 */

var executarLogin = function( opcoes ) {
    
    $.ajax({
        url: opcoes.contexto + "/login",
        data: {
            email: $( "#fieldEmail" ).val(),
            senha: $( "#fieldSenha" ).val()
        },
        type: "post",
        dataType: "json",
        success: function ( data, textStatus ) {
            
            var erro = data.erro;
            
            if ( data.success ) {
                $( "#dialogoErro" ).dialog( "close" );
                window.location = opcoes.contexto + "/principal.jsp";
            } else if ( !erro ) {
                $( "#dialogoErro .conteudo" ).html( "Email e/ou senha incorretos ou usuário inativo!" );
                $( "#dialogoErro" ).dialog( "open" );
            } else {
                
                var trace = erro.stackTrace;
                var traceMsg = "";

                for ( var i in trace ) {
                    if ( i != 0 ) {
                        traceMsg += "\n&nbsp;&nbsp;&nbsp;&nbsp;";
                    }
                    traceMsg += trace[i];
                }

                var mensagem = "Mensagem: " + erro.mensagem + "\n\n";
                mensagem += "Stack Trace: " + traceMsg;
                
                $( "#dialogoErro .conteudo" ).html( mensagem );
                $( "#dialogoErro" ).dialog( "open" );
                
            }
        }
    });
    
};

var iniciar = function( opcoes ) {
    
    $( document ).ready( function(){
        
        $( "#dialogoErro" ).dialog({
            autoOpen: false,
            resizable: false,
            modal: true,
            draggable: false,
            buttons: {
                Ok: function() {
                    $( this ).dialog( "close" );
                }
            },
            close: function( event, ui ) {
                $( "#fieldSenha" ).focus();
            }
        });
    
        $( "#btnLogin" ).click( function(){
            executarLogin( opcoes );
        });
        
        $( "#fieldSenha" ).keypress( function( evt ){
            if ( evt.which == 13 ) {
                $( "#btnLogin" ).click();
            }
        });
        
        executarLogin(opcoes);

    });
    
}