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
public class Tecnico extends javax.swing.JFrame {

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
    public Tecnico() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("CPU System Service S.A.S - PANEL");
        ListarEntradas();
        ListarSalidas();
        ListarEnvios();
        ListarEntradas_Garantias();
        ListarGarantiasProceso();
        autoCompleteEntradas();
        autoCompleteSalidas();
        autoCompleteEnvios();
        txtIdForanea.setEnabled(false);
        txtIdEntrada.setEnabled(false);
        txtIdSalida.setEnabled(false);
        txtIdEnvio.setEnabled(false);
        txtIdProceso.setEnabled(false);

        //txtFechaEntrad.setEnabled(false);
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
    public void ListarGarantiasProceso() {
        garantia = dbGarantia.ListGarantias();
        DefaultTableModel tb = (DefaultTableModel) tbProceso.getModel();
        for (Garantias gar : garantia) {
            tb.addRow(new Object[]{gar.getId_entra(), gar.getFecha_entrada(), gar.getNumero(), gar.getCliente(), gar.getNit(), gar.getSerie_vieja(), gar.getPrimera_serie(), gar.getFecha_garantia(), gar.getRma(), gar.getNumero_caso(), gar.getSerie_nueva(), gar.getEstado()});
        }
    }

    public void LimpiarGarantiasProceso() {
        DefaultTableModel tb = (DefaultTableModel) tbProceso.getModel();
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
    
    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    
    public void autoCompleteProceso() {

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
        jButton3 = new javax.swing.JButton();
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
        jButton4 = new javax.swing.JButton();
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
        jButton5 = new javax.swing.JButton();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        btnSalir6 = new javax.swing.JButton();
        btnVolver5 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane11 = new javax.swing.JScrollPane();
        tbEntrada_garantia = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        txtNitCliente5 = new javax.swing.JTextField();
        btnBusca5 = new javax.swing.JButton();
        cmbHistorial2 = new javax.swing.JComboBox();
        txtIdForanea = new javax.swing.JTextField();
        actualizar1 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbProceso = new javax.swing.JTable();
        btnSalir4 = new javax.swing.JButton();
        btnVolver3 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        autoProceso = new javax.swing.JTextField();
        btnBusca3 = new javax.swing.JButton();
        cmbProceso = new javax.swing.JComboBox();
        jLabel25 = new javax.swing.JLabel();
        actualizar = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        txtFechaGarantia = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtCaso = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtRmaGar = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtNuevaSerie = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        txtIdProceso = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        btnBusca6 = new javax.swing.JButton();
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

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
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
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton3)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        jButton4.setText("jButton3");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

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
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15)
                                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jButton5.setText("jButton3");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(96, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnVolver2))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(187, 187, 187)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSalir3))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(20, 20, 20))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalir3)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton5)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtIdEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVolver2))
                .addGap(22, 22, 22))
        );

        jTabbedPane1.addTab("ENVIOS", jPanel3);

        jTabbedPane2.addTab("REGISTROS", jTabbedPane1);

        jTabbedPane3.setBackground(new java.awt.Color(153, 153, 153));

        jPanel9.setBackground(new java.awt.Color(153, 153, 153));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("GARANTIAS EN REVISION");

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

        txtIdForanea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdForaneaActionPerformed(evt);
            }
        });

        actualizar1.setText("jButton2");
        actualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizar1ActionPerformed(evt);
            }
        });

        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton1.setBackground(new java.awt.Color(0, 153, 153));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("PROCESO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(204, 0, 0));
        jButton9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("RECHAZAR");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(actualizar1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 809, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(75, 75, 75))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtIdForanea, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVolver5)
                        .addGap(45, 45, 45))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalir6)))
                .addGap(44, 44, 44))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalir6)
                    .addComponent(jLabel16))
                .addGap(1, 1, 1)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(actualizar1))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdForanea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVolver5))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("REVISION", jPanel9);

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("GARANTIAS EN PROCESO");

        jScrollPane8.setAutoscrolls(true);

        tbProceso.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tbProceso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_KF", "FechaEntrada", "No.Rem", "Cliente", "Nit o Cedula", "SerieVieja", "PrimeraSerie", "FechaGarantia", "RMA", "No.Caso", "SerieNueva", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbProceso.setToolTipText("");
        tbProceso.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbProceso.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbProceso.setGridColor(new java.awt.Color(0, 153, 153));
        tbProceso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProcesoMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tbProceso);

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

        cmbProceso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RMA", "CASO", "SERIE", "NIT O CEDULA", "CLIENTE" }));
        cmbProceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProcesoActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 51, 102));
        jLabel25.setText("BUSCAR");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(autoProceso)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(cmbProceso, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 107, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBusca3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnBusca3))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbProceso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(autoProceso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );

        actualizar.setText("jButton2");
        actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("FECHA GARANTIA");

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("No. CASO");

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("RMA");

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("SERIE NUEVA");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCaso, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNuevaSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRmaGar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaGarantia, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(182, 182, 182))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRmaGar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNuevaSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFechaGarantia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jButton8.setBackground(new java.awt.Color(0, 153, 153));
        jButton8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("ASIGNAR");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton6.setBackground(new java.awt.Color(0, 153, 153));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("EDITAR");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(0, 153, 153));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("ELIMINAR");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setBackground(new java.awt.Color(204, 0, 0));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("RECHAZAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnBusca6.setBackground(new java.awt.Color(255, 255, 255));
        btnBusca6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBusca6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listo_m.png"))); // NOI18N
        btnBusca6.setText("LISTO");
        btnBusca6.setBorder(null);
        btnBusca6.setBorderPainted(false);
        btnBusca6.setContentAreaFilled(false);
        btnBusca6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBusca6.setIconTextGap(-1);
        btnBusca6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnBusca6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBusca6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusca6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBusca6, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnBusca6))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addGap(82, 82, 82)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnVolver3))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator2)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(403, 403, 403)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtIdProceso, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 837, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSalir4)
                            .addGap(30, 30, 30))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalir4)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(actualizar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIdProceso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnVolver3))
                .addGap(40, 40, 40))
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
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 906, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void cmbProcesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProcesoActionPerformed

        autoCompleteProceso();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbProcesoActionPerformed

    private void btnBusca3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusca3ActionPerformed

        String guardar = autoProceso.getText();
        int tipo = cmbProceso.getSelectedIndex();
        switch (tipo) {
            case 0:
                try {
                    // id_entra,  numero, fecha, elemento, potencia, marca, modelo, serie, empresa, nit, persona_remite, ciudad, direccion, contacto, telefono, correo, motivo, parrilla, bases_plas, conector_ori, garantia, estado_car, observaciones,
                    Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                    Statement st = cn.createStatement();
                    PreparedStatement pst = cn.prepareStatement("Select * from garantias where rma = ?");
                    pst.setString(1, guardar);
                    ResultSet rs = pst.executeQuery();
                    LimpiarGarantiasProceso();
                    while (rs.next()) {
                        Garantias gar = new Garantias();
                        gar.setId_entra(rs.getInt("id_entra"));
                        gar.setFecha_entrada(rs.getString("fecha_entrada"));
                        gar.setFecha_garantia(rs.getString("fecha_garantia"));
                        gar.setNumero(rs.getString("numero"));
                        gar.setRma(rs.getString("rma"));
                        gar.setNumero_caso(rs.getString("numero_caso"));
                        gar.setCliente(rs.getString("cliente"));
                        gar.setNit(rs.getString("nit"));
                        gar.setSerie_vieja(rs.getString("serie_vieja"));
                        gar.setSerie_nueva(rs.getString("serie_nueva"));
                        gar.setPrimera_serie(rs.getString("primera_serie"));
                        gar.setEstado(rs.getString("estado"));
                        garantia.add(gar);
                        DefaultTableModel tb = (DefaultTableModel) tbProceso.getModel();
                        tb.addRow(new Object[]{gar.getId_entra(), gar.getFecha_entrada(), gar.getNumero(), gar.getCliente(), gar.getNit(), gar.getSerie_vieja(), gar.getPrimera_serie(), gar.getFecha_garantia(), gar.getRma(), gar.getNumero_caso(), gar.getSerie_nueva(), gar.getEstado()});
                    }
                    cn.close();
                } catch (Exception e) {
                    System.out.print("ERROR AL BUSCAR RMA: " + e);
                }
                break;
            case 1:
                try {
                    Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                    Statement st = cn.createStatement();
                    PreparedStatement pst = cn.prepareStatement("Select * from garantias where numero_caso = ?");
                    pst.setString(1, guardar);
                    ResultSet rs = pst.executeQuery();
                    LimpiarGarantiasProceso();
                    while (rs.next()) {
                        Garantias gar = new Garantias();
                        gar.setId_entra(rs.getInt("id_entra"));
                        gar.setFecha_entrada(rs.getString("fecha_entrada"));
                        gar.setFecha_garantia(rs.getString("fecha_garantia"));
                        gar.setNumero(rs.getString("numero"));
                        gar.setRma(rs.getString("rma"));
                        gar.setNumero_caso(rs.getString("numero_caso"));
                        gar.setCliente(rs.getString("cliente"));
                        gar.setNit(rs.getString("nit"));
                        gar.setSerie_vieja(rs.getString("serie_vieja"));
                        gar.setSerie_nueva(rs.getString("serie_nueva"));
                        gar.setPrimera_serie(rs.getString("primera_serie"));
                        gar.setEstado(rs.getString("estado"));
                        garantia.add(gar);
                        DefaultTableModel tb = (DefaultTableModel) tbProceso.getModel();
                        tb.addRow(new Object[]{gar.getId_entra(), gar.getFecha_entrada(), gar.getNumero(), gar.getCliente(), gar.getNit(), gar.getSerie_vieja(), gar.getPrimera_serie(), gar.getFecha_garantia(), gar.getRma(), gar.getNumero_caso(), gar.getSerie_nueva(), gar.getEstado()});
                    }
                    cn.close();
                } catch (Exception e) {
                    System.out.print("ERROR AL BUSCAR NO.CASO: " + e);
                }

                break;
            case 2:
                try {
                    Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                    Statement st = cn.createStatement();
                    PreparedStatement pst = cn.prepareStatement("Select * from garantias where serie_vieja = ?");
                    pst.setString(1, guardar);
                    ResultSet rs = pst.executeQuery();
                    LimpiarGarantiasProceso();
                    while (rs.next()) {
                        Garantias gar = new Garantias();
                        gar.setId_entra(rs.getInt("id_entra"));
                        gar.setFecha_entrada(rs.getString("fecha_entrada"));
                        gar.setFecha_garantia(rs.getString("fecha_garantia"));
                        gar.setNumero(rs.getString("numero"));
                        gar.setRma(rs.getString("rma"));
                        gar.setNumero_caso(rs.getString("numero_caso"));
                        gar.setCliente(rs.getString("cliente"));
                        gar.setNit(rs.getString("nit"));
                        gar.setSerie_vieja(rs.getString("serie_vieja"));
                        gar.setSerie_nueva(rs.getString("serie_nueva"));
                        gar.setPrimera_serie(rs.getString("primera_serie"));
                        gar.setEstado(rs.getString("estado"));
                        garantia.add(gar);
                        DefaultTableModel tb = (DefaultTableModel) tbProceso.getModel();
                        tb.addRow(new Object[]{gar.getId_entra(), gar.getFecha_entrada(), gar.getNumero(), gar.getCliente(), gar.getNit(), gar.getSerie_vieja(), gar.getPrimera_serie(), gar.getFecha_garantia(), gar.getRma(), gar.getNumero_caso(), gar.getSerie_nueva(), gar.getEstado()});
                    }
                    cn.close();
                } catch (Exception e) {
                    System.out.print("ERROR AL BUSCAR SERIE: " + e);
                }
                break;
            case 3:
                try {
                    Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                    Statement st = cn.createStatement();
                    PreparedStatement pst = cn.prepareStatement("Select * from garantias where nit = ?");
                    pst.setString(1, guardar);
                    ResultSet rs = pst.executeQuery();
                    LimpiarGarantiasProceso();
                    while (rs.next()) {
                        Garantias gar = new Garantias();
                        gar.setId_entra(rs.getInt("id_entra"));
                        gar.setFecha_entrada(rs.getString("fecha_entrada"));
                        gar.setFecha_garantia(rs.getString("fecha_garantia"));
                        gar.setNumero(rs.getString("numero"));
                        gar.setRma(rs.getString("rma"));
                        gar.setNumero_caso(rs.getString("numero_caso"));
                        gar.setCliente(rs.getString("cliente"));
                        gar.setNit(rs.getString("nit"));
                        gar.setSerie_vieja(rs.getString("serie_vieja"));
                        gar.setSerie_nueva(rs.getString("serie_nueva"));
                        gar.setPrimera_serie(rs.getString("primera_serie"));
                        gar.setEstado(rs.getString("estado"));
                        garantia.add(gar);
                        DefaultTableModel tb = (DefaultTableModel) tbProceso.getModel();
                        tb.addRow(new Object[]{gar.getId_entra(), gar.getFecha_entrada(), gar.getNumero(), gar.getCliente(), gar.getNit(), gar.getSerie_vieja(), gar.getPrimera_serie(), gar.getFecha_garantia(), gar.getRma(), gar.getNumero_caso(), gar.getSerie_nueva(), gar.getEstado()});
                    }
                    cn.close();
                } catch (Exception e) {
                    System.out.print("ERROR AL BUSCAR REGISTRO: " + e);
                }
                break;
                case 4:
                try {
                    Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                    Statement st = cn.createStatement();
                    PreparedStatement pst = cn.prepareStatement("Select * from garantias where cliente = ?");
                    pst.setString(1, guardar);
                    ResultSet rs = pst.executeQuery();
                    LimpiarGarantiasProceso();
                    while (rs.next()) {
                        Garantias gar = new Garantias();
                        gar.setId_entra(rs.getInt("id_entra"));
                        gar.setFecha_entrada(rs.getString("fecha_entrada"));
                        gar.setFecha_garantia(rs.getString("fecha_garantia"));
                        gar.setNumero(rs.getString("numero"));
                        gar.setRma(rs.getString("rma"));
                        gar.setNumero_caso(rs.getString("numero_caso"));
                        gar.setCliente(rs.getString("cliente"));
                        gar.setNit(rs.getString("nit"));
                        gar.setSerie_vieja(rs.getString("serie_vieja"));
                        gar.setSerie_nueva(rs.getString("serie_nueva"));
                        gar.setPrimera_serie(rs.getString("primera_serie"));
                        gar.setEstado(rs.getString("estado"));
                        garantia.add(gar);
                        DefaultTableModel tb = (DefaultTableModel) tbProceso.getModel();
                        tb.addRow(new Object[]{gar.getId_entra(), gar.getFecha_entrada(), gar.getNumero(), gar.getCliente(), gar.getNit(), gar.getSerie_vieja(), gar.getPrimera_serie(), gar.getFecha_garantia(), gar.getRma(), gar.getNumero_caso(), gar.getSerie_nueva(), gar.getEstado()});
                    }
                    cn.close();
                } catch (Exception e) {
                    System.out.print("ERROR AL BUSCAR CLIENTE: " + e);
                }
                break;
            default:
                System.out.println("error");
                break;
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBusca3ActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed

        Principal_Tec obj = new Principal_Tec();
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

        Principal_Tec obj = new Principal_Tec();
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

        Principal_Tec obj = new Principal_Tec();
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

        Principal_Tec obj = new Principal_Tec();
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

        Bienvenida obj = new Bienvenida();
        obj.setVisible(true);
        dispose();

// TODO add your handling code here:
    }//GEN-LAST:event_btnSalir6ActionPerformed

    private void btnVolver5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolver5ActionPerformed

        Principal_Tec obj = new Principal_Tec();
        obj.setVisible(true);
        dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnVolver5ActionPerformed

    private void btnSalir7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir7ActionPerformed

        Bienvenida obj = new Bienvenida();
        obj.setVisible(true);
        dispose();

