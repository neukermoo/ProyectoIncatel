$(document).ready(function () {
    $("#numIni").on("focus", function (e) {
        $("#btnIniciar").prop("disabled", true);
    });

    $("#numIni").on("change", function (e) {
        $("#content").html("");
        if ($("#numIni").val() == "" || $("#numIni").val() == 0 || isNaN($("#numIni").val()) == true) {
            $("#numIni").val("");
            mostrarMensaje("Solo numeros entre 1 y 99.");
        } else {
            $("#btnIniciar").prop("disabled", false);
            $("#rangeNumIni").val($("#numIni").val());
        }
    });

    $("#rangeNumIni").on("input", function (e) {
        $("#numIni").val($("#rangeNumIni").val());
        $("#btnIniciar").prop("disabled", false);
    });

    $("#btnIniciar").on("click", function () {
        mostrarSerie($("#numIni").val());

    });

    $("#gifAleatorio").on("click", function () {
        $("#btnIniciar").prop("disabled", false);
        $("#numIni").val(numAle());
        $("#btnIniciar").trigger("click");
        $("#rangeNumIni").val($("#numIni").val());
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
            url: "http://localhost:8080/Proyecto1_Incatel/webresources/juego"
        })
                .done(function (data, textStatus, hqXHR) {
                    $("#contentSerie").html(data.serie); //muestra los datos devueltos con el objectoJson
                    recorrer_li(); //recorre la lista presentada para resaltar los buzz, los fizz y los fizzbuzz.
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
        $("#msg").css("background-color", "rgba(255,0,0, 0.8)");
        setTimeout(function () {
            $("#msg").html("");
            $("#msg").css("background-color", "rgb(255, 255, 255)");
        }, 3000);
    }

    //recorre la serie fizzbuzz y pone en negrita todos aquellos <li> que no sean numeros
    function recorrer_li() {
        $("#serieBuzzFizz li").each(function () {
            if ($(this).text() == " buzz" || $(this).text() == " fizz" || $(this).text() == " fizzbuzz") {
                $(this).css("font-weight", "bold");
            }
        });
    }
});



