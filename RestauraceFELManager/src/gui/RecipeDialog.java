/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import hibernate.Material;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import service.CodeList;
import service.ConfigParser;
import service.ResultTableModel;
import service.ServiceFacade;
import service.Validator;

/**
 *
 * @author Jarda
 */
public class RecipeDialog extends AbstractDialog {

    private JScrollPane paneRecipe = null;
    private JScrollPane paneTable = null;
    private JTable table = null;
    private RecipeForm recipeForm = null;
    private JFrame parent = null;
    private int row = -1;
    private int menuItemId;

    /**
     * Konstruktor tridy RecipeDialog.
     *
     * @param parent instance tridy JFrame jenz vytvorila tento formular
     * @param modal urcuje, zda bude dialog modalni
     * @param menuItemId ID polozky menu, pro kterou se ma definovat receptura
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    public RecipeDialog(JFrame parent, boolean modal, int menuItemId) throws RemoteException, NotBoundException, FileNotFoundException {
        super(parent, modal);
        super.setTitle("Tvorba Menu");
        this.parent = parent;
        this.menuItemId = menuItemId;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int y = (int) ((dim.getHeight() - 550) / 2);
        int x = (int) ((dim.getWidth() - 400) / 2);
        setBounds(x, y, 400, 550);
        initComponents();
        recipeForm.getMenuItemLabel().setText(ServiceFacade.getInstance().getMenuItemById(menuItemId).getName());
        initTable();
        setComboBoxesListeners(recipeForm.getMaterialTypeComboBox(), recipeForm.getMaterialComboBox(), recipeForm.getUniTypeComboBox());
        refresh();        
    }

    /**
     * Metoda pro inicializaci komponent dialogu.
     */
    protected void initComponents() throws RemoteException, NotBoundException, FileNotFoundException {
        paneRecipe = new JScrollPane();
        paneTable = new JScrollPane();
        table = new JTable();
        recipeForm = new RecipeForm();
        paneRecipe.getViewport().add(recipeForm);
        paneTable.getViewport().add(table);
        Container con = getContentPane();
        con.setLayout(new BorderLayout());
        con.add(paneRecipe, BorderLayout.NORTH);
        con.add(paneTable, BorderLayout.CENTER);

        recipeForm.getInsertButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    insertAction();
                } catch (FileNotFoundException fnfe){
                    JOptionPane.showMessageDialog(null, "Konfigurační soubor \""+ConfigParser.getConfigFile()+"\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        recipeForm.getDeleteButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    deleteAction();
                } catch (FileNotFoundException fnfe){
                    JOptionPane.showMessageDialog(null, "Konfigurační soubor \""+ConfigParser.getConfigFile()+"\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
                }
            }
        });        
    }    

    /**
     * Metoda pro pridani suroviny do receptury dane polozky menu.
     *
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    private void insertAction() throws RemoteException, NotBoundException, FileNotFoundException {
        int i = isValidInput();
        if (i == 0){
            double q = Double.parseDouble(recipeForm.getQuantityTextField().getText());
            int materialId = ServiceFacade.getInstance().getMaterialByName((String)recipeForm.getMaterialComboBox().getSelectedItem()).getMaterialId();
            int fromUnitTypeId = ServiceFacade.getInstance().getUnitTypeByAbbr((String)recipeForm.getUniTypeComboBox().getSelectedItem()).getUnitTypeId();
            int toUnitTypeId = ServiceFacade.getInstance().getMaterialByID(materialId).getUnitType().getUnitTypeId();
            double quantity = CodeList.transfer(fromUnitTypeId, toUnitTypeId, q);
            boolean isOK;
            if (row == -1){
                ServiceFacade.getInstance().createUsedMaterial(materialId, menuItemId, quantity);
            } else {
                int usedMaterialId = (Integer)table.getModel().getValueAt(row, 0);
                isOK = ServiceFacade.getInstance().updateUsedMaterial(usedMaterialId, materialId, menuItemId, quantity);
                if (!isOK){
                    JOptionPane.showMessageDialog(this, "Záznam nemohl být aktualizován.", "Receptura", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
            refreshTable();
            recipeForm.clearFields();
        } else if (i == 1){
            JOptionPane.showMessageDialog(this, "Musí být vybrána surovina.", "Receptura", JOptionPane.INFORMATION_MESSAGE);
        } else if (i == 2){
            JOptionPane.showMessageDialog(this, "Musí být vybrána měrná jednotka.", "Receptura", JOptionPane.INFORMATION_MESSAGE);
        } else if (i == 3){
            JOptionPane.showMessageDialog(this, "Musí být určeno množství použité suroviny.", "Receptura", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Metoda provadi smazani vybraneho vybrane slozky receptury.
     *
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    private void deleteAction() throws RemoteException, NotBoundException, FileNotFoundException {
        row = table.getSelectedRow();
        int usedMaterialId = (Integer)table.getModel().getValueAt(row, 0);
        boolean ok = ServiceFacade.getInstance().deleteUsedMaterial(usedMaterialId);
        if (!ok){
            JOptionPane.showMessageDialog(this, "Záznam nebyl smazán.", "Receptura", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        refreshTable();
    }


    /**
     * Metoda kontrolujici spravnost zadanych informaci ve vstupnich komponentach.
     *
     * @return 0 v pripade, ze jsou vsechny vstupy OK; jinak navraci cislo
     * vstupni komponenty
     */
    protected int isValidInput(){
        if (!Validator.isSelectedItem(recipeForm.getMaterialComboBox())) return 1;
        if (!Validator.isSelectedItem(recipeForm.getUniTypeComboBox())) return 2;
        if (!Validator.isPositiveDouble(recipeForm.getQuantityTextField())) return 3;
        return 0;
    }

    /**
     * Metoda inicializujici tabulku.
     *
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    protected void initTable() throws RemoteException, NotBoundException, FileNotFoundException {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                row = table.getSelectedRow();
                if (row == -1) {
                    recipeForm.clearFields();
                    recipeForm.getDeleteButton().setEnabled(false);
                    recipeForm.getInsertButton().setText("Vložit do receptury");
                    recipeForm.getInsertButton().setToolTipText("Vložit novou surovinu do receptury");
                } else {
                    String material = (String) table.getModel().getValueAt(row, 1);
                    recipeForm.getMaterialComboBox().setSelectedItem(material);
                    try {
                        recipeForm.getMaterialTypeComboBox().setSelectedItem(ServiceFacade.getInstance().getMaterialByName(material).getMaterialType().getName());
                    } catch (Exception ex){
                    }
                    recipeForm.getQuantityTextField().setText(String.valueOf(table.getModel().getValueAt(row, 2)));
                    recipeForm.getUniTypeComboBox().setSelectedItem((String) table.getModel().getValueAt(row, 3));
                    recipeForm.getDeleteButton().setEnabled(true);
                    recipeForm.getInsertButton().setText("Aktualizovat recepturu");
                    recipeForm.getInsertButton().setToolTipText("Aktualizovat stávající záznam o surovině receptury");
                }
            }
        });
        //refresh();
    }

    /**
     * Metoda provadi aktualizaci vsech comboBoxu a aktualizaci tabulky.
     * Zaroven prenastavuje statusBar.
     *
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    protected void refresh() throws RemoteException, NotBoundException, FileNotFoundException {
        setComboBoxModel(ServiceFacade.getInstance().getMaterialTypeNames(), recipeForm.getMaterialTypeComboBox());
        refreshTable();
    }    

    /**
     * Metoda pro aktualizaci tabulky daneho JDialogu.
     *
     * @throws java.io.FileNotFoundException
     * @throws java.rmi.NotBoundException
     * @throws java.rmi.RemoteException
     */
    protected void refreshTable() throws FileNotFoundException, NotBoundException, RemoteException {
        ResultTableModel rtm = new ResultTableModel();
        Object[][] recipes = ServiceFacade.getInstance().getUsedMaterialsByMenuItem(menuItemId);
        rtm.setColumnData(ResultTableModel.namesRecipe);
        rtm.setTableData(recipes);
        table.setModel(rtm);
    }

}
