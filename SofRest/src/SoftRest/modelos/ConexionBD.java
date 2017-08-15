package SoftRest.modelos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConexionBD {

    static Connection conn = null;
    public static boolean iscon = false;
    static Statement stmt_consul = null;
    private static final String SEPARATOR = ",";

    //parametros para conexion Postgres
    static String dbms = "postgres";
    static String server = leer()[1][0];
    static String bd = "SoftRest";
    static String user = leer()[1][1];
//    static String pass = leer()[1][2];
//    static String puerto = leer()[1][3];
//    
//    static String dbms = "postgres";
//    static String server = "localhost";
//    static String bd = "SoftRest";
//    static String user = "postgres";
    static String pass ="12345";//leer()[1][2];
    static String puerto = "5432";

    
    

    //Archivo de configuracion
    public static String[][] leer() {
        BufferedReader br = null;
        BufferedReader bt = null;
        String[][] a = null;
        int n = 0;
        int p = 0;
        try {
            String inputFile = new File("src/SoftRest/modelos/config.csv").getAbsolutePath();

            br = new BufferedReader(new FileReader(inputFile));
            bt = new BufferedReader(new FileReader(inputFile));
            String line = br.readLine();
            String lin = bt.readLine();
            while (null != lin) {
                n = n + 1;
                String[] fields = lin.split(SEPARATOR);
                p = fields.length;
                lin = bt.readLine();
            }
            a = new String[n][p];
            p = 0;
            while (null != line) {
                String[] fields = line.split(SEPARATOR);
                for (int i = 0; i < fields.length; i++) {
                    a[p][i] = fields[i];
                }
                line = br.readLine();
                p = p + 1;
            }

        } catch (Exception e) {

        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException ex) {

                }
            }
        }
        return a;

    }
  //conexion a la base de datos
    //si el servidor es Mysql

    public static Connection ConectarBDMysql() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://" + server + "/" + bd;
            conn = DriverManager.getConnection(url, user, pass);
            //conn = DriverManager.getConnection("jdbc:mysql://localhost/ventas?user=root&password=admin");
            iscon = true;
            System.out.println("conectoooooooooo");
        } catch (Exception ex) {
        }
        return conn;
    }

    //si el servidor es Posgres
    public static Connection ConectarBDPostgres() {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            String url = "jdbc:postgresql://" + server + ":" + puerto + "/" + bd;
            conn = DriverManager.getConnection(url, user, pass);
            //conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ventas","postgres", "admin");
            iscon = true;
            System.out.println("conectoooooooooo");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }
        return conn;
    }

    public static Connection ConectarBD() {
        //conecta a mysql
        if (dbms.equalsIgnoreCase("mysql")) {
            try {
                conn = ConectarBDMysql();
                iscon = true;
                System.out.println("conectoooooooooo");
            } catch (Exception ex) {
                iscon = false;
            }
        }

        //conecta a postgres
        if (dbms.equalsIgnoreCase("postgres")) {
            try {
                conn = ConectarBDPostgres();
                iscon = true;
                System.out.println("conectoooooooooo");
            } catch (Exception ex) {
                iscon = false;
            }
        }
        return conn;
    }

    //Ejecutar sentencias sql insert, update y delete
    public static boolean EjecutarSql(String strsql) {
        boolean ok = false;
        Statement stmt_consul = null;
        try {
            System.out.println("iniciando consulta");
            if (!iscon) {
                conn = ConectarBD();
            }
            stmt_consul = conn.createStatement();  //crear sentencia
            stmt_consul.executeUpdate(strsql); //ejecuta la sentencia
            ok = true;
            try {
                stmt_consul.close();
                conn.close();
                iscon = false;
            } catch (SQLException sqlEx) {
                ok = false;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ok = false;
        }
        return ok;
    }

    //Ejecutar sentencias Select
    public static ResultSet Consulta(String strsql) {
        Statement stmt_consul = null;
        ResultSet rs = null;
        try {
            if (!iscon) {
                conn = ConectarBD();
            }
            System.out.println("iniciando consulta");
            stmt_consul = conn.createStatement();  //crear sentencia
            stmt_consul.executeQuery(strsql); //ejecuta la sentencia
            rs = stmt_consul.getResultSet();
            System.out.println("fin de consulta");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return rs;
    }

  //inserta,eliminia, actualiza en la base de datos utilizando parametros
    public static int Ejecutar_sql_parametro(String sql, ArrayList param) {
        int id = -1;
        try {
            //conexion a la base de datos
            System.out.println("iniciando consulta");
            if (!iscon) {
                conn = ConectarBD();
            }
            //preparar la consulta
            PreparedStatement prest = conn.prepareStatement(sql);
            //Asigna un valor a un parametro;
            for (int i = 0; i < param.size(); i++) {
                prest.setObject(i + 1, param.get(i));
            }
            System.out.println(prest.toString());
            id = prest.executeUpdate();  //ejecuta sentencia en la base de datos
            System.out.println("Se ejecutó");

            prest.close();
            conn.close();
            iscon = false;
            return id;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    public ConexionBD() {
    }

    //cerrar conexion a la base de datos
    public static void CloseBD() {
        try {
            stmt_consul.close();
            conn.close();
            iscon = false;
        } catch (SQLException sqlEx) {
        }
    }
}
