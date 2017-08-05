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
public class Cargo {
    private int codigo_cargo;
    private String nombre_cargo;   

    public Cargo() {
    }

    public Cargo(int codigo_cargo, String nombre_cargo) {
        this.codigo_cargo = codigo_cargo;
        this.nombre_cargo = nombre_cargo;
    }

    public int getCodigo_cargo() {
        return codigo_cargo;
    }

    public void setCodigo_cargo(int codigo_cargo) {
        this.codigo_cargo = codigo_cargo;
    }

    public String getNombre_cargo() {
        return nombre_cargo;
    }

    public void setNombre_cargo(String nombre_cargo) {
        this.nombre_cargo = nombre_cargo;
    }

   
    
}
