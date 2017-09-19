/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conMySql;

import clasesPrincipales.Entradas;
import clasesPrincipales.Garantias;
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
public class garantiaMySql {

    
    public ArrayList<Garantias> ListGarantias() {
        ArrayList<Garantias> garantia = new ArrayList();
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_garantia, fecha_entrada, fecha_garantia, numero, rma, numero_caso, cliente, nit, serie_vieja, serie_nueva, primera_serie, estado, id_entra FROM garantias WHERE estado = 'PROCESO' ORDER BY fecha_entrada ASC");
            while (rs.next()) {
                Garantias gar = new Garantias();
                gar.setId_garantia(rs.getInt("id_garantia"));
                gar.setFecha_entrada(rs.getString("fecha_entrada"));
                gar.setFecha_garantia(rs.getString("fecha_garantia"));
                gar.setNumero(rs.getString("numero"));
                gar.setRma(rs.getString("rma"));
                gar.setCliente(rs.getString("cliente"));
                gar.setNit(rs.getString("nit"));
                gar.setSerie_vieja(rs.getString("serie_vieja"));
                gar.setSerie_nueva(rs.getString("serie_nueva"));
                gar.setPrimera_serie(rs.getString("primera_serie"));
                gar.setEstado(rs.getString("estado"));
                gar.setId_entra(rs.getInt("id_entra"));

                garantia.add(gar);
            }
            cn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en listar");
        }
        return garantia;
    }

    

    //Codigo para INSERTAR DATOS.........................................................
    public void insertarEntrada_Garantia(Garantias garantia) {
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            //PreparedStatement pst =  cn.prepareStatement("INSERT INTO garantias(fecha_entrada, cliente, nit, serie_vieja, primera_serie, estado, id_entra) VALUES (?,?,?,?,?,?,?)");
            PreparedStatement pst =  cn.prepareStatement("INSERT INTO garantias(fecha_entrada, fecha_garantia, numero, rma, numero_caso, cliente, nit, serie_vieja, serie_nueva, primera_serie, estado, id_entra) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
                              
            pst.setString(1, garantia.getFecha_entrada());
            pst.setString(2, garantia.getFecha_garantia());
            pst.setString(3, garantia.getNumero());
            pst.setString(4, garantia.getRma());
            pst.setString(5, garantia.getNumero_caso());
            pst.setString(6, garantia.getCliente());
            pst.setString(7, garantia.getNit());
            pst.setString(8, garantia.getSerie_vieja());
            pst.setString(9, garantia.getSerie_nueva());
            pst.setString(10, garantia.getPrimera_serie());
            pst.setString(11, garantia.getEstado());
            pst.setInt(12, garantia.getId_entra());
            
            pst.executeUpdate();
            cn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error al insertar registro");
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
            Logger.getLogger(garantiaMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    
    public void RevisionAProcesoEntrada(Entradas entrada) {
        try {
            //(FECHA, ELEMENTO, POTENCIA, MARCA, MODELO, SERIE, EMPRESA, NIT, PERSONA_REMITE, CIUDAD, DIRECCION, NOMBRE_CONTACTO, TELEFONO_CONTACTO, CORREO, MOTIVO, TARJETA_RED, PARRILLA, BASES_PLASTICAS, CONECTOR_ORIGI, GARANTIA, ESTADO_CARCASA, OBSERVACIONES)
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            PreparedStatement pst = (PreparedStatement) cn.prepareStatement("UPDATE entradas SET estado=? WHERE id_entra = ?");
            pst.setString(1, entrada.getEstado());
            pst.setInt(2, entrada.getId_entrada());
            
            pst.executeUpdate();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(garantiaMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void RevisionAProcesoGarantia(Garantias garantia) {
        try {
            //(FECHA, ELEMENTO, POTENCIA, MARCA, MODELO, SERIE, EMPRESA, NIT, PERSONA_REMITE, CIUDAD, DIRECCION, NOMBRE_CONTACTO, TELEFONO_CONTACTO, CORREO, MOTIVO, TARJETA_RED, PARRILLA, BASES_PLASTICAS, CONECTOR_ORIGI, GARANTIA, ESTADO_CARCASA, OBSERVACIONES)
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            PreparedStatement pst = (PreparedStatement) cn.prepareStatement("UPDATE garantias SET estado=? WHERE id_entra = ?");
            pst.setString(1, garantia.getEstado());
            pst.setInt(2, garantia.getId_entra());
            
            pst.executeUpdate();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(garantiaMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    
    public void EliminarEntrada(Entradas en) {
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://69.73.129.251:3306/cpusysc1_cpudb", "cpusysc1_root", "c8020123496");
            PreparedStatement pst = (PreparedStatement) cn.prepareStatement("DELETE FROM entradas "
                    + " WHERE numero=?");
            pst.setString(1, en.getNumero());
            pst.executeUpdate();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(garantiaMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
