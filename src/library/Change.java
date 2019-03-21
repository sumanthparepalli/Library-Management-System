/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Change extends javax.swing.JFrame {

    /**
     * Creates new form Change
     */
    String isbn;
    int incs;
    int decs;

    public Change(String x) {
        isbn = x;
        initComponents();
//        Toolkit t2=Toolkit.getDefaultToolkit();
//        Image i=t2.getImage("F:\\SEM-3\\CSE-2005_Object_Oriented_Programming\\back.png").getScaledInstance(30 , 30,Image.SCALE_SMOOTH);
//        ImageIcon im=new ImageIcon(i);
//        bac.setIcon(im);
//        i=t2.getImage("F:\\SEM-3\\CSE-2005_Object_Oriented_Programming\\tick.png").getScaledInstance(30 , 30,Image.SCALE_SMOOTH);
//        im=new ImageIcon(i);
//        tick.setIcon(im);
//        i=t2.getImage("F:\\SEM-3\\CSE-2005_Object_Oriented_Programming\\increse.png").getScaledInstance(30 , 30,Image.SCALE_SMOOTH);
//        im=new ImageIcon(i);
//        inc_lab.setIcon(im);
//        i=t2.getImage("F:\\SEM-3\\CSE-2005_Object_Oriented_Programming\\decrese.png").getScaledInstance(30 , 30,Image.SCALE_SMOOTH);
//        im=new ImageIcon(i);
//        dec_lab.setIcon(im);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        inc = new javax.swing.JCheckBox();
        dec = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        q = new javax.swing.JTextField();
        b1 = new javax.swing.JButton();
        back = new javax.swing.JButton();
        bac = new javax.swing.JLabel();
        tick = new javax.swing.JLabel();
        inc_lab = new javax.swing.JLabel();
        dec_lab = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Select the action you wan to perform:");

        inc.setText("Increase Quantity");
        inc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                incItemStateChanged(evt);
            }
        });
        inc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incActionPerformed(evt);
            }
        });

        dec.setText("Decrease Quantity");
        dec.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                decItemStateChanged(evt);
            }
        });

        jLabel2.setText("Enter the Quantity you want to add or reduce ");

        b1.setText("Submit");
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });
        b1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                b1KeyPressed(evt);
            }
        });

        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        back.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                backKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tick, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(inc_lab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dec_lab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(dec, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(inc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(b1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                .addComponent(q, javax.swing.GroupLayout.Alignment.LEADING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bac, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inc)
                    .addComponent(inc_lab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dec)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dec_lab, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                        .addGap(5, 5, 5)))
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(q, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b1)
                    .addComponent(back)
                    .addComponent(bac, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tick, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        try {
            // TODO add your handling code here:
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
            Statement st = con.createStatement();
            if (incs == 1) {
                try {
                    int qu = Integer.parseInt(q.getText());
                    String query = "update books set oquantity=oquantity+" + qu + " where ISBN=" + isbn;
                    st.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Quantity Updated ");
                } catch (SQLException ex) {
                    Logger.getLogger(Change.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                ResultSet rs;
                int qu = Integer.parseInt(q.getText());
                String q1 = "select * from books where ISBN=" + isbn;
                rs = st.executeQuery(q1);
                rs.next();
                int oquantity = rs.getInt(4);
                int iquantity = rs.getInt(5);
                if ((oquantity - qu) < iquantity) {
                    JOptionPane.showMessageDialog(null, "Invalid Input!!");
                } else {
                    String q2 = "update books set oquantity=oquantity-" + qu + " where ISBN=" + isbn;
                    st.executeUpdate(q2);
                    JOptionPane.showMessageDialog(null, "Quantity Updated ");
                }
            }
            Startup obj = new Startup();
            obj.setTitle("StartUp Page");
            //obj.setLocation(400,400);
            obj.setVisible(true);
            dispose();
        } catch (SQLException ex) {
            Logger.getLogger(Change.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_b1ActionPerformed

    private void incActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_incActionPerformed

    private void incItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_incItemStateChanged
        // TODO add your handling code here:
        incs = evt.getStateChange();
    }//GEN-LAST:event_incItemStateChanged

    private void decItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_decItemStateChanged
        // TODO add your handling code here:
        decs = evt.getStateChange();
    }//GEN-LAST:event_decItemStateChanged

    private void b1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                // TODO add your handling code here:
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "root");
                Statement st = con.createStatement();
                if (incs == 1) {
                    try {
                        int qu = Integer.parseInt(q.getText());
                        String query = "update books set oquantity=oquantity+" + qu + " where ISBN=" + isbn;
                        st.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, "Quantity Updated ");
                    } catch (SQLException ex) {
                        Logger.getLogger(Change.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    ResultSet rs;
                    int qu = Integer.parseInt(q.getText());
                    String q1 = "select * from books where ISBN=" + isbn;
                    rs = st.executeQuery(q1);
                    rs.next();
                    int oquantity = rs.getInt(4);
                    int iquantity = rs.getInt(5);
                    if ((oquantity - qu) < iquantity) {
                        JOptionPane.showMessageDialog(null, "Invalid Input!!");
                    } else {
                        String q2 = "update books set oquantity=oquantity-" + qu + " where ISBN=" + isbn;
                        st.executeUpdate(q2);
                        JOptionPane.showMessageDialog(null, "Quantity Updated ");
                    }
                }
                Startup obj = new Startup();
                obj.setTitle("StartUp Page");
                //obj.setLocation(400,400);
                obj.setVisible(true);
                dispose();
            } catch (SQLException ex) {
                Logger.getLogger(Change.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_b1KeyPressed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        Librarian_Menu obj = new Librarian_Menu();
        obj.setTitle("Librarian Menu");
        obj.setVisible(true);
        dispose();
    }//GEN-LAST:event_backActionPerformed

    private void backKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_backKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Librarian_Menu obj = new Librarian_Menu();
            obj.setTitle("Librarian Menu");
            obj.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_backKeyPressed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Change.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Change.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Change.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Change.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b1;
    private javax.swing.JLabel bac;
    private javax.swing.JButton back;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JCheckBox dec;
    private javax.swing.JLabel dec_lab;
    private javax.swing.JCheckBox inc;
    private javax.swing.JLabel inc_lab;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField q;
    private javax.swing.JLabel tick;
    // End of variables declaration//GEN-END:variables
}