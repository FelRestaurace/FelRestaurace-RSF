/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * UserForm.java
 *
 * Created on 5.4.2009, 11:31:27
 */

package gui;

import java.io.FileNotFoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import service.ConfigParser;
import service.ResultTableModel;
import service.ServiceFacade;
import service.Validator;

/**
 * Trida reprezentujici GUI formular pro editaci uzivatelu systemu.
 *
 * @author Jarda
 */
public class UserForm extends AbstractForm {

    private static String defualt_passwd = "";

    private JTable table = null;
    private UserRoleDialog userRoleDialog = null;
    private StatusBar statusBar = null;
    private int row = -1;
    private JFrame parent = null;

    /**
     * Konstruktor tridy UserForm.
     *
     * @param parent
     * @param bar
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    public UserForm(JFrame parent, StatusBar bar) throws FileNotFoundException, NotBoundException, RemoteException {
        this.parent = parent;
        this.statusBar = bar;               
        initComponents();        
        initTable();
        defualt_passwd = ServiceFacade.getInstance().getDefaultPasswd();
        refresh();
        clearFields();
    }

    /**
     * Metoda provadi aktualizaci tabulky.
     * Zaroven prenastavuje statusBar.
     *
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    protected void refresh() throws FileNotFoundException, NotBoundException, RemoteException {
        refreshTable();
        statusBar.setMessage("Tento formulář slouží k editaci a úpravě záznamů o zaměstnancích.");
    }                

    /**
     * Metoda kontrolujici spravnost vyplnenych udaju.
     *
     * @return Vraci index urcujici vstupni komponentu, ktera obsahuje
     * neplatny vstup. Pokud je vse vporadku tak navraci 0.
     */
    protected int isValidInput(){
        if (!Validator.isText(jTextFieldName)) return 1;
        if (!Validator.isText(jTextFieldSurname)) return 2;
        if (!Validator.isText(jTextFieldPIN)) return 3;
        if (!Validator.isDouble(jTextFieldPIN)) return 4;
        if (!Validator.isText(jTextFieldLogin)) return 5;        
        return 0;
    }

