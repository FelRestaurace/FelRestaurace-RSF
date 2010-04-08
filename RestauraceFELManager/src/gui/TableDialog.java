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
import javax.swing.JDialog;
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
 * Trida reprezentujici dialog pro evidenci stolu.
 *
 * @author Jarda
 */
public class TableDialog extends JDialog {

    private JScrollPane paneXTable = null;
    private JScrollPane paneTable = null;
    private JTable table = null;
    private TableForm tableForm = null;
    private JFrame parent = null;
    private int row = -1;

    /**
     * Konstruktor tridy TableDialog.
     *
     * @param parent instance tridy JFrame jenz vytvorila tento dialog
     * @param modal urcuje, zda bude dialog modalni
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    public TableDialog(JFrame parent, boolean modal) throws RemoteException, NotBoundException, FileNotFoundException {
        super(parent, modal);
        super.setTitle("Evidence stolů");
        this.parent = parent;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int y = (int) ((dim.getHeight() - 350) / 2);
        int x = (int) ((dim.getWidth() - 400) / 2);
        setBounds(x, y, 400, 350);
        initComponents();
        initTable();
    }

    /**
     * Inicializuje graficke komponenty, nastavuje akce pro tlacitka.
     *
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    private void initComponents() throws RemoteException, NotBoundException {
        paneXTable = new JScrollPane();
        paneTable = new JScrollPane();
        table = new JTable();
        tableForm = new TableForm();
        paneXTable.getViewport().add(tableForm);
        paneTable.getViewport().add(table);
        Container con = getContentPane();
        con.setLayout(new BorderLayout());
        con.add(paneXTable, BorderLayout.NORTH);
        con.add(paneTable, BorderLayout.CENTER);

        tableForm.getInsertButton().addActionListener(new ActionListener() {
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

        tableForm.getDeleteButton().addActionListener(new ActionListener() {
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
     * Vklada zaznam o novem stolu do databaze.
     *
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    private void insertAction() throws RemoteException, NotBoundException, FileNotFoundException {
        int i = isValidInput();
        if (i == 0) {
            int tableNumber = Integer.valueOf(tableForm.getTableNumberTextField().getText());
            int places = Integer.valueOf(tableForm.getPlacesTextField().getText());
            boolean isOK;
            if (row == -1){
                isOK = ServiceFacade.getInstance().createTable(tableNumber, places);
            } else {
                int tableId = (Integer) table.getModel().getValueAt(row, 0);
                isOK = ServiceFacade.getInstance().updateTable(tableId, tableNumber, places);
            }
            if (!isOK) {
                JOptionPane.showMessageDialog(this, "Záznam nemohl být uložen, protože záznam se stejným číslem stolu je již uložen.", "Stůl", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            tableForm.clearFields();
            refresh();
        } else if (i == 1) {
            JOptionPane.showMessageDialog(this, "Musí být uvedeno číslo vkládaného stolu.", "Stůl", JOptionPane.INFORMATION_MESSAGE);
        } else if (i == 2) {
            JOptionPane.showMessageDialog(this, "Musí být uveden počet míst k sezení pro daný stůl.", "Stůl", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Maze zaznam o stolu z databaze.
     *
     * @throws java.io.FileNotFoundException
     * @throws java.rmi.NotBoundException
     * @throws java.rmi.RemoteException
     */
    private void deleteAction() throws FileNotFoundException, NotBoundException, RemoteException{
        row = table.getSelectedRow();        
        int tableId = (Integer) table.getModel().getValueAt(row, 0);        
        boolean isOK = ServiceFacade.getInstance().deleteTable(tableId);
        if (!isOK){
            JOptionPane.showMessageDialog(this, "Záznam nebyl smazán.", "Stůl", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        refresh();
    }

    /**
     * Metoda kontrolujici spravnost vyplnenych udaju.
     *
     * @return Vraci index urcujici vstupni komponentu, ktera obsahuje
     * neplatny vstup; jinak vraci 0.
     */
    private int isValidInput() {
        if (!Validator.isPositiveDouble(tableForm.getTableNumberTextField())) return 1;
        if (!Validator.isPositiveDouble(tableForm.getPlacesTextField())) return 2;
        return 0;
    }

    /**
     * Metoda inicializujici tabulku.
     *
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    private void initTable() throws RemoteException, NotBoundException, FileNotFoundException {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                row = table.getSelectedRow();
                if (row == -1) {
                    tableForm.clearFields();
                    tableForm.getDeleteButton().setEnabled(false);
                    tableForm.getInsertButton().setText("Vložit záznam");
                    tableForm.getInsertButton().setToolTipText("Vložit nový záznam Menu");
                } else {
                    tableForm.getTableNumberTextField().setText(String.valueOf(table.getModel().getValueAt(row, 1)));
                    tableForm.getPlacesTextField().setText(String.valueOf(table.getModel().getValueAt(row, 2)));
                    tableForm.getDeleteButton().setEnabled(true);
                    tableForm.getInsertButton().setText("Aktualizovat záznam");
                    tableForm.getInsertButton().setToolTipText("Smazat vybraný záznam Menu");
                }
            }
        });
        refresh();
    }

    /**
     * Metoda aktualizujici tabulku.
     *
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    private void refresh() throws NotBoundException, RemoteException, FileNotFoundException {
        ResultTableModel rtm = new ResultTableModel();
        rtm.setColumnData(ResultTableModel.namesTable);
        Object[][] tables = ServiceFacade.getInstance().getTables();
        rtm.setTableData(tables);
        table.setModel(rtm);
    }
}
