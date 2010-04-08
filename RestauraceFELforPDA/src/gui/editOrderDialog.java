package gui;

import hibernate.User;
import java.io.FileNotFoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.ServiceFacade;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * editOrderDialog.java
 *
 * Created on 30.11.2009, 11:45:11
 */

/**
 *
 * @author Supervisor
 */
public class editOrderDialog extends AbstractDialog {
    private User loggedUser;

    /** Creates new form editOrderDialog */
    public editOrderDialog(javax.swing.JFrame parent, boolean modal, User loggedUser) throws RemoteException, NotBoundException, FileNotFoundException {
        super(parent, modal);
        this.loggedUser = loggedUser;
        initComponents();
        jLabel1.setText("Editace objednavek");
        jLabel2.setText("Ucet :");
        jLabel3.setText("Objednavka :");
        jLabel4.setText("Polozka :");
        exitButton.setText("Zpet");
        removeButton.setText("Odstranit");
        refresh();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        exitButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        accountBox = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        orderBox = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        itemField = new javax.swing.JTextField();
        removeButton = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(240, 320));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exitButton.setText("jButton1");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(exitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 290, -1, -1));

        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, -1));

        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        accountBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        accountBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                accountBoxItemStateChanged(evt);
            }
        });
        getContentPane().add(accountBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 110, -1));

        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        orderBox.setFont(new java.awt.Font("Tahoma", 0, 9));
        orderBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        orderBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                orderBoxItemStateChanged(evt);
            }
        });
        getContentPane().add(orderBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 110, -1));

        jLabel4.setText("jLabel4");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        itemField.setEditable(false);
        itemField.setText("jTextField1");
        getContentPane().add(itemField, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 110, -1));

        removeButton.setText("jButton1");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });
        getContentPane().add(removeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_exitButtonActionPerformed

    private void accountBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_accountBoxItemStateChanged
        try {
            int position = accountBox.getSelectedIndex();
            if (position == -1) return;
            Object[][] accounts = ServiceFacade.getInstance().getAccounts();
            int accountId = (Integer) accounts[position][0];
            Object[][] orders = ServiceFacade.getInstance().getOrdersByAccount(accountId);
            if (orders == null) {
                String[] fill = {"----"};
                setComboBoxModel(fill, orderBox);
                itemField.setText("Zadna objednavka");
                return;
            }
            String [] orderDates = new String[orders.length];
            for (int i = 0; i < orders.length; i++){
                orderDates[i] = orders[i][2].toString();
            }
            setComboBoxModel(orderDates, orderBox);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(editOrderDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(editOrderDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException rme) {}
    }//GEN-LAST:event_accountBoxItemStateChanged

    private void orderBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_orderBoxItemStateChanged
        try {
            int position = accountBox.getSelectedIndex();
            if (position == -1) return;
            Object[][] accounts = ServiceFacade.getInstance().getAccounts();
            int accId = (Integer) accounts[position][0];
            Object[][] orders = ServiceFacade.getInstance().getOrdersByAccount(accId);
            position = orderBox.getSelectedIndex();
            if (orders == null || position == -1 ) return;
            
            int itemId = (Integer) orders[position][0];
            Object[][] orderMenuItems = ServiceFacade.getInstance().getOrderMenuItems();
            String item = orderMenuItems[itemId-1][1].toString();
            itemField.setText(item);
            

        } catch (FileNotFoundException ex) {
            Logger.getLogger(editOrderDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(editOrderDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException rme) {}
    }//GEN-LAST:event_orderBoxItemStateChanged

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        try {
            int position = accountBox.getSelectedIndex();
            if (position == -1) return;
            Object[][] accounts = ServiceFacade.getInstance().getAccounts();
            int accId = (Integer) accounts[position][0];
            Object[][] orders = ServiceFacade.getInstance().getOrdersByAccount(accId);
            position = orderBox.getSelectedIndex();
            if (position == -1 ) return;
            int itemId = (Integer) orders[position][0];
            Object[][] orderMenuItems = ServiceFacade.getInstance().getOrderMenuItems();
            int item = (Integer) orderMenuItems[itemId-1][0];
            ServiceFacade.getInstance().deleteOrder(item);
            refresh();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(editOrderDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(editOrderDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException rme) {}
    }//GEN-LAST:event_removeButtonActionPerformed



    @Override
    protected int isValidInput() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void initTable() throws RemoteException, NotBoundException, FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void refresh() throws RemoteException, NotBoundException, FileNotFoundException {
        String [] accountNames = ServiceFacade.getInstance().getAccountNames();
        Object [][] accounts = ServiceFacade.getInstance().getAccounts();
        int firstId = (Integer) accounts[0][0];
        setComboBoxModel(accountNames, accountBox);
        Object [][] orders = ServiceFacade.getInstance().getOrdersByAccount(firstId);
        String [] orderDates = new String[orders.length];
        for (int i = 0; i < orders.length; i++){
            orderDates[i] = orders[i][2].toString();
            }
        setComboBoxModel(orderDates, orderBox);
        int itemId = (Integer) orders[0][0];
        Object[][] orderMenuItems = ServiceFacade.getInstance().getOrderMenuItems();
        String item = orderMenuItems[itemId][1].toString();
        itemField.setText(item);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox accountBox;
    private javax.swing.JButton exitButton;
    private javax.swing.JTextField itemField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JComboBox orderBox;
    private javax.swing.JButton removeButton;
    // End of variables declaration//GEN-END:variables

}
