package SoftRest.controladores;

import SoftRest.vistas.frmClientes;
import SoftRest.vistas.frmProveedores;
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

    public NodoProveedores<Proveedores> cab;
    public ConectorBD con = new ConectorBD();
    public Connection cn = con.conexion();

    public ListaEnlazadaProveedores() {
        this.cab = null;
    }

    public boolean estaVacia() {
        boolean vacia = false;
        if (cab == null) {
            vacia = true;
        }
        return vacia;
    }

    public ListaEnlazadaProveedores InsertarInicio(Proveedores x) {
        NodoProveedores<Proveedores> nuevo = new NodoProveedores(x);
        if (estaVacia()) {
            cab = nuevo;
        } else {
            nuevo.sgte = cab;
            cab = nuevo;
        }
        return this;
    }

    public void Visualizar() {
        NodoProveedores<Proveedores> n;
        n = cab;
        while (n != null) {
            System.out.print(" [ " + n.info.ImprimirProveedor() + "] => \n");
            n = n.sgte;
        }
    }

    public NodoProveedores buscarProveedor(String ruc) {
        NodoProveedores<Proveedores> indice;
        for (indice = cab; indice != null; indice = indice.sgte) {
            if (indice.info.getRuc() == ruc) {
                return indice;
            }
        }
        return null;
    }

    public void VaciarLista() {
        NodoProveedores<Proveedores> indice;
        if (!estaVacia()) {
            while (cab != null) {
                indice = cab;
                cab = cab.sgte;
                indice.sgte = null;
            }
        }
    }

    public void Cargar() {
        Proveedores prov;
        String sql;
        //sql = sirve para seleccionar la tabla "clientes" en la base de datos "proyecto"
        sql = "SELECT * FROM proveedores";
        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            //bucle para asignar datos a la clase Cliente
            while (rs.next()) {//pasa a la siguiente fila hata que ya no haya datos en la tabla Clientes
                prov = new Proveedores();

                prov.setRuc(rs.getString(1));//se extrae de la base de dato la columnna 1 y se asigna a la cedula de la Clase ClientP
                prov.setNombre(rs.getString(2));//se extrae de la base de dato la columnna 2 y se asigna a el nombre de la Clase ClientP
                prov.setDireccion(rs.getString(4));//se extrae de la base de dato la columnna 4 y se asigna a la direccion de la Clase ClientP
                prov.setEmail(rs.getString(5));//se extrae de la base de dato la columnna 5 y se asigna el email de la Clase ClientP
                prov.setTelefono(rs.getString(6));//se extrae de la base de dato la columnna 6 y se asigna el telefono de la Clase ClientP
                InsertarInicio(prov);//se insertan en la lista enlazada
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmProveedores.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "no se a podido cargar la base de datos");
        }
    }

    public void Guardar() {
        try {
            //"prov_..." son los nombres de los parametros de la tabla de la base de datos
            PreparedStatement pps = cn.prepareStatement("INSERT INTO proveedores(prov_ruc, prov_nom, prov_dir, prov_ema, prov_tel) VALUES(?,?,?,?,?)");
            pps.setString(1, cab.info.getRuc());
            pps.setString(2, cab.info.getNombre());
            pps.setString(3, cab.info.getDireccion());
            pps.setString(4, cab.info.getEmail());
            pps.setString(5, cab.info.getTelefono());
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "PROVEEDOR GUARDADO");
        } catch (SQLException ex) {
            Logger.getLogger(frmProveedores.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "error al guardar\nComuniquese con el programador.");
        }
    }
}
