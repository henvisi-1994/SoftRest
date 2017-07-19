package SoftRest.controladores;

/**
 *
 * @author Paul Torres
 */
public class NodoProveedores<T> {

    public T info;
    public NodoProveedores sgte;

    public NodoProveedores(T info) {
        this.info = info;
        this.sgte = null;
    }
}
