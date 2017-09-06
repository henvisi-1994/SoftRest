/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftRest.modelos;

import java.sql.Date;

/**
 *
 * @author Berty
 */

public class Fac_ped_detalle {
    public int fac_codigo;
    public String fac_numero;
    public int idpedido;   
    public String pro_nombre;
    public String fab_nombre;
    public double fv_cantidad;
    public double fv_puv;
    public double fv_iva;
    public double fv_descuento;
    public boolean fv_estado;    
    
    public Fac_ped_detalle() {
        
    }  

    public Fac_ped_detalle(int fac_codigo, int idpedido, double fv_cantidad, double fv_puv, double fv_iva, double fv_descuento) {
        this.fac_codigo = fac_codigo;
        this.idpedido = idpedido;
        this.fv_cantidad = fv_cantidad;
        this.fv_puv = fv_puv;
        this.fv_iva = fv_iva;
        this.fv_descuento = fv_descuento;
        this.fv_estado = fv_estado;
    }   
}
