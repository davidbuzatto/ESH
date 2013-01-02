// indica se um item está no processo de arraste
var arrastando = false;

// todos os objetos de drop da interface
var drops = [];

// todos os objetos de drag da interface
var drags = [];

function montar( opcoes ) {

    var colunas = opcoes.colunas ? opcoes.colunas : [];
    var altura = opcoes.altura ? opcoes.altura : 10;
    var quantidadeLinhas = opcoes.quantidadeLinhas ? opcoes.quantidadeLinhas : 1;
    var corPar = opcoes.corPar ? opcoes.corPar : "#FFF";
    var corImpar = opcoes.corImpar ? opcoes.corImpar : "#EEE";

    if ( opcoes.container ) {

        var container = opcoes.container;
        var espacamento = 0;

        for ( var c = 0; c < colunas.length; c++ ) {

            var coluna = colunas[c];
            var sufixo = coluna.sufixo;
            var titulo = coluna.titulo ? coluna.titulo : "";
            var labels = coluna.labels ? coluna.labels : [];
            var largura = coluna.largura ? coluna.largura : 10;

            if ( c != 0 ) {
                espacamento += colunas[c-1].largura;
            }

            for ( var i = 0; i <= quantidadeLinhas; i++ ) {

                var idDrop = container + "_div_" + sufixo + "_" + i;

                drops[idDrop] = {
                    container: container,
                    sufixo: sufixo,
                    label: labels[i-1] ? labels[i-1] : "",
                    largura: largura,
                    altura: altura,
                    x: 0, // gerado abaixo (*)
                    y: 0, // gerado abaixo,
                    corOut: "", // obtido abaixo (**)
                    corOver: coluna.corOver,  // se não for definida, será a mesma do out (verificado abaixo) (***)
                    espacamento: espacamento,
                    permitirDropDe: coluna.aceitarSomente,
                    permitirDropAte: coluna.aceitarAte,
                    drags: []
                };

                var drop = drops[idDrop];
                var codigoNovoDrop = "<div id='" + idDrop + "' class='to_" + drop.sufixo + "' ";

                // (**)
                if ( i % 2 == 0 ) {
                    drop.corOut = corPar;
                } else {
                    drop.corOut = corImpar;
                }

                // (***)
                if ( !drop.corOver ) {
                    drop.corOver = drop.corOut;
                }

                codigoNovoDrop += " style='background-color: " + drop.corOut + ";";

                // título
                if ( i == 0 ) {
                    codigoNovoDrop += " text-align: center;'><div style='line-height: " + drop.altura + "px;'>" +
                        titulo + "</div>";
                } else { // drops normais
                    codigoNovoDrop += "'><div style='line-height: " + 
                        drop.altura + "px; padding-right: 4px; padding-left: 4px;'>" + 
                        drop.label + "</div>";
                }

                codigoNovoDrop += "</div>";

                $( "#" + drop.container ).append( codigoNovoDrop );

            }

            for ( var i = 0; i <= quantidadeLinhas; i++ ) {

                var idDrop = container + "_div_" + sufixo + "_" + i;
                var seletorDrop = "#" + idDrop;
                var drop = drops[idDrop];

                $( seletorDrop ).css( "height", drop.altura + "px" );
                $( seletorDrop ).css( "width", drop.largura + "px" );

                // atualizando x e y do drop (*)
                drop.x = drop.espacamento;
                drop.y = drop.altura * i;

                $( seletorDrop ).css( "left", drop.x + "px" );
                $( seletorDrop ).css( "top", drop.y + "px" );

                if ( coluna.droppable && i > 0 ) {

                    $( seletorDrop ).droppable({
                        drop: function( evt, ui ) {

                            var thisId = $(this).attr( "id" );
                            var drop = drops[thisId];

                            var draggable = ui.draggable;
                            var draggableId = draggable.attr( "id" );

                            var itensDrag = drop.drags;

                            // verifica se o id do drag está configurado no permitirDropDe do drop
                            // e se o limite de drags do drop está correto.
                            if ( draggableId.search( drop.permitirDropDe ) != -1 && 
                                itensDrag.length < drop.permitirDropAte ) {

                                // insere um draggable na lista de itens do droppable
                                var existe = false;

                                for ( var i in itensDrag ) {
                                    if ( itensDrag[i] == draggableId ) {
                                        existe = true;
                                        break;
                                    }
                                }

                                if ( !existe ) {
                                    itensDrag[itensDrag.length] = draggable.attr( "id" );
                                }

                                // reorganiza os draggables
                                organizarDrags( container, $(this) );

                                // exibe o botão de remoção
                                $( "#" + draggableId + " .removeDrag" ).fadeIn();

                                // indica que o item foi inserido
                                drags[draggableId].inserido = true;

                                // troca a cor do drop quando um item for inserido
                                $( "#" + thisId ).css( "background-color", drop.corOver );

                            }

                        },
                        out: function( evt, ui ) {

                            var thisId = $(this).attr( "id" );
                            var drop = drops[thisId];
                            var itens = drop.drags;

                            if ( itens ) {

                                var draggableId = ui.draggable.attr( "id" );

                                // esconde o botão de remoção
                                $( "#" + draggableId + " .removeDrag" ).fadeOut();

                                // remove da lista de drags do drop
                                for ( var i in itens ) {
                                    if ( itens[i] == draggableId ) {
                                        itens.splice( i, 1 );
                                        organizarDrags( container, $(this) );
                                        break;
                                    }
                                }

                            }

                        }

                    });

                }

            }

        }

    }

}

