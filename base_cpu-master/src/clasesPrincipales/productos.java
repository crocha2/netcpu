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
public class productos {
    
    int id_producto;
    String numero;
    String item;
    int cantidad;
    String descripcion;
    int valor_unitario;
    int valor_total;
    String estado;
    int id_cli;

    public productos() {
    }

    public productos(int id_producto, String numero, String item, int cantidad, String descripcion, int valor_unitario, int valor_total, String estado,int id_cli) {
        this.id_producto = id_producto;
        this.numero = numero;
        this.item = item;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.valor_unitario = valor_unitario;
        this.valor_total = valor_total;
        this.estado = estado;
        this.id_cli = id_cli;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(int valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    public double getValor_total() {
        return valor_total;
    }

    public void setValor_total(int valor_total) {
        this.valor_total = valor_total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public int getId_cli() {
        return id_cli;
    }

    public void setId_cli(int id_cli) {
        this.id_cli = id_cli;
    }
    
    
    
}
