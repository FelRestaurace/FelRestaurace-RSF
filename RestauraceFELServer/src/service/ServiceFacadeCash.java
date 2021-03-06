/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import hibernate.Account;
import hibernate.AccountStatusType;
import hibernate.DiscountType;
import hibernate.Menu;
import hibernate.MenuItem;
import hibernate.MenuItemType;
import hibernate.Order;
import hibernate.OrderMenuItem;
import hibernate.Role;
import hibernate.Table;
import hibernate.User;
import hibernate.UserRole;
import java.net.InetAddress;
import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.server.*;
import java.util.Date;
import java.util.List;
import service.controllers.AccountController;
import service.controllers.AccountStatusTypeController;
import service.controllers.DiscountTypeController;
import service.controllers.MenuController;
import service.controllers.MenuItemController;
import service.controllers.MenuItemTypeController;
import service.controllers.OrderController;
import service.controllers.OrderMenuItemController;
import service.controllers.RoleController;
import service.controllers.TableController;
import service.controllers.UserController;
import service.controllers.UserRoleController;

/**
 *
 * @author Jarda
 */
public class ServiceFacadeCash extends UnicastRemoteObject implements IServiceFacadeCash {

    //singleton
    protected static ServiceFacadeCash instance = null;

    public ServiceFacadeCash() throws RemoteException {
        super();
    }

    public void initServiceFacadeRMI(Registry reg) throws java.net.UnknownHostException, RemoteException {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }

