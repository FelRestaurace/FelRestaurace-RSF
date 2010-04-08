/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service.controllers;

import hibernate.DiscountType;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tomáš
 */
public class DiscountTypeControllerTest {

    public DiscountTypeControllerTest() {
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
     * Test of getInstance method, of class DiscountTypeController.
     */

    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        DiscountTypeController expResult = DiscountTypeController.getInstance();
        DiscountTypeController result = DiscountTypeController.getInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test of createDiscountType method, of class DiscountTypeController.
     */
    @Test
    public void testCreateDiscountType() {
        System.out.println("createDiscountType");
        String name = "";
        DiscountTypeController instance = DiscountTypeController.getInstance();
        boolean expResult = false;
        boolean result = instance.createDiscountType(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDiscountTypeById method, of class DiscountTypeController.
     */
    @Test
    public void testGetDiscountTypeById() {
        System.out.println("getDiscountTypeById");
        int id = 0;
        DiscountTypeController instance = DiscountTypeController.getInstance();
        DiscountType expResult = null;
        DiscountType result = instance.getDiscountTypeById(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDiscountTypeByName method, of class DiscountTypeController.
     */
    @Test
    public void testGetDiscountTypeByName() {
        System.out.println("getDiscountTypeByName");
        String name = "";
        DiscountTypeController instance = DiscountTypeController.getInstance();
        DiscountType expResult = null;
        DiscountType result = instance.getDiscountTypeByName(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllDiscountTypes method, of class DiscountTypeController.
     */
    @Test
    public void testGetAllDiscountTypes() {
        System.out.println("getAllDiscountTypes");
        DiscountTypeController instance = DiscountTypeController.getInstance();
        List expResult = instance.getAllDiscountTypes();
        List result = instance.getAllDiscountTypes();
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteDiscountType method, of class DiscountTypeController.
     */
    @Test
    public void testDeleteDiscountType() {
        System.out.println("deleteDiscountType");
        int discountTypeId = 0;
        DiscountTypeController instance = DiscountTypeController.getInstance();
        boolean expResult = false;
        boolean result = instance.deleteDiscountType(discountTypeId);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDiscountTypeNames method, of class DiscountTypeController.
     */
    @Test
    public void testGetDiscountTypeNames() {
        System.out.println("getDiscountTypeNames");
        DiscountTypeController instance = DiscountTypeController.getInstance();
        String[] expResult = {"50%", "zadarmo"};
        String[] result = instance.getDiscountTypeNames();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDiscountTypes method, of class DiscountTypeController.
     */
    @Test
    public void testGetDiscountTypes() {
        System.out.println("getDiscountTypes");
        DiscountTypeController instance = DiscountTypeController.getInstance();
        Object[][] expResult = instance.getDiscountTypes();
        Object[][] result = instance.getDiscountTypes();
        assertEquals(expResult, result);
    }

}