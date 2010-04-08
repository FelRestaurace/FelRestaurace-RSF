/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * settingsForm.java
 *
 * Created on 29.11.2009, 18:21:18
 */

package gui;

import hibernate.User;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
import service.ConfigParser;

/**
 *
 * @author Supervisor
 */
public class SettingsFrame extends javax.swing.JFrame {
    private User loggedUser;

    /** Creates new form settingsForm */
    @SuppressWarnings("static-access")
    public SettingsFrame(User loggedUser) throws FileNotFoundException {
        this.loggedUser = loggedUser;
        initComponents();
        ConfigParser config = new ConfigParser();
        primaryIPField.setText(config.getPrimaryServerIP());
        secondaryIPField.setText(config.getSecondaryServerIP());
        currencyField.setText(config.getMoney());
        configField.setText(config.getConfigFile());

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        primaryIPField = new javax.swing.JTextField();
        secondaryIPField = new javax.swing.JTextField();
        configField = new javax.swing.JTextField();
        currencyField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(240, 320));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backButton.setText("Zpet");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        getContentPane().add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 70, -1));

        saveButton.setText("Ulozit");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        getContentPane().add(saveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 70, -1));

        jLabel1.setText("Nastaveni PDA");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        jLabel2.setText("Primarni IP :");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel3.setText("Sekundarni IP :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel4.setText("Config :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel5.setText("Mena :");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));
        getContentPane().add(primaryIPField, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 130, -1));

        secondaryIPField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                secondaryIPFieldActionPerformed(evt);
            }
        });
        getContentPane().add(secondaryIPField, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 130, -1));

        configField.setEditable(false);
        configField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configFieldActionPerformed(evt);
            }
        });
        getContentPane().add(configField, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 130, -1));
        getContentPane().add(currencyField, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 130, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        this.dispose();
        new NavFrame(loggedUser).setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed

    private void configFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_configFieldActionPerformed

    private void secondaryIPFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_secondaryIPFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_secondaryIPFieldActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        try {
            ConfigParser config = new ConfigParser();
            config.setPrimaryServerIP(primaryIPField.getText());
            config.setSecondaryServerIP(secondaryIPField.getText());
            config.setMoney(currencyField.getText());
        } catch (FileNotFoundException ex){
             JOptionPane.showMessageDialog(this, "Konfigurační soubor\n \""+ConfigParser.getConfigFile()+"\"\n nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {

        }
        this.dispose();
        new NavFrame(loggedUser).setVisible(true);
    }//GEN-LAST:event_saveButtonActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JTextField configField;
    private javax.swing.JTextField currencyField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField primaryIPField;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField secondaryIPField;
    // End of variables declaration//GEN-END:variables

}