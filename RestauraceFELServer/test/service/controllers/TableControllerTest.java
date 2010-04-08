
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service.controllers;

import hibernate.Table;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jambojak
 */
public class TableControllerTest {

    public TableControllerTest() {
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
     * Test of getInstance method, of class TableController.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        
        TableController result = TableController.getInstance();
        if (result == null) fail();
        if (!(result instanceof TableController)) fail();
    }

    /**
     * Test of createTable method, of class TableController.
     */
    @Test
    public void testCreateTable() {
        System.out.println("createTable");
        int tableNumber = 1;
        int numberOfPlaces = 1;
        TableController instance = TableController.getInstance();
        boolean expResult = true;
        boolean result = instance.createTable(tableNumber, numberOfPlaces);
        assertEquals(expResult, result);
        }

    /**
     * Test of getTableById method, of class TableController.
     */
    @Test
    public void testGetTableById() {
        System.out.println("getTableById");
        int tableId = 1;
        TableController instance = TableController.getInstance();
        Table expResult = new Table();
        expResult.setTableId(1);
        expResult.setTableNumber(1);
        expResult.setNumberOfPlaces(1);
        Table result = instance.getTableById(tableId);
        if (result == null) fail();
        if ((int) expResult.getTableId() != (int) result.getTableId()) fail();
    }

    /**
     * Test of getTableByTableNumber method, of class TableController.
     */
    @Test
    public void testGetTableByTableNumber() {
        System.out.println("getTableByTableNumber");
        int tableNumber = 1;
        TableController instance = TableController.getInstance();
        Table expResult = new Table();
        expResult.setTableId(1);
        expResult.setTableNumber(1);
        expResult.setNumberOfPlaces(1);
        Table result = instance.getTableByTableNumber(tableNumber);
        if (result == null) fail();
        if ((int) expResult.getTableId() != (int) result.getTableId()) fail();
    }

    /**
     * Test of getTableNumbers method, of class TableController.
     */
    @Test
    public void testGetTableNumbers() {
        System.out.println("getTableNumbers");
        TableController instance = TableController.getInstance();
        int[] expResult = {1};
        int[] result = instance.getTableNumbers();
        if (expResult.length != result.length) fail();
        if (expResult[0] != result[0]) fail();
    }

    /**
     * Test of getTableNames method, of class TableController.
     */
    @Test
    public void testGetTableNames() {
        System.out.println("getTableNames");
        TableController instance = TableController.getInstance();
        String[] expResult = {"1"};
        String[] result = instance.getTableNames();
        if (expResult.length != result.length) fail();
        if (!expResult[0].equals(result[0])) fail();
        
    }

    /**
     * Test of getTables method, of class TableController.
     */
    @Test
    public void testGetTables() {
        System.out.println("getTables");
        TableController instance = TableController.getInstance();
        
        Object[][] expResult = new Object[1][3];
        expResult [0][0] = 1;
        expResult [0][1] = "1";
        expResult [0][2] = "1";

        Object[][] result = instance.getTables();
        if (result == null) fail();
        if (result.length != expResult.length) fail();

        if (Integer.parseInt(expResult[0][0].toString()) != Integer.parseInt(result[0][0].toString())) fail();
        if (Integer.parseInt(expResult[0][1].toString()) != Integer.parseInt(result[0][1].toString())) fail();
        if (Integer.parseInt(expResult[0][2].toString()) != Integer.parseInt(result[0][2].toString())) fail();
    }


    /**
     * Test of updateTable method, of class TableController.
     */
    @Test
    public void testUpdateTable() {
        System.out.println("updateTable");
        int tableId = 1;
        int tableNumber = 1;
        int numberOfPlaces = 8;
        TableController instance = TableController.getInstance();
        boolean expResult = true;
        boolean result = instance.updateTable(tableId, tableNumber, numberOfPlaces);
        assertEquals(expResult, result);
        
    }
    /**
     * Test of deleteTable method, of class TableController.
     */
    @Test
    public void testDeleteTable() {
        System.out.println("deleteTable");
        int tableId =1;
        TableController instance = TableController.getInstance();
        boolean expResult = true;
        boolean result = instance.deleteTable(tableId);
        assertEquals(expResult, result);

    }

}
///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package service.controllers;
//
//import hibernate.Table;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author Tomáš
// */
//public class TableControllerTest {
//
//    public TableControllerTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() throws Exception {
//    }
//
//    @AfterClass
//    public static void tearDownClass() throws Exception {
//    }
//
//    @Before
//    public void setUp() {
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of getInstance method, of class TableController.
//     */
//    @Test
//    public void testGetInstance() {
//        System.out.println("getInstance");
//        TableController expResult = TableController.getInstance();
//        TableController result = TableController.getInstance();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of createTable method, of class TableController.
//     */
//    @Test
//    public void testCreateTable() {
//        System.out.println("createTable");
//        int tableNumber = 0;
//        int numberOfPlaces = 0;
//        TableController instance = TableController.getInstance();
//        boolean expResult = false;
//        boolean result = instance.createTable(tableNumber, numberOfPlaces);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of deleteTable method, of class TableController.
//     */
//    @Test
//    public void testDeleteTable() {
//        System.out.println("deleteTable");
//        int tableId = 0;
//        TableController instance = TableController.getInstance();
//        boolean expResult = false;
//        boolean result = instance.deleteTable(tableId);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of updateTable method, of class TableController.
//     */
//    @Test
//    public void testUpdateTable() {
//        System.out.println("updateTable");
//        int tableId = 0;
//        int tableNumber = 0;
//        int numberOfPlaces = 0;
//        TableController instance = TableController.getInstance();
//        boolean expResult = false;
//        boolean result = instance.updateTable(tableId, tableNumber, numberOfPlaces);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getTableById method, of class TableController.
//     */
//    @Test
//    public void testGetTableById() {
//        System.out.println("getTableById");
//        int tableId = 0;
//        TableController instance = TableController.getInstance();
//        Table expResult = null;
//        Table result = instance.getTableById(tableId);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getTableByTableNumber method, of class TableController.
//     */
//    @Test
//    public void testGetTableByTableNumber() {
//        System.out.println("getTableByTableNumber");
//        int tableNumber = 0;
//        TableController instance = TableController.getInstance();
//        Table expResult = null;
//        Table result = instance.getTableByTableNumber(tableNumber);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getTableNumbers method, of class TableController.
//     */
//    @Test
//    public void testGetTableNumbers() {
//        System.out.println("getTableNumbers");
//        TableController instance = TableController.getInstance();
//        int[] expResult = instance.getTableNumbers();
//        int[] result = instance.getTableNumbers();
//        assertEquals(expResult.length, result.length);
//        for (int i=0; i<result.length; i++) assertEquals(expResult[i], result[i]);
//    }
//
//    /**
//     * Test of getTableNames method, of class TableController.
//     */
//    @Test
//    public void testGetTableNames() {
//        System.out.println("getTableNames");
//        TableController instance = TableController.getInstance();
//        String[] expResult = {"1", "2", "3"};
//        String[] result = instance.getTableNames();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getTables method, of class TableController.
//     */
//    @Test
//    public void testGetTables() {
//        System.out.println("getTables");
//        TableController instance = TableController.getInstance();
//        Object[][] expResult = instance.getTables();
//        Object[][] result = instance.getTables();
//        assertEquals(expResult, result);
//    }
//
//}>>>>>>> .r68