// TODO add your handling code here:
    }//GEN-LAST:event_btnSalir7ActionPerformed

    private void btnVolver6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolver6ActionPerformed
        Principal_Tec obj = new Principal_Tec();
        obj.setVisible(true);
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnVolver6ActionPerformed

    private void btnBusca4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusca4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBusca4ActionPerformed

    private void cmbHistorial1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbHistorial1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbHistorial1ActionPerformed

    private void btnBusca5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusca5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBusca5ActionPerformed

    private void cmbHistorial2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbHistorial2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbHistorial2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            Garantias gar = new Garantias();
            Entradas en = new Entradas();

            int seleccion = tbEntrada_garantia.getSelectedRow();

            int idEn = Integer.parseInt("" + tbEntrada_garantia.getValueAt(seleccion, 0));
            en.setId_entrada(idEn);
            en.setEstado("PROCESO");

            /////////////////////PENDIENTE FECHA///////////////////////////
            int id = Integer.parseInt("" + tbEntrada_garantia.getValueAt(seleccion, 0));
            gar.setId_entra(id);
            gar.setFecha_entrada(String.valueOf(tbEntrada_garantia.getValueAt(seleccion, 1)));
            gar.setFecha_garantia(String.valueOf(tbEntrada_garantia.getValueAt(seleccion, 1)));
            gar.setNumero(String.valueOf(tbEntrada_garantia.getValueAt(seleccion, 2)));
            gar.setRma("");
            gar.setNumero_caso("");
            gar.setCliente(String.valueOf(tbEntrada_garantia.getValueAt(seleccion, 3)));
            gar.setNit(String.valueOf(tbEntrada_garantia.getValueAt(seleccion, 4)));
            gar.setSerie_vieja(String.valueOf(tbEntrada_garantia.getValueAt(seleccion, 8)));
            gar.setSerie_nueva("");
            gar.setPrimera_serie(String.valueOf(tbEntrada_garantia.getValueAt(seleccion, 8)));
            gar.setEstado("PROCESO");

            dbGarantia.insertarEntrada_Garantia(gar);
            JOptionPane.showMessageDialog(this, "Garantia en proceso", "", JOptionPane.INFORMATION_MESSAGE);

            dbGarantia.RevisionAProcesoGarantia(gar);
            dbGarantia.RevisionAProcesoEntrada(en);
            LimpiarEntradas_Garantias();
            ListarEntradas_Garantias();
            LimpiarGarantiasProceso();
            ListarGarantiasProceso();
            LimpiarEntradas();
            ListarEntradas();

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