        String name = "ServiceFacadeCash";
        InetAddress inetAddress = InetAddress.getLocalHost();
        //Stub
        IServiceFacadeCash facade = ServiceFacadeCash.getInstance();
        reg.rebind(name, facade);
        System.out.println("Servisni fasada pro modul POKLADNA zaregistrovana pod jmenem \"ServiceFacadeCash\"");
        System.out.println("Pripojeni pres adresu:" + inetAddress.toString() + "\n\n");

    }

    //Vraci instanci tridy ServiceFacadeManager
    public static ServiceFacadeCash getInstance() throws RemoteException {
        if (instance == null) {
            instance = new ServiceFacadeCash();
        }
        return instance;
    }


    /*
     *
     * Zde bude implementace metod deklarovanych v rozhrani IServiceFacadeCash
     *
     * metody pro modul POKLADNA
     */
    @Override
    public String getSomething() throws RemoteException {
        return "Message from cash module";
    }

    @Override
    public User verifyUser(String username, char[] password) throws RemoteException {
        return null;
    }

    /*
     * Copy paste z ServiceFasadeManager
     * pridana metoda getMenuByName
     *
     */
    //ACCOUNTSTATUSTYPE
    @Override
    public List getAllAccountStatusTypes() throws RemoteException {
        return AccountStatusTypeController.getInstance().getAllAccountStatusTypes();
    }

    @Override
    public boolean createAccountStatusType(String name, String note) throws RemoteException {
        return AccountStatusTypeController.getInstance().createAccountStatusType(name, note);
    }

    @Override
    public AccountStatusType getAccountStatusTypeById(int id) throws RemoteException {
        return AccountStatusTypeController.getInstance().getAccountStatusTypeById(id);
    }

    @Override
    public AccountStatusType getAccountStatusTypeByName(String name) throws RemoteException {
        return AccountStatusTypeController.getInstance().getAccountStatusTypeByName(name);
    }

    @Override
    public boolean deleteAccountStatusType(int accountStatusTypeId) throws RemoteException {
        return AccountStatusTypeController.getInstance().deleteAccountStatusType(accountStatusTypeId);
    }

    @Override
    public String[] getAccountStatusTypeNames() throws RemoteException {
        return AccountStatusTypeController.getInstance().getAccountStatusTypeNames();
    }

    @Override
    public Object[][] getAccountStatusTypes() throws RemoteException {
        return AccountStatusTypeController.getInstance().getAccountStatusTypes();
    }

    //ACCOUNT
    @Override
    public List getAllAccounts() throws RemoteException {
        return AccountController.getInstance().getAllAccounts();
    }

    @Override
    public boolean createAccount(String name, int accountStatusTypeId, int tableId, int userId, int discountTypeId) throws RemoteException {
        return AccountController.getInstance().createAccount(name, accountStatusTypeId, tableId, userId, discountTypeId);
    }

    @Override
    public Account getAccountById(int id) throws RemoteException {
        return AccountController.getInstance().getAccountById(id);
    }

    @Override
    public Account getAccountByName(String name) throws RemoteException {
        return AccountController.getInstance().getAccountByName(name);
    }

    @Override
    public Object[][] getAccountsByTable(int tableId) throws RemoteException {
        return AccountController.getInstance().getAccountsByTable(tableId);
    }

    @Override
    public Object[][] getAccountsByUser(int userId) throws RemoteException {
        return AccountController.getInstance().getAccountsByUser(userId);
    }

    @Override
    public Object[][] getAccountsByDiscountType(int discountTypeId) throws RemoteException {
        return AccountController.getInstance().getAccountsByDiscountType(discountTypeId);
    }

    @Override
    public Object[][] getAccountsByAccountStatusType(int accountStatusTypeId) throws RemoteException {
        return AccountController.getInstance().getAccountsByAccountStatusType(accountStatusTypeId);
    }

    @Override
    public boolean deleteAccount(int accountId) throws RemoteException {
        return AccountController.getInstance().deleteAccount(accountId);
    }

    @Override
    public String[] getAccountNames() throws RemoteException {
        return AccountController.getInstance().getAccountNames();
    }

    @Override
    public Object[][] getAccounts() throws RemoteException {
        return AccountController.getInstance().getAccounts();
    }

    @Override
    public boolean updateAccount(int accountId, int accountStatusTypeId) throws RemoteException {
        return AccountController.getInstance().updateAccount(accountId, accountStatusTypeId);
    }

    //DISCOUNTTYPE
    @Override
    public List getAllDiscountTypes() throws RemoteException {
        return DiscountTypeController.getInstance().getAllDiscountTypes();
    }

    @Override
    public boolean createDiscountType(String name) throws RemoteException {
        return DiscountTypeController.getInstance().createDiscountType(name);
    }

    @Override
    public DiscountType getDiscountTypeById(int id) throws RemoteException {
        return DiscountTypeController.getInstance().getDiscountTypeById(id);
    }

    @Override
    public DiscountType getDiscountTypeByName(String name) throws RemoteException {
        return DiscountTypeController.getInstance().getDiscountTypeByName(name);
    }

    @Override
    public boolean deleteDiscountType(int discountTypeId) throws RemoteException {
        return DiscountTypeController.getInstance().deleteDiscountType(discountTypeId);
    }

    @Override
    public String[] getDiscountTypeNames() throws RemoteException {
        return DiscountTypeController.getInstance().getDiscountTypeNames();
    }

    @Override
    public Object[][] getDiscountTypes() throws RemoteException {
        return DiscountTypeController.getInstance().getDiscountTypes();
    }

    //ORDER methods
    @Override
    public List getAllOrders() throws RemoteException {
        return OrderController.getInstance().getAllOrders();
    }

    @Override
    public boolean createOrder(int isPaid, Date time, int accountId, int userId) throws RemoteException {
        return OrderController.getInstance().createOrder(isPaid, time, accountId, userId);
    }

    @Override
    public boolean deleteOrder(int orderId) throws RemoteException {
        return OrderController.getInstance().deleteOrder(orderId);
    }

    @Override
    public Order getOrderById(int orderId) throws RemoteException {
        return OrderController.getInstance().getOrderById(orderId);
    }

    @Override
    public String [] getOrderNames() throws RemoteException {
        return OrderController.getInstance().getOrderNames();
    }

    @Override
    public Object [][] getOrders() throws RemoteException {
        return OrderController.getInstance().getOrders();
    }

    @Override
    public Object [][] getOrdersByAccount(int accountId) throws RemoteException {
        return OrderController.getInstance().getOrdersByAccount(accountId);
    }

    @Override
    public boolean payNMenuItemsByAccount(int n, int menuItemId, int accountId) throws RemoteException {
        return OrderController.getInstance().payNMenuItemsByAccount(n, menuItemId, accountId);
    }

    @Override
    public boolean moveNMenutItemsByAccount(int n, int menuItemId, int sourceAccountId, int targetAccountId) throws RemoteException {
        return OrderController.getInstance().moveNMenuItemsByAccount(n, menuItemId, sourceAccountId, targetAccountId);
    }

    //ORDERMENUITEM methods
    @Override
    public List getAllOrderMenuItems() throws RemoteException {
        return OrderMenuItemController.getInstance().getAllOrderMenuItems();
    }

    @Override
    public boolean createOrderMenuItem(int menuItemId, int orderId) throws RemoteException {
        return OrderMenuItemController.getInstance().createOrderMenuItem(menuItemId, orderId);
    }

    @Override
    public boolean deleteOrderMenuItem(int orderMenuItemId) throws RemoteException {
        return OrderMenuItemController.getInstance().deleteOrderMenuItem(orderMenuItemId);
    }

    @Override
    public OrderMenuItem getOrderMenuItemById(int orderMenuItemId) throws RemoteException {
        return OrderMenuItemController.getInstance().getOrderMenuItemById(orderMenuItemId);
    }

    @Override
    public String [] getOrderMenuItemNames() throws RemoteException {
        return OrderMenuItemController.getInstance().getOrderMenuItemNames();
    }

    @Override
    public Object [][] getOrderMenuItems() throws RemoteException {
        return OrderMenuItemController.getInstance().getOrderMenuItems();
    }

    //USER
    @Override
    public List getAllUsers() throws RemoteException {
        return UserController.getInstance().getAllUsers();
    }

    @Override
    public boolean createUser(String name, String surname, String pid, String username, String passwd) throws RemoteException {
        return UserController.getInstance().createUser(name, surname, pid, username, passwd);
    }

    @Override
    public boolean createUser(String name, String surname, String pid, String username) throws RemoteException {
        return UserController.getInstance().createUser(name, surname, pid, username);
    }

    @Override
    public boolean isValidUser(String username, String passwd) throws RemoteException {
        return UserController.getInstance().isValidUser(username, passwd);
    }

    @Override
    public User getUserByPID(String pid) throws RemoteException {
        return UserController.getInstance().getUserByPID(pid);
    }

    @Override
    public User getUserByUsername(String userName) throws RemoteException {
        return UserController.getInstance().getUserByUsername(userName);
    }

    @Override
    public User getUserById(int id) throws RemoteException {
        return UserController.getInstance().getUserById(id);
    }

    @Override
    public boolean deleteUser(int userId) throws RemoteException {
        return UserController.getInstance().deleteUser(userId);
    }

    @Override
    public String[] getUserNames() throws RemoteException {
        return UserController.getInstance().getUserNames();
    }

    @Override
    public String[] getUserUsernames() throws RemoteException {
        return UserController.getInstance().getUserUsernames();
    }

    @Override
    public Object[][] getUsers() throws RemoteException {
        return UserController.getInstance().getUsers();
    }

    @Override
    public boolean updateUser(Integer userId, String name, String surname, String pid, String username) throws RemoteException {
        return UserController.getInstance().updateUser(userId, name, surname, pid, username);
    }

    @Override
    public boolean updateUser(Integer userId, double credit) throws RemoteException {
        return UserController.getInstance().updateUser(userId, credit);
    }

    @Override
    public String getDefaultPasswd() throws RemoteException {
        return UserController.getInstance().getDefaultPasswd();
    }

    @Override
    public boolean updateUserPassword(Integer userId, String newPassword) throws RemoteException {
        return UserController.getInstance().updateUserPassword(userId, newPassword);
    }

    @Override
    public boolean isValidOldPasswd(Integer userId, String passwd) throws RemoteException {
        return UserController.getInstance().isValidOldPasswd(userId, passwd);
    }

    //ROLE
    @Override
    public List getAllRoles() throws RemoteException {
        return RoleController.getInstance().getAllRoles();
    }

    @Override
    public String[] getRoleNames() throws RemoteException {
        return RoleController.getInstance().getRoleNames();
    }

    @Override
    public Role getRoleByID(int id) throws RemoteException {
        return RoleController.getInstance().getRoleByID(id);
    }

    @Override
    public Role getRoleByName(String name) throws RemoteException {
        return RoleController.getInstance().getRoleByName(name);
    }

    //MENUITEM
    @Override
    public boolean createMenuItem(String name, double price, String quantity, int isAvailable, int menuItemTypeId) throws RemoteException {
        return MenuItemController.getInstance().createMenuItem(name, price, quantity, isAvailable, menuItemTypeId);
    }

    @Override
    public boolean deleteMenuItem(int menuItemId) throws RemoteException {
        return MenuItemController.getInstance().deleteMenuItem(menuItemId);
    }

    @Override
    public boolean updateMenuItem(int menuItemId, String name, double price, String quantity, int isAvailable, int menuItemTypeId) throws RemoteException {
        return MenuItemController.getInstance().updateMenuItem(menuItemId, name, price, quantity, isAvailable, menuItemTypeId);
    }

    @Override
    public MenuItem getMenuItemById(int menuItemId) throws RemoteException {
        return MenuItemController.getInstance().getMenuItemById(menuItemId);
    }

    @Override
    public MenuItem getMenuItemByName(String name) throws RemoteException {
        return MenuItemController.getInstance().getMenuItemByName(name);
    }

    @Override
    public String[] getMenuItemNames() throws RemoteException {
        return MenuItemController.getInstance().getMenuItemNames();
    }

    @Override
    public Object[][] getMenuItems() throws RemoteException {
        return MenuItemController.getInstance().getMenuItems();
    }

    @Override
    public Object[][] getMenuItemsByMenu(int menuId) throws RemoteException {
        return MenuItemController.getInstance().getMenuItemsByMenu(menuId);
    }

    @Override
    public List<MenuItem> getMenuItemsByMenuList(int menuId) throws RemoteException {
        return MenuItemController.getInstance().getMenuItemsByMenuList(menuId);
    }

    @Override
    public Object[][] getMenuItemsByAccount(int accountId) throws RemoteException {
        return MenuItemController.getInstance().getMenuItemsByAccount(accountId);
    }

    @Override
    public Object[][] getMenuItemsByMenuItemType(int menuItemTypeId) throws RemoteException {
        return MenuItemController.getInstance().getMenuItemsByMenuItemType(menuItemTypeId);
    }

    @Override
    public Object [][] getAllMenuItemsByAccount(int accountId) throws RemoteException {
        return MenuItemController.getInstance().getAllMenuItemsByAccount(accountId);
    }

    @Override
    public List<MenuItem> getMenuItemsByMenuItemTypeList(int menuItemTypeId) throws RemoteException {
        return MenuItemController.getInstance().getMenuItemsByMenuItemTypeList(menuItemTypeId);
    }

    //MENU
    @Override
    public boolean createMenu(int userId, String name, Date date) throws RemoteException {
        return MenuController.getInstance().createMenu(userId, name, date);
    }

    @Override
    public boolean deleteMenu(int menuId) throws RemoteException {
        return MenuController.getInstance().deleteMenu(menuId);
    }

    @Override
    public boolean updateMenu(int menuId, int userId, String name, Date date) throws RemoteException {
        return MenuController.getInstance().updateMenu(menuId, userId, name, date);
    }

    @Override
    public Menu getMenuById(int menuId) throws RemoteException {
        return MenuController.getInstance().getMenuById(menuId);
    }

    @Override
    public Menu getMenuByName(String name) throws RemoteException {
        return MenuController.getInstance().getMenuByName(name);
    }

    @Override
    public Object[][] getMenus() throws RemoteException {
        return MenuController.getInstance().getMenus();
    }

    @Override
    public String[] getMenuNames() throws RemoteException {
        return MenuController.getInstance().getMenuNames();
    }

    //USERROLE
    @Override
    public boolean createUserRole(int userId, int roleId) throws RemoteException {
        return UserRoleController.getInstance().createUserRole(userId, roleId);
    }

    @Override
    public void deleteUserRole(int userId, int roleId) throws RemoteException {
        UserRoleController.getInstance().deleteUserRole(userId, roleId);
    }

    @Override
    public UserRole getUserRoleById(int userRoleId) throws RemoteException {
        return UserRoleController.getInstance().getUserRoleById(userRoleId);
    }

    @Override
    public List getUserRoleByUserId(int userId) throws RemoteException {
        return UserRoleController.getInstance().getUserRoleByUserId(userId);
    }

    @Override
    public boolean isExistedUserRole(int userId, int roleId) throws RemoteException {
        UserRole ur = UserRoleController.getInstance().getUserRoleByUserAndRole(userId, roleId);
        if (ur == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean[] getUserRoles(int userId) throws RemoteException {
        return UserRoleController.getInstance().getUserRoles(userId);
    }

    @Override
    public boolean isUserRole(int userId, int roleId) throws RemoteException {
        return UserRoleController.getInstance().isUserRole(userId, roleId);
    }

    //TABLE
    @Override
    public boolean createTable(int tableNumber, int numberOfPlaces) throws RemoteException {
        return TableController.getInstance().createTable(tableNumber, numberOfPlaces);
    }

    @Override
    public boolean deleteTable(int tableId) throws RemoteException {
        return TableController.getInstance().deleteTable(tableId);
    }

    @Override
    public boolean updateTable(int tableId, int tableNumber, int numberOfPlaces) throws RemoteException {
        return TableController.getInstance().updateTable(tableId, tableNumber, numberOfPlaces);
    }

    @Override
    public Table getTableById(int tableId) throws RemoteException {
        return TableController.getInstance().getTableById(tableId);
    }

    @Override
    public Table getTableByTableNumber(int tableNumber) throws RemoteException {
        return TableController.getInstance().getTableByTableNumber(tableNumber);
    }

    @Override
    public int[] getTableNumbers() throws RemoteException {
        return TableController.getInstance().getTableNumbers();
    }

    @Override
    public String[] getTableNames() throws RemoteException {
        return TableController.getInstance().getTableNames();
    }

    @Override
    public Object[][] getTables() throws RemoteException {
        return TableController.getInstance().getTables();
    }

    //MENUITEMTYPE
    @Override
    public boolean createMenuItemType(String name) throws RemoteException {
        return MenuItemTypeController.getInstance().createMenuItemType(name);
    }

    @Override
    public boolean deleteMenuItemType(int menuItemTypeId) throws RemoteException {
        return MenuItemTypeController.getInstance().deleteMenuItemType(menuItemTypeId);
    }

    @Override
    public boolean updateMenuItemType(int menuItemTypeId, String name) throws RemoteException {
        return MenuItemTypeController.getInstance().updateMenuItemType(menuItemTypeId, name);
    }

    @Override
    public MenuItemType getMenuItemTypeById(int menuItemTypeId) throws RemoteException {
        return MenuItemTypeController.getInstance().getMenuItemTypeById(menuItemTypeId);
    }

    @Override
    public MenuItemType getMenuItemTypeByName(String name) throws RemoteException {
        return MenuItemTypeController.getInstance().getMenuItemTypeByName(name);
    }

    @Override
    public String[] getMenuItemTypeNames() throws RemoteException {
        return MenuItemTypeController.getInstance().getMenuItemTypeNames();
    }

    @Override
    public List<MenuItemType> getMenuItemTypesList() throws RemoteException {
        return MenuItemTypeController.getInstance().getMenuItemTypesList();
    }

    @Override
    public Object[][] getMenuItemTypes() throws RemoteException {
        return MenuItemTypeController.getInstance().getMenuItemTypes();
    }

    @Override
    public boolean isDeletableMenuItemType(int menuItemTypeId) throws RemoteException {
        return MenuItemTypeController.getInstance().isDeletableMenuItemType(menuItemTypeId);
    }
}
