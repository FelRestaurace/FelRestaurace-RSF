/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.controllers;

import hibernate.MenuItem;
import hibernate.MenuItemType;
import hibernate.MenuMenuItem;
import hibernate.UsedMaterial;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Jarda
 */
public class MenuItemController {

    private static MenuItemController instance = null;
    private MenuItem menuItem = null;

    private MenuItemController() {
    }

    public static MenuItemController getInstance() {
        if (instance == null) {
            instance = new MenuItemController();
        }
        return instance;
    }

    //vytvari novou polozku menu
    public boolean createMenuItem(String name, double price, String quantity, int isAvailable, int menuItemTypeId) {
        menuItem = MenuItem.findByName(name);
        if (menuItem == null) {
            MenuItemType menuItemType = MenuItemType.findById(menuItemTypeId);
            if (menuItemType == null) {
                return false;
            }
            menuItemType.setItemCount(menuItemType.getItemCount() + 1);
            menuItem = new MenuItem(name, price, quantity, isAvailable, 0, menuItemType);
            menuItem.create();
            return true;
        } else {
            return false;
        }
    }

    //maze stavajici polozku menu s danym Id
    public boolean deleteMenuItem(int menuItemId) {
        menuItem = MenuItem.findById(menuItemId);
        if (menuItem == null) {
            return false;
        } else {
            menuItem.getMenuItemType().setItemCount(menuItem.getMenuItemType().getItemCount() - 1);
            List<MenuMenuItem> list = MenuMenuItem.findByMenuItem(menuItemId);
            if (list != null) {
                Iterator it1 = list.iterator();
                MenuMenuItem mmi = null;
                while (it1.hasNext()) {
                    mmi = (MenuMenuItem) it1.next();
                    //mmi.delete();
                    mmi.setIsDeleted(1);
                }
            }
            List<UsedMaterial> list2 = UsedMaterial.findByMenuItem(menuItemId);
            if (list2 != null) {
                Iterator it2 = list2.iterator();
                UsedMaterial um = null;
                while (it2.hasNext()) {
                    um = (UsedMaterial) it2.next();
                    //um.delete();
                    um.setIsDeleted(1);
                }
            }
            //menuItem.delete();
            menuItem.setIsDeleted(1);
            return true;
        }
    }

    //aktualizuje stavajici polozku menu s danym Id
    public boolean updateMenuItem(int menuItemId, String name, double price, String quantity, int isAvailable, int menuItemTypeId) {
        menuItem = MenuItem.findById(menuItemId);
        if (menuItem == null) {
            return false;
        }
        MenuItem mi = MenuItem.findByName(name);
        if (mi != null && mi != menuItem) {
            return false;
        }
        MenuItemType menuItemType = MenuItemType.findById(menuItemTypeId);
        if (menuItemType == null) {
            return false;
        }
        menuItem.setName(name);
        menuItem.setPrice(price);
        menuItem.setQuantity(quantity);
        menuItem.setIsAvailable(isAvailable);

        MenuItemType mit = menuItem.getMenuItemType();
        mit.setItemCount(mit.getItemCount() - 1);

        menuItem.setMenuItemType(menuItemType);
        menuItemType.setItemCount(menuItemType.getItemCount() + 1);
        menuItem.update();
        return true;
    }

    //vraci polozku menu dle daneho Id
    public MenuItem getMenuItemById(int menuItemId) {
        return MenuItem.findById(menuItemId);
    }

    public MenuItem getMenuItemByName(String name) {
        return MenuItem.findByName(name);
    }

    public String[] getMenuItemNames() {
        List<MenuItem> list = MenuItem.findAll();
        if (list == null || list.isEmpty()) {
            return null;
        }
        String[] array = new String[list.size()];
        Iterator it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            menuItem = (MenuItem) it.next();
            array[i] = menuItem.getName();
            i++;
        }
        return array;
    }

    //vraci pole Objectu s polozkymi menu
    public Object[][] getMenuItems() {
        List<MenuItem> list = MenuItem.findAll();
        if (list == null || list.isEmpty()) {
            return null;
        }
        Object[][] array = new Object[list.size()][6];
        Iterator it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            menuItem = (MenuItem) it.next();
            array[i][0] = menuItem.getMenuItemId();
            array[i][1] = menuItem.getMenuItemType().getName();
            array[i][2] = menuItem.getName();
            array[i][3] = menuItem.getQuantity();
            array[i][4] = menuItem.getPrice();
            if (menuItem.getIsAvailable() == 1) {
                array[i][5] = "Dostupná";
            } else {
                array[i][5] = "Nedostupná";
            }
            i++;
        }
        return array;
    }

    public Object[][] getMenuItemsByMenu(int menuId) {
        List<MenuItem> list = MenuItem.findByMenu(menuId);
        if (list == null || list.isEmpty()) {
            return null;
        }
        Object[][] array = new Object[list.size()][6];
        Iterator it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            menuItem = (MenuItem) it.next();
            array[i][0] = menuItem.getMenuItemId();
            array[i][1] = menuItem.getMenuItemType().getName();
            array[i][2] = menuItem.getName();
            array[i][3] = menuItem.getQuantity();
            array[i][4] = menuItem.getPrice();
            if (menuItem.getIsAvailable() == 1) {
                array[i][5] = "Dostupná";
            } else {
                array[i][5] = "Nedostupná";
            }
            i++;
        }
        return array;
    }

    public List<MenuItem> getMenuItemsByMenuList(int menuId) {
        return MenuItem.findByMenu(menuId);
    }

    //vytvari dvojrozmerne pole typu Object pro metody getMenuItemsByAccount a getAllMenuItemsByAccount
    public Object[][] createMenuItemArray(List<Object[]> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        Object[][] array = new Object[list.size()][5];
        Iterator it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            Object[] pole = (Object[]) it.next();
            menuItem = (MenuItem) pole[0];

            array[i][0] = menuItem.getMenuItemId();
            array[i][1] = menuItem.getMenuItemType().getName();
            array[i][2] = menuItem.getName();
            array[i][3] = menuItem.getPrice();
            array[i][4] = pole[1];
            i++;
        }
        return array;
    }

    public Object[][] getMenuItemsByAccount(int accountId) {
        List<Object[]> list = MenuItem.findByAccount(accountId);
        return createMenuItemArray(list);
    }

    public Object[][] getAllMenuItemsByAccount(int accountId) {
        List<Object[]> list = MenuItem.findAllByAccount(accountId);
        return createMenuItemArray(list);
    }

    public Object[][] getMenuItemsByMenuItemType(int menuItemTypeId) {
        List<MenuItem> list = MenuItem.findByMenuItemType(menuItemTypeId);
        if (list == null || list.isEmpty()) {
            return null;
        }
        Object[][] array = new Object[list.size()][6];
        Iterator it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            menuItem = (MenuItem) it.next();
            array[i][0] = menuItem.getMenuItemId();
            array[i][1] = menuItem.getMenuItemType().getName();
            array[i][2] = menuItem.getName();
            array[i][3] = menuItem.getQuantity();
            array[i][4] = menuItem.getPrice();
            if (menuItem.getIsAvailable() == 1) {
                array[i][5] = "Dostupná";
            } else {
                array[i][5] = "Nedostupná";
            }
            i++;
        }
        return array;
    }

    public List<MenuItem> getMenuItemsByMenuItemTypeList(int menuItemTypeId) {
        return MenuItem.findByMenuItemType(menuItemTypeId);
    }
}
