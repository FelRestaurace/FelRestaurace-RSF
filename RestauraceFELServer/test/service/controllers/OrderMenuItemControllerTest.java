/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service.controllers;

import hibernate.OrderMenuItem;
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
public class OrderMenuItemControllerTest {

    public OrderMenuItemControllerTest() {
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
     * Test of getInstance method, of class OrderMenuItemController.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        OrderMenuItemController expResult = OrderMenuItemController.getInstance();
        OrderMenuItemController result = OrderMenuItemController.getInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test of createOrderMenuItem method, of class OrderMenuItemController.
     */
    @Test
    public void testCreateOrderMenuItem() {
        System.out.println("createOrderMenuItem");
        int menuItemId = 0;
        int orderId = 0;
        OrderMenuItemController instance = OrderMenuItemController.getInstance();
        boolean expResult = false;
        boolean result = instance.createOrderMenuItem(menuItemId, orderId);
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrderMenuItemById method, of class OrderMenuItemController.
     */
    @Test
    public void testGetOrderMenuItemById() {
        System.out.println("getOrderMenuItemById");
        int id = 0;
        OrderMenuItemController instance = OrderMenuItemController.getInstance();
        OrderMenuItem expResult = null;
        OrderMenuItem result = instance.getOrderMenuItemById(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllOrderMenuItems method, of class OrderMenuItemController.
     */
    @Test
    public void testGetAllOrderMenuItems() {
        System.out.println("getAllOrderMenuItems");
        OrderMenuItemController instance = OrderMenuItemController.getInstance();
        List expResult = instance.getAllOrderMenuItems();
        List result = instance.getAllOrderMenuItems();
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteOrderMenuItem method, of class OrderMenuItemController.
     */
    @Test
    public void testDeleteOrderMenuItem() {
        System.out.println("deleteOrderMenuItem");
        int orderMenuItemId = 0;
        OrderMenuItemController instance = OrderMenuItemController.getInstance();
        boolean expResult = false;
        boolean result = instance.deleteOrderMenuItem(orderMenuItemId);
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrderMenuItemNames method, of class OrderMenuItemController.
     */
    @Test
    public void testGetOrderMenuItemNames() {
        System.out.println("getOrderMenuItemNames");
        OrderMenuItemController instance = OrderMenuItemController.getInstance();
        String[] expResult = new String[21];
        for (int i=1; i<=expResult.length; i++) expResult[i-1] = "Objednavka cislo "+i;
        String[] result = instance.getOrderMenuItemNames();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrderMenuItems method, of class OrderMenuItemController.
     */
    @Test
    public void testGetOrderMenuItems() {
        System.out.println("getOrderMenuItems");
        OrderMenuItemController instance = OrderMenuItemController.getInstance();
        Object[][] expResult = instance.getOrderMenuItems();
        Object[][] result = instance.getOrderMenuItems();
        assertEquals(expResult, result);
    }

}