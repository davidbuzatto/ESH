/**
 * Funções utilitárias.
 *
 * @author    David Buzatto
 * @copyright (c) 2012, by David Buzatto
 * @date      03 de Julho de 2012
 *
 */

Ext.ns( "Uteis" );

/**
 * Obtém o idioma do navegador.
 */
Uteis.getIdioma = function( opcoes ) {

    var idioma = navigator.language || navigator.userLanguage;
    idioma = idioma.substring( 0, 2 );

    if ( idioma != "pt" )
        idioma = "en";

    return idioma;

};

/**
 * Evita o timeout da sessão configurada.
 */
Uteis.evitarTimeout = function( opcoes ) {
    
    Ext.TaskManager.start({
        run: function() {
            $.ajax({
                url: opcoes.contexto + "/dummy.jsp",
                dataType: "html",
                success: function( data ) {
                }
            });
        },
        interval: Math.floor( opcoes.intervaloRequisicao * 60 * 1000 )
    });

};

/**
 * Cria e exibe uma mensagem de erro, passando um objeto de erro.
 */
Uteis.exibirMensagemErro = function( objetoErro ) {
    
    var mensagem = "";
    
    if ( objetoErro.erro ) {
        
        var trace = objetoErro.erro.stackTrace;
        var traceMsg = "";
        
        for ( var i in trace ) {
            if ( i != 0 ) {
                traceMsg += "<br/>&nbsp;&nbsp;&nbsp;&nbsp;";
            }
            traceMsg += trace[i];
        }
        
        mensagem = "Mensagem: " + objetoErro.erro.mensagem + "<br/><br/>";
        mensagem += "Stack Trace: " + traceMsg;
        
    } else {
        mensagem = objetoErro.mensagem;
    }
    
    Ext.Msg.show({
        title: "ERRO",
        msg: mensagem,
        buttons: Ext.Msg.OK,
        icon: Ext.Msg.ERROR
    });
    
};

/**
 * Cria uma cor do ExtJS a partir da definição RGB em hexadecimal (6 dígitos).
 */
Uteis.criarCor = function( rgbHex ) {
    
    rgbHex = rgbHex.replace( "#", "" );
    
    var r = rgbHex.substring( 0, 2 );
    var g = rgbHex.substring( 2, 4 );
    var b = rgbHex.substring( 4, 6 );
    
    return new Ext.draw.Color(
            parseInt( r, 16 ),
            parseInt( g, 16 ),
            parseInt( b, 16 ));
    
};

/**
 * Cria uma cor invertida do ExtJS a partir de uma cor.
 */
Uteis.criarCorInvertida = function( cor ) {
    
    return new Ext.draw.Color(
            255 - cor.getRed(),
            255 - cor.getGreen(),
            255 - cor.getBlue());
    
};

/**
 * Função para executar o logoff.
 */
Uteis.executarLogoff = function( opcoes ) {
    Ext.Ajax.request({
        url: opcoes.contexto + "/servlets/logoff",
        success: function() {
            window.location = opcoes.contexto + "/index.jsp";
        },
        failure: function() {
            window.location = opcoes.contexto + "/index.jsp";
        }
    });
};

/**
 * Cria um novo modelo do ExtJS (alternatica para as associations dos 
 * modelos padrão).
 */
Uteis.createModel = function( modelData ) {
    
    var fields = modelData.fields;
    var processedFields = [];
    var normalFields = [];
    var relationFields = [];
    
    for ( var i in fields ) {
        
        if ( fields[i].type ) {
            
            switch ( fields[i].type ) {

                case "auto":
                case "string":
                case "int":
                case "float":
                case "boolean":
                case "date":
                    normalFields.push( fields[i] );
                    break;
                    
                default:
                    
                    var relationField = fields[i];
                    
                    var prefix = relationField.name + ".";
                    var modelInstance = Ext.create( relationField.type );
                    
                    modelInstance.fields.each( function( item, index, length ) {
                        
                        var newField = {};
                        
                        newField["name"] = prefix + item.name;
                        newField["type"] = item.type.type;
                        
                        newField["convert"] = item.convert;
                        newField["dateFormat"] = item.dateFormat;
                        newField["defaultValue"] = item.defaultValue;
                        newField["mapping"] = item.mapping;
                        newField["persist"] = item.persist;
                        newField["sortDir"] = item.sortDir;
                        newField["sortType"] = item.sortType;
                        newField["useNull"] = item.useNull;
                        
                        relationFields.push( newField );
                        
                    });
                    
                    break;

            }
            
        } else {
            normalFields.push( fields[i] );
        }
        
    }
            
    processedFields = normalFields.concat( relationFields );
    
    // debugging
    /*console.log( "*** " + modelData.name );
    for ( var i in processedFields ) {
        console.log( processedFields[i] );
    }*/
    
    Ext.define( modelData.name, {
        extend: "Ext.data.Model",
        fields: processedFields
    });
    
};

/**
 * Cria um validador (uma função) para confirmação de valores iguais em dois campos.
 */
Uteis.criarValidadorConfirmacao = function( nomeCampoComparar, labelCampoComparar ) {
    
    return function( value ) {
        if ( value == this.findParentByType( "form" ).getForm().findField( nomeCampoComparar ).getValue() ) {
            return true;
        } else {
            return "Repita o mesmo valor informado no campo \"" + labelCampoComparar + "\"!";
        }
    }
    
};

/**
 * Valida o valor de um campo, verificando se o valor é um email que não existe.
 */
Uteis.validarEmailAjax = function( opcoes ) {
    
    var campo = opcoes.campo;
    var contexto = opcoes.contexto;
    var existeEmail = false;
            
    var parametrosConsulta = [{
        campo: "email",
        valor: campo.getValue(),
        tipo: "string"
    }];

    $.ajax({
        url: contexto + "/servlets/jsonEntidades",
        type: "post",
        data: {
            entidade: "Usuario",
            tipoConsulta: "especifica",
            parametros: Ext.encode( parametrosConsulta )
        },
        async: false,
        dataType: "json",
        cache: false,
        success: function( data, textStatus ) {
            existeEmail = data.total == 1;
        }
    });
    
    return !existeEmail
    
};