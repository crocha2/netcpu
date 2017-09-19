/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.awt.Image;
import java.awt.Toolkit;


/**
 *

/**
 *
 * @author CPU_SYS
 */
public class Formatos_Oper extends javax.swing.JFrame {

    /**
     * Creates new form Formatos
     */
    public Formatos_Oper() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("FORMATOS");
        
    }
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imagenes/CPU_new_2.png"));
        return retValue;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        btnSalir2 = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        btnEntrada1 = new javax.swing.JButton();
        btnSalida1 = new javax.swing.JButton();
        btnEnvios1 = new javax.swing.JButton();
        btnCotizacion = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 170, 10));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Formatos");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        btnSalir2.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalir2.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir2.setText("Salir");
        btnSalir2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir2ActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        btnVolver.setBackground(new java.awt.Color(51, 153, 255));
        btnVolver.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        getContentPane().add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 260, 70, -1));

        btnEntrada1.setBackground(new java.awt.Color(255, 255, 255));
        btnEntrada1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEntrada1.setForeground(new java.awt.Color(255, 255, 255));
        btnEntrada1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/entrad.png"))); // NOI18N
        btnEntrada1.setText("ENTRADA");
        btnEntrada1.setBorder(null);
        btnEntrada1.setBorderPainted(false);
        btnEntrada1.setContentAreaFilled(false);
        btnEntrada1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEntrada1.setIconTextGap(-1);
        btnEntrada1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnEntrada1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEntrada1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrada1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnEntrada1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 90, 90));

        btnSalida1.setBackground(new java.awt.Color(255, 255, 255));
        btnSalida1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalida1.setForeground(new java.awt.Color(255, 255, 255));
        btnSalida1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/salid.png"))); // NOI18N
        btnSalida1.setText("SALIDA");
        btnSalida1.setBorder(null);
        btnSalida1.setBorderPainted(false);
        btnSalida1.setContentAreaFilled(false);
        btnSalida1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalida1.setIconTextGap(-1);
        btnSalida1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnSalida1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalida1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalida1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalida1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 90, 90));

        btnEnvios1.setBackground(new java.awt.Color(255, 255, 255));
        btnEnvios1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEnvios1.setForeground(new java.awt.Color(255, 255, 255));
        btnEnvios1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/envi.png"))); // NOI18N
        btnEnvios1.setText("ENVIOS");
        btnEnvios1.setBorder(null);
        btnEnvios1.setBorderPainted(false);
        btnEnvios1.setContentAreaFilled(false);
        btnEnvios1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEnvios1.setIconTextGap(-1);
        btnEnvios1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnEnvios1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEnvios1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnvios1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnEnvios1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 90, 90));

        btnCotizacion.setBackground(new java.awt.Color(255, 255, 255));
        btnCotizacion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCotizacion.setForeground(new java.awt.Color(255, 255, 255));
        btnCotizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/coti.png"))); // NOI18N
        btnCotizacion.setText("GARANTIAS");
        btnCotizacion.setBorder(null);
        btnCotizacion.setBorderPainted(false);
        btnCotizacion.setContentAreaFilled(false);
        btnCotizacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCotizacion.setIconTextGap(-1);
        btnCotizacion.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnCotizacion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCotizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCotizacionActionPerformed(evt);
            }
        });
        getContentPane().add(btnCotizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 90, 90));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ima2.2.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalir2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir2ActionPerformed

        Bienvenida obj = new Bienvenida();
        obj.setVisible(true);
        dispose();
       
// TODO add your handling code here:
    }//GEN-LAST:event_btnSalir2ActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed

        Principal_Oper obj = new Principal_Oper();
        obj.setVisible(true);
        dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnEntrada1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrada1ActionPerformed

        Entrada_Oper obj = new Entrada_Oper();
        obj.setVisible(true);
        dispose(); 

        // TODO add your handling code here:
    }//GEN-LAST:event_btnEntrada1ActionPerformed

    private void btnSalida1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalida1ActionPerformed

        Salidass_Oper obj = new Salidass_Oper();
        obj.setVisible(true);
        dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalida1ActionPerformed

    private void btnEnvios1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnvios1ActionPerformed

        Envio_Oper obj = new Envio_Oper();
        obj.setVisible(true);
        dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnEnvios1ActionPerformed

    private void btnCotizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCotizacionActionPerformed

        Garantias_Oper obj = new Garantias_Oper();
        obj.setVisible(true);
        dispose();
        
// TODO add your handling code here:
    }//GEN-LAST:event_btnCotizacionActionPerformed

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
            java.util.logging.Logger.getLogger(Formatos_Oper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Formatos_Oper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Formatos_Oper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Formatos_Oper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Formatos_Oper().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCotizacion;
    private javax.swing.JButton btnEntrada1;
    private javax.swing.JButton btnEnvios1;
    private javax.swing.JButton btnSalida1;
    private javax.swing.JButton btnSalir2;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
