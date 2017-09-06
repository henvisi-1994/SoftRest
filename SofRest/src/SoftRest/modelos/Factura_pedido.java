/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftRest.modelos;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Berty
 */

public class Factura_pedido {
    //datos de factura 
    public int fac_codigo;
    public String fac_numero;
    public Date fac_fecha;  
    //cliente
    public int cli_codigo;
    public String cliente;
    public String cli_ruc;
    public String cli_direccion;
    public String cli_telefono;
    public String cli_ciudad;
    public String cli_email;
    //idpedido
    public int idPedido;    
    //datos calculados de factura
    public double fac_subtotal;
    public double fac_subcero;
    public double fac_subiva;
    public double fac_total;
    public double fac_descuento;
    public boolean fac_estado;   
    //productos de detalle
    public ArrayList<Fac_ped_detalle> items;   
    
    public Factura_pedido() {
        items=new ArrayList<Fac_ped_detalle>();
    }  

    public Factura_pedido(int fac_codigo, String fac_numero, Date fac_fecha, 
            int cli_codigo, int ped_codigo, double fac_subtotal, double fac_subcero, 
            double fac_subiva, double fac_total, double fac_descuento, boolean fac_estado) {
        this.fac_codigo = fac_codigo;
        this.fac_numero = fac_numero;
        this.fac_fecha = fac_fecha;
        this.cli_codigo = cli_codigo;
        this.idPedido = ped_codigo;
        this.fac_subtotal = fac_subtotal;
        this.fac_subcero = fac_subcero;
        this.fac_subiva = fac_subiva;
        this.fac_total = fac_total;
        this.fac_descuento = fac_descuento;
        this.fac_estado = fac_estado;
        items=new ArrayList<Fac_ped_detalle>();
    }
    
    
   
    
}
