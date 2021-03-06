/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NavFrame.java
 *
 * Created on 29.11.2009, 18:00:02
 */

package gui;

import hibernate.User;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Main;

/**
 *
 * @author Supervisor
 */
public class NavFrame extends javax.swing.JFrame {

    private User loggedUser;

    /** Creates new form NavFrame */
    public NavFrame(User loggedUser) {
        this.loggedUser = loggedUser;
        initComponents();
        welcomeLabel.setText("PDA modul, prihlasen : "+ loggedUser.getUsername());

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        welcomeLabel = new javax.swing.JLabel();
        orderButton = new javax.swing.JButton();
        accountsButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        settingsButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(240, 320));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        welcomeLabel.setText("jLabel1");
        getContentPane().add(welcomeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        orderButton.setText("Objednavky");
        orderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderButtonActionPerformed(evt);
            }
        });
        getContentPane().add(orderButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 240, -1));

        accountsButton.setText("Sprava uctu");
        accountsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountsButtonActionPerformed(evt);
            }
        });
        getContentPane().add(accountsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 240, -1));

        exitButton.setText("Konec");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(exitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 70, -1));

        settingsButton.setText("Nastaveni");
        settingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsButtonActionPerformed(evt);
            }
        });
        getContentPane().add(settingsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 240, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void accountsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountsButtonActionPerformed
        this.dispose();
        new AccountFrame(loggedUser).setVisible(true);
    }//GEN-LAST:event_accountsButtonActionPerformed

    private void orderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderButtonActionPerformed
        this.dispose();
        new OrderFrame(loggedUser).setVisible(true);
    }//GEN-LAST:event_orderButtonActionPerformed

    private void settingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsButtonActionPerformed
        this.dispose();
        try {
            new SettingsFrame(loggedUser).setVisible(true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NavFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_settingsButtonActionPerformed

    /**
    * @param args the command line arguments
    */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton accountsButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton orderButton;
    private javax.swing.JButton settingsButton;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables

}
