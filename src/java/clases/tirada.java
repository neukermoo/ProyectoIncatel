/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author Jordi Gutierrez
 * Representa una tirada en concreto
 * 
 */
public class tirada {

    /*
    *Variables propias de la clase
    */
    private String resultado;
    
    //Constructor vacio
    public tirada() {
    }

    //Getter necessario
    public String getResultado() {
        return resultado;
    }
    
    //funcion que dado un numero comprueba si es divisible por 3 y por 5.
    public void calcular(int num) {
        boolean fb = false;
        resultado = "";
        if(num % 3 == 0 ){
            resultado +="fizz";
            fb=true;
        }
        if(num % 5 == 0 ){
            resultado +="buzz";
            fb=true; 
        }
        if(fb == false ){
            resultado += num;
        }
    }    

}
