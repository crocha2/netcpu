/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conMySql;

import clasesPrincipales.Salidas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CPU_SYS
 */
public class salidaMySql {
    
    public ArrayList<Salidas> ListSalidas() {
        ArrayList<Salidas> salida = new ArrayList();
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_salida, numero, fecha, empresa, ciudad, direccion, contacto, telefono, correo, equipo, modelo, serie, comentario, id_cli FROM salidas ORDER BY fecha DESC");
            while (rs.next()) {
                Salidas sal = new Salidas();
                sal.setId_salida(rs.getInt("id_salida"));
                sal.setNumero(rs.getString("numero"));
                sal.setFecha(rs.getString("fecha"));
                sal.setEmpresa(rs.getString("empresa"));
                sal.setCiudad(rs.getString("ciudad"));
                sal.setDireccion(rs.getString("direccion"));
                sal.setContacto(rs.getString("contacto"));
                sal.setTelefono(rs.getString("telefono"));
                sal.setCorreo(rs.getString("correo"));
                sal.setEquipo(rs.getString("equipo"));
                sal.setModelo(rs.getString("modelo"));
                sal.setSerie(rs.getString("serie"));
                sal.setComentario(rs.getString("comentario")); 
                sal.setId_cli(rs.getInt("id_cli"));
                salida.add(sal);
            }
            cn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en listado");
        }
        return salida;
    }
    
    public void insertarSalida(Salidas salida) {
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            PreparedStatement pst =  cn.prepareStatement("INSERT INTO salidas(numero, fecha, empresa, ciudad, direccion, contacto, telefono, correo, equipo, modelo, serie, comentario, id_cli) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, salida.getNumero());
            pst.setString(2, salida.getFecha());
            pst.setString(3, salida.getEmpresa());
            pst.setString(4, salida.getCiudad());
            pst.setString(5, salida.getDireccion());
            pst.setString(6, salida.getContacto());
            pst.setString(7, salida.getTelefono());
            pst.setString(8, salida.getCorreo());
            pst.setString(9, salida.getEquipo());
            pst.setString(10, salida.getModelo());
            pst.setString(11, salida.getSerie());
            pst.setString(12, salida.getComentario());
            pst.setInt(13, salida.getId_cli());
            pst.executeUpdate();
            cn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error al insertar");
        }
    }
    
    public void EditarSalida(Salidas salida) {
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            PreparedStatement pst = (PreparedStatement) cn.prepareStatement("UPDATE salidas SET fecha=?,empresa=?,ciudad=?,direccion=?,contacto=?,telefono=?,correo=?,equipo=?,modelo=?,serie=?,comentario=? WHERE id_cli = ?");
            pst.setString(1, salida.getFecha());
            pst.setString(2, salida.getEmpresa());
            pst.setString(3, salida.getCiudad());
            pst.setString(4, salida.getDireccion());
            pst.setString(5, salida.getContacto());
            pst.setString(6, salida.getTelefono());
            pst.setString(7, salida.getCorreo());
            pst.setString(8, salida.getEquipo());
            pst.setString(9, salida.getModelo());
            pst.setString(10, salida.getSerie());
            pst.setString(11, salida.getComentario());
            pst.setInt(12, salida.getId_cli());
         pst.executeUpdate();
         cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(salidaMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void EliminarSalida(Salidas sal) {
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            PreparedStatement pst = (PreparedStatement) cn.prepareStatement("DELETE FROM salidas WHERE id_cli = ?");
            pst.setInt(1, sal.getId_cli());
            pst.executeUpdate();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(salidaMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
