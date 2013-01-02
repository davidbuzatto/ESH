/**
 * Arquivo de código da interface de cadastro de usuários.
 *
 * @author    David Buzatto
 * @copyright (c) 2012, by David Buzatto
 * @date      12 de Julho de 2012
 *
 */

Ext.ns( "Usuario" );
    
/**
 * Cria a janela de consulta de usuários.
 */
Usuario.criarJanelaConsulta = function( opcoes ) {
    
    var contexto = opcoes.contexto;
    var icones = opcoes.icones;
    var quantidadeResultadosPorPagina = opcoes.quantidadeResultadosPorPagina;
    var itemSelecionado = null;
        
    var storeUsuarios = Ext.create( "Ext.data.JsonStore", {
        model: "Modelos.Usuario",
        pageSize: quantidadeResultadosPorPagina,
        autoLoad: true,
        proxy: {
            type: "ajax",
            url: contexto + "/servlets/jsonEntidades",
            extraParams: {
                entidade: "Usuario",
                tipoConsulta: "completa",
                aplicarLimite: true
            },
            reader: {
                type: "json",
                root: "itens",
                totalProperty: "total"
            },
            listeners: {
                exception: {
                    fn: function( proxy, response, operation, opts ) {
                        var retorno = Ext.decode( response.responseText, true );
                        Uteis.exibirMensagemErro( retorno );
                    }
                }
            }
        },
        listeners: {
            beforeload: {
                fn: function( store, operation, opts ) {
                    tabelaCadastro.setDisabled( true );
                    containerStatusCarregando.setVisible( true );
                }
            },
            load: {
                fn: function( store, records, successful, opts ) {
                    
                    tabelaCadastro.setDisabled( false );
                    containerStatusCarregando.setVisible( false );
                    
                    itemSelecionado = null;
                    
                }
            }
        }
    });
            
    var tiposConsulta = Ext.create( "Ext.data.Store", {
        fields: [ "label", "tipoConsulta" ],
        data : [
            {"label": "Id", "tipoConsulta": Ext.create( "Modelos.TipoConsulta", {
                campo: "id",
                tipo: "numero"
            })}, {"label": "Nome", "tipoConsulta": Ext.create( "Modelos.TipoConsulta", {
                campo: "nome",
                tipo: "string"
            })}, {"label": "Sigla", "tipoConsulta": Ext.create( "Modelos.TipoConsulta", {
                campo: "sigla",
                tipo: "string"
            })}, {"label": "Nome País", "tipoConsulta": Ext.create( "Modelos.TipoConsulta", {
                campo: "nomePais",
                tipo: "string"
            })}
        ]
    });
    
    var submeterConsulta = function() {
        
        var form = formularioConsulta.getForm();
                            
        if ( form.isValid() ) {

            var proxy = storeUsuarios.getProxy();
            var valores = form.getValues();

            if ( !valores[ "obterTudo" ] ) {
                
                proxy.setExtraParam( "tipoConsulta", "especifica" );

                var parametrosConsulta = [{
                    campo: valores["tipoConsulta"].get( "campo" ),
                    valor: valores["valor"],
                    tipo: valores["tipoConsulta"].get( "tipo" )
                }];
                
                proxy.setExtraParam( "parametros", Ext.encode( parametrosConsulta ) );

            } else {
                proxy.setExtraParam( "tipoConsulta", "completa" );
            }
            
            barraPaginacao.moveFirst();

        }
        
    }
    
    var containerStatusCarregando = Ext.create( "Componentes.StatusCarregando", {
        icones: icones,
        hidden: true
    });
    
    var formularioConsulta = Ext.create( "Ext.form.Panel", {
        title: Mensagens.strings.consulta,
        icon: icones + "magnifier.png",
        region: "north",
        collapsed: true,
        collapsible: true,
        url: contexto + "/servlets/jsonEntidades",
        method: "post",
        defaultType: "uppertextfield",
        bodyPadding: 5,
        items: [{
            xtype: "combo",
            fieldLabel: "Consultar por",
            emptyText: Mensagens.strings.selecioneItem,
            labelAlign: "right",
            name: "tipoConsulta",
            store: tiposConsulta,
            displayField: "label",
            valueField: "tipoConsulta",
            allowBlank: false,
            forceSelection: true,
            typeAhead: true,
            queryMode: "local"
        }, {
            fieldLabel: "Valor",
            labelAlign: "right",
            name: "valor",
            allowBlank: false,
            listeners: {
                specialkey: {
                    fn: function( campo, evt, opts ) {
                        if ( evt.getKey() == evt.ENTER ) {
                            submeterConsulta();
                        }
                    }
                }
            }
        }, {
            xtype: "checkbox",
            fieldLabel: Mensagens.strings.obterTudo,
            labelAlign: "right",
            name: "obterTudo",
            listeners: {
                change: {
                    fn: function( check, novoValor, valorAntigo, opts ) {

                        var form = formularioConsulta.getForm();

                        if ( novoValor ) {
                            form.findField( "tipoConsulta" ).setDisabled( true );
                            form.findField( "valor" ).setDisabled( true );
                        } else {
                            form.findField( "tipoConsulta" ).setDisabled( false );
                            form.findField( "valor" ).setDisabled( false );
                        }

                    }
                }
            }
        }],
        buttons: [
            containerStatusCarregando, "->", {
            text: Mensagens.strings.consultar,
            icon: icones + "magnifier.png",
            listeners: {
                click: {
                    fn: function( item, evt, opts ) {
                        submeterConsulta();
                    }
                }
            }
        }, {
            text: Mensagens.strings.limpar,
            icon: icones + "bin.png",
            listeners: {
                click: {
                    fn: function( item, evt, opts ) {
                        formularioConsulta.getForm().reset();
                    }
                }
            }
        }],
        listeners: {
            beforerender: {
                fn: function( form, opts ) {
                    form.getForm().findField( "obterTudo" ).setValue( true );
                }
            }
        }
    });
    
    var barraPaginacao = Ext.create( "Ext.toolbar.Paging", {
        store: storeUsuarios,
        displayInfo: true
    });
    
    var tabelaCadastro = Ext.create( "Ext.grid.Panel", {
        region: "center",
        title: "Usuários Cadastrados",
        icon: icones + "table.png",
        bodyPadding: 5,
        store: storeUsuarios,
        columns: [
            {header: "Id",  dataIndex: "id", width: 50},
            {header: "Email", dataIndex: "email", width: 150},
            {header: "Nome", dataIndex: "nome", width: 100},
            {header: "Sobrenome", dataIndex: "sobrenome", width: 100},
            {header: "Ativo", dataIndex: "ativo", width: 50, xtype: "booleancolumn", 
                              trueText: "<span class='ativo'>" + Mensagens.strings.sim + "</span>", 
                              falseText: "<span class='inativo'>" + Mensagens.strings.nao + "</span>" },
            {header: "Tipo", dataIndex: "tipo", width: 150, 
                             renderer: function( value, metaData ){
                                 
                                 var tipo = "";
                                 
                                 switch ( value ) {
                                     
                                     case "SUPER":
                                         metaData.tdCls = "tdS";
                                         tipo = "Super usuário";
                                         break;
                                         
                                     case "GERENTE":
                                         metaData.tdCls = "tdG";
                                         tipo = "Gerente";
                                         break;
                                         
                                     case "COORDENADOR_AREA":
                                         metaData.tdCls = "tdCA";
                                         tipo = "Coordenador de Área";
                                         break;
                                         
                                     case "COORDENADOR_CURSO":
                                         metaData.tdCls = "tdCC";
                                         tipo = "Coordenador de Curso";
                                         break;
                                         
                                     case "PROFESSOR":
                                         metaData.tdCls = "tdP";
                                         tipo = "Professor";
                                         break;
                                     
                                 }
                                
                                 return "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + tipo;
                                 
                             }}
        ],
        bbar: [
            barraPaginacao
        ],
        listeners: {
            itemclick: function( view, record, item, index, evt, opts ) {
                itemSelecionado = record;
            },
            itemcontextmenu: function( view, record, item, index, evt, opts ) {
                
                evt.preventDefault();
                
                itemSelecionado = record;
                
                var menu = Ext.create( "Ext.menu.Menu", {
                    items: [{
                        text: Mensagens.strings.editar,
                        icon: icones + "pencil.png",
                        listeners: {
                            click: {
                                fn: function( item, evt, opts ) {
                                    if ( itemSelecionado ) {
                                        Usuario.criarJanelaCadastro( janela, "editar", itemSelecionado, barraPaginacao, opcoes );
                                    } else {
                                        Uteis.exibirMensagemErro({ 
                                            mensagem: Mensagens.strings.mensagemEdicao
                                        });
                                    }
                                }
                            }
                        }
                    },{
                        text: Mensagens.strings.excluir,
                        icon: icones + "delete.png",
                        listeners: {
                            click: {
                                fn: function( item, evt, opts ) {
                                    if ( itemSelecionado ) {
                                        excluirItem();
                                    } else {
                                        Uteis.exibirMensagemErro({ 
                                            mensagem: Mensagens.strings.mensagemExclusao
                                        });
                                    }
                                }
                            }
                        }
                    }]
                });
                
                menu.showAt( evt.getXY() );
                
            }
        }
    });
    
    var excluirItem = function() {
        
        Ext.Msg.show({
            title: Mensagens.strings.confirmacao,
            msg: Mensagens.strings.mensagemConfirmacao,
            buttons: Ext.Msg.YESNO,
            icon: Ext.Msg.QUESTION, 
            fn: function( btnId, text, opts ) {
                
                if ( btnId == "yes" ) {
                    
                    Ext.Ajax.request({
                        url: contexto + "/servlets/usuario",
                        method: "post",
                        params: {
                            acao: "excluir",
                            id: itemSelecionado.get( "id" )
                        },
                        success: function( response, options ) {
                            var resultado = Ext.decode( response.responseText );

                            if ( resultado.success ) {
                                submeterConsulta();
                            } else {
                                Uteis.exibirMensagemErro( resultado );
                            }
                        },
                        failure: function( response, options ) {
                            Uteis.exibirMensagemErro( Ext.decode( response.responseText ) );
                        }
                    });
                    
                }

            }
        });
        
    };
    
    var janela = Ext.create( "Ext.window.Window", {
        title: "Cadastro de Usuários",
        icon: icones + "group.png",
        height: 450,
        width: 650,
        bodyPadding: 5,
        modal: true,
        layout: "border",
        items: [
            formularioConsulta,
            tabelaCadastro
        ],
        buttons: [{
            text: Mensagens.strings.novo,
            icon: icones + "add.png",
            listeners: {
                click: {
                    fn: function( item, evt, opts ) {
                        Usuario.criarJanelaCadastro( janela, "inserir", null, barraPaginacao, opcoes );
                    }
                }
            }
        }, {
            text: Mensagens.strings.editar,
            icon: icones + "pencil.png",
            listeners: {
                click: {
                    fn: function( item, evt, opts ) {
                        if ( itemSelecionado ) {
                            Usuario.criarJanelaCadastro( janela, "editar", itemSelecionado, barraPaginacao, opcoes );
                        } else {
                            Uteis.exibirMensagemErro({ 
                                mensagem: Mensagens.strings.mensagemEdicao
                            });
                        }
                    }
                }
            }
        }, {
            text: Mensagens.strings.excluir,
            icon: icones + "delete.png",
            listeners: {
                click: {
                    fn: function( item, evt, opts ) {
                        if ( itemSelecionado ) {
                            excluirItem();
                        } else {
                            Uteis.exibirMensagemErro({ 
                                mensagem: Mensagens.strings.mensagemExclusao
                            });
                        }
                    }
                }
            }
        }, {
            text: Mensagens.strings.cancelar,
            icon: icones + "cross.png",
            listeners: {
                click: {
                    fn: function() {
                        janela.close();
                    }
                }
            }
        }],
        listeners: {
            beforerender: {
                fn: function( janela, opts ) {
                    storeUsuarios.data.clear();
                }
            },
            beforeclose: {
                fn: function( painel, opts ) {
                    storeUsuarios.data.clear();
                }
            }
        }
    });
    
    janela.show();
    
};


