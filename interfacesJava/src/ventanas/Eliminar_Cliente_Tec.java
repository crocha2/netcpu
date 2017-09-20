/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import clasesPrincipales.clientes;
import com.mxrck.autocompleter.TextAutoCompleter;
import conMySql.clienteMySql;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author CPU_SYS
 */
public class Eliminar_Cliente_Tec extends javax.swing.JFrame {

    ArrayList<clientes> cliente;
    //clienteDB db = new clienteDB();
    clienteMySql db = new clienteMySql();

    /**
     * Creates new form Tabla_Clientes
     */
    public Eliminar_Cliente_Tec() {
        initComponents();
        ListarDatos();
        LimpirTabla();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("CPU System Service S.A.S - ELIMINAR CLIENTES");
        //CargarCmbCliente();
        autoComplete();
        txtID.setEnabled(false);
        txtNitCliente.setEnabled(false);
        txtNombreCliente.setEnabled(false);
        txtTelefonoCliente.setEnabled(false);
        txtDireccionCliente.setEnabled(false);
        txtCiudadCliente.setEnabled(false);
        txtCorreoCliente.setEnabled(false);
        txtContactoCliente.setEnabled(false);
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imagenes/CPU_new_2.png"));
        return retValue;
    }

    // METODOS:::::::::::::::::::::::::
    public void ListarDatos() {
        cliente = db.ListClientes();
        DefaultTableModel tb = (DefaultTableModel) tabla_clientes.getModel();
        for (clientes cl : cliente) {
            tb.addRow(new Object[]{cl.getId_cliente(), cl.getNit_cliente(), cl.getNombre_cliente(), cl.getTelefono_cliente(), cl.getDireccion_cliente(), cl.getCiudad_cliente(), cl.getCorreo_cliente(), cl.getNombre_contacto()});
        }
    }

    public void LimpirTabla() {
        DefaultTableModel tb = (DefaultTableModel) tabla_clientes.getModel();
        for (int i = tb.getRowCount() - 1; i >= 0; i--) {
            tb.removeRow(i);
        }
    }
    
