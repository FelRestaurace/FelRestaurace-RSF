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
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Jarda
 */
public class AccountController {

    private static final String DEFAULT_PASSWD = "1234";
    protected static AccountController instance = null;
    protected Account account = null;

    private AccountController() {
    }

    public static AccountController getInstance() {
        if (instance == null) {
            instance = new AccountController();
        }
        return instance;
    }

    //vytvari zaznam o novem uctu
    public boolean createAccount(String name, int accountStatusTypeId, int tableId, int userId, int discountTypeId) {
        if (name!=null&&!name.equals("")) {
            Account a = getAccountByName(name);
            if (a!=null&&a.getName().equals(name)/*&&a.getAccountStatusType().getAccountStatusTypeId()==*/) return false;
            account = new Account();
            account.setName(name);
            AccountStatusType accountStatusType = AccountStatusType.findById(accountStatusTypeId);
            account.setAccountStatusType(accountStatusType);
            Table table = Table.findById(tableId);
            account.setTable(table);
            User user = User.findById(userId);
            account.setUser(user);
            DiscountType discountType = DiscountType.findById(discountTypeId);
            account.setDiscountType(discountType);
            account.create();
            return true;
        } else {
            return false;
        }
    }

    //vraci account s danym Id
    public Account getAccountById(int id) {
        return Account.findById(id);
    }

    //vraci account s danym jmenem
    public Account getAccountByName(String name) {
        Account a = Account.findByName(name);
        return a;
    }

    //vytvari dvojrozmerne pole typu Object pro metody getAccountsByTable, getAccountsByUser, getAccountsByDiscountType, getAccountsByAccountStatusType, getAccounts
    public Object [][] createAccountArray(List<Account> list) {
        if (list == null || list.isEmpty()) return null;
        Object [][] array = new Object [list.size()][6];
        Iterator it = list.iterator();
        int i = 0;
        while (it.hasNext()){
            Account a = (Account) it.next();
            Table table = a.getTable();
            User user = a.getUser();
            DiscountType discountType = a.getDiscountType();
            array[i][0] = a.getAccountId();
            array[i][1] = a.getName();
            array[i][2] = (table==null?null:table.getTableNumber());
            array[i][3] = (user==null?null:user.getUsername());
            array[i][4] = (discountType==null)?null:discountType.getName();
            array[i][5] = a.getAccountStatusType().getName();
            i++;
        }
        return array;
    }

    // vraci vsechny ucty prirazene stolu s danym id
    public Object [][] getAccountsByTable(int tableId){
        List<Account> list = Account.findByTable(tableId);
        return createAccountArray(list);
    }

    // vraci vsechny ucty prirazene osobe s danym id
    public Object [][] getAccountsByUser(int userId){
        List<Account> list = Account.findByUser(userId);
        return createAccountArray(list);
    }

    // vraci vsechny ucty s typem slevy s danym id
    public Object [][] getAccountsByDiscountType(int discountTypeId){
        List<Account> list = Account.findByDiscountType(discountTypeId);
        return createAccountArray(list);
    }

    // vraci vsechny ucty se stavem s danym id
    public Object [][] getAccountsByAccountStatusType(int accountStatusTypeId){
        List<Account> list = Account.findByAccountStatusType(accountStatusTypeId);
        return createAccountArray(list);
    }

    //vraci vsechny ucty ve forme Listu
    public List getAllAccounts() {
        return Account.findAll();
    }

    //maze ucet s danym Id
    public boolean deleteAccount(int accountId) {
        account = Account.findById(accountId);
        if (account == null) {
            return false;
        }
        account.setIsDeleted(1);
        return true;
    }

    //nastavuje uctu s danym Id novy status
    public boolean updateAccount(Integer accountId, Integer accountStatusTypeId) {
        account = Account.findById(accountId);
        if (account == null) return false;
        AccountStatusType status = AccountStatusType.findById(accountStatusTypeId);
        account.setAccountStatusType(status);
        return true;
    }

    //v podobe pole typu String navraci nazvy vsech uctu
    public String[] getAccountNames() {
        List list = Account.findAll();
        if (list == null || list.isEmpty()) {
            return null;
        }
        String array[] = new String[list.size()];
        Iterator it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            array[i] = ((Account) it.next()).getName();
            i++;
        }
        return array;
    }

    //v podobe dvojrozmerneho pole typu Object navraci udaje o vsech uctech
    public Object[][] getAccounts() {
        List<Account> list = Account.findAll();
        return createAccountArray(list);
    }

    public boolean updateAccount(int accountId,String name, int accountStatusTypeId, int tableId, int userId, int discountTypeId){
        Account acc = Account.findById(accountId);
        if (acc == null) return false;
        if (name.equals("")) return false;
        
        AccountStatusType ast = AccountStatusType.findById(accountStatusTypeId);
        Table table = Table.findById(tableId);
        DiscountType discount = DiscountType.findById(discountTypeId);
        User user = User.findById(userId);
        if (ast == null) return false;
        acc.setAccountStatusType(ast);
        if (getAccountByName(name) == null) acc.setName(name);
        acc.setTable(table);
        acc.setDiscountType(discount);
        acc.setUser(user);
        acc.update();
        return true;
    }
}