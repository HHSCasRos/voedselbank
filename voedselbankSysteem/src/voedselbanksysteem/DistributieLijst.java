/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voedselbanksysteem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author FK
 */
public class DistributieLijst extends javax.swing.JFrame {
    
    private HomeScreen opener;
    private Connection connection;
    private Statement statement;
    private ResultSet rs;
    private JDBCDriver driver;
    

    /**
     * Creates new form DistributieLijst
     */
    public DistributieLijst() {
        
        try {
            initComponents();
            connection = DatabaseSource.getConnection();
            statement = connection.createStatement();

            
            String query = "SELECT naam,  \n" +
"Sum(CASE voedselpakketSoort WHEN 'Enkelvoudig pakket' THEN 1\n" +
"WHEN 'Dubbel pakket' THEN 2\n" +
"WHEN '3-voudig pakket' THEN 3 END) as 'Uit te leveren pakketen'\n" +
"from Uitgiftepunt join Cliënt c on naam = toegewezenUitgiftepunt\n" +
"				  join Intake i on kaartnr = Cliënt                  \n" +
"where i.StatusIntake = 'actief'\n" +
"AND i.startUitgifte < 42884\n" +
"AND i.datumStopzetting > 42884 \n" +
"group by naam;";
            
            rs = statement.executeQuery (query);
            // alle nummers van de collom
            
            
            JDBCDriver.fillTable(rs, jTable1);
            jTable1.getColumnModel().getColumn(0).setHeaderValue("Uitgiftepunt");
            jTable1.getColumnModel().getColumn(1).setHeaderValue("Deze week");
            
            
            
        }catch (SQLException ex) {
            System.out.println ("Er is iets mis met de database.");
            // Logger.getLogger(Blok4WC2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      void setOpener(HomeScreen a) {
        this.opener = a;
      }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        TransferRows = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        DeleteRow = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(900, 500));
        setResizable(false);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(1000, 500));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        TransferRows.setText("Toevoegen");
        TransferRows.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TransferRowsActionPerformed(evt);
            }
        });

        jButton2.setText("Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Return");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Uitgiftepunt", "Deze week"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        DeleteRow.setText("Delete");
        DeleteRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteRowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TransferRows, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                            .addComponent(DeleteRow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addComponent(TransferRows)
                                .addGap(33, 33, 33)
                                .addComponent(DeleteRow)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addComponent(jButton2))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //Refresh Button
    private void TransferRowsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TransferRowsActionPerformed

           
        TableModel model1 = jTable1.getModel();
            int[] indexs = jTable1.getSelectedRows();
            Object[] row = new Object[4];
            DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();
            for(int i = 0; i < indexs.length; i++){
                row[0] = model1.getValueAt(indexs[i], 0);
                row[1] = model1.getValueAt(indexs[i], 1);
                row[2] = model1.getValueAt(indexs[i], 0);
                row[3] = model1.getValueAt(indexs[i], 0);
                model2.addRow(row);
            }
    
    
        
        
    }//GEN-LAST:event_TransferRowsActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        try {
            MessageFormat header = new MessageFormat("Pagina header");
            MessageFormat footer = new MessageFormat("Pagina 1, number, integer");
            
            jTable1.print(JTable.PrintMode.FIT_WIDTH, header, footer);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        HomeScreen hs = new HomeScreen();
        hs.setVisible(true);
        hs.setLocationRelativeTo(null);
        this.setVisible(false);
        if(hs == null){
            hs = new HomeScreen();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void DeleteRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteRowActionPerformed
       DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();
       int SelectedRowIndex = jTable2.getSelectedRow();
       model2.removeRow(SelectedRowIndex);
       
       
    }//GEN-LAST:event_DeleteRowActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DistributieLijst.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DistributieLijst.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DistributieLijst.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DistributieLijst.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DistributieLijst().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DeleteRow;
    private javax.swing.JButton TransferRows;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
