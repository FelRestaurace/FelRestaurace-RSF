/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service.controllers;

import hibernate.AccountStatusType;
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
public class AccountStatusTypeControllerTest {

    public AccountStatusTypeControllerTest() {
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
     * Test of getInstance method, of class AccountStatusTypeController.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        AccountStatusTypeController expResult = AccountStatusTypeController.getInstance();
        AccountStatusTypeController result = AccountStatusTypeController.getInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test of createAccountStatusType method, of class AccountStatusTypeController.
     */
    @Test
    public void testCreateAccountStatusType() {
        System.out.println("createAccountStatusType");
        String name = "";
        String note = "";
        AccountStatusTypeController instance = AccountStatusTypeController.getInstance();
        boolean expResult = false;
        boolean result = instance.createAccountStatusType(name, note);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAccountStatusTypeById method, of class AccountStatusTypeController.
     */
    @Test
    public void testGetAccountStatusTypeById() {
        System.out.println("getAccountStatusTypeById");
        int id = 0;
        AccountStatusTypeController instance = AccountStatusTypeController.getInstance();
        AccountStatusType expResult = null;
        AccountStatusType result = instance.getAccountStatusTypeById(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAccountStatusTypeByName method, of class AccountStatusTypeController.
     */
    @Test
    public void testGetAccountStatusTypeByName() {
        System.out.println("getAccountStatusTypeByName");
        String name = "";
        AccountStatusTypeController instance = AccountStatusTypeController.getInstance();
        AccountStatusType expResult = null;
        AccountStatusType result = instance.getAccountStatusTypeByName(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllAccountStatusTypes method, of class AccountStatusTypeController.
     */
    @Test
    public void testGetAllAccountStatusTypes() {
        System.out.println("getAllAccountStatusTypes");
        AccountStatusTypeController instance = AccountStatusTypeController.getInstance();
        List expResult = instance.getAllAccountStatusTypes();
        List result = instance.getAllAccountStatusTypes();
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteAccountStatusType method, of class AccountStatusTypeController.
     */
    @Test
    public void testDeleteAccountStatusType() {
        System.out.println("deleteAccountStatusType");
        int accountStatusTypeId = 0;
        AccountStatusTypeController instance = AccountStatusTypeController.getInstance();
        boolean expResult = false;
        boolean result = instance.deleteAccountStatusType(accountStatusTypeId);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAccountStatusTypeNames method, of class AccountStatusTypeController.
     */
    @Test
    public void testGetAccountStatusTypeNames() {
        System.out.println("getAccountStatusTypeNames");
        AccountStatusTypeController instance = AccountStatusTypeController.getInstance();
        String[] expResult = {"zaplaceno", "nezaplaceno", "nemusi platit"};
        String[] result = instance.getAccountStatusTypeNames();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAccountStatusTypes method, of class AccountStatusTypeController.
     */
    @Test
    public void testGetAccountStatusTypes() {
        System.out.println("getAccountStatusTypes");
        AccountStatusTypeController instance = AccountStatusTypeController.getInstance();
        Object[][] expResult = instance.getAccountStatusTypes();
        Object[][] result = instance.getAccountStatusTypes();
        assertEquals(expResult, result);
    }

}