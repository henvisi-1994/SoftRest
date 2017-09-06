package SoftRest.controladores;

import SoftRest.modelos.ConexionBD;
import SoftRest.modelos.Pedido;
import SoftRest.modelos.Plato;
import SoftRest.modelos.TipoPlato;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class cPedido {

    //arreglo de objetos
    ArrayList<Plato> Lista;

    //tabla de datos
    DefaultTableModel datos;
    public String[] columnNames = {"Id", "Tipo", "Cliente"};

    //retorna el número de filas
    public int Count() {
        return datos.getRowCount();
    }

    //Constructor
    public cPedido() {
        Lista = new ArrayList<Plato>();
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

    //obtener un producto
    public Plato get_Plato(int pos) {
        return (Plato) Lista.get(pos);
    }

    //agrega la nueva fila al modelo de tabla   
    public void addFila(int id, String nom, int cant, String tipo) {
        Object[] row = {new Integer(id), nom, new Integer(cant),new String(tipo)};
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
    public void insertar(Pedido ob) {
        String str = "INSERT INTO pedidos_cli(id_pedido, tipo_pedido, ced_cli)"
                + "VALUES (?, ?, ?)";
        //lista de parametros
        ArrayList param = new ArrayList();
        param.add(ob.getIdPedido());
        param.add(ob.getTipo_pedido());
        param.add(ob.getCedula_cli());

        System.out.print(str);
        //boolean estado=false;
        try {
            Plato ob1 = (Plato) Lista.get(Count() - 1); //extraer el último código generado 
            int cod = ob1.getPlato_id() + 1; //incrementa en 1 último código generado
            System.out.println("Ultimo codigo " + cod);
            //modifica la secuencia según codigo del último registro
            ConexionBD.EjecutarSql("ALTER SEQUENCE sec_idplato RESTART WITH " + cod);
            ConexionBD.Ejecutar_sql_parametro(str, param);

            System.out.print("inserto");
        } catch (Exception ex) {
            throw new RuntimeException("Error al insertar el nuevo registro");
        }
        //return estado;
    }

    //actualizar un registro en la base de datos
    public void actualizar(Pedido ob) {
        String str = "update pedidos_cli set tipo_pedido=?, ced_cli=?"
                + "where id_pedido=?";
        //lista de parametros
        ArrayList param = new ArrayList();
        param.add(ob.getTipo_pedido());
        param.add(ob.getCedula_cli());
        param.add(ob.getIdPedido());

        System.out.print(str);
        //boolean estado=false;
        try {
            ConexionBD.Ejecutar_sql_parametro(str, param);
            //estado=true;
            System.out.print("actualizacion exitosa");
        } catch (Exception ex) {
            throw new RuntimeException("Error al actualizar los datos ");
        }
        //return estado;
    }

  

    //rellena el modelo de table seg�n los resultados obtenidos de la BD   
    public void rellenar(ResultSet rs) {
        try {
            int id, cat, fab;
            String cod, nom, desc, cate, fabr;
            int can;
            boolean iva, estado;
            reset();  //limpia modelo de tabla
            Lista.clear(); //limpia la lista de productos
            while (rs.next()) {
                id = Integer.parseInt(rs.getObject("id_plato").toString());
                nom = rs.getObject("nombre_plato").toString();
                can = Integer.parseInt(rs.getObject("cant_plato").toString());
                //obtiene el nombre de la categoria
                cat = Integer.parseInt(rs.getObject("id_tipo_plato").toString());
                cTipoPlato liscat = new cTipoPlato();
                liscat.consultaAll();
                cate = liscat.get_Nombre(liscat.buscar_codigo("" + cat));
                addFila(id,nom, can,cate);
                Lista.add(new Plato(id,nom,can,cat));
                 System.out.println(id);
            }
            ConexionBD.CloseBD();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //consulta todos los elementos de la tabla productos
    public void consultaAll() {
        String str = "select * from view_plato ";
        ResultSet rs = null;
        try {
            rs = ConexionBD.Consulta(str);
            rellenar(rs);
            rs.close();
        } catch (Exception ex) {
        }
    }

    //consulta por nombre
    public cPedido buscar_nombre(String nom) {
        cPedido ob = null;
        String str = "select * from plato where nombre_plato like '%"
                + nom + "%' order by id_plato";
        System.out.println("" + str);
        ResultSet rs = null;
        try {
            rs = ConexionBD.Consulta(str);
            ob = new cPedido();
            ob.rellenar(rs);
            System.out.println("relleno");
            rs.close();
        } catch (Exception ex) {
        }
        return ob;
    }
 public cPedido buscar_varios(String nom)
    {
        cPedido plat=null;
        String str="select * from view_plato where nombre_plato ilike '%"
                + nom + "%'" + "order by id_plato";
        System.out.println(""+str);
        ResultSet rs = null;
        try{
            rs=ConexionBD.Consulta(str);
            plat=new cPedido();
            plat.rellenar(rs);                
            rs.close();
        }
        catch(Exception ex){throw new RuntimeException("Error de conexión con el servidor de datos");}
        return plat;
    }
    //consulta por codigo
    public cPedido buscar_codigo(String codigo) {
        cPedido ob = null;
        String str = "select * from plato where id_plato ="
                + codigo ;
        System.out.println("" + str);
        ResultSet rs = null;
        try {
            rs = ConexionBD.Consulta(str);
            ob = new cPedido();
            ob.rellenar(rs);
            System.out.println("relleno");
            rs.close();
        } catch (Exception ex) {
        }
        return ob;
    }

    //consulta por id en base de datos
    public Plato buscar_id_bd(String id) {
        cPedido ob = null;
        String str = "select * from plato where id_plato =" + id;
        System.out.println("" + str);
        ResultSet rs = null;
        try {
            rs = ConexionBD.Consulta(str);
            ob = new cPedido();
            ob.rellenar(rs);
            System.out.println("relleno");
            rs.close();
        } catch (Exception ex) {
        }
        return ob.Count() > 0 ? ob.get_Plato(0) : null;
    }

    //buscar por id
    public int buscar_id(int id) {
        for (int i = 0; i < Count(); i++) {
            if (get_Plato(i).getPlato_id() == id) {
                return i;
            }
        }
        return -1;
    }

    public TipoPlato buscar_id_tipoPlato(String codigo) {
         cTipoPlato ob = null;
        String str = "select id_plato from plato where id_plato =" + codigo;
        System.out.println("" + str);
        ResultSet rs = null;
        try {
            rs = ConexionBD.Consulta(str);
            ob = new cTipoPlato();
            ob.rellenar(rs);
            System.out.println("relleno");
            rs.close();
        } catch (Exception ex) {
        }
        return ob.Count() > 0 ? ob.get_TipoPlato(0) : null;
    }
}
