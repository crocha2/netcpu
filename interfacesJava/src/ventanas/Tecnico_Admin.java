/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import clasesPrincipales.Entradas;
import clasesPrincipales.Envios;
import clasesPrincipales.Garantias;
import clasesPrincipales.Salidas;
import clasesPrincipales.contratos;
import com.mxrck.autocompleter.TextAutoCompleter;
import conMySql.entradaMySql;
import conMySql.envioMySql;
import conMySql.garantiaMySql;
import conMySql.salidaMySql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JR
 */
public class Tecnico_Admin extends javax.swing.JFrame {

    ArrayList<Entradas> entrada;
    entradaMySql dbEntrada = new entradaMySql();

    ArrayList<Salidas> salida;
    salidaMySql dbSalida = new salidaMySql();

    ArrayList<Envios> envio;
    envioMySql dbEnvio = new envioMySql();

    ArrayList<Garantias> garantia;
    garantiaMySql dbGarantia = new garantiaMySql();

    /**
     * Creates new form Tecnico
     */
    public Tecnico_Admin() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("CPU System Service S.A.S - PANEL");
        ListarEntradas();
        ListarSalidas();
        ListarEnvios();
        ListarEntradas_Garantias();
        autoCompleteEntradas();
        autoCompleteSalidas();
        autoCompleteEnvios();
        txtIdForanea.setEnabled(false);
        txtFechaEntrad.setEnabled(false);
    }

    public void ListarEntradas() {
        entrada = dbEntrada.ListEntradas();
        DefaultTableModel tb = (DefaultTableModel) tbEntradas.getModel();
        for (Entradas en : entrada) {
            tb.addRow(new Object[]{en.getId_entrada(), en.getFecha(), en.getNumero(), en.getEmpresa(), en.getNit(), en.getTelefono_contacto(), en.getCorreo(), en.getElemento(), en.getMarca(), en.getModelo(), en.getSerie(), en.getObservaciones(), en.getEstado()});
        }
    }

    public void LimpiarEntradas() {
        DefaultTableModel tb = (DefaultTableModel) tbEntradas.getModel();
        for (int i = tb.getRowCount() - 1; i >= 0; i--) {
            tb.removeRow(i);
        }
    }

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public void ListarSalidas() {
        salida = dbSalida.ListSalidas();
        DefaultTableModel tb = (DefaultTableModel) tbSalidas.getModel();
        for (Salidas sal : salida) {
            tb.addRow(new Object[]{sal.getId_salida(), sal.getFecha(), sal.getNumero(), sal.getEmpresa(), sal.getTelefono(), sal.getCorreo(), sal.getEquipo(), sal.getModelo(), sal.getSerie(), sal.getComentario()});
        }
    }

    public void LimpiarSalidas() {
        DefaultTableModel tb = (DefaultTableModel) tbSalidas.getModel();
        for (int i = tb.getRowCount() - 1; i >= 0; i--) {
            tb.removeRow(i);
        }
    }

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public void ListarEnvios() {
        envio = dbEnvio.ListEnvios();
        DefaultTableModel tb = (DefaultTableModel) tbEnvios.getModel();
        for (Envios en : envio) {
            tb.addRow(new Object[]{en.getId_envio(), en.getFecha(), en.getNumero(), en.getDestinatario(), en.getATN(), en.getDireccion(), en.getTelefono(), en.getCiudad(), en.getComentario()});
        }
    }

    public void LimpiarEnvios() {
        DefaultTableModel tb = (DefaultTableModel) tbEnvios.getModel();
        for (int i = tb.getRowCount() - 1; i >= 0; i--) {
            tb.removeRow(i);
        }
    }

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public void autoCompleteEntradas() {

        TextAutoCompleter TextAutoCompleter = new TextAutoCompleter(autoEntra);

        try {
            String guardar = cmbEntradas.getSelectedItem().toString();
            if (guardar.equals("NUMERO")) {
                Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                Statement st = (Statement) cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM entradas");
                while (rs.next()) {
                    TextAutoCompleter.addItem(rs.getString("numero"));
                }
                cn.close();
            }
            if (guardar.equals("CLIENTE")) {
                Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                Statement st = (Statement) cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM entradas");
                while (rs.next()) {
                    TextAutoCompleter.addItem(rs.getString("empresa"));
                }
                cn.close();
            }
            if ("NIT O CEDULA".equals(guardar)) {
                Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                Statement st = (Statement) cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM entradas");
                while (rs.next()) {
                    TextAutoCompleter.addItem(rs.getString("nit"));
                }
                cn.close();
            }
            if ("SERIE".equals(guardar)) {
                Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                Statement st = (Statement) cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM entradas");
                while (rs.next()) {
                    TextAutoCompleter.addItem(rs.getString("serie"));
                }
                cn.close();
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

    }

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public void autoCompleteSalidas() {

        TextAutoCompleter TextAutoCompleter = new TextAutoCompleter(autoSal);

        try {
            String guardar = cmbSalidas.getSelectedItem().toString();
            if (guardar.equals("NUMERO")) {
                Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                Statement st = (Statement) cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM salidas");
                while (rs.next()) {
                    TextAutoCompleter.addItem(rs.getString("numero"));
                }
                cn.close();
            }
            if (guardar.equals("CLIENTE")) {
                Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                Statement st = (Statement) cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM salidas");
                while (rs.next()) {
                    TextAutoCompleter.addItem(rs.getString("empresa"));
                }
                cn.close();
            }
            if ("SERIE".equals(guardar)) {
                Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                Statement st = (Statement) cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM salidas");
                while (rs.next()) {
                    TextAutoCompleter.addItem(rs.getString("serie"));
                }
                cn.close();
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

    }

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public void autoCompleteEnvios() {

        TextAutoCompleter TextAutoCompleter = new TextAutoCompleter(autoEnvio);

        try {
            String guardar = cmbEnvios.getSelectedItem().toString();
            if (guardar.equals("NUMERO")) {
                Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                Statement st = (Statement) cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM envios");
                while (rs.next()) {
                    TextAutoCompleter.addItem(rs.getString("numero"));
                }
                cn.close();
            }
            if ("DESTINATARIO".equals(guardar)) {
                Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                Statement st = (Statement) cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM envios");
                while (rs.next()) {
                    TextAutoCompleter.addItem(rs.getString("destinatario"));
                }
                cn.close();
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

    }

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public void ListarEntradas_Garantias() {
        entrada = dbEntrada.ListEntradas_garantias();
        DefaultTableModel tb = (DefaultTableModel) tbEntrada_garantia.getModel();
        for (Entradas en : entrada) {
            tb.addRow(new Object[]{en.getId_entrada(), en.getFecha(), en.getNumero(), en.getEmpresa(), en.getNit(), en.getMarca(), en.getElemento(), en.getModelo(), en.getSerie(), en.getGarantia(), en.getEstado()});
        }
    }

    public void LimpiarEntradas_Garantias() {
        DefaultTableModel tb = (DefaultTableModel) tbEntrada_garantia.getModel();
        for (int i = tb.getRowCount() - 1; i >= 0; i--) {
            tb.removeRow(i);
        }
    }

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbEntradas = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        areaEntrada = new javax.swing.JTextArea();
        btnVolver = new javax.swing.JButton();
        btnSalir1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        cmbEntradas = new javax.swing.JComboBox();
        autoEntra = new javax.swing.JTextField();
        btnBusca = new javax.swing.JButton();
        txtIdEntrada = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbSalidas = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        areaSalida = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        cmbSalidas = new javax.swing.JComboBox();
        autoSal = new javax.swing.JTextField();
        btnBusca1 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnSalir5 = new javax.swing.JButton();
        btnVolver4 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        txtIdSalida = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbEnvios = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        areaEnvio = new javax.swing.JTextArea();
        btnSalir3 = new javax.swing.JButton();
        btnVolver2 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        cmbEnvios = new javax.swing.JComboBox();
        autoEnvio = new javax.swing.JTextField();
        btnBusca2 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        txtIdEnvio = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        btnSalir6 = new javax.swing.JButton();
        btnVolver5 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane11 = new javax.swing.JScrollPane();
        tbEntrada_garantia = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel28 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        txtNitCliente5 = new javax.swing.JTextField();
        btnBusca5 = new javax.swing.JButton();
        cmbHistorial2 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        txtIdForanea = new javax.swing.JTextField();
        txtFechaEntrad = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        areaGarantia = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbGarantias = new javax.swing.JTable();
        btnSalir4 = new javax.swing.JButton();
        btnVolver3 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        txtNitCliente3 = new javax.swing.JTextField();
        btnBusca3 = new javax.swing.JButton();
        cmbHistorial = new javax.swing.JComboBox();
        jPanel11 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        areaGarantia1 = new javax.swing.JTextArea();
        jScrollPane10 = new javax.swing.JScrollPane();
        tbGarantias1 = new javax.swing.JTable();
        btnSalir7 = new javax.swing.JButton();
        btnVolver6 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        txtNitCliente4 = new javax.swing.JTextField();
        btnBusca4 = new javax.swing.JButton();
        cmbHistorial1 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        tbEntradas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fecha", "No.Rem", "Cliente", "Nit o Cedula", "Telefono", "Correo", "Elemento", "Marca", "Modelo", "Serie", "Observacion", "estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbEntradas.setToolTipText("");
        tbEntradas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbEntradas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbEntradas.setGridColor(new java.awt.Color(0, 153, 153));
        tbEntradas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbEntradasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbEntradas);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Observaciones");

        areaEntrada.setColumns(20);
        areaEntrada.setRows(5);
        jScrollPane4.setViewportView(areaEntrada);

        btnVolver.setBackground(new java.awt.Color(51, 153, 255));
        btnVolver.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnSalir1.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalir1.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir1.setText("Salir");
        btnSalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir1ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("REGISTRO DE ENTRADAS");

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cmbEntradas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NUMERO", "CLIENTE", "NIT O CEDULA", "SERIE" }));
        cmbEntradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEntradasActionPerformed(evt);
            }
        });

        autoEntra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoEntraActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(autoEntra, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(cmbEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(autoEntra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBusca))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("ID");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(239, 239, 239)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 262, Short.MAX_VALUE)
                                .addComponent(btnSalir1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnVolver))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jScrollPane4)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSalir1)
                        .addGap(41, 41, 41)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolver)
                    .addComponent(jLabel22)
                    .addComponent(txtIdEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ENTRADAS", jPanel1);

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        tbSalidas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tbSalidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fecha", "No.Rem", "Cliente", "Telefono", "Correo", "Equipo", "Modelo", "Serie", "Observacion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSalidas.setToolTipText("");
        tbSalidas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbSalidas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbSalidas.setGridColor(new java.awt.Color(0, 153, 153));
        tbSalidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSalidasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbSalidas);

        areaSalida.setColumns(20);
        areaSalida.setRows(5);
        jScrollPane5.setViewportView(areaSalida);

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cmbSalidas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NUMERO", "CLIENTE", "SERIE" }));
        cmbSalidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSalidasActionPerformed(evt);
            }
        });

        btnBusca1.setBackground(new java.awt.Color(255, 255, 255));
        btnBusca1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBusca1.setForeground(new java.awt.Color(255, 255, 255));
        btnBusca1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lupa2.png"))); // NOI18N
        btnBusca1.setBorder(null);
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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbSalidas, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autoSal, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnBusca1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBusca1)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(cmbSalidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(autoSal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("REGISTRO DE SALIDAS");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Observaciones");

        btnSalir5.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalir5.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir5.setText("Salir");
        btnSalir5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir5ActionPerformed(evt);
            }
        });

        btnVolver4.setBackground(new java.awt.Color(51, 153, 255));
        btnVolver4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnVolver4.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver4.setText("Volver");
        btnVolver4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolver4ActionPerformed(evt);
            }
        });

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("ID");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalir5))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15)
                                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 55, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnVolver4)))))
                .addGap(24, 24, 24))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(275, 275, 275)
                    .addComponent(jLabel14)
                    .addContainerGap(341, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalir5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtIdSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVolver4))
                .addGap(10, 10, 10))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(jLabel14)
                    .addContainerGap(409, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("SALIDAS", jPanel2);

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("REGISTRO DE ENVIOS");

        tbEnvios.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tbEnvios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fecha", "No.Rem", "Destinatario", "ATN", "Direccion", "Telefono", "Ciudad", "Comentario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbEnvios.setToolTipText("");
        tbEnvios.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbEnvios.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbEnvios.setGridColor(new java.awt.Color(0, 153, 153));
        tbEnvios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbEnviosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbEnvios);

        areaEnvio.setColumns(20);
        areaEnvio.setRows(5);
        jScrollPane6.setViewportView(areaEnvio);

        btnSalir3.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalir3.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir3.setText("Salir");
        btnSalir3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir3ActionPerformed(evt);
            }
        });

        btnVolver2.setBackground(new java.awt.Color(51, 153, 255));
        btnVolver2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnVolver2.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver2.setText("Volver");
        btnVolver2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolver2ActionPerformed(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cmbEnvios.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NUMERO", "DESTINATARIO" }));
        cmbEnvios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEnviosActionPerformed(evt);
            }
        });

        btnBusca2.setBackground(new java.awt.Color(255, 255, 255));
        btnBusca2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBusca2.setForeground(new java.awt.Color(255, 255, 255));
        btnBusca2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lupa2.png"))); // NOI18N
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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbEnvios, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autoEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBusca2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBusca2)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(cmbEnvios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(autoEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("ID");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Comentario");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnVolver2))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(187, 187, 187)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSalir3)))
                        .addGap(20, 20, 20))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(96, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalir3)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnVolver2)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(txtIdEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24))))
        );

        jTabbedPane1.addTab("ENVIOS", jPanel3);

        jTabbedPane2.addTab("REGISTROS", jTabbedPane1);

        jTabbedPane3.setBackground(new java.awt.Color(153, 153, 153));

        jPanel9.setBackground(new java.awt.Color(153, 153, 153));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("GARANTIAS");

        btnSalir6.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalir6.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir6.setText("Salir");
        btnSalir6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir6ActionPerformed(evt);
            }
        });

        btnVolver5.setBackground(new java.awt.Color(51, 153, 255));
        btnVolver5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnVolver5.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver5.setText("Volver");
        btnVolver5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolver5ActionPerformed(evt);
            }
        });

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("FECHA ENTRADA");

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("No.REM");

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("CLIENTE");

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("NIT O CEDULA");

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("ELEMENTO");

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("MARCA");

        jLabel26.setBackground(new java.awt.Color(255, 255, 255));
        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("MODELO");

        jLabel27.setBackground(new java.awt.Color(255, 255, 255));
        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("SERIE");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addComponent(jLabel26)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                            .addComponent(jLabel25)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addComponent(jLabel21)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );

        jScrollPane11.setAutoscrolls(true);

        tbEntrada_garantia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tbEntrada_garantia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fecha", "No.Rem", "Cliente", "Nit o Cedula", "Marca", "Elemento", "Modelo", "Serie", "Garantia", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbEntrada_garantia.setToolTipText("");
        tbEntrada_garantia.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbEntrada_garantia.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbEntrada_garantia.setGridColor(new java.awt.Color(0, 153, 153));
        tbEntrada_garantia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbEntrada_garantiaMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tbEntrada_garantia);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "REVISION", "PROCESO", "LISTO" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("ESTADO");

        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnBusca5.setBackground(new java.awt.Color(255, 255, 255));
        btnBusca5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBusca5.setForeground(new java.awt.Color(255, 255, 255));
        btnBusca5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lupa2.png"))); // NOI18N
        btnBusca5.setBorder(null);
        btnBusca5.setBorderPainted(false);
        btnBusca5.setContentAreaFilled(false);
        btnBusca5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBusca5.setIconTextGap(-1);
        btnBusca5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnBusca5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBusca5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusca5ActionPerformed(evt);
            }
        });

        cmbHistorial2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RMA", "SERIE", "NIT", "CEDULA", "CLIENTE" }));
        cmbHistorial2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbHistorial2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbHistorial2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNitCliente5, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(btnBusca5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBusca5)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(cmbHistorial2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNitCliente5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setBackground(new java.awt.Color(0, 153, 153));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("INGRESAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtIdForanea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdForaneaActionPerformed(evt);
            }
        });

        txtFechaEntrad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaEntradActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalir6))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(13, 13, 13))
                            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnVolver5)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 776, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtIdForanea, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(txtFechaEntrad, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(59, 59, 59))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSalir6)
                            .addComponent(jLabel16))
                        .addGap(1, 1, 1)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(23, 23, 23))
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdForanea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVolver5)
                    .addComponent(txtFechaEntrad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        jTabbedPane3.addTab("REVISION", jPanel9);

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("HISTORIAL DE GARANTIAS");

        areaGarantia.setColumns(20);
        areaGarantia.setRows(5);
        jScrollPane7.setViewportView(areaGarantia);

        jScrollPane8.setAutoscrolls(true);

        tbGarantias.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tbGarantias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "FechaEntrada", "No.Rem", "Cliente", "Nit o Cedula", "Elemento", "Marca", "Modelo", "Serie", "FechaGarantia", "SerieNueva", "PrimeraSerie", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbGarantias.setToolTipText("");
        tbGarantias.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbGarantias.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbGarantias.setGridColor(new java.awt.Color(0, 153, 153));
        jScrollPane8.setViewportView(tbGarantias);
        if (tbGarantias.getColumnModel().getColumnCount() > 0) {
            tbGarantias.getColumnModel().getColumn(9).setHeaderValue("FechaGarantia");
            tbGarantias.getColumnModel().getColumn(10).setHeaderValue("SerieNueva");
            tbGarantias.getColumnModel().getColumn(11).setHeaderValue("PrimeraSerie");
        }

        btnSalir4.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalir4.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir4.setText("Salir");
        btnSalir4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir4ActionPerformed(evt);
            }
        });

        btnVolver3.setBackground(new java.awt.Color(51, 153, 255));
        btnVolver3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnVolver3.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver3.setText("Volver");
        btnVolver3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolver3ActionPerformed(evt);
            }
        });

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnBusca3.setBackground(new java.awt.Color(255, 255, 255));
        btnBusca3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBusca3.setForeground(new java.awt.Color(255, 255, 255));
        btnBusca3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lupa2.png"))); // NOI18N
        btnBusca3.setBorder(null);
        btnBusca3.setBorderPainted(false);
        btnBusca3.setContentAreaFilled(false);
        btnBusca3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBusca3.setIconTextGap(-1);
        btnBusca3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnBusca3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBusca3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusca3ActionPerformed(evt);
            }
        });

        cmbHistorial.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RMA", "SERIE", "NIT", "CEDULA", "CLIENTE" }));
        cmbHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbHistorialActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNitCliente3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBusca3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(cmbHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNitCliente3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBusca3))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addGap(214, 214, 214)
                        .addComponent(btnSalir4))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 17, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnVolver3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 869, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalir4)
                    .addComponent(jLabel11))
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                .addComponent(btnVolver3)
                .addGap(18, 18, 18))
        );

        jTabbedPane3.addTab("PROCESO", jPanel4);

        jPanel11.setBackground(new java.awt.Color(153, 153, 153));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("HISTORIAL DE GARANTIAS");

        areaGarantia1.setColumns(20);
        areaGarantia1.setRows(5);
        jScrollPane9.setViewportView(areaGarantia1);

        jScrollPane10.setAutoscrolls(true);

        tbGarantias1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tbGarantias1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "FechaEntrada", "No.Rem", "Cliente", "Nit o Cedula", "Elemento", "Marca", "Modelo", "Serie", "FechaGarantia", "SerieNueva", "PrimeraSerie"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbGarantias1.setToolTipText("");
        tbGarantias1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbGarantias1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbGarantias1.setGridColor(new java.awt.Color(0, 153, 153));
        jScrollPane10.setViewportView(tbGarantias1);
        if (tbGarantias1.getColumnModel().getColumnCount() > 0) {
            tbGarantias1.getColumnModel().getColumn(9).setHeaderValue("FechaGarantia");
            tbGarantias1.getColumnModel().getColumn(10).setHeaderValue("SerieNueva");
            tbGarantias1.getColumnModel().getColumn(11).setHeaderValue("PrimeraSerie");
        }

        btnSalir7.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalir7.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir7.setText("Salir");
        btnSalir7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir7ActionPerformed(evt);
            }
        });

        btnVolver6.setBackground(new java.awt.Color(51, 153, 255));
        btnVolver6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnVolver6.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver6.setText("Volver");
        btnVolver6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolver6ActionPerformed(evt);
            }
        });

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnBusca4.setBackground(new java.awt.Color(255, 255, 255));
        btnBusca4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBusca4.setForeground(new java.awt.Color(255, 255, 255));
        btnBusca4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lupa2.png"))); // NOI18N
        btnBusca4.setBorder(null);
        btnBusca4.setBorderPainted(false);
        btnBusca4.setContentAreaFilled(false);
        btnBusca4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBusca4.setIconTextGap(-1);
        btnBusca4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnBusca4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBusca4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusca4ActionPerformed(evt);
            }
        });

        cmbHistorial1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RMA", "SERIE", "NIT", "CEDULA", "CLIENTE" }));
        cmbHistorial1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbHistorial1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNitCliente4, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbHistorial1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBusca4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(cmbHistorial1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNitCliente4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBusca4))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17)
                        .addGap(214, 214, 214)
                        .addComponent(btnSalir7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnVolver6))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane10))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 46, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalir7)
                    .addComponent(jLabel17))
                .addGap(37, 37, 37)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(btnVolver6)
                .addGap(18, 18, 18))
        );

        jTabbedPane3.addTab("HISTORIAL", jPanel11);

        jTabbedPane2.addTab("GARANTIAS", jTabbedPane3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 906, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaActionPerformed

        String guardar = autoEntra.getText();
        int tipo = cmbEntradas.getSelectedIndex();
        switch (tipo) {
            case 0:
                try {
                    // id_entra,  numero, fecha, elemento, potencia, marca, modelo, serie, empresa, nit, persona_remite, ciudad, direccion, contacto, telefono, correo, motivo, parrilla, bases_plas, conector_ori, garantia, estado_car, observaciones,
                    Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                    Statement st = cn.createStatement();
                    PreparedStatement pst = cn.prepareStatement("Select * from entradas where numero = ?");
                    pst.setString(1, guardar);
                    ResultSet rs = pst.executeQuery();
                    LimpiarEntradas();
                    if (rs.next()) {
                        Entradas en = new Entradas();
                        en.setId_entrada(rs.getInt("id_entra"));
                        en.setFecha(rs.getString("fecha"));
                        en.setNumero(rs.getString("numero"));
                        en.setEmpresa(rs.getString("empresa"));
                        en.setNit(rs.getString("nit"));
                        en.setTelefono_contacto(rs.getString("telefono"));
                        en.setCorreo(rs.getString("correo"));
                        en.setElemento(rs.getString("elemento"));
                        en.setMarca(rs.getString("marca"));
                        en.setModelo(rs.getString("modelo"));
                        en.setSerie(rs.getString("serie"));
                        en.setObservaciones(rs.getString("observaciones"));
                        entrada.add(en);
                        DefaultTableModel tb = (DefaultTableModel) tbEntradas.getModel();
                        tb.addRow(new Object[]{en.getId_entrada(), en.getFecha(), en.getNumero(), en.getEmpresa(), en.getNit(), en.getTelefono_contacto(), en.getCorreo(), en.getElemento(), en.getMarca(), en.getModelo(), en.getSerie(), en.getObservaciones()});
                    }
                    cn.close();
                } catch (Exception e) {
                    System.out.print("ERROR AL BUSCAR NUMERO: " + e);
                }
                break;
            case 1:
                 try {  
                 Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                 Statement st = cn.createStatement();
                 PreparedStatement pst = cn.prepareStatement("Select * from entradas where empresa = ?");
                 pst.setString(1, guardar);
                 ResultSet rs = pst.executeQuery();
                 LimpiarEntradas();
                 while (rs.next()) {
                 Entradas en = new Entradas();
                 en.setId_entrada(rs.getInt("id_entra"));
                 en.setFecha(rs.getString("fecha"));
                 en.setNumero(rs.getString("numero"));
                 en.setEmpresa(rs.getString("empresa"));
                 en.setNit(rs.getString("nit"));
                 en.setTelefono_contacto(rs.getString("telefono"));
                 en.setCorreo(rs.getString("correo"));
                 en.setElemento(rs.getString("elemento"));
                 en.setMarca(rs.getString("marca"));
                 en.setModelo(rs.getString("modelo"));
                 en.setSerie(rs.getString("serie"));
                 en.setObservaciones(rs.getString("observaciones"));
                 entrada.add(en);
                 DefaultTableModel tb = (DefaultTableModel) tbEntradas.getModel();
                 tb.addRow(new Object[]{en.getId_entrada(), en.getFecha(), en.getNumero(), en.getEmpresa(), en.getNit(), en.getTelefono_contacto(), en.getCorreo(), en.getElemento(), en.getMarca(), en.getModelo(), en.getSerie(), en.getObservaciones()});
                 }
                 cn.close();
                 } catch (Exception e) {
                 System.out.print("ERROR AL BUSCAR CLIENTE: " + e);
                 }
                 
                break;
            case 2:
                try {
                    Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                    Statement st = cn.createStatement();
                    PreparedStatement pst = cn.prepareStatement("Select * from entradas where nit = ?");
                    pst.setString(1, guardar);
                    ResultSet rs = pst.executeQuery();
                    LimpiarEntradas();
                    while (rs.next()) {
                        Entradas en = new Entradas();
                        en.setId_entrada(rs.getInt("id_entra"));
                        en.setFecha(rs.getString("fecha"));
                        en.setNumero(rs.getString("numero"));
                        en.setEmpresa(rs.getString("empresa"));
                        en.setNit(rs.getString("nit"));
                        en.setTelefono_contacto(rs.getString("telefono"));
                        en.setCorreo(rs.getString("correo"));
                        en.setElemento(rs.getString("elemento"));
                        en.setMarca(rs.getString("marca"));
                        en.setModelo(rs.getString("modelo"));
                        en.setSerie(rs.getString("serie"));
                        en.setObservaciones(rs.getString("observaciones"));
                        entrada.add(en);
                        DefaultTableModel tb = (DefaultTableModel) tbEntradas.getModel();
                        tb.addRow(new Object[]{en.getId_entrada(), en.getFecha(), en.getNumero(), en.getEmpresa(), en.getNit(), en.getTelefono_contacto(), en.getCorreo(), en.getElemento(), en.getMarca(), en.getModelo(), en.getSerie(), en.getObservaciones()});
                    }
                    cn.close();
                } catch (Exception e) {
                    System.out.print("ERROR AL BUSCAR REGISTRO: " + e);
                }
                break;
            case 3:
                try {
                    Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                    Statement st = cn.createStatement();
                    PreparedStatement pst = cn.prepareStatement("Select * from entradas where serie = ?");
                    pst.setString(1, guardar);
                    ResultSet rs = pst.executeQuery();
                    LimpiarEntradas();
                    if (rs.next()) {
                        Entradas en = new Entradas();
                        en.setId_entrada(rs.getInt("id_entra"));
                        en.setFecha(rs.getString("fecha"));
                        en.setNumero(rs.getString("numero"));
                        en.setEmpresa(rs.getString("empresa"));
                        en.setNit(rs.getString("nit"));
                        en.setTelefono_contacto(rs.getString("telefono"));
                        en.setCorreo(rs.getString("correo"));
                        en.setElemento(rs.getString("elemento"));
                        en.setMarca(rs.getString("marca"));
                        en.setModelo(rs.getString("modelo"));
                        en.setSerie(rs.getString("serie"));
                        en.setObservaciones(rs.getString("observaciones"));
                        entrada.add(en);
                        DefaultTableModel tb = (DefaultTableModel) tbEntradas.getModel();
                        tb.addRow(new Object[]{en.getId_entrada(), en.getFecha(), en.getNumero(), en.getEmpresa(), en.getNit(), en.getTelefono_contacto(), en.getCorreo(), en.getElemento(), en.getMarca(), en.getModelo(), en.getSerie(), en.getObservaciones()});
                    }
                    cn.close();
                } catch (Exception e) {
                    System.out.print("ERROR AL BUSCAR SERIE: " + e);
                }
                break;
            default:
                System.out.println("error");
                break;
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscaActionPerformed

    private void btnBusca1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusca1ActionPerformed

        String guardar = autoSal.getText();
        int tipo = cmbSalidas.getSelectedIndex();
        switch (tipo) {
            case 0:
                try {
                    // id_entra,  numero, fecha, elemento, potencia, marca, modelo, serie, empresa, nit, persona_remite, ciudad, direccion, contacto, telefono, correo, motivo, parrilla, bases_plas, conector_ori, garantia, estado_car, observaciones,
                    Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                    Statement st = cn.createStatement();
                    PreparedStatement pst = cn.prepareStatement("Select * from salidas where numero = ?");
                    pst.setString(1, guardar);
                    ResultSet rs = pst.executeQuery();
                    LimpiarSalidas();
                    if (rs.next()) {
                        Salidas sal = new Salidas();
                        sal.setId_salida(rs.getInt("id_salida"));
                        sal.setFecha(rs.getString("fecha"));
                        sal.setNumero(rs.getString("numero"));
                        sal.setEmpresa(rs.getString("empresa"));
                        sal.setTelefono(rs.getString("telefono"));
                        sal.setCorreo(rs.getString("correo"));
                        sal.setEquipo(rs.getString("equipo"));
                        sal.setModelo(rs.getString("modelo"));
                        sal.setSerie(rs.getString("serie"));
                        sal.setComentario(rs.getString("comentario"));
                        salida.add(sal);
                        DefaultTableModel tb = (DefaultTableModel) tbSalidas.getModel();
                        tb.addRow(new Object[]{sal.getId_salida(), sal.getFecha(), sal.getNumero(), sal.getEmpresa(), sal.getTelefono(), sal.getCorreo(), sal.getEquipo(), sal.getModelo(), sal.getSerie(), sal.getComentario()});
                    }
                    cn.close();
                } catch (Exception e) {
                    System.out.print("ERROR AL BUSCAR NUMERO: " + e);
                }
                break;
            case 1:
                 try {
                    // id_entra,  numero, fecha, elemento, potencia, marca, modelo, serie, empresa, nit, persona_remite, ciudad, direccion, contacto, telefono, correo, motivo, parrilla, bases_plas, conector_ori, garantia, estado_car, observaciones,
                    Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                    Statement st = cn.createStatement();
                    PreparedStatement pst = cn.prepareStatement("Select * from salidas where empresa = ?");
                    pst.setString(1, guardar);
                    ResultSet rs = pst.executeQuery();
                    LimpiarSalidas();
                    while (rs.next()) {
                        Salidas sal = new Salidas();
                        sal.setId_salida(rs.getInt("id_salida"));
                        sal.setFecha(rs.getString("fecha"));
                        sal.setNumero(rs.getString("numero"));
                        sal.setEmpresa(rs.getString("empresa"));
                        sal.setTelefono(rs.getString("telefono"));
                        sal.setCorreo(rs.getString("correo"));
                        sal.setEquipo(rs.getString("equipo"));
                        sal.setModelo(rs.getString("modelo"));
                        sal.setSerie(rs.getString("serie"));
                        sal.setComentario(rs.getString("comentario"));
                        salida.add(sal);
                        DefaultTableModel tb = (DefaultTableModel) tbSalidas.getModel();
                        tb.addRow(new Object[]{sal.getId_salida(), sal.getFecha(), sal.getNumero(), sal.getEmpresa(), sal.getTelefono(), sal.getCorreo(), sal.getEquipo(), sal.getModelo(), sal.getSerie(), sal.getComentario()});
                    }
                    cn.close();
                } catch (Exception e) {
                    System.out.print("ERROR AL BUSCAR CLIENTE: " + e);
                }
                break; 
            case 2:
                try {
                    // id_entra,  numero, fecha, elemento, potencia, marca, modelo, serie, empresa, nit, persona_remite, ciudad, direccion, contacto, telefono, correo, motivo, parrilla, bases_plas, conector_ori, garantia, estado_car, observaciones,
                    Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                    Statement st = cn.createStatement();
                    PreparedStatement pst = cn.prepareStatement("Select * from salidas where serie = ?");
                    pst.setString(1, guardar);
                    ResultSet rs = pst.executeQuery();
                    LimpiarSalidas();
                    while (rs.next()) {
                        Salidas sal = new Salidas();
                        sal.setId_salida(rs.getInt("id_salida"));
                        sal.setFecha(rs.getString("fecha"));
                        sal.setNumero(rs.getString("numero"));
                        sal.setEmpresa(rs.getString("empresa"));
                        sal.setTelefono(rs.getString("telefono"));
                        sal.setCorreo(rs.getString("correo"));
                        sal.setEquipo(rs.getString("equipo"));
                        sal.setModelo(rs.getString("modelo"));
                        sal.setSerie(rs.getString("serie"));
                        sal.setComentario(rs.getString("comentario"));
                        salida.add(sal);
                        DefaultTableModel tb = (DefaultTableModel) tbSalidas.getModel();
                        tb.addRow(new Object[]{sal.getId_salida(), sal.getFecha(), sal.getNumero(), sal.getEmpresa(), sal.getTelefono(), sal.getCorreo(), sal.getEquipo(), sal.getModelo(), sal.getSerie(), sal.getComentario()});
                    }
                    cn.close();
                } catch (Exception e) {
                    System.out.print("ERROR AL BUSCAR SERIE: " + e);
                }
                break;
            default:
                System.out.println("error");
                break;
        }
        
// TODO add your handling code here:
    }//GEN-LAST:event_btnBusca1ActionPerformed

    private void btnBusca2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusca2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBusca2ActionPerformed

    private void cmbSalidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSalidasActionPerformed

        autoCompleteSalidas();

        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSalidasActionPerformed

    private void cmbEnviosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEnviosActionPerformed

        autoCompleteEnvios();

        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEnviosActionPerformed

    private void cmbHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbHistorialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbHistorialActionPerformed

    private void btnBusca3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusca3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBusca3ActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed

        Formatos_Admin obj = new Formatos_Admin();
        obj.setVisible(true);
        dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnSalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir1ActionPerformed

        Bienvenida obj = new Bienvenida();
        obj.setVisible(true);
        dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalir1ActionPerformed

    private void btnSalir3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir3ActionPerformed

        Bienvenida obj = new Bienvenida();
        obj.setVisible(true);
        dispose();

// TODO add your handling code here:
    }//GEN-LAST:event_btnSalir3ActionPerformed

    private void btnVolver2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolver2ActionPerformed

        Formatos_Admin obj = new Formatos_Admin();
        obj.setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVolver2ActionPerformed

    private void btnSalir4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir4ActionPerformed

        Bienvenida obj = new Bienvenida();
        obj.setVisible(true);
        dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalir4ActionPerformed

    private void btnVolver3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolver3ActionPerformed

        Formatos_Admin obj = new Formatos_Admin();
        obj.setVisible(true);
        dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnVolver3ActionPerformed

    private void tbEntradasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEntradasMouseClicked

        int seleccion = tbEntradas.getSelectedRow();
        txtIdEntrada.setText(String.valueOf(tbEntradas.getValueAt(seleccion, 0)));
        areaEntrada.setText(String.valueOf(tbEntradas.getValueAt(seleccion, 11)));

