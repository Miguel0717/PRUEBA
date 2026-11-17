// ---------------------------------------------------------------------
//  CONTRATO OFICIAL del Parcial II (Java). Lo entrega el profesor:
//  LOS ESTUDIANTES NO LO MODIFICAN. El autocalificador usa siempre esta version.
// ---------------------------------------------------------------------

import java.util.ArrayList;
import java.util.List;

/** Carrito de compras: UN carrito por usuario. productos tiene un elemento por unidad agregada. */
public class CarritoDeCompras {
    private int idCarrito;
    private Usuario usuario;
    private List<Producto> productos = new ArrayList<>();
    private double subtotal;
    private double impuestos;

    public CarritoDeCompras() {}

    public CarritoDeCompras(int idCarrito, Usuario usuario) {
        this.idCarrito = idCarrito;
        this.usuario = usuario;
    }

    public int getIdCarrito() { return idCarrito; }
    public void setIdCarrito(int idCarrito) { this.idCarrito = idCarrito; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public List<Producto> getProductos() { return productos; }
    public void setProductos(List<Producto> productos) { this.productos = productos; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    public double getImpuestos() { return impuestos; }
    public void setImpuestos(double impuestos) { this.impuestos = impuestos; }
}