    /**
     * Metoda inicializujici tabulku.          
     */
    protected void initTable(){
        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e) {
                row = table.getSelectedRow();
                if (row == -1){
                    clearFields();
                    jButtonDelete.setEnabled(false);
                    jButtonRole.setEnabled(false);
                    jButtonInsert.setText("Vložit záznam o zaměstnanci");
                    statusBar.setMessage("Vložit nový záznam o zaměstnanci.");
                } else {
                    jTextFieldName.setText((String)table.getModel().getValueAt(row, 1));
                    jTextFieldSurname.setText((String)table.getModel().getValueAt(row, 2));
                    jTextFieldRoles.setText((String)table.getModel().getValueAt(row, 3));
                    jTextFieldPIN.setText((String)table.getModel().getValueAt(row, 4));
                    jTextFieldLogin.setText((String)table.getModel().getValueAt(row, 5));
                    jTextFieldOwnPasswd.setText((String)table.getModel().getValueAt(row, 6));
                    jButtonDelete.setEnabled(true);
                    jButtonRole.setEnabled(true);
                    jButtonInsert.setText("Změnit záznam o zaměstnanci");
                    statusBar.setMessage("Zaměstnanec ID: "+table.getValueAt(row, 0)+", \""+table.getValueAt(row, 2)+", "+table.getModel().getValueAt(row, 1)+"\"");
                }
            }
        });
        //refreshTable();
    }

    /**
     * Metoda aktualizuje tabulku.
     *
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    protected void refreshTable() throws FileNotFoundException, NotBoundException, RemoteException {
        ResultTableModel rtm = new ResultTableModel();        
        Object[][] users = ServiceFacade.getInstance().getUsers();
        rtm.setColumnData(ResultTableModel.namesUser);
        rtm.setTableData(users);
        table.setModel(rtm);        
    }

    /**
     * Metoda navraci tabulku (instanci tridy JTable).
     *
     * @return instance tridy JTable
     */
    public JTable getTable() {
        return table;
    }

    /**
     * Metoda cisti vsechny vstupni formulare, formular pro datum nastavuje na
     * aktualni datum a u comboBoxu nastavuje aktualni vybranou polozku na
     * prvni polozku daneho comboBoxu.
     */
    protected void clearFields(){
        Validator.clearTextField(jTextFieldName);
        Validator.clearTextField(jTextFieldSurname);
        Validator.clearTextField(jTextFieldPIN);
        Validator.clearTextField(jTextFieldLogin);
        jTextFieldPasswd.setText(defualt_passwd);
        Validator.clearTextField(jTextFieldRoles);
    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelName = new javax.swing.JLabel();
        jLabelSurname = new javax.swing.JLabel();
        jLabelPIN = new javax.swing.JLabel();
        jLabelLogin = new javax.swing.JLabel();
        jLabelPasswd = new javax.swing.JLabel();
        jTextFieldLogin = new javax.swing.JTextField();
        jTextFieldName = new javax.swing.JTextField();
        jTextFieldSurname = new javax.swing.JTextField();
        jTextFieldPIN = new javax.swing.JTextField();
        jButtonInsert = new javax.swing.JButton();
        jButtonClear = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jLabelRoles = new javax.swing.JLabel();
        jTextFieldRoles = new javax.swing.JTextField();
        jButtonRole = new javax.swing.JButton();
        jTextFieldPasswd = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldOwnPasswd = new javax.swing.JTextField();

        jLabelName.setText("Jméno");

        jLabelSurname.setText("Příjmení");

        jLabelPIN.setText("PIN");

        jLabelLogin.setText("Uživatelské jméno");

        jLabelPasswd.setText("Výchozí heslo");
        jLabelPasswd.setToolTipText("Výchozí heslo, které se uživateli nastaví\n při vytváření jeho uživatelského záznamu.");

        jButtonInsert.setText("Vložit záznam o zaměstnanci");
        jButtonInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertActionPerformed(evt);
            }
        });

        jButtonClear.setText("Vyčistit formulář");
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });

        jButtonDelete.setText("Smazat záznam");
        jButtonDelete.setEnabled(false);
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jLabelRoles.setText("Uživatelské role");

        jTextFieldRoles.setEditable(false);

        jButtonRole.setText("Upravit uživatelské role");
        jButtonRole.setEnabled(false);
        jButtonRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRoleActionPerformed(evt);
            }
        });

        jTextFieldPasswd.setEditable(false);
        jTextFieldPasswd.setText("1234");
        jTextFieldPasswd.setToolTipText("Výchozí heslo, které se uživateli nastaví  při vytváření jeho uživatelského záznamu.");

        jLabel1.setText("Vlastní heslo");
        jLabel1.setToolTipText("Změnil si již daný uživatel výchozí heslo na svoje tajné heslo?");

        jTextFieldOwnPasswd.setEditable(false);
        jTextFieldOwnPasswd.setToolTipText("Změnil si již daný uživatel výchozí heslo na svoje tajné heslo?");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelLogin)
                    .addComponent(jLabelSurname)
                    .addComponent(jLabelPIN)
                    .addComponent(jLabelPasswd)
                    .addComponent(jLabelName)
                    .addComponent(jLabelRoles))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldPasswd, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldRoles, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addComponent(jTextFieldSurname, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addComponent(jTextFieldPIN, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addComponent(jTextFieldLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldOwnPasswd))
                    .addComponent(jButtonDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                    .addComponent(jButtonClear, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                    .addComponent(jButtonInsert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonRole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelName)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonInsert))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSurname)
                    .addComponent(jTextFieldSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonClear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPIN)
                    .addComponent(jTextFieldPIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLogin)
                    .addComponent(jTextFieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPasswd)
                    .addComponent(jTextFieldPasswd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldOwnPasswd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelRoles)
                    .addComponent(jTextFieldRoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRole))
                .addGap(39, 39, 39))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
        clearFields();
}//GEN-LAST:event_jButtonClearActionPerformed

    private void jButtonInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertActionPerformed
        int i = isValidInput();
        if (i == 0){
            String name = jTextFieldName.getText();
            String surname = jTextFieldSurname.getText();
            String pin = jTextFieldPIN.getText();
            String username = jTextFieldLogin.getText();
            String passwd = jTextFieldPasswd.getText();
            try {
                boolean isOK;
                if (row == -1){
                    isOK = ServiceFacade.getInstance().createUser(name, surname, pin, username, passwd);
                    if (!isOK){
                        JOptionPane.showMessageDialog(this, "Záznam nemohl být uložen, protože záznam se stejným uživatelským jménem nebo PID číslem je již uložen.", "Zaměstnanci", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    int userId = (Integer)table.getModel().getValueAt(row, 0);
                    isOK = ServiceFacade.getInstance().updateUser(userId, name, surname, pin, username);
                    if (!isOK){
                        JOptionPane.showMessageDialog(this, "Záznam nemohl být aktualizován, protože záznam se stejným uživatelským jménem nebo PID číslem je již uložen.", "Zaměstnanci", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                refreshTable();
            } catch (FileNotFoundException fnfe){
                JOptionPane.showMessageDialog(this, "Konfigurační soubor \""+ConfigParser.getConfigFile()+"\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex){
                JOptionPane.showMessageDialog(this, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
            }
            clearFields();
        } else if (i == 1){
            JOptionPane.showMessageDialog(this, "Musí být uvedeno křestní jméno nového uživatele.", "Nový uživatel", JOptionPane.INFORMATION_MESSAGE);
        } else if (i == 2){
            JOptionPane.showMessageDialog(this, "Musí být uvedeno příjmení nového uživatele.", "Nový uživatel", JOptionPane.INFORMATION_MESSAGE);
        } else if (i == 3){
            JOptionPane.showMessageDialog(this, "Musí být uvedeno personální identifikační číslo (PIN) nového uživatele.", "Nový uživatel", JOptionPane.INFORMATION_MESSAGE);
        } else if (i == 4){
            JOptionPane.showMessageDialog(this, "Personální identifikační číslo (PIN) nového uživatele musí být číslo.", "Nový uživatel", JOptionPane.INFORMATION_MESSAGE);
        } else if (i == 5){
            JOptionPane.showMessageDialog(this, "Musí být uvedeno uživatelské jméno nového uživatele.", "Nový uživatel", JOptionPane.INFORMATION_MESSAGE);
        } 
    }//GEN-LAST:event_jButtonInsertActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        row = table.getSelectedRow();
        int userId = (Integer)table.getModel().getValueAt(row, 0);
        try {
            if (userId == MainFrame.getLoggedUser().getUserId()){
                JOptionPane.showMessageDialog(this, "Není možné odstranit záznam právě přihlášeného uživatele.", "Záznam nebyl smazán", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            boolean ok = ServiceFacade.getInstance().deleteUser(userId);
            if (!ok){
                JOptionPane.showMessageDialog(this, "Záznam nebyl smazán.", "Záznam nebyl smazán", JOptionPane.INFORMATION_MESSAGE);
            }
            refreshTable();
        } catch (FileNotFoundException fnfe){
            JOptionPane.showMessageDialog(null, "Konfigurační soubor \""+ConfigParser.getConfigFile()+"\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex){
            JOptionPane.showMessageDialog(this, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRoleActionPerformed
        int userId = (Integer)table.getModel().getValueAt(row, 0);
        try {
            userRoleDialog = new UserRoleDialog(parent, true, userId);
            userRoleDialog.setVisible(true);
            refreshTable();
        } catch (FileNotFoundException fnfe){
            JOptionPane.showMessageDialog(null, "Konfigurační soubor \""+ConfigParser.getConfigFile()+"\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex){
            JOptionPane.showMessageDialog(this, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jButtonRoleActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonInsert;
    private javax.swing.JButton jButtonRole;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelLogin;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelPIN;
    private javax.swing.JLabel jLabelPasswd;
    private javax.swing.JLabel jLabelRoles;
    private javax.swing.JLabel jLabelSurname;
    private javax.swing.JTextField jTextFieldLogin;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldOwnPasswd;
    private javax.swing.JTextField jTextFieldPIN;
    private javax.swing.JTextField jTextFieldPasswd;
    private javax.swing.JTextField jTextFieldRoles;
    private javax.swing.JTextField jTextFieldSurname;
    // End of variables declaration//GEN-END:variables

}
