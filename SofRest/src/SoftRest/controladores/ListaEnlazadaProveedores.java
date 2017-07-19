package SoftRest.controladores;
import SoftRest.vistas.frmClientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Paul Torres
 */
public class ListaEnlazadaProveedores {
    
    public Nodo<Cliente> cab;
    public ConectorBD con = new ConectorBD();
    public Connection cn = con.conexion();
   
    
    public ListaEnlazadaProveedores(){
        this.cab = null;
    }
    public boolean estaVacia(){
        boolean vacia=false;
        if (cab == null){
            vacia = true;
        }
        return vacia;
    }
    public ListaEnlazadaProveedores InsertarInicio(Cliente x) {
        Nodo<Cliente> nuevo = new Nodo(x);
        if (estaVacia()) {
            cab = nuevo;
        } else {
            nuevo.sgte = cab;
            cab = nuevo;
        }
        return this;
    }
    public void Visualizar() {
        Nodo<Cliente> n;
        n = cab;
        while (n != null) {
            System.out.print(" [ " + n.info.Imprimir() + "] => \n");
            n = n.sgte;
        }
    }
    public Nodo buscarProveedor(String ced){
        Nodo<Cliente> indice;
        for(indice = cab; indice != null; indice = indice.sgte){
            if(indice.info.getCedula()==ced){
                return indice;
            }
        }
        return null;
    }
    public void VaciarLista(){
        Nodo<Cliente> indice;
        if(!estaVacia()){
            while (cab != null) {
                indice = cab;
                cab = cab.sgte;
                indice.sgte = null;
            }
        }
    }
    public void Cargar(){
        Cliente cli;
        String sql;
        //sql = sirve para seleccionar la tabla "clientes" en la base de datos "proyecto"
        sql = "SELECT * FROM clientes";
        Statement st;
        
        try{
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            //bucle para asignar datos a la clase Cliente
            while(rs.next()){//pasa a la siguiente fila hata que ya no haya datos en la tabla Clientes
                cli = new Cliente();
                
                cli.setCedula(rs.getString(1));//se extrae de la base de dato la columnna 1 y se asigna a la cedula de la Clase ClientP
                cli.setNombre(rs.getString(2));//se extrae de la base de dato la columnna 2 y se asigna a el nombre de la Clase ClientP
                cli.setApellido(rs.getString(3));//se extrae de la base de dato la columnna 3 y se asigna el apellido de la Clase ClientP
                cli.setDireccion(rs.getString(4));//se extrae de la base de dato la columnna 4 y se asigna a la direccion de la Clase ClientP
                cli.setEmail(rs.getString(5));//se extrae de la base de dato la columnna 5 y se asigna el email de la Clase ClientP
                cli.setTelefono(rs.getString(6));//se extrae de la base de dato la columnna 6 y se asigna el telefono de la Clase ClientP
                InsertarInicio(cli);//se insertan en la lista enlazada
            }
        }catch(SQLException ex){
            Logger.getLogger(frmClientes.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "no se a podido cargar la base de datos");
        }
    }
    public void Guardar(){
        try{
            //"cli_..." son los nombres de los parametros de la tabla de la base de datos
            PreparedStatement pps = cn.prepareStatement("INSERT INTO clientes(cli_ced, cli_nom, cli_ape, cli_dir, cli_ema, cli_tel) VALUES(?,?,?,?,?,?)");
            pps.setString(1, cab.info.getCedula());
            pps.setString(2, cab.info.getNombre());
            pps.setString(3, cab.info.getApellido());
            pps.setString(4, cab.info.getDireccion());
            pps.setString(5, cab.info.getEmail());
            pps.setString(6, cab.info.getTelefono());
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "CLIENTE GUARDADO");
        }catch (SQLException ex) {
            Logger.getLogger(frmClientes.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "error al guardar\nComuniquese con el programador.");
        }
    }
}
