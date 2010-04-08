/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MaterialTypeForm.java
 *
 * Created on 26.4.2009, 20:54:33
 */

package gui;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import service.Validator;

/**
 * Trida vytvarejici GUI formular vyuzivany tridou MaterialTypeDialog.
 *
 * @author Jarda
 */
public class MaterialTypeForm extends javax.swing.JPanel {

    /**
     * Konstruktor tridy MaterialTypeForm.
     */
    public MaterialTypeForm() {
        initComponents();
    }

    /**
     * Metoda cistici vsechny vsatupni komponenty.
     */
    public void clearFields(){
        Validator.clearTextField(jTextFieldName);
        Validator.clearTextField(jTextAreaNote);
    }

    /**
     * Metoda navracejici textField urceny pro jmeno druhu materialu.
     *
     * @return instance tridy JTextField
     */
    public JTextField getNameTextField(){
        return jTextFieldName;
    }

    /**
     * Metoda navracejici textAreu urcenou pro poznamky k druhu materialu.
     *
     * @return instance tridy JTextArea
     */
    public JTextArea getNoteTextArea(){
        return jTextAreaNote;
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

        jLabelName = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jLabelNote = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaNote = new javax.swing.JTextArea();
        jButtonInsert = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();

        jLabelName.setText("Název druhu");

        jLabelNote.setText("Poznámka");

        jTextAreaNote.setColumns(20);
        jTextAreaNote.setRows(5);
        jScrollPane1.setViewportView(jTextAreaNote);

        jButtonInsert.setText("Vložit záznam");
        jButtonInsert.setToolTipText("Vložit nový záznam");

        jButtonDelete.setText("Smazat záznam");
        jButtonDelete.setToolTipText("Smazat vybraný záznam");
        jButtonDelete.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelName)
                    .addComponent(jLabelNote))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(149, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                .addComponent(jButtonDelete)))
                        .addGap(29, 29, 29))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelName)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonDelete)
                            .addComponent(jButtonInsert)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabelNote)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonInsert;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelNote;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaNote;
    private javax.swing.JTextField jTextFieldName;
    // End of variables declaration//GEN-END:variables

}
