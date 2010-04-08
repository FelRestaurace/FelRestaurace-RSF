/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * StatistikaDialog.java
 *
 * Created on 17.11.2009, 13:53:20
 */

package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import service.ConfigParser;
import service.Printer;
import service.ResultTableModel;
import service.ServiceFacade;

/**
 *
 * @author Lukas Camra
 */
public class StatistikaDialog extends javax.swing.JDialog {

    /** Creates new form StatistikaDialog */
    private Object[][]data;

    public StatistikaDialog(java.awt.Frame parent, boolean modal, Object[][] data) {
        super(parent, modal);
        initComponents();
        setTitle("Statistika");
        ResultTableModel model=new ResultTableModel(ResultTableModel.namesUzaverkaStatistika, data);
        this.data=data;
        dataTable.setModel(model);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int y = (int) ((dim.getHeight() - 150) / 2);
        int x = (int) ((dim.getWidth() - 300) / 2);
        setLocation(x, y);
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
        dataTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(dataTable);

        jButton1.setText("Vytisknout");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Zavřít");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed



    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Map<String, Object> params = new HashMap<String, Object>();
        try {
            String template = "stats.jasper";
            ConfigParser config = new ConfigParser();
            params.put("title", "Statistika uzávěrek");
            params.put("surovina", "Surovina");
            params.put("puvodniStav", "Původní stav");
            params.put("prodano", "Prodáno");
            String printingFilePath = config.getTemplatesDir() + System.getProperty("file.separator") + template;
            File printFile = new File(printingFilePath);
            if (!printFile.exists()){
                JOptionPane.showMessageDialog(null, "Šablona \""+template+"\" nebyla v adresáři:\n\n " +
                        config.getTemplatesDir() + "\n\n nelezena. Pokud se šablony nachází v jiném adresáři, " +
                        "nastavte cestu k tomuto adresáři v nastavení.","Tisková sestava nenalezena", JOptionPane.ERROR_MESSAGE);
                return;
            }
            PrintDialog printDialog = new PrintDialog((JFrame)super.getParent(), true, params, printFile);
            printDialog.setTable(dataTable);
           
            printDialog.setVisible(true);
            //Printer.compileReport(printFile, config.getTemplatesDir() + System.getProperty("file.separator") + "stats.jasper");
        } catch (FileNotFoundException ex){
            JOptionPane.showMessageDialog(this, "Konfigurační soubor \"" + ConfigParser.getConfigFile() + "\" nebyl nalezen.", "Chyba", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Nelze navázat spojení se serverem.", "Chyba komunikace", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable dataTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
