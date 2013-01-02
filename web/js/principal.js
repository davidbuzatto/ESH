/**
 * Arquivo de código da interface principal.
 *
 * @author    David Buzatto
 * @copyright (c) 2012, by David Buzatto
 * @date      03 de Julho de 2012
 *
 */

Ext.ns( "Principal" );

/**
 * Cria o ambiente principal da aplicação.
 */
Principal.criarAmbiente = function( opcoes ) {
    
    var contexto = opcoes.contexto;
    var icones = opcoes.icones;
                        
    var barraMenu = Ext.create( "Ext.toolbar.Toolbar", {
        region: "north",
        border: 0,
        items: [{
            text: "Cadastros",
            icon: icones + "book_edit.png",
            menu: [{
                text: "Séries/Semestres/Módulos",
                icon: icones + "tag_blue.png",
                listeners: {
                    click: {
                        fn: function( item, evt, opts ) {
                        }
                    }
                }
            }, {
                text: "Cursos",
                icon: icones + "tag_green.png",
                listeners: {
                    click: {
                        fn: function( item, evt, opts ) {
                        }
                    }
                }
            }, {
                text: "Áreas",
                icon: icones + "tag_red.png",
                listeners: {
                    click: {
                        fn: function( item, evt, opts ) {
                        }
                    }
                }
            }, {
                text: "Campi",
                icon: icones + "tag_orange.png",
                listeners: {
                    click: {
                        fn: function( item, evt, opts ) {
                            Campus.criarJanelaConsulta( opcoes );
                        }
                    }
                }
            }, {
                text: "Instituições",
                icon: icones + "tag_yellow.png",
                listeners: {
                    click: {
                        fn: function( item, evt, opts ) {
                            Instituicao.criarJanelaConsulta( opcoes );
                        }
                    }
                }
            }, "-", {
                text: "Salas",
                icon: icones + "tag_pink.png",
                listeners: {
                    click: {
                        fn: function( item, evt, opts ) {
                        }
                    }
                }
            }, {
                text: "Prédios",
                icon: icones + "tag_purple.png",
                listeners: {
                    click: {
                        fn: function( item, evt, opts ) {
                        }
                    }
                }
            }, "-", {
                text: "Professores",
                icon: icones + "user_suit.png",
                listeners: {
                    click: {
                        fn: function( item, evt, opts ) {
                        }
                    }
                }
            }, {
                text: "Disciplinas",
                icon: icones + "script.png",
                listeners: {
                    click: {
                        fn: function( item, evt, opts ) {
                        }
                    }
                }
            }, "-", {
                text: "Horários",
                icon: icones + "clock.png",
                listeners: {
                    click: {
                        fn: function( item, evt, opts ) {
                        }
                    }
                }
            }, {
                text: "Turnos",
                icon: icones + "weather_sun.png",
                listeners: {
                    click: {
                        fn: function( item, evt, opts ) {
                        }
                    }
                }
            }, {
                text: "Horas de Atividade",
                icon: icones + "time.png",
                listeners: {
                    click: {
                        fn: function( item, evt, opts ) {
                        }
                    }
                }
            }, {
                text: "Modelos de Disponibilidade",
                icon: icones + "clock_red.png",
                listeners: {
                    click: {
                        fn: function( item, evt, opts ) {
                        }
                    }
                }
            }, {
                text: "Tipos de Atividade",
                icon: icones + "bricks.png",
                listeners: {
                    click: {
                        fn: function( item, evt, opts ) {
                        }
                    }
                }
            }, "-", {
                text: "Usuários",
                icon: icones + "group.png",
                listeners: {
                    click: {
                        fn: function( item, evt, opts ) {
                            Usuario.criarJanelaConsulta( opcoes );
                        }
                    }
                }
            }, "-", {
                text: "Cidades",
                icon: icones + "building.png",
                listeners: {
                    click: {
                        fn: function( item, evt, opts ) {
                            Cidade.criarJanelaConsulta( opcoes );
                        }
                    }
                }
            }, {
                text: "Estados",
                icon: icones + "flag_red.png",
                listeners: {
                    click: {
                        fn: function( item, evt, opts ) {
                            Estado.criarJanelaConsulta( opcoes );
                        }
                    }
                }
            }, {
                text: "Países",
                icon: icones + "world.png",
                listeners: {
                    click: {
                        fn: function( item, evt, opts ) {
                            Pais.criarJanelaConsulta( opcoes );
                        }
                    }
                }
            }]
        }, {
            text: "Visualizações",
            icon: icones + "report_picture.png",
            menu: [{
                text: "Mapa de Horário",
                icon: icones + "calendar.png",
                listeners: {
                    click: {
                        fn: function( item, evt, opts ) {
                        }
                    }
                }
            }, {
                text: "Mapa de Ocupação",
                icon: icones + "calendar_red.png",
                listeners: {
                    click: {
                        fn: function( item, evt, opts ) {
                        }
                    }
                }
            }]
        }, {
            text: "Relatórios",
            icon: icones + "report.png",
            menu: [{
                text: "Mapa de Horário",
                icon: icones + "calendar.png",
                listeners: {
                    click: {
                        fn: function( item, evt, opts ) {
                        }
                    }
                }
            }, {
                text: "Mapa de Ocupação",
                icon: icones + "calendar_red.png",
                listeners: {
                    click: {
                        fn: function( item, evt, opts ) {
                        }
                    }
                }
            }, {
                text: "Horário para Divulgação",
                icon: icones + "table.png",
                listeners: {
                    click: {
                        fn: function( item, evt, opts ) {
                        }
                    }
                }
            }]
        }, {
            text: "Minhas Disponibilidades",
            icon: icones + "clock_red.png",
            listeners: {
                click: {
                    fn: function( item, evt, opts ) {
                    }
                }
            }
        }, {
            text: "Meus Horários",
            icon: icones + "clock.png",
            listeners: {
                click: {
                    fn: function( item, evt, opts ) {
                    }
                }
            }
        }, {
            text: "Configurações",
            icon: icones + "cog.png",
            menu: [{
                text: "Configurações Locais",
                icon: icones + "wrench_orange.png",
                listeners: {
                    click: {
                        fn: function( item, evt, opts ) {
                        }
                    }
                }
            }, {
                text: "Configurações Globais",
                icon: icones + "wrench.png",
                listeners: {
                    click: {
                        fn: function( item, evt, opts ) {
                        }
                    }
                }
            }]
        }, "->", {
            text: "Dados Pessoais",
            icon: icones + "user_edit.png",
            listeners: {
                click: {
                    fn: function( item, evt, opts ) {
                    }
                }
            }
        }, {
            text: "Sair",
            icon: icones + "exit.png",
            listeners: {
                click: {
                    fn: function( item, evt, opts ) {
                        Ext.Msg.confirm(
                            "Sair",
                            "Deseja mesmo sair?",
                            function( btnId ) {
                                if ( btnId == 'yes' ) {
                                    Uteis.executarLogoff( opcoes );
                                }
                            }
                        );
            
                    }
                }
            }
        }]
    });

    var tabPanel = Ext.create( "Ext.tab.Panel", {
        region: "center",
        activeTab: 0,
        items: [{
            title: "Bem vindo(a)!",
            html: "Conteúdo..."
        }]
    });

    Ext.create( "Ext.container.Viewport", {
        layout: "border",
        renderTo: Ext.getBody(),
        items: [ barraMenu, tabPanel ]
    });
    
};
