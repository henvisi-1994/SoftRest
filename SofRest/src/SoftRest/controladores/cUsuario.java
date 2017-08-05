package SoftRest.controladores;

import SoftRest.modelos.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import SoftRest.modelos.Cargo;
import SoftRest.modelos.Usuario;
import SoftRest.vistas.FrmMenuPrincipal;
import javax.swing.JOptionPane;
import org.apache.commons.codec.digest.DigestUtils;

public class cUsuario {

    DefaultTableModel datos;

    //etiquetas de tabla
    public String[] columnNames = {"Código", "Usuario"};

    //Constructor
    public cUsuario() {
        datos = new DefaultTableModel();
        for (int i = 0; i < columnNames.length; i++) {
            datos.addColumn(columnNames[i]);
        }
    }

    //metodo que retorna el numero de filas
    public int Count() {
        return datos.getRowCount();
    }

    //Metodos que retornan valores de una celda segun campos individuales
    public String get_Codigo(int pos) {
        return datos.getValueAt(pos, 0).toString();
    }

    public String get_Nombre(int pos) {
        return datos.getValueAt(pos, 1).toString();
    }

    //agrega la nueva fila
    public void addFila(int cod, String nombre) {
        Object[] row = {new Integer(cod), nombre};
        datos.addRow(row);
    }

    //limpia todos los datos del Modelo de tabla
    public void reset() {
        while (Count() > 0) {
            datos.removeRow(Count() - 1);
        }
    }

    //Retorna el modelo de tabla
    public DefaultTableModel getTablaDatos() {
        return datos;
    }

    /**
     * ******Metodos de acceso a la base de datos
     */
    //inserta un registro en la base de datos
    public void insertar(Usuario ob) {
        String str = "insert into usuario(nombre_usuario,contrasenia_usuario) values(?,?)";
        //lista de parametros
        ArrayList param = new ArrayList();
        param.add(ob.getNombre_usuario());
        param.add(ob.getContrasenia_usuario());

        System.out.print(str);
        //boolean estado=false;
        try {
            int cod = Integer.parseInt(get_Codigo(Count() - 1)) + 1; //extraer el último código generado
            System.out.print("Ultimo codigo " + cod);
            //modifica secuencia según codigo del último registro
            ConexionBD.EjecutarSql("ALTER SEQUENCE sec_idusuario RESTART WITH " + cod);
            ConexionBD.Ejecutar_sql_parametro(str, param);
            //estado=true;
            System.out.print("inserto");
        } catch (Exception ex) {
            throw new RuntimeException("Error al insertar el nuevo registro");
        }
        //return estado;
    }

    //actualizar un registro en la base de datos
    public void actualizar(Usuario ob) {
        String str = "update usuario set nombre_usuario=? contrasenia_usuario where id_usuario=?";
        //lista de parametros
        ArrayList param = new ArrayList();
        param.add(ob.getNombre_usuario());
        param.add(ob.getContrasenia_usuario());
        param.add(ob.getId_usuario());

        System.out.print(str);
        //boolean estado=false;
        try {
            ConexionBD.Ejecutar_sql_parametro(str, param);
            //estado=true;
            System.out.print("actualizacion exitosa");
        } catch (Exception ex) {
            throw new RuntimeException("Error al actualizar los datos ");
            //throw new RuntimeException(ex.getMessage());

        }
        //return estado;
    }

    //actualizar un registro en la base de datos
    public void eliminar(int cod) {
        String str = "delete from usuario where id_usuario=?";
        //lista de parametros
        ArrayList param = new ArrayList();
        param.add(cod);

        System.out.print(str);
        //boolean estado=false;
        try {
            ConexionBD.Ejecutar_sql_parametro(str, param);
            //estado=true;
            System.out.print("eliminación exitosa");
        } catch (Exception ex) {
            throw new RuntimeException("Error: No se puede eliminar el registro,"
                    + " existen dependencias en producto");
            //throw new RuntimeException(ex.getMessage());
        }
        //return estado;
    }

    //carga datos en el modelo de tabla
    public void rellenar(ResultSet rs) {
        try {
            int cod;
            String nom;
            reset();  //limpia la lista de productos
            while (rs.next()) {
                cod = Integer.parseInt(rs.getObject("id_usuario").toString());
                nom = rs.getObject("nombre_usuario").toString();
                addFila(cod, nom); //añade una fila al modelo
                System.out.println(nom);
            }
            System.out.println("Aqui  1.2.1");
            ConexionBD.CloseBD();
        } catch (Exception ex) {//throw new RuntimeException("Error de visualización de datos");            
        }
    }

    //consulta todos los elementos de la tabla productos
    public void consultaAll() {
        String str = "select * from usuario order by id_usuario";
        ResultSet rs = null;
        try {
            System.out.println("Aqui  1.1");
            rs = ConexionBD.Consulta(str);
            System.out.println("Aqui  1.2");
            rellenar(rs);
            System.out.println("Aqui  1.3");
            rs.close();
        } catch (Exception ex) {
            throw new RuntimeException("Error de conexión con el servidor de datos");
        }
    }

    //consulta los elementos segun la descripci�n
    public cUsuario buscar_varios(String nom) {
        cUsuario cat = null;
        String str = "select * from usuario where nombre_usuario ilike '%"
                + nom + "%'" + "order by id_usuario";
        System.out.println("" + str);
        ResultSet rs = null;
        try {
            rs = ConexionBD.Consulta(str);
            cat = new cUsuario();
            cat.rellenar(rs);
            rs.close();
        } catch (Exception ex) {
            throw new RuntimeException("Error de conexión con el servidor de datos");
        }
        return cat;
    }

    //buscar por código
    public int buscar_codigo(String cod) {
        for (int i = 0; i < Count(); i++) {
            if (get_Codigo(i).equals(cod)) {
                return i;
            }
        }
        return -1;
    }

    //buscar por nombre
    public int buscar_nombre(String nom) {
        for (int i = 0; i < Count(); i++) {
            if (get_Nombre(i).equalsIgnoreCase(nom)) {
                return i;
            }
        }
        return -1;
    }

    public boolean Login(String nombre, String contrasena) {

        String str = "select * from usuario where nombre_usuario ilike '%"
                + nombre + "%'" + "and contrasenia_usuario ilike '%"
                + contrasena + "%'";
        System.out.println("Paso 2" + str);
        ResultSet rs = null;
        boolean correcto = false;
        try {
            rs = ConexionBD.Consulta(str);
            while (rs.next()) {
            String nom = rs.getObject("nombre_usuario").toString();
            String contrs = rs.getObject("contrasenia_usuario").toString();
            if (nombre.equals(nom)&& contrasena.equals(contrs)) {
             correcto= true;
            }else
            {
                correcto= false;
            }
            System.out.println("Paso 3"+correcto);
            }
             ConexionBD.CloseBD();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return correcto;

    }
}
