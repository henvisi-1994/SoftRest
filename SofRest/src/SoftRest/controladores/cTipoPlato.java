/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftRest.controladores;

import SoftRest.modelos.Categoria;
import SoftRest.modelos.ConexionBD;
import SoftRest.modelos.Plato;
import SoftRest.modelos.TipoPlato;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author usuario
 */
public class cTipoPlato {
   DefaultTableModel datos;
    ArrayList<TipoPlato> Lista;
    //etiquetas de tabla
    public String[] columnNames = {"Código", "Nombre"};
    
    //Constructor
    public cTipoPlato(){
         Lista = new ArrayList<TipoPlato>();
        datos=new DefaultTableModel();
        for(int i=0;i<columnNames.length;i++)
            datos.addColumn(columnNames[i]);
    }

    //metodo que retorna el numero de filas
   public int Count(){return datos.getRowCount();}
     public TipoPlato get_TipoPlato(int pos) {
        return (TipoPlato) Lista.get(pos);
    }
   //Metodos que retornan valores de una celda segun campos individuales
    public String get_Codigo(int pos){
        if (pos==-1|| pos <0) {
            return "0";
        } else {
             return datos.getValueAt(pos, 0).toString();
        }
    }
    public String get_Nombre(int pos){
        return datos.getValueAt(pos, 1).toString();
    }
    
   //agrega la nueva fila
    public void addFila(int cod,String nombre){
        Object[] row={new Integer(cod),nombre};
        datos.addRow(row);
    }
    //limpia todos los datos del Modelo de tabla
    public void reset()
    {
        while(Count()>0)
            datos.removeRow(Count()-1);
    }
    //Retorna el modelo de tabla
    public DefaultTableModel getTablaDatos()
    {
        return datos;
    }

    /********Metodos de acceso a la base de datos*/
    //inserta un registro en la base de datos
    public void insertar(TipoPlato ob)
    {
        String str="insert into tipo_plato(nombre_tipo_plato) values(?)";
        //lista de parametros
        ArrayList param=new ArrayList();
        param.add(ob.getTipoplato_nombre());

        System.out.print(str);
        //boolean estado=false;
        try{
            int cod=Integer.parseInt(get_Codigo(Count()-1))+1; //extraer el último código generado
            System.out.print("Ultimo codigo "+ cod);
            //modifica secuencia según codigo del último registro
            ConexionBD.EjecutarSql("ALTER SEQUENCE sec_id_tplato RESTART WITH "+cod);
            ConexionBD.Ejecutar_sql_parametro(str,param);
            //estado=true;
            System.out.print("inserto");
        }
        catch(Exception ex){throw new RuntimeException("Error al insertar el nuevo registro");}
        //return estado;
    }

    //actualizar un registro en la base de datos
    public void actualizar(TipoPlato ob)
    {
        String str="update tipo_plato set nombre_tipo_plato=? where id_tipo_plato=?";
        //lista de parametros
        ArrayList param=new ArrayList();
        param.add(ob.getTipoplato_nombre());
        param.add(ob.getTipoplato_id());           

        System.out.print(str);
        //boolean estado=false;
        try{
            ConexionBD.Ejecutar_sql_parametro(str,param);
            //estado=true;
            System.out.print("actualizacion exitosa");
        }
        catch(Exception ex){
            throw new RuntimeException("Error al actualizar los datos ");
            //throw new RuntimeException(ex.getMessage());

        }
        //return estado;
    }   
    
     //actualizar un registro en la base de datos
    public void eliminar(int cod)
    {
        String str="delete from tipo_plato where id_tipo_plato=?";
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
    public void rellenar(ResultSet rs)
    {
        try{
            int cod;
            String nom;
            reset();  //limpia la lista de productos
            while (rs.next()) {
                cod=Integer.parseInt(rs.getObject("id_tipo_plato").toString());
                nom=rs.getObject("nombre_tipo_plato").toString();
                addFila(cod,nom); //añade una fila al modelo
                System.out.println(nom);
            }
            System.out.println("Aqui  1.2.1");
            ConexionBD.CloseBD();
        }
        catch(Exception ex){//throw new RuntimeException("Error de visualización de datos");            
        }
    }

    //consulta todos los elementos de la tabla productos
    public void consultaAll()
    {
        String str="select * from tipo_plato order by id_tipo_plato";
        ResultSet rs = null;
        try{
            System.out.println("Aqui  1.1");
            rs=ConexionBD.Consulta(str);
            System.out.println("Aqui  1.2");
            rellenar(rs);
            System.out.println("Aqui  1.3");
            rs.close();
        }
        catch(Exception ex){throw new RuntimeException("Error de conexión con el servidor de datos");}
    }

    //consulta los elementos segun la descripci�n
    public cTipoPlato buscar_varios(String nom)
    {
        cTipoPlato cat=null;
        String str="select * from tipo_plato where nombre_tipo_plato ilike '%"
                + nom + "%'" + "order by id_tipo_plato";
        System.out.println(""+str);
        ResultSet rs = null;
        try{
            rs=ConexionBD.Consulta(str);
            cat=new cTipoPlato();
            cat.rellenar(rs);                
            rs.close();
        }
        catch(Exception ex){throw new RuntimeException("Error de conexión con el servidor de datos");}
        return cat;
    }

    //buscar por código
    public int buscar_codigo(String cod)
    {
        for(int i=0; i<Count();i++)
            if(get_Codigo(i)==(cod))
                return i;
        return -1;        
    }
    //buscar por nombre
    public int buscar_nombre(String nom)
    {
        for(int i=0; i<Count();i++)
            if(get_Nombre(i).equalsIgnoreCase(nom))
                return i;
        return -1;        
    }
      public TipoPlato buscar_id_bd(String nombre) {
        cTipoPlato ob = null;
        String str = "select id_tipo_plato from producto where nombre_tipo_plato ilike '%" + nombre+"%'";
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
