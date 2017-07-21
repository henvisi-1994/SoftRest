/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftRest.modelos;

/**
 *
 * @author Usuario
 */
public class Empleados {
    private String cedula;
    private String nombre;
    private String direcion;
    private String telefono;
    public Empleados()
    {
        
    }
    public Empleados(String cedula, String nombre, String direcion, String telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.direcion = direcion;
        this.telefono = telefono;
    }
    
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirecion() {
        return direcion;
    }

    public void setDirecion(String direcion) {
        this.direcion = direcion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}
