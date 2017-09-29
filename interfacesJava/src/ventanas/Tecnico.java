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
        autoCompleteProceso();
        autoCompleteRevision();
        txtIdForanea.setEnabled(false);
        txtIdEntrada.setEnabled(false);
        txtIdSalida.setEnabled(false);
        txtIdEnvio.setEnabled(false);
        txtIdProceso.setEnabled(false);

        //txtFechaEntrad.setEnabled(false);
    }
    
    public void limpiarTextFiealdProceso(){
        txtCaso.setText("");
        txtNuevaSerie.setText("");
        txtFechaGarantia.setText("");
        txtRmaGar.setText("");
        txtCaso.requestFocus();
    }

    public void ListarEntradas() {
        entrada = dbEntrada.ListEntradas();
        DefaultTableModel tb = (DefaultTableModel) tbEntradas.getModel();
        for (Entradas en : entrada) {
            tb.addRow(new Object[]{en.getId_entrada(), en.getFecha(), en.getNumero(), en.getEmpresa(), en.getNit(), en.getTelefono_contacto(), en.getCorreo(), en.getElemento(), en.getMarca(), en.getModelo(), en.getSerie(), en.getGarantia(), en.getObservaciones(), en.getEstado()});
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
    
    public void autoCompleteRevision() {

        TextAutoCompleter TextAutoCompleter = new TextAutoCompleter(autoRevision);

        try {
            String guardar = cmbRevision.getSelectedItem().toString();
            if (guardar.equals("NUMERO")) {
                Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                Statement st = (Statement) cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM entradas WHERE garantia = 'SI' AND estado = 'REVISION'");
                while (rs.next()) {
                    TextAutoCompleter.addItem(rs.getString("numero"));
                }
                cn.close();
            }
            if (guardar.equals("CLIENTE")) {
                Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                Statement st = (Statement) cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM entradas WHERE garantia = 'SI' AND estado = 'REVISION'");
                while (rs.next()) {
                    TextAutoCompleter.addItem(rs.getString("empresa"));
                }
                cn.close();
            }
            if ("NIT O CEDULA".equals(guardar)) {
                Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                Statement st = (Statement) cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM entradas WHERE garantia = 'SI' AND estado = 'REVISION'");
                while (rs.next()) {
                    TextAutoCompleter.addItem(rs.getString("nit"));
                }
                cn.close();
            }
            if ("SERIE".equals(guardar)) {
                Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                Statement st = (Statement) cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM entradas WHERE garantia = 'SI' AND estado = 'REVISION'");
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
        btnSalida = new javax.swing.JButton();
        btnSalida2 = new javax.swing.JButton();
        btnSalida1 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
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
        btnSalida3 = new javax.swing.JButton();
        btnSalida4 = new javax.swing.JButton();
        btnSalida5 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
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
        btnSalida6 = new javax.swing.JButton();
        btnSalida7 = new javax.swing.JButton();
        btnSalida8 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        btnSalir6 = new javax.swing.JButton();
        btnVolver5 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane11 = new javax.swing.JScrollPane();
        tbEntrada_garantia = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        autoRevision = new javax.swing.JTextField();
        btnBusca5 = new javax.swing.JButton();
        cmbRevision = new javax.swing.JComboBox();
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
        jPanel1.setLayout(null);

        tbEntradas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fecha", "No.Rem", "Cliente", "Nit o Cedula", "Telefono", "Correo", "Elemento", "Marca", "Modelo", "Serie", "Garantia", "Observacion", "estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false, false, false, false, false, false
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

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 150, 876, 206);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Observaciones");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(430, 50, 87, 15);

        areaEntrada.setColumns(20);
        areaEntrada.setRows(5);
        jScrollPane4.setViewportView(areaEntrada);

        jPanel1.add(jScrollPane4);
        jScrollPane4.setBounds(430, 70, 459, 72);

        btnVolver.setBackground(new java.awt.Color(51, 153, 255));
        btnVolver.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        jPanel1.add(btnVolver);
        btnVolver.setBounds(820, 410, 69, 23);

        btnSalir1.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalir1.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir1.setText("Salir");
        btnSalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir1);
        btnSalir1.setBounds(829, 17, 57, 23);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("REGISTRO DE ENTRADAS");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(290, 10, 305, 29);

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

        jPanel1.add(jPanel5);
        jPanel5.setBounds(10, 70, 345, 69);
        jPanel1.add(txtIdEntrada);
        txtIdEntrada.setBounds(50, 370, 76, 20);

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("ID");
        jPanel1.add(jLabel22);
        jLabel22.setBounds(20, 370, 24, 14);

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(380, 70, 23, 23);

        btnSalida.setBackground(new java.awt.Color(255, 255, 255));
        btnSalida.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalida.setForeground(new java.awt.Color(255, 255, 255));
        btnSalida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/entrada_mini.png"))); // NOI18N
        btnSalida.setBorder(null);
        btnSalida.setBorderPainted(false);
        btnSalida.setContentAreaFilled(false);
        btnSalida.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalida.setIconTextGap(-1);
        btnSalida.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnSalida.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalidaActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalida);
        btnSalida.setBounds(160, 370, 40, 40);

        btnSalida2.setBackground(new java.awt.Color(255, 255, 255));
        btnSalida2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalida2.setForeground(new java.awt.Color(255, 255, 255));
        btnSalida2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/salida_mini.png"))); // NOI18N
        btnSalida2.setBorder(null);
        btnSalida2.setBorderPainted(false);
        btnSalida2.setContentAreaFilled(false);
        btnSalida2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalida2.setIconTextGap(-1);
        btnSalida2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnSalida2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalida2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalida2ActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalida2);
        btnSalida2.setBounds(200, 370, 40, 40);

        btnSalida1.setBackground(new java.awt.Color(255, 255, 255));
        btnSalida1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalida1.setForeground(new java.awt.Color(255, 255, 255));
        btnSalida1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/envio_mini.png"))); // NOI18N
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
        jPanel1.add(btnSalida1);
        btnSalida1.setBounds(240, 370, 40, 40);

        jButton10.setBackground(new java.awt.Color(0, 153, 153));
        jButton10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("EDITAR");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton10);
        jButton10.setBounds(360, 387, 95, 40);

        jButton12.setBackground(new java.awt.Color(0, 153, 153));
        jButton12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("ELIMINAR");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton12);
        jButton12.setBounds(460, 387, 95, 40);

        jTabbedPane1.addTab("ENTRADAS", jPanel1);

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setLayout(null);

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

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(68, 170, 760, 202);

        areaSalida.setColumns(20);
        areaSalida.setRows(5);
        jScrollPane5.setViewportView(areaSalida);

        jPanel2.add(jScrollPane5);
        jScrollPane5.setBounds(417, 75, 410, 84);

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

        jPanel2.add(jPanel6);
        jPanel6.setBounds(68, 75, 277, 84);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("REGISTRO DE SALIDAS");
        jPanel2.add(jLabel14);
        jLabel14.setBounds(275, 21, 280, 29);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Observaciones");
        jPanel2.add(jLabel15);
        jLabel15.setBounds(417, 54, 87, 15);

        btnSalir5.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalir5.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir5.setText("Salir");
        btnSalir5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir5ActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalir5);
        btnSalir5.setBounds(820, 10, 57, 23);

        btnVolver4.setBackground(new java.awt.Color(51, 153, 255));
        btnVolver4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnVolver4.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver4.setText("Volver");
        btnVolver4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolver4ActionPerformed(evt);
            }
        });
        jPanel2.add(btnVolver4);
        btnVolver4.setBounds(820, 420, 69, 23);

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("ID");
        jPanel2.add(jLabel23);
        jLabel23.setBounds(70, 390, 24, 14);
        jPanel2.add(txtIdSalida);
        txtIdSalida.setBounds(100, 390, 76, 20);

        jButton4.setText("jButton3");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4);
        jButton4.setBounds(355, 75, 24, 23);

        btnSalida3.setBackground(new java.awt.Color(255, 255, 255));
        btnSalida3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalida3.setForeground(new java.awt.Color(255, 255, 255));
        btnSalida3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/entrada_mini.png"))); // NOI18N
        btnSalida3.setBorder(null);
        btnSalida3.setBorderPainted(false);
        btnSalida3.setContentAreaFilled(false);
        btnSalida3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalida3.setIconTextGap(-1);
        btnSalida3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnSalida3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalida3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalida3ActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalida3);
        btnSalida3.setBounds(200, 380, 40, 40);

        btnSalida4.setBackground(new java.awt.Color(255, 255, 255));
        btnSalida4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalida4.setForeground(new java.awt.Color(255, 255, 255));
        btnSalida4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/salida_mini.png"))); // NOI18N
        btnSalida4.setBorder(null);
        btnSalida4.setBorderPainted(false);
        btnSalida4.setContentAreaFilled(false);
        btnSalida4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalida4.setIconTextGap(-1);
        btnSalida4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnSalida4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalida4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalida4ActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalida4);
        btnSalida4.setBounds(240, 380, 40, 40);

        btnSalida5.setBackground(new java.awt.Color(255, 255, 255));
        btnSalida5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalida5.setForeground(new java.awt.Color(255, 255, 255));
        btnSalida5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/envio_mini.png"))); // NOI18N
        btnSalida5.setBorder(null);
        btnSalida5.setBorderPainted(false);
        btnSalida5.setContentAreaFilled(false);
        btnSalida5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalida5.setIconTextGap(-1);
        btnSalida5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnSalida5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalida5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalida5ActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalida5);
        btnSalida5.setBounds(280, 380, 40, 40);

        jButton13.setBackground(new java.awt.Color(0, 153, 153));
        jButton13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setText("ELIMINAR");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton13);
        jButton13.setBounds(450, 380, 100, 40);

        jButton14.setBackground(new java.awt.Color(0, 153, 153));
        jButton14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setText("EDITAR");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton14);
        jButton14.setBounds(340, 380, 100, 40);

        jTabbedPane1.addTab("SALIDAS", jPanel2);

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));
        jPanel3.setLayout(null);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("REGISTRO DE ENVIOS");
        jPanel3.add(jLabel12);
        jLabel12.setBounds(276, 11, 269, 29);

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

        jPanel3.add(jScrollPane3);
        jScrollPane3.setBounds(89, 181, 711, 200);

        areaEnvio.setColumns(20);
        areaEnvio.setRows(5);
        jScrollPane6.setViewportView(areaEnvio);

        jPanel3.add(jScrollPane6);
        jScrollPane6.setBounds(433, 79, 367, 84);

        btnSalir3.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalir3.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir3.setText("Salir");
        btnSalir3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir3ActionPerformed(evt);
            }
        });
        jPanel3.add(btnSalir3);
        btnSalir3.setBounds(819, 11, 57, 23);

        btnVolver2.setBackground(new java.awt.Color(51, 153, 255));
        btnVolver2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnVolver2.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver2.setText("Volver");
        btnVolver2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolver2ActionPerformed(evt);
            }
        });
        jPanel3.add(btnVolver2);
        btnVolver2.setBounds(807, 413, 69, 23);

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

        jPanel3.add(jPanel7);
        jPanel7.setBounds(89, 81, 277, 82);

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("ID");
        jPanel3.add(jLabel24);
        jLabel24.setBounds(90, 410, 24, 14);
        jPanel3.add(txtIdEnvio);
        txtIdEnvio.setBounds(120, 410, 76, 20);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Comentario");
        jPanel3.add(jLabel10);
        jLabel10.setBounds(433, 58, 71, 15);

        jButton5.setText("jButton3");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5);
        jButton5.setBounds(376, 81, 24, 23);

        btnSalida6.setBackground(new java.awt.Color(255, 255, 255));
        btnSalida6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalida6.setForeground(new java.awt.Color(255, 255, 255));
        btnSalida6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/entrada_mini.png"))); // NOI18N
        btnSalida6.setBorder(null);
        btnSalida6.setBorderPainted(false);
        btnSalida6.setContentAreaFilled(false);
        btnSalida6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalida6.setIconTextGap(-1);
        btnSalida6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnSalida6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalida6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalida6ActionPerformed(evt);
            }
        });
        jPanel3.add(btnSalida6);
        btnSalida6.setBounds(210, 390, 40, 40);

        btnSalida7.setBackground(new java.awt.Color(255, 255, 255));
        btnSalida7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalida7.setForeground(new java.awt.Color(255, 255, 255));
        btnSalida7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/salida_mini.png"))); // NOI18N
        btnSalida7.setBorder(null);
        btnSalida7.setBorderPainted(false);
        btnSalida7.setContentAreaFilled(false);
        btnSalida7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalida7.setIconTextGap(-1);
        btnSalida7.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnSalida7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalida7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalida7ActionPerformed(evt);
            }
        });
        jPanel3.add(btnSalida7);
        btnSalida7.setBounds(250, 390, 40, 40);

        btnSalida8.setBackground(new java.awt.Color(255, 255, 255));
        btnSalida8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalida8.setForeground(new java.awt.Color(255, 255, 255));
        btnSalida8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/envio_mini.png"))); // NOI18N
        btnSalida8.setBorder(null);
        btnSalida8.setBorderPainted(false);
        btnSalida8.setContentAreaFilled(false);
        btnSalida8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalida8.setIconTextGap(-1);
        btnSalida8.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnSalida8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalida8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalida8ActionPerformed(evt);
            }
        });
        jPanel3.add(btnSalida8);
        btnSalida8.setBounds(290, 390, 40, 40);

        jButton15.setBackground(new java.awt.Color(0, 153, 153));
        jButton15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setText("ELIMINAR");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton15);
        jButton15.setBounds(474, 400, 95, 40);

        jButton16.setBackground(new java.awt.Color(0, 153, 153));
        jButton16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setText("EDITAR");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton16);
        jButton16.setBounds(373, 400, 95, 40);

        jTabbedPane1.addTab("ENVIOS", jPanel3);

        jTabbedPane2.addTab("REGISTROS", jTabbedPane1);

        jTabbedPane3.setBackground(new java.awt.Color(153, 153, 153));

        jPanel9.setBackground(new java.awt.Color(153, 153, 153));
        jPanel9.setLayout(null);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("GARANTIAS EN REVISION");
        jPanel9.add(jLabel16);
        jLabel16.setBounds(29, 11, 372, 29);

        btnSalir6.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalir6.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir6.setText("Salir");
        btnSalir6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir6ActionPerformed(evt);
            }
        });
        jPanel9.add(btnSalir6);
        btnSalir6.setBounds(822, 11, 57, 23);

        btnVolver5.setBackground(new java.awt.Color(51, 153, 255));
        btnVolver5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnVolver5.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver5.setText("Volver");
        btnVolver5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolver5ActionPerformed(evt);
            }
        });
        jPanel9.add(btnVolver5);
        btnVolver5.setBounds(809, 402, 69, 23);
        jPanel9.add(jSeparator1);
        jSeparator1.setBounds(29, 41, 850, 10);

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

        jPanel9.add(jScrollPane11);
        jScrollPane11.setBounds(39, 155, 809, 236);

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

        cmbRevision.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NUMERO", "CLIENTE", "NIT O CEDULA", "SERIE" }));
        cmbRevision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRevisionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbRevision, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autoRevision, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBusca5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBusca5)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(cmbRevision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(autoRevision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel9.add(jPanel13);
        jPanel13.setBounds(39, 57, 291, 71);

        txtIdForanea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdForaneaActionPerformed(evt);
            }
        });
        jPanel9.add(txtIdForanea);
        txtIdForanea.setBounds(39, 403, 76, 20);

        actualizar1.setText("jButton2");
        actualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizar1ActionPerformed(evt);
            }
        });
        jPanel9.add(actualizar1);
        actualizar1.setBounds(370, 57, 26, 23);

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

        jPanel9.add(jPanel15);
        jPanel15.setBounds(406, 57, 254, 71);

        jTabbedPane3.addTab("REVISION", jPanel9);

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setLayout(null);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("GARANTIAS EN PROCESO");
        jPanel4.add(jLabel11);
        jLabel11.setBounds(33, 11, 393, 29);

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

        jPanel4.add(jScrollPane8);
        jScrollPane8.setBounds(33, 234, 837, 156);

        btnSalir4.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalir4.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir4.setText("Salir");
        btnSalir4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir4ActionPerformed(evt);
            }
        });
        jPanel4.add(btnSalir4);
        btnSalir4.setBounds(783, 11, 57, 23);

        btnVolver3.setBackground(new java.awt.Color(51, 153, 255));
        btnVolver3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnVolver3.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver3.setText("Volver");
        btnVolver3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolver3ActionPerformed(evt);
            }
        });
        jPanel4.add(btnVolver3);
        btnVolver3.setBounds(801, 396, 69, 23);

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

        jPanel4.add(jPanel8);
        jPanel8.setBounds(493, 62, 377, 104);

        actualizar.setText("jButton2");
        actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });
        jPanel4.add(actualizar);
        actualizar.setBounds(441, 62, 26, 23);

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

        jPanel4.add(jPanel10);
        jPanel10.setBounds(39, 62, 396, 131);
        jPanel4.add(jSeparator2);
        jSeparator2.setBounds(39, 46, 831, 10);
        jPanel4.add(txtIdProceso);
        txtIdProceso.setBounds(33, 403, 73, 20);

        jButton8.setBackground(new java.awt.Color(0, 153, 153));
        jButton8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("ACTUALIZAR");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton8);
        jButton8.setBounds(39, 199, 396, 29);

        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel14);
        jPanel14.setBounds(577, 172, 144, 56);

        jButton2.setBackground(new java.awt.Color(204, 0, 0));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("RECHAZAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2);
        jButton2.setBounds(115, 396, 102, 32);

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

        jPanel4.add(jPanel16);
        jPanel16.setBounds(493, 172, 78, 56);

        jTabbedPane3.addTab("PROCESO", jPanel4);

        jPanel11.setBackground(new java.awt.Color(153, 153, 153));
        jPanel11.setLayout(null);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("HISTORIAL DE GARANTIAS");
        jPanel11.add(jLabel17);
        jLabel17.setBounds(287, 11, 328, 29);

        areaGarantia1.setColumns(20);
        areaGarantia1.setRows(5);
        jScrollPane9.setViewportView(areaGarantia1);

        jPanel11.add(jScrollPane9);
        jScrollPane9.setBounds(382, 77, 458, 96);

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

        jPanel11.add(jScrollPane10);
        jScrollPane10.setBounds(10, 191, 876, 227);

        btnSalir7.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalir7.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir7.setText("Salir");
        btnSalir7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir7ActionPerformed(evt);
            }
        });
        jPanel11.add(btnSalir7);
        btnSalir7.setBounds(829, 11, 57, 23);

        btnVolver6.setBackground(new java.awt.Color(51, 153, 255));
        btnVolver6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnVolver6.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver6.setText("Volver");
        btnVolver6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolver6ActionPerformed(evt);
            }
        });
        jPanel11.add(btnVolver6);
        btnVolver6.setBounds(817, 425, 69, 23);

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

        jPanel11.add(jPanel12);
        jPanel12.setBounds(29, 77, 313, 81);

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

        Formatos_Tec obj = new Formatos_Tec();
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

        Formatos_Tec obj = new Formatos_Tec();
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

        Formatos_Tec obj = new Formatos_Tec();
        obj.setVisible(true);
        dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnVolver3ActionPerformed

    private void tbEntradasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEntradasMouseClicked

        int seleccion = tbEntradas.getSelectedRow();
        txtIdEntrada.setText(String.valueOf(tbEntradas.getValueAt(seleccion, 0)));
        areaEntrada.setText(String.valueOf(tbEntradas.getValueAt(seleccion, 12)));

