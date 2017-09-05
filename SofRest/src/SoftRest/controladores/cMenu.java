package SoftRest.controladores;

import SoftRest.modelos.ConexionBD;
import SoftRest.modelos.Menu;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class cMenu {

    //arreglo de objetos
    ArrayList<Menu> Lista;

    //tabla de datos
    DefaultTableModel datos;
    public String[] columnNames = {"Codigo","Platos", "Fecha", "Direccion"
    };

    //retorna el número de filas
    public int Count() {
        return datos.getRowCount();
    }

    //Constructor
    public cMenu() {
        Lista = new ArrayList<Menu>();
        datos = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //return (column == 7) || (column == 8);
                //bloquea edición de columnas
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 7 || (columnIndex == 8)) {
                    return Boolean.class;
                }
                return super.getColumnClass(columnIndex);
            }
        };
        for (int i = 0; i < columnNames.length; i++) {
            datos.addColumn(columnNames[i]);
        }
    }
    
     //Metodos que retornan valores de una celda segun campos individuales
    public String get_Codigo(int pos){
        if (pos==-1|| pos <0) {
            return "0";
        } else {
             return datos.getValueAt(pos, 0).toString();
        }
    }

    //obtener un producto
    public Menu get_Menu(int pos) {
        return (Menu) Lista.get(pos);
    }
    
    //agrega la nueva fila al modelo de tabla   
    public void addFila( int cod,String nom, String fech, String dir) {
        Object[] row = {new Integer (cod),nom, (fech),(dir)};
        datos.addRow(row);
    }
    
    public void addFilaTP(int id, String nom, int cant,String tipPlato)
    {
        Object[] row={new Integer(id),nom,new Integer(cant),tipPlato};
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
    public void insertar(Menu ob) {
        String str = "INSERT INTO menu(num_menu,fecha_menu,id_plato,id_local)"
                + "VALUES (?, ?, ?,?)";
        //lista de parametros
        ArrayList param = new ArrayList();
        param.add(ob.getNum_menu());
        param.add(ob.getFecha());        
        param.add(ob.getId_plato());
        param.add(ob.getId_local());
        System.out.print(str);
        try {
            ConexionBD.Ejecutar_sql_parametro(str, param);
            System.out.print("inserto");
        } catch (Exception ex) {
            throw new RuntimeException("Error al insertar el nuevo registro");
        }
    }

    //actualizar un registro en la base de datos
    public void actualizar(Menu ob) {
        String str = "update menu set fecha_menu=?, id_plato=?, id_local=?"
                + "where num_menu=?";
        //lista de parametros
        ArrayList param = new ArrayList();
        param.add(ob.getNum_menu());
        param.add(ob.getFecha());
        param.add(ob.getId_plato());
        param.add(ob.getId_local());

        System.out.print(str);
        try {
            ConexionBD.Ejecutar_sql_parametro(str, param);
            System.out.print("actualizacion exitosa");
        } catch (Exception ex) {
            throw new RuntimeException("Error al actualizar los datos ");
        }
    }

    //actualizar un registro en la base de datos
    public void eliminar(int id) {
        String str = "delete from plato where num_menu=?";
        //lista de parametros
        ArrayList param = new ArrayList();
        param.add(id);
        System.out.print(str);
        try {
            ConexionBD.Ejecutar_sql_parametro(str, param);
            System.out.print("eliminación exitosa");
        } catch (Exception ex) {
            throw new RuntimeException("Error: No se puede eliminar el registro,"
                    + " existen dependencias en menu");
        }
    }

    //rellena el modelo de table seg�n los resultados obtenidos de la BD   
    public void rellenar(ResultSet rs) {
        try {
            int id;
            String nom,dir;
            Date fecha;
            reset();  //limpia modelo de tabla
            Lista.clear(); //limpia la lista de productos
            while (rs.next()) {
                id =Integer.parseInt(rs.getObject("num_menu").toString());
                nom = rs.getObject("nombre_plato").toString();
                fecha = rs.getDate("fecha_menu");
                dir = rs.getObject("dir_loc").toString();
                addFila(id,nom,fecha.toString(),dir);
                Lista.add(new Menu(nom,fecha,dir));

            }
            ConexionBD.CloseBD();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //consulta todos los elementos de la tabla productos
    public void consultaAll() {
        String str = "select * from view_menu ";
        ResultSet rs = null;
        try {
            rs = ConexionBD.Consulta(str);
            rellenar(rs);
            rs.close();
        } catch (Exception ex) {
        }
    }

    public cMenu buscar_varios(String nom)
    {
        cMenu plat=null;
        String str="select * from view_menu where nombre_plato ilike '%"
                + nom + "%'" + "order by id_plato";
        System.out.println(""+str);
        ResultSet rs = null;
        try{
            rs=ConexionBD.Consulta(str);
            plat=new cMenu();
            plat.rellenar(rs);                
            rs.close();
        }
        catch(Exception ex){throw new RuntimeException("Error de conexión con el servidor de datos");}
        return plat;
    }
    //consulta por codigo
    public cMenu buscar_codigo(String codigo) {
        cMenu ob = null;
        String str = "select * from menu where num_menu ="
                + codigo ;
        System.out.println("" + str);
        ResultSet rs = null;
        try {
            rs = ConexionBD.Consulta(str);
            ob = new cMenu();
            ob.rellenar(rs);
            System.out.println("relleno");
            rs.close();
        } catch (Exception ex) {
        }
        return ob;
    }

    //consulta por id en base de datos
    public Menu buscar_id_bd(String id) {
        cMenu ob = null;
        String str = "select * from menu where num_menu =" + id;
        System.out.println("" + str);
        ResultSet rs = null;
        try {
            rs = ConexionBD.Consulta(str);
            ob = new cMenu();
            ob.rellenar(rs);
            System.out.println("relleno");
            rs.close();
        } catch (Exception ex) {
        }
        return ob.Count() > 0 ? ob.get_Menu(0) : null;
    }

    //buscar por id
    public int buscar_id(String nom) {
        for (int i = 0; i < Count(); i++) {
            if (get_Menu(i).getNombreMenu().equals(nom) ) {
                return i;
            }
        }
        return -1;
    }
}
