package SoftRest.modelos;

/**
 *
 * @author Paul Torres
 */
public class Proveedores {
    protected String ruc;
    protected String nombre; 
    protected String telefono;    
    protected String direccion;
    protected int id_local;

    public Proveedores() {
    }

    public Proveedores(String ruc, String nombre, String telefono, String direccion, int id_local) {
        this.ruc = ruc;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.id_local = id_local;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
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
    
    
    public String ImprimirProveedor() {
        return ruc + "\t" + nombre + "\t" + direccion + "\t"+ telefono + "\t"+id_local;
    }    
}
