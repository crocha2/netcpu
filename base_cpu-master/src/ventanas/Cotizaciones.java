package ventanas;

import clasesPrincipales.Entradas;
import clasesPrincipales.clientes;
import clasesPrincipales.cotizaciones;
import clasesPrincipales.productos;
import com.mxrck.autocompleter.TextAutoCompleter;
import conMySql.GenerarNumeros;
import conMySql.cotizaMySql;
import conMySql.productosMySql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.DocFlavor;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class Cotizaciones extends javax.swing.JFrame {

    ArrayList<productos> producto;
    productosMySql dbProducto = new productosMySql();
    
    ArrayList<cotizaciones> cotizacion;
    cotizaMySql dbCotiza = new cotizaMySql();

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
        txtIdProducto.setVisible(false);
        txtIdCliente.setEnabled(false);
        txtTotal.setEnabled(false);
        txtItem.setEnabled(false);
        numero.setEnabled(false);
        txtSubTotal.setEnabled(false);
        txtFletes.setEnabled(false);
        txtIva.setEnabled(false);
        txtGranTotal.setEnabled(false);
        colorNumero();
        autoCompleteCliente();
        cero();
        traerNumeros();
        txtItem.setText("1");
    }

    public void colorNumero() {
        numero.setDisabledTextColor(java.awt.Color.BLUE);
        txtTotal.setDisabledTextColor(java.awt.Color.BLUE);
//txtSec.setText(gen.serie());
    }

    public void autoCompleteCliente() {
        TextAutoCompleter TextAutoCompleter = new TextAutoCompleter(txtCliente);
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            Statement st = (Statement) cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT nombre_cli FROM clientes");
            while (rs.next()) {
                TextAutoCompleter.addItem(rs.getString("nombre_cli"));
            }
            TextAutoCompleter.isCaseSensitive();
            cn.close();
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void cero() {
        txtCantidad.setText("0");
        txtValorUnitario.setText("0");
    }

    public void LimpiarCliente() {
        txtCuidadCliente.setText("");
        txtIdCliente.setText("");
    }

    public void LimpiarPanelProductos() {
        txtCantidad.setText("0");
        txtValorUnitario.setText("0");
        txtTotal.setText("");
        txtDescripcion.setText("");
        txtCantidad.requestFocus();
        txtItem.setVisible(true);
    }

    public void ListarProductos() {
        producto = dbProducto.ListProductos();
        DefaultTableModel tb = (DefaultTableModel) tbProductos.getModel();
        for (productos pro : producto) {
            tb.addRow(new Object[]{pro.getItem(), pro.getCantidad(), pro.getDescripcion(), pro.getValor_unitario(), pro.getValor_total(), pro.getId_producto()});
        }
    }
    
    public void traerClienteYNumero() {
        
        //int seleccion = tbProductos.getSelectedRow();
        
        
        try {
            int guardar = Integer.parseInt("" + tbProductos.getValueAt(0, 5));
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            Statement st = cn.createStatement();
            PreparedStatement pst = cn.prepareStatement("SELECT p.id_cli FROM productos p WHERE id_producto = ?");
            pst.setInt(1, guardar);
            ResultSet rs = pst.executeQuery();
            txtIdCliente.setText("");
            if (rs.next()) {
                clientes cl = new clientes();
                txtIdCliente.setText(rs.getString("id_cli"));
            } else {
                JOptionPane.showMessageDialog(null, "No existe el cliente");
            }
            cn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error\n" + ex.getMessage());
        }
        
        try {
            int guar = Integer.parseInt(txtIdCliente.getText());
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            Statement st = cn.createStatement();
            PreparedStatement pst = cn.prepareStatement("SELECT c.nombre_cli, c.ciudad_cli FROM clientes c WHERE id_cli = ?");
            pst.setInt(1, guar);
            ResultSet rs = pst.executeQuery();
            txtCliente.setText("");
            txtCuidadCliente.setText("");
            if (rs.next()) {
                clientes cl = new clientes();
                txtCliente.setText(rs.getString("nombre_cli"));
                txtCuidadCliente.setText(rs.getString("ciudad_cli"));
            } else {
                JOptionPane.showMessageDialog(null, "No existe el cliente");
            }
            cn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error\n" + ex.getMessage());
        }
        
    }

    public void LimpiarProductos() {
        DefaultTableModel tb = (DefaultTableModel) tbProductos.getModel();
        for (int i = tb.getRowCount() - 1; i >= 0; i--) {
            tb.removeRow(i);
        }
    }

    void traerNumeros() {
        int j;
        String c = "";
        String SQL = "SELECT MAX(numero) AS numero FROM cotizaciones";

        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                c = rs.getString("numero");
            }
            if (c == null) {
                numero.setText("01-000001");
                System.out.println(c);
            } else {
                char n1 = c.charAt(0);
                char n2 = c.charAt(1);
                String junto = "" + n1 + n2;

                if (junto.equals("01")) {
                    cmbConcepto.setSelectedIndex(0);
                    char r1 = c.charAt(3);
                    char r2 = c.charAt(4);
                    char r3 = c.charAt(5);
                    char r4 = c.charAt(6);
                    char r5 = c.charAt(7);
                    char r6 = c.charAt(8);

                    System.out.println("" + r1 + r2 + r3 + r4 + r5 + r6);
                    String juntar = "" + r1 + r2 + r3 + r4 + r5 + r6;
                    int var = Integer.parseInt(juntar);
                    String concepto = "01";
                    System.out.println(concepto + var);

                    System.out.println("\n lo que vale: " + var);
                    GenerarNumeros gen = new GenerarNumeros();
                    gen.generarCotiza(var, concepto);

                    numero.setDisabledTextColor(java.awt.Color.BLUE);
                    numero.setText(gen.serie());
                }
                if (junto.equals("02")) {
                    cmbConcepto.setSelectedIndex(1);
                    char r1 = c.charAt(3);
                    char r2 = c.charAt(4);
                    char r3 = c.charAt(5);
                    char r4 = c.charAt(6);
                    char r5 = c.charAt(7);
                    char r6 = c.charAt(8);

                    System.out.println("" + r1 + r2 + r3 + r4 + r5 + r6);
                    String juntar = "" + r1 + r2 + r3 + r4 + r5 + r6;
                    int var = Integer.parseInt(juntar);
                    String concepto = "02";
                    System.out.println(concepto + var);

                    System.out.println("\n lo que vale: " + var);
                    GenerarNumeros gen = new GenerarNumeros();
                    gen.generarCotiza(var, concepto);

                    numero.setDisabledTextColor(java.awt.Color.BLUE);
                    numero.setText(gen.serie());
                }
                if (junto.equals("03")) {
                    cmbConcepto.setSelectedIndex(2);
                    char r1 = c.charAt(3);
                    char r2 = c.charAt(4);
                    char r3 = c.charAt(5);
                    char r4 = c.charAt(6);
                    char r5 = c.charAt(7);
                    char r6 = c.charAt(8);

                    System.out.println("" + r1 + r2 + r3 + r4 + r5 + r6);
                    String juntar = "" + r1 + r2 + r3 + r4 + r5 + r6;
                    int var = Integer.parseInt(juntar);
                    String concepto = "03";
                    System.out.println(concepto + var);

                    System.out.println("\n lo que vale: " + var);
                    GenerarNumeros gen = new GenerarNumeros();
                    gen.generarCotiza(var, concepto);

                    numero.setDisabledTextColor(java.awt.Color.BLUE);
                    numero.setText(gen.serie());
                }
                if (junto.equals("04")) {
                    cmbConcepto.setSelectedIndex(3);
                    char r1 = c.charAt(3);
                    char r2 = c.charAt(4);
                    char r3 = c.charAt(5);
                    char r4 = c.charAt(6);
                    char r5 = c.charAt(7);
                    char r6 = c.charAt(8);

                    System.out.println("" + r1 + r2 + r3 + r4 + r5 + r6);
                    String juntar = "" + r1 + r2 + r3 + r4 + r5 + r6;
                    int var = Integer.parseInt(juntar);
                    String concepto = "04";
                    System.out.println(concepto + var);

                    System.out.println("\n lo que vale: " + var);
                    GenerarNumeros gen = new GenerarNumeros();
                    gen.generarCotiza(var, concepto);

                    numero.setDisabledTextColor(java.awt.Color.BLUE);
                    numero.setText(gen.serie());
                }
            }

        } catch (Exception e) {
        }

    }

    void numeros() {
        int j;
        String c = "";
        String SQL = "SELECT MAX(numero) AS numero FROM cotizaciones";

        if (cmbConcepto.getSelectedIndex() == 0) {
            try {
                Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(SQL);
                if (rs.next()) {
                    c = rs.getString("numero");
                }
                System.out.println(c);
                if (c == null) {
                    numero.setText("01-000001");
                    System.out.println(c);
                } else {
                    char r1 = c.charAt(3);
                    char r2 = c.charAt(4);
                    char r3 = c.charAt(5);
                    char r4 = c.charAt(6);
                    char r5 = c.charAt(7);
                    char r6 = c.charAt(8);

                    System.out.println("" + r1 + r2 + r3 + r4 + r5 + r6);
                    String juntar = "" + r1 + r2 + r3 + r4 + r5 + r6;
                    int var = Integer.parseInt(juntar);
                    String concepto = "01";
                    System.out.println(concepto + var);

                    System.out.println("\n lo que vale: " + var);
                    GenerarNumeros gen = new GenerarNumeros();
                    gen.generarCotiza(var, concepto);

                    numero.setDisabledTextColor(java.awt.Color.BLUE);
                    numero.setText(gen.serie());
                }

            } catch (SQLException | NumberFormatException ex) {
                Logger.getLogger(Entradas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (cmbConcepto.getSelectedIndex() == 1) {
            try {
                Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(SQL);
                if (rs.next()) {
                    c = rs.getString("numero");
                }
                System.out.println(c);
                if (c == null) {
                    numero.setText("02-000001");
                    System.out.println(c);
                } else {
                    char r1 = c.charAt(3);
                    char r2 = c.charAt(4);
                    char r3 = c.charAt(5);
                    char r4 = c.charAt(6);
                    char r5 = c.charAt(7);
                    char r6 = c.charAt(8);

                    System.out.println("" + r1 + r2 + r3 + r4 + r5 + r6);
                    String juntar = "" + r1 + r2 + r3 + r4 + r5 + r6;
                    int var = Integer.parseInt(juntar);
                    String concepto = "02";
                    System.out.println(concepto + var);

                    System.out.println("\n lo que vale: " + var);
                    GenerarNumeros gen = new GenerarNumeros();
                    gen.generarCotiza(var, concepto);

                    numero.setDisabledTextColor(java.awt.Color.BLUE);
                    numero.setText(gen.serie());
                }

            } catch (SQLException | NumberFormatException ex) {
                Logger.getLogger(Entradas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (cmbConcepto.getSelectedIndex() == 2) {
            try {
                Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(SQL);
                if (rs.next()) {
                    c = rs.getString("numero");
                }
                System.out.println(c);
                if (c == null) {
                    numero.setText("03-000001");
                    System.out.println(c);
                } else {
                    char r1 = c.charAt(3);
                    char r2 = c.charAt(4);
                    char r3 = c.charAt(5);
                    char r4 = c.charAt(6);
                    char r5 = c.charAt(7);
                    char r6 = c.charAt(8);

                    System.out.println("" + r1 + r2 + r3 + r4 + r5 + r6);
                    String juntar = "" + r1 + r2 + r3 + r4 + r5 + r6;
                    int var = Integer.parseInt(juntar);
                    String concepto = "03";
                    System.out.println(concepto + var);

                    System.out.println("\n lo que vale: " + var);
                    GenerarNumeros gen = new GenerarNumeros();
                    gen.generarCotiza(var, concepto);

                    numero.setDisabledTextColor(java.awt.Color.BLUE);
                    numero.setText(gen.serie());
                }

            } catch (SQLException | NumberFormatException ex) {
                Logger.getLogger(Entradas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (cmbConcepto.getSelectedIndex() == 3) {
            try {
                Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(SQL);
                if (rs.next()) {
                    c = rs.getString("numero");
                }
                System.out.println(c);
                if (c == null) {
                    numero.setText("04-000001");
                    System.out.println(c);
                } else {
                    char r1 = c.charAt(3);
                    char r2 = c.charAt(4);
                    char r3 = c.charAt(5);
                    char r4 = c.charAt(6);
                    char r5 = c.charAt(7);
                    char r6 = c.charAt(8);

                    System.out.println("" + r1 + r2 + r3 + r4 + r5 + r6);
                    String juntar = "" + r1 + r2 + r3 + r4 + r5 + r6;
                    int var = Integer.parseInt(juntar);
                    String concepto = "04";
                    System.out.println(concepto + var);

                    System.out.println("\n lo que vale: " + var);
                    GenerarNumeros gen = new GenerarNumeros();
                    gen.generarCotiza(var, concepto);

                    numero.setDisabledTextColor(java.awt.Color.BLUE);
                    numero.setText(gen.serie());
                }

            } catch (SQLException | NumberFormatException ex) {
                Logger.getLogger(Entradas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    public void calcular(){
        txtSubTotal.setText("0");
        int ta = tbProductos.getRowCount();
        System.out.println("filas: " + ta);
        int c = 0;

        do {
            try {
                int f = c++;
                double num1 = Double.parseDouble(tbProductos.getValueAt(f, 4).toString());
                String dato = txtSubTotal.getText();
                double num2 = Double.parseDouble(dato);

                double resultado = num1 + num2;
                txtSubTotal.setDisabledTextColor(java.awt.Color.RED);
                txtSubTotal.setText(String.valueOf(resultado));

                double fletes = resultado * (0.015);
                txtFletes.setDisabledTextColor(java.awt.Color.RED);
                txtFletes.setText(String.valueOf(fletes));

                double iva = (resultado + fletes) * 0.19;
                txtIva.setDisabledTextColor(java.awt.Color.RED);
                txtIva.setText(String.valueOf(iva));

                double granTotal = resultado + fletes + iva;
                txtGranTotal.setDisabledTextColor(java.awt.Color.RED);
                txtGranTotal.setText(String.valueOf(granTotal));
                
                //txtGranTotal.setDisabledTextColor(java.awt.Color.RED);
                //txtGranTotal.setText(String.valueOf(granTotal));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
            }
        } while (c < ta);
       
        double sub = Double.parseDouble(txtSubTotal.getText());
        double fle = Double.parseDouble(txtFletes.getText());
        double iva = Double.parseDouble(txtIva.getText());
        double GranTotal = Double.parseDouble(txtGranTotal.getText());
        
        Locale locale = new Locale("es", "AR");
        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
        txtGranTotal.setDisabledTextColor(java.awt.Color.RED);
        txtGranTotal.setText(nf.format(GranTotal));
   
        Locale locale2 = new Locale("es", "AR");
        NumberFormat nf2 = NumberFormat.getCurrencyInstance(locale2);
        txtIva.setDisabledTextColor(java.awt.Color.RED);
        txtIva.setText(nf2.format(iva));
        
        Locale locale3 = new Locale("es", "AR");
        NumberFormat nf3 = NumberFormat.getCurrencyInstance(locale3);
        txtFletes.setDisabledTextColor(java.awt.Color.RED);
        txtFletes.setText(nf2.format(fle));
        
        Locale locale4 = new Locale("es", "AR");
        NumberFormat nf4 = NumberFormat.getCurrencyInstance(locale4);
        txtSubTotal.setDisabledTextColor(java.awt.Color.RED);
        txtSubTotal.setText(nf2.format(sub));
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
        txtFecha = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        txtCuidadCliente = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        txtPais = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        txtIdCliente = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbProductos = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        txtValorUnitario = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        txtItem = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        txtIdProducto = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
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
        txtGranTotal = new javax.swing.JTextField();
        txtIva = new javax.swing.JTextField();
        txtFletes = new javax.swing.JTextField();
        txtSubTotal = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtComentario = new javax.swing.JTextArea();
        jLabel23 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 153));
        jLabel2.setText("COTIZACIÓN");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(460, 10, 410, 70);

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
        jSeparator1.setBounds(20, 90, 1270, 10);
        jPanel1.add(numero);
        numero.setBounds(1130, 50, 160, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 153));
        jLabel3.setText("No.");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(1090, 60, 40, 20);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Generales", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel4.setLayout(null);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 153));
        jLabel8.setText("CIUDAD");
        jPanel4.add(jLabel8);
        jLabel8.setBounds(1040, 20, 80, 20);

        txtFecha.setDateFormatString("yyyy-MM-dd");
        jPanel4.add(txtFecha);
        txtFecha.setBounds(600, 40, 200, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 153));
        jLabel9.setText("FECHA");
        jPanel4.add(jLabel9);
        jLabel9.setBounds(600, 20, 40, 20);
        jPanel4.add(txtCuidadCliente);
        txtCuidadCliente.setBounds(1040, 40, 210, 30);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 153));
        jLabel10.setText("CLIENTE");
        jPanel4.add(jLabel10);
        jLabel10.setBounds(10, 20, 80, 20);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 153));
        jLabel11.setText("PAIS");
        jPanel4.add(jLabel11);
        jLabel11.setBounds(820, 20, 80, 20);
        jPanel4.add(txtCliente);
        txtCliente.setBounds(10, 40, 440, 30);
        jPanel4.add(txtPais);
        txtPais.setBounds(820, 40, 200, 30);

        jButton10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton10.setText(">>");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton10);
        jButton10.setBounds(460, 40, 60, 30);
        jPanel4.add(txtIdCliente);
        txtIdCliente.setBounds(520, 40, 50, 30);

        jPanel1.add(jPanel4);
        jPanel4.setBounds(20, 100, 1270, 90);

        tbProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ITEM", "CANT", "DESCRIPCIÓN", "V.UNITARIO", "V.TOTAL", "ID"
            }
        ));
        tbProductos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProductosMouseClicked(evt);
            }
        });
        tbProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbProductosKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tbProductos);

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(500, 200, 640, 250);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos De Producto", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel5.setLayout(null);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 153));
        jLabel12.setText("ITEM");
        jPanel5.add(jLabel12);
        jLabel12.setBounds(20, 190, 40, 20);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 153));
        jLabel13.setText("CANT.");
        jPanel5.add(jLabel13);
        jLabel13.setBounds(20, 20, 50, 20);

        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });
        jPanel5.add(txtTotal);
        txtTotal.setBounds(290, 40, 160, 30);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 153));
        jLabel14.setText("DESCRIPCIÓN");
        jPanel5.add(jLabel14);
        jLabel14.setBounds(20, 80, 120, 20);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 153));
        jLabel15.setText("V. UNITARIO");
        jPanel5.add(jLabel15);
        jLabel15.setBounds(130, 20, 80, 20);

        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });
        jPanel5.add(txtCantidad);
        txtCantidad.setBounds(20, 40, 80, 30);

        txtValorUnitario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorUnitarioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorUnitarioKeyTyped(evt);
            }
        });
        jPanel5.add(txtValorUnitario);
        txtValorUnitario.setBounds(130, 40, 140, 30);

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane4.setViewportView(txtDescripcion);

        jPanel5.add(jScrollPane4);
        jScrollPane4.setBounds(20, 100, 430, 90);

        jButton5.setBackground(new java.awt.Color(0, 153, 51));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("INSERTAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5);
        jButton5.setBounds(120, 200, 140, 40);

        jButton6.setBackground(new java.awt.Color(0, 0, 153));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("EDITAR");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton6);
        jButton6.setBounds(270, 200, 90, 40);
        jPanel5.add(txtItem);
        txtItem.setBounds(20, 210, 60, 30);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 153));
        jLabel21.setText("V. TOTAL");
        jPanel5.add(jLabel21);
        jLabel21.setBounds(290, 20, 80, 20);

        jButton4.setBackground(new java.awt.Color(204, 0, 0));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("QUITAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4);
        jButton4.setBounds(360, 200, 90, 40);

        jButton12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton12.setText("...");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton12);
        jButton12.setBounds(409, 10, 50, 23);
        jPanel5.add(txtIdProducto);
        txtIdProducto.setBounds(390, 70, 59, 20);

        jButton13.setText("+");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton13);
        jButton13.setBounds(80, 200, 40, 20);

        jButton15.setText("-");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton15);
        jButton15.setBounds(80, 220, 40, 20);

        jPanel1.add(jPanel5);
        jPanel5.setBounds(20, 200, 470, 250);

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
        panelProducto.setBounds(620, 470, 390, 180);

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
        panelServicio.setBounds(620, 470, 390, 180);
        jPanel1.add(jSeparator2);
        jSeparator2.setBounds(670, 550, 240, 0);

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        txtGranTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(txtGranTotal);
        txtGranTotal.setBounds(100, 110, 160, 30);

        txtIva.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel3.add(txtIva);
        txtIva.setBounds(100, 70, 160, 30);

        txtFletes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel3.add(txtFletes);
        txtFletes.setBounds(100, 40, 160, 30);

        txtSubTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtSubTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSubTotalKeyReleased(evt);
            }
        });
        jPanel3.add(txtSubTotal);
        txtSubTotal.setBounds(100, 10, 160, 30);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(1030, 470, 270, 150);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Bienvenido.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(1160, 200, 150, 120);

        jButton11.setBackground(new java.awt.Color(0, 0, 102));
        jButton11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("COTIZAR");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton11);
        jButton11.setBounds(1030, 630, 270, 40);

        txtComentario.setColumns(20);
        txtComentario.setRows(5);
        jScrollPane1.setViewportView(txtComentario);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(20, 480, 590, 170);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 153));
        jLabel23.setText("COMENTARIO");
        jPanel1.add(jLabel23);
        jLabel23.setBounds(20, 460, 100, 20);

        jButton14.setBackground(new java.awt.Color(255, 0, 51));
        jButton14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setText("+");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton14);
        jButton14.setBounds(1150, 400, 150, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1312, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void cmbConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbConceptoActionPerformed

        if (cmbConcepto.getSelectedIndex() == 1 || cmbConcepto.getSelectedIndex() == 2) {
            panelProducto.setVisible(false);
            panelServicio.setVisible(true);
        } else {
            panelServicio.setVisible(false);
            panelProducto.setVisible(true);
        }

        numeros();

// TODO add your handling code here:
    }//GEN-LAST:event_cmbConceptoActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed

        try {
            String guardar = txtCliente.getText();
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            Statement st = cn.createStatement();
            PreparedStatement pst = cn.prepareStatement("Select * from clientes where nombre_cli = ?");
            pst.setString(1, guardar);
            //pst.setString(1, CMBID.getName());
            ResultSet rs = pst.executeQuery();
            LimpiarCliente();
            if (rs.next()) {

                clientes cl = new clientes();
                txtIdCliente.setText(rs.getString("id_cli"));
                txtCuidadCliente.setText(rs.getString("ciudad_cli"));

            } else {
                JOptionPane.showMessageDialog(null, "No existe el usuario");
            }
            cn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error\n" + ex.getMessage());
        }

// TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped

        try {
            char c = evt.getKeyChar();
            if (c < '0' || c > '9') {
                evt.consume();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtValorUnitarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorUnitarioKeyTyped

        try {
            char c = evt.getKeyChar();
            if (c < '0' || c > '9') {
                evt.consume();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorUnitarioKeyTyped

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased

        String dato = txtCantidad.getText();
        String dato2 = txtValorUnitario.getText();

        if (dato.equals("") == false && dato.matches("[0-9]*") && (dato2.equals("") == false && dato2.matches("[0-9]*"))) {
            int cant = Integer.parseInt(dato);
            int valor_unitario = Integer.parseInt(dato2);

            int total = cant * valor_unitario;
            txtTotal.setDisabledTextColor(java.awt.Color.BLUE);
            txtTotal.setText("" + total);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void txtValorUnitarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorUnitarioKeyReleased

        String dato = txtCantidad.getText();
        String dato2 = txtValorUnitario.getText();

        if (dato.equals("") == false && dato.matches("[0-9]*") && (dato2.equals("") == false && dato2.matches("[0-9]*"))) {
            int cant = Integer.parseInt(dato);
            int valor_unitario = Integer.parseInt(dato2);

            int total = cant * valor_unitario;
            txtTotal.setDisabledTextColor(java.awt.Color.BLUE);
            txtTotal.setText("" + total);
        }

        /*
         String dato = txtCantidad.getText();
         String dato2 = txtValorUnitario.getText();
        
         if (dato.equals("") == false && dato.matches("[0-9]*")&&(dato2.equals("") == false && dato2.matches("[0-9]*"))) {
         double cant = Integer.parseInt(dato);
         double valor_unitario = Integer.parseInt(dato2);
         double total = cant * valor_unitario;
         //String numero = String.valueOf(total);
                
                
         //numero en formato monetario...
         Locale locale = new Locale("es","AR");
         NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
                
         txtTotal.setDisabledTextColor(java.awt.Color.BLUE);
         //txtTotal.setText("" + total);
         txtTotal.setText(nf.format(total));
                
         double num = Double.parseDouble(txtTotal.getText());
         System.out.println(""+num);
                
                
         //txtTotal.setText("" + nf.format(total));
         //System.out.println("El monto a pagar es de: " + nf.format(total));
        
         }
         */
        /*
         double monto = 500000.00;

         NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.getDefault());

         System.out.println("El monto a pagar es de: " + nf.format(monto));
         */
// TODO add your handling code here:
    }//GEN-LAST:event_txtValorUnitarioKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        if (txtIdCliente.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "DEBE SELECCIONAR UN CLIENTE");
        }
        if (txtCantidad.getText().equals("") || txtValorUnitario.getText().equals("") || txtDescripcion.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "DEBE GESTIONAR TODOS LOS DATOS DE PRODUCTO");
        } else {
            try {

                productos pro = new productos();

                pro.setNumero(numero.getText());
             
                pro.setItem(txtItem.getText());
                pro.setCantidad(Integer.parseInt(txtCantidad.getText()));
                pro.setDescripcion(txtDescripcion.getText().toUpperCase());
                pro.setValor_unitario(Integer.parseInt(txtValorUnitario.getText()));
                pro.setValor_total(Integer.parseInt(txtTotal.getText()));
                pro.setEstado("PROCESO");
                pro.setId_cli(Integer.parseInt(txtIdCliente.getText()));

                dbProducto.insertarProducto(pro);

                int cont = 1;
                int aux = Integer.parseInt(txtItem.getText());

                int item = cont + aux;
                txtItem.setText("" + item);

                LimpiarPanelProductos();
                LimpiarProductos();
                ListarProductos();
                calcular();
            } catch (Exception e) {
                System.err.println("error" + e);
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        if (txtIdProducto.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "DEBE SELECCIONAR UN REGISTRO");
        }
        if (txtCantidad.getText().equals("") || txtValorUnitario.getText().equals("") || txtDescripcion.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "DEBE GESTIONAR TODOS LOS DATOS DE PRODUCTO");
        } else {
            try {

                productos pro = new productos();

                pro.setCantidad(Integer.parseInt(txtCantidad.getText()));
                pro.setDescripcion(txtDescripcion.getText().toUpperCase());
                pro.setValor_unitario(Integer.parseInt(txtValorUnitario.getText()));
                pro.setValor_total(Integer.parseInt(txtTotal.getText()));
                pro.setId_producto(Integer.parseInt(txtIdProducto.getText()));
                pro.setItem(txtItem.getText());
                

                dbProducto.EditarProducto(pro);

                LimpiarPanelProductos();
                LimpiarProductos();
                ListarProductos();
                txtItem.setVisible(true);
                calcular();

            } catch (Exception e) {
                System.err.println("error" + e);
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void tbProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductosMouseClicked

        int seleccion = tbProductos.getSelectedRow();

        txtCantidad.setText(String.valueOf(tbProductos.getValueAt(seleccion, 1)));
        txtDescripcion.setText(String.valueOf(tbProductos.getValueAt(seleccion, 2)));
        txtValorUnitario.setText(String.valueOf(tbProductos.getValueAt(seleccion, 3)));
        txtTotal.setText(String.valueOf(tbProductos.getValueAt(seleccion, 4)));
        txtIdProducto.setText(String.valueOf(tbProductos.getValueAt(seleccion, 5)));
        txtItem.setText(String.valueOf(tbProductos.getValueAt(seleccion, 0)));
        
        txtItem.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_tbProductosMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (txtIdProducto.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "DEBE SELECCIONAR UN REGISTRO");
        } else {
            try {

                productos pro = new productos();

                pro.setId_producto(Integer.parseInt(txtIdProducto.getText()));

                dbProducto.EliminarProducto(pro);

                LimpiarPanelProductos();
                LimpiarProductos();
                ListarProductos();
                txtItem.setVisible(true);
                txtSubTotal.setText("0");
                txtFletes.setText("0");
                txtIva.setText("0");
                txtGranTotal.setText("0");

            } catch (Exception e) {
                System.err.println("error" + e);
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed

        LimpiarPanelProductos();
        LimpiarProductos();
        ListarProductos();
        traerClienteYNumero();

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed

        int cont = 1;
        int aux = Integer.parseInt(txtItem.getText());

        int item = cont + aux;
        txtItem.setText("" + item);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed

        calcular();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void txtSubTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSubTotalKeyReleased

        /*    
         String dato = txtSubTotal.getText();
         //String dato2 = txtValorUnitario.getText();
         if (dato.equals("") == false && dato.matches("[0-9]*")) {
         double SubTotal = Double.parseDouble(dato);
         //int valor_unitario = Integer.parseInt(dato2);

         double total = SubTotal * (0.015);
         txtFletes.setDisabledTextColor(java.awt.Color.BLUE);
         txtFletes.setText("" + total);
         }
         */
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubTotalKeyReleased

    private void tbProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProductosKeyReleased

        
        
// TODO add your handling code here:
    }//GEN-LAST:event_tbProductosKeyReleased

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

        if(txtIdCliente.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente para cotizar");
        }
        if(txtSubTotal.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Presione el boton + para calcular cotización");
        }else{
            try {
                cotizaciones co = new cotizaciones();
                
                co.setNumero(numero.getText());
                
                
            } catch (Exception e) {
            }
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed

        int cont = 1;
        int aux = Integer.parseInt(txtItem.getText());
        
        if (aux==1) {
            txtItem.setText("" + 1);
        }else{
            int item = aux - cont;
        txtItem.setText("" + item);
        }
  
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

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
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField numero;
    private javax.swing.JPanel panelProducto;
    private javax.swing.JPanel panelServicio;
    private javax.swing.JTable tbProductos;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextArea txtComentario;
    private javax.swing.JTextField txtCuidadCliente;
    private javax.swing.JTextArea txtDescripcion;
    private com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JTextField txtFletes;
    private javax.swing.JTextField txtGranTotal;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtIdProducto;
    private javax.swing.JTextField txtItem;
    private javax.swing.JTextField txtIva;
    private javax.swing.JTextField txtPais;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtValorUnitario;
    // End of variables declaration//GEN-END:variables
}
