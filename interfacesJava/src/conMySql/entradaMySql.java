/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conMySql;

import clasesPrincipales.Entradas;
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
public class entradaMySql {

    public ArrayList<Entradas> ListEntradas() {
        ArrayList<Entradas> entrada = new ArrayList();
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_entra,  numero, fecha, elemento, potencia, marca, modelo, serie, empresa, nit, persona_remite, ciudad, direccion, contacto, telefono, correo, motivo, parrilla, bases_plas, conector_ori, garantia, estado_car, observaciones, tarjeta FROM entradas ORDER BY fecha DESC");
            while (rs.next()) {
                Entradas en = new Entradas();
                en.setId_entrada(rs.getInt("id_entra"));
                en.setNumero(rs.getString("numero"));
                en.setFecha(rs.getString("fecha"));
                en.setElemento(rs.getString("elemento"));
                en.setPotencia(rs.getString("potencia"));
                en.setMarca(rs.getString("marca"));
                en.setModelo(rs.getString("modelo"));
                en.setSerie(rs.getString("serie"));
                en.setEmpresa(rs.getString("empresa"));
                en.setNit(rs.getString("nit"));
                en.setPersona_remite(rs.getString("persona_remite"));
                en.setCiudad(rs.getString("ciudad"));
                en.setDireccion(rs.getString("direccion"));
                en.setNombre_contacto(rs.getString("contacto"));
                en.setTelefono_contacto(rs.getString("telefono"));
                en.setCorreo(rs.getString("correo"));
                en.setMotivo(rs.getString("motivo"));
                en.setParrilla(rs.getString("parrilla"));
                en.setBases_plasticas(rs.getString("bases_plas"));
                en.setConector_origi(rs.getString("conector_ori"));
                en.setGarantia(rs.getString("garantia"));
                en.setEstado_carcasa(rs.getString("estado_car"));
                en.setObservaciones(rs.getString("observaciones"));
                en.setTarjeta_red(rs.getString("tarjeta"));
                entrada.add(en);
            }
            cn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en listado");
        }
        return entrada;
    }

    //Codigo para INSERTAR DATOS.........................................................
    public void insertarEntrada(Entradas entrada) {
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            PreparedStatement pst =  cn.prepareStatement("INSERT INTO entradas(numero, fecha, elemento, potencia, marca, modelo, serie, empresa, nit, persona_remite, ciudad, direccion, contacto, telefono, correo, motivo, parrilla, bases_plas, conector_ori, garantia, estado_car, observaciones, tarjeta) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, entrada.getNumero());
            pst.setString(2, entrada.getFecha());
            pst.setString(3, entrada.getElemento());
            pst.setString(4, entrada.getPotencia());
            pst.setString(5, entrada.getMarca());
            pst.setString(6, entrada.getModelo());
            pst.setString(7, entrada.getSerie());
            pst.setString(8, entrada.getEmpresa());
            pst.setString(9, entrada.getNit());
            pst.setString(10, entrada.getPersona_remite());
            pst.setString(11, entrada.getCiudad());
            pst.setString(12, entrada.getDireccion());
            pst.setString(13, entrada.getNombre_contacto());
            pst.setString(14, entrada.getTelefono_contacto());
            pst.setString(15, entrada.getCorreo());
            pst.setString(16, entrada.getMotivo());   
            pst.setString(17, entrada.getParrilla());
            pst.setString(18, entrada.getBases_plasticas());
            pst.setString(19, entrada.getConector_origi());
            pst.setString(20, entrada.getGarantia());
            pst.setString(21, entrada.getEstado_carcasa());
            pst.setString(22, entrada.getObservaciones());
            pst.setString(23, entrada.getTarjeta_red());
            pst.executeUpdate();
            cn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error al insertar");
        }
    }
    
    public void EditarEntrada(Entradas entrada) {
        try {
            //(FECHA, ELEMENTO, POTENCIA, MARCA, MODELO, SERIE, EMPRESA, NIT, PERSONA_REMITE, CIUDAD, DIRECCION, NOMBRE_CONTACTO, TELEFONO_CONTACTO, CORREO, MOTIVO, TARJETA_RED, PARRILLA, BASES_PLASTICAS, CONECTOR_ORIGI, GARANTIA, ESTADO_CARCASA, OBSERVACIONES)
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            PreparedStatement pst = (PreparedStatement) cn.prepareStatement("UPDATE entradas SET fecha=?,elemento=?,potencia=?,marca=?,modelo=?,serie=?,empresa=?,nit=?,persona_remite=?,ciudad=?,direccion=?,contacto=?,telefono=?,correo=?,motivo=?,parrilla=?,bases_plas=?,conector_ori=?,garantia=?,estado_car=?,observaciones=?,tarjeta=? WHERE numero = ?");
            pst.setString(1, entrada.getFecha());
            pst.setString(2, entrada.getElemento());
            pst.setString(3, entrada.getPotencia());
            pst.setString(4, entrada.getMarca());
            pst.setString(5, entrada.getModelo());
            pst.setString(6, entrada.getSerie());
            pst.setString(7, entrada.getEmpresa());
            pst.setString(8, entrada.getNit());
            pst.setString(9, entrada.getPersona_remite());
            pst.setString(10, entrada.getCiudad());
            pst.setString(11, entrada.getDireccion());
            pst.setString(12, entrada.getNombre_contacto());
            pst.setString(13, entrada.getTelefono_contacto());
            pst.setString(14, entrada.getCorreo());
            pst.setString(15, entrada.getMotivo());
            pst.setString(16, entrada.getParrilla());
            pst.setString(17, entrada.getBases_plasticas());
            pst.setString(18, entrada.getConector_origi());
            pst.setString(19, entrada.getGarantia());
            pst.setString(20, entrada.getEstado_carcasa());
            pst.setString(21, entrada.getObservaciones());
            pst.setString(22, entrada.getTarjeta_red());
            pst.setString(23, entrada.getNumero());
            pst.executeUpdate();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(entradaMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void EliminarEntrada(Entradas en) {
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            PreparedStatement pst = (PreparedStatement) cn.prepareStatement("DELETE FROM entradas "
                    + " WHERE numero=?");
            pst.setString(1, en.getNumero());
            pst.executeUpdate();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(entradaMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
