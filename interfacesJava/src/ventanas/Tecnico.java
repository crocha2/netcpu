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
public class Tecnico extends javax.swing.JFrame {

    ArrayList<clientes> cliente;
    //clienteDB db = new clienteDB();
    clienteMySql db = new clienteMySql();

    /**
     * Creates new form Tabla_Clientes
     */
    public Tecnico() {
        initComponents();
        //ListarDatos();
        //LimpirTabla();
        this.setLocationRelativeTo(null);
        this.setTitle("CPU System Service S.A.S - TABLA DE CLIENTES");
        //CargarCmbCliente();
        //autoComplete();
        
    }

    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imagenes/CPU_new_2.png"));
        return retValue;
    }

    // METODOS::::::::::::::::::::
    /*
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
    */
/*
    public void CargarCmbCliente() {
        try {
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            Connection cn;
            cn = DriverManager.getConnection("jdbc:mysql://localhost/basecpu", "root", "8020123496");
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(0, 153, 153));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jTabbedPane1.addTab("ENTRADAS", jPanel1);

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jTabbedPane1.addTab("GARANTIAS", jPanel2);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
                new Tecnico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
