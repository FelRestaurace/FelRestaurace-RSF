/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ChooseAccountForm.java
 *
 * Created on 7.11.2008, 20:34:04
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import service.ConfigParser;
import service.ServiceFacade;

/**
 * Trida reprezentujici GUI formular pro vyber uctu pro novou objednavku, nebo placeni.
 *
 * @author Tomas Hnizdil
 */
public class ChooseAccountForm extends AbstractForm {

    private JTable table = null;
    private JFrame parent = null;
    private MainFrame main = null;
    private StatusBar statusBar = null;
    private int loggedUserId = 1;
    private CreateOrderDialog createOrderDialog = null;
    private PayOrderDialog payOrderDialog = null;
    private MoveOrderDialog moveOrderDialog = null;
    private ConfigParser config = null;
    private int action;
    ImageIcon image = new ImageIcon("images/backgroundpane-bg.jpg");
    private ImageIcon money = new ImageIcon("images/money.png");
    private ImageIcon pencil = new ImageIcon("images/pencil.png");
    private ImageIcon move = new ImageIcon("images/move.png");
    private ImageIcon icon = null;


    /**
     * Konstruktor tridy ChooseAccountForm
     *
     * @param parent instance tridy JFrame jenz vytvorila tento formular
     * @param bar statusBar, do ktereho budou vypisovany popisky
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    public ChooseAccountForm(JFrame parent, StatusBar bar, int loggedUserId, int action, MainFrame main) throws FileNotFoundException, RemoteException, NotBoundException {
        this.parent = parent;
        this.main = main;

       //parent.set
        this.statusBar = bar;
        this.loggedUserId = loggedUserId;
        this.action = action;
        initComponents();
        switch (action) {
            case 1:
                jLabelTitle.setText("Vytvořit novou objednávku");
                this.icon = pencil;
                this.repaint();
                break;
            case 2:
                jLabelTitle.setText("Zaplatit účet");
                this.icon = money;
                this.repaint();
                break;
            case 3:
                jLabelTitle.setText("Přesunout položky z účtu");
                this.icon = move;
                this.repaint();
                break;
            default:
                break;
        }
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
        initButtonGroup();
        statusBar.setMessage("Tento formulář slouží k vybrání účtu.");
    }

    /**
     * Metoda kontrolujici spravnost vyplnenych udaju.
     *
     * @return Vraci index urcujici vstupni komponentu, ktera obsahuje
     * neplatny vstup.
     */
    protected int isValidInput() {
        //if (!Validator.isSelectedItem(jComboBoxMaterial)) return 1;
        //if (!Validator.isPositiveDouble(jTextFieldQuantity)) return 2;
        //if (!Validator.isPositiveDouble(jTextFieldPrice)) return 3;
        //if (Validator.isValidDate(jTextFieldDate.getText()) == null) return 4;
        //if (!Validator.isSelectedItem(jComboBoxUser)) return 5;
        return 0;
    }

    /**
     * Metoda inicializujici tabulku.     
     */
    protected void initTable() {
    }

    /**
     * Metoda vytvari novy dialog pro placeni uctu.
     *
     * @param accountId Id uctu, na ktery se bude objednavat nova polozka
     *
     */
    protected void payOrder(int accountId) {
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int y = (int) ((dim.getHeight() - 150) / 2);
        int x = (int) (dim.getWidth());
        this.main.setEnabledToolBar(false);
        try {
            payOrderDialog = new PayOrderDialog(this.main, false, accountId);
            //payOrderDialog.setLocation(4, 193);
            payOrderDialog.setBounds(5, 193, x-11, 550);
            payOrderDialog.setVisible(true);
            refresh();
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(this, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metoda vytvari novy dialog pro novou objednavku.
     *
     * @param accountId Id uctu, na ktery se bude objednavat nova polozka
     *
     */
    protected void createOrder(int accountId) {
        try {
            createOrderDialog = new CreateOrderDialog(parent, false, accountId, loggedUserId);
            createOrderDialog.setLocation(20, 20);
            createOrderDialog.setVisible(true);
            refresh();
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(this, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metoda vytvari novy dialog pro novou objednavku.
     *
     * @param accountId Id uctu, na ktery se bude objednavat nova polozka
     *
     */
    protected void moveOrder(int accountId) {
        try {
            moveOrderDialog = new MoveOrderDialog(main, false, accountId);
             Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int y = (int) ((dim.getHeight() - 150) / 2);
        int x = (int) (dim.getWidth());
            moveOrderDialog.setBounds(5, 193, x-11, 550);
            moveOrderDialog.setVisible(true);
            refresh();
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(this, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metoda inicializuje pole tlacitek jButton (1 pro kazdy ucet) pro vyber uctu.
     *
     * @throws FileNotFoundException
     * @throws NotBoundException
     * @throws RemoteException
     *
     */
    public void initButtonGroup() throws FileNotFoundException, NotBoundException, RemoteException {
        // pozdeji zmenit na getAccountsByAccountStatusType(nezaplaceno|neuzavreno)
        Border border = new LineBorder(Color.BLACK, 20);
        Object[][] menus = ServiceFacade.getInstance().getAccounts();
        JButton[] buttonArray = new JButton[menus.length];
        jPanel.removeAll();
        jPanel.validate();
        int pocetSloupcu = 8;
        // kvuli DivisionByZeroException
        if (pocetSloupcu == 0) {
            pocetSloupcu = 8;
        }
        int j = 0;
        for (int i = 0; i < menus.length; i++) {
            buttonArray[i] = new JButton();
            buttonArray[i].setText(menus[i][1].toString());
            buttonArray[i].setVisible(true);
            final int accountId = Integer.parseInt(menus[i][0].toString());
            buttonArray[i].addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    switch (action) {
                        case 1:
                            createOrder(accountId);
                            break;
                        case 2:
                            payOrder(accountId);
                            break;
                        case 3:
                            moveOrder(accountId);
                            break;
                        default:
                            break;
                    }
                }
            });
            jPanel.add(
                    buttonArray[i]);
            jPanel.validate();
            buttonArray[i].setBounds((i % pocetSloupcu) * 150, j * 95, 140, 80);
            buttonArray[i].setIcon(icon);
            buttonArray[i].setHorizontalAlignment(SwingConstants.LEFT);
            if (i % pocetSloupcu == pocetSloupcu - 1) {
                j++;
            }
        }
    }

    @Override
    /**
     * Metoda aktualizuje tabulku.
     *
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.io.FileNotFoundException
     */
    protected void refreshTable() throws FileNotFoundException, NotBoundException, RemoteException {
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
     * Metoda cisti vsechny vstupni formulare, formular pro datum nastavuje na
     * aktualni datum a u comboBoxu nastavuje aktualni vybranou polozku na
     * prvni polozku daneho comboBoxu.
     */
    protected void clearFields() {
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        jLabelTitle = new javax.swing.JLabel();

        setToolTipText("Formulář pro evidování skladových příjmů.");

        jPanel.setOpaque(false);

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 677, Short.MAX_VALUE)
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 433, Short.MAX_VALUE)
        );

        jLabelTitle.setBackground(new java.awt.Color(255, 255, 255));
        jLabelTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitle.setText("jLabel1");
        jLabelTitle.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JPanel jPanel;
    // End of variables declaration//GEN-END:variables

  @Override
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    if (image != null)
      g.drawImage(image.getImage(), 0,0,this);
  }

}
