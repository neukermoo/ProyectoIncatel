<%-- 
    Document   : inicio
    Created on : 29-sep-2018, 16:58:43
    Author     : Jordi Gutierrez
--%>

<%@page import="pruebas.velocidad"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        
        <title>FUZZBIZZ</title>
        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
        crossorigin="anonymous"></script>
        <script src="index.js" type="text/javascript"></script>

        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">        <link href="index.css" rel="stylesheet" type="text/css"/>

    </head>

    <body>

        <div id="contentMsg">
            <p id="msg"></p>
        </div>
        <input class="btn btn-success" type="button" id="test" value="Peticiones por segundo">

        <div id="contentIni">
            <div id="contentInstruccio">
                <p> Introduce el nº inicial de la serie
            </div>
            
            <div id="contentInp_btnIni">
                <img src="img/dado.gif" alt="" id="gifAleatorio"/>
                <input type="text" size="5" id="numIni" name="NumeroIntroducido" maxlength="2" placeholder="50" value="0">        
                <input class="btn btn-default" type="button" id="btnIniciar" value="Iniciar serie" >
            </div>
            
            <div id="contentRange" class="form-group">
                <input class="form-control" type="range" min="1" max="99" value="50" step="1" id="rangeNumIni" name="rangeNumIni">
            </div>
        </div>
        <br>

        <div id="contentSerie">
            
        </div>

    </body>
    <script>
        $("#test").on("click", function () {
        <%
            velocidad v = new velocidad();
            v.realizarPrueba300();
        %>

            $("#msg").html("<%= v.getMsg()%>");
            $("#msg").css("background-color", "green");

        });
    </script>
</html>
