/**
 * Componentes de interface gráfica.
 *
 * @author    David Buzatto
 * @copyright (c) 2012, by David Buzatto
 * @date      06 de Julho de 2012
 *
 */

Ext.ns( "Componentes" );

/**
 * Define o color picker.
 */
Ext.define( "Componentes.ColorPicker", {
    extend: "Ext.menu.ColorPicker",
    constructor: function( config ) {
        this.callParent( arguments );
        if ( this.icone ) {
            this.icone = " url(\"" + this.icone + "\") right no-repeat";
        } else {
            this.icone = "";
        }
    },
    listeners: {
        mouseleave: {
            fn: function( colorPicker, evt, opts ) {
                colorPicker.setVisible( false );
            }
        },
        select: {
            fn: function( colorPicker, color, opts ) {
                this.changeColor( color );
            }
        }
    },
    changeColor: function( color ) {
        
        if ( color ) {
            this.cor = Uteis.criarCor( "#" + color );
            this.corInvertida = Uteis.criarCorInvertida( this.cor );
        } else {
            this.cor = Uteis.criarCor( "#FFFFFF" );
            this.corInvertida = Uteis.criarCor( "#000000" );
        }
        
        if ( this.target ) {
            
            this.target.setValue( this.cor.toString().toUpperCase() );
            
            this.target.setFieldStyle({
                background: this.cor + this.icone,
                color: this.corInvertida,
                cursor: "pointer"
            });
            
        }
        
    }
});

/**
 * Cria um color picker a partir do color picker definido.
 */
Componentes.criarColorPicker = function( configs ) {
        
    var colorPicker = Ext.create( "Componentes.ColorPicker", {
        target: configs.target,
        icone: configs.icone,
        cor: Uteis.criarCor( "#FFFFFF" ),
        corInvertida: Uteis.criarCor( "#000000" ),
        
        /*width: 206,
        height: 166,
        colors: [ "000000", "434343", "666666", "999999", "B7B7B7", "CCCCCC", "D9D9D9", "EFEFEF", "F3F3F3", "FFFFFF", 
                  "980000", "FF0000", "FF9900", "FFFF00", "00FF00", "00FFFF", "4A86E8", "0000FF", "9900FF", "FF00FF", 
                  "E6B8AF", "F4CCCC", "FCE5CD", "FFF2CC", "D9EAD3", "D0E0E3", "C9DAF8", "CFE2F3", "D9D2E9", "EAD1DC", 
                  "DD7E6B", "EA9999", "F9CB9C", "FFE599", "B6D7A8", "A2C4C9", "A4C2F4", "9FC5E8", "B4A7D6", "D5A6BD", 
                  "CC4125", "E06666", "F6B26B", "FFD966", "93C47D", "76A5AF", "6D9EEB", "6FA8DC", "8E7CC3", "C27BA0", 
                  "A61C00", "CC0000", "E69138", "F1C232", "6AA84F", "45818E", "3C78D8", "3D85C6", "674EA7", "A64D79", 
                  "85200C", "990000", "B45F06", "BF9000", "38761D", "134F5C", "1155CC", "0B5394", "351C75", "741B47", 
                  "5B0F00", "660000", "783F04", "7F6000", "274E13", "0C343D", "1C4587", "073763", "20124D", "4C1130" ]*/
              
        width: 426,
        height: 306,      
        colors: [ "FFBFBF", "FFCFBF", "FFDFBF", "FFEFBF", "FFFFBF", "EFFFBF", "DFFFBF", "CFFFBF", "BFFFBF", "BFFFCF", "BFFFDF", "BFFFEF", "BFFFFF", "BFEFFF", "BFDFFF", "BFCFFF", "BFBFFF", "CFBFFF", "DFBFFF", "EFBFFF", "FFBFFF", 
                  "FF9999", "FFB399", "FFCC99", "FFE599", "FFFF99", "E5FF99", "CCFF99", "B3FF99", "99FF99", "99FFB3", "99FFCC", "99FFE5", "99FFFF", "99E5FF", "99CCFF", "99B3FF", "9999FF", "B399FF", "CC99FF", "E599FF", "FF99FF", 
                  "FF7373", "FF9673", "FFB973", "FFDC73", "FFFF73", "DCFF73", "B9FF73", "96FF73", "73FF73", "73FF96", "73FFB9", "73FFDC", "73FFFF", "73DCFF", "73B9FF", "7396FF", "7373FF", "9673FF", "B973FF", "DC73FF", "FF73FF", 
                  "FF4D4D", "FF7A4D", "FFA64D", "FFD24D", "FFFF4D", "D2FF4D", "A6FF4D", "7AFF4D", "4DFF4D", "4DFF7A", "4DFFA6", "4DFFD2", "4DFFFF", "4DD2FF", "4DA6FF", "4D7AFF", "4D4DFF", "7A4DFF", "A64DFF", "D24DFF", "FF4DFF", 
                  "FF2626", "FF5C26", "FF9326", "FFC926", "FFFF26", "C9FF26", "93FF26", "5CFF26", "26FF26", "26FF5C", "26FF93", "26FFC9", "26FFFF", "26C9FF", "2693FF", "265CFF", "2626FF", "5C26FF", "9326FF", "C926FF", "FF26FF", 
                  "FF0000", "FF4000", "FF8000", "FFBF00", "FFFF00", "BFFF00", "80FF00", "40FF00", "00FF00", "00FF40", "00FF80", "00FFBF", "00FFFF", "00BFFF", "0080FF", "0040FF", "0000FF", "4000FF", "8000FF", "BF00FF", "FF00FF", 
                  "D90000", "D93600", "D96D00", "D9A300", "D9D900", "A3D900", "6DD900", "36D900", "00D900", "00D936", "00D96D", "00D9A3", "00D9D9", "00A3D9", "006DD9", "0036D9", "0000D9", "3600D9", "6D00D9", "A300D9", "D900D9", 
                  "B20000", "B22D00", "B25900", "B28500", "B2B200", "85B200", "59B200", "2DB200", "00B200", "00B22D", "00B259", "00B285", "00B2B2", "0085B2", "0059B2", "002DB2", "0000B2", "2D00B2", "5900B2", "8500B2", "B200B2", 
                  "8C0000", "8C2300", "8C4600", "8C6900", "8C8C00", "698C00", "468C00", "238C00", "008C00", "008C23", "008C46", "008C69", "008C8C", "00698C", "00468C", "00238C", "00008C", "23008C", "46008C", "69008C", "8C008C", 
                  "660000", "661A00", "663300", "664C00", "666600", "4C6600", "336600", "1A6600", "006600", "00661A", "006633", "00664C", "006666", "004C66", "003366", "001A66", "000066", "1A0066", "330066", "4C0066", "660066", 
                  "400000", "401000", "402000", "403000", "404000", "304000", "204000", "104000", "004000", "004010", "004020", "004030", "004040", "003040", "002040", "001040", "000040", "100040", "200040", "300040", "400040", 
                  "FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF", 
                  "FFFFFF", "EEEEEE", "DDDDDD", "CCCCCC", "BBBBBB", "AAAAAA", "999999", "888888", "777777", "666666", "555555", "444444", "333333", "222222", "111111", "000000", "000000", "FF0000", "FF7F00", "FFFF00", "7FFF00", 
                  "FFFFFF", "F0F0E1", "DFDFD0", "CECEBF", "BDBDAE", "ACAC9D", "9B9B8C", "8A8A7B", "79796A", "686859", "575748", "464637", "353526", "242415", "131304", "000000", "000000", "007FFF", "00FFFF", "00FF7F", "00FF00", 
                  "FFFFFF", "ECECFB", "DBDBEA", "CACAD9", "B9B9C8", "A8A8B7", "9797A6", "868695", "757584", "646473", "535362", "424251", "313140", "20202F", "0F0F1E", "000000", "000000", "0000FF", "7F00FF", "FF00FF", "FF007F" ]
              
    });
    
    return colorPicker;
    
};

