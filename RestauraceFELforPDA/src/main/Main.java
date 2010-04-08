/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import gui.LogingDialog;
import gui.NavFrame;
import javax.swing.JFrame;
/**
 *
 * @author Supervisor
 */
public class Main {

    public static void main(String[] args) {
        LogingDialog ld = new LogingDialog(new JFrame(), true);
        ld.pack();
        ld.setVisible(true);
        new NavFrame(ld.getLoggedUser()).setVisible(true);
    }

   
}