/**
 * Organiza os drags dentro dos drops.
 */
function organizarDrags( container, droppable ) {

    var droppableId = droppable.attr( "id" );
    var drop = drops[droppableId];
    var itens = drop.drags;
    var cont = 0;
    var tamanhoItem = Math.floor( drop.largura / itens.length );
    var base = 0;

    for ( var i in itens ) {

        var idDrag = itens[i];

        // drag
        var drag = drags[idDrag];

        // componente drag
        var dragCp = $( "#" + idDrag );

        var xContainer = parseInt( $( "#" + container ).css( "left" ), 10 );
        var yContainer = parseInt( $( "#" + container ).css( "top" ), 10 );

        // atualizando valores do drag
        drag.y = drop.y + yContainer + 2;
        drag.largura = tamanhoItem - 7;
        drag.altura = drop.altura - 7;

        // replicando alterações
        dragCp.css( "top", drag.y + "px" );            
        dragCp.css( "width", drag.largura + "px" );
        dragCp.css( "height", drag.altura + "px" );

        // label interno
        $( "#" + idDrag + " " + ".dragLabel" ).css( "line-height", drag.altura + "px" );

        if ( cont == 0 ) {
            drag.x = drop.x + xContainer + 2;
            base = drag.x;
            cont++;
        } else {
            drag.x = tamanhoItem * cont++ + base;
        }

        dragCp.css( "left", drag.x + "px" );

    }

    if ( itens.length == 0 ) {
        // volta a cor ao normal caso o drop esteja vazio
        $( "#" + droppableId ).css( "background-color", drop.corOut );
    }

}

/**
 * Cria o componente e o objeto que representam um drag.
 */
