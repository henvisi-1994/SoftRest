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
    public class NodoClientes<T> {

        public T info;
        public NodoClientes sgte;

        public NodoClientes(T info) {
            this.info = info;
            this.sgte = null;
        }
    
}
