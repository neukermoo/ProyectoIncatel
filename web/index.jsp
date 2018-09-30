<%-- 
    Document   : inicio
    Created on : 29-sep-2018, 16:58:43
    Author     : Jordi Gutierrez
--%>

<%@page import="pruebas.velocidad"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
        crossorigin="anonymous"></script>
    </head>
    <style>
        #numIni{
            text-align:center;
        }
        #content{
            margin-top:10px;
            column-count:7;
        }
    </style>
    <body>
        <br>        <br>        <br>

        <input type="text" size="5" id="numIni" name="NumeroIntroducido" maxlength="2">        
        <input type="button" id="iniciar" value="Iniciar serie" >

        <input type="button" id="ale" value="Aleatorio">

        <input type="button" id="test" value="Peticiones por segundo">
        <br>
        <p id="msg"></p>

        <div id="content"></div>

    </body>
    <script>
        $("#numIni").on("focus", function (e) {
            $("#iniciar").prop("disabled", true);
        });
        $("#numIni").on("change", function (e) {
            $("#content").html("");
            if ($("#numIni").val() == "" || $("#numIni").val() == 0 || isNaN($("#numIni").val()) == true) {
                $("#numIni").val("");
                mostrarMensaje("Solo se pueden introducir numeros entre 1 y 99.");
            } else {
                $("#iniciar").prop("disabled", false);
            }
        });
        $("#iniciar").on("click", function () {
            mostrarSerie($("#numIni").val());
        });
        $("#ale").on("click", function () {
            $("#numIni").val(numAle());
            $("#iniciar").trigger("click");
        });
        $("#test").on("click", function () {
        <%
            velocidad v = new velocidad();
            v.realizarPrueba300();
        %>
            mostrarMensaje("<%= v.getMsg()%>");
        });
        //Calcula un numero aleatorio entre 1 y 100
        function numAle() {
            return  Math.floor(((Math.random() * 100) + 1));
        }

        //Envia el control con Ajax al servicio rest
        function mostrarSerie() {
            $.ajax({
                data: {"numIntr": arguments[0]},
                type: "GET",
                dataType: "json",
                url: "http://localhost:8080/Prueba_webrest/webresources/manual"
            })
                    .done(function (data, textStatus, hqXHR) {
                        $("#content").css("column-count", "7");
                        $("#content").html(data.serie); //muestra los datos devueltos con el objectoJson
                        if (console && console.log) {
                            console.log("La solicitud se ha completado correctamente.");
                        }
                    })
                    .fail(function (jqXHR, textStatus, errprThrown) {
                        if (console && console.log) {
                            console.log("La solicitud ha fallado: " + textStatus);
                        }
                    });
        }

        //Muestra informacion al usuario durante tres segundos
        function mostrarMensaje(msg) {
            $("#msg").html(msg);
            setTimeout(function () {
                $("#msg").html("");
            }, '3000');
        }

    </script>
</html>
