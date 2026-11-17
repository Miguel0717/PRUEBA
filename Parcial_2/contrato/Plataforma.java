// =====================================================================
//  Plataforma.java -- CONTRATO OFICIAL del Parcial II (Plataforma Online, Java)
//
//  * Lo entrega el profesor. LOS ESTUDIANTES NO LO MODIFICAN: el autocalificador
//    usa siempre esta version.
//  * Implementen TODOS los metodos en la clase PlataformaImpl
//        public class PlataformaImpl implements Plataforma
//    (archivo PlataformaImpl.java, con constructor sin parametros).
//  * Main.java contiene el login y el menu (unico lugar con Scanner/System.out).
//    Los metodos de PlataformaImpl NO deben leer ni imprimir en consola.
//  * Java 17 o superior. Sin paquete (package) en ninguno de los archivos.
//
//  COLECCIONES OBLIGATORIAS (los tipos de retorno las fijan):
//    ArrayList     -> las listas de datos y los productos de cada carrito
//    HashMap       -> usuarios indexados por correo (login)
//    LinkedHashMap -> productos por id y carritos por usuario (conservan el orden)
//    TreeMap       -> comentarios indexados por fecha (consulta "desde una fecha")
// =====================================================================
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.TreeMap;

public interface Plataforma {

    double TASA_IVA = 0.19;          // impuestos = subtotal * TASA_IVA
    int UMBRAL_STOCK_BAJO = 15;      // "por debajo de 15 unidades"

    // -----------------------------------------------------------------
    //  a) Inicializacion (0.5 pts)
    //  cargar*: devuelven los datos EXACTOS de las tablas del enunciado, en el
    //  mismo orden (usuarios 1..5, productos 1..50, comentarios 1..50).
    //  indexar*: construyen los indices que usa el resto del programa.
    // -----------------------------------------------------------------
    ArrayList<Usuario> cargarUsuarios();

    ArrayList<Producto> cargarProductos();

    ArrayList<Comentario> cargarComentarios();

    /** Clave = correoElectronico. */
    HashMap<String, Usuario> indexarUsuariosPorCorreo(List<Usuario> usuarios);

    /** Clave = idProducto. El orden de iteracion debe ser el de la lista recibida. */
    LinkedHashMap<Integer, Producto> indexarProductosPorId(List<Producto> productos);

    /**
     * Clave = fecha del comentario (convertida con parsearFecha); el valor es la lista de
     * comentarios de ese dia, en el orden de la lista recibida. Los comentarios con
     * fecha invalida se omiten.
     */
    TreeMap<LocalDate, List<Comentario>> indexarComentariosPorFecha(List<Comentario> comentarios);

    // -----------------------------------------------------------------
    //  b) Iniciar sesion (0.5 pts)
    //  Busca por correo en el indice y compara la contrasena EXACTAMENTE
    //  (distingue mayusculas y minusculas). Devuelve el usuario si coincide y
    //  Optional.empty() en cualquier otro caso. Main imprime "Usuario invalido".
    // -----------------------------------------------------------------
    Optional<Usuario> iniciarSesion(Map<String, Usuario> usuariosPorCorreo, String correo, String contrasena);

    // -----------------------------------------------------------------
    //  c) Menu (1.5 pts: 0.5 por cada consulta)
    // -----------------------------------------------------------------

    /** i. Productos con stock ESTRICTAMENTE menor que umbral, en el orden del inventario. No modifica nada. */
    List<Producto> productosBajoStock(Map<Integer, Producto> productosPorId, int umbral);

    /**
     * Convierte "d/M/yyyy" (con o sin cero inicial: "9/06/2025" y "09/06/2025") a LocalDate.
     * Devuelve null si el texto no es una fecha valida ("hola", "", "32/13/2025", null).
     */
    LocalDate parsearFecha(String texto);

    /**
     * ii. Comentarios con fecha IGUAL O POSTERIOR a `fecha`, ordenados por fecha ascendente
     * (dentro de un mismo dia, en el orden del indice). Si `fecha` es null devuelve una lista vacia.
     * Pista: NavigableMap.tailMap(fecha, true).
     */
    List<Comentario> comentariosDesde(NavigableMap<LocalDate, List<Comentario>> comentariosPorFecha, LocalDate fecha);

    /**
     * iii. Una cadena por usuario (mismo orden). Cada cadena debe contener el NOMBRE EN MAYUSCULAS
     * y NO debe contener la contrasena.
     * Formato sugerido: "1 | JUAN PÉREZ | juan.perez@email.com | Carrera 45 #10-20 | Tarjeta de crédito"
     */
    List<String> listarUsuariosFormateados(List<Usuario> usuarios);

    // -----------------------------------------------------------------
    //  d) Carrito de compras (1.5 pts)
    //  El mapa de carritos tiene como CLAVE el idUsuario: un usuario = un carrito.
    // -----------------------------------------------------------------

    /** Id para un carrito nuevo: (mayor idCarrito existente) + 1; si no hay carritos devuelve 1. */
    int siguienteIdCarrito(Map<Integer, CarritoDeCompras> carritosPorUsuario);

    /**
     * Agrega UNA unidad del producto `idProducto` al carrito de `usuario`:
     *  1. Si el producto no existe -> CARRITO_PRODUCTO_INVALIDO (no cambia nada).
     *  2. Si su stock es 0         -> CARRITO_SIN_STOCK (no cambia nada: no se crea carrito, el stock no queda negativo).
     *  3. Si el usuario aun no tiene carrito, se crea uno con idCarrito = siguienteIdCarrito(...) y se guarda
     *     con clave usuario.getIdUsuario(). Las siguientes llamadas del mismo usuario reutilizan ese carrito.
     *  4. Se descuenta 1 del stock del producto EN EL MAPA `productosPorId` recibido.
     *  5. Se agrega el producto a carrito.getProductos() (un elemento por unidad).
     *  6. Se actualiza subtotal (suma de precios) e impuestos = subtotal * TASA_IVA.
     */
    ResultadoCarrito agregarAlCarrito(Map<Integer, CarritoDeCompras> carritosPorUsuario,
                                      Map<Integer, Producto> productosPorId,
                                      Usuario usuario, int idProducto);

    // -----------------------------------------------------------------
    //  e) Listar productos del carrito (1.0 pts)
    //  Productos del carrito cuyo idCarrito sea el indicado, en el orden en que se agregaron.
    //  Si no existe ese carrito, devuelve una lista vacia.
    // -----------------------------------------------------------------
    List<Producto> listarProductosCarrito(Map<Integer, CarritoDeCompras> carritosPorUsuario, int idCarrito);
}
