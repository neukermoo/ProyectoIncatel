/*
 *ERRORES
 *Rutas relativas no funcionan, he tenido que indicar ruta absoluta para el archivo series.txt(guarda las series realizadas) 
 *   como para el archivo config.properties que contiene el limite del programa
 *log4j implementado pero no funcional(no me crea el archivo error.log tal como indico en su archivo log4j.properties
 */
package recursos;

import clases.PathException;
import clases.buzzFizz;
import java.net.URL;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * REST Web Service Se encarga de procesar la peticion get que llega desde la
 * vista.
 *
 * @author Jordi Gutierrez
 */
@Path("manual")
public class GenericResource {

    /*
    *Variables necesarias para el logger de log4j
    */
    private final static Logger log = Logger.getLogger(GenericResource.class);
    URL url = GenericResource.class.getResource("config/log4j.properties");

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {

    }

    /**
     *
     * metodo get del servicio rest que se encarga de obtener, de la vista, el
     * inicio de la serie, crear la serie buzzFizz y mostrar la serie por
     * pantalla. En un segundo hilo escribe la serie con la fecha en el archivo
     * series.txt
     *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson(@QueryParam("numIntr") String numInt) throws PathException {

        buzzFizz bf = new buzzFizz();

        PropertyConfigurator.configure(url); //configuro el log
        log.info("Inicio de servicio restful");

        /*
        *Metodos alojados en la clase buzzFizz que realizan los pasos necessarios para ejecutar el programa
         */
        bf.setNumeroInicial(Integer.parseInt(numInt));//se guarda en numeroInicial de buzzFizz el valor introducido por el usuario
        bf.obtenerLimite(); //se obtiene el limite indicado en el archivo de configuración
        bf.realizarJuego(); //se realiza la serie buzzFizz.
        log.info("Inicio escritura con multithreading");
        bf.start(); //inicio el segundo hilo que escribe el archivo
        bf.crearSerieVista();//crea un String con la serie buzzFizz para presentarla como una lista

        /*
        *Creamos el objeto Json que se devuelve a la vista con la serie en forma de string
         */
        JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder(); //constructor de expressiones json
        jsonObjBuilder.add("serie", bf.getResultadoVista()); //añadimos como respuesta el String de la serie guardad en buzzFizz,
        JsonObject jsonObj = jsonObjBuilder.build(); // construye el objeto
        return Response.ok(jsonObj.toString()).build(); //se devuelve tipo response
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {

    }

}
