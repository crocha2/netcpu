/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conMySql;

import clasesPrincipales.clientes;
import clasesPrincipales.productos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
//import run.Main;


//import com.mysql.jdbc.PreparedStatement;
//import com.mysql.jdbc.Statement;
//import com.mysql.jdbc.Connection;
/**
 *
 * @author CPU_SYS
 */
public class productosMySql {

    //Codigo para el listado en la base de datos...
    public ArrayList<productos> ListProductos() {
        ArrayList<productos> producto = new ArrayList();
        try {
            
            //Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            //Connection cn = DataBaseConexion.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM productos WHERE estado = 'PROCESO' ORDER BY item ASC");
            while (rs.next()) {
                productos pro = new productos();
                pro.setItem(rs.getString("item"));
                pro.setCantidad(rs.getInt("cantidad"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setValor_unitario(rs.getInt("valor_unitario"));
                pro.setValor_total(rs.getInt("valor_total"));
                pro.setId_producto(rs.getInt("id_producto"));
           
                producto.add(pro);      
            }
            cn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en listado:\n"+ex.getMessage());
        }
        return producto;
    }

    //Codigo para INSERTAR DATOS.........................................................
    public void insertarProducto(productos pro) {
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            PreparedStatement pst =  cn.prepareStatement("INSERT INTO productos(numero, item, cantidad, descripcion, valor_unitario, valor_total, estado, id_cli) VALUES (?,?,?,?,?,?,?,?)");
            
            pst.setString(1, pro.getNumero());
            pst.setString(2, pro.getItem());
            pst.setInt(3, pro.getCantidad());
            pst.setString(4, pro.getDescripcion());
            pst.setInt(5, pro.getValor_unitario());
            pst.setInt(6, pro.getValor_total());
            pst.setString(7, pro.getEstado());
            pst.setInt(8, pro.getId_cli());
            pst.executeUpdate();

            cn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar:\n"+ex.getMessage()); 
        }
    }


    public void EditarProducto(productos pro) {
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            PreparedStatement pst = (PreparedStatement) cn.prepareStatement("UPDATE productos SET item=?,cantidad=?,descripcion=?,valor_unitario=?,valor_total=? WHERE id_producto = ?");
            pst.setString(1, pro.getItem());
            pst.setInt(2, pro.getCantidad());
            pst.setString(3, pro.getDescripcion());
            pst.setDouble(4, pro.getValor_unitario());
            pst.setDouble(5, pro.getValor_total());
            pst.setInt(6, pro.getId_producto());
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Editado exitosamente");
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(productosMySql.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al editar:\n"+ex.getMessage());
        }
    }

    public void EliminarProducto(productos pro) {
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            PreparedStatement pst = (PreparedStatement) cn.prepareStatement("DELETE FROM productos WHERE id_producto=?");
            pst.setInt(1, pro.getId_producto());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Eliminado exitosamente");
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(productosMySql.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al eliminar:\n"+ex.getMessage());
        }
    }

}
