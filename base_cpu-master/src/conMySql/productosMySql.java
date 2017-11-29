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
            ResultSet rs = st.executeQuery("SELECT * FROM productos ORDER BY item ASC");
            while (rs.next()) {
                productos pro = new productos();
                pro.setItem(rs.getString("item"));
                pro.setCantidad(rs.getInt("cantidad"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setValor_unitario(rs.getDouble("valor_unitario"));
                pro.setValor_total(rs.getDouble("valor_total"));
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
            PreparedStatement pst =  cn.prepareStatement("INSERT INTO productos(numero, fecha, item, cantidad, descripcion, valor_unitario, valor_total, id_cli) VALUES (?,?,?,?,?,?,?,?)");
            
            pst.setString(1, pro.getNumero());
            pst.setString(2, pro.getFecha());
            pst.setString(3, pro.getItem());
            pst.setInt(4, pro.getCantidad());
            pst.setString(5, pro.getDescripcion());
            pst.setDouble(6, pro.getValor_unitario());
            pst.setDouble(7, pro.getValor_total());
            pst.setInt(8, pro.getId_cli());
            pst.executeUpdate();

            cn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar:\n"+ex.getMessage()); 
        }
    }
    /*
    public void insertarUsuarios(clientes cliente) {
        try {
            Connection cnx = DataBaseConexion.getConnection();
            PreparedStatement pst = (PreparedStatement) cnx.prepareStatement("INSERT INTO CLIENTES (NIT, NOMBRE, TELEFONO, DIRECCION, CIUDAD, CORREO, CONTACTO) \n"
                    + " VALUES (?,?,?,?,?,?,?)");
            pst.setString(1, cliente.getNit_cliente());
            pst.setString(2, cliente.getNombre_cliente());
            pst.setString(3, cliente.getTelefono_cliente());
            pst.setString(4, cliente.getDireccion_cliente());
            pst.setString(5, cliente.getCiudad_cliente());
            pst.setString(6, cliente.getCorreo_cliente());
            pst.setString(7, cliente.getNombre_contacto());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en listado");
        }
    }
    */

    //Codigo para MODIFICAR datos
    public void EditarCliente(clientes cli) {
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            PreparedStatement pst = (PreparedStatement) cn.prepareStatement("UPDATE clientes SET nit_cli = ?, nombre_cli = ?, telefono_cli = ?, direccion_cli = ?, ciudad_cli = ?, correo_cli = ?, contacto_cli = ? WHERE id_cli = ?");
            pst.setString(1, cli.getNit_cliente());
            pst.setString(2, cli.getNombre_cliente());
            pst.setString(3, cli.getTelefono_cliente());
            pst.setString(4, cli.getDireccion_cliente());
            pst.setString(5, cli.getCiudad_cliente());
            pst.setString(6, cli.getCorreo_cliente());
            pst.setString(7, cli.getNombre_contacto());
            pst.setInt(8, cli.getId_cliente());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Editado exitosamente");
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(productosMySql.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al editar:\n"+ex.getMessage());
        }
    }

    public void EditarClienteEntrada(clientes cli) {
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            PreparedStatement pst = (PreparedStatement) cn.prepareStatement("UPDATE clientes SET nit_cli = ?, nombre_cli = ?, telefono_cli = ?, correo_cli = ? WHERE id_cli = ?");
            pst.setString(1, cli.getNit_cliente());
            pst.setString(2, cli.getNombre_cliente());
            pst.setString(3, cli.getTelefono_cliente());
            pst.setString(4, cli.getCorreo_cliente());
            pst.setInt(5, cli.getId_cliente());
            pst.executeUpdate();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(productosMySql.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al editar:\n"+ex.getMessage());
        }
    }
    
    //ELIMINAR DATOS DE CLIENTES
    public void EliminarCliente(clientes cli) {
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            PreparedStatement pst = (PreparedStatement) cn.prepareStatement("DELETE FROM clientes WHERE nombre_cli=?");
            pst.setString(1, cli.getNombre_cliente());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Eliminado exitosamente");
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(productosMySql.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al eliminar:\n"+ex.getMessage());
        }
    }
    


    /*
    public void Buscar(clientes cli) {

        try {
            Connection cnx = DataBaseConexion.getConnection();
            PreparedStatement pst = cnx.prepareStatement("SELECT * FROM CLIENTES WHERE NOMBRECLIENTE = ?");
            //pst.setString(1, CMBID.getName());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {

                pst.setString(1, rs.getString(cli.getNit_cliente()).trim());
                pst.setString(2, rs.getString(cli.getNombre_cliente()).trim());
                pst.setString(3, rs.getString(cli.getTelefono_cliente()).trim());
                pst.setString(4, rs.getString(cli.getDireccion_cliente()).trim());
                pst.setString(5, rs.getString(cli.getCiudad_cliente()).trim());
                pst.setString(6, rs.getString(cli.getCorreo_cliente()).trim());
                pst.setString(7, rs.getString(cli.getNombre_contacto()).trim());
           
                //pst.setString(1, CMBID.getName());
                //String guardar = txtBuscar.getText();
                //txtID.setText(guardar);

            } else {
                JOptionPane.showMessageDialog(null, "No existe el usuario");
            }
        } catch (Exception er) {
            System.out.println("error en buscar " + er);
        }
    }
    */
   

    /*

     //BUSCAR DATOS DE CLIENTES
     public clientes Buscar(String buscar) {
     clientes c = null;
     try {
     Connection cnx = DataBaseConexion.getConnection();
     Statement st = cnx.createStatement();
     ResultSet rs = st.executeQuery("SELECT IDCLIENTE,NOMBRE,APELLIDO,DNI,TELEFONO,DIRECCION"
     + " FROM CLIENTES "
     + " WHERE NOMBRE='" + buscar + "' OR DNI='" + buscar + "'  ");
     while (rs.next()) {
     c = new clientes();
     c.setId_cliente(rs.getInt(1));
     c.setNit_cliente(rs.getString(2));
     c.setNombre_cliente(rs.getString(3));
     c.setTelefono_cliente(rs.getString(4));
     c.setDireccion_cliente(rs.getString(5));
     c.setCiudad_cliente(rs.getString(6));
     c.setCorreo_cliente(rs.getString(7));
     c.setNombre_contacto(rs.getString(8));
     }
     } catch (SQLException ex) {
     System.out.println(ex.getMessage());
     }
     return c;
     }

     public void Buscar(clientes cli) {
     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }
    
     */
}
