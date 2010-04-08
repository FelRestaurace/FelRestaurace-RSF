 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import hibernate.User;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import service.ConfigParser;

/**
 * Hlavni trida grafickeho uzivatelskeho rozhrani. Stara se o vytvareni ostatnich
 * trid (lazy inicialization), predavani rizeni temto tridam atd.
 *
 * @author Tomas Hnizdil
 */
public class MainFrame extends JFrame {

    private Container con = null;
    private JMenuBar menuBar = null;
    private ToolBar toolBar = new ToolBar();
    private JMenu menuFile = null;
    private JMenu menuBill = null;
    private JMenu menuOrder = null;
    private JMenu menuCustomer = null;
    private JMenu menuSettings = null;
    private JMenu menuHelp = null;
    private JMenuItem close = null;
    private JMenuItem accountList = null;
    private JMenuItem createAccount = null;
    private JMenuItem moveMenuItems = null;
    private JMenuItem payAccount = null;
    private JMenuItem createOrder = null;
    private JMenuItem createCustomer = null;
    private JMenuItem customerList = null;
    private JMenuItem setConnections = null;
    private JMenuItem about = null;
    private BackroundPane panel = null;
    private JScrollPane panelTable = null;
    private static AbstractForm form = null;
    private static ChooseAccountForm chooseAccountForm = null;
    private static CreateAccountForm createAccountForm = null;
    private static AccountForm accountForm = null;
    private static CreateCustomerForm createCustomerForm = null;
    private static CustomerForm customerForm = null;
    private static LogingDialog ld = null;
    private static SettingsDialog sd = null;
    private static StatusBar statusBar = new StatusBar();
    private static User loggedUser = null;
    final static int CREATE_ORDER = 1;
    final static int PAY_ORDER = 2;
    final static int MOVE_ORDER = 3;
    public static Bill openBill = null;
    public static javax.swing.JPanel PanelVyberu = new JPanel();

    /**
     * Konstruktor tridy MainFrame.
     */
    public MainFrame() {
        ld = new LogingDialog(this, true);
        ld.setLocation(100, 100);
        ld.setVisible(true);
        loggedUser = ld.getLoggedUser();
        if (loggedUser == null) {
            System.exit(0);
        }
        this.setTitle("Restaurace FEL - Pokladní modul, přihlášený uživatel: " + loggedUser.getUsername());
        initComponents();
    }

    public void setEnabledToolBar(boolean b) {
        if (b) {
            toolBar.setAllEnabled();
        } else {
            toolBar.setAllDisabled();
        }
    }

    /**
     * Metoda navraci referenci na instanci pro kterou byla zavolana.
     *
     * @return instance tridy JFrame
     */
    private JFrame getThis() {
        return this;
    }

    /**
     * Metoda pro ukonceni cele aplikace.
     */
    public void close() {
        System.exit(0);
    }

