/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * chooseDiscountDialog.java
 *
 * Created on 30.11.2009, 14:05:28
 */

package gui;

import java.io.FileNotFoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import service.ServiceFacade;

/**
 *
 * @author Supervisor
 */
public class chooseDiscountDialog extends AbstractDialog {

    private AddAccountDialog addCaller = null;
    private EditAccountDialog editCaller = null;
    private int mode;
    /** Creates new form chooseTableDialog */

    public chooseDiscountDialog(javax.swing.JFrame parent, boolean modal, AddAccountDialog caller, int mode) throws FileNotFoundException, NotBoundException, RemoteException {
        super(parent, modal);
        this.addCaller = caller;
        this.mode = mode;
        initComponents();
        jLabel1.setText("Vyberte slevu :");
        okButton.setText("Vybrat");
        refresh();
    }

    public chooseDiscountDialog(javax.swing.JFrame parent, boolean modal, EditAccountDialog caller, int mode) throws FileNotFoundException, NotBoundException, RemoteException {
        super(parent, modal);
        this.editCaller = caller;
        this.mode = mode;
        initComponents();
        jLabel1.setText("Vyberte slevu :");
        okButton.setText("Vybrat");
        refresh();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        discountBox = new javax.swing.JComboBox();
        okButton = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(240, 320));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("jLabel1");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        discountBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(discountBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 170, -1));

        okButton.setText("jButton1");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        add(okButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        if (mode == 1) addCaller.fillDiscount(discountBox.getSelectedItem().toString());
        else editCaller.fillDiscount(discountBox.getSelectedItem().toString());
        this.dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox discountBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables

    @Override
    protected int isValidInput() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void initTable() throws RemoteException, NotBoundException, FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void refresh() throws RemoteException, NotBoundException, FileNotFoundException {
        setComboBoxModel(ServiceFacade.getInstance().getDiscountTypeNames(), discountBox);
    }

}
