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
 * Trida reprezentujici dialog pro praci s kategoriemi polozek menu (MenuItemType).
 *
 * @author Jarda
 */
public class MenuItemTypeDialog extends AbstractDialog {

    private JScrollPane paneMenuItemType = null;
    private JScrollPane paneTable = null;
    private JTable table = null;
    private MenuItemTypeForm menuItemTypeForm = null;
    private JFrame parent = null;
    private int row = -1;

    /**
     * Konstruktor tridy MenuItemTypeDialog.
     *
     * @param parent instance tridy JFrame jenz vytvorila tento formular
     * @param modal urcuje, zda bude okno modalni     
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    public MenuItemTypeDialog(JFrame parent, boolean modal) throws RemoteException, NotBoundException, FileNotFoundException {
        super(parent, modal);
        super.setTitle("Kategorie položek menu");
        this.parent = parent;        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int y = (int) ((dim.getHeight() - 550) / 2);
        int x = (int) ((dim.getWidth() - 450) / 2);
        setBounds(x, y, 450, 550);
        initComponents();        
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
        paneMenuItemType = new JScrollPane();
        paneTable = new JScrollPane();
        table = new JTable();
        menuItemTypeForm = new MenuItemTypeForm();
        paneMenuItemType.getViewport().add(menuItemTypeForm);
        paneTable.getViewport().add(table);
        Container con = getContentPane();
        con.setLayout(new BorderLayout());
        con.add(paneMenuItemType, BorderLayout.NORTH);
        con.add(paneTable, BorderLayout.CENTER);

        menuItemTypeForm.getInsertButton().addActionListener(new ActionListener() {
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

        menuItemTypeForm.getDeleteButton().addActionListener(new ActionListener() {
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
     * Metoda pro vkladani novych kategorii polozek menu do systemu.
     *
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    public void insertAction() throws RemoteException, NotBoundException, FileNotFoundException {
        int i = isValidInput();
        if (i == 0) {
            String name = menuItemTypeForm.getTextFieldName().getText();
            boolean isOK;
            if (row == -1){
                isOK = ServiceFacade.getInstance().createMenuItemType(name);
            } else {
                int menuItemTypeId = (Integer) table.getModel().getValueAt(row, 0);
                isOK = ServiceFacade.getInstance().updateMenuItemType(menuItemTypeId, name);
            }
            if (!isOK) {
                JOptionPane.showMessageDialog(this, "Záznam nemohl být uložen, protože záznam se stejným názvem kategorie již uložen.", "Kategorie položek menu", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            menuItemTypeForm.clearFields();
            refresh();
        } else if (i == 1) {
            JOptionPane.showMessageDialog(this, "Musí být uveden název nové kategorie.", "Kategorie položek menu", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Metoda pro mazani kategorii polozek menu ze systemu.
     *
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    public void deleteAction() throws RemoteException, NotBoundException, FileNotFoundException {
        row = table.getSelectedRow();
        int menuItemTypeId = (Integer) table.getModel().getValueAt(row, 0);
        if (!ServiceFacade.getInstance().isDeletableMenuItemType(menuItemTypeId)){
            JOptionPane.showMessageDialog(this, "Kategorie nemůže být smazána, protože se na ní odkazují existující položky menu.", "Kategorie položek menu", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        boolean isOK = ServiceFacade.getInstance().deleteMenuItemType(menuItemTypeId);
        if (!isOK){
            JOptionPane.showMessageDialog(this, "Záznam nebyl smazán.", "Kategorie položek menu", JOptionPane.INFORMATION_MESSAGE);
        }
        refresh();
    }

    /**
     * Metoda kontrolujici spravnost zadanych informaci ve vstupnich komponentach.
     *
     * @return 0 v pripade, ze jsou vsechny vstupy OK; jinak navraci cislo
     * vstupni komponenty s chybovou hodnotou
     */
    @Override
    protected int isValidInput() {
        if (!Validator.isText(menuItemTypeForm.getTextFieldName())) return 1;
        return 0;
    }

    /**
     * Metoda inicializujici tabulku na danem JDialogu.
     *
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    @Override
    protected void initTable() throws RemoteException, NotBoundException, FileNotFoundException {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                row = table.getSelectedRow();
                if (row == -1) {
                    menuItemTypeForm.clearFields();
                    menuItemTypeForm.getInsertButton().setEnabled(true);
                    menuItemTypeForm.getDeleteButton().setEnabled(false);
                    menuItemTypeForm.getInsertButton().setText("Vytvořit kategorii");
                    menuItemTypeForm.getInsertButton().setToolTipText("Vytvořit novou kategorii položek menu");
                } else {
                    menuItemTypeForm.getTextFieldName().setText((String)table.getModel().getValueAt(row, 1));
                    menuItemTypeForm.getDeleteButton().setEnabled(true);
                    menuItemTypeForm.getInsertButton().setText("Aktualizovat");
                }
            }
        });
        //refresh();
    }

    /**
     * Metoda aktualizujici zaznamy v dane tabulce na tomto JDialogu.
     *
     * @throws java.io.FileNotFoundException
     * @throws java.rmi.NotBoundException
     * @throws java.rmi.RemoteException
     */
    @Override
    protected void refresh() throws RemoteException, NotBoundException, FileNotFoundException {
        ResultTableModel rtm = new ResultTableModel();
        Object[][] menuMenus = ServiceFacade.getInstance().getMenuItemTypes();
        rtm.setColumnData(ResultTableModel.namesMenuItemType);
        rtm.setTableData(menuMenus);
        table.setModel(rtm);
    }

    

}