/**
 * Cria a janela de cadastro de usuários.
 */
Usuario.criarJanelaCadastro = function( pai, operacao, item, barraPaginacao, opcoes ) {
    
    var contexto = opcoes.contexto;
    var icones = opcoes.icones;
    
    var storeTipos = Ext.create( "Ext.data.Store", {
        fields: [ "label", "valor" ],
        data: [{
            label: "Super usuário", valor: "SUPER"
        }, {
            label: "Gerente", valor: "GERENTE"
        }, {
            label: "Coordenador de Área", valor: "COORDENADOR_AREA"
        }, {
            label: "Coordenador de Curso", valor: "COORDENADOR_CURSO"
        }, {
            label: "Professor", valor: "PROFESSOR"
        }]/*,
        listeners: {
            beforeload: {
                fn: function( store, operation, opts ) {
                    formularioCadastro.setDisabled( true );
                    containerStatusCarregando.setVisible( true );
                }
            },
            load: {
                fn: function( store, records, successful, opts ) {
                    if ( operacao == "editar" ) {
                        form.findField( "idPais" ).setValue( item.get( "pais.id" ) );
                    }
                    formularioCadastro.setDisabled( false );
                    containerStatusCarregando.setVisible( false );
                }
            }
        }*/
    });
    
    var storePaises = Ext.create( "Ext.data.JsonStore", {
        model: "Modelos.Pais",
        autoLoad: true,
        proxy: {
            type: "ajax",
            url: contexto + "/servlets/jsonEntidades",
            extraParams: {
                entidade: "Pais",
                tipoConsulta: "completa"
            },
            reader: {
                type: "json",
                root: "itens",
                totalProperty: "total"
            },
            listeners: {
                exception: {
                    fn: function( proxy, response, operation, opts ) {
                        var retorno = Ext.decode( response.responseText, true );
                        Uteis.exibirMensagemErro( retorno );
                    }
                }
            }
        },
        listeners: {
            beforeload: {
                fn: function( store, operation, opts ) {
                    formularioCadastro.setDisabled( true );
                    containerStatusCarregando.setVisible( true );
                }
            },
            load: {
                fn: function( store, records, successful, opts ) {
                    if ( operacao == "editar" ) {
                        form.findField( "idPais" ).setValue( item.get( "pais.id" ) );
                    }
                    formularioCadastro.setDisabled( false );
                    containerStatusCarregando.setVisible( false );
                }
            }
        }
    });
    
    var containerStatusCarregando = Ext.create( "Componentes.StatusCarregando", {
        icones: icones,
        hidden: true
    });
    
    var formularioCadastro = Ext.create( "Ext.form.Panel", {
        xtype: "form",
        title: "Dados do Usuário",
        icon: icones + "page.png",
        url: contexto + "/servlets/usuario",
        method: "post",
        defaultType: "uppertextfield",
        bodyPadding: 5,
        items: [{
            xtype: "hidden",
            name: "id",
            value: "null"
        }, {
            xtype: "lowertextfield",
            fieldLabel: "Email",
            labelAlign: "right",
            name: "email",
            vtype: "email",
            allowBlank: false,
            maxLength: 70
        }, {
            xtype: "lowertextfield",
            fieldLabel: "Repita o Email",
            labelAlign: "right",
            name: "emailR",
            validator: Uteis.criarValidadorConfirmacao( "email", "Email" ),
            submitValue: false,
            allowBlank: false,
            maxLength: 70
        }, {
            xtype: "textfield",
            inputType: "password",
            fieldLabel: "Senha",
            labelAlign: "right",
            name: "senha",
            allowBlank: false,
            minLength: 8,
            maxLength: 20
        }, {
            xtype: "textfield",
            inputType: "password",
            fieldLabel: "Repita a Senha",
            labelAlign: "right",
            name: "senhaR",
            validator: Uteis.criarValidadorConfirmacao( "senha", "Senha" ),
            submitValue: false,
            allowBlank: false,
            minLength: 8,
            maxLength: 20
        }, {
            fieldLabel: "Nome",
            labelAlign: "right",
            name: "nome",
            allowBlank: false,
            maxLength: 40
        }, {
            fieldLabel: "Sobrenome",
            labelAlign: "right",
            name: "sobrenome",
            allowBlank: false,
            maxLength: 50
        }, {
            xtype: "checkbox",
            fieldLabel: "Ativo",
            labelAlign: "right",
            name: "ativo"
        }, {
            xtype: "fieldcontainer",
            itemId: "containerTipo",
            fieldLabel: "Tipo",
            labelAlign: "right",
            layout: "hbox",
            items: [{
                xtype: "combo",
                emptyText: Mensagens.strings.selecioneItem,
                name: "tipo",
                store: storeTipos,
                displayField: "label",
                valueField: "valor",
                allowBlank: false,
                forceSelection: true,
                typeAhead: true,
                queryMode: "local",
                listeners: {
                    select: {
                        fn: function( combo, records, opts ) {

                            var form = formularioCadastro.getForm();
                            var imagem = formularioCadastro.getComponent( "containerTipo" ).getComponent( "imagemTipo" );

                            form.findField( "idCampus" ).setDisabled( true );
                            form.findField( "idArea" ).setDisabled( true );
                            form.findField( "idCurso" ).setDisabled( true );
                            form.findField( "idProfessor" ).setDisabled( true );

                            if ( records[0] ) {

                                switch ( records[0].get( "valor" ) ) {

                                    case "SUPER":
                                        imagem.setSrc( icones + "user.png" );
                                        break;
                                        
                                    case "GERENTE":
                                        form.findField( "idCampus" ).setDisabled( false );
                                        imagem.setSrc( icones + "user_gray.png" );
                                        break;

                                    case "COORDENADOR_AREA":
                                        form.findField( "idArea" ).setDisabled( false );
                                        imagem.setSrc( icones + "user_green.png" );
                                        break;

                                    case "COORDENADOR_CURSO":
                                        form.findField( "idCurso" ).setDisabled( false );
                                        imagem.setSrc( icones + "user_orange.png" );
                                        break;

                                    case "PROFESSOR":
                                        form.findField( "idProfessor" ).setDisabled( false );
                                        imagem.setSrc( icones + "user_suit.png" );
                                        break;

                                }

                            }
                        }
                    }
                }
            }, {
                xtype: "tbspacer",
                width: 5
            }, {
                xtype: "image",
                itemId: "imagemTipo",
                padding: "3 0 0 0"
            }]
        }, {
            xtype: "combo",
            fieldLabel: "Campus",
            emptyText: Mensagens.strings.selecioneItem,
            labelAlign: "right",
            name: "idCampus",
            store: storePaises,
            displayField: "nome",
            valueField: "id",
            allowBlank: false,
            disabled: true,
            forceSelection: true,
            typeAhead: true,
            queryMode: "local"
        }, {
            xtype: "combo",
            fieldLabel: "Área",
            emptyText: Mensagens.strings.selecioneItem,
            labelAlign: "right",
            name: "idArea",
            store: storePaises,
            displayField: "nome",
            valueField: "id",
            allowBlank: false,
            disabled: true,
            forceSelection: true,
            typeAhead: true,
            queryMode: "local"
        }, {
            xtype: "combo",
            fieldLabel: "Curso",
            emptyText: Mensagens.strings.selecioneItem,
            labelAlign: "right",
            name: "idCurso",
            store: storePaises,
            displayField: "nome",
            valueField: "id",
            allowBlank: false,
            disabled: true,
            forceSelection: true,
            typeAhead: true,
            queryMode: "local"
        }, {
            xtype: "combo",
            fieldLabel: "Professor",
            emptyText: Mensagens.strings.selecioneItem,
            labelAlign: "right",
            name: "idProfessor",
            store: storePaises,
            displayField: "nome",
            valueField: "id",
            allowBlank: false,
            disabled: true,
            forceSelection: true,
            typeAhead: true,
            queryMode: "local"
        }]
    });
        
    var janela = Ext.create( "Ext.window.Window", {
        height: 430,
        width: 350,
        bodyPadding: 5,
        modal: true,
        layout: "fit",
        items: [
            formularioCadastro
        ],
        buttons: [
            containerStatusCarregando, "->", {
            text: Mensagens.strings.salvar,
            icon: icones + "accept.png",
            listeners: {
                click: {
                    fn: function( item, evt, opts ) {
                        
                        var form = formularioCadastro.getForm();
                        
                        if ( form.isValid() ) {
                            
                            var emailOk = Uteis.validarEmailAjax({
                                contexto: contexto,
                                campo: form.findField( "email" )
                            });
                        
                            if ( emailOk ) {
                                
                                form.submit({
                                    params: {
                                        acao: "salvar"
                                    },
                                    success: function( form, action ) {
                                        form.reset();
                                        janela.close();
                                    },
                                    failure: function( form, action ) {
                                        Uteis.exibirMensagemErro( action.result );
                                    },
                                    waitTitle: Mensagens.strings.aguarde,
                                    waitMsg: Mensagens.strings.enviandoDados
                                });
                                
                            } else {
                                
                                Uteis.exibirMensagemErro({ 
                                    mensagem: "O email fornecido já está em uso!"
                                });
                                
                            }
                            
                        }
                        
                    }
                }
            }
        }, {
            text: Mensagens.strings.cancelar,
            icon: icones + "cross.png",
            listeners: {
                click: {
                    fn: function() {
                        janela.close();
                    }
                }
            }
        }],
        listeners: {
            beforeclose: {
                fn: function( janela, opts ) {
                    if ( barraPaginacao ) {
                        barraPaginacao.doRefresh();
                    }
                }
            }
        }
    });
    
    if ( operacao == "inserir" ) {
        janela.title = "Novo Usuário";
        janela.icon = icones + "add.png";
    } else {
        janela.title = "Editar Usuário";
        janela.icon = icones + "pencil.png";
    }
    
    if ( item ) {
        
        var form = formularioCadastro.getForm();
        
        /*form.findField( "id" ).setValue( item.get( "id" ) );
        form.findField( "nome" ).setValue( item.get( "nome" ) );
        form.findField( "sigla" ).setValue( item.get( "sigla" ) );*/
        
    }
    
    janela.show();
    
};