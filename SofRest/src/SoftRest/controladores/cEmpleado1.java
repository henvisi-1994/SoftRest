package SoftRest.controladores;

import SoftRest.modelos.ConexionBD;
import SoftRest.modelos.Empleados;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class cEmpleado1 {

    //arreglo de objetos
    ArrayList<Empleados> Lista;

    //tabla de datos
    DefaultTableModel datos;
    public String[] columnNames = {"Cedula", "Nombre", "Direccion", "Telefono", "Fecha_Ingreso","Cargo", "Local" };

    //retorna el número de filas
    public int Count() {
        return datos.getRowCount();
    }

    //Constructor
    public cEmpleado1() {
        Lista = new ArrayList<Empleados>();
        datos = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //return (column == 7) || (column == 8);
                //bloquea edición de columnas
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 6 || (columnIndex == 7)) {
                    return Boolean.class;
                }
                return super.getColumnClass(columnIndex);
            }
        };
        for (int i = 0; i < columnNames.length; i++) {
            datos.addColumn(columnNames[i]);
        }
    }

    //obtener un producto
    public Empleados get_Empleados(int pos) {
        return (Empleados) Lista.get(pos);
    }

    //agrega la nueva fila al modelo de tabla   
    public void addFila(String ced, String nom, String dir,String tel,String fec,String car,String loc) {
        Object[] row = { ced, nom, dir,tel,fec, new String(car),new String(loc)
        };
        datos.addRow(row);
    }
    
    public void addFilaTP(String ced, String nom, String dir,String tel,String fec,String car,String loc)
    {
        Object[] row={ced, nom, dir,tel,fec, new String(car),new String(loc)
        };
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
    public void insertar(Empleados ob) {
        String str = "INSERT INTO empleado(nombre_emp, dir_emp, telf_emp, fecha_ingreso_emp, id_cargo, id_local, ced_empleado)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        //lista de parametros
        ArrayList param = new ArrayList();
        param.add(ob.getNombre());
        param.add(ob.getDirecion());
        param.add(ob.getTelefono());
        param.add(ob.getFecha());
        param.add(ob.getCargo());
        param.add(ob.getLocal());
        param.add(ob.getCedula());
        System.out.print(str);
    }

    //actualizar un registro en la base de datos
    public void actualizar(Empleados ob) {
        String str = "update empleado set nombre_emp=?, dir_emp=?, telf_emp=?, fecha_ingreso_emp=?, id_cargo=?, id_local=? "
                + "where ced_empleado=?";
        //lista de parametros
        ArrayList param = new ArrayList();
        param.add(ob.getNombre());
        param.add(ob.getDirecion());
        param.add(ob.getTelefono());
        param.add(ob.getFecha());
        param.add(ob.getCargo());
        param.add(ob.getLocal());
        param.add(ob.getCedula());

        System.out.print(str);
        try {
            ConexionBD.Ejecutar_sql_parametro(str, param);
            System.out.print("actualizacion exitosa");
        } catch (Exception ex) {
            throw new RuntimeException("Error al actualizar los datos ");
        }
    }

    //eliminar un registro en la base de datos
    public void eliminar(int ced) {
        String str = "delete from empleado where ced_empleado=?";
        //lista de parametros
        ArrayList param = new ArrayList();
        param.add(ced);
        try {
            ConexionBD.Ejecutar_sql_parametro(str, param);
            System.out.print("eliminación exitosa");
        } catch (Exception ex) {
            throw new RuntimeException("Error: No se puede eliminar el registro,"
                    + " existen dependencias en producto");
        }
    }

    //rellena el modelo de table seg�n los resultados obtenidos de la BD   
    public void rellenar(ResultSet rs) {
        try {
            int  car, fab;
            String ced, nom, dir, tel, fec,cate,loca;
            int loc;
            reset();  //limpia modelo de tabla
            Lista.clear(); //limpia la lista de productos
            while (rs.next()) {
                ced = (rs.getObject("ced_empleado").toString());
                nom = rs.getObject("nombre_emp").toString();
                dir = rs.getObject("dir_emp").toString();
                tel = rs.getObject("telf_emp").toString();
                fec = rs.getObject("fecha_ingreso_emp").toString();
                //obtiene el nombre del cargo
                car = Integer.parseInt(rs.getObject("id_cargo").toString());
                cCargo liscat = new cCargo();
                liscat.consultaAll();
                cate = liscat.get_Codigo(liscat.buscar_codigo(""+car));
                //obtiene el nombre del local
                loc = Integer.parseInt(rs.getObject("id_local").toString());
                cLocales lisloc = new cLocales();
                lisloc.consultaAll();
                loca = lisloc.get_Codigo(lisloc.buscar_codigo(""+loc));
                addFila(ced, nom, dir, tel, fec, cate, loca);
                Lista.add(new Empleados(tel, nom, dir, tel, fec, car, loc));
                System.out.println(ced);
            }
            ConexionBD.CloseBD();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
//rellena el modelo de table seg�n los resultados obtenidos de la BD  con vistas
   public void rellenarV(ResultSet rs)
    {
        try{
            String nomcargo,dirlocal;
            String ced,nom,dir,tel,fec, desc;
            reset();  //limpia modelo de tabla
            Lista.clear(); //limpia la lista de productos
            while (rs.next()) {
                ced=rs.getObject("ced_empleado").toString();
                nom=rs.getObject("nombre_emp").toString();
                dir=rs.getObject("dir_emp").toString();
                tel=rs.getObject("telf_emp").toString();
                fec=rs.getObject("fecha_ingreso_emp").toString();              
                //obtiene el nombre del tipo de plato   
                nomcargo = rs.getObject("nombre_cargo").toString();
                //obtiene el nombre del tipo de plato   
                dirlocal =  rs.getObject("dir_loc").toString();
                addFilaTP(ced, nom, dir, tel, fec, nomcargo, dirlocal);
                Lista.add(new Empleados(ced, nom, dir, tel, fec, nomcargo, dirlocal));
                System.out.println(ced);
            }
            ConexionBD.CloseBD();
        }
        catch(Exception ex){System.out.println(ex.getMessage());}
    }
    //consulta todos los elementos de la tabla productos
    public void consultaAll() {
        String str = "select * from view_empleado ";
        ResultSet rs = null;
        try {
            rs = ConexionBD.Consulta(str);
            rellenarV(rs);
            rs.close();
        } catch (Exception ex) {
        }
    }

    //consulta por nombre
    public cEmpleado1 buscar_nombre(String nom) {
        cEmpleado1 ob = null;
        String str = "select * from plato where nombre_plato like '%"
                + nom + "%' order by id_plato";
        System.out.println("" + str);
        ResultSet rs = null;
        try {
            rs = ConexionBD.Consulta(str);
            ob = new cEmpleado1();
            ob.rellenar(rs);
            System.out.println("relleno");
            rs.close();
        } catch (Exception ex) {
        }
        return ob;
    }
 public cEmpleado1 buscar_varios(String nom)
    {
        cEmpleado1 plat=null;
        String str="select * from view_plato where nombre_plato ilike '%"
                + nom + "%'" + "order by id_plato";
        System.out.println(""+str);
        ResultSet rs = null;
        try{
            rs=ConexionBD.Consulta(str);
            plat=new cEmpleado1();
            plat.rellenarV(rs);                
            rs.close();
        }
        catch(Exception ex){throw new RuntimeException("Error de conexión con el servidor de datos");}
        return plat;
    }
    //consulta por codigo
    public cEmpleado1 buscar_codigo(String codigo) {
        cEmpleado1 ob = null;
        String str = "select * from plato where id_plato ="
                + codigo ;
        System.out.println("" + str);
        ResultSet rs = null;
        try {
            rs = ConexionBD.Consulta(str);
            ob = new cEmpleado1();
            ob.rellenar(rs);
            System.out.println("relleno");
            rs.close();
        } catch (Exception ex) {
        }
        return ob;
    }

    //consulta por id en base de datos
    public Empleados buscar_id_bd(String id) {
        cEmpleado1 ob = null;
        String str = "select * from producto where id_plato =" + id;
        System.out.println("" + str);
        ResultSet rs = null;
        try {
            rs = ConexionBD.Consulta(str);
            ob = new cEmpleado1();
            ob.rellenar(rs);
            System.out.println("relleno");
            rs.close();
        } catch (Exception ex) {
        }
        return ob.Count() > 0 ? ob.get_Empleados(0) : null;
    }

    //buscar por id
    public int buscar_id(String id) {
        for (int i = 0; i < Count(); i++) {
            if (get_Empleados(i).getCedula()== id) {
                return i;
            }
        }
        return -1;
    }
}
