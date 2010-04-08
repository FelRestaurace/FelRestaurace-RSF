/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * OpenAccount.java
 *
 * Created on 1.12.2009, 15:03:30
 */
package gui;

import hibernate.Account;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import service.ServiceFacade;

/**
 *
 * @author Tomáš
 */
public class OpenAccount extends javax.swing.JPanel {

    /** Creates new form OpenAccount */
    public OpenAccount() {
        initComponents();
        loadAccounts();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MenuPanel = new javax.swing.JPanel();

        MenuPanel.setLayout(new java.awt.GridLayout(5, 1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(410, 410, 410))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MenuPanel;
    // End of variables declaration//GEN-END:variables

    public void loadAccounts() {
        try {
            try {
                String[] acc = ServiceFacade.getInstance().getAccountNames();
                for (int a = 0; a < acc.length; a++) {
                    JButton butt = new JButton();
                    butt.setText(acc[a]);
                    butt.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            openAcc(((JButton)e.getSource()).getText());
                        }
                    });
                    this.MenuPanel.add(butt);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(OpenAccount.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
                Logger.getLogger(OpenAccount.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(OpenAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void openAcc(String accName)
    {
        try {            
            Account acc = ServiceFacade.getInstance().getAccountByName(accName);
            if(acc==null)return;
            MainJFrame.openBill = new Bill();
            Bill bill = MainJFrame.openBill;
            String billName="";
            String tableName="";
            String userName="";
            String sleva="";
            String status="";
            if(acc.getName()!=null)billName=acc.getName();
            if(acc.getTable()!=null)tableName=acc.getTable().getTableId()+"";
            if(acc.getUser()!=null)userName=acc.getUser().getUsername();
            if(acc.getDiscountType()!=null)sleva=acc.getDiscountType().getName();
            if(acc.getAccountStatusType()!=null)status=acc.getAccountStatusType().getName();

            bill.setHeader(billName, tableName, userName, sleva, status);
            Object[][] orders = ServiceFacade.getInstance().getAllMenuItemsByAccount(acc.getAccountId());
            bill.fillOrders(orders);

            MainJFrame.PanelListku.add(bill);
            MainJFrame.PanelListku.setVisible(true);
            MainJFrame.PanelVyberu.removeAll();
            MainJFrame.NewAcc.setEnabled(false);
            MainJFrame.OpenAcc.setEnabled(false);
            MainJFrame.CloseAcc.setEnabled(true);
            MainJFrame.PayAcc.setEnabled(true);
            MainJFrame.PrintBill.setEnabled(true);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(OpenAccount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(OpenAccount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(OpenAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}