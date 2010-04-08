/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TableForm.java
 *
 * Created on 31.5.2009, 1:30:00
 */

package gui;

import javax.swing.JButton;
import javax.swing.JTextField;
import service.Validator;

/**
 * * Trida vytvarejici GUI formular vyuzivany tridou TableDialog.
 *
 * @author Jarda
 */
public class TableForm extends javax.swing.JPanel {

    /**
     * Konstruktor tridy TableForm.
     */
    public TableForm() {
        initComponents();
    }

    /**
     * Metoda cistici vsechny vsatupni komponenty.
     */
    public void clearFields(){
        Validator.clearTextField(jTextFieldTableNumber);
        Validator.clearTextField(jTextFieldPlaces);
    }

    /**
     * Metoda navracejici textField urceny pro cislo stolu.
     *
     * @return instance tridy JTextField
     */
    public JTextField getTableNumberTextField(){
        return jTextFieldTableNumber;
    }

    /**
     * Metoda navracejici textField urcujici pocet mist k sezeni o daneho stolu.
     *
     * @return instance tridy JTextField
     */
    public JTextField getPlacesTextField(){
        return jTextFieldPlaces;
    }

    /**
     * Metoda navracejici jButton, pri jehoz stisknuti dochazi k vlozeni noveho
     * zaznamu o druhu materialu.
     *
     * @return instance tridy JButton
     */
    public JButton getInsertButton(){
        return jButtonInsert;
    }

    /**
     * Metoda navracejici jButton, pri jehoz stisknuti dochazi ke smazani
     * vybraneho zaznamu.
     *
     * @return instance tridy JButton
     */
    public JButton getDeleteButton(){
        return jButtonDelete;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelTableNumber = new javax.swing.JLabel();
        jLabelPlaces = new javax.swing.JLabel();
        jTextFieldPlaces = new javax.swing.JTextField();
        jTextFieldTableNumber = new javax.swing.JTextField();
        jButtonInsert = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();

        jLabelTableNumber.setText("Číslo stolu");

        jLabelPlaces.setText("Počet míst k sezení");

        jButtonInsert.setText("Vytvořit záznam");
        jButtonInsert.setToolTipText("Vložit nový záznam Menu");

        jButtonDelete.setText("Smazat záznam");
        jButtonDelete.setToolTipText("Smazat vybraný záznam Menu");
        jButtonDelete.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelPlaces)
                            .addComponent(jLabelTableNumber))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldPlaces, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                            .addComponent(jTextFieldTableNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                        .addGap(55, 55, 55))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonInsert)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonDelete)
                        .addGap(26, 26, 26))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTableNumber)
                    .addComponent(jTextFieldTableNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPlaces)
                    .addComponent(jTextFieldPlaces, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDelete)
                    .addComponent(jButtonInsert))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 9, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 10, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonInsert;
    private javax.swing.JLabel jLabelPlaces;
    private javax.swing.JLabel jLabelTableNumber;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldPlaces;
    private javax.swing.JTextField jTextFieldTableNumber;
    // End of variables declaration//GEN-END:variables

}
