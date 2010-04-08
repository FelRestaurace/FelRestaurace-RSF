/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service.controllers;

import hibernate.Menu;
import hibernate.MenuItem;
import hibernate.MenuMenuItem;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Jarda
 */
public class MenuMenuItemController {

    private static MenuMenuItemController instance = null;
    private MenuMenuItem menuMenuItem = null;

    private MenuMenuItemController(){
    }

    public static MenuMenuItemController getInstance(){
        if (instance == null){
            instance = new MenuMenuItemController();
        }
        return instance;
    }

    public boolean createMenuMenuItem(int menuId, int menuItemId){
        menuMenuItem = MenuMenuItem.findByMenuAndMenuItem(menuId, menuItemId);
        if (menuMenuItem == null){
            menuMenuItem = new MenuMenuItem();
            Menu menu = Menu.findById(menuId);
            if (menu == null){
                return false;
            }
            menuMenuItem.setMenu(menu);
            MenuItem menuItem = MenuItem.findById(menuItemId);
            if (menuItem == null){
                return false;
            }
            menuMenuItem.setMenuItem(menuItem);
            menuMenuItem.create();
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteMenuMenuItem(int menuId, int menuItemId){
        menuMenuItem = MenuMenuItem.findByMenuAndMenuItem(menuId, menuItemId);
        if (menuMenuItem == null){
            return false;
        }
        //menuMenuItem.delete();
        menuMenuItem.setIsDeleted(1);
        return true;
    }

    public boolean updateMenuMenuItem(int menuMenuItemId, int menuId, int menuItemId){
        menuMenuItem = MenuMenuItem.findById(menuMenuItemId);
        if (menuMenuItem == null){
            return false;
        }
        Menu menu = Menu.findById(menuId);
        if (menu == null){
            return false;
        }
        menuMenuItem.setMenu(menu);
        MenuItem menuItem = MenuItem.findById(menuItemId);
        if (menuItem == null){
            return false;
        }
        menuMenuItem.setMenuItem(menuItem);
        menuMenuItem.update();
        return true;
    }    
    
}
