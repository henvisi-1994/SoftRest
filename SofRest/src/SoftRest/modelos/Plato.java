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
public class Plato {

    private int plato_id;
    private String nombre;
    private int cantidad;
    private int tipoplato_id;
    private String tipoplato_nom;

    public Plato(int plato_id, String nombre, int cantidad, int tipoplato_id) {
        this.plato_id = plato_id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipoplato_id = tipoplato_id;
    }

    public Plato(int plato_id, String nombre, int cantidad, String tipoplato_nom) {
        this.plato_id = plato_id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipoplato_nom = tipoplato_nom;
    }
    

    public Plato() {
    }

    public int getPlato_id() {
        return plato_id;
    }

    public void setPlato_id(int plato_id) {
        this.plato_id = plato_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getTipoplato_id() {
        return tipoplato_id;
    }

    public void setTipoplato_id(int tipoplato_id) {
        this.tipoplato_id = tipoplato_id;
    }

}
