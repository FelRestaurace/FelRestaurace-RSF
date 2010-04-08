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
import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import service.Backup;
import service.ConfigParser;

/**
 * Hlavni trida grafickeho uzivatelskeho rozhrani. Stara se o vytvareni ostatnich
 * trid (lazy inicialization), predavani rizeni temto tridam atd.
 *
 * @author Jarda
 */
public class MainFrame extends JFrame {

    private Container con = null;
    private JMenuBar menuBar = null;
    private JMenu menuFile = null;
    private JMenu menuStore = null;
    private JMenu menuEmployers = null;
    private JMenu menuMenu = null;
    private JMenu menuSettings = null;
    private JMenu zrcadlo = null;
    private JMenu zalohovani = null;
    private JMenu menuHelp = null;
    private JMenuItem close = null;
    private JMenuItem insertEmp = null;
    private JMenuItem uzaverka = null;
    private JMenuItem uzaverkaPrehled = null;
    private JMenuItem changePasswd = null;
    private JMenuItem newMaterial = null;
    private JMenuItem insertMat = null;
    private JMenuItem exportMat = null;
    private JMenuItem deprecMat = null;
    private JMenuItem matTypes = null;
    private JMenuItem reasonTypes = null;
    private JMenuItem createMenu = null;
    private JMenuItem createMenuItem = null;
    private JMenuItem createTable = null;
    private JMenuItem setConnections = null;
    private JMenuItem about = null;
    private JMenuItem vytvoritZalohu = null;
    private JScrollPane panel = null;
    private JScrollPane panelTable = null;
    private static AbstractForm form = null;
    private static MaterialForm materialForm = null;
    private static UzaverkaForm uzaverkaForm = null;
    private static UzaverkaList uzaverkaFormList = null;
    private static IncomeForm insertMaterialForm = null;
    private static ExpenditureForm expenditureForm = null;
    private static DepreciationForm depreciationForm = null;
    private static UserForm userForm = null;
    private static MenuItemForm menuItemForm = null;
    private static MenuForm menuForm = null;
    private static MaterialTypeDialog mtd = null;
    private static ReasonTypeDialog rtd = null;
    private static TableDialog td = null;
    private static LogingDialog ld = null;
    private static SettingsDialog sd = null;
    private static StatusBar statusBar = new StatusBar();
    private static User loggedUser = null;

