/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CustomerForm.java
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
import service.ConfigParser;
import service.ResultTableModel;
import service.ServiceFacade;
import service.Validator;

/**
 * Trida reprezentujici GUI formular pro prehled zakazniku.
 *
 * @author Tomas Hnizdil
 */
public class CustomerForm extends AbstractForm {

    private JTable table = null;
    private StatusBar statusBar = null;
    private int row = -1;
    private JFrame parent = null;
    private ConfigParser config = null;

    /**
     * Konstruktor tridy CustomerForm.
     *
     * @param parent
     * @param bar
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    public CustomerForm(JFrame parent, StatusBar bar) throws FileNotFoundException, NotBoundException, RemoteException {
        this.parent = parent;
        this.statusBar = bar;
        config = new ConfigParser();
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
        jLabelCurrency.setText(",- " + config.getMoney());
        refreshTable();
        statusBar.setMessage("Tento formulář slouží k zobrazení záznamů o zákaznících.");
    }

    /**
     * Metoda kontrolujici spravnost vyplnenych udaju.
     *
     * @return Vraci index urcujici vstupni komponentu, ktera obsahuje
     * neplatny vstup. Pokud je vse vporadku tak navraci 0.
     */
    protected int isValidInput() {
        if (!Validator.isPositiveDouble(jTextFieldCredit)) {
            return 1;
        }
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
     * Metoda aktualizuje tabulku s daty vsech zakazniku.
     *
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    protected void refreshTable() throws FileNotFoundException, NotBoundException, RemoteException {
        Object [][] accounts = ServiceFacade.getInstance().getUsers();
        ResultTableModel rtm = new ResultTableModel();
        rtm.setColumnData(ResultTableModel.namesUser);
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
        jTextFieldCredit.setText("0");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonSetCredit = new javax.swing.JButton();
        JLabelCredit = new javax.swing.JLabel();
        jTextFieldCredit = new javax.swing.JTextField();
        jLabelCurrency = new javax.swing.JLabel();

        jButtonSetCredit.setText("Nastavit kredit");
        jButtonSetCredit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSetCreditActionPerformed(evt);
            }
        });

        JLabelCredit.setText("Předplacený kredit:");

        jLabelCurrency.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JLabelCredit)
                .addGap(30, 30, 30)
                .addComponent(jTextFieldCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelCurrency)
                .addGap(43, 43, 43)
                .addComponent(jButtonSetCredit)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLabelCredit)
                    .addComponent(jTextFieldCredit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSetCredit)
                    .addComponent(jLabelCurrency))
                .addContainerGap(23, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSetCreditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSetCreditActionPerformed
        row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Nebyl vybrán žádný zákazník.", "Žádný zákazník", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        switch (isValidInput()) {
            case 0:
                int userId = (Integer) table.getModel().getValueAt(row, 0);
                double credit = Double.parseDouble(jTextFieldCredit.getText());
                try {
                    ServiceFacade.getInstance().updateUser(userId, credit);
                } catch (FileNotFoundException fnfe) {
                    JOptionPane.showMessageDialog(this, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 1:
                JOptionPane.showMessageDialog(this, "Údaj o předplaceném kreditu musí být kladné číslo.", "Předplacený kredit", JOptionPane.INFORMATION_MESSAGE);
                break;
            default: break;
        }
    }//GEN-LAST:event_jButtonSetCreditActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLabelCredit;
    private javax.swing.JButton jButtonSetCredit;
    private javax.swing.JLabel jLabelCurrency;
    private javax.swing.JTextField jTextFieldCredit;
    // End of variables declaration//GEN-END:variables
}
