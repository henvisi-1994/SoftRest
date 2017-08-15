/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftRest.modelos;

/**
 *
 * @author Paul Torres
 */
public class Locales {

    private int codigo_local;
    private String dir_local;

    public Locales(int codigo_local, String dir_local) {
        this.codigo_local = codigo_local;
        this.dir_local = dir_local;
    }

    public Locales() {
    }

    public int getCodigo_local() {
        return codigo_local;
    }

    public void setCodigo_local(int codigo_local) {
        this.codigo_local = codigo_local;
    }

    public String getDir_local() {
        return dir_local;
    }

    public void setDir_local(String dir_local) {
        this.dir_local = dir_local;
    }
    
}
