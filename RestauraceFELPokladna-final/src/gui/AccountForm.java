/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AccountForm.java
 *
 * Created on 5.4.2009, 11:31:27
 */
package gui;

import hibernate.Account;
import hibernate.DiscountType;
import hibernate.Table;
import hibernate.User;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import service.ConfigParser;
import service.ResultTableModel;
import service.ServiceFacade;
import service.Validator;

/**
 * Trida reprezentujici GUI formular pro prehled uctu systemu.
 *
 * @author Tomas Hnizdil
 */
public class AccountForm extends AbstractForm {

    private JTable table = null;
    private StatusBar statusBar = null;
    private int row = -1;
    private JFrame parent = null;
    private AccountDialog accountDialog = null;
    ImageIcon image = new ImageIcon("images/backgroundpane-bg.jpg");

    /**
     * Konstruktor tridy AccountForm.
     *
     * @param parent
     * @param bar
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    public AccountForm(JFrame parent, StatusBar bar) throws FileNotFoundException, NotBoundException, RemoteException {
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
        setComboBoxModel((ServiceFacade.getInstance().getTableNames()), jComboBoxTable);
        setComboBoxModel((ServiceFacade.getInstance().getUserUsernames()), jComboBoxPerson);
        setComboBoxModel((ServiceFacade.getInstance().getDiscountTypeNames()), jComboBoxDiscount);
        setComboBoxModel((ServiceFacade.getInstance().getAccountStatusTypeNames()), jComboBoxAccountStatusType);
        refreshTable();
        statusBar.setMessage("Tento formulář slouží k zobrazení záznamů o účtech.");
    }

    /**
     * Metoda kontrolujici spravnost vyplnenych udaju.
     *
     * @return Vraci index urcujici vstupni komponentu, ktera obsahuje
     * neplatny vstup. Pokud je vse vporadku tak navraci 0.
     */
    protected int isValidInput() {
        if (!Validator.isText(jTextFieldName)) return 1;
        return 0;
    }

