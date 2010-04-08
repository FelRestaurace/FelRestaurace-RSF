/*d
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.io.FileNotFoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import service.ServiceFacade;

/**
 * Abstraktni trida, ze ktere dedi klientske JDialog tridy.
 *
 * @author Jarda
 */
public abstract class AbstractDialog extends JDialog {

    /**
     * Konstruktor tridy AbstractDialog.
     */
    public AbstractDialog(JFrame parent, boolean modal) {
        super(parent, modal);
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

    //ABSTRACT methods

    /**
     * Metoda inicializuje komponenty na danem JDialogu.
     *
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    //protected abstract void initComponents() throws RemoteException, NotBoundException, FileNotFoundException;

    /**
     * Metoda kontrolujici spravnost vstupupnich poli na formulari.
     *
     * @return Vraci index urcujici vstupni komponentu, ktera obsahuje
     * neplatny vstup.
     */
    protected abstract int isValidInput();

    /**
     * Metoda inicializuje tabulku na danem JDialogu.
     *
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    protected abstract void initTable() throws RemoteException, NotBoundException, FileNotFoundException;

    /**
     * Metoda provadi aktualizaci vsech comboBoxu a aktualizaci tabulky na danem
     * JDialogu.
     *
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    protected abstract void refresh() throws RemoteException, NotBoundException, FileNotFoundException;
}
