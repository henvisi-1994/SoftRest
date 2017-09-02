/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftRest.vistas;

import SoftRest.modelos.ConexionBD;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Berty
 */
public class Global {

    public static double iva = 0.14;

    //obtiene ruta de la carpeta de reportes
    public static String getPathReport() {
        String os = System.getProperty("os.name");
        if (!os.equals("Linux")) {
            return getPath() + "Reportes\\";
        } else {
            return getPath() + "Reportes//";
        }
    }

    //obtiene ruta de la carpeta de vistas
    public static String getPathVistas() {
        String os = System.getProperty("os.name");
        if (!os.equals("Linux")) {
            return getPath() + "proyinventario\\vistas";
        } else {
            return getPath() + "proyinventario//vistas";
        }
    }

    //obtiene ruta de la carpeta de vistas
    public static String getPathImagenes() {
        String os = System.getProperty("os.name");
        if (!os.equals("Linux")) {
            return getPath() + "imagenes\\";
        } else {

            return getPath() + "imagenes//";
        }

    }

    //obtiene ruta del código fuente del Proyecto
    public static String getPath() {
        //extraer ruta del proyecto de forma dinámica
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        System.out.println("Path del Proyecto " + path);
        //eliminar los dos caracteres del final del path
        path = path.substring(0, path.length() - 2);
          String os = System.getProperty("os.name");
        if (!os.equals("Linux")) {
             return path+="\\src\\";
        }else
        {
            return path += "//src//";
        }
    }

    //genera reportes sin parámetros
    public static void generarReporte(String reporte) throws JRException, FileNotFoundException {
        Map<String, Object> params = new HashMap<String, Object>();
        generarReporte(reporte, params);
    }

    //carga reporte simple
    public static void generarReporte(String reporte, Map<String, Object> params) throws JRException, FileNotFoundException {
        //conexión base de datos
        Connection con = new ConexionBD().ConectarBD();
        //extraer ruta de la carpeta de reportes     
        String path = Global.getPathReport() + reporte;
        String pathlogo = Global.getPathImagenes() + "logo.jpg";
        //reporte fuente
        String reportSource = path + ".jrxml";
        //archivo pdf destino
        String reportDest = path + ".pdf";

        System.out.println(reportSource);
        System.out.println(reportDest);
        System.out.println(pathlogo);

        //Map<String, Object> params = new HashMap<String, Object>();
        params.put("Titulo", new String("Empresa ABC"));
        params.put("Logo", new String(pathlogo));

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, con);
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportDest);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }
}
