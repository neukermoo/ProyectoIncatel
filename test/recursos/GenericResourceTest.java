/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import javax.ws.rs.core.Response;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jordi Gutierrez Test unitario del programa
 *
 */
public class GenericResourceTest {

    public GenericResourceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getJson method, of class GenericResource.
     */
    @Test
    public void testGetJson() throws Exception {
        System.out.println("Realizando test get;");

        String paramVista = "" + (int) ((Math.floor(Math.random() * 100)) + 1);
        GenericResource instance = new GenericResource();
        Response expResult = null;
        Response result = instance.getJson(paramVista);
        System.out.println("Test get realizado correctamente:");
        System.out.println(result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of putJson method, of class GenericResource.
     */
    @Test
    public void testPutJson() {
        System.out.println("Realizando test put;");
        String content = "";
        GenericResource instance = new GenericResource();
        instance.putJson(content);
        System.out.println("Test put realizado correctamente. \n");
        fail("The test case is a prototype.");
    }

}
