/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftRest.modelos;

import java.sql.Date;

/**
 *
 * @author Paul Torres
 */
public class Menu {

   private String  nombreMenu;
   private Date fecha;
   private String direccion;
   private int num_menu;
   private double precio;
   private int id_local;
   private int id_plato;

    public Menu(String nombreMenu, Date fecha, String direccion) {
        this.nombreMenu = nombreMenu;
        this.fecha = fecha;
        this.direccion = direccion;
    }   

    public Menu(String nombreMenu, Date fecha, int id_local, int id_plato) {
        this.nombreMenu = nombreMenu;
        this.fecha = fecha;
        this.id_local = id_local;
        this.id_plato = id_plato;
    }   

    public Menu(String nombreMenu, double precio) {
        this.nombreMenu = nombreMenu;
        this.precio = precio;
    }
    public Menu() {
    }

    public String getNombreMenu() {
        return nombreMenu;
    }

    public void setNombreMenu(String nombreMenu) {
        this.nombreMenu = nombreMenu;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getId_local() {
        return id_local;
    }

    public void setId_local(int id_local) {
        this.id_local = id_local;
    }

    public int getId_plato() {
        return id_plato;
    }

    public void setId_plato(int id_plato) {
        this.id_plato = id_plato;
    }   

    public int getNum_menu() {
        return num_menu;
    }

    public void setNum_menu(int num_menu) {
        this.num_menu = num_menu;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

}
