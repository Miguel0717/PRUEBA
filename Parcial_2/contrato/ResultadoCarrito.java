// ---------------------------------------------------------------------
//  CONTRATO OFICIAL del Parcial II (Java). Lo entrega el profesor:
//  LOS ESTUDIANTES NO LO MODIFICAN. El autocalificador usa siempre esta version.
// ---------------------------------------------------------------------

/** Resultado de agregarAlCarrito. */
public enum ResultadoCarrito {
    CARRITO_OK,                 // se agrego una unidad
    CARRITO_PRODUCTO_INVALIDO,  // no existe un producto con ese id
    CARRITO_SIN_STOCK           // el producto existe pero stock == 0
}
