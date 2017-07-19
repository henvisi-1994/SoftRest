/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftRest.controladores;
 
/**
 *
 * @author Eddie Bustamante
 */
    public class NodoProveedores<T> {

        public T info;
        public NodoProveedores sgte;

        public NodoProveedores(T info) {
            this.info = info;
            this.sgte = null;
        }
    
}
