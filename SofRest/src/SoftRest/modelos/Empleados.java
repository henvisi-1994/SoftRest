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
    private String fecha;
    private int cargo;
    private int local;
    private String carg;
    private String locl;
    public Empleados()
    {
        
    }

    public Empleados(String cedula, String nombre, String direcion, String telefono, String fecha, int cargo, int local) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.direcion = direcion;
        this.telefono = telefono;
        this.fecha = fecha;
        this.cargo = cargo;
        this.local = local;
    }

    public Empleados(String cedula, String nombre, String direcion, String telefono, String fecha, String carg, String locl) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.direcion = direcion;
        this.telefono = telefono;
        this.fecha = fecha;
        this.carg = carg;
        this.locl = locl;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCargo() {
        return cargo;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }

    public int getLocal() {
        return local;
    }

    public void setLocal(int local) {
        this.local = local;
    }

    public String getCarg() {
        return carg;
    }

    public void setCarg(String carg) {
        this.carg = carg;
    }

    public String getLocl() {
        return locl;
    }

    public void setLocl(String locl) {
        this.locl = locl;
    }
    
}
