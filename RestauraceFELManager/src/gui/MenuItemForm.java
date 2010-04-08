/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MenuItemForm.java
 *
 * Created on 3.6.2009, 0:20:13
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
 * Trida reprezentujici GUI pro okno, kde se vytvareji polozky menu.
 *
 * @author Jarda
 */
public class MenuItemForm extends AbstractForm {

    private RecipeDialog recipeDialog = null;
    private MenuItemTypeDialog menuItemTypeDialog = null;
    private JTable table = null;
    private JFrame parent = null;
    private StatusBar statusBar = null;
    private int row = -1;    
    private ConfigParser config = null;

    /**
     * Konstruktor tridy MenuItemForm.
     */
    public MenuItemForm(JFrame parent, StatusBar bar) throws FileNotFoundException, RemoteException, NotBoundException {
        this.parent = parent;
        this.statusBar = bar;
        initComponents();
        initTable();
        config = new ConfigParser();
        refreshSettings();
        refresh();
        clearFields();
    }

    /**
     * Metoda aktualizuje formular dle udaju ulozenych v config. souboru.
     *
     * @throws java.io.FileNotFoundException
     */
    public void refreshSettings() throws FileNotFoundException {
        String money = " v " + config.getMoney();
        jLabelMoney.setText(money);
    }

    /**
     * Metoda provadi aktualizaci vsech comboBoxu a aktualizaci tabulky.
     * Zaroven prenastavuje statusBar.
     *
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    @Override
    protected void refresh() throws FileNotFoundException, NotBoundException, RemoteException {
        setComboBoxModel(ServiceFacade.getInstance().getMenuItemTypeNames(), jComboBoxType);
        refreshTable();
        statusBar.setMessage("Tento formulář slouží k práci s položkami menu.");
    }

    /**
     * Metoda kontrolujici spravnost vyplnenych udaju.
     *
     * @return Vraci index urcujici vstupni komponentu, ktera obsahuje
     * neplatny vstup.
     */
    @Override
    protected int isValidInput() {
        if (!Validator.isText(jTextFieldName)) return 1;
        if (!Validator.isPositiveDouble(jTextFieldPrice)) return 2;
        if (!Validator.isSelectedItem(jComboBoxAvailable)) return 3;
        if (!Validator.isText(jTextFieldQuantity)) return 4;
        if (!Validator.isSelectedItem(jComboBoxType)) return 5;
        return 0;
    }

