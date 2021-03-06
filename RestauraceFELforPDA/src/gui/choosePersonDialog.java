/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * choosePersonDialog.java
 *
 * Created on 30.11.2009, 14:05:21
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
public class choosePersonDialog extends AbstractDialog {

    private AddAccountDialog addCaller = null;
    private EditAccountDialog editCaller = null;
    private int mode;
    /** Creates new form chooseTableDialog */

    public choosePersonDialog(javax.swing.JFrame parent, boolean modal, AddAccountDialog caller, int mode) throws FileNotFoundException, NotBoundException, RemoteException {
        super(parent, modal);
        this.addCaller = caller;
        this.mode = mode;
        initComponents();
        jLabel1.setText("Vyberte osobu :");
        okButton.setText("Vybrat");
        refresh();
    }

    public choosePersonDialog(javax.swing.JFrame parent, boolean modal, EditAccountDialog caller, int mode) throws FileNotFoundException, NotBoundException, RemoteException {
        super(parent, modal);
        this.editCaller = caller;
        this.mode = mode;
        initComponents();
        jLabel1.setText("Vyberte osobu :");
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
        personBox = new javax.swing.JComboBox();
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

        personBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(personBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 170, -1));

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
        if (mode == 1) addCaller.fillPerson(personBox.getSelectedItem().toString());
        else editCaller.fillPerson(personBox.getSelectedItem().toString());
        this.dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton okButton;
    private javax.swing.JComboBox personBox;
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
        setComboBoxModel((ServiceFacade.getInstance().getUserUsernames()), personBox);
        
    }

}
