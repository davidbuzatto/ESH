<%-- 
    Document   : principal
    Created on : Jul 3, 2012, 1:31:58 PM
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Easy School Hours</title>
        
        <link rel="icon" href="${pageContext.request.contextPath}/imagens/icones/favicon.png" type="image/png"/>
        <link rel="shortcut icon" href="favicon.ico"/>
        
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/estilosAplicacao.css"/>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/js/extjs/4.1.0/resources/css/ext-all.css"/>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/extOverride.css"/>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery-ui/css/ui-lightness/jquery-ui-1.8.21.custom.css"/>
        
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/extjs/4.1.0/ext-all-dev.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/extjs/4.1.0/ext-lang-pt_BR.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui/jquery-ui-1.8.21.custom.min.js"></script>
        
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/mensagens.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/uteis.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/modelos.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/componentes.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/instituicao.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/usuario.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/cidade.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/estado.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/pais.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/campus.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/principal.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/aplicacao.js"></script>
        
        <script type="text/javascript">
            
            Ext.onReady( function(){
                
                Aplicacao.iniciar({
                    contexto: "${pageContext.request.contextPath}",
                    icones: "${pageContext.request.contextPath}" + "/imagens/icones/",
                    intervaloRequisicao: 2,
                    quantidadeResultadosPorPagina: 12
                });
                
            });
            
        </script>
        
    </head>
    <body>
    </body>
</html>
