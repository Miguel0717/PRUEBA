// =====================================================================
//  Main.java
//  Login y menu. Es el UNICO lugar donde se usa Scanner y System.out.
//  Toda la logica la hace PlataformaImpl; aqui solo se pide y se muestra.
// =====================================================================
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Plataforma plataforma = new PlataformaImpl();

        // ---- 1. Cargar los datos y crear los indices ----
        ArrayList<Usuario> usuarios = plataforma.cargarUsuarios();
        ArrayList<Producto> productos = plataforma.cargarProductos();
        ArrayList<Comentario> comentarios = plataforma.cargarComentarios();

        HashMap<String, Usuario> usuariosPorCorreo = plataforma.indexarUsuariosPorCorreo(usuarios);
        LinkedHashMap<Integer, Producto> productosPorId = plataforma.indexarProductosPorId(productos);
        TreeMap<LocalDate, List<Comentario>> comentariosPorFecha = plataforma.indexarComentariosPorFecha(comentarios);
        LinkedHashMap<Integer, CarritoDeCompras> carritos = new LinkedHashMap<>(); // clave = idUsuario

        // ---- 2. Login: pide correo y contrasena (si falla, termina) ----
        System.out.println("===== INICIO DE SESION =====");
        System.out.print("Ingrese su correo electronico: ");
        if (!sc.hasNext()) { // fin de entrada
            System.out.println("Usuario invalido");
            sc.close();
            return;
        }
        String correo = sc.next();

        System.out.print("Ingrese su contrasena: ");
        if (!sc.hasNext()) {
            System.out.println("Usuario invalido");
            sc.close();
            return;
        }
        String contrasena = sc.next();

        Optional<Usuario> sesion = plataforma.iniciarSesion(usuariosPorCorreo, correo, contrasena);
        if (sesion.isEmpty()) {
            System.out.println("Usuario invalido");
            sc.close();
            return;
        }
        Usuario usuario = sesion.get();
        System.out.println("Bienvenido, " + usuario.getNombre() + "!");
        System.out.println();

        // ---- 3. Menu en ciclo hasta elegir 0 ----
        int opcion = -1;
        while (opcion != 0) {
            mostrarMenu();

            // Si ya no hay mas entrada, salimos (evita ciclo infinito).
            if (!sc.hasNext()) {
                break;
            }

            // Se lee como texto y se convierte; si no es numero, avisa y repite el menu.
            String texto = sc.next();
            try {
                opcion = Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("Opcion invalida");
                opcion = -1;
                continue;
            }

            switch (opcion) {

                case 1: // Productos con stock bajo (< 15)
                    for (Producto p : plataforma.productosBajoStock(productosPorId, Plataforma.UMBRAL_STOCK_BAJO)) {
                        System.out.println(p.getIdProducto() + " | " + p.getNombre()
                                + " | " + p.getPrecio() + " | stock: " + p.getStock());
                    }
                    break;

                case 2: // Comentarios desde una fecha
                    if (!sc.hasNext()) {
                        opcion = 0; // sin mas entrada: salir
                        break;
                    }
                    LocalDate fecha = plataforma.parsearFecha(sc.next());
                    if (fecha == null) {
                        System.out.println("Fecha invalida");
                    } else {
                        for (Comentario c : plataforma.comentariosDesde(comentariosPorFecha, fecha)) {
                            System.out.println(c.getFecha() + " | " + c.getProducto()
                                    + " | " + c.getUsuario() + " | " + c.getComentario());
                        }
                    }
                    break;

                case 3: // Listar usuarios
                    for (String linea : plataforma.listarUsuariosFormateados(usuarios)) {
                        System.out.println(linea);
                    }
                    break;

                case 4: // Adicionar producto al carrito
                    if (!sc.hasNext()) {
                        opcion = 0;
                        break;
                    }
                    int idProducto;
                    try {
                        idProducto = Integer.parseInt(sc.next());
                    } catch (NumberFormatException e) {
                        System.out.println("Id invalido");
                        break;
                    }
                    ResultadoCarrito resultado = plataforma.agregarAlCarrito(carritos, productosPorId, usuario,
                            idProducto);
                    if (resultado == ResultadoCarrito.CARRITO_OK) {
                        System.out.println("Producto agregado al carrito");
                    } else if (resultado == ResultadoCarrito.CARRITO_PRODUCTO_INVALIDO) {
                        System.out.println("Producto invalido");
                    } else {
                        System.out.println("Producto sin stock");
                    }
                    break;

                case 5: // Listar productos del carrito con subtotal e impuestos
                    CarritoDeCompras carrito = carritos.get(usuario.getIdUsuario());
                    if (carrito == null) {
                        System.out.println("Carrito vacio");
                    } else {
                        for (Producto p : plataforma.listarProductosCarrito(carritos, carrito.getIdCarrito())) {
                            System.out.println(p.getIdProducto() + " | " + p.getNombre() + " | " + p.getPrecio());
                        }
                        System.out.println("Subtotal: " + carrito.getSubtotal());
                        System.out.println("Impuestos: " + carrito.getImpuestos());
                    }
                    break;

                case 0: // Salir
                    System.out.println("Hasta luego");
                    break;

                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
        sc.close();
    }

    // Muestra las opciones del menu.
    private static void mostrarMenu() {
        System.out.println("===== MENU =====");
        System.out.println("1. Productos con stock bajo");
        System.out.println("2. Comentarios desde una fecha (dd/mm/yyyy)");
        System.out.println("3. Listar usuarios");
        System.out.println("4. Adicionar producto al carrito");
        System.out.println("5. Listar productos del carrito");
        System.out.println("0. Salir");
        System.out.print("Opcion: ");
    }
}
