/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PayOrderDialog.java
 *
 * Created on 22.4.2009, 12:47:32
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import service.ConfigParser;
import service.ServiceFacade;
import service.Validator;

/**
 * Trida vytvarejici dialog pro placeni uctu.
 *
 * @author Tomas Hnizdil
 */
public class PayOrderDialog extends AbstractDialog {

    private int accountId;
    private MainFrame parent;
    private ConfigParser config = null;
    private JTextField[] jTextFields = null;
    private JButton[][] jButtons = null;
    private Object[][] orders = null;
    private BillDialog billDialog = null;
    ImageIcon image = new ImageIcon("images/backgroundpane-bg.jpg");

    /**
     * Konstruktor tridy PayOrderDialog.
     *
     * @param parent
     * @param modal
     * @param accountId
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    public PayOrderDialog(MainFrame parent, boolean modal, int accountId) throws FileNotFoundException, NotBoundException, RemoteException {
        super(parent, modal);
        super.setTitle("Účet \""+ServiceFacade.getInstance().getAccountById(accountId).getName()+"\"");
        this.parent = parent;
        this.accountId = accountId;
        config = new ConfigParser();
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int y = (int) ((dim.getHeight() - 150) / 2);
        int x = (int) ((dim.getWidth() - 300) / 2);
        setLocation(x, y);
        refresh();
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
        double sum = 0;
        if (orders != null) {
            for (int i = 0; i < orders.length; i++) {
                JLabel polozka = new JLabel();
                double value = Integer.valueOf(orders[i][4].toString()) * Double.valueOf(orders[i][3].toString());
                polozka.setText(orders[i][4].toString() + "x " + orders[i][2].toString() + " " + value + ",- " + config.getMoney());
                polozka.setBounds(10, i * 20, 150, 20);
                sum += value;
                jPanelOrders.add(polozka);
            }
        }
        jLabelSum.setText("  Celkem: " + sum + ",- " + config.getMoney());
        jPanelBill.validate();
        jPanelBill.repaint();
    }

    /**
     * Metoda zajistuje placeni vybranych polozek.
     * Zjisti pocty jednotlivych placenych plozek a z nich vytvori List, ktery
     * preda jako parametr nove vytvorenemu dialogu billDialog
     *
     */
    protected void pay() {
        int i = isValidInput();
        switch (i) {
            case 0:
                try {
                    List<Object[]> billItems = new ArrayList<Object[]>();
                    for (int j = 0; j < orders.length; j++) {
                        int number = Integer.parseInt(jTextFields[j].getText());
                        if (number > 0) {
                            Object[] polozka = new Object[4];
                            polozka[0] = orders[j][0];
                            polozka[1] = orders[j][2];
                            polozka[2] = number * Double.valueOf(orders[j][3].toString());
                            polozka[3] = number;
                            billItems.add(polozka);
                        }
                    }
                    if (billItems.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Nejsou vybrány žádné položky k zaplacení.", "Nelze platit", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        billDialog = new BillDialog(parent, true, this, accountId, billItems);
                        billDialog.setLocation(575, 220);
                       // this.setVisible(false);
                        billDialog.setVisible(true);
                        
                    }
                } catch (FileNotFoundException fnfe) {
                    JOptionPane.showMessageDialog(this, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 1:
                JOptionPane.showMessageDialog(this, "Počet placených položek musí být celé nezáporné číslo.", "Placení účtu", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 2:
                JOptionPane.showMessageDialog(this, "Nelze zaplatit více položek, než bylo objednáno.", "Placení účtu", JOptionPane.INFORMATION_MESSAGE);
                break;
            default:
                break;
        }
    }

    /**
     * Metoda vytvari skupinu tlacitek (popisku a vstupnich poli) pro placeni
     * (pro kazdy typ polozku na uctu 1 jLabel a jTextField).
     *
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    protected void initButtonGroup() throws FileNotFoundException, NotBoundException, RemoteException {
        Font textFont = new Font("Verdana", Font.BOLD, 20);
        Font btnFont = new Font("Verdana", Font.PLAIN, 30);
        Font labelFont = new Font("Verdana", Font.BOLD, 17);
        Color color = new Color(255, 255, 255);

        if (orders == null) return;
        jPanel.removeAll();
        jTextFields = new JTextField[orders.length];
        jButtons = new JButton[orders.length][2];
        for (int i = 0; i < orders.length; i++) {
            final int j=i;
            JLabel order = new JLabel();
            order.setText(orders[i][2].toString());
            order.setForeground(color);
            order.setBackground(new Color(0, 0, 0));
            order.setHorizontalTextPosition(JLabel.LEFT);
            order.setBounds(0, i * 70, 100, 60);
            order.setFont(labelFont);
            order.setVisible(true);
            jTextFields[i] = new JTextField();
            jTextFields[i].setText("0");
            jTextFields[i].setBounds(300, i * 70, 50, 60);
            jTextFields[i].setFont(textFont);
            jTextFields[i].setBorder(null);
            jTextFields[i].setHorizontalAlignment(JTextField.CENTER);
            jButtons[i][0] = new JButton();
            jButtons[i][0].setText("-");
            jButtons[i][0].setFont(btnFont);
            jButtons[i][0].setFocusPainted(false);
            jButtons[i][0].setBounds(170, i*70, 120, 60);
            jButtons[i][0].addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Integer hodnota = Integer.parseInt(jTextFields[j].getText());
                    if (hodnota>0) {
                        hodnota--;
                        jTextFields[j].setText(hodnota.toString());
                    }
                }
            });
            jButtons[i][1] = new JButton();
            jButtons[i][1].setText("+");
            jButtons[i][1].setFont(btnFont);
            jButtons[i][1].setFocusPainted(false);
            jButtons[i][1].setBounds(360, i*70, 120, 60);
            jButtons[i][1].addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Integer hodnota = Integer.parseInt(jTextFields[j].getText());
                    hodnota++;
                    jTextFields[j].setText(hodnota.toString());
                }
            });
            jPanel.add(order);
            jPanel.add(jTextFields[i]);
            jPanel.add(jButtons[i][0]);
            jPanel.add(jButtons[i][1]);
        }

        jPanel.validate();
        jPanel.repaint();
    }

    /**
     * Metoda nejprve ziska vsechny nezaplacene polozky z tohoto uctu,
     * pak provadi aktualizaci uctu a aktualizaci tlacitek pro placeni.
     *
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    protected void refresh() throws RemoteException, NotBoundException, FileNotFoundException {
        orders = ServiceFacade.getInstance().getMenuItemsByAccount(accountId);
        initButtonGroup();
        refreshBill();
    }

    /**
     * Metoda kontrolujici spravnost vyplnenych udaju.
     *
     * @return Vraci index urcujici vstupni komponentu, ktera obsahuje
     * neplatny vstup.
     */
    protected int isValidInput() {
        for (int i = 0; i < jTextFields.length; i++) {
            if (!Validator.isPositiveInt(jTextFields[i])) {
                return 1;
            }
            if (!Validator.isGreaterInt(jTextFields[i], Integer.parseInt(orders[i][4].toString()))) {
                return 2;
            }
        }
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
        for (int i = 0; i < jTextFields.length; i++) {
            jTextFields[i].setText("0");
        }

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new BackroundPane();
        jPanel1 = new javax.swing.JPanel();
        jPanelBill = new BackgroundPanel();
        jPanelOrders = new javax.swing.JPanel();
        jLabelSum = new javax.swing.JLabel();
        jButtonBack = new javax.swing.JButton();
        jButtonClear = new javax.swing.JButton();
        jButtonPay = new javax.swing.JButton();
        jPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(1388, 567));

        jPanelBill.setOpaque(false);

        jPanelOrders.setAutoscrolls(true);
        jPanelOrders.setOpaque(false);

        javax.swing.GroupLayout jPanelOrdersLayout = new javax.swing.GroupLayout(jPanelOrders);
        jPanelOrders.setLayout(jPanelOrdersLayout);
        jPanelOrdersLayout.setHorizontalGroup(
            jPanelOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );
        jPanelOrdersLayout.setVerticalGroup(
            jPanelOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 395, Short.MAX_VALUE)
        );

        jLabelSum.setText("   CELKEM:");
        jLabelSum.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanelBillLayout = new javax.swing.GroupLayout(jPanelBill);
        jPanelBill.setLayout(jPanelBillLayout);
        jPanelBillLayout.setHorizontalGroup(
            jPanelBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBillLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanelBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBillLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabelSum, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelOrders, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanelBillLayout.setVerticalGroup(
            jPanelBillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBillLayout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(jPanelOrders, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSum, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );

        jButtonBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buttons/ko.png"))); // NOI18N
        jButtonBack.setText("  Zavřít");
        jButtonBack.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });

        jButtonClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buttons/left.png"))); // NOI18N
        jButtonClear.setText("  Reset");
        jButtonClear.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });

        jButtonPay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buttons/ok.png"))); // NOI18N
        jButtonPay.setText(" Zaplatit");
        jButtonPay.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPayActionPerformed(evt);
            }
        });

        jPanel.setOpaque(false);

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 565, Short.MAX_VALUE)
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 467, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanelBill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(163, 163, 163)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPay, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(128, 128, 128))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelBill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonPay, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        this.parent.setEnabledToolBar(true);
        dispose();
    }//GEN-LAST:event_jButtonBackActionPerformed

    private void jButtonPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPayActionPerformed
        pay();
    }//GEN-LAST:event_jButtonPayActionPerformed

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
        clearFields();
    }//GEN-LAST:event_jButtonClearActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonPay;
    private javax.swing.JLabel jLabelSum;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelBill;
    private javax.swing.JPanel jPanelOrders;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables


}
