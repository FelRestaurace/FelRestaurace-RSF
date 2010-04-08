/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainJFrame.java
 *
 * Created on 3.11.2009, 16:31:33
 */
package gui;

import hibernate.User;

/**
 *
 * @author Tomáš
 */
public class MainJFrame extends javax.swing.JFrame {

    private static LogingDialog ld = null;    
    private static User loggedUser = null;
    public static Bill openBill = null;
    final static int CREATE_ORDER = 1;
    final static int PAY_ORDER = 2;
    /** Creates new form MainJFrame */
    public MainJFrame() {
        ld = new LogingDialog(this, true);
        ld.setVisible(true);
        loggedUser = ld.getLoggedUser();
        if (loggedUser == null) {
            System.exit(0);
        }
        this.setTitle("Restaurace FEL - Pokladní modul, přihlášený uživatel: " + loggedUser.getUsername());
        initComponents();
        MainJFrame.PanelListku.setVisible(false);
        MainJFrame.PanelVyberu.setVisible(false);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelListku = new javax.swing.JPanel();
        PanelVyberu = new javax.swing.JPanel();
        PanelMenu = new javax.swing.JPanel();
        NewAcc = new javax.swing.JButton();
        OpenAcc = new javax.swing.JButton();
        CloseAcc = new javax.swing.JButton();
        PayAcc = new javax.swing.JButton();
        PrintBill = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        PanelListku.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PanelListku.setMinimumSize(new java.awt.Dimension(280, 4));
        PanelListku.setPreferredSize(new java.awt.Dimension(280, 4));
        PanelListku.setLayout(new java.awt.BorderLayout());

        PanelVyberu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PanelVyberu.setMinimumSize(new java.awt.Dimension(450, 4));
        PanelVyberu.setPreferredSize(new java.awt.Dimension(450, 4));
        PanelVyberu.setLayout(new java.awt.BorderLayout());

        PanelMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PanelMenu.setMinimumSize(new java.awt.Dimension(780, 27));
        PanelMenu.setLayout(new java.awt.GridLayout(1, 10));

        NewAcc.setText("Nový účet");
        NewAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewAccActionPerformed(evt);
            }
        });
        PanelMenu.add(NewAcc);

        OpenAcc.setText("Otevřít účet");
        OpenAcc.setMaximumSize(new java.awt.Dimension(91, 23));
        OpenAcc.setMinimumSize(new java.awt.Dimension(91, 23));
        OpenAcc.setPreferredSize(new java.awt.Dimension(91, 23));
        OpenAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenAccActionPerformed(evt);
            }
        });
        PanelMenu.add(OpenAcc);

        CloseAcc.setText("Zavřít účet");
        CloseAcc.setEnabled(false);
        CloseAcc.setMaximumSize(new java.awt.Dimension(85, 23));
        CloseAcc.setMinimumSize(new java.awt.Dimension(85, 23));
        CloseAcc.setPreferredSize(new java.awt.Dimension(85, 23));
        CloseAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseAccActionPerformed(evt);
            }
        });
        PanelMenu.add(CloseAcc);

        PayAcc.setText("Zaplatit účet");
        PayAcc.setEnabled(false);
        PayAcc.setMaximumSize(new java.awt.Dimension(93, 23));
        PayAcc.setMinimumSize(new java.awt.Dimension(93, 23));
        PayAcc.setPreferredSize(new java.awt.Dimension(93, 23));
        PanelMenu.add(PayAcc);

        PrintBill.setText("Vytisknout účet");
        PrintBill.setEnabled(false);
        PrintBill.setMaximumSize(new java.awt.Dimension(107, 23));
        PrintBill.setMinimumSize(new java.awt.Dimension(107, 23));
        PrintBill.setPreferredSize(new java.awt.Dimension(107, 23));
        PanelMenu.add(PrintBill);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelListku, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelVyberu, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(PanelMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 892, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelListku, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(PanelVyberu, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NewAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewAccActionPerformed
        MainJFrame.PanelVyberu.removeAll();
        MainJFrame.PanelListku.removeAll();
        MainJFrame.openBill = new Bill();
        MainJFrame.PanelListku.add(MainJFrame.openBill);
        MainJFrame.PanelVyberu.add(new NewAccount());
        MainJFrame.PanelVyberu.setVisible(true);
        MainJFrame.PanelListku.setVisible(true);
        this.validate();
        this.repaint();
    }//GEN-LAST:event_NewAccActionPerformed

    private void OpenAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenAccActionPerformed
        MainJFrame.PanelVyberu.removeAll();
        MainJFrame.PanelListku.removeAll();
        MainJFrame.PanelListku.setVisible(false);
        MainJFrame.PanelVyberu.add(new OpenAccount());
        MainJFrame.PanelVyberu.setVisible(true);
        this.validate();
        this.repaint();
    }//GEN-LAST:event_OpenAccActionPerformed

    private void CloseAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseAccActionPerformed
        MainJFrame.openBill=null;
        MainJFrame.PanelListku.removeAll();
        MainJFrame.PanelVyberu.removeAll();
        MainJFrame.NewAcc.setEnabled(true);
        MainJFrame.OpenAcc.setEnabled(true);
        MainJFrame.CloseAcc.setEnabled(false);
        MainJFrame.PayAcc.setEnabled(false);
        MainJFrame.PrintBill.setEnabled(false);
    }//GEN-LAST:event_CloseAccActionPerformed

/**
 * @param args the command line arguments
 */
public static void

main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void

run() {
                new MainJFrame().setVisible(true);
            }

});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton CloseAcc;
    public static javax.swing.JButton NewAcc;
    public static javax.swing.JButton OpenAcc;
    public static javax.swing.JPanel PanelListku;
    private javax.swing.JPanel PanelMenu;
    public static javax.swing.JPanel PanelVyberu;
    public static javax.swing.JButton PayAcc;
    public static javax.swing.JButton PrintBill;
    // End of variables declaration//GEN-END:variables

}
