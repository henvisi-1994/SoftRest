/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftRest.controladores;

import SoftRest.modelos.ConexionBD;
import SoftRest.modelos.Clientes;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author usuario
 */
public class cClientes {
    ArrayList<Clientes> Lista;    
           
    //tabla de datos
    DefaultTableModel datos;
    public String[] columnNames = {"cedula", "nombre", "apellido", "telefono", "email", "direccion"};
    //retorna el número de filas
    public int Count(){return datos.getRowCount();}

    //Constructor
    public cClientes(){
        Lista= new ArrayList<Clientes>();
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
    public Clientes get_Clientes(int pos){
        return (Clientes)Lista.get(pos);
    }
    
   //agrega la nueva fila al modelo de tabla   
    //agrega la nueva fila al modelo de tabla   
    public void addFila(String cedula, String nom,String apellido, String telefono, String email, String direccion)
    {
        Object[] row={new Clientes(cedula,nom,apellido,telefono,email,direccion)};
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
    public String insertar(Clientes ob)
    {
        cClientes lis=new cClientes();
        ob.setCedula(lis.insertar((Clientes)ob));
        String str="insert into Clientes(" +"ced_cli,nom_cli,ape_cli,tel_cli,ema_cli,dir_cli) " + "values(?,?,?,?,?,?)";
        //lista de parametros
        ArrayList param=new ArrayList();
        param.add(ob.getCedula());
        param.add(ob.getNombre());
        param.add(ob.getApellido());
        param.add(ob.getTelefono());
        param.add(ob.getEmail());
        param.add(ob.getDireccion());
         
        System.out.println(str);
        
        //boolean estado=false;
        try{           
            ConexionBD.Ejecutar_sql_parametro(str,param);            
            System.out.println("inserto");
            return ob.getCedula();
        }
        catch(Exception ex){throw new RuntimeException("Error al insertar el nuevo registro");}
        //return estado;
    }

   
    //actualizar un registro en la base de datos
    public void actualizar(Clientes ob)
    {
        cClientes lis=new cClientes();
        lis.actualizar((Clientes)ob);
        
        String str="update Clientes set  nom_cli=?,ape_cli=?,tel_cli=?,ema_cli=?,dir_cli=? "
                + "where ced_cli=?";
        //lista de parametros
        ArrayList param=new ArrayList();
       
        param.add(ob.getNombre());
        param.add(ob.getApellido());
        param.add(ob.getTelefono());
        param.add(ob.getEmail());
        param.add(ob.getDireccion());
        param.add(ob.getCedula());
        System.out.print(str);
        try{
            ConexionBD.Ejecutar_sql_parametro(str,param);
            
            System.out.print("actualizacion exitosa");
        }
        catch(Exception ex){throw new RuntimeException("Error al actualizar los datos ");}
        //return estado;
    }   
    
     //actualizar un registro en la base de datos
    public void eliminar(String cedula)
    {
        String str="delete from Clientes where ced_cli=?";
        //lista de parametros
        ArrayList param=new ArrayList();        
        param.add(cedula);           

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
    } 
  
    //rellena el modelo de table seg�n los resultados obtenidos de la BD   
    public void rellenar(ResultSet rs)
    {
        try{
            Clientes ob=new Clientes();  
            reset();  //limpia modelo de tabla
            Lista.clear(); //limpia la lista de productos
            while (rs.next()) {
                ob.setCedula(rs.getObject("ced_cli").toString());                
                ob.setNombre(rs.getObject("nom_cli").toString());
                ob.setApellido(rs.getObject("ape_cli").toString());
                ob.setTelefono(rs.getObject("tel_cli").toString());
                ob.setEmail(rs.getObject("ema_cli").toString());          
                ob.setDireccion(rs.getObject("dir_cli").toString());          
                addFila(ob.getCedula(), ob.getNombre(), ob.getApellido(), ob.getTelefono(), ob.getEmail(), ob.getDireccion());
                Lista.add(ob);
                System.out.println("Nombre: "+ob.getNombre());
            }
            ConexionBD.CloseBD();
        }
        catch(Exception ex){System.out.println(ex.getMessage());}
    }

    //consulta todos los elementos de la tabla productos
    public void consultaAll()
    {
        String str="select * from Clientes order by ced_cli";
        ResultSet rs = null;
        try{
                rs=ConexionBD.Consulta(str);
                rellenar(rs);
                rs.close();
        }
        catch(Exception ex){}
    }
       
    //consulta por codigo
    public cClientes buscar_codigo_bd(String cedula)
    {
        cClientes ob=new cClientes();
        String str="select * from Clientes where ced_cli =" + cedula +
                " order by ced_cli";
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
    public cClientes buscar_nombre(String nom)
    {
        cClientes ob=new cClientes();
        String str="select * from Clientes where nom_cli like '%"
            + nom + "%' order by per_codigo";//que es esto????
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
    public cClientes buscar_ruc_bd(String ruc)
    {
        cClientes ob=new cClientes();
        String str="select * from Clientes where ced_cli like '%" + ruc + "%' order by ced_cli";
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
