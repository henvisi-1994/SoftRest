/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftRest.modelos;

/**
 *
 * @author usuario
 */
public class Categoria {
    private int codigo_categoria;
    private String nombre_categoria;   

    public Categoria() {
    }

    public Categoria(int codigo_categoria, String nombre_categoria) {
        this.codigo_categoria = codigo_categoria;
        this.nombre_categoria = nombre_categoria;
    }

    public int getCodigo_categoria() {
        return codigo_categoria;
    }

    public void setCodigo_categoria(int codigo_categoria) {
        this.codigo_categoria = codigo_categoria;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }
}