// TODO add your handling code here:
    }//GEN-LAST:event_tbEntradasMouseClicked

    private void btnSalir5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir5ActionPerformed

        Bienvenida obj = new Bienvenida();
        obj.setVisible(true);
        dispose();

// TODO add your handling code here:
    }//GEN-LAST:event_btnSalir5ActionPerformed

    private void btnVolver4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolver4ActionPerformed

        Formatos_Tec obj = new Formatos_Tec();
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

        Formatos_Tec obj = new Formatos_Tec();
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
        Formatos_Tec obj = new Formatos_Tec();
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

    private void btnBusca5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusca5ActionPerformed

        String guardar = autoRevision.getText();
        int tipo = cmbRevision.getSelectedIndex();
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
                        en.setMarca(rs.getString("marca"));
                        en.setElemento(rs.getString("elemento"));
                        en.setModelo(rs.getString("modelo"));
                        en.setSerie(rs.getString("serie"));
                        en.setGarantia(rs.getString("garantia"));
                        en.setEstado(rs.getString("estado"));
                        entrada.add(en);
                        DefaultTableModel tb = (DefaultTableModel) tbEntradas.getModel();
                        tb.addRow(new Object[]{en.getId_entrada(), en.getFecha(), en.getNumero(), en.getEmpresa(), en.getNit(),  en.getMarca(), en.getElemento(), en.getModelo(), en.getSerie(), en.getGarantia(), en.getEstado()});
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
                        en.setMarca(rs.getString("marca"));
                        en.setElemento(rs.getString("elemento"));
                        en.setModelo(rs.getString("modelo"));
                        en.setSerie(rs.getString("serie"));
                        en.setGarantia(rs.getString("garantia"));
                        en.setEstado(rs.getString("estado"));
                        entrada.add(en);
                        DefaultTableModel tb = (DefaultTableModel) tbEntradas.getModel();
                        tb.addRow(new Object[]{en.getId_entrada(), en.getFecha(), en.getNumero(), en.getEmpresa(), en.getNit(),  en.getMarca(), en.getElemento(), en.getModelo(), en.getSerie(), en.getGarantia(), en.getEstado()});
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
                        en.setMarca(rs.getString("marca"));
                        en.setElemento(rs.getString("elemento"));
                        en.setModelo(rs.getString("modelo"));
                        en.setSerie(rs.getString("serie"));
                        en.setGarantia(rs.getString("garantia"));
                        en.setEstado(rs.getString("estado"));
                        entrada.add(en);
                        DefaultTableModel tb = (DefaultTableModel) tbEntradas.getModel();
                        tb.addRow(new Object[]{en.getId_entrada(), en.getFecha(), en.getNumero(), en.getEmpresa(), en.getNit(),  en.getMarca(), en.getElemento(), en.getModelo(), en.getSerie(), en.getGarantia(), en.getEstado()});
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
                        en.setMarca(rs.getString("marca"));
                        en.setElemento(rs.getString("elemento"));
                        en.setModelo(rs.getString("modelo"));
                        en.setSerie(rs.getString("serie"));
                        en.setGarantia(rs.getString("garantia"));
                        en.setEstado(rs.getString("estado"));
                        entrada.add(en);
                        DefaultTableModel tb = (DefaultTableModel) tbEntradas.getModel();
                        tb.addRow(new Object[]{en.getId_entrada(), en.getFecha(), en.getNumero(), en.getEmpresa(), en.getNit(),  en.getMarca(), en.getElemento(), en.getModelo(), en.getSerie(), en.getGarantia(), en.getEstado()});
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
    }//GEN-LAST:event_btnBusca5ActionPerformed

    private void cmbRevisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRevisionActionPerformed

        autoCompleteRevision();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRevisionActionPerformed

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
            gar.setRma(txtRmaGar.getText().toUpperCase());
            dbGarantia.Asignar(gar);
            JOptionPane.showMessageDialog(this, "Registros asignados");
            LimpiarGarantiasProceso();
            ListarGarantiasProceso();
            limpiarTextFiealdProceso();

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

    private void btnSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalidaActionPerformed

        Entrada_Tec obj = new Entrada_Tec();
        obj.setVisible(true);
        dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalidaActionPerformed

    private void btnSalida2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalida2ActionPerformed

        Salidass_Tec obj = new Salidass_Tec();
        obj.setVisible(true);
        dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalida2ActionPerformed

    private void btnSalida1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalida1ActionPerformed

        Envio_Tec obj = new Envio_Tec();
        obj.setVisible(true);
        dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalida1ActionPerformed

    private void btnSalida3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalida3ActionPerformed

        Entrada_Tec obj = new Entrada_Tec();
        obj.setVisible(true);
        dispose();
        
