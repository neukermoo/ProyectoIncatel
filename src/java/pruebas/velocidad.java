/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import clases.PathException;
import clases.buzzFizz;
import javax.ws.rs.Path;

/**
 *
 * @author Jordi Gutierrez
 */
@Path("testeo")
public class velocidad {

    private String msg;
    private buzzFizz bf;

    public velocidad() {
    }

    public velocidad(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void realizarPrueba300() throws PathException {

        bf = new buzzFizz();
        long tiemIni = System.currentTimeMillis();
        int repeticiones = 0;
        float duracion_s = 0;

        for (repeticiones = 0; repeticiones < 1000; repeticiones++) {
            bf.setNumeroInicial(numAl());
            bf.obtenerLimite();
            bf.realizarJuego();
            bf.escribirArchivo();

            //Calculo cuanto tiempo de ejecuccion lleva, si lleva mas de un segundo se para el bucle
            duracion_s = calcularDiferencia(tiemIni);
            if (duracion_s >= 1) {
                break;
            }
        }

        msg = "Testeado. Ha realizado " + repeticiones + " peticiones en " + duracion_s + " segundos";
    }

    //calcula un numero aleatorio entre 1 y 100
    private int numAl() {
        return (int) (Math.floor(Math.random() * 100)) + 1;
    }

    //calcula la diferencia entre dos tiempos
    private float calcularDiferencia(long tiemIni) {
        return (float) (System.currentTimeMillis() - tiemIni) / 1000;
    }
}
