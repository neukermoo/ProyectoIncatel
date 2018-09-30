/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author Jordi Gutierrez
 * Custom Exception
 * controla el fallo de
 */
public class PathException extends Exception {

    public PathException(String msg) {
        super(msg);
    }
}
