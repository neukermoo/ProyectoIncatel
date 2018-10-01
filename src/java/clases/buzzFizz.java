/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Jordi Gutierrez Classe buzzFizz, representa al Juego
 */
public class buzzFizz extends Thread {

    /*
    *Variables necesarias para el logger de log4j
     */
    private final static Logger log = Logger.getLogger(buzzFizz.class);
    URL url = buzzFizz.class.getResource("config/log4j.properties");

    /*
    *Variables propias de la clase
     */
    private int numeroInicial;
    private int numeroFinal;

    private ArrayList<String> resultado;

    private String resultadoVista;

    //Constructor vacio
    public buzzFizz() {
        PropertyConfigurator.configure(url);
    }

    //constructor parametrizado con todos las variables de la clase
    public buzzFizz(int numeroInicial, int numeroFinal, ArrayList<String> resultado, String resultadoVista) {
        this.numeroInicial = numeroInicial;
        this.numeroFinal = numeroFinal;
        this.resultado = resultado;
        this.resultadoVista = resultadoVista;
    }

    /*
    Getters y setters necessarios en la clase
     */
    public String getResultadoVista() {
        return resultadoVista;
    }

    public void setNumeroInicial(int numeroInicial) {
        this.numeroInicial = numeroInicial;
    }

    //Funcion que accede al archivo de configuracion "config.properties" y obtiene el limite de la serie.
    public void obtenerLimite() throws PathException {
        try {

            Properties p = new Properties();
            p.load(new FileReader("C:\\Users\\Jordi Gutierrez\\Desktop\\SERVLETJSP\\Proyecto1_Incatel\\src\\java\\config\\config.properties"));
            // p.load(new FileReader("../config/config.properties"));

            numeroFinal = Integer.parseInt(p.getProperty("FinSerie"));
        } catch (FileNotFoundException ex) {
            throw new PathException("Problema al encontrar el archivo con el limite. La ruta es absoluta, canviar a relativa o a la propia del ordenador!");
        } catch (IOException ex) {
            log.error("Error de IOException : " + ex);
        }
    }

    //Funcion que crea la serie utilizando, para cada incremento, un metodo de tirada y guardandolo en la ArrayList
    public void realizarJuego() {
        try {
            tirada tir = new tirada();
            resultado = new ArrayList<String>();
            for (int i = numeroInicial; i < numeroFinal; i++) {
                tir.calcular(i);
                resultado.add(tir.getResultado());
            }
        } catch (Exception e) {
            log.error("Ha habido un error, probablemente la lista.");
        }
    }

    //for que recorre la lista con la serie y crea un String que se imprimira en la vista.
    public void crearSerieVista() {
        resultadoVista = "<ul id='serieBuzzFizz'>";

        try {
            for (Object list : resultado) {
                resultadoVista += "<li> " + list + "</li>";
            }
        } catch (Exception e) {
            log.error("Ha habido un error, probablemente la lista.");
        }

        resultadoVista += "</ul>";
    }

    //Multithread para escribir el archivo en un hilo distinto al principal
    public void run() {
        try {
            escribirArchivo();
            log.info("Escribiendo el archivo.");
        } catch (PathException ex) {
            log.warn("Problema al escribir el archivo de series.");
        }
    }

    //Escribe la serie en el archivo. Tiene una excepcion propia.
    public void escribirArchivo() throws PathException {
        try {
            Date d = new Date();
            String serieConFecha = d + resultado.toString();

            FileWriter fsortida = new FileWriter("C:\\Users\\Jordi Gutierrez\\Desktop\\SERVLETJSP\\Proyecto1_Incatel\\src\\java\\archivos\\series.txt", true);
            //FileWriter fsortida = new FileWriter("../archivos/series.txt", true);

            BufferedWriter bsortida = new BufferedWriter(fsortida);
            bsortida.write(serieConFecha);
            bsortida.newLine();
            bsortida.close();
            fsortida.close();
        } catch (IOException e) {
            throw new PathException("Problema al escribir en el archivo. La ruta es absoluta, canviar a relativa o a la propia del ordenador!");
        }
    }
}
