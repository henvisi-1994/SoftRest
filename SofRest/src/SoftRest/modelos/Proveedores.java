package SoftRest.controladores;

/**
 *
 * @author Paul Torres
 */
public class Proveedores {
    protected String ruc;
    protected String nombre; 
    protected String telefono;
    protected String email;
    protected String direccion;
    
    public Proveedores(String ruc, String nombre, String telefono, String email, String direccion) {
        this.ruc = ruc;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        }
    public Proveedores(){
        
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String cedula) {
        this.ruc = ruc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String ImprimirProveedor() {
        return "[ " + ruc + "\t" + nombre + "\t" + email 
                + "\t" + direccion + "\t"+ telefono ;
    }    
}
