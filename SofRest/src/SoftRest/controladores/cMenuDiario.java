package SoftRest.controladores;

import SoftRest.modelos.ConexionBD;
import SoftRest.modelos.Menu;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class cMenuDiario {

    //arreglo de objetos
    ArrayList<Menu> Lista;

    //tabla de datos
    DefaultTableModel datos;
    public String[] columnNames = {"Codigo","Nombre del plato","Precio"};

    //retorna el número de filas
    public int Count() {
        return datos.getRowCount();
    }

    //Constructor
    public cMenuDiario() {
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
    public void addFila( int codigo,String nom, Double precio) {
        Object[] row = {new Integer (codigo),new String (nom),new Double(precio)};
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

  
    //rellena el modelo de table seg�n los resultados obtenidos de la BD   
    public void rellenar(ResultSet rs) {
        try {
            String nom;
            double precio;
            int cod;
            reset();  //limpia modelo de tabla
            Lista.clear(); //limpia la lista de productos
            while (rs.next()) {
                cod =Integer.parseInt(rs.getObject("num_menu").toString());
                nom = rs.getObject("nombre_plato").toString();
                precio = rs.getDouble("prec_tipo_plato");
                addFila(cod,nom,precio);
                Lista.add(new Menu(nom,precio));
            }
            ConexionBD.CloseBD();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //consulta todos los elementos de la tabla productos
    public void consultaAll() {
          String str = "SELECT  menu.num_menu,plato.nombre_plato,tipo_plato.prec_tipo_plato FROM menu,plato,tipo_plato\n" +
"WHERE \n" +
"  fecha_menu =current_date\n" +
"  AND  plato.id_plato = menu.id_plato \n" +
"  AND  tipo_plato.id_tipo_plato = plato.id_tipo_plato; ";
        ResultSet rs = null;
        try {
            rs = ConexionBD.Consulta(str);
            rellenar(rs);
            rs.close();
        } catch (Exception ex) {
        }
    }
 

    public cMenuDiario buscar_varios(String nom)
    {
        cMenuDiario plat=null;
        String str="SELECT menu.num_menu, plato.nombre_plato,tipo_plato.prec_tipo_plato FROM  menu, plato,tipo_plato\n" +
"WHERE \n" +
"  fecha_menu =current_date\n" +
"  AND  plato.id_plato = menu.id_plato \n" +
"  AND  tipo_plato.id_tipo_plato = plato.id_tipo_plato\n" +
"  AND  nombre_plato ilike '%"
                + nom + "%'";
        System.out.println(""+str);
        ResultSet rs = null;
        try{
            rs=ConexionBD.Consulta(str);
            plat=new cMenuDiario();
            plat.rellenar(rs);                
            rs.close();
        }
        catch(Exception ex){throw new RuntimeException("Error de conexión con el servidor de datos");}
        return plat;
    }
    //consulta por codigo
    public cMenuDiario buscar_codigo(String codigo) {
        cMenuDiario ob = null;
        String str = "select * from menu where num_menu ="
                + codigo ;
        System.out.println("" + str);
        ResultSet rs = null;
        try {
            rs = ConexionBD.Consulta(str);
            ob = new cMenuDiario();
            ob.rellenar(rs);
            System.out.println("relleno");
            rs.close();
        } catch (Exception ex) {
        }
        return ob;
    }

    //consulta por id en base de datos
    public Menu buscar_id_bd(String id) {
        cMenuDiario ob = null;
        String str = "select * from menu where num_menu =" + id;
        System.out.println("" + str);
        ResultSet rs = null;
        try {
            rs = ConexionBD.Consulta(str);
            ob = new cMenuDiario();
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
