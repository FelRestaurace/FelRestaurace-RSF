/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Bill.java
 *
 * Created on 30.11.2009, 11:21:13
 */

package gui;

import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Tomáš
 */
public class Bill extends javax.swing.JPanel {
    public int tableId=0;
    public int userId = 0;
    public int statusTypeId = 2;
    public String billName = "";
    public int slevaId = 0;
    public BillHeaderPart bill = new BillHeaderPart("Účet:");
    public BillHeaderPart table = new BillHeaderPart("Stůl č.:");
    public BillHeaderPart user = new BillHeaderPart("Zákaznik:");
    public BillHeaderPart  sleva = new BillHeaderPart("Sleva:");
    public BillHeaderPart status = new BillHeaderPart("Stav:");    

    

    /** Creates new form Bill */
    public Bill() {
        initComponents();
        this.BillBody.setLayout(new GroupLayout(this.BillBody));
        this.BillHeader.add(bill);
        this.BillHeader.add(table);
        this.BillHeader.add(user);
        this.BillHeader.add(sleva);
        this.BillHeader.add(status);
        this.status.value.setText("nezaplaceno");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BillHeader = new javax.swing.JPanel();
        BillSum = new javax.swing.JLabel();
        BillBody = new javax.swing.JPanel();

        BillHeader.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BillHeader.setLayout(new java.awt.GridLayout(3, 2));

        BillBody.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout BillBodyLayout = new javax.swing.GroupLayout(BillBody);
        BillBody.setLayout(BillBodyLayout);
        BillBodyLayout.setHorizontalGroup(
            BillBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );
        BillBodyLayout.setVerticalGroup(
            BillBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 311, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BillBody, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BillSum, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BillHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BillHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BillBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BillSum, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BillBody;
    public javax.swing.JPanel BillHeader;
    private javax.swing.JLabel BillSum;
    // End of variables declaration//GEN-END:variables


    public void setHeader(String billname,String tablename,String username,String sleva, String status)
    {
        if(billname!=null)this.bill.value.setText(billname);
        if(tableId!=0)this.table.value.setText(tablename);
        if(username!=null)this.user.value.setText(username);
        if(sleva!=null)this.sleva.value.setText(sleva);
        if(status!=null)this.status.value.setText(status);
    }

    public void fillOrders(Object[][] orders)
    {
        double sum = 0;
        if (orders != null) {
            //((GridLayout)BillBody.getLayout()).setRows(orders.length);
            for (int i = 0; i < orders.length; i++) {
                JLabel polozka = new JLabel();
                polozka.setText(orders[i][4].toString() + "x " + orders[i][2].toString() + " " + orders[i][3].toString() + ",-");
                polozka.setBounds(10, i * 20, 150, 20);
                sum += Double.valueOf(orders[i][4].toString()) * Double.valueOf(orders[i][3].toString());
                BillBody.add(polozka);
            }
        }
        BillSum.setText("Celkem: " + sum + ",-");
        
    }    
}