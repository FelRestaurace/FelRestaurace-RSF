
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service.controllers;

import hibernate.Account;
import hibernate.AccountStatusType;
import hibernate.DiscountType;
import hibernate.Table;
import hibernate.User;
import java.util.ArrayList;
import java.util.List;
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
public class AccountControllerTest {

    public AccountControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        AccountStatusTypeController astinst = AccountStatusTypeController.getInstance();
        TableController tableinst = TableController.getInstance();
        UserController userinst = UserController.getInstance();
        DiscountTypeController discinst = DiscountTypeController.getInstance();

        astinst.createAccountStatusType("test", "test");
        tableinst.createTable(1, 4);
        userinst.createUser("Jakub", "Jambor", "aaa","Arnoui");
        discinst.createDiscountType("Sekera");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class AccountController.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        AccountController result = AccountController.getInstance();
        if (result == null) fail("Expected AccountController instance, got null.");
        if (!(result instanceof AccountController)) {
            String inst = result.getClass().getName();
            fail("Expected AccountController instance, got " + inst);
        }
    }

    /**
     * Test of createAccount method, of class AccountController.
     */
    @Test
    public void testCreateAccount() {
        System.out.println("createAccount");

        AccountController instance = AccountController.getInstance();
        
        String name = "ucet";
        int accountStatusTypeId = 1;
        int tableId = 1;
        int userId = 1;
        int discountTypeId = 1;
        
        boolean expResult = true;
        boolean result = instance.createAccount(name, accountStatusTypeId, tableId, userId, discountTypeId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getAccountById method, of class AccountController.
     */
    @Test
    public void testGetAccountById() {
        System.out.println("getAccountById");
        AccountController instance = AccountController.getInstance();
        Account expResult = new Account();
        AccountStatusType ast = new AccountStatusType();
        User person = new User();
        Table table = new Table();
        table.setTableId(1);
        person.setUserId(1);
        ast.setAccountStatusTypeId(1);
        DiscountType dt = new DiscountType();
        dt.setDiscountTypeId(1);
        expResult.setAccountId(1);
        expResult.setAccountStatusType(ast);
        expResult.setDiscountType(dt);
        expResult.setUser(person);
        expResult.setTable(table);
        expResult.setName("ucet");
        Account result1 = instance.getAccountById(1);
        Account result2 = instance.getAccountById(2);
        if (result2 != null) fail("Expected null, returned " + result2.toString());
        //if ((int) result1.getAccountId() != (int) expResult.getAccountId()) fail();
        
        
    }

    /**
     * Test of getAccountByName method, of class AccountController.
     */
    @Test
    public void testGetAccountByName() {
        System.out.println("getAccountByName");
        String name = "ucet";
        AccountController instance = AccountController.getInstance();
        Account expResult = generateTestAccount();
        Account result = instance.getAccountByName(name);
        if (!expResult.getName().equals(result.getName())) fail();
    }

    /**
     * Test of createAccountArray method, of class AccountController.
     */
    @Test
    public void testCreateAccountArray() {
        System.out.println("createAccountArray");
        List<Account> list = Account.findAll();
        if (list == null) fail("List is null.");
        AccountController instance = AccountController.getInstance();
        Account expResult = generateTestAccount();
        Table table = expResult.getTable();
        User person = expResult.getUser();
        DiscountType dt = expResult.getDiscountType();
        
        Object[][] exp = new Object[1][6];
        exp[0][0] = expResult.getAccountId();
        exp[0][1] = expResult.getName();
        exp[0][2] = (table==null?null:table.getTableNumber());
        exp[0][3] = (person==null?null:person.getUsername());
        exp[0][4] = (dt==null)?null:dt.getName();
        exp[0][5] = expResult.getAccountStatusType().getName();

        Object[][] result = instance.createAccountArray(list);
        
        if (exp.length != result.length) fail();
        for (int i = 0; i<6; i++) {
            
            if (!(exp[0][i].toString().equals(result[0][i].toString()))) fail("" + i);
        }
        
    }

    /**
     * Test of getAccountsByTable method, of class AccountController.
     */
    @Test
    public void testGetAccountsByTable() {
        System.out.println("getAccountsByTable");
        int tableId = 1;
        AccountController instance = AccountController.getInstance();
        
        Object[][] result = instance.getAccountsByTable(tableId);
        for (int i = 0; i < result.length; i++) {
            if (Integer.parseInt(result[i][2].toString()) != tableId) fail();
        }
    }

    /**
     * Test of getAccountsByUser method, of class AccountController.
     */
    @Test
    public void testGetAccountsByUser() {
        System.out.println("getAccountsByUser");
        int userId = 1;
        AccountController instance = AccountController.getInstance();
        User user = User.findById(userId);
        Object[][] result = instance.getAccountsByUser(userId);
        for (int i = 0; i < result.length; i++) {
            if (!(result[i][3].toString().equals(user.getUsername()))) fail();
        }
    }

    /**
     * Test of getAccountsByDiscountType method, of class AccountController.
     */
    @Test
    public void testGetAccountsByDiscountType() {
        System.out.println("getAccountsByDiscountType");
        int discountTypeId = 1;
        AccountController instance = AccountController.getInstance();
        DiscountType dsc = DiscountType.findById(discountTypeId);
        Object[][] result = instance.getAccountsByDiscountType(discountTypeId);
        for (int i = 0; i < result.length; i++) {
            if (!(result[i][4].toString().equals(dsc.getName()))) fail();
        }
    }

    /**
     * Test of getAccountsByAccountStatusType method, of class AccountController.
     */
    @Test
    public void testGetAccountsByAccountStatusType() {
        System.out.println("getAccountsByAccountStatusType");
        int accountStatusTypeId = 1;
        AccountController instance = AccountController.getInstance();
        AccountStatusType ast = AccountStatusType.findById(accountStatusTypeId );
        Object[][] result = instance.getAccountsByAccountStatusType(accountStatusTypeId);
        for (int i = 0; i < result.length; i++) {
            if (!(result[i][5].toString().equals(ast.getName()))) fail();
        }
    }

    /**
     * Test of getAllAccounts method, of class AccountController.
     */
    @Test
    public void testGetAllAccounts() {
        System.out.println("getAllAccounts");
        AccountController instance = AccountController.getInstance();;
        List expResult = new ArrayList();
        Account acc = generateTestAccount();
        expResult.add(acc);
        List result = instance.getAllAccounts();
        if (expResult.size() != result.size()) fail();
        Account acc1 = (Account) result.get(0);
        Account acc2 = (Account) expResult.get(0);

        if ((int) acc1.getAccountId() != (int) acc2.getAccountId()) fail();
        
    }

    /**
     * Test of getAccountNames method, of class AccountController.
     */
    @Test
    public void testGetAccountNames() {
        System.out.println("getAccountNames");
        AccountController instance = AccountController.getInstance();
        String[] result = instance.getAccountNames();
        if (result.length != 1 || !result[0].equals("ucet")) fail();

    }

     

    /**
     * Test of getAccounts method, of class AccountController.
     */
    @Test
    public void testGetAccounts() {
        System.out.println("getAccounts");
        AccountController instance = AccountController.getInstance();
        Account expResult = generateTestAccount();
        User person = expResult.getUser();
        Table table = expResult.getTable();
        DiscountType dt = expResult.getDiscountType();

        Object[][] exp = new Object[1][6];
        exp[0][0] = expResult.getAccountId();
        exp[0][1] = expResult.getName();
        exp[0][2] = (table==null?null:table.getTableNumber());
        exp[0][3] = (person==null?null:person.getUsername());
        exp[0][4] = (dt==null)?null:dt.getName();
        exp[0][5] = expResult.getAccountStatusType().getName();

        Object[][] result = instance.getAccounts();
        if (result == null) fail();
        if (exp.length != result.length) fail();
        for (int i = 0; i<6; i++) {
            
            if (!(exp[0][i].toString().equals(result[0][i].toString()))) fail("" + i);
        }


    }

    /**
     * Test of updateAccount method, of class AccountController.
     */
    @Test
    public void testUpdateAccount() {
        System.out.println("updateAccount");
        int accountId = 1;
        String name = "Ucet 2";
        int accountStatusTypeId = 1;
        int tableId = 1;
        int userId = 1;
        int discountTypeId = 1;
        AccountController instance = AccountController.getInstance();
        boolean expResult = true;
        boolean result = instance.updateAccount(accountId, name, accountStatusTypeId, tableId, userId, discountTypeId);
        assertEquals(expResult, result);

        Account acc = Account.findById(accountId);
        if (!acc.getName().equals(name)) fail();
    }

    /**
     * Test of deleteAccount method, of class AccountController.
     */
    @Test
    public void testDeleteAccount() {
        System.out.println("deleteAccount");
        int accountId = 1;
        AccountController instance = AccountController.getInstance();
        boolean expResult = true;
        boolean result = instance.deleteAccount(accountId);
        assertEquals(expResult, result);
        expResult = false;
        result = instance.deleteAccount(accountId);
        assertEquals(expResult, result);
    }

    public Account generateTestAccount(){
        Account expResult = new Account();
        AccountStatusType ast = new AccountStatusType();
        User person = new User();
        Table table = new Table();
        DiscountType dt = new DiscountType();
        table.setTableId(1);
        table.setNumberOfPlaces(4);
        table.setTableNumber(1);
        person.setUserId(1);
        person.setFirstName("Jakub");
        person.setLastName("Jambor");
        person.setPersonalIdentificationNumber("aaa");
        person.setUsername("Arnoui");
        person.setCredit(500);
        ast.setAccountStatusTypeId(1);
        ast.setName("test");
        ast.setNote("test");
        dt.setName("Sekera");
        dt.setDiscountTypeId(1);
        expResult.setAccountId(1);
        expResult.setAccountStatusType(ast);
        expResult.setDiscountType(dt);
        expResult.setUser(person);
        expResult.setTable(table);
        expResult.setName("ucet");
        return expResult;
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */




//
//package service.controllers;
//
//import hibernate.Account;
//import java.util.List;
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
//public class AccountControllerTest {
//
//    public AccountControllerTest() {
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
//     * Test of getInstance method, of class AccountController.
//     */
//
//    @Test
//    public void testGetInstance() {
//        System.out.println("getInstance");
//        AccountController expResult = AccountController.getInstance();
//        AccountController result = AccountController.getInstance();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of createAccount method, of class AccountController.
//     */
//    @Test
//    public void testCreateAccount() {
//        System.out.println("createAccount");
//        String name = null;
//        int accountStatusTypeId = 0;
//        int tableId = 0;
//        int userId = 0;
//        int discountTypeId = 0;
//        AccountController instance = AccountController.getInstance();
//        boolean expResult = false;
//        boolean result = instance.createAccount(name, accountStatusTypeId, tableId, userId, discountTypeId);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getAccountById method, of class AccountController.
//     */
//    @Test
//    public void testGetAccountById() {
//        System.out.println("getAccountById");
//        int id = 0;
//        AccountController instance = AccountController.getInstance();
//        Account expResult = null;
//        Account result = instance.getAccountById(id);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getAccountByName method, of class AccountController.
//     */
//    @Test
//    public void testGetAccountByName() {
//        System.out.println("getAccountByName");
//        String name = "";
//        AccountController instance = AccountController.getInstance();
//        Account expResult = null;
//        Account result = instance.getAccountByName(name);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of createAccountArray method, of class AccountController.
//     */
//    @Test
//    public void testCreateAccountArray() {
//        System.out.println("createAccountArray");
//        List<Account> list = null;
//        AccountController instance = AccountController.getInstance();
//        Object[][] expResult = null;
//        Object[][] result = instance.createAccountArray(list);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getAccountsByTable method, of class AccountController.
//     */
//    @Test
//    public void testGetAccountsByTable() {
//        System.out.println("getAccountsByTable");
//        int tableId = 0;
//        AccountController instance = AccountController.getInstance();
//        Object[][] expResult = null;
//        Object[][] result = instance.getAccountsByTable(tableId);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getAccountsByUser method, of class AccountController.
//     */
//    @Test
//    public void testGetAccountsByUser() {
//        System.out.println("getAccountsByUser");
//        int userId = 0;
//        AccountController instance = AccountController.getInstance();
//        Object[][] expResult = null;
//        Object[][] result = instance.getAccountsByUser(userId);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getAccountsByDiscountType method, of class AccountController.
//     */
//    @Test
//    public void testGetAccountsByDiscountType() {
//        System.out.println("getAccountsByDiscountType");
//        int discountTypeId = 0;
//        AccountController instance = AccountController.getInstance();
//        Object[][] expResult = null;
//        Object[][] result = instance.getAccountsByDiscountType(discountTypeId);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getAccountsByAccountStatusType method, of class AccountController.
//     */
//    @Test
//    public void testGetAccountsByAccountStatusType() {
//        System.out.println("getAccountsByAccountStatusType");
//        int accountStatusTypeId = 0;
//        AccountController instance = AccountController.getInstance();
//        Object[][] expResult = null;
//        Object[][] result = instance.getAccountsByAccountStatusType(accountStatusTypeId);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getAllAccounts method, of class AccountController.
//     */
//    @Test
//    public void testGetAllAccounts() {
//        System.out.println("getAllAccounts");
//        AccountController instance = AccountController.getInstance();
//        List expResult = instance.getAllAccounts();
//        List result = instance.getAllAccounts();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of deleteAccount method, of class AccountController.
//     */
//    @Test
//    public void testDeleteAccount() {
//        System.out.println("deleteAccount");
//        int accountId = 0;
//        AccountController instance = AccountController.getInstance();
//        boolean expResult = false;
//        boolean result = instance.deleteAccount(accountId);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of updateAccount method, of class AccountController.
//     */
//    @Test
//    public void testUpdateAccount() {
//        System.out.println("updateAccount");
//        Integer accountId = null;
//        Integer accountStatusTypeId = null;
//        AccountController instance = AccountController.getInstance();
//        boolean expResult = false;
//        boolean result = instance.updateAccount(accountId, accountStatusTypeId);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getAccountNames method, of class AccountController.
//     */
//    @Test
//    public void testGetAccountNames() {
//        System.out.println("getAccountNames");
//        AccountController instance = AccountController.getInstance();
//        String[] expResult = {"prvniUcet", "druhyUcet", "ucet na baru", "bar", "ucetUcet", "posledni pokus"};
//        String[] result = instance.getAccountNames();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getAccounts method, of class AccountController.
//     */
//    @Test
//    public void testGetAccounts() {
//        System.out.println("getAccounts");
//        AccountController instance = AccountController.getInstance();
//        Object[][] expResult = instance.getAccounts();
//        Object[][] result = instance.getAccounts();
//        assertEquals(expResult, result);
//    }
//
//} //>>>>>>> .r68
