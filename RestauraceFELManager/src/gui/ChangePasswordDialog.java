/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ChangePasswordDialog.java
 *
 * Created on 28.4.2009, 15:55:19
 */

package gui;

import hibernate.User;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import service.ConfigParser;
import service.ServiceFacade;

/**
 * Trida reprezentujici dialog pro zmena uzivatelskeho hesla.
 *
 * @author Jarda
 */
public class ChangePasswordDialog extends javax.swing.JDialog {

    private JFrame parent = null;
    private int userId;
    private String oldPasswd = "";
    private String newPasswd = "";
    private String validPasswd = "";

    /**
     * Konstruktor tridy ChangePasswordDialog.
     *
     * @param parent instance tridy JFrame jenz vytvorila tento dialog
     * @param modal urcuje, zda bude dialog modalni
     * @param u uzivatel, kteremu se bude menit heslo
     */
    public ChangePasswordDialog(JFrame parent, boolean modal, int userId) throws NotBoundException, RemoteException, FileNotFoundException {
        super(parent, modal);
        super.setTitle("Změna hesla");
        this.parent = parent;
        this.userId = userId;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int y = (int) ((dim.getHeight() - 182) / 2);
        int x = (int) ((dim.getWidth() - 340) / 2);
        setLocation(x, y);
        setUserTextField();
    }   

    /**
     * Nastavuje do textFieldu jmeno a prijmeni daneho uzivatele
     */
    private void setUserTextField() throws NotBoundException, RemoteException, FileNotFoundException {
        User user = null;        
        user = ServiceFacade.getInstance().getUserById(userId);
        jTextFieldUser.setText(user.getLastName()+", "+user.getFirstName());
        jTextFieldUsername.setText(user.getUsername());
    }

    /**
     * Validuje vstupy zapsane do textFieldu. Kontroluje, zda stare heslo odpovida
     * stavajicimu heslu, kontroluje zda nove heslo odpovida heslu zadanemu pro potvrzeni.
     *
     * @return true, pokud stavajici heslo = heslo zadane jako stare a
     * nove heslo = potvrzeni noveho hesla; jinak false
     */
    private int isValidInput() {
        oldPasswd = String.copyValueOf(jTextFieldOldPasswd.getPassword());
        newPasswd = String.copyValueOf(jTextFieldNewPasswd.getPassword());
        validPasswd = String.copyValueOf(jTextFieldValidPasswd.getPassword());

        if (oldPasswd.isEmpty()){
            return 1;
        }
        if (newPasswd.isEmpty() || newPasswd.length() < 4){
            return 2;
        }                
        if (!newPasswd.equals(validPasswd)){
            return 3;
        }
        try {
            if (!ServiceFacade.getInstance().isValidOldPasswd(userId, oldPasswd)){
                return 4;
            }
        } catch (FileNotFoundException fnfe){
            JOptionPane.showMessageDialog(this, "Konfigurační soubor \""+ConfigParser.getConfigFile()+"\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex){
            JOptionPane.showMessageDialog(this, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelUsername = new javax.swing.JLabel();
        jLabelOldPasswd = new javax.swing.JLabel();
        jLabelNewPasswd = new javax.swing.JLabel();
        jLabelValidPasswd = new javax.swing.JLabel();
        jTextFieldUsername = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabelUser = new javax.swing.JLabel();
        jTextFieldUser = new javax.swing.JTextField();
        jTextFieldOldPasswd = new javax.swing.JPasswordField();
        jTextFieldNewPasswd = new javax.swing.JPasswordField();
        jTextFieldValidPasswd = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabelUsername.setText("Uživatelské jméno");

        jLabelOldPasswd.setText("Staré heslo");

        jLabelNewPasswd.setText("Nové heslo");

        jLabelValidPasswd.setText("Ověření hesla");

        jTextFieldUsername.setEditable(false);

        jButton1.setText("Změnit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Zpět");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabelUser.setText("Uživatel");

        jTextFieldUser.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelValidPasswd)
                            .addComponent(jLabelNewPasswd)
                            .addComponent(jLabelOldPasswd)
                            .addComponent(jLabelUsername))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                            .addComponent(jTextFieldUser, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                            .addComponent(jTextFieldOldPasswd, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                            .addComponent(jTextFieldNewPasswd, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                            .addComponent(jTextFieldValidPasswd, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelUser)
                        .addGap(202, 202, 202)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUser)
                    .addComponent(jTextFieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelUsername)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelOldPasswd)
                    .addComponent(jTextFieldOldPasswd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNewPasswd)
                    .addComponent(jTextFieldNewPasswd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelValidPasswd)
                    .addComponent(jTextFieldValidPasswd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int i = isValidInput();
        if (i == 0){                       
            boolean isOK = false;
            try {
                isOK = ServiceFacade.getInstance().updateUserPassword(userId, newPasswd);
            } catch (FileNotFoundException fnfe){
                JOptionPane.showMessageDialog(this, "Konfigurační soubor \""+ConfigParser.getConfigFile()+"\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex){
                JOptionPane.showMessageDialog(this, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
            }
            if (isOK){
                JOptionPane.showMessageDialog(this, "Změna hesla proběhla úspěšně.", "Změna hesla", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }            
        } else if (i == 1){
            JOptionPane.showMessageDialog(this, "Musí být uvedeno stávající heslo.", "Změna hesla", JOptionPane.INFORMATION_MESSAGE);
        } else if (i == 2){
            JOptionPane.showMessageDialog(this, "Musí být uvedeno nové heslo, které má minimální délku 4 znaky", "Změna hesla", JOptionPane.INFORMATION_MESSAGE);
        } else if (i == 3){
            JOptionPane.showMessageDialog(this, "Nové heslo se musí shodovat s heslem v kolonce \"Ověření hesla\".", "Změna hesla", JOptionPane.INFORMATION_MESSAGE);
        } else if (i == 4){
            JOptionPane.showMessageDialog(this, "Uvedeno chýbné stávající heslo uživatele.", "Změna hesla", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabelNewPasswd;
    private javax.swing.JLabel jLabelOldPasswd;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JLabel jLabelUsername;
    private javax.swing.JLabel jLabelValidPasswd;
    private javax.swing.JPasswordField jTextFieldNewPasswd;
    private javax.swing.JPasswordField jTextFieldOldPasswd;
    private javax.swing.JTextField jTextFieldUser;
    private javax.swing.JTextField jTextFieldUsername;
    private javax.swing.JPasswordField jTextFieldValidPasswd;
    // End of variables declaration//GEN-END:variables

}