function criarDrag( opcoes ) {

    var id = opcoes.id;
    var sufixo = opcoes.sufixo;
    var idDrag = "div_" + sufixo + "_" + id;
    var container = opcoes.container;

    drags[idDrag] = {
        id: id,
        sufixo: sufixo,
        label: opcoes.label ? opcoes.label : "",
        largura: opcoes.largura ? opcoes.largura : 10,
        altura: opcoes.altura ? opcoes.altura : 10,
        larguraInicial: opcoes.largura ? opcoes.largura : 10, // usado para animação de remoção
        alturaInicial: opcoes.altura ? opcoes.altura : 10, // usado para animação de remoção
        x: opcoes.x,
        y: opcoes.y,
        xInicial: opcoes.x, // usado para animação de remoção
        yInicial: opcoes.y, // usado para animação de remoção
        tooltip: opcoes.tooltip ? opcoes.tooltip : "",
        corFundo: opcoes.corFundo ? opcoes.corFundo : "#FFF",
        corBorda: opcoes.corBorda ? opcoes.corBorda : "#000",
        corLabel: opcoes.corLabel ? opcoes.corLabel : "#000",
        inserido: false
    }

    var drag = drags[idDrag];

    var codigoNovoDrag = "<div id='" + idDrag + 
        "' class='to_" + drag.sufixo + 
        "' style='background-color: " + drag.corFundo + 
        "; border-color: " + drag.corBorda + 
        "; color: " + drag.corLabel + 
        "; left: " + drag.x + 
        "px; top: " + drag.y + 
        "px;' >";

    codigoNovoDrag += "<div id='remover_" + idDrag + 
        "' class='removeDrag' style='background-color: " + drag.corBorda + 
        "; color: " + drag.corFundo + ";'>X</div>";

    codigoNovoDrag += "<div id='ajuda_" + idDrag + 
        "' class='ajudaDrag' style='background-color: " + drag.corBorda + 
        "; color: " + drag.corFundo + 
        ";'>?</div>";

    codigoNovoDrag += "<div class='dragLabel' style='line-height: " + drag.altura + "px;'>" + 
        drag.label.toUpperCase() + "</div></div>";
    
    if ( container ) {
        $( "#" + container ).append( codigoNovoDrag );
    } else {
        $( "body" ).append( codigoNovoDrag );
    }

    // eventos de remoção e ajuda
    $( "#remover_" + idDrag ).click( function( event ){
        removerDrag( event );
    });

    $( "#ajuda_" + idDrag ).hover( function( event ){ // mouseover

        var drag = drags[ $(this).parent().attr( "id" ) ];
        mostrarAjudaDrag( drag, event );

    }, function( event ) { // mouseout

        esconderAjudaDrag( event );

    });

    $( "#" + idDrag ).css( "width", drag.largura );
    $( "#" + idDrag ).css( "height", drag.altura ); 

    $( "#" + idDrag ).draggable({
        start: function( evt, ui ) {

            var esse = $(this);
            esse.css( "z-index", 11 );
            arrastando = true;
            drags[esse.attr("id")].inserido = false;

        },
        stop: function( evt, ui ) {

            var esse = $(this);
            esse.css( "z-index", 10 );
            arrastando = false;
            animarRemocao( esse );

        }
    });

}

/**
 * Anima a remoção de um drag, levando-o para sua posição inicial.
 */
function animarRemocao( draggable ) {

    var drag = drags[draggable.attr("id")];

    if ( !drag.inserido ) {

        // esconde o botão de remover
        draggable.children( ".removeDrag" ).fadeOut();

        draggable.animate({
            left: drag.xInicial,
            top: drag.yInicial,
            width: drag.larguraInicial,
            height: drag.alturaInicial
        });

        draggable.children( ".dragLabel" ).css( "line-height", drag.alturaInicial + "px" );

    }

}

/**
 * Remove um drag dos itens do drop e anima a remoção.
 */
function removerDrag( event ) {

    if ( confirm( "Deseja mesmo remover este item?" ) ) {

        var idDrag = $(event.target).parent().attr( "id" );

        for ( var i in drops ) {

            var itens = drops[i].drags;
            var dropCp = $("#" + i);

            for ( var j in itens ) {
                if ( idDrag == itens[j] ) {

                    // indica que o item foi removido
                    drags[idDrag].inserido = false;

                    // anima a remoção do item
                    animarRemocao( $( "#" + idDrag ) );

                    // remove o item da lista de drags do drop
                    itens.splice( j, 1 );

                    // reorganiza os draggables dentro do horário
                    organizarDrags( dropCp.parent().attr("id"), dropCp );

                }

            }

        }

    }

}

/**
 * Mostra a ajuda de um drag.
 */
function mostrarAjudaDrag( drag, event ) {

    if ( !arrastando ) {
        $( "#divTooltip" ).html( drag.tooltip );
        $( "#divTooltip" ).css( "background-color", drag.corFundo );
        $( "#divTooltip" ).css( "border-color", drag.corBorda );
        $( "#divTooltip" ).css( "color", drag.corLabel );
        $( "#divTooltip" ).css( "left", event.clientX + 20 );
        $( "#divTooltip" ).css( "top", event.clientY + 2 );
        $( "#divTooltip" ).fadeIn();
    }

}

/**
 * Esconde a ajuda de um drag.
 */
function esconderAjudaDrag( event ) {
    $( "#divTooltip" ).fadeOut();
}