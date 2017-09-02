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
public class Producto {
    public int id_producto;
    public String nom_produc;
    public String unidad_medida_produc;
    public int cantidad_proc;
    public double precio_produc;
    public int id_categoria;
    private String nom_categoria;

    public Producto() {
    }

    public Producto(int id_producto, String nom_produc, String unidad_medida_produc, int cantidad_proc, double precio_produc, int id_categoria) {
        this.id_producto = id_producto;
        this.nom_produc = nom_produc;
        this.unidad_medida_produc = unidad_medida_produc;
        this.cantidad_proc = cantidad_proc;
        this.precio_produc = precio_produc;
        this.id_categoria = id_categoria;
    }

    public Producto(int id_producto, String nom_produc, String unidad_medida_produc, int cantidad_proc, double precio_produc, String nom_categoria) {
        this.id_producto = id_producto;
        this.nom_produc = nom_produc;
        this.unidad_medida_produc = unidad_medida_produc;
        this.cantidad_proc = cantidad_proc;
        this.precio_produc = precio_produc;
        this.nom_categoria = nom_categoria;
    }
    

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNom_produc() {
        return nom_produc;
    }

    public void setNom_produc(String nom_produc) {
        this.nom_produc = nom_produc;
    }

    public String getUnidad_medida_produc() {
        return unidad_medida_produc;
    }

    public void setUnidad_medida_produc(String unidad_medida_produc) {
        this.unidad_medida_produc = unidad_medida_produc;
    }

    public int getCantidad_proc() {
        return cantidad_proc;
    }

    public void setCantidad_proc(int cantidad_proc) {
        this.cantidad_proc = cantidad_proc;
    }

    public double getPrecio_produc() {
        return precio_produc;
    }

    public void setPrecio_produc(double precio_produc) {
        this.precio_produc = precio_produc;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNom_categoria() {
        return nom_categoria;
    }

    public void setNom_categoria(String nom_categoria) {
        this.nom_categoria = nom_categoria;
    }
    
    
    
}