// TODO add your handling code here:
    }//GEN-LAST:event_btnSalida3ActionPerformed

    private void btnSalida4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalida4ActionPerformed

        Salidass_Tec obj = new Salidass_Tec();
        obj.setVisible(true);
        dispose();
        
// TODO add your handling code here:
    }//GEN-LAST:event_btnSalida4ActionPerformed

    private void btnSalida5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalida5ActionPerformed

        Envio_Tec obj = new Envio_Tec();
        obj.setVisible(true);
        dispose();
        
// TODO add your handling code here:
    }//GEN-LAST:event_btnSalida5ActionPerformed

    private void btnSalida6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalida6ActionPerformed

        Entrada_Tec obj = new Entrada_Tec();
        obj.setVisible(true);
        dispose();
        
// TODO add your handling code here:
    }//GEN-LAST:event_btnSalida6ActionPerformed

    private void btnSalida7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalida7ActionPerformed

        Salidass_Tec obj = new Salidass_Tec();
        obj.setVisible(true);
        dispose();
        
// TODO add your handling code here:
    }//GEN-LAST:event_btnSalida7ActionPerformed

    private void btnSalida8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalida8ActionPerformed

        Envio_Tec obj = new Envio_Tec();
        obj.setVisible(true);
        dispose();
        
// TODO add your handling code here:
    }//GEN-LAST:event_btnSalida8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed

        Facturas_Entrada_Tec obj = new Facturas_Entrada_Tec();
        obj.setVisible(true);
        
// TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

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
    private javax.swing.JTextField autoRevision;
    private javax.swing.JTextField autoSal;
    private javax.swing.JButton btnBusca;
    private javax.swing.JButton btnBusca1;
    private javax.swing.JButton btnBusca2;
    private javax.swing.JButton btnBusca3;
    private javax.swing.JButton btnBusca4;
    private javax.swing.JButton btnBusca5;
    private javax.swing.JButton btnBusca6;
    private javax.swing.JButton btnSalida;
    private javax.swing.JButton btnSalida1;
    private javax.swing.JButton btnSalida2;
    private javax.swing.JButton btnSalida3;
    private javax.swing.JButton btnSalida4;
    private javax.swing.JButton btnSalida5;
    private javax.swing.JButton btnSalida6;
    private javax.swing.JButton btnSalida7;
    private javax.swing.JButton btnSalida8;
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
    private javax.swing.JComboBox cmbProceso;
    private javax.swing.JComboBox cmbRevision;
    private javax.swing.JComboBox cmbSalidas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JTextField txtNuevaSerie;
    private javax.swing.JTextField txtRmaGar;
    // End of variables declaration//GEN-END:variables
}