// TODO add your handling code here:
    }//GEN-LAST:event_tbEntradasMouseClicked

    private void btnSalir5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir5ActionPerformed

        Bienvenida obj = new Bienvenida();
        obj.setVisible(true);
        dispose();

// TODO add your handling code here:
    }//GEN-LAST:event_btnSalir5ActionPerformed

    private void btnVolver4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolver4ActionPerformed

        Formatos_Admin obj = new Formatos_Admin();
        obj.setVisible(true);
        dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnVolver4ActionPerformed

    private void tbSalidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSalidasMouseClicked

        int seleccion = tbSalidas.getSelectedRow();
        txtIdSalida.setText(String.valueOf(tbSalidas.getValueAt(seleccion, 0)));
        areaSalida.setText(String.valueOf(tbSalidas.getValueAt(seleccion, 9)));

// TODO add your handling code here:
    }//GEN-LAST:event_tbSalidasMouseClicked

    private void tbEnviosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEnviosMouseClicked

        int seleccion = tbEnvios.getSelectedRow();
        txtIdEnvio.setText(String.valueOf(tbEnvios.getValueAt(seleccion, 0)));
        areaEnvio.setText(String.valueOf(tbEnvios.getValueAt(seleccion, 8)));

// TODO add your handling code here:
    }//GEN-LAST:event_tbEnviosMouseClicked

    private void cmbEntradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEntradasActionPerformed

        autoCompleteEntradas();

