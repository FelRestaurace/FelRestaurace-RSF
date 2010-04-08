/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service.controllers;

import hibernate.Order;
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
 * @author Tomáš
 */
public class OrderControllerTest {

    public OrderControllerTest() {
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
     * Test of getInstance method, of class OrderController.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        OrderController expResult = OrderController.getInstance();
        OrderController result = OrderController.getInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test of createOrder method, of class OrderController.
     */
    @Test
    public void testCreateOrder() {
        System.out.println("createOrder");
        int isPaid = 0;
        Date time = null;
        int accountId = 0;
        int userId = 0;
        OrderController instance = OrderController.getInstance();
        boolean expResult = false;
        boolean result = instance.createOrder(isPaid, time, accountId, userId);
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrderById method, of class OrderController.
     */
    @Test
    public void testGetOrderById() {
        System.out.println("getOrderById");
        int id = 0;
        OrderController instance = OrderController.getInstance();
        Order expResult = null;
        Order result = instance.getOrderById(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllOrders method, of class OrderController.
     */
    @Test
    public void testGetAllOrders() {
        System.out.println("getAllOrders");
        OrderController instance = OrderController.getInstance();
        List expResult = instance.getAllOrders();
        List result = instance.getAllOrders();
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteOrder method, of class OrderController.
     */
    @Test
    public void testDeleteOrder() {
        System.out.println("deleteOrder");
        int orderId = 0;
        OrderController instance = OrderController.getInstance();
        boolean expResult = false;
        boolean result = instance.deleteOrder(orderId);
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrderNames method, of class OrderController.
     */
    @Test
    public void testGetOrderNames() {
        System.out.println("getOrderNames");
        OrderController instance = OrderController.getInstance();
        String[] expResult = new String[21];
        for (int i=1; i<=expResult.length; i++) expResult[i-1] = "Objednavka cislo "+i;
        String[] result = instance.getOrderNames();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrdersByAccount method, of class OrderController.
     */
    @Test
    public void testGetOrdersByAccount() {
        System.out.println("getOrdersByAccount");
        int accountId = 0;
        OrderController instance = OrderController.getInstance();
        Object[][] expResult = null;
        Object[][] result = instance.getOrdersByAccount(accountId);
        assertEquals(expResult, result);
    }

    /**
     * Test of payNMenuItemsByAccount method, of class OrderController.
     */
    @Test
    public void testPayNMenuItemsByAccount() {
        System.out.println("payNMenuItemsByAccount");
        int n = 0;
        int menuItemId = 0;
        int accountId = 0;
        OrderController instance = OrderController.getInstance();
        boolean expResult = false;
        boolean result = instance.payNMenuItemsByAccount(n, menuItemId, accountId);
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrders method, of class OrderController.
     */
    @Test
    public void testGetOrders() {
        System.out.println("getOrders");
        OrderController instance = OrderController.getInstance();
        Object[][] expResult = instance.getOrders();
        Object[][] result = instance.getOrders();
        assertEquals(expResult, result);
    }

}