/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import clasesPrincipales.Entradas;
import clasesPrincipales.Envios;
import clasesPrincipales.clientes;
import com.mxrck.autocompleter.TextAutoCompleter;
import conMySql.GenerarNumeros;
import conMySql.clienteMySql;
import conMySql.envioMySql;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author CPU_SYS
 */
public class Envio extends javax.swing.JFrame {

    ArrayList<Entradas> entrada;
    envioMySql db = new envioMySql();

    ArrayList<clientes> cliente;
    clienteMySql dbCli = new clienteMySql();

    /**
     * Creates new form Entrada
     */
    public Envio() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("CPU System Service S.A.S - ENVIOS");
        //CargarCmbCliente();
        autoComplete();
        numerosEnvios();
        txtSec.setEnabled(false);
        txtIdCli.setEnabled(false);
        txtIdCli.setText("" + 0);
    }

    public void autoComplete() {
        TextAutoCompleter TextAutoCompleter = new TextAutoCompleter(auto);
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            Statement st = (Statement) cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT nombre_cli FROM clientes");
            while (rs.next()) {
                TextAutoCompleter.addItem(rs.getString("nombre_cli"));
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }
    /*
     public void CargarCmbCliente(){
     try {
     Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/basecpu", "root", "8020123496");
     Statement st = cn.createStatement();
     ResultSet rs = st.executeQuery("SELECT nombre_cli FROM clientes ORDER BY nombre_cli ASC");
     while (rs.next()) {
     this.cmbClientes.addItem(rs.getString("nombre_cli"));
     }
     cn.close();
     } catch (Exception e) {
     }
     }
     */

    public void limpiar() {
        txtDestinatario.setText("");
        txtATN.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtCiudad.setText("");
        areaComentario.setText("");
        txtDestinatario.requestFocus();
    }

    void numerosEnvios() {
        int j;
        String c = "";
        String SQL = "SELECT MAX(numero) AS numero FROM envios";
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                c = rs.getString("numero");
            }
            System.out.println(c);
            if (c == null) {
                txtSec.setText("EN000000001");
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
                gen.generarEnvios(var);

                txtSec.setDisabledTextColor(java.awt.Color.BLUE);
                txtSec.setText(gen.serie());
            }
        } catch (SQLException | NumberFormatException ex) {
            Logger.getLogger(Envio.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        txtATN = new javax.swing.JTextField();
        txtDestinatario = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaComentario = new javax.swing.JTextArea();
        jSeparator9 = new javax.swing.JSeparator();
        btnDescartar = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        txtCiudad = new javax.swing.JTextField();
        txtFecha = new com.toedter.calendar.JDateChooser();
        jLabel25 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btnBusca = new javax.swing.JButton();
        btnPdf = new javax.swing.JButton();
        btnGuarda = new javax.swing.JButton();
        txtSec = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel26 = new javax.swing.JLabel();
        auto = new javax.swing.JTextField();
        btnBusca1 = new javax.swing.JButton();
        txtIdCli = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabelFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Formato De Envios");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 200, 10));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 255, 153));
        jLabel6.setText("Clientes");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 50, 20));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("DESTINATARIO");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, 20));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("ATN");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, 20));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("DIRECCION");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, 20));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(153, 255, 153));
        jLabel16.setText("DATOS DE ENVIO");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, -1, -1));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 230, 10));
        getContentPane().add(txtATN, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 170, -1));
        getContentPane().add(txtDestinatario, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 170, -1));
        getContentPane().add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 170, -1));
        getContentPane().add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 170, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("CIUDAD");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 60, 20));
        getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 220, 10));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(153, 255, 153));
        jLabel22.setText("DATOS DEL EQUIPO");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 280, -1, -1));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Comentario");
        getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, -1, 20));
        getContentPane().add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 240, 10));

        areaComentario.setColumns(20);
        areaComentario.setRows(5);
        jScrollPane1.setViewportView(areaComentario);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 380, 100));
        getContentPane().add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 290, 190, 10));

        btnDescartar.setBackground(new java.awt.Color(255, 255, 255));
        btnDescartar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnDescartar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/limpiar2.png"))); // NOI18N
        btnDescartar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescartarActionPerformed(evt);
            }
        });
        getContentPane().add(btnDescartar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 40, 40));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("TELEFONO");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 60, 20));

        txtCiudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCiudadActionPerformed(evt);
            }
        });
        getContentPane().add(txtCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 170, -1));

        txtFecha.setDateFormatString("yyyy/MM/dd");
        getContentPane().add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 150, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(153, 255, 153));
        jLabel25.setText("FECHA");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, 40, 20));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 120, 110, 10));

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
        getContentPane().add(btnBusca, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 40, -1));

        btnPdf.setBackground(new java.awt.Color(255, 255, 255));
        btnPdf.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnPdf.setForeground(new java.awt.Color(255, 255, 255));
        btnPdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/generarr.png"))); // NOI18N
        btnPdf.setText("Go");
        btnPdf.setBorder(null);
        btnPdf.setBorderPainted(false);
        btnPdf.setContentAreaFilled(false);
        btnPdf.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPdf.setIconTextGap(-1);
        btnPdf.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnPdf.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPdfActionPerformed(evt);
            }
        });
        getContentPane().add(btnPdf, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 310, 60, 70));

        btnGuarda.setBackground(new java.awt.Color(255, 255, 255));
        btnGuarda.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnGuarda.setForeground(new java.awt.Color(255, 255, 255));
        btnGuarda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar_1.png"))); // NOI18N
        btnGuarda.setText("Guardar");
        btnGuarda.setBorder(null);
        btnGuarda.setBorderPainted(false);
        btnGuarda.setContentAreaFilled(false);
        btnGuarda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuarda.setIconTextGap(-1);
        btnGuarda.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnGuarda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuarda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardaActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuarda, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 320, 60, -1));
        getContentPane().add(txtSec, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, 180, -1));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 120, 10));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(153, 255, 153));
        jLabel26.setText("No. REM");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, 60, 20));
        getContentPane().add(auto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 240, -1));

        btnBusca1.setBackground(new java.awt.Color(255, 255, 255));
        btnBusca1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBusca1.setForeground(new java.awt.Color(255, 255, 255));
        btnBusca1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo_usu_mini.png"))); // NOI18N
        btnBusca1.setBorderPainted(false);
        btnBusca1.setContentAreaFilled(false);
        btnBusca1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBusca1.setIconTextGap(-1);
        btnBusca1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnBusca1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBusca1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusca1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnBusca1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, 50, -1));

        txtIdCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdCliActionPerformed(evt);
            }
        });
        getContentPane().add(txtIdCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 250, 80, -1));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("ID");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 250, 20, 20));

        jLabelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ima2.2_ampliada.png"))); // NOI18N
        getContentPane().add(jLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDescartarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescartarActionPerformed

        limpiar();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnDescartarActionPerformed

    private void txtCiudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCiudadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCiudadActionPerformed

    private void btnBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaActionPerformed

        try {

            String guardar = auto.getText();
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            Statement st = cn.createStatement();
            PreparedStatement pst = cn.prepareStatement("Select * from clientes where nombre_cli = ?");
            pst.setString(1, guardar);
            //pst.setString(1, CMBID.getName());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {

                txtDestinatario.setText(rs.getString("nombre_cli").trim());
                txtTelefono.setText(rs.getString("telefono_cli").trim());
                txtDireccion.setText(rs.getString("direccion_cli").trim());
                txtATN.setText(rs.getString("contacto_cli").trim());
                txtCiudad.setText(rs.getString("ciudad_cli").trim());
                txtIdCli.setText(rs.getString("id_cli"));
                //pst.setString(1, CMBID.getName());
                //String guardar = txtBuscar.getText();
            } else {
                JOptionPane.showMessageDialog(null, "No existe el usuario");
            }
            cn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscaActionPerformed

    private void btnPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPdfActionPerformed

        Facturas_Envio obj = new Facturas_Envio();
        obj.setVisible(true);
     
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPdfActionPerformed

    private void btnGuardaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardaActionPerformed

        int id = Integer.parseInt(txtIdCli.getText());
        try {
            Envios en = new Envios();
            clientes cli = new clientes();
            cli.setNombre_cliente(this.txtDestinatario.getText());
            
            String formato = txtFecha.getDateFormatString();
            Date date = txtFecha.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            String dato = String.valueOf(sdf.format(date));
            en.setFecha(dato);

            en.setNumero(txtSec.getText());
            en.setDestinatario(txtDestinatario.getText().toUpperCase());
            en.setATN(txtATN.getText().toUpperCase());
            en.setDireccion(txtDireccion.getText().toUpperCase());
            en.setTelefono(txtTelefono.getText().toUpperCase());
            en.setCiudad(txtCiudad.getText().toUpperCase());
            en.setComentario(areaComentario.getText().toUpperCase());
            en.setId_cli(Integer.parseInt(txtIdCli.getText()));

            cli.setId_cliente(id);

            try {
                Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                PreparedStatement pst = cn.prepareStatement("Select * From clientes Where nombre_cli = ?");
                pst.setString(1, cli.getNombre_cliente());
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    db.insertarEnvio(en);
                    JOptionPane.showMessageDialog(this, "Envio guardado exitosamente", "", JOptionPane.INFORMATION_MESSAGE);
                    numerosEnvios();
                    limpiar();
                } else {
                    if (id == 0) {
                        JOptionPane.showMessageDialog(this, "Debe registrar al cliente");
                        Nuevo_Cliente obj = new Nuevo_Cliente();
                        obj.setVisible(true);
                    }
                }
                cn.close();
            } catch (SQLException | HeadlessException e) {
                System.err.println("error" + e);
            }

        } catch (Exception e) {
            System.err.println("error" + e);
        }
        
        
        
       
        /*
        

        int id = Integer.parseInt(txtIdCli.getText());
        try {
            clientes cli = new clientes();
            cli.setNombre_cliente(this.txtEmpresa.getText());

            Entradas en = new Entradas();

            String formato = txtFecha.getDateFormatString();
            Date date = txtFecha.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            String dato = String.valueOf(sdf.format(date));
            //no_rem.setDisabledTextColor(java.awt.Color.BLUE);
            en.setFecha(dato);
            en.setElemento(txtElemento.getText().toUpperCase());
            en.setPotencia(txtPotencia.getText().toUpperCase());
            en.setMarca(txtMarca.getText().toUpperCase());
            en.setModelo(txtModelo.getText().toUpperCase());
            en.setSerie(txtSerie.getText().toUpperCase());
            en.setEmpresa(txtEmpresa.getText().toUpperCase());
            en.setNit(txtNitCliente.getText().toUpperCase());
            en.setPersona_remite(txtPersonaRemitente.getText().toUpperCase());
            en.setCiudad(txtCiudadCliente.getText().toUpperCase());
            en.setDireccion(txtDireccionCliente.getText().toUpperCase());
            en.setNombre_contacto(txtContactoCliente.getText().toUpperCase());
            en.setTelefono_contacto(txtTelefonoCliente.getText().toUpperCase());
            en.setCorreo(txtCorreoCliente.getText().toUpperCase());
            en.setMotivo(txtMotivo.getText().toUpperCase());
            en.setTarjeta_red(cmbTarjetaDeRed.getSelectedItem().toString().toUpperCase());
            en.setParrilla(cmbParrilla.getSelectedItem().toString().toUpperCase());
            en.setBases_plasticas(cmbBasesPlasticas.getSelectedItem().toString().toUpperCase());
            en.setConector_origi(cmbConectorOriginal.getSelectedItem().toString().toUpperCase());
            en.setGarantia(cmbGarantia.getSelectedItem().toString().toUpperCase());
            en.setEstado_carcasa(cmbEstadoCarcasa.getSelectedItem().toString().toUpperCase());
            en.setObservaciones(areaObservaciones.getText().toUpperCase());
            en.setId_cli(Integer.parseInt(txtIdCli.getText()));
            en.setEstado("REVISION");
            //en.setId_garantia(Integer.parseInt(txtForanea.getText()));
            en.setNumero(txtSec.getText());

            cli.setId_cliente(id);
            cli.setNombre_cliente(txtEmpresa.getText().toUpperCase());
            cli.setNit_cliente(txtNitCliente.getText().toUpperCase());
            cli.setCiudad_cliente(txtCiudadCliente.getText().toUpperCase());
            cli.setDireccion_cliente(txtDireccionCliente.getText().toUpperCase());
            cli.setNombre_contacto(txtContactoCliente.getText().toUpperCase());
            cli.setTelefono_cliente(txtTelefonoCliente.getText().toUpperCase());
            cli.setCorreo_cliente(txtCorreoCliente.getText().toUpperCase());
            try {
                Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                PreparedStatement pst = cn.prepareStatement("Select * From clientes Where nombre_cli = ?");
                pst.setString(1, cli.getNombre_cliente());
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    db.insertarEntrada(en);
                    JOptionPane.showMessageDialog(this, "Entrada guardado exitosamente", "", JOptionPane.INFORMATION_MESSAGE);
                    limpiar();
                } else {
                    if (id == 0) {
                        JOptionPane.showMessageDialog(this, "Debe registrar al cliente");
                        Nuevo_Cliente obj = new Nuevo_Cliente();
                        obj.setVisible(true);
                        dispose();
                    }
                }
                cn.close();
            } catch (Exception e) {
            }

        } catch (Exception e) {
            System.err.println("error" + e);
        }
        */

        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardaActionPerformed

    private void btnBusca1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusca1ActionPerformed

        Nuevo_Cliente obj = new Nuevo_Cliente();
        obj.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_btnBusca1ActionPerformed

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
            java.util.logging.Logger.getLogger(Envio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Envio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Envio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Envio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Envio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaComentario;
    private javax.swing.JTextField auto;
    private javax.swing.JButton btnBusca;
    private javax.swing.JButton btnBusca1;
    private javax.swing.JButton btnDescartar;
    private javax.swing.JButton btnGuarda;
    private javax.swing.JButton btnPdf;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelFondo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField txtATN;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtDestinatario;
    private javax.swing.JTextField txtDireccion;
    private com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JTextField txtIdCli;
    private javax.swing.JTextField txtSec;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
