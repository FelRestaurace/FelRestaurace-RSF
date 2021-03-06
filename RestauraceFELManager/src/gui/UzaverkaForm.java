/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Uzaverka.java
 *
 * Created on 3.11.2009, 21:03:44
 */
package gui;

//import service.CommReader;
import hibernate.Kontrola;
import hibernate.Material;
import hibernate.Uzaverka;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import service.CommReader;
import service.ResultTableModel;
import service.ServiceFacade;
import service.SurovinyModel;
import service.Validator;

/**
 *
 * @author Lukas Camra
 */
public class UzaverkaForm extends AbstractForm {

    private JTable table = null;
    private StatusBar statusBar = null;
    private JFrame parent = null;
    private Uzaverka uzaverka;
    private List<Kontrola> kontroly;

    public UzaverkaForm() {
    }

    /**
     * Kontruktor tridy Uzaverka
     *
     * @param parent
     * @param bar
     * @throws FileNotFoundException
     * @throws NotBoundException
     * @throws RemoteException
     */
    public UzaverkaForm(JFrame parent, StatusBar bar) throws FileNotFoundException, NotBoundException, RemoteException {
        this.parent = parent;
        this.statusBar = bar;

        try {
            CommReader.start();
        } catch (gnu.io.NoSuchPortException ex) {
            JOptionPane.showMessageDialog(null, "Nastavený sériový port neexistuje!/Váha není připojena!", "Port neexistuje", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Jiná chyba s portem COM", "Jiná chyba s portem COM", JOptionPane.ERROR_MESSAGE);
        }
        uzaverka = new Uzaverka();
        uzaverka.setDate(new Date());
        uzaverka.setUser(LogingDialog.getLoggedUser());
        kontroly = new LinkedList<Kontrola>();
        bar.setText("Tento formulář slouží k přidání nové uzávěrky!");
        initComponents();
        seznamSurovinList.setModel(new SurovinyModel(ServiceFacade.getInstance().getAllMaterials()));
        initTable();
        refresh();
        clearFields();
        vahaInput.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                changeVaha();
            }

            public void removeUpdate(DocumentEvent e) {
                changeVaha();
            }

            public void changedUpdate(DocumentEvent e) {
                changeVaha();
            }
        });
        barCodeInput.requestFocus();

    }

    private void changeVaha() {
        try{
        int selectedI = seznamSurovinList.getSelectedIndex();
        if (selectedI == -1) {
            return;
        }
        SurovinyModel model = (SurovinyModel) seznamSurovinList.getModel();
        Material mat = (Material) model.getMaterialAt(selectedI);
        double pocetNaVaze = Double.parseDouble(jTextFieldBaleniNaVaze.getText());
        double vahaKapaliny = (Double.parseDouble(vahaInput.getText())) - pocetNaVaze * mat.getEmptyPackageWeight();
        double mnozstvi = vahaKapaliny * mat.getDensity(); // krat nebo deleno
        //zaokrouhleni
        int intMnoztvi = (int) mnozstvi * 10;
        mnozstvi = ((double) intMnoztvi) / 10;
        double plnaBaleni = Double.parseDouble(jTextFieldPlnaBaleni.getText());
        mnozstvi += plnaBaleni * mat.getPackageCapacity();
        surovinaLabel.setText(mat.getName());
        rozdilLabel.setText(String.valueOf(mnozstvi));
        }catch(Exception ex){
            System.out.println("Wrong format number");
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

        jScrollPane1 = new javax.swing.JScrollPane();
        seznamSurovinList = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        barCodeInput = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        vahaInput = new javax.swing.JTextField();
        okButtonCode = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        surovinaLabel = new javax.swing.JLabel();
        rozdilLabel = new javax.swing.JLabel();
        vahaOKButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldPlnaBaleni = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldBaleniNaVaze = new javax.swing.JTextField();
        jButtonOpakovat = new javax.swing.JButton();

        seznamSurovinList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(seznamSurovinList);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel1.setText("Čárový kód:");

        barCodeInput.setFont(new java.awt.Font("Tahoma", 0, 12));
        barCodeInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                barCodeInputKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel2.setText("Váha:");

        vahaInput.setFont(new java.awt.Font("Tahoma", 0, 12));
        vahaInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vahaInputActionPerformed(evt);
            }
        });
        vahaInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                vahaInputKeyPressed(evt);
            }
        });

        okButtonCode.setFont(new java.awt.Font("Tahoma", 0, 12));
        okButtonCode.setText("Vyber surovinu");
        okButtonCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonCodeActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel3.setText("Přidávaný záznam:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel4.setText("Surovina:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel5.setText("Množství na váze:");

        surovinaLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        rozdilLabel.setFont(new java.awt.Font("Tahoma", 0, 14));

        vahaOKButton.setFont(new java.awt.Font("Tahoma", 0, 12));
        vahaOKButton.setText("Ulož kontrolu");
        vahaOKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vahaOKButtonActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18));
        jButton1.setText("Ulož uzávěrku");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel7.setText("Plná balení:");

        jTextFieldPlnaBaleni.setText("0");
        jTextFieldPlnaBaleni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldPlnaBaleniKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel6.setText("Balení na váze:");

        jTextFieldBaleniNaVaze.setText("1");
        jTextFieldBaleniNaVaze.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldBaleniNaVazeKeyPressed(evt);
            }
        });

        jButtonOpakovat.setText("Opakovat");
        jButtonOpakovat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOpakovatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(barCodeInput, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(vahaInput, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                                            .addComponent(jTextFieldPlnaBaleni, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(okButtonCode)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(vahaOKButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonOpakovat))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jTextFieldBaleniNaVaze, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rozdilLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(19, 19, 19))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(surovinaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel3)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel1)
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(barCodeInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(okButtonCode))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldPlnaBaleni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldBaleniNaVaze, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vahaInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vahaOKButton)
                            .addComponent(jButtonOpakovat))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                                .addGap(1, 1, 1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(surovinaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                                .addGap(5, 5, 5))
                            .addComponent(rozdilLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(14, 14, 14))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonCodeActionPerformed
        // TODO add your handling code here:
        String kod = barCodeInput.getText();
        SurovinyModel model = (SurovinyModel) seznamSurovinList.getModel();
        int j = 0;
        boolean found = false;
        for (Material m : model.getSuroviny()) {
            if (m.getBarcode().equals(kod)) {
                seznamSurovinList.setSelectedIndex(j);
                found =
                        true;
                break;

            }




            j++;
        }

        if (!found) {
            seznamSurovinList.setSelectedIndex(-1);
            JOptionPane.showMessageDialog(null, "Surovina s tímto čárkovým kódem neexistuje!", "Surovina neexistuje", JOptionPane.ERROR_MESSAGE);
        } else {
            jTextFieldPlnaBaleni.requestFocus();
        }

        seznamSurovinList.ensureIndexIsVisible(j);

    }//GEN-LAST:event_okButtonCodeActionPerformed

    private boolean saveKontrola(Material mat) {
        Kontrola k = new Kontrola();
        k.setSurovina(mat);
        k.setUzaverka(uzaverka);
        k.setStareMnozstvi(mat.getCurrentQuantity());
        double pocetNaVaze = Double.parseDouble(jTextFieldBaleniNaVaze.getText());
        double vahaKapaliny = (Double.parseDouble(vahaInput.getText())) - pocetNaVaze * mat.getEmptyPackageWeight();
        double mnozstvi = vahaKapaliny * mat.getDensity(); // krat nebo deleno
        //zaokrouhleni
        int intMnoztvi = (int) mnozstvi * 10;
        mnozstvi =
                ((double) intMnoztvi) / 10;
        double plnaBaleni = Double.parseDouble(jTextFieldPlnaBaleni.getText());
        mnozstvi +=
                plnaBaleni * mat.getPackageCapacity();
        k.setNoveMnozstvi(mnozstvi);
        k.setProdanoVahou(k.getStareMnozstvi() - mnozstvi);
//        if (k.getProdanoVahou() < 0) {
//            JOptionPane.showMessageDialog(this, "Nemožné: bylo prodáno více množství než je na skladě! Na skladě máte: " + mat.getCurrentQuantity(), "Zrcadlo", JOptionPane.INFORMATION_MESSAGE);
//            return false;
//        }
        if (vahaKapaliny < 0) {
            JOptionPane.showMessageDialog(this, "Pozor: po odečtení váhy lahve vyšla záporná hodnota. Možná není dobře nastavena váha prázdné lahve!" + vahaKapaliny, "Zrcadlo", JOptionPane.INFORMATION_MESSAGE);
//            return false;
            mnozstvi =
                    0;
            k.setNoveMnozstvi(mnozstvi);
        }

//mat.setCurrentQuantity(mnozstvi);
        surovinaLabel.setText(mat.getName());
        rozdilLabel.setText(String.valueOf(k.getProdanoVahou()));
        k.setUzaverka(uzaverka);
        kontroly.add(k);
        return true;

    }
    private void vahaOKButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vahaOKButtonActionPerformed
        // TODO add your handling code here:
        try {
            if (isValidInput() == 0) {
                int selectedI = seznamSurovinList.getSelectedIndex();
                SurovinyModel model = (SurovinyModel) seznamSurovinList.getModel();
                Material material = (Material) model.getMaterialAt(selectedI);
                boolean ok = saveKontrola(material);

                if (ok) {
                    refreshTable();
                    clearFields();

                    model.removeElement(selectedI);
                    barCodeInput.requestFocus();
                }

            } else if (isValidInput() == 1) {
                JOptionPane.showMessageDialog(this, "Není vybrána žádná surovina!", "Zrcadlo", JOptionPane.INFORMATION_MESSAGE);
                return;

            } else if (isValidInput() == 2) {
                JOptionPane.showMessageDialog(this, "Zadaná váha není číslem!", "Zrcadlo", JOptionPane.INFORMATION_MESSAGE);
                return;

            } else if (isValidInput() == 4) {
                JOptionPane.showMessageDialog(this, "Počet balení musí být kladné číslo!", "Zrcadlo", JOptionPane.INFORMATION_MESSAGE);
                return;

            } else {
                JOptionPane.showMessageDialog(this, "Počet nově otevřených balení musí být číslo!", "Zrcadlo", JOptionPane.INFORMATION_MESSAGE);
                return;

            }






        } catch (FileNotFoundException ex) {
            Logger.getLogger(UzaverkaForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(UzaverkaForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(UzaverkaForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        

    }//GEN-LAST:event_vahaOKButtonActionPerformed

    private void barCodeInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barCodeInputKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            okButtonCodeActionPerformed(null);

        }
    }//GEN-LAST:event_barCodeInputKeyPressed

    private void jTextFieldPlnaBaleniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPlnaBaleniKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jTextFieldBaleniNaVaze.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldPlnaBaleniKeyPressed

    private void jTextFieldBaleniNaVazeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBaleniNaVazeKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            vahaInput.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldBaleniNaVazeKeyPressed

    private void jButtonOpakovatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOpakovatActionPerformed
        // TODO add your handling code here:
        int selectedIndex = table.getSelectedRow();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Nebyla vybrána kontrola, kterou chcete zopakovat", "Zrcadlo", JOptionPane.ERROR_MESSAGE);
            return;

        }
        Kontrola kontrolaOld = kontroly.get(selectedIndex);
        boolean ok = saveKontrola(kontrolaOld.getSurovina());
        if (ok) {
            kontrolaOld.setValid(false);
            try {
                refreshTable();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(UzaverkaForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
                Logger.getLogger(UzaverkaForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger(UzaverkaForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            clearFields();
            barCodeInput.requestFocus();
        }
        
    }//GEN-LAST:event_jButtonOpakovatActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        CommReader.isRun = false;
        if (kontroly.size() == 0) {
            JOptionPane.showMessageDialog(this, "Uzávěrka nemá žádné kontroly!", "Zrcadlo", JOptionPane.ERROR_MESSAGE);
            return;

        }
        try {
            ServiceFacade.getInstance().createUzaverka(uzaverka.getUser(), uzaverka.getDate());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UzaverkaForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(UzaverkaForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(UzaverkaForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        //ulozeni vydeje
        for (Kontrola k : kontroly) {
            try {
                //pridani uzaverky
                ServiceFacade.getInstance().addKontrolaToUzaverka(k);
                if (k.isValid()) {
                    //vytvoreni vydeju
                    if (k.getProdanoVahou() > 0) {
                        ServiceFacade.getInstance().createExpenditure(uzaverka.getDate(), k.getSurovina().getMaterialId(), k.getProdanoVahou(), LogingDialog.getLoggedUser().getUserId(), "Zrcadlo");
                    } else if (k.getProdanoVahou() < 0) {
                        ServiceFacade.getInstance().createIncome(uzaverka.getDate(), k.getSurovina().getMaterialId(), -k.getProdanoVahou(), 0, LogingDialog.getLoggedUser().getUserId(), "Zrcadlo");
                    }
//update materialu
//ServiceFacade.getInstance().updateMaterial(k.getSurovina().getMaterialId(), k.getSurovina().getName(), k.getSurovina().getMaterialType().getMaterialTypeId(), k.getSurovina().getUnitType().getTypeId(), k.getSurovina().getBarcode(), k.getSurovina().getMinimal(), k.getSurovina().getDensity(), k.getSurovina().getEmptyPackageWeight(), k.getSurovina().getPackageCapacity());


                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(UzaverkaForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
                Logger.getLogger(UzaverkaForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger(UzaverkaForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        parent.getContentPane().removeAll();
        parent.getContentPane().repaint();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void vahaInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vahaInputActionPerformed
    }//GEN-LAST:event_vahaInputActionPerformed

    private void vahaInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vahaInputKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            vahaOKButtonActionPerformed(null);
        }
    }//GEN-LAST:event_vahaInputKeyPressed
    /**
     * Metoda provadi aktualizaci tabulky
     *
     * @throws FileNotFoundException
     * @throws NotBoundException
     * @throws RemoteException
     */
    @Override
    protected void refresh() throws FileNotFoundException, NotBoundException, RemoteException {
        refreshTable();
        statusBar.setMessage("Tento formulář slouží k vytvoření zrcadla.");

    }

    /**
     * Metoda kontroluje validitu vstupu
     *
     * @return Vraci index charakterizujici dany problem
     */
    @Override
    protected int isValidInput() {
        if (seznamSurovinList.getSelectedIndex() == -1) {
            return 1;
        } else if (!Validator.isPositiveDouble(vahaInput)) {
            return 2;
        } else if (!Validator.isPositiveDouble(jTextFieldPlnaBaleni)) {
            return 3;
        } else if (!Validator.isPositiveDouble(jTextFieldBaleniNaVaze)) {
            return 4;
        } else {
            return 0;
        }

    }

    /**
     * inicalizuje tabulku s prehledem kontrol
     */
    @Override
    protected void initTable() {
        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    /**
     * Aktualizuje tabulku s prehledem kontrol
     *
     * @throws FileNotFoundException
     * @throws NotBoundException
     * @throws RemoteException
     */
    @Override
    protected void refreshTable() throws FileNotFoundException, NotBoundException, RemoteException {
        ResultTableModel rtm = new ResultTableModel();
        rtm.setColumnData(ResultTableModel.namesUzaverkaKontroly);
        rtm.setTableData(getKontroly());
        table.setModel(rtm);
        //seznamSurovinList.setPreferredSize(seznamSurovinList.getCellBounds(0, seznamSurovinList.getModel().getSize()-1).getSize());
        int selected = seznamSurovinList.getSelectedIndex();
        seznamSurovinList.revalidate();
        if (selected != -1) {
            seznamSurovinList.setSelectedIndex(selected);
        }

        jScrollPane1.revalidate();
        seznamSurovinList.repaint();
        seznamSurovinList.clearSelection();


    }

    public Object[][] getKontroly() {
        Object[][] result = new Object[kontroly.size()][7];
        int i = 0;
        for (Kontrola k : kontroly) {
            result[i][0] = k.getSurovina().getName();
            result[i][1] = k.getStareMnozstvi();
            result[i][2] = k.getNoveMnozstvi();
            result[i][3] = k.getProdanoVahou();
            result[i][4] = k.getProdanoPokladnou();
            result[i][5] = k.getRozdil();
            result[i][6] = k.isValid() ? "Ano" : "Ne";
            i++;

        }




        return result;

    }

    /**
     * Metoda cisti inputboxy
     */
    @Override
    protected void clearFields() {
        Validator.clearTextField(vahaInput);
        Validator.clearTextField(barCodeInput);
        jTextFieldBaleniNaVaze.setText("1");
        jTextFieldPlnaBaleni.setText("0");

    }

    public static void setWeight(double weight) {
        vahaInput.setText(String.valueOf(weight));
        vahaInput.requestFocus();
    }

    /**
     * getter pro JTable
     * @return vraci tabulku JTable, ktera zobrazuje vsechny kontroly dane uzaverky
     */
    public JTable getTable() {
        return table;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barCodeInput;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonOpakovat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldBaleniNaVaze;
    private javax.swing.JTextField jTextFieldPlnaBaleni;
    private javax.swing.JButton okButtonCode;
    private javax.swing.JLabel rozdilLabel;
    private javax.swing.JList seznamSurovinList;
    private javax.swing.JLabel surovinaLabel;
    private static javax.swing.JTextField vahaInput;
    private javax.swing.JButton vahaOKButton;
    // End of variables declaration//GEN-END:variables
}
