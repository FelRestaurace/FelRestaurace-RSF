/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 *
 * @author RealNeo
 */
public class ToolBar extends JToolBar {
    
    public JButton createAccount = new JButton(new ImageIcon("images/new-bill-normal.png"));
    public JButton accountList = new JButton(new ImageIcon("images/new-order-normal.png"));
    public JButton payAccount = new JButton(new ImageIcon("images/count-bill-normal.png"));
    public JButton createOrder = new JButton(new ImageIcon("images/browse-bills.png"));
    public JButton moveMenuItems = new JButton(new ImageIcon("images/move-menu-items.png"));
    public JButton createCustomer = new JButton(new ImageIcon("images/new-client-normal.png"));
    public JButton settings = new JButton(new ImageIcon("images/settings-normal.png"));
    public JButton close = new JButton(new ImageIcon("images/exit.png"));
    private JPanel panel = new JPanel();

    public ToolBar() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
    this.setLayout(new BorderLayout());
        createAccount.setFocusPainted(false);
        accountList.setFocusPainted(false);
        payAccount.setFocusPainted(false);
        createOrder.setFocusPainted(false);
        moveMenuItems.setFocusPainted(false);
        createCustomer.setFocusPainted(false);
        settings.setFocusPainted(false);
        close.setFocusPainted(false);

        panel.add(createAccount);
        //panel.addSeparator();
        panel.add(accountList);
        //this.addSeparator();
        panel.add(payAccount);
       // this.addSeparator();
        panel.add(createOrder);
       // this.addSeparator();
        panel.add(moveMenuItems);
       // this.addSeparator();
        panel.add(createCustomer);
       // this.addSeparator();
        panel.add(settings);
      //  this.addSeparator();
        this.add(panel,BorderLayout.WEST);
        this.add(close, BorderLayout.EAST);
    }

    public void setAllDisabled() {
        createAccount.setEnabled(false);
        accountList.setEnabled(false);
        payAccount.setEnabled(false);
        createOrder.setEnabled(false);
        moveMenuItems.setEnabled(false);
        createCustomer.setEnabled(false);
        settings.setEnabled(false);
      //  close.setEnabled(false);
    }

       public void setAllEnabled() {
        createAccount.setEnabled(true);
        accountList.setEnabled(true);
        payAccount.setEnabled(true);
        createOrder.setEnabled(true);
        moveMenuItems.setEnabled(true);
        createCustomer.setEnabled(true);
        settings.setEnabled(true);
      //  close.setEnabled(true);
    }
}