    /**
     * Metoda inicializujici tabulku.
     */
    @Override
    protected void initTable() {
        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                row = table.getSelectedRow();
                if (row == -1) {
                    clearFields();
                    jButtonDelete.setEnabled(false);
                    jButtonRecipe.setEnabled(false);
                    jButtonInsert.setText("Vytvořit položku");
                    statusBar.setMessage("Vytvořit novou položku menu.");
                } else {
                    jComboBoxType.setSelectedItem((String)table.getModel().getValueAt(row, 1));
                    jTextFieldName.setText((String) table.getModel().getValueAt(row, 2));
                    jTextFieldQuantity.setText((String) table.getModel().getValueAt(row, 3));
                    jTextFieldPrice.setText(String.valueOf(table.getModel().getValueAt(row, 4)));
                    jComboBoxAvailable.setSelectedItem((String)table.getModel().getValueAt(row, 5));
                    jButtonDelete.setEnabled(true);
                    jButtonRecipe.setEnabled(true);
                    jButtonInsert.setText("Aktualizovat položku");
                    statusBar.setMessage("Položka menu ID: " +table.getValueAt(row, 0)+", \""+table.getValueAt(row, 2)+"\"");
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
    @Override
    protected void refreshTable() throws FileNotFoundException, NotBoundException, RemoteException {
        ResultTableModel rtm = new ResultTableModel();
        Object[][] menuItems = ServiceFacade.getInstance().getMenuItems();
        rtm.setColumnData(ResultTableModel.namesMenuItem);
        rtm.setTableData(menuItems);
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
     * Metoda cisti vstupni pole na tomto formulari.
     */
    @Override
    protected void clearFields() {
        Validator.clearTextField(jTextFieldName);
        Validator.clearTextField(jTextFieldPrice);
        Validator.clearTextField(jTextFieldQuantity);
        Validator.clearComboBox(jComboBoxAvailable);
        Validator.clearComboBox(jComboBoxType);
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
        jLabelPrice = new javax.swing.JLabel();
        jLabelAvailable = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jTextFieldPrice = new javax.swing.JTextField();
        jComboBoxAvailable = new javax.swing.JComboBox();
        jLabelMoney = new javax.swing.JLabel();
        jButtonInsert = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonClear = new javax.swing.JButton();
        jButtonRecipe = new javax.swing.JButton();
        jLabelQuantity = new javax.swing.JLabel();
        jTextFieldQuantity = new javax.swing.JTextField();
        jLabelType = new javax.swing.JLabel();
        jComboBoxType = new javax.swing.JComboBox();
        jButtonKategory = new javax.swing.JButton();

        jLabelName.setText("Název položky menu");

        jLabelPrice.setText("Cena položky menu");

        jLabelAvailable.setText("Dostupnost");

        jComboBoxAvailable.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dostupná", "Nedostupná" }));

        jLabelMoney.setText("měna");

        jButtonInsert.setText("Vytvořit položku");
        jButtonInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertActionPerformed(evt);
            }
        });

        jButtonDelete.setText("Smazat položku");
        jButtonDelete.setEnabled(false);
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButtonClear.setText("Vyčistit formulář");
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });

        jButtonRecipe.setText("Receptura");
        jButtonRecipe.setEnabled(false);
        jButtonRecipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRecipeActionPerformed(evt);
            }
        });

        jLabelQuantity.setText("Množství");

        jLabelType.setText("Kategorie");

        jButtonKategory.setText("Kategorie");
        jButtonKategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKategoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelName)
                    .addComponent(jLabelPrice)
                    .addComponent(jLabelQuantity)
                    .addComponent(jLabelAvailable)
                    .addComponent(jLabelType))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jComboBoxAvailable, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldQuantity, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldPrice, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelMoney)))
                .addGap(1, 1, 1)
                .addComponent(jButtonKategory, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButtonRecipe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonInsert, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(jButtonDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelType)
                    .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonInsert))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelName)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonClear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPrice)
                    .addComponent(jTextFieldPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMoney)
                    .addComponent(jButtonDelete))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelQuantity)
                            .addComponent(jTextFieldQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelAvailable)
                            .addComponent(jComboBoxAvailable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonRecipe)
                        .addComponent(jButtonKategory)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertActionPerformed
        int i = isValidInput();
        if (i == 0){
            String name = jTextFieldName.getText();
            double price = Double.parseDouble((String)jTextFieldPrice.getText());
            String quantity = jTextFieldQuantity.getText();
            int available;
            if (((String)jComboBoxAvailable.getSelectedItem()).equals("Dostupná")){
                available = 1;
            } else {
                available = 0;
            }            
            boolean isOK;
            try {
                int menuItemTypeId = ServiceFacade.getInstance().getMenuItemTypeByName((String)jComboBoxType.getSelectedItem()).getMenuItemTypeId();
                if (row == -1){
                    isOK = ServiceFacade.getInstance().createMenuItem(name, price, quantity, available, menuItemTypeId);
                    if (!isOK){
                        JOptionPane.showMessageDialog(this, "Položka menu nemohla být uložena, protože položka se stejným názvem již existuje.", "Položka menu", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                } else {
                    int menuItemId = (Integer)table.getModel().getValueAt(row, 0);
                    isOK = ServiceFacade.getInstance().updateMenuItem(menuItemId, name, price, quantity, available, menuItemTypeId);
                    if (!isOK){
                        JOptionPane.showMessageDialog(this, "Položka menu nemohla být aktualizovaná, protože položka se stejným názvem již existuje.", "Položka menu", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }
                refreshTable();
            } catch (FileNotFoundException fnfe){
                JOptionPane.showMessageDialog(this, "Konfigurační soubor \""+ConfigParser.getConfigFile()+"\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex){
                JOptionPane.showMessageDialog(this, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
            }
            clearFields();
        } else if (i == 1) {
            JOptionPane.showMessageDialog(this, "Údaj o názvu položky menu musí být vyplněn.", "Položka menu", JOptionPane.INFORMATION_MESSAGE);
        } else if (i == 2) {
            JOptionPane.showMessageDialog(this, "Údaj o ceně položky menu musí být vyplněn.", "Položka menu", JOptionPane.INFORMATION_MESSAGE);
        } else if (i == 3) {
            JOptionPane.showMessageDialog(this, "Musí být určeno, zda je položka dostupná či nedostupná.", "Položka menu", JOptionPane.INFORMATION_MESSAGE);
        } else if (i == 4){
            JOptionPane.showMessageDialog(this, "Musí být uvedeno množství.", "Položky menu", JOptionPane.INFORMATION_MESSAGE);
        } else if (i == 5){
            JOptionPane.showMessageDialog(this, "Musí být vybrána kategorie, kam bude daná položka spadat.", "Položka menu", JOptionPane.INFORMATION_MESSAGE);
        }
}//GEN-LAST:event_jButtonInsertActionPerformed

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
        clearFields();
}//GEN-LAST:event_jButtonClearActionPerformed

    private void jButtonRecipeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRecipeActionPerformed
        Integer menuItemId = (Integer)table.getModel().getValueAt(row, 0);
        try {
            recipeDialog = new RecipeDialog(parent, true, menuItemId);
            recipeDialog.setVisible(true);
        } catch (FileNotFoundException fnfe){
            JOptionPane.showMessageDialog(this, "Konfigurační soubor \""+ConfigParser.getConfigFile()+"\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex){
            JOptionPane.showMessageDialog(this, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
        }
}//GEN-LAST:event_jButtonRecipeActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        row = table.getSelectedRow();
        int menuItemId = (Integer)table.getModel().getValueAt(row, 0);
        try {
            boolean ok = ServiceFacade.getInstance().deleteMenuItem(menuItemId);
            if (!ok){
                JOptionPane.showMessageDialog(this, "Záznam nebyl smazán.", "Položka menu", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            refreshTable();
        } catch (FileNotFoundException fnfe){
            JOptionPane.showMessageDialog(this, "Konfigurační soubor \""+ConfigParser.getConfigFile()+"\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex){
            JOptionPane.showMessageDialog(this, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);            
        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonKategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKategoryActionPerformed
        try {
            menuItemTypeDialog = new MenuItemTypeDialog(parent, true);
            menuItemTypeDialog.setVisible(true);
            refresh();
        } catch (FileNotFoundException fnfe){
            JOptionPane.showMessageDialog(this, "Konfigurační soubor \""+ConfigParser.getConfigFile()+"\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonKategoryActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonInsert;
    private javax.swing.JButton jButtonKategory;
    private javax.swing.JButton jButtonRecipe;
    private javax.swing.JComboBox jComboBoxAvailable;
    private javax.swing.JComboBox jComboBoxType;
    private javax.swing.JLabel jLabelAvailable;
    private javax.swing.JLabel jLabelMoney;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelPrice;
    private javax.swing.JLabel jLabelQuantity;
    private javax.swing.JLabel jLabelType;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldPrice;
    private javax.swing.JTextField jTextFieldQuantity;
    // End of variables declaration//GEN-END:variables


}
