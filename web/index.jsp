<%-- 
    Document   : index
    Created on : Jun 28, 2012, 4:50:31 PM
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Easy School Hours - Login</title>
        
        <link rel="icon" href="${pageContext.request.contextPath}/imagens/icones/favicon.png" type="image/png"/>
        <link rel="shortcut icon" href="favicon.ico"/>
        
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/estilosIndex.css"/>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery-ui/css/redmond/jquery-ui-1.8.21.custom.css"/>
        
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui/jquery-ui-1.8.21.custom.min.js"></script>
        
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
        
        <script type="text/javascript">
            
            iniciar({
                contexto: "${pageContext.request.contextPath}"
            });
            
        </script>
        
    </head>
    <body>
        
        Email: <input id="fieldEmail" type="text" name="email" value="davidbuzatto@gmail.com"/>
        <br/>
        Senha: <input id="fieldSenha" type="password" name="senha" value="123456789"/>
        <br/>
        <input id="btnLogin" type="button" value="Enviar"/>
        
        <div id="dialogoErro" title="ERRO">
            <div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
                <p>
                    <span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
                    <p class="conteudo"></p>
                </p>
            </div>
        </div>
        
    </body>
</html>
