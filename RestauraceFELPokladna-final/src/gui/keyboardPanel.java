/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * keyboardPanel.java
 *
 * Created on 1.12.2009, 13:24:45
 */

package gui;

import javax.swing.JPanel;

/**
 *
 * @author Tomáš
 */
public class keyboardPanel extends JPanel {
    Bill target;
    NewAccount nw;
    /** Creates new form keyboardPanel */
    public keyboardPanel() {
        initComponents();
      //  this.target = target;
       // this.nw = nw;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jButton47 = new javax.swing.JButton();
        jButton48 = new javax.swing.JButton();
        jButton49 = new javax.swing.JButton();
        jButton50 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(500, 250));
        setPreferredSize(new java.awt.Dimension(500, 250));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("1");
        jButton1.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton1.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton1.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jButton2.setText("2");
        jButton2.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton2.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton2.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, -1, -1));

        jButton3.setText("3");
        jButton3.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton3.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton3.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, -1, -1));

        jButton19.setText("4");
        jButton19.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton19.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton19.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, -1, -1));

        jButton18.setText("5");
        jButton18.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton18.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton18.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, -1, -1));

        jButton4.setText("6");
        jButton4.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton4.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton4.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, -1, -1));

        jButton11.setText("7");
        jButton11.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton11.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton11.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, -1, -1));

        jButton14.setText("8");
        jButton14.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton14.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton14.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, -1, -1));

        jButton21.setText("9");
        jButton21.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton21.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton21.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, -1, -1));

        jButton22.setText("0");
        jButton22.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton22.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton22.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, -1, -1));

        jButton25.setText("Q");
        jButton25.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton25.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton25.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, -1));

        jButton26.setText("W");
        jButton26.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton26.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton26.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton26, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        jButton27.setText("E");
        jButton27.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton27.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton27.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton27, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, -1, -1));

        jButton28.setText("R");
        jButton28.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton28.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton28.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton28, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, -1, -1));

        jButton29.setText("T");
        jButton29.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton29.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton29.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton29, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));

        jButton30.setText("Z");
        jButton30.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton30.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton30.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton30, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, -1, -1));

        jButton31.setText("U");
        jButton31.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton31.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton31.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton31, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, -1, -1));

        jButton32.setText("I");
        jButton32.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton32.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton32.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton32, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, -1, -1));

        jButton33.setText("O");
        jButton33.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton33.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton33.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton33, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, -1, -1));

        jButton34.setText("P");
        jButton34.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton34.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton34.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton34, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, -1, -1));

        jButton35.setText("A");
        jButton35.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton35.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton35.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton35, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        jButton36.setText("S");
        jButton36.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton36.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton36.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton36, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, -1, -1));

        jButton37.setText("D");
        jButton37.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton37.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton37.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton37, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, -1, -1));

        jButton38.setText("F");
        jButton38.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton38.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton38.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton38, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, -1, -1));

        jButton39.setText("G");
        jButton39.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton39.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton39.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton39, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, -1, -1));

        jButton40.setText("H");
        jButton40.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton40.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton40.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton40, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, -1, -1));

        jButton41.setText("J");
        jButton41.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton41.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton41.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton41, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, -1, -1));

        jButton42.setText("K");
        jButton42.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton42.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton42.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton42, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, -1, -1));

        jButton43.setText("L");
        jButton43.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton43.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton43.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton43, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, -1, -1));

        jButton45.setText("Y");
        jButton45.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton45.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton45.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton45, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

        jButton46.setText("X");
        jButton46.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton46.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton46.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton46, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, -1, -1));

        jButton47.setText("C");
        jButton47.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton47.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton47.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton47, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, -1, -1));

        jButton48.setText("V");
        jButton48.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton48.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton48.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton48, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, -1, -1));

        jButton49.setText("B");
        jButton49.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton49.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton49.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton49, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, -1, -1));

        jButton50.setText("N");
        jButton50.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton50.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton50.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton50, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, -1, -1));

        jButton51.setText("M");
        jButton51.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton51.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton51.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton51, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, -1, -1));

        jButton5.setText(" ");
        jButton5.setMaximumSize(new java.awt.Dimension(350, 50));
        jButton5.setMinimumSize(new java.awt.Dimension(350, 50));
        jButton5.setPreferredSize(new java.awt.Dimension(350, 50));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keypressed(evt);
            }
        });
        add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 350, -1));

        jButton6.setText("ENT");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, 70, 100));

        jButton7.setText("DEL");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 80, 100));
    }// </editor-fold>//GEN-END:initComponents

    private void keypressed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keypressed
             //   target.bill.value.setText(target.bill.value.getText()+((JButton)evt.getSource()).getText());
    }//GEN-LAST:event_keypressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       // nw.SelectPanel.setVisible(false);
       // nw.MenuPanel.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
      //  String a = target.bill.value.getText();
      //  if(a.length()==0)return;
      //  a=a.substring(0, a.length()-1);
      //  target.bill.value.setText(a);
    }//GEN-LAST:event_jButton7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    // End of variables declaration//GEN-END:variables

}