// TODO add your handling code here:
    }//GEN-LAST:event_tbEntrada_garantiaMouseClicked

    private void actualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizar1ActionPerformed

        LimpiarEntradas_Garantias();
        ListarEntradas_Garantias();

        // TODO add your handling code here:
    }//GEN-LAST:event_actualizar1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try {
            Garantias gar = new Garantias();
            Entradas en = new Entradas();

            int seleccion = tbProceso.getSelectedRow();

            int idEn = Integer.parseInt("" + tbProceso.getValueAt(seleccion, 0));
            en.setId_entrada(idEn);
            en.setEstado("RECHAZADO");

            int id = Integer.parseInt("" + tbProceso.getValueAt(seleccion, 0));
            gar.setId_entra(id);
            gar.setEstado("RECHAZADO");
            
            dbGarantia.RechazarGarantia(gar);
            dbGarantia.RechazarEntrada(en);
            JOptionPane.showMessageDialog(this, "REGISTRO RECHAZADO", "", JOptionPane.INFORMATION_MESSAGE);
            
            LimpiarEntradas_Garantias();
            ListarEntradas_Garantias();
            LimpiarGarantiasProceso();
            ListarGarantiasProceso();
            LimpiarEntradas();
            ListarEntradas();

        } catch (Exception e) {
            System.err.println("error::" + e);
        }
        
// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        LimpiarEntradas();
        ListarEntradas();

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed

        LimpiarGarantiasProceso();
        ListarGarantiasProceso();

        // TODO add your handling code here:
    }//GEN-LAST:event_actualizarActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        LimpiarSalidas();
        ListarSalidas();

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        LimpiarEnvios();
        ListarEnvios();

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

        try {
            Garantias gar = new Garantias();
            int seleccion = tbProceso.getSelectedRow();

            int idForanea = Integer.parseInt("" + tbProceso.getValueAt(seleccion, 0));
            gar.setId_entra(idForanea);
            gar.setNumero_caso(txtCaso.getText().toUpperCase());
            gar.setSerie_nueva(txtNuevaSerie.getText().toUpperCase());
            gar.setFecha_garantia(txtFechaGarantia.getText());
            dbGarantia.Asignar(gar);
            JOptionPane.showMessageDialog(this, "Registros asignados");
            LimpiarGarantiasProceso();
            ListarGarantiasProceso();

        } catch (Exception e) {
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void tbProcesoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProcesoMouseClicked

        int seleccion = tbProceso.getSelectedRow();
        txtIdProceso.setText(String.valueOf(tbProceso.getValueAt(seleccion, 0)));
        txtFechaGarantia.setText(String.valueOf(tbProceso.getValueAt(seleccion, 7)));
        txtRmaGar.setText(String.valueOf(tbProceso.getValueAt(seleccion, 8)));
        txtCaso.setText(String.valueOf(tbProceso.getValueAt(seleccion, 9)));
        txtNuevaSerie.setText(String.valueOf(tbProceso.getValueAt(seleccion, 10)));

// TODO add your handling code here:
    }//GEN-LAST:event_tbProcesoMouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

        try {
            Garantias gar = new Garantias();
            Entradas en = new Entradas();

            int seleccion = tbEntrada_garantia.getSelectedRow();

            int idEn = Integer.parseInt("" + tbEntrada_garantia.getValueAt(seleccion, 0));
            en.setId_entrada(idEn);
            en.setEstado("RECHAZADO");

            int id = Integer.parseInt("" + tbEntrada_garantia.getValueAt(seleccion, 0));
            gar.setId_entra(id);
            gar.setEstado("RECHAZADO");
            
            dbGarantia.RechazarGarantia(gar);
            dbGarantia.RechazarEntrada(en);
            JOptionPane.showMessageDialog(this, "REGISTRO RECHAZADO", "", JOptionPane.INFORMATION_MESSAGE);
            
            LimpiarEntradas_Garantias();
            ListarEntradas_Garantias();
            LimpiarGarantiasProceso();
            ListarGarantiasProceso();
            LimpiarEntradas();
            ListarEntradas();

        } catch (Exception e) {
            System.err.println("error::" + e);
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void btnBusca6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusca6ActionPerformed

        try {
            Garantias gar = new Garantias();
            Entradas en = new Entradas();

            int seleccion = tbProceso.getSelectedRow();

            int idEn = Integer.parseInt("" + tbProceso.getValueAt(seleccion, 0));
            en.setId_entrada(idEn);
            en.setEstado("LISTO");

            int id = Integer.parseInt("" + tbProceso.getValueAt(seleccion, 0));
            gar.setId_entra(id);
            gar.setEstado("LISTO");
            
            dbGarantia.ListoGarantia(gar);
            dbGarantia.ListoEntrada(en);
            JOptionPane.showMessageDialog(this, "PROCESO EXITOSO", "", JOptionPane.INFORMATION_MESSAGE);
            
            LimpiarEntradas_Garantias();
            ListarEntradas_Garantias();
            LimpiarGarantiasProceso();
            ListarGarantiasProceso();
            LimpiarEntradas();
            ListarEntradas();

        } catch (Exception e) {
            System.err.println("error::" + e);
        }
   
// TODO add your handling code here:
    }//GEN-LAST:event_btnBusca6ActionPerformed

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
            java.util.logging.Logger.getLogger(Tecnico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tecnico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tecnico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tecnico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tecnico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizar;
    private javax.swing.JButton actualizar1;
    private javax.swing.JTextArea areaEntrada;
    private javax.swing.JTextArea areaEnvio;
    private javax.swing.JTextArea areaGarantia1;
    private javax.swing.JTextArea areaSalida;
    private javax.swing.JTextField autoEntra;
    private javax.swing.JTextField autoEnvio;
    private javax.swing.JTextField autoProceso;
    private javax.swing.JTextField autoSal;
    private javax.swing.JButton btnBusca;
    private javax.swing.JButton btnBusca1;
    private javax.swing.JButton btnBusca2;
    private javax.swing.JButton btnBusca3;
    private javax.swing.JButton btnBusca4;
    private javax.swing.JButton btnBusca5;
    private javax.swing.JButton btnBusca6;
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
    private javax.swing.JComboBox cmbHistorial1;
    private javax.swing.JComboBox cmbHistorial2;
    private javax.swing.JComboBox cmbProceso;
    private javax.swing.JComboBox cmbSalidas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
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
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable tbEntrada_garantia;
    private javax.swing.JTable tbEntradas;
    private javax.swing.JTable tbEnvios;
    private javax.swing.JTable tbGarantias1;
    private javax.swing.JTable tbProceso;
    private javax.swing.JTable tbSalidas;
    private javax.swing.JTextField txtCaso;
    private javax.swing.JTextField txtFechaGarantia;
    private javax.swing.JTextField txtIdEntrada;
    private javax.swing.JTextField txtIdEnvio;
    private javax.swing.JTextField txtIdForanea;
    private javax.swing.JTextField txtIdProceso;
    private javax.swing.JTextField txtIdSalida;
    private javax.swing.JTextField txtNitCliente4;
    private javax.swing.JTextField txtNitCliente5;
    private javax.swing.JTextField txtNuevaSerie;
    private javax.swing.JTextField txtRmaGar;
    // End of variables declaration//GEN-END:variables
}
