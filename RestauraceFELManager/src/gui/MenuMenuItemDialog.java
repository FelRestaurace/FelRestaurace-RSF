/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

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
import service.ConfigParser;
import service.ResultTableModel;
import service.ServiceFacade;
import service.Validator;

/**
 * Trida reprezentujici JDialog pro evidenci polozek menu v ramci nektereho
 * menu.
 *
 * @author Jarda
 */
public class MenuMenuItemDialog extends AbstractDialog {

    private JScrollPane paneMenuItem = null;
    private JScrollPane paneTable = null;
    private JTable table = null;
    private MenuMenuItemForm menuMenuItemForm = null;
    private JFrame parent = null;
    private int row = -1;
    private int menuId;

    /**
     * Konstruktor tridy MenuMenuItemDialog.
     *
     * @param parent instance tridy JFrame jenz vytvorila tento formular
     * @param modal urcuje, zda bude okno modalni
     * @param menuId ID menu, pro ktere se maji evidovat polozky
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    public MenuMenuItemDialog(JFrame parent, boolean modal, int menuId) throws RemoteException, NotBoundException, FileNotFoundException {
        super(parent, modal);
        super.setTitle("Přiřazení položek menu");
        this.parent = parent;
        this.menuId = menuId;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int y = (int) ((dim.getHeight() - 550) / 2);
        int x = (int) ((dim.getWidth() - 450) / 2);
        setBounds(x, y, 450, 550);
        initComponents();
        menuMenuItemForm.getMenuLabel().setText(ServiceFacade.getInstance().getMenuById(menuId).getName());
        initTable();        
        refresh();
    }

    /**
     * Metoda inicializuje komponenty na JDialogu.
     *
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    @Override
    protected void initComponents() throws RemoteException, NotBoundException, FileNotFoundException {
        paneMenuItem = new JScrollPane();
        paneTable = new JScrollPane();
        table = new JTable();
        menuMenuItemForm = new MenuMenuItemForm();
        paneMenuItem.getViewport().add(menuMenuItemForm);
        paneTable.getViewport().add(table);
        Container con = getContentPane();
        con.setLayout(new BorderLayout());
        con.add(paneMenuItem, BorderLayout.NORTH);
        con.add(paneTable, BorderLayout.CENTER);

        menuMenuItemForm.getInsertButton().addActionListener(new ActionListener() {
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

        menuMenuItemForm.getDeleteButton().addActionListener(new ActionListener() {
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
     * Metoda pro vytvoreni noveho zaznamu o prirazeni dane polozky k danemu menu.
     *
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    private void insertAction() throws RemoteException, NotBoundException, FileNotFoundException {
        int i = isValidInput();
        if (i == 0) {
            String name = (String)menuMenuItemForm.getMenuItemComboBox().getSelectedItem();
            int menuItemId = ServiceFacade.getInstance().getMenuItemByName(name).getMenuItemId();
            boolean isOK;            
            isOK = ServiceFacade.getInstance().createMenuMenuItem(menuId, menuItemId);
            if (!isOK) {
                JOptionPane.showMessageDialog(parent, "Daná položka menu se již v menu nachází.", "Přiřazení položky menu", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            menuMenuItemForm.clearFields();
            refresh();
        } else if (i == 1) {
            JOptionPane.showMessageDialog(parent, "Musí být vybrána položka menu.", "Přiřazení položky menu", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Metoda provadi smazani vybrane vazby mezi polozkou menu a menu.
     *
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    private void deleteAction() throws RemoteException, NotBoundException, FileNotFoundException {
        row = table.getSelectedRow();
        String menuItemName = String.valueOf(table.getModel().getValueAt(row, 2));
        int menuItemId = ServiceFacade.getInstance().getMenuItemByName(menuItemName).getMenuItemId();
        boolean isOK = ServiceFacade.getInstance().deleteMenuMenuItem(menuId, menuItemId);
        if (!isOK){
            JOptionPane.showMessageDialog(parent, "Záznam nebyl smazán.", "Přiřazení položky menu", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        refresh();
    }

    /**
     * Metoda kontrolujici spravnost zadanych informaci ve vstupnich komponentach.
     *
     * @return 0 v pripade, ze jsou vsechny vstupy OK; jinak navraci cislo
     * vstupni komponenty
     */
    protected int isValidInput(){
        if (!Validator.isSelectedItem(menuMenuItemForm.getMenuItemComboBox())) return 1;
        return 0;
    }    

    /**
     * Metoda inicializujici tabulku na danem JDialogu.
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
                    menuMenuItemForm.clearFields();
                    menuMenuItemForm.getInsertButton().setEnabled(true);
                    menuMenuItemForm.getDeleteButton().setEnabled(false);
                    menuMenuItemForm.getInsertButton().setText("Vložit položku menu");
                    menuMenuItemForm.getInsertButton().setToolTipText("Vložit položku do menu.");
                } else {                   
                    menuMenuItemForm.getMenuItemComboBox().setSelectedItem((String) table.getModel().getValueAt(row, 1));
                    menuMenuItemForm.getDeleteButton().setEnabled(true);
                    menuMenuItemForm.getInsertButton().setEnabled(false);
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
        setComboBoxModel(ServiceFacade.getInstance().getMenuItemNames(), menuMenuItemForm.getMenuItemComboBox());
        refreshTable();
    }

    /**
     * Metoda aktualizujici zaznamy v dane tabulce na tomto JDialogu.
     *
     * @throws java.io.FileNotFoundException
     * @throws java.rmi.NotBoundException
     * @throws java.rmi.RemoteException
     */
    protected void refreshTable() throws FileNotFoundException, NotBoundException, RemoteException {
        ResultTableModel rtm = new ResultTableModel();
        Object[][] menuMenus = ServiceFacade.getInstance().getMenuItemsByMenu(menuId);
        rtm.setColumnData(ResultTableModel.namesMenuMenuItem);
        rtm.setTableData(menuMenus);
        table.setModel(rtm);
    }
}
