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
import SoftRest.modelos.Empleados;
import static SoftRest.vistas.Validaciones.cedula;
import java.sql.Connection;
/**
 *
 * @author Usuario
 */
public class cEmpleados {
      //arreglo de objetos
    ArrayList<Empleados> Lista;    
           
    //tabla de datos
    DefaultTableModel datos;
    public String[] columnNames = {"Cedula", "Nombre", "Direccion", "Telefono", "Local"};
    //retorna el número de filas
    public int Count(){return datos.getRowCount();}

    //Constructor
    public cEmpleados(){
        Lista= new ArrayList<Empleados>();
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
    public Empleados get_Empleados(int pos){
        return (Empleados)Lista.get(pos);
    }
    
    //Metodos que retornan valores de una celda segun campos individuales
    public String get_Cedula(int pos){
        return datos.getValueAt(pos, 0).toString();
    }
    public String get_Nombre(int pos){
        return datos.getValueAt(pos, 1).toString();
    }
   //agrega la nueva fila al modelo de tabla   
    public void addFila(String cedula, String nom, String direccion, String telefono)
    {
        Object[] row={new Empleados(cedula,nom, direccion,telefono)};
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
    public String insertar(Empleados ob)
    {
        cEmpleados lis=new cEmpleados();
        ob.setCedula(lis.insertar((Empleados)ob));
        String str="insert into empleados(" +
                "ced_empleado, nombre_emp, dir_emp, telf_emp, id_local) " + "values(?,?,?,?,?)";
        //lista de parametros
        ArrayList param=new ArrayList();
        param.add(ob.getCedula());
        param.add(ob.getNombre());
        param.add(ob.getDirecion());
         param.add(ob.getTelefono());
         param.add(1);
         
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
    public void actualizar(Empleados ob)
    {
          cEmpleados lis=new cEmpleados();
        lis.actualizar((Empleados)ob);
        
        String str="update empleados SET  nombre_emp=?, dir_emp=?, telf_emp=?, id_local=? "
                + "where ced_empleado=?";
        //lista de parametros
        ArrayList param=new ArrayList();
        param.add(ob.getNombre());  
        param.add(ob.getDirecion());  
        param.add(ob.getTelefono()); 
        param.add(1);
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
        String str="delete from cliente where ced_empleado=?";
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
            Empleados ob=new Empleados();  
            reset();  //limpia modelo de tabla
            Lista.clear(); //limpia la lista de productos
            while (rs.next()) {
                ob.setCedula(rs.getObject("ced_empleado").toString());                
                ob.setNombre(rs.getObject("nombre_emp").toString());
                ob.setDirecion(rs.getObject("dir_emp").toString());
                ob.setTelefono(rs.getObject("telf_emp").toString());          
                addFila(ob.getCedula(), ob.getNombre(), ob.getDirecion(), ob.getTelefono());
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
        String str="select * from empleados order by ced_empleado";
        ResultSet rs = null;
        try{
                rs=ConexionBD.Consulta(str);
                rellenar(rs);
                rs.close();
        }
        catch(Exception ex){}
    }
       
    //consulta por codigo
     public cEmpleados buscar_ruc_completo_bd(String ruc)
    {
        cEmpleados ob=new cEmpleados();
        String str="select * from empleados where ced_empleado = '" + ruc + "'";
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
    public cEmpleados buscar_nombre(String nom)
    {
        cEmpleados ob=new cEmpleados();
        String str="select * from Empleados where nombre_emp like '%"
            + nom + "%' order by per_codigo";
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
    public cEmpleados buscar_ruc_bd(String ruc)
    {
        cEmpleados ob=new cEmpleados();
        String str="select * from Empleados where ced_empleado like '%" + ruc + "%' order by ced_empleado";
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
