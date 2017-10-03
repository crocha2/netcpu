/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import clasesPrincipales.Salidas;
import clasesPrincipales.clientes;
import conMySql.clienteMySql;
import conMySql.salidaMySql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import reportes.GenerarReportes;

/**
 *
 * @author CPU_SYS
 */
public class Editar_Salida extends javax.swing.JFrame {

    ArrayList<Salidass> salida;
    //salidaDB db = new salidaDB();
    salidaMySql db = new salidaMySql();
    
    ArrayList<clientes> cliente;
    //salidaDB db = new salidaDB();
    clienteMySql dbCli = new clienteMySql();
    
    Tecnico tec = new Tecnico();

    /**
     * Creates new form Entrada
     */
    public Editar_Salida() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("CPU System Service S.A.S - FACTURAS DE SALIDA");
        //numeros();
        txtSec.setEnabled(false);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
        txtIdCli.setEnabled(false);
        traerDatos();
    }
    
    public void traerDatos() {

        Tecnico obj = new Tecnico();
        txtIdCli.setText(obj.id_cli_salida);
        txtSec.setText(obj.numero_salida);
        txtFechaFact.setText(obj.fecha_salida);
        txtEquipo.setText(obj.equipo_salida);
        txtModelo.setText(obj.modelo_salida);
        txtSerie.setText(obj.serie_salida);
        txtEmpresa.setText(obj.cliente_salida);
        txtTelefono.setText(obj.telefono_salida);
        txtCorreo.setText(obj.correo_salida);
        areaComentario.setText(obj.observacion_salida);
    
}

    public void limpiar() {
        txtSec.setText("");
        txtFechaFact.setText("");
        txtEmpresa.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtEquipo.setText("");
        areaComentario.setText("");
        txtModelo.setText("");
        txtSerie.setText("");
        txtEmpresa.requestFocus();
    }

   
    /*
     void numeros() {
     int j;
     String c = "";
     String SQL = "SELECT MAX(numero) AS numero FROM salidas";
     try {
     Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/basecpu", "root", "8020123496");
     Statement st = cn.createStatement();
     ResultSet rs = st.executeQuery(SQL);
     if (rs.next()) {
     c = rs.getString("numero");
     }
     System.out.println(c);
     if (c == null) {
     txtSec.setText("NS000000001");
     System.out.println(c);
     } else {
     char r1 = c.charAt(2);
     char r2 = c.charAt(3);
     char r3 = c.charAt(4);
     char r4 = c.charAt(5);
     char r5 = c.charAt(6);
     char r6 = c.charAt(7);
     char r7 = c.charAt(8);
     char r8 = c.charAt(9);
     char r9 = c.charAt(10);

     System.out.println("" + r1 + r2 + r3 + r4 + r5 + r6 + r7 + r8 + r9);
     String juntar = "" + r1 + r2 + r3 + r4 + r5 + r6 + r7 + r8 + r9;
     int var = Integer.parseInt(juntar);

     System.out.println("\n lo que vale: " + var);
     GenerarNumeros gen = new GenerarNumeros();
     gen.generarSalidas(var);

     txtSec.setDisabledTextColor(java.awt.Color.BLUE);
     txtSec.setText(gen.serie());
     }
     } catch (SQLException | NumberFormatException ex) {
     Logger.getLogger(Facturas_Salida.class.getName()).log(Level.SEVERE, null, ex);
     }
     }
     */

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        txtEmpresa = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        txtEquipo = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaComentario = new javax.swing.JTextArea();
        jSeparator9 = new javax.swing.JSeparator();
        txtModelo = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtSerie = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        btnEliminar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        btnBusca2 = new javax.swing.JButton();
        jSeparator11 = new javax.swing.JSeparator();
        txtFechaFact = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtSec = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtIdCli = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabelFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 255, 153));
        jLabel2.setText("Editar Salida");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Empresa");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, 20));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 660, 10));
        getContentPane().add(txtEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 290, -1));
        getContentPane().add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 180, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Correo");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 120, 50, 20));

        txtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoActionPerformed(evt);
            }
        });
        getContentPane().add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 120, 200, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Telefono");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 50, 20));
        getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 270, 10));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(153, 255, 153));
        jLabel22.setText("DATOS DEL EQUIPO");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, -1, -1));
        getContentPane().add(txtEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 170, -1));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Equipo");
        getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 40, 20));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Comentario");
        getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, 20));
        getContentPane().add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 210, 10));

        areaComentario.setColumns(20);
        areaComentario.setRows(5);
        jScrollPane1.setViewportView(areaComentario);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 470, 110));
        getContentPane().add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 190, 250, 10));

        txtModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtModeloActionPerformed(evt);
            }
        });
        getContentPane().add(txtModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, 170, -1));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Modelo");
        getContentPane().add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, 50, 20));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Serie");
        getContentPane().add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 210, 40, 20));
        getContentPane().add(txtSerie, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 210, 150, -1));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 260, 10, 70));

        btnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(null);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEliminar.setIconTextGap(-1);
        btnEliminar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 270, 50, 50));

        btnEditar.setBackground(new java.awt.Color(255, 255, 255));
        btnEditar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setBorder(null);
        btnEditar.setBorderPainted(false);
        btnEditar.setContentAreaFilled(false);
        btnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditar.setIconTextGap(-1);
        btnEditar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 270, 40, 50));

        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 260, 10, 70));
        getContentPane().add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 330, 150, 10));

        btnBusca2.setBackground(new java.awt.Color(255, 255, 255));
        btnBusca2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBusca2.setForeground(new java.awt.Color(255, 255, 255));
        btnBusca2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/juega.png"))); // NOI18N
        btnBusca2.setBorder(null);
        btnBusca2.setBorderPainted(false);
        btnBusca2.setContentAreaFilled(false);
        btnBusca2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBusca2.setIconTextGap(-1);
        btnBusca2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnBusca2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBusca2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusca2ActionPerformed(evt);
            }
        });
        getContentPane().add(btnBusca2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 260, 30, 30));
        getContentPane().add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 260, 150, 10));

        txtFechaFact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaFactActionPerformed(evt);
            }
        });
        getContentPane().add(txtFechaFact, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 110, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("FECHA");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, -1, 20));

        txtSec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSecActionPerformed(evt);
            }
        });
        getContentPane().add(txtSec, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 160, -1));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(153, 255, 153));
        jLabel27.setText("No. REM");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 60, 20));

        txtIdCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdCliActionPerformed(evt);
            }
        });
        getContentPane().add(txtIdCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 80, -1));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(153, 255, 153));
        jLabel29.setText("ID_CLI");
        getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, 50, 20));

        jLabelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ima2.2_ampliada.png"))); // NOI18N
        getContentPane().add(jLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    private void txtModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtModeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModeloActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        if (txtEmpresa.getText().equals("") || txtTelefono.getText().equals("") || txtCorreo.getText().equals("")
                || txtEquipo.getText().equals("") || txtModelo.getText().equals("") || txtSerie.getText().equals("") || areaComentario.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Debe llenar todos los campos", "", JOptionPane.INFORMATION_MESSAGE);
        } else {

            Salidas sal = new Salidas();
            sal.setId_cli(Integer.parseInt(txtIdCli.getText()));
            sal.setNumero(txtSec.getText());
            sal.setFecha(txtFechaFact.getText().toUpperCase());
            sal.setEmpresa(txtEmpresa.getText().toUpperCase());
            sal.setTelefono(txtTelefono.getText().toUpperCase());
            sal.setCorreo(txtCorreo.getText().toUpperCase());
            sal.setEquipo(txtEquipo.getText().toUpperCase());
            sal.setModelo(txtModelo.getText().toUpperCase());
            sal.setSerie(txtSerie.getText().toUpperCase());
            sal.setComentario(areaComentario.getText().toUpperCase());

            Object[] opciones = {"Aceptar", "Cancelar"};
            int eleccion = JOptionPane.showOptionDialog(rootPane, "¿En realidad desea ELIMINAR este registro?", "Mensaje de Confirmacion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
            if (eleccion == JOptionPane.YES_OPTION) {
                db.EliminarSalida(sal);
                JOptionPane.showMessageDialog(this, "Datos ELIMINADOS exitosamente", "", JOptionPane.INFORMATION_MESSAGE);
                tec.LimpiarSalidas();
                tec.ListarSalidas();
                limpiar();
                this.setVisible(false);
            } else {
                limpiar();
                this.setVisible(false);
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

        if (txtEmpresa.getText().equals("") || txtTelefono.getText().equals("") || txtCorreo.getText().equals("")
                || txtEquipo.getText().equals("") || txtModelo.getText().equals("") || txtSerie.getText().equals("") || areaComentario.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Debe llenar todos los campos", "", JOptionPane.INFORMATION_MESSAGE);
        } else {

            Salidas sal = new Salidas();
            clientes cli = new clientes();

            sal.setNumero(txtSec.getText());
            sal.setFecha(txtFechaFact.getText().toUpperCase());
            sal.setEmpresa(txtEmpresa.getText().toUpperCase());
            sal.setTelefono(txtTelefono.getText().toUpperCase());
            sal.setCorreo(txtCorreo.getText().toUpperCase());
            sal.setEquipo(txtEquipo.getText().toUpperCase());
            sal.setModelo(txtModelo.getText().toUpperCase());
            sal.setSerie(txtSerie.getText().toUpperCase());
            sal.setComentario(areaComentario.getText().toUpperCase());
            sal.setId_cli(Integer.parseInt(txtIdCli.getText()));

            cli.setId_cliente(Integer.parseInt(txtIdCli.getText()));
            cli.setNombre_cliente(txtEmpresa.getText().toUpperCase());
            cli.setTelefono_cliente(txtTelefono.getText().toUpperCase());
            cli.setCorreo_cliente(txtCorreo.getText().toUpperCase());

            Object[] opciones = {"Aceptar", "Cancelar"};
            int eleccion = JOptionPane.showOptionDialog(rootPane, "¿En realidad desea EDITAR este registro?", "Mensaje de Confirmacion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
            if (eleccion == JOptionPane.YES_OPTION) {
                db.EditarTablaSalida(sal);
                db.EditarClienteSalida(cli);
                JOptionPane.showMessageDialog(this, "Datos EDITADOS exitosamente", "", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
                tec.LimpiarSalidas();
                tec.ListarSalidas();
                this.setVisible(false);
            } else {
                limpiar();
                this.setVisible(false);
            }          
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnBusca2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusca2ActionPerformed

        btnEditar.setEnabled(true);
        btnEliminar.setEnabled(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_btnBusca2ActionPerformed

    private void txtFechaFactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaFactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaFactActionPerformed

    private void txtSecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSecActionPerformed

    private void txtIdCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdCliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdCliActionPerformed

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
            java.util.logging.Logger.getLogger(Editar_Salida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Editar_Salida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Editar_Salida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Editar_Salida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Editar_Salida().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaComentario;
    private javax.swing.JButton btnBusca2;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabelFondo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtEmpresa;
    private javax.swing.JTextField txtEquipo;
    private javax.swing.JTextField txtFechaFact;
    private javax.swing.JTextField txtIdCli;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtSec;
    private javax.swing.JTextField txtSerie;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
