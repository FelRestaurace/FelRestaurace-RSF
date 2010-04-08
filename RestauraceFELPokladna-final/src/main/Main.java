/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import gui.MainFrame;
import gui.MainJFrame;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.UIManager;

import org.jvnet.substance.skin.SubstanceOfficeSilver2007LookAndFeel;

/**
 * Hlavni trida modulu Administrace.
 *
 * @author Jarda
 */
public class Main {

    public static void main(String[] args) {

        JFrame.setDefaultLookAndFeelDecorated(true);
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    UIManager.setLookAndFeel(new SubstanceOfficeSilver2007LookAndFeel());
                    UIManager.put("ScrollBar.width", 32);
                } catch (Exception e) {
                    System.out.println("Substance Raven Graphite failed to initialize");
                }
                //new NewApplication().setVisible(true);
                Toolkit tk = Toolkit.getDefaultToolkit();
                //Cursor invisible = tk.createCustomCursor(tk.createImage(""), new Point(), null);

                MainFrame mainFrame = new MainFrame();
               // mainFrame.setCursor(invisible);
                mainFrame.setVisible(true);
//                new MainJFrame().setVisible(true);
            }
        });

    }
}
