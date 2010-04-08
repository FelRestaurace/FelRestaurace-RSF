/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.io.FileNotFoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import service.ServiceFacade;

/**
 * Abstraktni trida, ze ktere dedi klientske JPanel tridy.
 *
 * @author Jarda
 */
public abstract class AbstractForm extends JPanel {

    /**
     * Konstruktor tridy AbstractForm.
     */
    public AbstractForm() {
    }    

    /**
     * Metoda pro nastaveni datoveho modelu pro urceny JComboBox. String retezce
     * obsazene v poli namse budou nastaveny jako polozky JComboBoxu.
     *
     * @param names retezce, ktere budou nastaveny jako polozky daneho JComboBoxu
     * @param box JComboBox, pro ktery budou nastaveny polozky
     */
    protected void setComboBoxModel(String names[], JComboBox box){
        box.removeAllItems();
        if (names == null || names.length == 0) {
            return;
        }
        for (int i = 0; i < names.length; i++) {
            box.addItem(names[i]);
        }
        box.setSelectedIndex(0);
    }

    //ABSTRAKTNI METODY

    /**
     * Metoda provadi aktualizaci vsech comboBoxu a aktualizaci tabulky.
     * Zaroven prenastavuje statusBar.
     *
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    protected abstract void refresh() throws FileNotFoundException, NotBoundException, RemoteException;

    /**
     * Metoda kontrolujici spravnost vyplnenych udaju.
     *
     * @return Vraci index urcujici vstupni komponentu, ktera obsahuje
     * neplatny vstup.
     */
    protected abstract int isValidInput();

    /**
     * Metoda inicializujici tabulku.     
     */
    protected abstract void initTable();

    /**
     * Metoda aktualizuje tabulku.
     * 
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    protected abstract void refreshTable() throws FileNotFoundException, NotBoundException, RemoteException;

    /**
     * Metoda cisti vsechny vstupni formulare, formular pro datum nastavuje na
     * aktualni datum a u comboBoxu nastavuje aktualni vybranou polozku na
     * prvni polozku daneho comboBoxu.
     */
    protected abstract void clearFields();
    

}
