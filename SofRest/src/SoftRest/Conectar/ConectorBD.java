/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftRest.Conectar;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author EddieBustamant
 */
public class ConectorBD {
    static Connection conn= null;
    public static boolean iscon=false;
    static Statement stmt_consul=null;
      //parametros para conexion Postgres
    static String dbms="postgres";
    static String server="localhost";
    static String bd="SoftRest";
    static String user="postgres";
    static String pass="1234";
    static String puerto="5432";


    public static Connection ConectarBDPostgres()
    {
    try {
        Class.forName("org.postgresql.Driver").newInstance();
        String url="jdbc:postgresql://"+server+":"+puerto+"/"+bd;
        conn = DriverManager.getConnection(url, user, pass);
        iscon=true;
        System.out.println("conecto");
        }catch(Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
            }return conn;
    }
    
        //conexion a la base de datos
        //si el servidor es Mysql
//    public static Connection ConectarBDMysql()
//    {
//      try {
//        Class.forName("com.mysql.jdbc.Driver").newInstance();
//        String url="jdbc:mysql://"+server+"/"+bd;
//        conn = DriverManager.getConnection(url,  user, pass);
//        iscon=true;
//        System.out.println("conectoooooooooo");
//      }catch(Exception ex){
//            }return conn;
//    }
      public static Connection conexion()
    {
      //conecta a mysql
//      if (dbms.equalsIgnoreCase("mysql"))
//      {
//        try {
//          conn = ConectarBDMysql();
//          iscon=true;
//          System.out.println("conectoooooooooo");
//        }catch(Exception ex) {iscon=false;}
//      }
      //conecta a postgres
      if (dbms.equalsIgnoreCase("postgres"))
      {
        try {
          conn = ConectarBDPostgres();
          iscon=true;
          System.out.println("conectoo");
        }catch(Exception ex) {iscon=false;}
      }
      return conn;
    }
      

  }
