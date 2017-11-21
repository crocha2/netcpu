
package ventanas;

import clasesPrincipales.Entradas;
import conMySql.GenerarNumeros;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cotizaciones extends javax.swing.JFrame {


    public Cotizaciones() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        //this.setResizable(false);
        this.setTitle("CPU System Service S.A.S - COTIZACIONES");
        //numeros();
        //txtSec.setEnabled(false);
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        numero = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        numero4 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        numero5 = new javax.swing.JTextField();
        numero6 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        numero2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        numero8 = new javax.swing.JTextField();
        numero9 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jComboBox4 = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        numero1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Bienvenido.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(770, 510, 160, 140);

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 153));
        jLabel2.setText("COTIZACIÓN");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(310, 10, 410, 70);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Concepto", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), java.awt.Color.blue)); // NOI18N
        jPanel2.setLayout(null);

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "VENTAS - 01", "SERVICIOS APC - 02", "SERVICIOS CPU - 03", "PARTES - 04" }));
        jPanel2.add(jComboBox1);
        jComboBox1.setBounds(10, 20, 220, 23);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(20, 30, 240, 50);

        jSeparator1.setBackground(new java.awt.Color(0, 0, 102));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 153));
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(20, 90, 890, 10);
        jPanel1.add(numero);
        numero.setBounds(750, 50, 160, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 153));
        jLabel3.setText("No.");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(710, 60, 40, 20);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Generales", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel4.setLayout(null);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 153));
        jLabel8.setText("CIUDAD");
        jPanel4.add(jLabel8);
        jLabel8.setBounds(700, 20, 80, 20);
        jPanel4.add(jDateChooser2);
        jDateChooser2.setBounds(20, 40, 170, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 153));
        jLabel9.setText("FECHA");
        jPanel4.add(jLabel9);
        jLabel9.setBounds(20, 20, 40, 20);
        jPanel4.add(numero4);
        numero4.setBounds(700, 40, 170, 30);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 153));
        jLabel10.setText("CLIENTE");
        jPanel4.add(jLabel10);
        jLabel10.setBounds(210, 20, 80, 20);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 153));
        jLabel11.setText("PAIS");
        jPanel4.add(jLabel11);
        jLabel11.setBounds(510, 20, 80, 20);
        jPanel4.add(numero5);
        numero5.setBounds(210, 40, 280, 30);
        jPanel4.add(numero6);
        numero6.setBounds(510, 40, 170, 30);

        jPanel1.add(jPanel4);
        jPanel4.setBounds(20, 110, 890, 90);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ITEM", "CANT", "DESCRIPCIÓN", "V.UNITARIO", "V.TOTAL", "ID"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(420, 210, 490, 240);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos De Producto", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel5.setLayout(null);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 153));
        jLabel12.setText("V. TOTAL");
        jPanel5.add(jLabel12);
        jLabel12.setBounds(220, 20, 80, 20);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 153));
        jLabel13.setText("CANT.");
        jPanel5.add(jLabel13);
        jLabel13.setBounds(20, 20, 50, 20);
        jPanel5.add(numero2);
        numero2.setBounds(220, 40, 150, 30);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 153));
        jLabel14.setText("DESCRIPCIÓN");
        jPanel5.add(jLabel14);
        jLabel14.setBounds(20, 80, 120, 20);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 153));
        jLabel15.setText("V. UNITARIO");
        jPanel5.add(jLabel15);
        jLabel15.setBounds(80, 20, 80, 20);
        jPanel5.add(numero8);
        numero8.setBounds(20, 40, 50, 30);
        jPanel5.add(numero9);
        numero9.setBounds(80, 40, 130, 30);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane4.setViewportView(jTextArea2);

        jPanel5.add(jScrollPane4);
        jScrollPane4.setBounds(20, 100, 350, 96);

        jButton4.setText("QUITAR");
        jPanel5.add(jButton4);
        jButton4.setBounds(260, 200, 110, 30);

        jButton5.setText("INSERTAR");
        jPanel5.add(jButton5);
        jButton5.setBounds(20, 200, 110, 30);

        jButton6.setText("EDITAR");
        jPanel5.add(jButton6);
        jButton6.setBounds(140, 200, 110, 30);

        jPanel1.add(jPanel5);
        jPanel5.setBounds(20, 210, 390, 250);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos De Producto", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), java.awt.Color.red)); // NOI18N
        jPanel3.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("TIEMPO DE REALIZACION DE SERVICIO:");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(20, 110, 250, 20);

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("FORMA DE PAGO:");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(20, 70, 120, 20);

        jButton1.setText("QUITAR");
        jPanel3.add(jButton1);
        jButton1.setBounds(260, 200, 110, 30);

        jButton2.setText("INSERTAR");
        jPanel3.add(jButton2);
        jButton2.setBounds(20, 200, 110, 30);

        jButton3.setText("EDITAR");
        jPanel3.add(jButton3);
        jButton3.setBounds(140, 200, 110, 30);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" }));
        jPanel3.add(jComboBox2);
        jComboBox2.setBounds(400, 100, 60, 30);

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("...........................................");
        jPanel3.add(jLabel7);
        jLabel7.setBounds(220, 30, 170, 20);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" }));
        jPanel3.add(jComboBox3);
        jComboBox3.setBounds(400, 20, 60, 30);

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ANTICIPADO", "CONTADO", "TREINTA (30) DIAS RECIBO FECHA DE FACTIRA", "TREINTA (45) DIAS RECIBO FECHA DE FACTIRA", "TREINTA (60) DIAS RECIBO FECHA DE FACTIRA" }));
        jPanel3.add(jComboBox4);
        jComboBox4.setBounds(180, 60, 280, 30);

        jLabel16.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("VALIDEZ DE LA OFERTA (DIAS):");
        jPanel3.add(jLabel16);
        jLabel16.setBounds(20, 30, 200, 20);

        jLabel17.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("..............................");
        jPanel3.add(jLabel17);
        jLabel17.setBounds(270, 110, 120, 20);

        jLabel18.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("...........");
        jPanel3.add(jLabel18);
        jLabel18.setBounds(130, 70, 50, 20);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(20, 470, 480, 170);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 153));
        jLabel4.setText("V. TOTAL");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(570, 470, 80, 20);
        jPanel1.add(numero1);
        numero1.setBounds(570, 490, 150, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 934, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Cotizaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cotizaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cotizaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cotizaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cotizaciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField numero;
    private javax.swing.JTextField numero1;
    private javax.swing.JTextField numero2;
    private javax.swing.JTextField numero4;
    private javax.swing.JTextField numero5;
    private javax.swing.JTextField numero6;
    private javax.swing.JTextField numero8;
    private javax.swing.JTextField numero9;
    // End of variables declaration//GEN-END:variables
}