    /**
     * Metoda inicializujici tabulku.          
     */
    protected void initTable() {
        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    /**
     * Metoda aktualizuje tabulku s daty v parametru accounts.
     *
     * @param accounts
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    protected void refreshTable(Object[][] accounts) throws FileNotFoundException, NotBoundException, RemoteException {
        ResultTableModel rtm = new ResultTableModel();
        rtm.setColumnData(ResultTableModel.namesAccount);
        rtm.setTableData(accounts);
        table.setModel(rtm);
    }

    /**
     * Metoda aktualizuje tabulku s daty vsech uctu.
     *
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    protected void refreshTable() throws FileNotFoundException, NotBoundException, RemoteException {
        Object [][] accounts = ServiceFacade.getInstance().getAccounts();
        ResultTableModel rtm = new ResultTableModel();
        rtm.setColumnData(ResultTableModel.namesAccount);
        rtm.setTableData(accounts);
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
     * Metoda cisti vsechny vstupni pole, u comboBoxu nastavuje aktualni vybranou polozku na
     * prvni polozku daneho comboBoxu.
     */
    protected void clearFields() {
        Validator.clearTextField(jTextFieldName);
        Validator.clearComboBox(jComboBoxTable);
        Validator.clearComboBox(jComboBoxPerson);
        Validator.clearComboBox(jComboBoxDiscount);
        Validator.clearComboBox(jComboBoxAccountStatusType);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTable = new javax.swing.JLabel();
        jLabelPerson = new javax.swing.JLabel();
        jLabelDiscount = new javax.swing.JLabel();
        jButtonSearchByTable = new javax.swing.JButton();
        jButtonView = new javax.swing.JButton();
        jComboBoxTable = new javax.swing.JComboBox();
        jComboBoxPerson = new javax.swing.JComboBox();
        jComboBoxDiscount = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jButtonSearchByName = new javax.swing.JButton();
        jButtonSearchByPerson = new javax.swing.JButton();
        jButtonSearchByDiscountType = new javax.swing.JButton();
        jLabelAccountStatusType = new javax.swing.JLabel();
        jComboBoxAccountStatusType = new javax.swing.JComboBox();
        jButtonSearchByAccountStatusType = new javax.swing.JButton();
        jButtonClear = new javax.swing.JButton();
        jTextFieldName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        jLabelTable.setText("Stůl:");

        jLabelPerson.setText("Osoba:");

        jLabelDiscount.setText("Typ slevy:");

        jButtonSearchByTable.setText("Hledat podle stolu");
        jButtonSearchByTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchByTableActionPerformed(evt);
            }
        });

        jButtonView.setText("Zobrazit účet");
        jButtonView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonViewActionPerformed(evt);
            }
        });

        jLabel1.setText("Název:");

        jButtonSearchByName.setText("Hledat podle názvu");
        jButtonSearchByName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchByNameActionPerformed(evt);
            }
        });

        jButtonSearchByPerson.setText("Hledat podle osoby");
        jButtonSearchByPerson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchByPersonActionPerformed(evt);
            }
        });

        jButtonSearchByDiscountType.setText("Hledat podle typu slevy");
        jButtonSearchByDiscountType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchByDiscountTypeActionPerformed(evt);
            }
        });

        jLabelAccountStatusType.setText("Stav:");

        jButtonSearchByAccountStatusType.setText("Hledat podle stavu");
        jButtonSearchByAccountStatusType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchByAccountStatusTypeActionPerformed(evt);
            }
        });

        jButtonClear.setText("Zrušit filtr");
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Hledej");
        jLabel2.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabelAccountStatusType)
                    .addComponent(jLabelDiscount)
                    .addComponent(jLabelPerson)
                    .addComponent(jLabelTable))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxPerson, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jComboBoxAccountStatusType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBoxDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextFieldName)
                        .addComponent(jComboBoxTable, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonSearchByAccountStatusType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSearchByName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSearchByTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSearchByDiscountType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSearchByPerson, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonView, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonSearchByName)
                            .addComponent(jButtonView))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonSearchByTable)
                            .addComponent(jLabelTable))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxPerson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonSearchByPerson)
                            .addComponent(jLabelPerson)
                            .addComponent(jButtonClear))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonSearchByDiscountType)
                            .addComponent(jLabelDiscount))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxAccountStatusType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonSearchByAccountStatusType)
                            .addComponent(jLabelAccountStatusType))))
                .addContainerGap(297, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonViewActionPerformed
        row = table.getSelectedRow();
        if (row==-1) {
            JOptionPane.showMessageDialog(this, "Nebyl vybrán žádný účet.", "Žádný účet", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int accountId = (Integer)table.getModel().getValueAt(row, 0);
        try {
            accountDialog = new AccountDialog(parent, false, accountId, this);
            accountDialog.setVisible(true);
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(this, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
        }
}//GEN-LAST:event_jButtonViewActionPerformed

    private void jButtonSearchByTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchByTableActionPerformed
        try {
            int tableId = Integer.parseInt(jComboBoxTable.getSelectedItem().toString());
            Object[][] accounts = ServiceFacade.getInstance().getAccountsByTable(tableId);
            refreshTable(accounts);
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(this, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonSearchByTableActionPerformed

    private void jButtonSearchByNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchByNameActionPerformed
        
        KeyboardDialog keyboard = new KeyboardDialog(parent, true);
        keyboard.setLocation(600, 220);
        keyboard.setTextField(jTextFieldName);
        keyboard.setVisible(true);
        String name = jTextFieldName.getText();
        try {
            Account a = ServiceFacade.getInstance().getAccountByName(name);
            if (a==null) {
                refreshTable(null);
                return;
            }
            Object[][] accounts = new Object[1][6];
            Table t = a.getTable();
            User user = a.getUser();
            DiscountType discountType = a.getDiscountType();
            accounts[0][0] = a.getAccountId();
            accounts[0][1] = a.getName();
            accounts[0][2] = (t == null ? null : t.getTableNumber());
            accounts[0][3] = (user == null ? null : user.getUsername());
            accounts[0][4] = (discountType == null) ? null : discountType.getName();
            accounts[0][5] = a.getAccountStatusType().getName();
            refreshTable(accounts);
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(this, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonSearchByNameActionPerformed

    private void jButtonSearchByPersonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchByPersonActionPerformed
        try {
            int userId = ServiceFacade.getInstance().getUserByUsername(jComboBoxPerson.getSelectedItem().toString()).getUserId();
            Object[][] accounts = ServiceFacade.getInstance().getAccountsByUser(userId);
            refreshTable(accounts);
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(this, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonSearchByPersonActionPerformed

    private void jButtonSearchByDiscountTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchByDiscountTypeActionPerformed
        try {
            int discountTypeId = ServiceFacade.getInstance().getDiscountTypeByName(jComboBoxDiscount.getSelectedItem().toString()).getDiscountTypeId();
            Object[][] accounts = ServiceFacade.getInstance().getAccountsByDiscountType(discountTypeId);
            refreshTable(accounts);
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(this, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonSearchByDiscountTypeActionPerformed

    private void jButtonSearchByAccountStatusTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchByAccountStatusTypeActionPerformed
        try {
            int statusId = ServiceFacade.getInstance().getAccountStatusTypeByName(jComboBoxAccountStatusType.getSelectedItem().toString()).getAccountStatusTypeId();
            Object[][] accounts = ServiceFacade.getInstance().getAccountsByAccountStatusType(statusId);
            refreshTable(accounts);
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(this, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonSearchByAccountStatusTypeActionPerformed

  @Override
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    if (image != null)
      g.drawImage(image.getImage(), 0,0,this);
  }

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
        clearFields();
        try {
            Object[][] accounts = ServiceFacade.getInstance().getAccounts();
            refreshTable(accounts);
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(this, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonClearActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonSearchByAccountStatusType;
    private javax.swing.JButton jButtonSearchByDiscountType;
    private javax.swing.JButton jButtonSearchByName;
    private javax.swing.JButton jButtonSearchByPerson;
    private javax.swing.JButton jButtonSearchByTable;
    private javax.swing.JButton jButtonView;
    private javax.swing.JComboBox jComboBoxAccountStatusType;
    private javax.swing.JComboBox jComboBoxDiscount;
    private javax.swing.JComboBox jComboBoxPerson;
    private javax.swing.JComboBox jComboBoxTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelAccountStatusType;
    private javax.swing.JLabel jLabelDiscount;
    private javax.swing.JLabel jLabelPerson;
    private javax.swing.JLabel jLabelTable;
    private javax.swing.JTextField jTextFieldName;
    // End of variables declaration//GEN-END:variables
}
