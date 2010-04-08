/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service.controllers;

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
public class UserControllerTest {

    public UserControllerTest() {
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
     * Test of getInstance method, of class UserController.
     */
    @Test
    public void testGetInstance() {
       System.out.println("getInstance");
       UserController result = UserController.getInstance();
       if (result == null) fail();
       if (!(result instanceof UserController)) fail();
    }

    /**
     * Test of createUser method, of class UserController.
     */
    @Test
    public void testCreateUser_5args_1() {
        System.out.println("createUser");
        String name = "Jakub";
        String surname = "Jambor";
        String pid = "aaa";
        String username = "Arnoui";
        String passwd = "stripe";
        UserController instance = UserController.getInstance();
        boolean expResult = true;
        boolean result = instance.createUser(name, surname, pid, username, passwd);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of createUser method, of class UserController.
     */
    @Test
    public void testCreateUser_5args_2() {
        System.out.println("createUser");
        String name = "Lojza";
        String surname = "Bojza";
        String pid = "bbb";
        String username = "Bojza";
        double credit = 500.0;
        UserController instance = UserController.getInstance();
        boolean expResult = true;
        boolean result = instance.createUser(name, surname, pid, username);
        assertEquals(expResult, result);
    }

     /**
     * Test of deleteUser method, of class UserController.
     */
    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        int userId = 2;
        UserController instance = UserController.getInstance();
        boolean expResult = true;
        boolean result = instance.deleteUser(userId);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of isExistedByUsername method, of class UserController.
     */
    @Test
    public void testIsExistedByUsername() {
        System.out.println("isExistedByUsername");
        String username = "Arnoui";
        String username2 = "Andrej";
        UserController instance = UserController.getInstance();
        boolean expResult = true;
        boolean result = instance.isExistedByUsername(username);
        assertEquals(expResult, result);
        expResult = false;
        result = instance.isExistedByUsername(username2);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of isExistedByPID method, of class UserController.
     */
    @Test
    public void testIsExistedByPID() {
        System.out.println("isExistedByPID");
        String pid = "aaa";
        UserController instance = UserController.getInstance();
        boolean expResult = true;
        boolean result = instance.isExistedByPID(pid);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of isValidUser method, of class UserController.
     */
    @Test
    public void testIsValidUser() {
        System.out.println("isValidUser");
        String username = "Arnoui";
        String passwd = "stripe";
        UserController instance = UserController.getInstance();
        boolean expResult = true;
        boolean result = instance.isValidUser(username, passwd);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getUserByPID method, of class UserController.
     */
    @Test
    public void testGetUserByPID() {
        System.out.println("getUserByPID");
        String pid = "aaa";
        UserController instance = UserController.getInstance();
        User expResult = generateTestUser();
        User result = instance.getUserByPID(pid);
        if ((int) expResult.getUserId() != (int) result.getUserId()) fail();
    }

    /**
     * Test of getUserByUsername method, of class UserController.
     */
    @Test
    public void testGetUserByUsername() {
        System.out.println("getUserByUsername");
        String userName = "Arnoui";
        UserController instance = UserController.getInstance();
        User expResult = generateTestUser();
        User result = instance.getUserByUsername(userName);
        if ((int) expResult.getUserId() != (int) result.getUserId()) fail();
    }

    /**
     * Test of getUserById method, of class UserController.
     */
    @Test
    public void testGetUserById() {
        System.out.println("getUserById");
        int id = 1;
        UserController instance = UserController.getInstance();
        User expResult = generateTestUser();
        User result = instance.getUserById(id);
        if ((int) expResult.getUserId() != (int) result.getUserId()) fail();
    }

    /**
     * Test of getAllUsers method, of class UserController.
     */
    @Test
    public void testGetAllUsers() {
        System.out.println("getAllUsers");
        UserController instance = UserController.getInstance();
        List<User> expResult = new ArrayList();
        User user = generateTestUser();
        expResult.add(user);
        List<User> result = instance.getAllUsers();
        System.out.println(result.size() + " " + expResult.size());
        if (result.size() != expResult.size()) fail();
        if ((int) result.get(0).getUserId() != (int) expResult.get(0).getUserId()) fail();
    }

    

    /**
     * Test of getUserNames method, of class UserController.
     */
    @Test
    public void testGetUserNames() {
        System.out.println("getUserNames");
        UserController instance = UserController.getInstance();
        String[] expResult = new String[1];
        expResult[0] = "Jambor";
        String[] result = instance.getUserNames();
        if (result == null) fail();
        if (!expResult[0].equals(result[0])) fail();
    }

    /**
     * Test of getUserUsernames method, of class UserController.
     */
    @Test
    public void testGetUserUsernames() {
        System.out.println("getUserUsernames");
        UserController instance = UserController.getInstance();
        String[] expResult = new String[1];
        expResult[0] = "Arnoui";
        String[] result = instance.getUserUsernames();
        if (result == null) fail();
        if (!expResult[0].equals(result[0])) fail();
    }

    /**
     * Test of getUsers method, of class UserController.
     */
    @Test
    public void testGetUsers() {
        System.out.println("getUsers");
        UserController instance = UserController.getInstance();
        Object[][] expResult = new Object[1][7];
        User user = generateTestUser();
        expResult[0][0] = user.getUserId();
        expResult[0][1] = user.getFirstName();
        expResult[0][2] = user.getLastName();
        String roles = "";
        roles = "Žádné definované role";
        expResult[0][3] = roles;
        expResult[0][4] = user.getPersonalIdentificationNumber();
        expResult[0][5] = user.getUsername();
        String pass = user.getPassword();
        if (pass==null) expResult[0][6] = "-";
            else if (pass.equals("1234")){
                expResult[0][6] = "NE";
            } else {
                expResult[0][6] = "ANO";
            }

        Object[][] result = instance.getUsers();
        if (expResult.length != result.length) fail();
        for (int i = 0; i < expResult.length; i++) {
            if (!expResult[0][i].equals(result[0][i])) fail("" +i);
        }
    }

    /**
     * Test of updateUser method, of class UserController.
     */
    @Test
    public void testUpdateUser() {
        System.out.println("updateUser");
        Integer userId = 1;
        String name = "Jakub";
        String surname = "Jambor";
        String pid = "aaa";
        String username = "Arny";
        UserController instance = UserController.getInstance();
        boolean expResult = true;
        boolean result = instance.updateUser(userId, name, surname, pid, username);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of updateUserPassword method, of class UserController.
     */
    @Test
    public void testUpdateUserPassword() {
        System.out.println("updateUserPassword");
        Integer userId = 1;
        String newPassword = "stripe1212";
        UserController instance = UserController.getInstance();
        boolean expResult = true;
        boolean result = instance.updateUserPassword(userId, newPassword);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getDefaultPasswd method, of class UserController.
     */
    @Test
    public void testGetDefaultPasswd() {
        System.out.println("getDefaultPasswd");
        UserController instance = UserController.getInstance();
        String expResult = "1234";
        String result = instance.getDefaultPasswd();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of isValidOldPasswd method, of class UserController.
     */
    @Test
    public void testIsValidOldPasswd() {
        System.out.println("isValidOldPasswd");
        Integer userId = 1;
        String passwd = "stripe1212";
        UserController instance = UserController.getInstance();
        boolean expResult = true;
        boolean result = instance.isValidOldPasswd(userId, passwd);
        assertEquals(expResult, result);
        
    }

   

    public User generateTestUser() {
        User user = new User();
        user.setUserId(1);
        user.setFirstName("Jakub");
        user.setLastName("Jambor");
        user.setPersonalIdentificationNumber("aaa");
        user.setUsername("Arnoui");
        user.setPassword("stripe");
        return user;
    }
}