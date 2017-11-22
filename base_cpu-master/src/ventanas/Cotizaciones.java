
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
        panelProducto.setVisible(false);
        panelServicio.setVisible(false);
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cmbConcepto = new javax.swing.JComboBox();
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
        panelProducto = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox();
        jComboBox4 = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox();
        panelServicio = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jComboBox6 = new javax.swing.JComboBox();
        jComboBox7 = new javax.swing.JComboBox();
        jLabel22 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        numero10 = new javax.swing.JTextField();
        numero7 = new javax.swing.JTextField();
        numero3 = new javax.swing.JTextField();
        numero1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 153));
        jLabel2.setText("COTIZACIÓN");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(310, 10, 410, 70);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Concepto", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), java.awt.Color.blue)); // NOI18N
        jPanel2.setLayout(null);

        cmbConcepto.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        cmbConcepto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "VENTAS - 01", "SERVICIOS APC - 02", "SERVICIOS CPU - 03", "PARTES - 04" }));
        cmbConcepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbConceptoActionPerformed(evt);
            }
        });
        jPanel2.add(cmbConcepto);
        cmbConcepto.setBounds(10, 20, 220, 23);

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
        jScrollPane3.setBounds(420, 210, 490, 190);

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

        panelProducto.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Del Producto", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), java.awt.Color.red)); // NOI18N
        panelProducto.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("TIEMPO DE ENTREGA:");
        panelProducto.add(jLabel5);
        jLabel5.setBounds(20, 120, 140, 20);

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("FORMA DE PAGO:");
        panelProducto.add(jLabel6);
        jLabel6.setBounds(20, 70, 120, 20);

        jButton1.setText("QUITAR");
        panelProducto.add(jButton1);
        jButton1.setBounds(260, 200, 110, 30);

        jButton2.setText("INSERTAR");
        panelProducto.add(jButton2);
        jButton2.setBounds(20, 200, 110, 30);

        jButton3.setText("EDITAR");
        panelProducto.add(jButton3);
        jButton3.setBounds(140, 200, 110, 30);

        jComboBox3.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" }));
        panelProducto.add(jComboBox3);
        jComboBox3.setBounds(20, 40, 90, 20);

        jComboBox4.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ANTICIPADO", "CONTADO", "(30) DIAS RECIBO FECHA DE FACTURA", "(45) DIAS RECIBO FECHA DE FACTURA", "(60) DIAS RECIBO FECHA DE FACTURA" }));
        panelProducto.add(jComboBox4);
        jComboBox4.setBounds(20, 90, 250, 20);

        jLabel16.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("VALIDEZ DE LA OFERTA (DIAS):");
        panelProducto.add(jLabel16);
        jLabel16.setBounds(20, 20, 200, 20);

        jComboBox5.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "INMEDIATO", "1 dia despues de recibida la orden de compra", "2 dias despues de recibida la orden de compra", "3 dias despues de recibida la orden de compra", "4 dias despues de recibida la orden de compra", "5 dias despues de recibida la orden de compra", "6 dias despues de recibida la orden de compra", "7 dias despues de recibida la orden de compra", "8 dias despues de recibida la orden de compra", "9 dias despues de recibida la orden de compra", "10 dias despues de recibida la orden de compra", "11 dias despues de recibida la orden de compra", "12 dias despues de recibida la orden de compra", "13 dias despues de recibida la orden de compra", "14 dias despues de recibida la orden de compra", "15 dias despues de recibida la orden de compra", "16 dias despues de recibida la orden de compra", "17 dias despues de recibida la orden de compra", "18 dias despues de recibida la orden de compra", "19 dias despues de recibida la orden de compra", "20 dias despues de recibida la orden de compra", "21 dias despues de recibida la orden de compra", "22 dias despues de recibida la orden de compra", "23 dias despues de recibida la orden de compra", "24 dias despues de recibida la orden de compra", "25 dias despues de recibida la orden de compra", "26 dias despues de recibida la orden de compra", "27 dias despues de recibida la orden de compra", "28 dias despues de recibida la orden de compra", "29 dias despues de recibida la orden de compra", "30 dias despues de recibida la orden de compra", "31 dias despues de recibida la orden de compra", "32 dias despues de recibida la orden de compra", "33 dias despues de recibida la orden de compra", "34 dias despues de recibida la orden de compra", "35 dias despues de recibida la orden de compra", "36 dias despues de recibida la orden de compra", "37 dias despues de recibida la orden de compra", "38 dias despues de recibida la orden de compra", "39 dias despues de recibida la orden de compra", "40 dias despues de recibida la orden de compra", "41 dias despues de recibida la orden de compra", "42 dias despues de recibida la orden de compra", "43 dias despues de recibida la orden de compra", "44 dias despues de recibida la orden de compra", "45 dias despues de recibida la orden de compra", "46 dias despues de recibida la orden de compra", "47 dias despues de recibida la orden de compra", "48 dias despues de recibida la orden de compra", "49 dias despues de recibida la orden de compra", "50 dias despues de recibida la orden de compra", "51 dias despues de recibida la orden de compra", "52 dias despues de recibida la orden de compra", "53 dias despues de recibida la orden de compra", "54 dias despues de recibida la orden de compra", "55 dias despues de recibida la orden de compra", "56 dias despues de recibida la orden de compra", "57 dias despues de recibida la orden de compra", "58 dias despues de recibida la orden de compra", "59 dias despues de recibida la orden de compra", "60 dias despues de recibida la orden de compra" }));
        panelProducto.add(jComboBox5);
        jComboBox5.setBounds(20, 140, 330, 20);

        jPanel1.add(panelProducto);
        panelProducto.setBounds(20, 470, 390, 180);

        panelServicio.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Del Servicio", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), java.awt.Color.red)); // NOI18N
        panelServicio.setLayout(null);

        jLabel19.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setText("TIEMPO REALIZACION DE SERVICIO:");
        panelServicio.add(jLabel19);
        jLabel19.setBounds(20, 120, 230, 20);

        jLabel20.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setText("FORMA DE PAGO:");
        panelServicio.add(jLabel20);
        jLabel20.setBounds(20, 70, 120, 20);

        jButton7.setText("QUITAR");
        panelServicio.add(jButton7);
        jButton7.setBounds(260, 200, 110, 30);

        jButton8.setText("INSERTAR");
        panelServicio.add(jButton8);
        jButton8.setBounds(20, 200, 110, 30);

        jButton9.setText("EDITAR");
        panelServicio.add(jButton9);
        jButton9.setBounds(140, 200, 110, 30);

        jComboBox6.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" }));
        panelServicio.add(jComboBox6);
        jComboBox6.setBounds(20, 40, 90, 20);

        jComboBox7.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ANTICIPADO", "CONTADO", "(30) DIAS RECIBO FECHA DE FACTURA", "(45) DIAS RECIBO FECHA DE FACTURA", "(60) DIAS RECIBO FECHA DE FACTURA" }));
        panelServicio.add(jComboBox7);
        jComboBox7.setBounds(20, 90, 260, 20);

        jLabel22.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 102, 102));
        jLabel22.setText("VALIDEZ DE LA OFERTA (DIAS):");
        panelServicio.add(jLabel22);
        jLabel22.setBounds(20, 20, 200, 20);

        jComboBox2.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "INMEDIATO", "DE COMUN ACUERDO", "45 DIAS DESPUES DE LA ORDEN DEL PEDIDO", "60 DIAS DESPUES DE LA ORDEN DEL PEDIDO" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        panelServicio.add(jComboBox2);
        jComboBox2.setBounds(20, 140, 320, 20);

        jPanel1.add(panelServicio);
        panelServicio.setBounds(20, 470, 390, 180);
        jPanel1.add(jSeparator2);
        jSeparator2.setBounds(670, 550, 240, 0);

        jPanel3.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 153));
        jLabel4.setText("SUB-TOTAL");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(10, 20, 70, 20);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 153));
        jLabel7.setText("FLETES");
        jPanel3.add(jLabel7);
        jLabel7.setBounds(10, 50, 70, 20);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 153));
        jLabel17.setText("IVA");
        jPanel3.add(jLabel17);
        jLabel17.setBounds(10, 80, 70, 20);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 153, 51));
        jLabel18.setText("GRAN TOTAL");
        jPanel3.add(jLabel18);
        jLabel18.setBounds(10, 120, 80, 20);
        jPanel3.add(numero10);
        numero10.setBounds(100, 110, 160, 30);
        jPanel3.add(numero7);
        numero7.setBounds(100, 70, 160, 30);
        jPanel3.add(numero3);
        numero3.setBounds(100, 40, 160, 30);
        jPanel3.add(numero1);
        numero1.setBounds(100, 10, 160, 30);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(420, 410, 270, 150);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Bienvenido.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(750, 500, 170, 140);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 934, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void cmbConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbConceptoActionPerformed

        if(cmbConcepto.getSelectedIndex()==1||cmbConcepto.getSelectedIndex()==2){
            panelProducto.setVisible(false);
            panelServicio.setVisible(true);
        }else{
            panelServicio.setVisible(false);
            panelProducto.setVisible(true);
        }
        
        
        
// TODO add your handling code here:
    }//GEN-LAST:event_cmbConceptoActionPerformed

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
    private javax.swing.JComboBox cmbConcepto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JComboBox jComboBox7;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
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
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField numero;
    private javax.swing.JTextField numero1;
    private javax.swing.JTextField numero10;
    private javax.swing.JTextField numero2;
    private javax.swing.JTextField numero3;
    private javax.swing.JTextField numero4;
    private javax.swing.JTextField numero5;
    private javax.swing.JTextField numero6;
    private javax.swing.JTextField numero7;
    private javax.swing.JTextField numero8;
    private javax.swing.JTextField numero9;
    private javax.swing.JPanel panelProducto;
    private javax.swing.JPanel panelServicio;
    // End of variables declaration//GEN-END:variables
}
