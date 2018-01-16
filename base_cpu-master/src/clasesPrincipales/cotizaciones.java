/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesPrincipales;

/**
 *
 * @author JR
 */
public class cotizaciones {
    
    int id_coti;
    String tipo_coti;
    String fecha_coti;
    String numero;
    String cliente_coti;
    String contacto;
    String ciudad_coti;
    String pais_coti;
    String comentario_1;
    String sub_total;
    String fletes;
    String iva;
    String gran_total;
    String validez_oferta;
    String formato_pago;
    String tiempo_entrega;
    int id_cli;

    public cotizaciones() {
    }

    public cotizaciones(int id_coti, String tipo_coti, String fecha_coti, String numero, String cliente_coti, String contacto, String ciudad_coti, String pais_coti, String comentario_1, String sub_total, String fletes, String iva, String gran_total, String validez_oferta, String formato_pago, String tiempo_entrega, int id_cli) {
        this.id_coti = id_coti;
        this.tipo_coti = tipo_coti;
        this.fecha_coti = fecha_coti;
        this.numero = numero;
        this.cliente_coti = cliente_coti;
        this.contacto = contacto;
        this.ciudad_coti = ciudad_coti;
        this.pais_coti = pais_coti;
        this.comentario_1 = comentario_1;
        this.sub_total = sub_total;
        this.fletes = fletes;
        this.iva = iva;
        this.gran_total = gran_total;
        this.validez_oferta = validez_oferta;
        this.formato_pago = formato_pago;
        this.tiempo_entrega = tiempo_entrega;
        this.id_cli = id_cli;
    }

    public int getId_coti() {
        return id_coti;
    }

    public void setId_coti(int id_coti) {
        this.id_coti = id_coti;
    }

    public String getTipo_coti() {
        return tipo_coti;
    }

    public void setTipo_coti(String tipo_coti) {
        this.tipo_coti = tipo_coti;
    }

    public String getFecha_coti() {
        return fecha_coti;
    }

    public void setFecha_coti(String fecha_coti) {
        this.fecha_coti = fecha_coti;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCliente_coti() {
        return cliente_coti;
    }

    public void setCliente_coti(String cliente_coti) {
        this.cliente_coti = cliente_coti;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getCiudad_coti() {
        return ciudad_coti;
    }

    public void setCiudad_coti(String ciudad_coti) {
        this.ciudad_coti = ciudad_coti;
    }

    public String getPais_coti() {
        return pais_coti;
    }

    public void setPais_coti(String pais_coti) {
        this.pais_coti = pais_coti;
    }

    public String getComentario_1() {
        return comentario_1;
    }

    public void setComentario_1(String comentario_1) {
        this.comentario_1 = comentario_1;
    }

    public String getSub_total() {
        return sub_total;
    }

    public void setSub_total(String sub_total) {
        this.sub_total = sub_total;
    }

    public String getFletes() {
        return fletes;
    }

    public void setFletes(String fletes) {
        this.fletes = fletes;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public String getGran_total() {
        return gran_total;
    }

    public void setGran_total(String gran_total) {
        this.gran_total = gran_total;
    }

    public String getValidez_oferta() {
        return validez_oferta;
    }

    public void setValidez_oferta(String validez_oferta) {
        this.validez_oferta = validez_oferta;
    }

    public String getFormato_pago() {
        return formato_pago;
    }

    public void setFormato_pago(String formato_pago) {
        this.formato_pago = formato_pago;
    }

    public String getTiempo_entrega() {
        return tiempo_entrega;
    }

    public void setTiempo_entrega(String tiempo_entrega) {
        this.tiempo_entrega = tiempo_entrega;
    }

    public int getId_cli() {
        return id_cli;
    }

    public void setId_cli(int id_cli) {
        this.id_cli = id_cli;
    }

    

    
    
    

}
