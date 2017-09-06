/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftRest.modelos;

/**
 *
 * @author henvisi
 */
public class Pedido 
{
    private int idPedido;
    private String cedula_cli;
    private String tipo_pedido;

    public Pedido() {
    }

    public Pedido(int idPedido, String cedula_cli, String tipo_pedido) {
        this.idPedido = idPedido;
        this.cedula_cli = cedula_cli;
        this.tipo_pedido = tipo_pedido;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getCedula_cli() {
        return cedula_cli;
    }

    public void setCedula_cli(String cedula_cli) {
        this.cedula_cli = cedula_cli;
    }

    public String getTipo_pedido() {
        return tipo_pedido;
    }

    public void setTipo_pedido(String tipo_pedido) {
        this.tipo_pedido = tipo_pedido;
    }
    
}
