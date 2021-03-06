/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CreateCustomerForm.java
 *
 * Created on 5.4.2009, 11:31:27
 */

package gui;

import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.ImageIcon;
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
 * Trida reprezentujici GUI formular pro vytvareni a editaci zakazniku.
 *
 * @author Tomas Hnizdil
 */
public class CreateCustomerForm extends AbstractForm {

    private JTable table = null;
    private StatusBar statusBar = null;
    private int row = -1;
    private JFrame parent = null;
    ImageIcon image = new ImageIcon("images/backgroundpane-bg.jpg");
    /**
     * Konstruktor tridy CreateCustomerForm.
     *
     * @param parent
     * @param bar
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    public CreateCustomerForm(JFrame parent, StatusBar bar) throws FileNotFoundException, NotBoundException, RemoteException {
        this.parent = parent;
        this.statusBar = bar;               
        initComponents();        
        initTable();
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
        statusBar.setMessage("Tento formulář slouží k editaci a úpravě záznamů o zákaznících.");
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
                    jButtonInsert.setText("Vložit záznam o zaměstnanci");
                    statusBar.setMessage("Vložit nový záznam o zákazníkovi.");
                } else {
                    jTextFieldName.setText((String)table.getModel().getValueAt(row, 1));
                    jTextFieldSurname.setText((String)table.getModel().getValueAt(row, 2));
                    jTextFieldPIN.setText((String)table.getModel().getValueAt(row, 4));
                    jTextFieldLogin.setText((String)table.getModel().getValueAt(row, 5));
                    jButtonDelete.setEnabled(true);
                    jButtonInsert.setText("Změnit záznam o zákazníkovi");
                    statusBar.setMessage("Zaměstnanec ID: "+table.getValueAt(row, 0)+", \""+table.getValueAt(row, 2)+", "+table.getModel().getValueAt(row, 1)+"\"");
                }
            }
        });
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
        jTextFieldLogin = new javax.swing.JTextField();
        jTextFieldName = new javax.swing.JTextField();
        jTextFieldSurname = new javax.swing.JTextField();
        jTextFieldPIN = new javax.swing.JTextField();
        jButtonInsert = new javax.swing.JButton();
        jButtonClear = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jLabelTitle = new javax.swing.JLabel();

        jLabelName.setText("Jméno");
        jLabelName.setOpaque(true);

        jLabelSurname.setText("Příjmení");
        jLabelSurname.setOpaque(true);

        jLabelPIN.setText("PIN");
        jLabelPIN.setOpaque(true);

        jLabelLogin.setText("Uživatelské jméno");
        jLabelLogin.setOpaque(true);

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

        jLabelTitle.setBackground(new java.awt.Color(255, 255, 255));
        jLabelTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitle.setText("Nový zaměstnanec");
        jLabelTitle.setOpaque(true);

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
                    .addComponent(jLabelName))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addComponent(jTextFieldSurname, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addComponent(jTextFieldPIN, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addComponent(jTextFieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                    .addComponent(jButtonClear, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                    .addComponent(jButtonInsert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(35, Short.MAX_VALUE))
            .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelName)
                            .addComponent(jButtonInsert))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelSurname)
                            .addComponent(jButtonClear))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelPIN)
                            .addComponent(jButtonDelete))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelLogin))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldPIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(110, 110, 110))
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
            try {
                boolean isOK;
                if (row == -1){
                    isOK = ServiceFacade.getInstance().createUser(name, surname, pin, username);
                    if (!isOK){
                        JOptionPane.showMessageDialog(this, "Záznam nemohl být uložen, protože záznam se stejným uživatelským jménem nebo PID číslem je již uložen.", "Zaměstnanci", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    int userId = ServiceFacade.getInstance().getUserByUsername(username).getUserId();
                    int roleId = ServiceFacade.getInstance().getRoleByName("customer").getRoleId();
                    isOK = ServiceFacade.getInstance().createUserRole(userId, roleId);
                    if (!isOK){
                        JOptionPane.showMessageDialog(this, "Záznam nemohl být uložen, protože záznam se stejným uživatelským jménem nebo PID číslem je již uložen.", "Zaměstnanci", JOptionPane.INFORMATION_MESSAGE);
                        return;
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
            JOptionPane.showMessageDialog(this, "Musí být uvedeno křestní jméno nového zákazníka.", "Nový zákazník", JOptionPane.INFORMATION_MESSAGE);
        } else if (i == 2){
            JOptionPane.showMessageDialog(this, "Musí být uvedeno příjmení nového zákazníka.", "Nový zákazník", JOptionPane.INFORMATION_MESSAGE);
        } else if (i == 3){
            JOptionPane.showMessageDialog(this, "Musí být uvedeno personální identifikační číslo (PIN) nového zákazníka.", "Nový zákazník", JOptionPane.INFORMATION_MESSAGE);
        } else if (i == 4){
            JOptionPane.showMessageDialog(this, "Personální identifikační číslo (PIN) nového zákazníka musí být číslo.", "Nový zákazník", JOptionPane.INFORMATION_MESSAGE);
        } else if (i == 5){
            JOptionPane.showMessageDialog(this, "Musí být uvedeno uživatelské jméno nového zákazníka.", "Nový zákazník", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButtonInsertActionPerformed


    @Override
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    if (image != null)
      g.drawImage(image.getImage(), 0,0,this);
  }


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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonInsert;
    private javax.swing.JLabel jLabelLogin;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelPIN;
    private javax.swing.JLabel jLabelSurname;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JTextField jTextFieldLogin;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldPIN;
    private javax.swing.JTextField jTextFieldSurname;
    // End of variables declaration//GEN-END:variables

}
