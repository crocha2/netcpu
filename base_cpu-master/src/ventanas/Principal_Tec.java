/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import clasesPrincipales.Entradas;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author CPU_SYS
 */
public class Principal_Tec extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    public Principal_Tec() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("CPU System Service S.A.S - PRINCIPAL");
        /*
        this.jLabel1.add(this.lblonline);
        usuarios usu = new usuarios();
        this.lblonline.setText("Conectado: "+usu.getNombre());
        */
        aviso();
    }
    
    public void aviso() {
        String str = "";
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT numero, fecha, empresa, elemento, modelo, serie  FROM `entradas` WHERE fecha = DATE_SUB(CURDATE(),INTERVAL 5 DAY) \n"
                    + "AND garantia = 'SI' \n"
                    + "AND estado = 'REVISION'");
            while (rs.next()) {
                Entradas en = new Entradas();
                en.setFecha(rs.getString("fecha"));
                en.setNumero(rs.getString("numero"));
                en.setEmpresa(rs.getString("empresa"));
                en.setElemento(rs.getString("elemento"));
                en.setModelo(rs.getString("modelo"));
                en.setSerie(rs.getString("serie"));
                str = "* "+en.getFecha()+"\n"+"* "+en.getNumero()+"\n"+"* "+en.getEmpresa()+"\n"+"* "+en.getElemento()+"\n"+"* "+en.getModelo()+"\n"+"* "+en.getSerie()+
                        "\n\n"+"_____________________";
                JOptionPane.showMessageDialog(this, "GARANTIAS PENDIENTES\n\n"+str+"\n");
            }
            cn.close();
        } catch (SQLException | HeadlessException e) {
            System.out.println("error:" + e);;
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSalir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        lblonline = new javax.swing.JLabel();
        btnNuevoCliente1 = new javax.swing.JButton();
        btnClientes1 = new javax.swing.JButton();
        btnFormatos1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSalir.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Principal");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 160, 50));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 330, 10));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 330, 10));

        lblonline.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lblonline, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 130, 20));

        btnNuevoCliente1.setBackground(new java.awt.Color(255, 255, 255));
        btnNuevoCliente1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnNuevoCliente1.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevoCliente1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/newCli.png"))); // NOI18N
        btnNuevoCliente1.setText("NUEVO CLIENTE");
        btnNuevoCliente1.setBorder(null);
        btnNuevoCliente1.setBorderPainted(false);
        btnNuevoCliente1.setContentAreaFilled(false);
        btnNuevoCliente1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevoCliente1.setIconTextGap(-1);
        btnNuevoCliente1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnNuevoCliente1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNuevoCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoCliente1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnNuevoCliente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 90, 90));

        btnClientes1.setBackground(new java.awt.Color(255, 255, 255));
        btnClientes1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnClientes1.setForeground(new java.awt.Color(255, 255, 255));
        btnClientes1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/clientes.png"))); // NOI18N
        btnClientes1.setText("CLIENTES");
        btnClientes1.setBorder(null);
        btnClientes1.setBorderPainted(false);
        btnClientes1.setContentAreaFilled(false);
        btnClientes1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnClientes1.setIconTextGap(-1);
        btnClientes1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnClientes1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnClientes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientes1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnClientes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 90, 90));

        btnFormatos1.setBackground(new java.awt.Color(255, 255, 255));
        btnFormatos1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnFormatos1.setForeground(new java.awt.Color(255, 255, 255));
        btnFormatos1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/formatos.png"))); // NOI18N
        btnFormatos1.setText("FORMATOS");
        btnFormatos1.setBorder(null);
        btnFormatos1.setBorderPainted(false);
        btnFormatos1.setContentAreaFilled(false);
        btnFormatos1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFormatos1.setIconTextGap(-1);
        btnFormatos1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnFormatos1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFormatos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFormatos1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnFormatos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, 90, 90));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ima_2.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 220));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed

        Object[] opciones = {"Aceptar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(rootPane, "¿En realidad desea SALIR?", "Mensaje de Confirmacion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
        if (eleccion == JOptionPane.YES_OPTION) {
            Bienvenida obj = new Bienvenida();
            obj.setVisible(true);
            dispose();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnNuevoCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoCliente1ActionPerformed

        Nuevo_Cliente obj = new Nuevo_Cliente();
        obj.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevoCliente1ActionPerformed

    private void btnClientes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientes1ActionPerformed

        Tabla_Clientes_Admin obj = new Tabla_Clientes_Admin();
        obj.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_btnClientes1ActionPerformed

    private void btnFormatos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFormatos1ActionPerformed

        Formatos_Tec obj = new Formatos_Tec();
        obj.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_btnFormatos1ActionPerformed

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
            java.util.logging.Logger.getLogger(Principal_Tec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal_Tec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal_Tec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal_Tec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal_Tec().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClientes1;
    private javax.swing.JButton btnFormatos1;
    private javax.swing.JButton btnNuevoCliente1;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblonline;
    // End of variables declaration//GEN-END:variables
}