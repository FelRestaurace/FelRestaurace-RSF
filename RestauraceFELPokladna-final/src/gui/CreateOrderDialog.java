/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CreateOrderDialog.java
 *
 * Created on 22.4.2009, 12:47:32
 */
package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import service.ConfigParser;
import service.ServiceFacade;
import service.Validator;

/**
 * Trida vytvarejici dialog vytvareni novych objednavek.
 *
 * @author Tomas Hnizdil
 */
public class CreateOrderDialog extends AbstractDialog {

    private int accountId;
    private int loggedUserId;
    private ConfigParser config = null;

    /**
     * Konstruktor tridy CreateOrderDialog.
     *
     * @param parent
     * @param bar
     * @param accountId Id uctu, na ktery se objednava
     * @param loggedUserId Id prihlaseneho uzivatele, ktere se predava jako id prijemce objednavky
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    public CreateOrderDialog(JFrame parent, boolean modal, int accountId, int loggedUserId) throws FileNotFoundException, NotBoundException, RemoteException {
        super(parent, modal);
        super.setTitle("Účet \""+ServiceFacade.getInstance().getAccountById(accountId).getName()+"\"");
        this.accountId = accountId;
        this.loggedUserId = loggedUserId;
        config = new ConfigParser();
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int y = (int) ((dim.getHeight() - 150) / 2);
        int x = (int) ((dim.getWidth() - 300) / 2);
        setLocation(x, y);
        setComboBoxModel((ServiceFacade.getInstance().getMenuNames()), jComboBoxMenu);
        clearFields();
        refresh();
        jComboBoxMenu.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                try {
                    initButtonGroup(jComboBoxMenu);
                } catch (Exception ex) {}
            }
        });
    }

    /**
     * Metoda provadi aktualizaci uctu zobrazeneho v prave casti dialogu.
     *
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    protected void refreshBill() throws FileNotFoundException, NotBoundException, RemoteException {
        jPanelOrders.removeAll();
        Object[][] orders = ServiceFacade.getInstance().getMenuItemsByAccount(accountId);
        double sum = 0;
        if (orders != null) {
            for (int i = 0; i < orders.length; i++) {
                JLabel polozka = new JLabel();
                polozka.setText(orders[i][4].toString()+"x "+orders[i][2].toString()+" "+orders[i][3].toString()+",- "+config.getMoney());
                polozka.setBounds(10, i * 20, 150, 20);
                sum+=Double.valueOf(orders[i][4].toString())*Double.valueOf(orders[i][3].toString());
                jPanelOrders.add(polozka);
            }
        }
        jLabelSum.setText("Celkem: "+sum+",- "+config.getMoney());
        jPanelBill.validate();
        jPanelBill.repaint();
    }

    /**
     * Metoda vytvari novou objednavku polozky menu s id menuItemId na ucet s id
     * accountId.
     *
     * @param menuItemId
     */
    protected void orderMenuItem(int menuItemId) {
        int i = isValidInput();
        switch (i) {
            case 0:
                boolean isOK;
                try {
                    isOK = ServiceFacade.getInstance().createOrder(0, new Date(), accountId, loggedUserId);
                    if (!isOK) {
                        JOptionPane.showMessageDialog(this, "Objednávka nemohla být přijata.", "Objednávka", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    Object [][] orders = ServiceFacade.getInstance().getOrdersByAccount(accountId);
                    isOK = ServiceFacade.getInstance().createOrderMenuItem(menuItemId, (Integer)orders[orders.length-1][0]);
                    if (!isOK) {
                        JOptionPane.showMessageDialog(this, "Objednávka nemohla být přijata.", "Objednávka", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                } catch (FileNotFoundException fnfe) {
                    JOptionPane.showMessageDialog(this, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
                }
                break;
            default:
                break;
    }
    }


    /**
     * Metoda vytvari skupinu tlacitek pro objednavani na zaklade vybraneho menu
     * v jComboBoxMenu (pro kazdou polozku z menu 1 jButton).
     *
     * @param jComboBoxMenu
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    protected void initButtonGroup(JComboBox jComboBoxMenu) throws FileNotFoundException, NotBoundException, RemoteException {
        jPanel.removeAll();
        if (jComboBoxMenu.getSelectedIndex() == -1) {
            jComboBoxMenu.setSelectedIndex(1);
        }
        int menuId = ServiceFacade.getInstance().getMenuByName((String) jComboBoxMenu.getSelectedItem()).getMenuId();
        final Object[][] menus = ServiceFacade.getInstance().getMenuItemsByMenu(menuId);
        JButton[] buttonArray = new JButton[menus.length];
        int pocetSloupcu = jPanel.getWidth() / 120;
        // kvuli eliminovani divisionByZeroException
        if (pocetSloupcu == 0) pocetSloupcu = 1;
        int j = 0;
        for (int i = 0; i < menus.length; i++) {
            final int k = i;
            buttonArray[i] = new JButton();
            buttonArray[i].setText((String) menus[i][2]);
            buttonArray[i].setVisible(true);
            buttonArray[i].addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    orderMenuItem((Integer)menus[k][0]);
                    try {
                        refresh();
                    } catch (Exception ex) {
                        Logger.getLogger(CreateOrderDialog.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            jPanel.add(buttonArray[i]);
            buttonArray[i].setBounds((i % pocetSloupcu) * 120, j * 70, 100, 50);
            if (i % pocetSloupcu == pocetSloupcu - 1) {
                j++;
            }
        }
        jPanel.validate();
        jPanel.repaint();
    }

    /**
     * Metoda provadi aktualizaci skupiny tlacitek pro objednavani a aktualizaci
     * uctu.
     *
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    protected void refresh() throws RemoteException, NotBoundException, FileNotFoundException {
        initButtonGroup(jComboBoxMenu);
        refreshBill();
    }

    /**
     * Metoda kontrolujici spravnost vyplnenych udaju.
     *
     * @return Vraci index urcujici vstupni komponentu, ktera obsahuje
     * neplatny vstup.
     */
    protected int isValidInput() {
        return 0;
    }

    /**
     * Metoda inicializujici tabulku.
     */
    protected void initTable() {
    }

    /**
     * Metoda cisti vsechny vstupni formulare, formular pro datum nastavuje na
     * aktualni datum a u comboBoxu nastavuje aktualni vybranou polozku na
     * prvni polozku daneho comboBoxu.
     */
    protected void clearFields() {
        Validator.clearComboBox(jComboBoxMenu);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBoxMenu = new javax.swing.JComboBox();
        jButtonBack = new javax.swing.JButton();
        jLabelMenu = new javax.swing.JLabel();
        jPanelBill = new javax.swing.JPanel();
        jLabelSum = new javax.swing.JLabel();
        jPanelOrders = new javax.swing.JPanel();
        jPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jButtonBack.setText("Zavřít");
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });

        jLabelMenu.setText("Menu:");

        jPanelBill.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabelSum.setText("Celkem:");
        jLabelSum.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanelOrdersLayout = new javax.swing.GroupLayout(jPanelOrders);
        jPanelOrders.setLayout(jPanelOrdersLayout);
        jPanelOrdersLayout.setHorizontalGroup(
            jPanelOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 173, Short.MAX_VALUE)
        );
        jPanelOrdersLayout.setVerticalGroup(
            jPanelOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 301, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelBillLayout = new javax.swing.GroupLayout(jPanelBill);
        jPanelBill.setLayout(jPanelBillLayout);
        jPanelBillLayout.setHorizontalGroup(
            jPanelBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelSum, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
            .addComponent(jPanelOrders, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelBillLayout.setVerticalGroup(
            jPanelBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBillLayout.createSequentialGroup()
                .addComponent(jPanelOrders, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSum, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 331, Short.MAX_VALUE)
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 318, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabelMenu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(244, Short.MAX_VALUE)
                .addComponent(jButtonBack)
                .addGap(229, 229, 229))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelBill, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelMenu))
                        .addGap(11, 11, 11)
                        .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jButtonBack)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonBackActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBack;
    private javax.swing.JComboBox jComboBoxMenu;
    private javax.swing.JLabel jLabelMenu;
    private javax.swing.JLabel jLabelSum;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanelBill;
    private javax.swing.JPanel jPanelOrders;
    // End of variables declaration//GEN-END:variables
}
