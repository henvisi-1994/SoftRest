package SoftRest.controladores;

import SoftRest.modelos.ConexionBD;
import SoftRest.modelos.Fac_ped_detalle;
import SoftRest.modelos.Factura_pedido;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;



public class cFactura_pedido
{
    //arreglo de objetos
    ArrayList<Factura_pedido> Lista;    
    
    //tabla de datos
    DefaultTableModel datos;
    public String[] columnNames = {"Fecha", "Número","Ruc-Ci",
                                    "Cliente", "Vendedor", "Subtotal","Subtotal 0%",
                                    "Sutotal Iva", "Descuento","Total", "Estado"
                                  };
    //retorna el número de filas
    public int Count(){return datos.getRowCount();}

    //Constructor
    public cFactura_pedido(){
        Lista= new ArrayList<Factura_pedido>();
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
    public Factura_pedido get_Factura_venta(int pos){
        return (Factura_pedido)Lista.get(pos);
    }
    
    //agrega la nueva fila al modelo de tabla   
    public void addFila(Date fecha, String num, String ruc, String cli,String vend, double subtotal,
                        double sub0, double subiva, double desc, double total,boolean estado)
    {
        Object[] row={fecha,num, ruc,cli,vend,new Double(subtotal),
                      new Double(sub0),new Double(subiva),new Double(desc),
                      new Double(total),new Boolean(estado)
                     };
        datos.addRow(row);
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
    public void insertar(Factura_pedido ob)
    {
        int cod=-1;
        String str="INSERT INTO factura_venta(" +
                   "fac_numero, fac_fecha, cli_codigo, ven_codigo, fac_subtotal," +
                   "fac_subcero, fac_subiva, fac_total, fac_descuento, fac_estado) " +
                   "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        //lista de parametros
        ArrayList param=new ArrayList();
        param.add(ob.fac_numero);
        param.add(ob.fac_fecha);
        param.add(ob.cli_codigo);
        param.add(ob.idPedido);
        param.add(ob.fac_subtotal);
        param.add(ob.fac_subcero);
        param.add(ob.fac_subiva);
        param.add(ob.fac_total);
        param.add(ob.fac_descuento);
        param.add(ob.fac_estado);
        System.out.println(str);
        try{           
            cod=buscar_max_codigo() + 1; //incrementa en 1 último código generado
            cod=cod<=0?1:cod; //establece a 1 cuando no hay registros
            System.out.println("Nuevo codigo generado: "+ cod);
            //modifica la secuencia según codigo del último registro
            ConexionBD.EjecutarSql("ALTER SEQUENCE factura_venta_fac_codigo_seq RESTART WITH "+cod);
            //guarda el encabezado de la factura
            ConexionBD.Ejecutar_sql_parametro(str,param);
            //guarda detalles de la factura  
            cFac_pedido_detalle f=new cFac_pedido_detalle();
            for(int i=0;i<ob.items.size();i++)
            {
                //insertar items
                Fac_ped_detalle fv=(Fac_ped_detalle)ob.items.get(i);
                fv.fac_codigo=cod; 
                f.insertar(fv);                
            }
            System.out.println("inserto");            
        }
        catch(Exception ex){throw new RuntimeException("Error al insertar el nuevo registro");}
        //return estado;
    }
    
    //consulta el máximo codigo
    public int buscar_max_codigo()
    {
        int cod=-1;
        String str="select max(fac_codigo) as fac_codigo from factura_venta";               
        System.out.println(""+str);
        ResultSet rs = null;
        try{
            rs=ConexionBD.Consulta(str);           
            if (rs.next()) {
                cod=Integer.parseInt(rs.getObject("fac_codigo").toString());                
            }
            System.out.println("relleno");
            rs.close();
        }
        catch(Exception ex){}
        return cod;
    }

   
    //actualizar un registro en la base de datos
    /*public void actualizar(Factura_venta ob)
    {
        cPersona lis=new cPersona();
        lis.actualizar((Persona)ob);
        
        String str="update cliente set cli_tipo=?, cli_saldo=? "
                + "where cli_codigo=?";
        //lista de parametros
        ArrayList param=new ArrayList();
        param.add(ob.cli_tipo);
        param.add(ob.cli_saldo);  
        param.add(ob.per_codigo);  
       
        System.out.print(str);
        try{
            ConexionBD.Ejecutar_sql_parametro(str,param);
            
            System.out.print("actualizacion exitosa");
        }
        catch(Exception ex){throw new RuntimeException("Error al actualizar los datos ");}
        //return estado;
    }*/   
    
     //actualizar un registro en la base de datos
    /*public void eliminar(int id)
    {
        String str="delete from cliente where cli_codigo=?";
        //lista de parametros
        ArrayList param=new ArrayList();        
        param.add(id);           

        System.out.print(str);
        //boolean estado=false;
        try{
            ConexionBD.Ejecutar_sql_parametro(str,param);
            //estado=true;
            System.out.print("eliminación exitosa");
        }
        catch(Exception ex){throw new RuntimeException("Error: No se puede eliminar el registro,"
            +" existen dependencias");}
        //return estado;
    }*/ 
  
    //rellena el modelo de table seg�n los resultados obtenidos de la BD   
    public void rellenar(ResultSet rs)
    {
        try{
            Factura_pedido ob=new Factura_pedido();  
            reset();  //limpia modelo de tabla
            Lista.clear(); //limpia la lista de productos
            while (rs.next()) {
                ob.fac_codigo=Integer.parseInt(rs.getObject("fac_codigo").toString());                
                ob.fac_numero=rs.getObject("fac_numero").toString();
                ob.fac_fecha=Date.valueOf(rs.getObject("fac_fecha").toString());
                ob.cli_codigo=Integer.parseInt(rs.getObject("cli_codigo").toString());   
                ob.cliente=rs.getObject("cliente").toString();
                ob.cli_ruc=rs.getObject("cli_ruc").toString();
                ob.cli_direccion=rs.getObject("cli_direccion").toString();
                ob.cli_ciudad=rs.getObject("cli_ciudad").toString();
                ob.cli_telefono=rs.getObject("cli_telefono").toString();
                ob.cli_email=rs.getObject("cli_email").toString();
//                ob.ven_codigo=Integer.parseInt(rs.getObject("ven_codigo").toString());                 
//                ob.vendedor= rs.getObject("vendedor").toString();
                ob.fac_subtotal=Float.parseFloat(rs.getObject("fac_subtotal").toString());
                ob.fac_subcero=Float.parseFloat(rs.getObject("fac_subcero").toString());
                ob.fac_subiva=Float.parseFloat(rs.getObject("fac_subiva").toString());
                ob.fac_total=Float.parseFloat(rs.getObject("fac_total").toString());
                ob.fac_descuento=Float.parseFloat(rs.getObject("fac_descuento").toString());
                ob.fac_estado=Boolean.parseBoolean(rs.getObject("fac_estado").toString());

//                addFila(ob.fac_fecha, ob.fac_numero, ob.cli_ruc, ob.cliente, ob.vendedor, 
//                        ob.fac_subtotal,ob.fac_subcero, ob.fac_subiva, ob.fac_descuento, ob.fac_total,ob.fac_estado);
                Lista.add(ob);
                System.out.println("Nombre: "+ob.cliente);
            }
            ConexionBD.CloseBD();
        }
        catch(Exception ex){System.out.println(ex.getMessage());}
    }

          
    //consulta por codigo
    public cFactura_pedido buscar_codigo_bd(String codigo)
    {
        cFactura_pedido ob=new cFactura_pedido();
        String str="select * from vi_factura_venta where fac_codigo =" + codigo +
                " order by fac_codigo";
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
    
    public cFactura_pedido buscar_fac_numero(String numero)
    {
        cFactura_pedido ob=new cFactura_pedido();
        String str="select * from vi_factura_venta where fac_numero like '%"
            + numero + "%' order by fac_codigo";
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
    
    //consulta por codigo
    public cFactura_pedido buscar_cliente(String nom)
    {
        cFactura_pedido ob=new cFactura_pedido();
        String str="select * from vi_factura_venta where cliente like '%"
            + nom + "%' order by fac_codigo";
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
    
    //consulta por codigo
    public cFactura_pedido buscar_vendedor(String nom)
    {
        cFactura_pedido ob=new cFactura_pedido();
        String str="select * from vi_factura_venta where vendedor like '%"
            + nom + "%' order by fac_codigo";
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
    
    //consulta por ruc
    public cFactura_pedido buscar_ruc_bd(String ruc)
    {
        cFactura_pedido ob=new cFactura_pedido();
        String str="select * from vi_factura_venta where cli_ruc like '%" + ruc + "%' order by fac_codigo";
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
    //consulta por ruc
    public cFactura_pedido buscar_fecha(Date fecha1,Date fecha2)
    {
        cFactura_pedido ob=new cFactura_pedido();
        String str="select * from vi_factura_venta where fac_fecha between '" + fecha1 + "'"+
                   "and '" + fecha2 + "' order by fac_codigo";
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