    public void autoComplete(){
        TextAutoCompleter TextAutoCompleter = new TextAutoCompleter(auto);
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            Statement st = (Statement)cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT nombre_cli FROM clientes");
            while (rs.next()) {
                TextAutoCompleter.addItem(rs.getString("nombre_cli"));
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error: "+e);
        }
    }
/*
    public void CargarCmbCliente() {
        try {
            Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost/basecpu", "root", "8020123496");
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT nombre_cli FROM clientes ORDER BY nombre_cli ASC");
            while (rs.next()) {
                this.cmbClientes.addItem(rs.getString("nombre_cli"));
            }
        } catch (Exception e) {
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

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnSalir1 = new javax.swing.JButton();
        btnVolver1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_clientes = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtNitCliente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTelefonoCliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCiudadCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtContactoCliente = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCorreoCliente = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtDireccionCliente = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        brnListar1 = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        auto = new javax.swing.JTextField();
        btnBusca = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabelFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Eliminar Cliente");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 590, 10));

        btnSalir1.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalir1.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir1.setText("Salir");
        btnSalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, -1, -1));

        btnVolver1.setBackground(new java.awt.Color(51, 153, 255));
        btnVolver1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnVolver1.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver1.setText("Volver");
        btnVolver1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolver1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnVolver1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 490, 70, -1));

        tabla_clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "NIT", "CLIENTE", "TELEFONO", "DIRECCION", "CIUDAD", "CORREO", "CONTACTO"
            }
        ));
        tabla_clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_clientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabla_clientes);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 820, 260));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("NIT");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));
        getContentPane().add(txtNitCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 210, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Telefono");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, -1, -1));
        getContentPane().add(txtTelefonoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 180, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Ciudad");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, -1, -1));
        getContentPane().add(txtCiudadCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 160, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Contacto");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, -1, -1));
        getContentPane().add(txtContactoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 70, 150, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nombre Cliente");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 100, -1));
        getContentPane().add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 210, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Direccion");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, -1, -1));
        getContentPane().add(txtCorreoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 120, 160, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Correo");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, -1, -1));
        getContentPane().add(txtDireccionCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 180, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("ID");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 160, -1, -1));
        getContentPane().add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 180, 70, -1));

        brnListar1.setBackground(new java.awt.Color(255, 255, 255));
        brnListar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        brnListar1.setForeground(new java.awt.Color(255, 255, 255));
        brnListar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/list.png"))); // NOI18N
        brnListar1.setBorder(null);
        brnListar1.setBorderPainted(false);
        brnListar1.setContentAreaFilled(false);
        brnListar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        brnListar1.setIconTextGap(-1);
        brnListar1.setLabel("Listar");
        brnListar1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        brnListar1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        brnListar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brnListar1ActionPerformed(evt);
            }
        });
        getContentPane().add(brnListar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 40, 50));

        btnDelete.setBackground(new java.awt.Color(255, 255, 255));
        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        btnDelete.setText("Eliminar");
        btnDelete.setBorder(null);
        btnDelete.setBorderPainted(false);
        btnDelete.setContentAreaFilled(false);
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDelete.setIconTextGap(-1);
        btnDelete.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 50, 50));
        getContentPane().add(auto, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 240, -1));

        btnBusca.setBackground(new java.awt.Color(255, 255, 255));
        btnBusca.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBusca.setForeground(new java.awt.Color(255, 255, 255));
        btnBusca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lupa2.png"))); // NOI18N
        btnBusca.setBorder(null);
        btnBusca.setBorderPainted(false);
        btnBusca.setContentAreaFilled(false);
        btnBusca.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBusca.setIconTextGap(-1);
        btnBusca.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnBusca.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaActionPerformed(evt);
            }
        });
        getContentPane().add(btnBusca, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 160, 40, 40));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(153, 255, 153));
        jLabel11.setText("Buscar");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, -1, -1));

        jLabelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ima2.2_ampliada.png"))); // NOI18N
        getContentPane().add(jLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir1ActionPerformed

        Bienvenida obj = new Bienvenida();
        obj.setVisible(true);
        dispose();

// TODO add your handling code here:
    }//GEN-LAST:event_btnSalir1ActionPerformed

    private void btnVolver1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolver1ActionPerformed

        Tabla_Clientes_Tec obj = new Tabla_Clientes_Tec();
        obj.setVisible(true);
        dispose();

// TODO add your handling code here:
    }//GEN-LAST:event_btnVolver1ActionPerformed

    private void tabla_clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_clientesMouseClicked

        int seleccion = tabla_clientes.getSelectedRow();

        txtID.setText(String.valueOf(tabla_clientes.getValueAt(seleccion, 0)));
        txtNitCliente.setText(String.valueOf(tabla_clientes.getValueAt(seleccion, 1)));
        txtNombreCliente.setText(String.valueOf(tabla_clientes.getValueAt(seleccion, 2)));
        txtTelefonoCliente.setText(String.valueOf(tabla_clientes.getValueAt(seleccion, 3)));
        txtDireccionCliente.setText(String.valueOf(tabla_clientes.getValueAt(seleccion, 4)));
        txtCiudadCliente.setText(String.valueOf(tabla_clientes.getValueAt(seleccion, 5)));
        txtCorreoCliente.setText(String.valueOf(tabla_clientes.getValueAt(seleccion, 6)));
        txtContactoCliente.setText(String.valueOf(tabla_clientes.getValueAt(seleccion, 7)));

// TODO add your handling code here:
    }//GEN-LAST:event_tabla_clientesMouseClicked

    private void brnListar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brnListar1ActionPerformed

        LimpirTabla();
        ListarDatos();

        // TODO add your handling code here:
    }//GEN-LAST:event_brnListar1ActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

        if (txtNitCliente.getText().equals("") || txtNombreCliente.getText().equals("") || txtTelefonoCliente.getText().equals("") || txtDireccionCliente.getText().equals("") || txtCiudadCliente.getText().equals("") || txtCorreoCliente.getText().equals("") || txtContactoCliente.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Selecciones un registro de la tabla", "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            clientes cli = new clientes();
            cli.setId_cliente(Integer.parseInt(txtID.getText()));
            cli.setNit_cliente(txtNitCliente.getText());
            cli.setNombre_cliente(txtNombreCliente.getText());
            cli.setTelefono_cliente(txtTelefonoCliente.getText());
            cli.setDireccion_cliente(txtDireccionCliente.getText());
            cli.setCiudad_cliente(txtCiudadCliente.getText());
            cli.setCorreo_cliente(txtCorreoCliente.getText());
            cli.setNombre_contacto(txtContactoCliente.getText());

            Object[] opciones = {"Aceptar", "Cancelar"};
            int eleccion = JOptionPane.showOptionDialog(rootPane, "¿En realidad desea ELIMINAR este registro?", "Mensaje de Confirmacion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
            if (eleccion == JOptionPane.YES_OPTION) {
                db.EliminarCliente(cli);
                JOptionPane.showMessageDialog(this, "Datos ELIMINADOS exitosamente", "", JOptionPane.INFORMATION_MESSAGE);
                LimpirTabla();
                ListarDatos();
                autoComplete();
                //this.cmbClientes.removeAllItems();
                //CargarCmbCliente();
                txtID.setText("");
                txtNitCliente.setText("");
                txtNombreCliente.setText("");
                txtTelefonoCliente.setText("");
                txtDireccionCliente.setText("");
                txtCiudadCliente.setText("");
                txtCorreoCliente.setText("");
                txtContactoCliente.setText("");
                txtNitCliente.requestFocus();

            } else {
                LimpirTabla();
                ListarDatos();
                autoComplete();
                //this.cmbClientes.removeAllItems();
                //CargarCmbCliente();
                txtID.setText("");
                txtNitCliente.setText("");
                txtNombreCliente.setText("");
                txtTelefonoCliente.setText("");
                txtDireccionCliente.setText("");
                txtCiudadCliente.setText("");
                txtCorreoCliente.setText("");
                txtContactoCliente.setText("");
                txtNitCliente.requestFocus();
            }
        }
        
// TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaActionPerformed

        try {
            String guardar = auto.getText();
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            //Statement st = cn.createStatement();
            PreparedStatement pst = cn.prepareStatement("Select * from clientes where nombre_cli = ?");
            pst.setString(1, guardar);
            //pst.setString(1, CMBID.getName());
            ResultSet rs = pst.executeQuery();
            LimpirTabla();
            if (rs.next()) {

                txtID.setText(rs.getString("id_cli").trim());
                txtNitCliente.setText(rs.getString("nit_cli").trim());
                txtNombreCliente.setText(rs.getString("nombre_cli").trim());
                txtTelefonoCliente.setText(rs.getString("telefono_cli").trim());
                txtDireccionCliente.setText(rs.getString("direccion_cli").trim());
                txtCiudadCliente.setText(rs.getString("ciudad_cli").trim());
                txtCorreoCliente.setText(rs.getString("correo_cli").trim());
                txtContactoCliente.setText(rs.getString("contacto_cli").trim());
                autoComplete();
            } else {
                JOptionPane.showMessageDialog(null, "No existe el usuario");
            }
            cn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscaActionPerformed

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
            java.util.logging.Logger.getLogger(Eliminar_Cliente_Tec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Eliminar_Cliente_Tec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Eliminar_Cliente_Tec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Eliminar_Cliente_Tec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Eliminar_Cliente_Tec().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField auto;
    private javax.swing.JButton brnListar1;
    private javax.swing.JButton btnBusca;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSalir1;
    private javax.swing.JButton btnVolver1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelFondo;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tabla_clientes;
    private javax.swing.JTextField txtCiudadCliente;
    private javax.swing.JTextField txtContactoCliente;
    private javax.swing.JTextField txtCorreoCliente;
    private javax.swing.JTextField txtDireccionCliente;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNitCliente;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtTelefonoCliente;
    // End of variables declaration//GEN-END:variables
}
