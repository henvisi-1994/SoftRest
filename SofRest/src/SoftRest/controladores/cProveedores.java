/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftRest.controladores;

import SoftRest.modelos.ConexionBD;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import SoftRest.modelos.Proveedores;
import static SoftRest.vistas.Validaciones.cedula;
import java.sql.Connection;

/**
 *
 * @author Paul Torres
 */
public class cProveedores {

    //arreglo de objetos

    ArrayList<Proveedores> Lista;

    //tabla de datos
    DefaultTableModel datos;
    public String[] columnNames = {"Ruc - Cedula", "Nombre", "Direccion", "Telefono", "# Local Abastecido"};

    //retorna el número de filas

    public int Count() {
        return datos.getRowCount();
    }

    //Constructor
    public cProveedores() {
        Lista = new ArrayList<Proveedores>();
        datos = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            /*
             @Override
             public Class<?> getColumnClass(int columnIndex) {
             if(columnIndex == 7|| (columnIndex == 8)){
             return Boolean.class;
             }
             return super.getColumnClass(columnIndex);
             }*/
        };
        for (int i = 0; i < columnNames.length; i++) {
            datos.addColumn(columnNames[i]);
        }
    }

    //obtener un producto
    public Proveedores get_Proveedores(int pos) {
        return (Proveedores) Lista.get(pos);
    }

    //Metodos que retornan valores de una celda segun campos individuales
    public String get_Ruc(int pos) {
        return datos.getValueAt(pos, 0).toString();
    }

    public String get_Nombre(int pos) {
        return datos.getValueAt(pos, 1).toString();
    }

    //agrega la nueva fila al modelo de tabla
    public void addFila(String ruc, String nom, String direccion, String telefono, String id_local) {
        Object[] row = {new Proveedores(ruc, nom, direccion, telefono,new Integer(id_local))};
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
    public String insertar(Proveedores ob) {
        cProveedores lis = new cProveedores();
        ob.setRuc(lis.insertar((Proveedores) ob));
        String str = "insert into proveedores("
                + "ruc_prov, nombre_prov, dir_prov, telf_prov, id_local) " + "values(?,?,?,?,?,?)";
        //lista de parametros
        ArrayList param = new ArrayList();
        param.add(ob.getRuc());
        param.add(ob.getNombre());
        param.add(ob.getDireccion());
        param.add(ob.getTelefono());
        param.add(ob.getId_local());        
        System.out.println(str);
        try {
            ConexionBD.Ejecutar_sql_parametro(str, param);
            System.out.println("inserto");
            return ob.getRuc();
        } catch (Exception ex) {
            throw new RuntimeException("Error al insertar el nuevo registro");
        }
    }

    //actualizar un registro en la base de datos
    public void actualizar(Proveedores ob) {
        cProveedores lis = new cProveedores();
        lis.actualizar((Proveedores) ob);
        String str = "update proveedores SET  nombre_prov=?, dir_prov=?, telf_prov=?, id_local=? "
                + "where ruc_prov=?";
        //lista de parametros
        ArrayList param = new ArrayList();
        param.add(ob.getNombre());
        param.add(ob.getDireccion());
        param.add(ob.getTelefono());        
        param.add(ob.getRuc());
        param.add(ob.getId_local());
        System.out.print(str);
        try {
            ConexionBD.Ejecutar_sql_parametro(str, param);
            System.out.print("actualizacion exitosa");
        } catch (Exception ex) {
            throw new RuntimeException("Error al actualizar los datos ");
        }
        //return estado;
    }

    //eliminar un registro en la base de datos
    public void eliminar(String ruc) {
        String str = "delete from proveedores where ruc_prov=?";
        //lista de parametros
        ArrayList param = new ArrayList();
        param.add(ruc);

        System.out.print(str);
        //boolean estado=false;
        try {
            ConexionBD.Ejecutar_sql_parametro(str, param);
            //estado=true;
            System.out.print("eliminación exitosa");
        } catch (Exception ex) {
            throw new RuntimeException("Error: No se puede eliminar el registro,"
                    + " existen dependencias");
        }
        //return estado;
    }

    //rellena el modelo de table seg�n los resultados obtenidos de la BD   
    public void rellenar(ResultSet rs) {
        try {
            //int idloc;
            String cate,ruc,nom,dir,tel,idloc;
            Proveedores ob = new Proveedores();
            reset();  //limpia modelo de tabla
            Lista.clear(); //limpia la lista de productos
            while (rs.next()) {
                ruc = rs.getObject("ruc_prov").toString();
                nom = rs.getObject("nombre_prov").toString();
                dir = rs.getObject("dir_prov").toString();
                tel = rs.getObject("telf_prov").toString();
                //obtiene el id de locales
                idloc = rs.getObject("id_local").toString();
                cLocales liscat = new cLocales();
                liscat.consultaAll();
                cate = liscat.get_Codigo(liscat.buscar_codigo("" + idloc));
                
                addFila(ruc, nom, dir, tel,idloc);
                Lista.add(ob);
                System.out.println("Nombre: " + ob.getNombre());
            }
            ConexionBD.CloseBD();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //consulta todos los elementos de la tabla productos
    public void consultaAll() {
        String str = "select * from proveedores order by ruc_prov";
        ResultSet rs = null;
        try {
            rs = ConexionBD.Consulta(str);
            rellenar(rs);
            rs.close();
        } catch (Exception ex) {
        }
    }

    //consulta por codigo
    public cProveedores buscar_ruc_completo_bd(String ruc) {
        cProveedores ob = new cProveedores();
        String str = "select * from proveedores where ruc_prov = '" + ruc + "'";
        System.out.println("" + str);
        ResultSet rs = null;
        try {
            rs = ConexionBD.Consulta(str);
            ob.rellenar(rs);
            System.out.println("relleno");
            rs.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
        return ob;
    }

    //consulta por codigo
    public cProveedores buscar_nombre(String nom) {
        cProveedores ob = new cProveedores();
        String str = "select * from proveedores where nombre_prov like '%"
                + nom + "%' order by per_codigo";
        System.out.println("" + str);
        ResultSet rs = null;
        try {
            rs = ConexionBD.Consulta(str);
            ob.rellenar(rs);
            System.out.println("relleno");
            rs.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
        return ob;
    }

    //consulta por ruc
    public cProveedores buscar_ruc_bd(String ruc) {
        cProveedores ob = new cProveedores();
        String str = "select * from proveedores where ruc_prov like '%" + ruc + "%' order by ruc_prov";
        System.out.println("" + str);
        ResultSet rs = null;
        try {
            rs = ConexionBD.Consulta(str);
            ob.rellenar(rs);
            System.out.println("relleno");
            rs.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
        return ob;
    }

}
