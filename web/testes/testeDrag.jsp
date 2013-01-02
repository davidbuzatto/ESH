<%-- 
    Document   : testeDrag
    Created on : Jun 28, 2012, 5:20:04 PM
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Horário</title>
        
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-ui/css/ui-lightness/jquery-ui-1.8.21.custom.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilosAplicacao.css"/>
        
        <style type="text/css">
            body {
                font-family: Verdana, Geneva, sans-serif;
                font-size: 12px;
                font-weight: bold;
                margin: 0px;
            }
        </style>
        
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui/jquery-ui-1.8.21.custom.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/componentesHorario.js"></script>
        
        <script type="text/javascript">
    
            $(document).ready( function() {
        
                var i;
        
                for ( i = 0; i < 10; i++ ) {
                    criarDrag({
                        id: "disc" + i,
                        sufixo: "drag_disc", // usado para identificar o tipo de drag e nos css (to_sufixo)
                        label: "Disc " + i,
                        tooltip: "Disciplina: DISC " + i + "<br/>Professor(a): David",
                        corFundo: "#00D24C",
                        corBorda: "#00832F",
                        corLabel: "#FFF",
                        x: 10,
                        y: 10,
                        largura: 60,
                        altura: 30
                    });
                }
        
                for ( ; i < 20; i++ ) {
                    criarDrag({
                        id: "disc" + i,
                        sufixo: "drag_disc", // usado para identificar o tipo de drag e nos css (to_sufixo)
                        label: "Disc " + i,
                        tooltip: "Disciplina: DISC " + i + "<br/>Professor(a): Fernando",
                        corFundo: "#258DBB",
                        corBorda: "#00529A",
                        corLabel: "#FFF",
                        x: 10,
                        y: 50,
                        largura: 60,
                        altura: 30
                    });
                }
        
                for ( ; i < 30; i++ ) {
                    criarDrag({
                        id: "disc" + i,
                        sufixo: "drag_disc", // usado para identificar o tipo de drag e nos css (to_sufixo)
                        label: "Disc " + i,
                        tooltip: "Disciplina: DISC " + i + "<br/>Professor(a): Fernanda",
                        corFundo: "#B466FF",
                        corBorda: "#430083",
                        corLabel: "#FFF",
                        x: 10,
                        y: 90,
                        largura: 60,
                        altura: 30
                    });
                }
        
                for ( ; i < 40; i++ ) {
                    criarDrag({
                        id: "disc" + i,
                        sufixo: "drag_disc", // usado para identificar o tipo de drag e nos css (to_sufixo)
                        label: "Disc " + i,
                        tooltip: "Disciplina: DISC " + i + "<br/>Professor(a): Luiz",
                        corFundo: "#FF667F",
                        corBorda: "#D00022",
                        corLabel: "#FFF",
                        x: 10,
                        y: 130,
                        largura: 60,
                        altura: 30
                    });
                }
        
                for ( i = 0; i < 10; i++ ) {
                    criarDrag({
                        id: "local" + i,
                        sufixo: "drag_local",
                        label: "Lab Inf " + i,
                        tooltip: "Laboratório de Informática " + i + "<br/>(Sala " + i + ")",
                        corFundo: "#FFF246",
                        corBorda: "#958B00",
                        corLabel: "#000",
                        x: 10,
                        y: 170,
                        largura: 60,
                        altura: 30
                    });
                }
        
                for ( ; i < 20; i++ ) {
                    criarDrag({
                        id: "local" + i,
                        sufixo: "drag_local",
                        label: "Sala T. " + i,
                        tooltip: "Sala de Aula Teórica " + i + "<br/>(Sala " + i + ")",
                        corFundo: "#FACE00",
                        corBorda: "#FF7000",
                        corLabel: "#AF550E",
                        x: 10,
                        y: 210,
                        largura: 60,
                        altura: 30
                    });
                }
        
                montar({
                    colunas: [{
                            sufixo: "hr",
                            titulo: "Horário",
                            labels: [ "7:00", "7:50", "8:40", "9:45", "10:35", "11:25" ],
                            largura: 70,
                            droppable: false
                        }, {
                            sufixo: "drop_disc",
                            titulo: "Disciplina",
                            largura: 100,
                            droppable: true,
                            corOver: "#D7EEFF",
                            aceitarSomente: "drag_disc", // usado para indicar quem é aceito neste drop (sufixo do drag)
                            aceitarAte: 4
                        }, {
                            sufixo: "drop_local",
                            titulo: "Local",
                            largura: 100,
                            droppable: true,
                            corOver: "#FFE7BB",
                            aceitarSomente: "drag_local",
                            aceitarAte: 2
                        }],
                    altura: 30,
                    quantidadeLinhas: 6,
                    corPar: "#F2F2F2",
                    corImpar: "#E2E2E2",
                    container: "c1"
                });
        
                montar({
                    colunas: [{
                            sufixo: "hr",
                            titulo: "Horário",
                            labels: [ "13:00", "13:50", "14:40", "15:45", "16:35", "17:25" ],
                            largura: 70,
                            droppable: false
                        }, {
                            sufixo: "drop_disc",
                            titulo: "Disciplina",
                            largura: 100,
                            droppable: true,
                            corOver: "#D7EEFF",
                            aceitarSomente: "drag_disc",
                            aceitarAte: 4
                        }, {
                            sufixo: "drop_local",
                            titulo: "Local",
                            largura: 100,
                            droppable: true,
                            corOver: "#FFE7BB",
                            aceitarSomente: "drag_local",
                            aceitarAte: 2
                        }],
                    altura: 30,
                    quantidadeLinhas: 6,
                    corPar: "#F2F2F2",
                    corImpar: "#E2E2E2",
                    container: "c2"
                });
        
                montar({
                    colunas: [{
                            sufixo: "hr",
                            titulo: "Horário",
                            labels: [ "18:20", "19:10", "20:00", "21:05", "21:55" ],
                            largura: 70,
                            droppable: false
                        }, {
                            sufixo: "drop_disc",
                            titulo: "Disciplina",
                            largura: 100,
                            droppable: true,
                            corOver: "#D7EEFF",
                            aceitarSomente: "drag_disc",
                            aceitarAte: 4
                        }, {
                            sufixo: "drop_local",
                            titulo: "Local",
                            largura: 100,
                            droppable: true,
                            corOver: "#FFE7BB",
                            aceitarSomente: "drag_local",
                            aceitarAte: 2
                        }],
                    altura: 30,
                    quantidadeLinhas: 5,
                    corPar: "#F2F2F2",
                    corImpar: "#E2E2E2",
                    container: "c3"
                });
        
            });
    
        </script>

    </head>

    <body>

        <div id="divTooltip" class="divTooltip"></div>
        <div id="c1" style="background: #000000; width:280px; height: 220px; position: absolute; left: 100px; top: 0px;"></div>
        <div id="c2" style="background: #000000; width:280px; height: 220px; position: absolute; left: 100px; top: 220px;"></div>
        <div id="c3" style="background: #000000; width:280px; height: 190px; position: absolute; left: 100px; top: 440px;"></div>

    </body>
</html>
