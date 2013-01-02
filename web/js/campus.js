/**
 * Arquivo de código da interface de cadastro de campi.
 *
 * @author    David Buzatto
 * @copyright (c) 2012, by David Buzatto
 * @date      11 de Julho de 2012
 *
 */

Ext.ns( "Campus" );
    
/**
 * Cria a janela de consulta de campi.
 */
Campus.criarJanelaConsulta = function( opcoes ) {
    
    var contexto = opcoes.contexto;
    var icones = opcoes.icones;
    var quantidadeResultadosPorPagina = opcoes.quantidadeResultadosPorPagina;
    var itemSelecionado = null;
        
    var storeCampi = Ext.create( "Ext.data.JsonStore", {
        model: "Modelos.Campus",
        pageSize: quantidadeResultadosPorPagina,
        autoLoad: true,
        proxy: {
            type: "ajax",
            url: contexto + "/servlets/jsonEntidades",
            extraParams: {
                entidade: "Campus",
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
            { "label": "Id", "tipoConsulta": Ext.create( "Modelos.TipoConsulta", {
                campo: "id",
                tipo: "numero"
            })}, { "label": "Nome", "tipoConsulta": Ext.create( "Modelos.TipoConsulta", {
                campo: "nome",
                tipo: "string"
            })}, { "label": "Nome Instituição", "tipoConsulta": Ext.create( "Modelos.TipoConsulta", {
                campo: "nomeInstituicao",
                tipo: "string"
            })}, { "label": "Nome Cidade", "tipoConsulta": Ext.create( "Modelos.TipoConsulta", {
                campo: "nomeCidade",
                tipo: "string"
            })}
        ]
    });
    
    var submeterConsulta = function() {
        
        var form = formularioConsulta.getForm();
                            
        if ( form.isValid() ) {

            var proxy = storeCampi.getProxy();
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
        store: storeCampi,
        displayInfo: true
    });
    
    var tabelaCadastro = Ext.create( "Ext.grid.Panel", {
        region: "center",
        title: "Campi Cadastrados",
        icon: icones + "table.png",
        bodyPadding: 5,
        store: storeCampi,
        columns: [
            { header: "Id",  dataIndex: "id", width: 50 },
            { header: "Nome", dataIndex: "nome", width: 150 },
            { header: "Instituição", dataIndex: "instituicao.nome", width: 150 },
            { header: "Cidade", dataIndex: "cidade.nome", width: 150 }
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
                                        Campus.criarJanelaCadastro( janela, "editar", itemSelecionado, barraPaginacao, opcoes );
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
                        url: contexto + "/servlets/campus",
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
        title: "Cadastro de Campi",
        icon: icones + "tag_orange.png",
        height: 450,
        width: 560,
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
                        Campus.criarJanelaCadastro( janela, "inserir", null, barraPaginacao, opcoes );
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
                            Campus.criarJanelaCadastro( janela, "editar", itemSelecionado, barraPaginacao, opcoes );
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
                    storeCampi.data.clear();
                }
            },
            beforeclose: {
                fn: function( painel, opts ) {
                    storeCampi.data.clear();
                }
            }
        }
    });
    
    janela.show();
    
};


/**
 * Cria a janela de cadastro de campi.
 */
