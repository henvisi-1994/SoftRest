/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftRest.modelos;

/**
 *
 * @author henvisi
 */
public class TipoPlato {
    private int tipoplato_id;
    private String tipoplato_nombre;
    private  double precio;

    public TipoPlato() {
    }

    public TipoPlato(int tipoplato_id, String tipoplato_nombre,double precio) {
        this.tipoplato_id = tipoplato_id;
        this.tipoplato_nombre = tipoplato_nombre;
        this.precio = precio;
    }

    public int getTipoplato_id() {
        return tipoplato_id;
    }

    public void setTipoplato_id(int tipoplato_id) {
        this.tipoplato_id = tipoplato_id;
    }

    public String getTipoplato_nombre() {
        return tipoplato_nombre;
    }

    public void setTipoplato_nombre(String tipoplato_nombre) {
        this.tipoplato_nombre = tipoplato_nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
}
