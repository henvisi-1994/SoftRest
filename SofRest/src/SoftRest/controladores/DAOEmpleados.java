/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class DAOEmpleados {

    private DefaultTableModel datos;
    public int Count(){return datos.getRowCount();}
}
