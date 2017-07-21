package SoftRest.controladores;

/**
 *
 * @author Eddie Bustamante
 */
public class Cliente {

    protected String cedula;
    protected String nombre;
    protected String apellido;
    protected String telefono;
    protected String email;
    protected String direccion;

    public Cliente(String cedula, String nombre, String apellido, String telefono, String email, String direccion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }

    public Cliente() {

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public String Imprimir() {
        return "[ " + cedula + "\t" + nombre + "\t" + apellido 
                + "\t" + email + "\t" + direccion + "\t" + telefono;
    }
}