    /**
     * Konstruktor tridy MainFrame.
     */
    public MainFrame() {
        ld = new LogingDialog(this, true);
        ld.setVisible(true);
        loggedUser = ld.getLoggedUser();
        if (loggedUser == null) {
            System.exit(0);
        }
        this.setTitle("Restaurace FEL - Manažerský modul, přihlášený uživatel: " + loggedUser.getUsername());
        initComponents();
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

    /**
     * Tato metoda se stara o inicializaci a vytvoreni hlavniho menu (instance
     * tridy JMenuBar). Pro jednotlive polozky menu (JMenuItem) jsou zde definovane
     * posluchace (ActionListener).
     */
    private void createMenu() {
        menuBar = new JMenuBar();
        menuFile = new JMenu("Soubory");
        menuStore = new JMenu("Sklad");
        menuEmployers = new JMenu("Zaměstnanci");
        menuMenu = new JMenu("Menu");
        menuSettings = new JMenu("Nastavení");
        zrcadlo = new JMenu("Zrcadlo");
        menuHelp = new JMenu("Nápověda");
        zalohovani = new JMenu("Zálohování");

        close = new JMenuItem("Konec aplikace");
        insertEmp = new JMenuItem("Evidence zaměstnanců");
        changePasswd = new JMenuItem("Změnit heslo přihlášeného uživatele");
        newMaterial = new JMenuItem("Skladové záznamy");
        insertMat = new JMenuItem("Příjem surovin na sklad");
        exportMat = new JMenuItem("Výdej surovin ze skladu");
        deprecMat = new JMenuItem("Odpis surovin ze skladu");
        matTypes = new JMenuItem("Druhy surovin");
        reasonTypes = new JMenuItem("Důvody odpisů surovin");
        createMenu = new JMenuItem("Evidence menu");
        createMenuItem = new JMenuItem("Evidence položek menu");
        createTable = new JMenuItem("Evidence stolů");
        setConnections = new JMenuItem("Nastavení klienta");
        uzaverka = new JMenuItem("Udělat uzávěrku");
        uzaverkaPrehled = new JMenuItem("Přehled uzávěrek");
        vytvoritZalohu = new JMenuItem("Vytvořit zálohu dat");
        about = new JMenuItem("O aplikaci");

        close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_MASK));
        close.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int x = JOptionPane.showConfirmDialog(null, "Opravdu chcete ukončit běh programu?", "Konec", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (x == 0) {
                    close();
                }
            }
        });

        insertEmp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
        insertEmp.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    if (userForm == null) {
                        userForm = new UserForm(getThis(), statusBar);
                    } else {
                        userForm.refresh();
                    }
                } catch (FileNotFoundException fnfe) {
                    JOptionPane.showMessageDialog(null, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                form = userForm;

                con.removeAll();
                panel.getViewport().add(userForm);
                con.add(panel, BorderLayout.NORTH);

                panelTable.getViewport().add(userForm.getTable());
                con.add(panelTable, BorderLayout.CENTER);

                con.add(statusBar, BorderLayout.SOUTH);
                setVisible(true);
            }
        });

        changePasswd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        changePasswd.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    ChangePasswordDialog chpd = new ChangePasswordDialog(getThis(), true, ld.getLoggedUser().getUserId());
                    chpd.setVisible(true);
                    if (form != null) {
                        form.refresh();
                    }
                } catch (FileNotFoundException fnfe) {
                    JOptionPane.showMessageDialog(null, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });

        newMaterial.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        newMaterial.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    if (materialForm == null) {
                        materialForm = new MaterialForm(getThis(), statusBar);
                    } else {
                        materialForm.refresh();
                    }
                } catch (FileNotFoundException fnfe) {
                    JOptionPane.showMessageDialog(null, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                form = materialForm;

                con.removeAll();
                panel.getViewport().add(materialForm);
                con.add(panel, BorderLayout.NORTH);


                panelTable.getViewport().add(materialForm.getTable());
                con.add(panelTable, BorderLayout.CENTER);

                con.add(statusBar, BorderLayout.SOUTH);
                setVisible(true);
            }
        });

        insertMat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
        insertMat.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    if (insertMaterialForm == null) {
                        insertMaterialForm = new IncomeForm(getThis(), statusBar);
                    } else {
                        insertMaterialForm.refresh();
                    }
                } catch (FileNotFoundException fnfe) {
                    JOptionPane.showMessageDialog(null, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                form = insertMaterialForm;

                con.removeAll();
                panel.getViewport().add(insertMaterialForm);
                con.add(panel, BorderLayout.NORTH);

                panelTable.getViewport().add(insertMaterialForm.getTable());
                con.add(panelTable, BorderLayout.CENTER);

                con.add(statusBar, BorderLayout.SOUTH);
                setVisible(true);
            }
        });

        exportMat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
        exportMat.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    if (expenditureForm == null) {
                        expenditureForm = new ExpenditureForm(getThis(), statusBar);
                    } else {
                        expenditureForm.refresh();
                    }
                } catch (FileNotFoundException fnfe) {
                    JOptionPane.showMessageDialog(null, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                form = expenditureForm;

                con.removeAll();
                panel.getViewport().add(expenditureForm);
                con.add(panel, BorderLayout.NORTH);

                panelTable.getViewport().add(expenditureForm.getTable());
                con.add(panelTable, BorderLayout.CENTER);

                con.add(statusBar, BorderLayout.SOUTH);
                setVisible(true);
            }
        });

        deprecMat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        deprecMat.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    if (depreciationForm == null) {
                        depreciationForm = new DepreciationForm(getThis(), statusBar);
                    } else {
                        depreciationForm.refresh();
                    }
                } catch (FileNotFoundException fnfe) {
                    JOptionPane.showMessageDialog(null, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                form = depreciationForm;

                con.removeAll();
                panel.getViewport().add(depreciationForm);
                con.add(panel, BorderLayout.NORTH);

                panelTable.getViewport().add(depreciationForm.getTable());
                con.add(panelTable, BorderLayout.CENTER);

                con.add(statusBar, BorderLayout.SOUTH);
                setVisible(true);
            }
        });

        matTypes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK));
        matTypes.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    mtd = new MaterialTypeDialog(getThis(), true);
                    mtd.setVisible(true);

                    if (form != null) {
                        form.refresh();
                    }
                } catch (FileNotFoundException fnfe) {
                    JOptionPane.showMessageDialog(null, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });

        reasonTypes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));
        reasonTypes.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    rtd = new ReasonTypeDialog(getThis(), true);
                    rtd.setVisible(true);
                    if (form != null) {
                        form.refresh();
                    }
                } catch (FileNotFoundException fnfe) {
                    JOptionPane.showMessageDialog(null, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });

        createMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        createMenu.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    if (menuForm == null) {
                        menuForm = new MenuForm(getThis(), statusBar);
                    } else {
                        menuForm.refresh();
                    }
                } catch (FileNotFoundException fnfe) {
                    JOptionPane.showMessageDialog(null, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                form = menuForm;

                con.removeAll();
                panel.getViewport().add(menuForm);
                con.add(panel, BorderLayout.NORTH);

                panelTable.getViewport().add(menuForm.getTable());
                con.add(panelTable, BorderLayout.CENTER);

                con.add(statusBar, BorderLayout.SOUTH);
                setVisible(true);
            }
        });

        createMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
        createMenuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    if (menuItemForm == null) {
                        menuItemForm = new MenuItemForm(getThis(), statusBar);
                    } else {
                        menuItemForm.refresh();
                    }
                } catch (FileNotFoundException fnfe) {
                    JOptionPane.showMessageDialog(null, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                form = menuItemForm;

                con.removeAll();
                panel.getViewport().add(menuItemForm);
                con.add(panel, BorderLayout.NORTH);

                panelTable.getViewport().add(menuItemForm.getTable());
                con.add(panelTable, BorderLayout.CENTER);

                con.add(statusBar, BorderLayout.SOUTH);
                setVisible(true);
            }
        });

        createTable.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
        createTable.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    td = new TableDialog(getThis(), true);
                    td.setVisible(true);

                } catch (FileNotFoundException fnfe) {
                    JOptionPane.showMessageDialog(null, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });

        setConnections.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_MASK));
        setConnections.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    sd = new SettingsDialog(getThis(), true);
                    sd.setVisible(true);
                    if (insertMaterialForm != null) {
                        insertMaterialForm.refreshSettings();
                    }
                    if (menuItemForm != null) {
                        menuItemForm.refreshSettings();
                    }
                } catch (FileNotFoundException fnfe) {
                    JOptionPane.showMessageDialog(null, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });

        uzaverka.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
        uzaverka.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                int result = JOptionPane.showConfirmDialog(null, "Opravdu chcete vytvořit novou uzávěrku?", "Vytvoření uzávěrky", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {

                    try {
                        uzaverkaForm = new UzaverkaForm(getThis(), statusBar);
                        uzaverkaForm.refresh();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NotBoundException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (RemoteException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);


                    }

                    con.removeAll();
                    panel.getViewport().add(uzaverkaForm);
                    con.add(panel, BorderLayout.NORTH);


                    panelTable.getViewport().add(uzaverkaForm.getTable());
                    con.add(panelTable, BorderLayout.CENTER);

                    con.add(statusBar, BorderLayout.SOUTH);
                    setVisible(true);
                }
            }
        });
        uzaverkaPrehled.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
        uzaverkaPrehled.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                uzaverkaFormList = new UzaverkaList(getThis(), statusBar);
                try {
                    uzaverkaFormList.refresh();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NotBoundException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }



                con.removeAll();
                panel.getViewport().add(uzaverkaFormList);
                con.add(panel, BorderLayout.NORTH);


                panelTable.getViewport().add(uzaverkaFormList.getTable());
                con.add(panelTable, BorderLayout.CENTER);

                con.add(statusBar, BorderLayout.SOUTH);
                setVisible(true);

            }
        });

        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        about.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(getThis(), "Aplikace RestauraceFEL - Manažerský modul.", "O aplikaci", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        vytvoritZalohu.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                try {
                    fileChooser.setCurrentDirectory(new File(".").getCanonicalFile());
                    File backingUp = new File("../Server/db_rest_fel");
                    int val = fileChooser.showDialog(getThis(),"Vyber");
                    if(val == JFileChooser.APPROVE_OPTION){
                        File where = fileChooser.getSelectedFile();
                        File dst = Backup.createRootFolder(backingUp, where);
                        Backup.copyDirectory(backingUp, dst);
                        JOptionPane.showMessageDialog(null, "Záloha byla vytvořena", "Záloha vytvořena", JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (FileNotFoundException fnfe) {
                    JOptionPane.showMessageDialog(null, fnfe.getMessage(), "Chyba", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });



        menuFile.add(close);
        menuStore.add(newMaterial);
        menuStore.add(insertMat);
        menuStore.add(exportMat);
        menuStore.add(deprecMat);
        menuStore.add(new JSeparator());
        menuStore.add(matTypes);
        menuStore.add(reasonTypes);
        menuEmployers.add(insertEmp);
        menuEmployers.add(changePasswd);
        menuMenu.add(createMenu);
        menuMenu.add(createMenuItem);
        menuMenu.add(new JSeparator());
        menuMenu.add(createTable);
        menuSettings.add(setConnections);
        menuHelp.add(about);
        zrcadlo.add(uzaverka);
        zrcadlo.add(uzaverkaPrehled);
        zalohovani.add(vytvoritZalohu);

        menuBar.add(menuFile);
        menuBar.add(menuStore);
        menuBar.add(menuEmployers);
        menuBar.add(menuMenu);
        menuBar.add(zrcadlo);
        menuBar.add(zalohovani);
        menuBar.add(menuSettings);
        menuBar.add(menuHelp);

        this.setJMenuBar(menuBar);
    }

    /**
     * Metoda inicializuje okno a jeho komponenty. Vola metodu pro vytvoreni
     * hlavniho menu.
     */
    private void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = 650;
        int h = 550;
        int y = (int) ((dim.getHeight() - h) / 2);
        int x = (int) ((dim.getWidth() - w) / 2);
        this.setBounds(x, y, w, h);
        this.setVisible(true);
        this.setMinimumSize(new Dimension(400, 400));

        con = this.getContentPane();
        con.setLayout(new BorderLayout());

        panel = new JScrollPane();
        panelTable = new JScrollPane();

        this.createMenu();

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
