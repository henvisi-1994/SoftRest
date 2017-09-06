package SoftRest.controladores;


import SoftRest.modelos.ConexionBD;
import SoftRest.modelos.Fac_ped_detalle;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


/*
fac_codigo bigint,
  pro_codigo bigint,
  fv_cantidad double precision,
  fv_puv double precision,
  fv_iva double precision,
  fv_descuento double precision,
  fv_estado boolean,
*/
public class cFac_pedido_detalle
{
    //arreglo de objetos
    ArrayList<Fac_ped_detalle> Lista;    
    
    //tabla de datos
    DefaultTableModel datos;
    public String[] columnNames = {"Código", "Descripción","Cantidad",
                                    "Precio", "Iva","Descuento","Importe"
                                  };
    //retorna el número de filas
    public int Count(){return datos.getRowCount();}

    //Constructor
    public cFac_pedido_detalle(){
        Lista= new ArrayList<Fac_ped_detalle>();
        datos=new DefaultTableModel(){
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
        for(int i=0;i<columnNames.length;i++)
                datos.addColumn(columnNames[i]);
    }
    
    //obtener un producto
    public Fac_ped_detalle get_Fac_ped_detalle (int pos){
        return (Fac_ped_detalle)Lista.get(pos);
    }
    
    //agrega la nueva fila al modelo de tabla   
    public void addFila(int cod, String desc, double cant,
                        double precio, double iva, double descuento, double importe)
    {
        Object[] row={new Integer(cod),desc,new Double(cant), new Double(precio),
                      new Double(iva),new Double(descuento),new Double(importe)
                     };
        datos.addRow(row);
    }
    
    //eliminar una fila
    public void deleteFila(int pos)
    {
        datos.removeRow(pos);
    }
    //limpia todos los datos del Modelo de tabla
    public void reset()
    {
        while(Count()>0){
            datos.removeRow(Count()-1);                
        }
    }
    //Retorna el modelo de tabla
    public DefaultTableModel getTablaDatos()
    {
        return datos;
    }

    /********Metodos de acceso a la base de datos*/
    //inserta un registro en la base de datos
    public void insertar(Fac_ped_detalle ob)
    {
        int cod=-1;
        String str="INSERT INTO fac_ven_detalle(fac_codigo, pro_codigo, fv_cantidad, " +
                   "fv_puv, fv_iva, fv_descuento, fv_estado) " +
                   "VALUES (?, ?, ?, ?, ?, ?, ?);";
        //lista de parametros
        ArrayList param=new ArrayList();
        param.add(ob.fac_codigo);
        param.add(ob.idpedido);
        param.add(ob.fv_cantidad);
        param.add(ob.fv_puv);
        param.add(ob.fv_iva);
        param.add(ob.fv_descuento);
        param.add(ob.fv_estado);
       
        System.out.println(str);
        try{
            ConexionBD.Ejecutar_sql_parametro(str,param);
            System.out.println("inserto");
            //aqui invocar a actualizar precio
            
            //aqui invocar a insertar en kardex
        }
        catch(Exception ex){throw new RuntimeException("Error al insertar el nuevo registro");}
        //return estado;
    }

  
    //rellena el modelo de table seg�n los resultados obtenidos de la BD   
    public void rellenar(ResultSet rs)
    {
        try{
            Fac_ped_detalle ob=new Fac_ped_detalle();  
            reset();  //limpia modelo de tabla
            Lista.clear(); //limpia la lista de productos
            while (rs.next()) {
                ob.fac_codigo=Integer.parseInt(rs.getObject("fac_codigo").toString());                
                ob.fac_numero=rs.getObject("fac_numero").toString();
                ob.idpedido=Integer.parseInt(rs.getObject("pro_codigo").toString());    
                
                ob.pro_nombre=rs.getObject("pro_nombre").toString();
                ob.fab_nombre=rs.getObject("fab_nombre").toString();
                
                ob.fv_cantidad=Float.parseFloat(rs.getObject("fv_cantidad").toString());
                ob.fv_puv=Float.parseFloat(rs.getObject("fv_puv").toString());
                ob.fv_iva=Float.parseFloat(rs.getObject("fv_iva").toString());
                ob.fv_descuento=Float.parseFloat(rs.getObject("fv_descuento").toString());                
                ob.fv_estado=Boolean.parseBoolean(rs.getObject("fv_estado").toString());

                double importe= ob.fv_puv*ob.fv_cantidad;
                /*
                int cod, String desc, double cant,
                        double precio, double importe, double iva, double descuento
                */
                addFila(ob.idpedido, ob.pro_nombre, ob.fv_cantidad, ob.fv_puv, 
                        ob.fv_iva,ob.fv_descuento, importe);
                Lista.add(ob);
                System.out.println("Nombre: "+ob.pro_nombre);
            }
            ConexionBD.CloseBD();
        }
        catch(Exception ex){System.out.println(ex.getMessage());}
    }

          
    //consulta por codigo
    public cFac_pedido_detalle buscar_codigo_bd(String fac_codigo)
    {
        cFac_pedido_detalle ob=new cFac_pedido_detalle();
        String str="select * from vi_fac_ven_detalle where fac_codigo =" + fac_codigo +
                " order by pro_codigo";
        System.out.println(""+str);
        ResultSet rs = null;
        try{
            rs=ConexionBD.Consulta(str);
            ob.rellenar(rs);            
            System.out.println("relleno");
            rs.close();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
        return ob;
    }   
    
   
}