    private void refreshWindowLayout() {
        // con.removeAll();
        // con.validate();

        // con.remove(1);
        //panel.validate();
        //panel.repaint();



        con.add(toolBar, BorderLayout.NORTH);
        con.add(panel, BorderLayout.CENTER, 1);
        con.add(panel, BorderLayout.CENTER);
        con.add(statusBar, BorderLayout.SOUTH);

        setVisible(true);
    }

//-------------------------  ACTION METODY ------------------------------//
    /**
     * Akce vytvori novy ucet
     */
    private void createAccountAction() {
        try {
            if (createAccountForm == null) {
                createAccountForm = new CreateAccountForm(getThis(), statusBar);
            } else {
                createAccountForm.refresh();
            }
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // panel.removeAll();
        panel.getViewport().add(createAccountForm);
        con.removeAll();
        refreshWindowLayout();
    }

    private void accountListAction() {
        try {
            if (accountForm == null) {
                accountForm = new AccountForm(getThis(), statusBar);
            } else {
                accountForm.refresh();
            }
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
            return;
        }
        form = accountForm;

        
        panel.getViewport().add(accountForm);
        con.removeAll();
        con.add(statusBar, BorderLayout.NORTH);
        con.add(panel, BorderLayout.NORTH);
        panelTable.getViewport().add(accountForm.getTable());
        con.add(panelTable, BorderLayout.SOUTH);
        setVisible(true);
        
        refreshWindowLayout();

    }

    private void payAccountAction() {
        try {
//            if (chooseAccountForm == null) {
//                chooseAccountForm = new ChooseAccountForm(this, statusBar, loggedUser.getUserId(), PAY_ORDER, this);
//            } else {
//                chooseAccountForm.refresh();
//            }
            chooseAccountForm = new ChooseAccountForm(this, statusBar, loggedUser.getUserId(), PAY_ORDER, this);
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // panel.removeAll();
        
        panel.getViewport().add(chooseAccountForm);
        con.removeAll();
        panel.repaint();
        panel.validate();
        refreshWindowLayout();
    }

    private void createOrderAction() {
        try {
//            if (chooseAccountForm == null) {
//                chooseAccountForm = new ChooseAccountForm(getThis(), statusBar, loggedUser.getUserId(), CREATE_ORDER, this);
//            } else {
//                chooseAccountForm.refresh();
//            }
            chooseAccountForm = new ChooseAccountForm(getThis(), statusBar, loggedUser.getUserId(), CREATE_ORDER, this);

        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //  panel.removeAll();
        
        panel.getViewport().add(chooseAccountForm);
        con.removeAll();
        panel.repaint();
        panel.validate();
        refreshWindowLayout();
    }

    public void moveMenuItemAction() {
        try {
//                    if (chooseAccountForm == null) {
////                        chooseAccountForm = new ChooseAccountForm(getThis(), statusBar, loggedUser.getUserId(), MOVE_ORDER,this);
//                    } else {
//                        chooseAccountForm.refresh();
//                    }
            chooseAccountForm = new ChooseAccountForm(getThis(), statusBar, loggedUser.getUserId(), MOVE_ORDER, this);
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
            return;
        }
        panel.getViewport().add(chooseAccountForm);
        con.removeAll();
        panel.repaint();
        panel.validate();
        refreshWindowLayout();
    }

    public void createCustomerAction() {
        try {
            if (createCustomerForm == null) {
                createCustomerForm = new CreateCustomerForm(getThis(), statusBar);
            } else {
                createCustomerForm.refresh();
            }
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
            return;
        }
        panel.getViewport().add(createCustomerForm);
        con.removeAll();
        refreshWindowLayout();
        
    }

    /**
     * Akce ukonci beh programu
     */
    private void closeAction() {
        //  int x = JOptionPane.showConfirmDialog(null, "Opravdu chcete ukončit běh programu?", "Konec", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        // if (x == 0) {
        close();
        // }
    }

    /**
     * Tato metoda se stara o inicializaci a vytvoreni hlavniho menu (instance
     * tridy JMenuBar). Pro jednotlive polozky menu (JMenuItem) jsou zde definovane
     * posluchace (ActionListener).
     */
    private void createMenu() {
        menuBar = new JMenuBar();
        menuFile = new JMenu("Soubor");
        menuBill = new JMenu("Účet");
        menuOrder = new JMenu("Objednávka");
        menuCustomer = new JMenu("Zákazník");
        menuSettings = new JMenu("Nastavení");
        menuHelp = new JMenu("Nápověda");

        close = new JMenuItem("Konec");//OK
        accountList = new JMenuItem("Přehled účtů");//CON
        createAccount = new JMenuItem("Vytvořit nový účet");//OK
        moveMenuItems = new JMenuItem("Přesunout položky z účtu na účet"); //CON
        payAccount = new JMenuItem("Zaplatit účet");//OK
        createOrder = new JMenuItem("Vytvořit novou objednávku");//CON
        createCustomer = new JMenuItem("Přidat nového zákazníka");//CON
        customerList = new JMenuItem("Přehled zákazníků");
        setConnections = new JMenuItem("Nastavení klienta");
        about = new JMenuItem("O aplikaci");

//        close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_MASK));
//        close.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//                int x = JOptionPane.showConfirmDialog(null, "Opravdu chcete ukončit běh programu?", "Konec", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//                if (x == 0) {
//                    close();
//                }
//            }
//        });
//
//
//
//        accountList.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
//        accountList.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    if (accountForm == null) {
//                        accountForm = new AccountForm(getThis(), statusBar);
//                    } else {
//                        accountForm.refresh();
//                    }
//                } catch (FileNotFoundException fnfe) {
//                    JOptionPane.showMessageDialog(null, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
//                    return;
//                } catch (Exception ex) {
//                    JOptionPane.showMessageDialog(null, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//                form = accountForm;
//
//                con.removeAll();
//                panel.getViewport().add(accountForm);
//                con.add(panel, BorderLayout.NORTH);
//
//                panelTable.getViewport().add(accountForm.getTable());
//                con.add(panelTable, BorderLayout.CENTER);
//
//                con.add(statusBar, BorderLayout.SOUTH);
//                setVisible(true);
//            }
//        });
//
//        createAccount.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
//        createAccount.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//                createAccountAction();
//            }
//        });
//
//        moveMenuItems.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
//        moveMenuItems.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    if (chooseAccountForm == null) {
////                        chooseAccountForm = new ChooseAccountForm(getThis(), statusBar, loggedUser.getUserId(), MOVE_ORDER,this);
//                    } else {
//                        chooseAccountForm.refresh();
//                    }
//                } catch (FileNotFoundException fnfe) {
//                    JOptionPane.showMessageDialog(null, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
//                    return;
//                } catch (Exception ex) {
//                    JOptionPane.showMessageDialog(null, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//                form = chooseAccountForm;
//
//                con.removeAll();
//                panel.getViewport().add(chooseAccountForm);
//                con.add(panel, BorderLayout.NORTH);
//
//                con.add(statusBar, BorderLayout.SOUTH);
//                setVisible(true);
//            }
//        });
//
//        payAccount.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
//        payAccount.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    if (chooseAccountForm == null) {
////                        chooseAccountForm = new ChooseAccountForm(getThis(), statusBar, loggedUser.getUserId(), PAY_ORDER,this);
//                    } else {
//                        chooseAccountForm.refresh();
//                    }
//                } catch (FileNotFoundException fnfe) {
//                    JOptionPane.showMessageDialog(null, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
//                    return;
//                } catch (Exception ex) {
//                    JOptionPane.showMessageDialog(null, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//                form = chooseAccountForm;
//
//                con.removeAll();
//                panel.getViewport().add(chooseAccountForm);
//                con.add(panel, BorderLayout.NORTH);
//
//                con.add(statusBar, BorderLayout.SOUTH);
//                setVisible(true);
//            }
//        });
//
//        createOrder.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
//        createOrder.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    if (chooseAccountForm == null) {
//                        //        chooseAccountForm = new ChooseAccountForm(getThis(), statusBar, loggedUser.getUserId(), CREATE_ORDER);
//                    } else {
//                        chooseAccountForm.refresh();
//                    }
//                } catch (FileNotFoundException fnfe) {
//                    JOptionPane.showMessageDialog(null, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
//                    return;
//                } catch (Exception ex) {
//                    JOptionPane.showMessageDialog(null, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//                form = chooseAccountForm;
//
//                con.removeAll();
//                panel.getViewport().add(chooseAccountForm);
//                con.add(panel, BorderLayout.NORTH);
//
//                con.add(statusBar, BorderLayout.SOUTH);
//                setVisible(true);
//            }
//        });
//
//        customerList.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK));
//        customerList.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    if (customerForm == null) {
//                        customerForm = new CustomerForm(getThis(), statusBar);
//                    } else {
//                        customerForm.refresh();
//                    }
//                } catch (FileNotFoundException fnfe) {
//                    JOptionPane.showMessageDialog(null, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
//                    return;
//                } catch (Exception ex) {
//                    JOptionPane.showMessageDialog(null, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//                form = customerForm;
//
//                con.removeAll();
//                panel.getViewport().add(customerForm);
//                con.add(panel, BorderLayout.NORTH);
//
//                con.add(statusBar, BorderLayout.SOUTH);
//                setVisible(true);
//            }
//        });
//
//        createCustomer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK));
//        createCustomer.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    if (createCustomerForm == null) {
//                        createCustomerForm = new CreateCustomerForm(getThis(), statusBar);
//                    } else {
//                        createCustomerForm.refresh();
//                    }
//                } catch (FileNotFoundException fnfe) {
//                    JOptionPane.showMessageDialog(null, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
//                    return;
//                } catch (Exception ex) {
//                    JOptionPane.showMessageDialog(null, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//                form = createCustomerForm;
//
//                con.removeAll();
//                panel.getViewport().add(createCustomerForm);
//                con.add(panel, BorderLayout.NORTH);
//
//                con.add(statusBar, BorderLayout.SOUTH);
//                setVisible(true);
//            }
//        });
//
//        setConnections.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
//        setConnections.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    sd = new SettingsDialog(getThis(), true);
//                    sd.setVisible(true);
//                    /*if (createOrderForm != null) {
//                    createOrderForm.refreshSettings();
//                    }*/
//                } catch (FileNotFoundException fnfe) {
//                    JOptionPane.showMessageDialog(null, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
//                    return;
//                } catch (Exception ex) {
//                    JOptionPane.showMessageDialog(null, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//            }
//        });
//
//        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
//        about.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(getThis(), "Aplikace RestauraceFEL - Pokladní modul.", "O aplikaci", JOptionPane.INFORMATION_MESSAGE);
//            }
//        });
//
//
//        menuFile.add(close);
//        menuBill.add(accountList);
//        menuBill.add(createAccount);
//        menuBill.add(moveMenuItems);
//        menuBill.add(payAccount);
//        menuOrder.add(createOrder);
//        menuCustomer.add(customerList);
//        menuCustomer.add(createCustomer);
//        menuSettings.add(setConnections);
//        menuHelp.add(about);
//
//        menuBar.add(menuFile);
//        menuBar.add(menuBill);
//        menuBar.add(menuOrder);
//        menuBar.add(menuCustomer);
//        menuBar.add(menuSettings);
//        menuBar.add(menuHelp);
//
//        this.setJMenuBar(menuBar);
    }

    private void createToolBar() {
        
        toolBar.createAccount.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                createAccountAction();
            }
        });

        toolBar.close.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                closeAction();
            }
        });

        toolBar.accountList.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                accountListAction();
            }
        });

        toolBar.createOrder.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                createOrderAction();
            }
        });

        toolBar.payAccount.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                payAccountAction();
            }
        });

        toolBar.moveMenuItems.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                moveMenuItemAction();
            }
        });

        toolBar.createCustomer.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                createCustomerAction();
            }
        });

        refreshWindowLayout();
    }

    /**
     * Metoda inicializuje okno a jeho komponenty. Vola metodu pro vytvoreni
     * hlavniho menu.
     */
    private void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = (int) dim.getWidth();
        int h = (int) dim.getHeight();
        int y = (int) ((dim.getHeight() - h) / 2);
        int x = (int) ((dim.getWidth() - w) / 2);
        this.setBounds(x, y, w, h);
        this.setVisible(true);
        this.setMinimumSize(new Dimension(400, 400));

        con = this.getContentPane();
        con.setLayout(new BorderLayout());

        panel = new BackroundPane();
        panelTable = new JScrollPane();

        this.createToolBar();


        //  this.createMenu();

    }

    /**
     * Metoda navraci instanci tridy User prave prihlaseneho uzivatele.
     *
     * @return prihlaseny uzivatel
     */
    public static User getLoggedUser() {
        return loggedUser;
    }
}