/**
 * Componente ColorPickerField usando o color picker criado pela função criarColorPicker.
 */
Ext.define( "Componentes.ColorPickerField", {
    extend: "Ext.form.field.Text",
    alias: "widget.colorpickerfield",
    colorPicker: null,
    constructor: function( config ) {
        this.callParent( arguments );
        this.colorPicker = Componentes.criarColorPicker({ 
            target: this,
            icone: config.icone
        });
    },
    readOnly: true,
    listeners: {
        afterrender: {
            fn: function( field, opts ) {
                
                field.colorPicker.changeColor( field.getValue().replace( "#", "" ) );
                
                field.getEl().on( "click", function( evt ){
                    field.colorPicker.showAt( evt.getXY() );
                });
                
            }
        },
        beforedestroy: {
            fn: function( field, ops ) {
                Ext.destroy( field.colorPicker );
            }
        },
        change: {
            fn: function( field, newValue, oldValue, opts ) {
                field.colorPicker.changeColor( newValue.replace( "#", "" ) );
            }
        }
    }
});

/**
 * Componente UpperTextField que é um Ext.form.field.Text que insere apenas 
 * letras maiúsculas.
 */
Ext.define( "Componentes.UpperTextField", {
    extend: "Ext.form.field.Text",
    alias: "widget.uppertextfield",
    fieldStyle: "text-transform: uppercase",
    enableKeyEvents: true,
    constructor: function( config ) {
        this.callParent( arguments );
    },
    listeners: {
        keyup: {
            fn: function( field, evt, opts ) {
                field.setValue( field.getValue().toUpperCase() );
            }
        }
    }
});

/**
 * Componente LowerTextField que é um Ext.form.field.Text que insere apenas 
 * letras minúsculas.
 */
Ext.define( "Componentes.LowerTextField", {
    extend: "Ext.form.field.Text",
    alias: "widget.lowertextfield",
    fieldStyle: "text-transform: lowercase",
    enableKeyEvents: true,
    constructor: function( config ) {
        this.callParent( arguments );
    },
    listeners: {
        keyup: {
            fn: function( field, evt, opts ) {
                field.setValue( field.getValue().toLowerCase() );
            }
        }
    }
});

/**
 * Componente para mostrar o status de carregando.
 */
Ext.define( "Componentes.StatusCarregando", {
    extend: "Ext.container.Container",
    constructor: function( config ) {
        this.callParent( arguments );
    },
    layout: "hbox",
    items: [{
        xtype: "image",
        itemId: "animacao"
    }, {
        xtype: "tbspacer",
        width: 5
    }, {
        xtype: "label",
        text: Mensagens.strings.carregando
    }],
    listeners: {
        beforerender: {
            fn: function( container, opts ) {
                container.getComponent( "animacao" ).setSrc( container.icones + "loading.gif ");
            }
        }
    }
});