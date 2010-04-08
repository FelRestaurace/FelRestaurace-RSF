/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service.controllers;

import hibernate.Kontrola;
import hibernate.User;
import hibernate.Uzaverka;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lukas Camra
 */
public class UzaverkaControllerTest {

    public UzaverkaControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setUzaverka method, of class UzaverkaController.
     */
    @Test
    public void testSetUzaverka() {
        System.out.println("setUzaverka");
        Uzaverka uzaverka = new Uzaverka();
        UzaverkaController instance = new UzaverkaController();
        instance.setUzaverka(uzaverka);
        assertEquals(uzaverka, instance.uzaverka);
    }

    /**
     * Test of getInstance method, of class UzaverkaController.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        UzaverkaController result = UzaverkaController.getInstance();
        if (!(result instanceof UzaverkaController)) {
            fail();
        }
    }

    /**
     * Test of createUzaverka method, of class UzaverkaController.
     */
    @Test
    public void testCreateUzaverka() {
        System.out.println("createUzaverka");
        User user = new User();
        Date date = new Date();
        UzaverkaController instance = new UzaverkaController();
        Uzaverka result = instance.createUzaverka(user, date);
        assertEquals(result.getDate(), date);
        assertEquals(result.getUser(), user);

        
    }

    /**
     * Test of getKontroly method, of class UzaverkaController.
     */
    @Test
    public void testGetKontroly() {
        System.out.println("getKontroly");
        UzaverkaController instance = new UzaverkaController();
        Object[][] result = instance.getKontroly();
        assertEquals(result[0].length, 0);
       
    }



    /**
     * Test of getAllUzaverky method, of class UzaverkaController.
     */
    @Test
    public void testGetAllUzaverky() {
        System.out.println("getAllUzaverky");
        UzaverkaController instance = UzaverkaController.getInstance();
        Object[][] result = instance.getAllUzaverky();
        if(result==null)
            fail("result is null");
        // TODO review the generated test code and remove the default call to fail.
    }




}