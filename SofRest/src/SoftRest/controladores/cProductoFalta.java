package SoftRest.controladores;

import SoftRest.modelos.ConexionBD;
import SoftRest.modelos.Producto;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class cProductoFalta {
    
    //arreglo de objetos
    ArrayList<Producto> Lista;
    
    DefaultTableModel datos;
    
    //etiquetas de tabla
    public String[] columnNames = {"id_producto","nom_produc","unidad_medida_produc","cantidad_proc","precio_produc" +
                                   "id_categoria"};
    
    //metodo que retorna el numero de filas
    public int Count(){return datos.getRowCount();}
    //Constructor
    public cProductoFalta(){
        Lista = new ArrayList<Producto>();
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
    public Producto get_Producto(int pos) {
        return (Producto) Lista.get(pos);
    }
    //agrega la nueva fila al modelo de tabla   
    public void addFila(int id, String nom, String uni,int can,double pre,String cat) {
        Object[] row = {new Integer(id), nom,new String(uni), new Integer(can), new Double (pre), new String (cat)};
        datos.addRow(row);
    }
    public void addFilaTP(int id, String nom, String uni,int can,double pre,int idcate)
    {
        Object[] row={new Integer(id), nom,new String(uni), new Integer(can), new Double (pre), idcate};
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
    /********Metodos de acceso a la base de datos*/
    //inserta un registro en la base de datos
    public void insertar(Producto ob)
    {
        String str="insert into productos (id_producto,nom_produc,unidad_medida_produc,cantidad_proc,precio_produc,id_categoria) values(?,?,?,?,?,?)";
        //lista de parametros
        ArrayList param=new ArrayList();
        param.add(ob.getId_producto());
        param.add(ob.getNom_produc());
        param.add(ob.getUnidad_medida_produc());
        param.add(ob.getCantidad_proc());
        param.add(ob.getPrecio_produc());
        param.add(ob.getId_categoria());

        System.out.print(str);
        //boolean estado=false;
       try {
            Producto ob1 = (Producto) Lista.get(Count() - 1); //extraer el último código generado 
            int cod = ob1.getId_producto()+ 1; //incrementa en 1 último código generado
            System.out.println("Ultimo codigo " + cod);
            //modifica la secuencia según codigo del último registro
            ConexionBD.EjecutarSql("ALTER SEQUENCE sec_id_producto RESTART WITH " + cod);
            ConexionBD.Ejecutar_sql_parametro(str, param);

            System.out.print("inserto");
        } catch (Exception ex) {
            throw new RuntimeException("Error al insertar el nuevo registro");
        }
        //return estado;
    }

    //actualizar un registro en la base de datos
    public void actualizar(Producto ob) {
        String str = "update productos set nom_produc=?, unidad_medida_produc=?, cantidad_proc=?, precio_produc=?, id_categoria=? where id_producto=?";
        //lista de parametros
        ArrayList param = new ArrayList();
        param.add(ob.getNom_produc());
        param.add(ob.getUnidad_medida_produc());
        param.add(ob.getCantidad_proc());
        param.add(ob.getPrecio_produc());
        param.add(ob.getId_categoria());
        param.add(ob.getId_producto());
        
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
    
     //actualizar un registro en la base de datos
    public void eliminar(int cod)
    {
        String str="delete from productos where id_producto=?";
        //lista de parametros
        ArrayList param=new ArrayList();        
        param.add(cod);           

        System.out.print(str);
        //boolean estado=false;
        try{
            ConexionBD.Ejecutar_sql_parametro(str,param);
            //estado=true;
            System.out.print("eliminación exitosa");
        }
        catch(Exception ex){            
            throw new RuntimeException("Error: No se puede eliminar el registro,"
            +" existen dependencias en producto");            
            //throw new RuntimeException(ex.getMessage());
        }
        //return estado;
    } 

    //carga datos en el modelo de tabla
   public void rellenar(ResultSet rs) {
        try {
            double pre;
            int id, categor,cant;
            String nom,can,cate;
            reset();  //limpia modelo de tabla
            Lista.clear(); //limpia la lista de productos
            while (rs.next()) {
                id = Integer.parseInt(rs.getObject("id_producto").toString());
                nom = rs.getObject("nombre_produc").toString();
                can = rs.getObject("unidad_medida_produc").toString();
                cant = Integer.parseInt(rs.getObject("cantidad_produc").toString());
                pre = Double.parseDouble(rs.getObject("precio_produc").toString());
                //obtiene el nombre de la categoria
                categor = Integer.parseInt(rs.getObject("id_categoria").toString());
                cCategoria liscat = new cCategoria();
                liscat.consultaAll();
                cate = liscat.get_Nombre(liscat.buscar_codigo(" " + categor));
                addFila(id, nom, can, cant, pre, cate);
                Lista.add(new Producto(id, nom, can, cant, pre, categor));
                 System.out.println(id);
            }
            ConexionBD.CloseBD();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
   public void rellenarV(ResultSet rs)
    {
        try{
            int id, can;
            String cod, nom, desc, cate,fabr,nomTiPlato;
           
           String uni;
           double pre;
           int cant,idcate;
           boolean iva, estado;
            reset();  //limpia modelo de tabla
            Lista.clear(); //limpia la lista de productos
            while (rs.next()) {
                id=Integer.parseInt(rs.getObject("id_producto").toString());
                nom=rs.getObject("nombre_produc").toString();
                uni=rs.getObject("unidad_medida_produc").toString();
                cant=Integer.parseInt(rs.getObject("cantidad_produc").toString());
                pre=Double.parseDouble(rs.getObject("precio_produc").toString());
                
                              
                //obtiene el nombre del tipo de plato   
                idcate =Integer.parseInt(rs.getObject("id_categoria").toString());
                addFilaTP(id, nom,uni,cant,pre,idcate);
                Lista.add(new Producto(id, nom, uni, cant, pre, idcate));
                System.out.println(id);
            }
            ConexionBD.CloseBD();
        }
        catch(Exception ex){System.out.println(ex.getMessage());}
    }
   //consulta todos los elementos de la tabla productos
    public void consultaAll() {
        String str = "select * from view_producto ";
        ResultSet rs = null;
        try {
            rs = ConexionBD.Consulta(str);
            rellenarV(rs);
            rs.close();
        } catch (Exception ex) {
        }
    }

    //consulta por nombre
    public cProductoFalta buscar_nombre(String nom) {
        cProductoFalta ob = null;
        String str = "select * from productos where nom_produc like '%"
                + nom + "%' order by id_producto";
        System.out.println("" + str);
        ResultSet rs = null;
        try {
            rs = ConexionBD.Consulta(str);
            ob = new cProductoFalta();
            ob.rellenar(rs);
            System.out.println("relleno");
            rs.close();
        } catch (Exception ex) {
        }
        return ob;
    }
    public cProductoFalta buscar_varios(String nom)
       {
           cProductoFalta plat=null;
           String str="select * from view_producto where nom_producto ilike '%"
                   + nom + "%'" + "order by id_producto";
           System.out.println(""+str);
           ResultSet rs = null;
           try{
               rs=ConexionBD.Consulta(str);
               plat=new cProductoFalta();
               plat.rellenarV(rs);                
               rs.close();
           }
           catch(Exception ex){throw new RuntimeException("Error de conexión con el servidor de datos");}
           return plat;
       }
       //consulta por codigo
       public cProductoFalta buscar_codigo(String codigo) {
           cProductoFalta ob = null;
           String str = "select * from productos where id_producto="
                   + codigo ;
           System.out.println("" + str);
           ResultSet rs = null;
           try {
               rs = ConexionBD.Consulta(str);
               ob = new cProductoFalta();
               ob.rellenar(rs);
               System.out.println("relleno");
               rs.close();
           } catch (Exception ex) {
           }
           return ob;
       }

       //consulta por id en base de datos
       public Producto buscar_id_bd(String id) {
           cProductoFalta ob = null;
           String str = "select * from productos where id_prodcuto =" + id;
           System.out.println("" + str);
           ResultSet rs = null;
           try {
               rs = ConexionBD.Consulta(str);
               ob = new cProductoFalta();
               ob.rellenar(rs);
               System.out.println("relleno");
               rs.close();
           } catch (Exception ex) {
           }
           return ob.Count() > 0 ? ob.get_Producto(0) : null;
       }

       //buscar por id
       public int buscar_id(int id) {
           for (int i = 0; i < Count(); i++) {
               if (get_Producto(i).getId_producto()== id) {
                   return i;
               }
           }
           return -1;
    }
}