// TODO add your handling code here:
    }//GEN-LAST:event_cmbEntradasActionPerformed

    private void btnSalir6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalir6ActionPerformed

    private void btnVolver5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolver5ActionPerformed

        Formatos_Admin obj = new Formatos_Admin();
        obj.setVisible(true);
        dispose();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVolver5ActionPerformed

    private void btnSalir7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalir7ActionPerformed

    private void btnVolver6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolver6ActionPerformed

        Formatos_Admin obj = new Formatos_Admin();
        obj.setVisible(true);
        dispose();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVolver6ActionPerformed

    private void btnBusca4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusca4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBusca4ActionPerformed

    private void cmbHistorial1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbHistorial1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbHistorial1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void btnBusca5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusca5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBusca5ActionPerformed

    private void cmbHistorial2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbHistorial2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbHistorial2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            Garantias gar = new Garantias();

            /////////////////////PENDIENTE FECHA///////////////////////////
            /*
             String formato = tbEntrada_garantia.getValueAt(seleccion, 1).toString();

             SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
             //String strFecha = fecha;
             String strFecha = String.valueOf(sdf.format(formato));
             //Date fechaDate = null;
             gar.setFecha_entrada(strFecha);
             */
            ///////////////////////////////////////777777
            /*
             try {
             fechaDate = formato.parse(strFecha);
             //System.out.println(fechaDate.toString());
             gar.setFecha_entrada(String.valueOf(fechaDate));
             } catch (ParseException ex) {
             ex.printStackTrace();
             }
             */
            /////////////////////PENDIENTE FECHA///////////////////////////
            /*
             String formato = txtFecha.getDateFormatString();
             Date date = txtFecha.getDate();
             SimpleDateFormat sdf = new SimpleDateFormat(formato);
             String dato = String.valueOf(sdf.format(date));
             //no_rem.setDisabledTextColor(java.awt.Color.BLUE);
             en.setFecha(dato);
             */
            int seleccion = tbEntrada_garantia.getSelectedRow();

            //gar.setId_garantia(Integer.parseInt("" + tbEntrada_garantia.getValueAt(seleccion, 0)));
            /*
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String strFecha = tbEntrada_garantia.getValueAt(seleccion, 1).toString();
            Date fechaDate = null;
            try {
                fechaDate = formato.parse(strFecha);
                gar.setFecha_entrada(strFecha);

            } catch (ParseException ex) {
                ex.printStackTrace();
                //return fechaDate;
            }
            */
      
            //gar.setFecha_entrada(txtFechaEntrad.getText());
            gar.setFecha_entrada(String.valueOf(tbEntrada_garantia.getValueAt(seleccion, 1)));
            gar.setCliente(String.valueOf(tbEntrada_garantia.getValueAt(seleccion, 3)));
            gar.setNit(String.valueOf(tbEntrada_garantia.getValueAt(seleccion, 4)));
            gar.setSerie_vieja(String.valueOf(tbEntrada_garantia.getValueAt(seleccion, 8)));
            gar.setPrimera_serie(String.valueOf(tbEntrada_garantia.getValueAt(seleccion, 8)));
            gar.setEstado("PROCESO");

            /*
            
             gar.setId_garantia(Integer.parseInt(tbEntrada_garantia.getValueAt(seleccion, 0)));
             gar.setId_garantia(Integer.parseInt(tbEntrada_garantia.getValueAt(seleccion, 0)));
             gar.setId_garantia(Integer.parseInt(tbEntrada_garantia.getValueAt(seleccion, 0)));
             gar.setId_garantia(Integer.parseInt(tbEntrada_garantia.getValueAt(seleccion, 0)));
             gar.setId_garantia(Integer.parseInt(tbEntrada_garantia.getValueAt(seleccion, 0)));
             gar.setId_garantia(Integer.parseInt(tbEntrada_garantia.getValueAt(seleccion, 0)));
             gar.setId_garantia(Integer.parseInt(""+tbEntrada_garantia.getValueAt(seleccion, 0)));

             txtID.setText(String.valueOf(tbEntrada_garantia.getValueAt(seleccion, 0)));
             txtNitCliente.setText(String.valueOf(tabla_clientes.getValueAt(seleccion, 1)));
             txtNombreCliente.setText(String.valueOf(tabla_clientes.getValueAt(seleccion, 2)));
             txtTelefonoCliente.setText(String.valueOf(tabla_clientes.getValueAt(seleccion, 3)));
             txtDireccionCliente.setText(String.valueOf(tabla_clientes.getValueAt(seleccion, 4)));
             txtCiudadCliente.setText(String.valueOf(tabla_clientes.getValueAt(seleccion, 5)));
             txtCorreoCliente.setText(String.valueOf(tabla_clientes.getValueAt(seleccion, 6)));
             txtContactoCliente.setText(String.valueOf(tabla_clientes.getValueAt(seleccion, 7)));

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
             en.setEstado("REVISION");
             //en.setId_garantia(Integer.parseInt(txtForanea.getText()));
             en.setNumero(txtSec.getText());
             */
            dbGarantia.insertarEntrada_Garantia(gar);
            JOptionPane.showMessageDialog(this, "Garantia en proceso", "", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            System.err.println("error::" + e);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtIdForaneaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdForaneaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdForaneaActionPerformed

    private void autoEntraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoEntraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_autoEntraActionPerformed

    private void tbEntrada_garantiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEntrada_garantiaMouseClicked

        int seleccion = tbEntrada_garantia.getSelectedRow();
        txtIdForanea.setText(String.valueOf(tbEntrada_garantia.getValueAt(seleccion, 0)));
        txtFechaEntrad.setText(String.valueOf(tbEntrada_garantia.getValueAt(seleccion, 1)));
        
// TODO add your handling code here:
    }//GEN-LAST:event_tbEntrada_garantiaMouseClicked

    private void txtFechaEntradActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaEntradActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaEntradActionPerformed

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
            java.util.logging.Logger.getLogger(Tecnico_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tecnico_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tecnico_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tecnico_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tecnico_Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaEntrada;
    private javax.swing.JTextArea areaEnvio;
    private javax.swing.JTextArea areaGarantia;
    private javax.swing.JTextArea areaGarantia1;
    private javax.swing.JTextArea areaSalida;
    private javax.swing.JTextField autoEntra;
    private javax.swing.JTextField autoEnvio;
    private javax.swing.JTextField autoSal;
    private javax.swing.JButton btnBusca;
    private javax.swing.JButton btnBusca1;
    private javax.swing.JButton btnBusca2;
    private javax.swing.JButton btnBusca3;
    private javax.swing.JButton btnBusca4;
    private javax.swing.JButton btnBusca5;
    private javax.swing.JButton btnSalir1;
    private javax.swing.JButton btnSalir3;
    private javax.swing.JButton btnSalir4;
    private javax.swing.JButton btnSalir5;
    private javax.swing.JButton btnSalir6;
    private javax.swing.JButton btnSalir7;
    private javax.swing.JButton btnVolver;
    private javax.swing.JButton btnVolver2;
    private javax.swing.JButton btnVolver3;
    private javax.swing.JButton btnVolver4;
    private javax.swing.JButton btnVolver5;
    private javax.swing.JButton btnVolver6;
    private javax.swing.JComboBox cmbEntradas;
    private javax.swing.JComboBox cmbEnvios;
    private javax.swing.JComboBox cmbHistorial;
    private javax.swing.JComboBox cmbHistorial1;
    private javax.swing.JComboBox cmbHistorial2;
    private javax.swing.JComboBox cmbSalidas;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTable tbEntrada_garantia;
    private javax.swing.JTable tbEntradas;
    private javax.swing.JTable tbEnvios;
    private javax.swing.JTable tbGarantias;
    private javax.swing.JTable tbGarantias1;
    private javax.swing.JTable tbSalidas;
    private javax.swing.JTextField txtFechaEntrad;
    private javax.swing.JTextField txtIdEntrada;
    private javax.swing.JTextField txtIdEnvio;
    private javax.swing.JTextField txtIdForanea;
    private javax.swing.JTextField txtIdSalida;
    private javax.swing.JTextField txtNitCliente3;
    private javax.swing.JTextField txtNitCliente4;
    private javax.swing.JTextField txtNitCliente5;
    // End of variables declaration//GEN-END:variables
}
