// ---------------------------------------------------------------------
//  CONTRATO OFICIAL del Parcial II (Java). Lo entrega el profesor:
//  LOS ESTUDIANTES NO LO MODIFICAN. El autocalificador usa siempre esta version.
// ---------------------------------------------------------------------

import java.util.ArrayList;
import java.util.List;

/** Se declara pero NO se evalua en este parcial. */
public class OrdenDeCompra {
    private int idOrden;
    private List<Producto> productos = new ArrayList<>();
    private double subtotal;
    private double impuestos;
    private double envio;
    private double total;

    public OrdenDeCompra() {}


    public int getIdOrden() { return idOrden; }
    public void setIdOrden(int idOrden) { this.idOrden = idOrden; }

    public List<Producto> getProductos() { return productos; }
    public void setProductos(List<Producto> productos) { this.productos = productos; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    public double getImpuestos() { return impuestos; }
    public void setImpuestos(double impuestos) { this.impuestos = impuestos; }

    public double getEnvio() { return envio; }
    public void setEnvio(double envio) { this.envio = envio; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}