Campus.criarJanelaCadastro = function( pai, operacao, item, barraPaginacao, opcoes ) {
    
    var contexto = opcoes.contexto;
    var icones = opcoes.icones;
    
    var storeInstituicoes = Ext.create( "Ext.data.JsonStore", {
        model: "Modelos.Instituicao",
        autoLoad: true,
        proxy: {
            type: "ajax",
            url: contexto + "/servlets/jsonEntidades",
            extraParams: {
                entidade: "Instituicao",
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
                        form.findField( "idInstituicao" ).setValue( item.get( "instituicao.id" ) );
                    }
                    formularioCadastro.setDisabled( false );
                    containerStatusCarregando.setVisible( false );
                }
            }
        }
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
                        form.findField( "idPais" ).setValue( item.get( "cidade.estado.pais.id" ) );
                        storeEstados.load({
                            params: {
                                parametros: Ext.encode([{
                                    campo: "idPais",
                                    valor: item.get( "cidade.estado.pais.id" ),
                                    tipo: "numero"
                                }])
                            }
                        });
                    }
                    formularioCadastro.setDisabled( false );
                    containerStatusCarregando.setVisible( false );
                }
            }
        }
    });
    
    var storeEstados = Ext.create( "Ext.data.JsonStore", {
        model: "Modelos.Estado",
        proxy: {
            type: "ajax",
            url: contexto + "/servlets/jsonEntidades",
            extraParams: {
                entidade: "Estado",
                tipoConsulta: "especifica"
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
                        form.findField( "idEstado" ).setValue( item.get( "cidade.estado.id" ) );
                        storeCidades.load({
                            params: {
                                parametros: Ext.encode([{
                                    campo: "idEstado",
                                    valor: item.get( "cidade.estado.id" ),
                                    tipo: "numero"
                                }])
                            }
                        });
                    }
                    formularioCadastro.setDisabled( false );
                    containerStatusCarregando.setVisible( false );
                }
            }
        }
    });
    
    var storeCidades = Ext.create( "Ext.data.JsonStore", {
        model: "Modelos.Cidade",
        proxy: {
            type: "ajax",
            url: contexto + "/servlets/jsonEntidades",
            extraParams: {
                entidade: "Cidade",
                tipoConsulta: "especifica"
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
                        form.findField( "idCidade" ).setValue( item.get( "cidade.id" ) );
                        operacao = "";
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
        title: "Dados do Campus",
        icon: icones + "page.png",
        url: contexto + "/servlets/campus",
        method: "post",
        defaultType: "uppertextfield",
        bodyPadding: 5,
        items: [{
            xtype: "hidden",
            name: "id",
            value: "null"
        }, {
            fieldLabel: "Nome",
            labelAlign: "right",
            name: "nome",
            allowBlank: false,
            maxLength: 100
        }, {
            xtype: "combo",
            fieldLabel: "Instituicao",
            emptyText: Mensagens.strings.selecioneItem,
            labelAlign: "right",
            name: "idInstituicao",
            store: storeInstituicoes,
            displayField: "nome",
            valueField: "id",
            allowBlank: false,
            forceSelection: true,
            typeAhead: true,
            queryMode: "local"
        }, {
            xtype: "combo",
            fieldLabel: "País",
            emptyText: Mensagens.strings.selecioneItem,
            labelAlign: "right",
            name: "idPais",
            store: storePaises,
            displayField: "nome",
            valueField: "id",
            allowBlank: false,
            forceSelection: true,
            typeAhead: true,
            queryMode: "local",
            listeners: {
                select: {
                    fn: function( combo, records, opts ) {
                        if ( records[0] ) {
                            storeEstados.load({
                                params: {
                                    parametros: Ext.encode([{
                                        campo: "idPais",
                                        valor: records[0].get("id"),
                                        tipo: "numero"
                                    }])
                                }
                            });
                        } else {
                            storeEstados.removeAll();
                        }
                    }
                }
            }
        }, {
            xtype: "combo",
            fieldLabel: "Estado",
            emptyText: Mensagens.strings.selecioneItem,
            labelAlign: "right",
            name: "idEstado",
            store: storeEstados,
            displayField: "nome",
            valueField: "id",
            allowBlank: false,
            forceSelection: true,
            typeAhead: true,
            queryMode: "local",
            listeners: {
                select: {
                    fn: function( combo, records, opts ) {
                        if ( records[0] ) {
                            storeCidades.load({
                                params: {
                                    parametros: Ext.encode([{
                                        campo: "idEstado",
                                        valor: records[0].get("id"),
                                        tipo: "numero"
                                    }])
                                }
                            });
                        } else {
                            storeEstados.removeAll();
                        }
                    }
                }
            }
        }, {
            xtype: "combo",
            fieldLabel: "Cidade",
            emptyText: Mensagens.strings.selecioneItem,
            labelAlign: "right",
            name: "idCidade",
            store: storeCidades,
            displayField: "nome",
            valueField: "id",
            allowBlank: false,
            forceSelection: true,
            typeAhead: true,
            queryMode: "local"
        }]
    });
        
    var janela = Ext.create( "Ext.window.Window", {
        height: 250,
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
        janela.title = "Novo Campus";
        janela.icon = icones + "add.png";
    } else {
        janela.title = "Editar Campus";
        janela.icon = icones + "pencil.png";
    }
    
    if ( item ) {
        
        var form = formularioCadastro.getForm();
        
        form.findField( "id" ).setValue( item.get( "id" ) );
        form.findField( "nome" ).setValue( item.get( "nome" ) );
        
    }
    
    janela.show();
    
};