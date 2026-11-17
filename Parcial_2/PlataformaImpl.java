// =====================================================================
//  PlataformaImpl.java
//  Toda la logica de la plataforma de compras (Parcial II).
//  Aqui NO se usa Scanner ni System.out: eso solo va en Main.java.
//
//  Colecciones que se usan:
//    ArrayList     -> listas de datos y productos de cada carrito
//    HashMap       -> usuarios por correo (para el login)
//    LinkedHashMap -> productos por id (conserva el orden) y carritos por idUsuario
//    TreeMap       -> comentarios por fecha (siempre ordenados por fecha)
// =====================================================================
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.TreeMap;

public class PlataformaImpl implements Plataforma {

    // Formato de fecha d/M/yyyy: acepta "1/05/2025" y "01/05/2025".
    // STRICT hace que fechas imposibles (31/02/2025) den error en vez de "corregirse".
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("d/M/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);

    public PlataformaImpl() {
    }

    // =================================================================
    //  a) INICIALIZACION: cargar datos
    // =================================================================

    // Crea la lista con los 5 usuarios de la tabla del enunciado (mismo orden).
    @Override
    public ArrayList<Usuario> cargarUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<>();
        lista.add(new Usuario(1, "Juan Pérez", "juan.perez@email.com", "Qwerty123", "Carrera 45 #10-20",
                        "Tarjeta de crédito"));
        lista.add(new Usuario(2, "Ana Gómez", "ana.gomez@email.com", "Pass456", "Calle 21 #35-50", "PayPal"));
        lista.add(new Usuario(3, "Carlos Ruiz", "carlos.ruiz@email.com", "Segura789", "Avenida Principal #100",
                        "Transferencia bancaria"));
        lista.add(new Usuario(4, "Sofía Martínez", "sofia.martinez@email.com", "Clave987", "Calle 8 #20-30",
                        "Efectivo"));
        lista.add(new Usuario(5, "Diego Fernández", "diego.fernandez@email.com", "Contra654",
                        "Carrera 77 #40-60", "Tarjeta débito"));
        return lista;
    }

    // Crea la lista con los 50 productos del inventario (mismo orden).
    @Override
    public ArrayList<Producto> cargarProductos() {
        ArrayList<Producto> l = new ArrayList<>();
        l.add(new Producto(1, "Laptop", "Portátil con pantalla Full HD y SSD de 512GB", 89999, 10));
        l.add(new Producto(2, "Smartphone", "Teléfono con cámara de 108MP y carga rápida", 49950, 20));
        l.add(new Producto(3, "Tablet", "Dispositivo con pantalla táctil de 10 pulgadas", 29999, 15));
        l.add(new Producto(4, "Auriculares", "Audífonos inalámbricos con cancelación de ruido", 12999, 25));
        l.add(new Producto(5, "Teclado", "Teclado mecánico con iluminación RGB", 8999, 30));
        l.add(new Producto(6, "Mouse", "Ratón inalámbrico con sensor óptico de alta precisión", 5999, 50));
        l.add(new Producto(7, "Monitor", "Pantalla LED 4K de 27 pulgadas", 49900, 12));
        l.add(new Producto(8, "Impresora", "Láser multifuncional con Wi-Fi", 17999, 18));
        l.add(new Producto(9, "Cámara", "Cámara digital con lente profesional", 79999, 8));
        l.add(new Producto(10, "Smartwatch", "Reloj inteligente con GPS y monitoreo cardíaco", 19999, 22));
        l.add(new Producto(11, "Silla Gamer", "Silla ergonómica ajustable con soporte lumbar", 29999, 14));
        l.add(new Producto(12, "Microondas", "Horno microondas con múltiples funciones", 12999, 40));
        l.add(new Producto(13, "Refrigerador", "Frigorífico doble puerta con sistema No Frost", 119999, 5));
        l.add(new Producto(14, "Lavadora", "Lavadora automática con capacidad de 10kg", 59999, 7));
        l.add(new Producto(15, "Cafetera", "Cafetera express con vaporizador de leche", 14999, 35));
        l.add(new Producto(16, "Drone", "Drone con cámara 4K y estabilizador", 69999, 9));
        l.add(new Producto(17, "Bocina Bluetooth", "Altavoz portátil con sonido envolvente", 8999, 33));
        l.add(new Producto(18, "Videocámara", "Videocámara profesional con grabación en 4K", 99999, 6));
        l.add(new Producto(19, "TV LED", "Televisor inteligente de 55 pulgadas con HDR", 74999, 11));
        l.add(new Producto(20, "Batería Externa", "Batería de 20000mAh con carga rápida", 3999, 45));
        l.add(new Producto(21, "Disco Duro", "Disco duro externo de 2TB", 12999, 28));
        l.add(new Producto(22, "Memoria USB", "Pendrive de 128GB", 2999, 60));
        l.add(new Producto(23, "Router", "Router Wi-Fi 6 de alta velocidad", 19999, 16));
        l.add(new Producto(24, "Joystick", "Control inalámbrico para videojuegos", 7999, 20));
        l.add(new Producto(25, "Fuente de Poder", "Fuente de alimentación para PC de 750W", 8999, 17));
        l.add(new Producto(26, "SSD", "Unidad de almacenamiento SSD de 1TB", 14999, 32));
        l.add(new Producto(27, "Altavoces", "Par de bocinas estéreo con subwoofer", 13999, 23));
        l.add(new Producto(28, "Webcam", "Cámara web Full HD con micrófono integrado", 6999, 37));
        l.add(new Producto(29, "Procesador", "CPU Intel i7 de última generación", 34999, 9));
        l.add(new Producto(30, "Motherboard", "Placa base compatible con procesadores modernos", 19999, 13));
        l.add(new Producto(31, "Memoria RAM", "Módulo de RAM DDR4 de 16GB", 7999, 41));
        l.add(new Producto(32, "Fuente Solar", "Panel solar portátil con batería integrada", 24999, 4));
        l.add(new Producto(33, "Control Remoto", "Mando universal para TV y dispositivos", 2499, 50));
        l.add(new Producto(34, "Termostato", "Termostato digital programable", 9999, 22));
        l.add(new Producto(35, "Smart Lock", "Cerradura electrónica con huella digital", 19999, 6));
        l.add(new Producto(36, "Proyector", "Proyector LED con resolución Full HD", 29999, 12));
        l.add(new Producto(37, "Switch Ethernet", "Switch de red de 8 puertos", 5999, 38));
        l.add(new Producto(38, "Reloj Digital", "Reloj inteligente con pantalla AMOLED", 8999, 26));
        l.add(new Producto(39, "Luces LED", "Tiras LED RGB con control remoto", 3999, 55));
        l.add(new Producto(40, "Estabilizador", "Estabilizador de voltaje para dispositivos electrónicos",
                        15999, 10));
        l.add(new Producto(41, "Cargador Inalámbrico", "Base de carga inalámbrica rápida", 4999, 30));
        l.add(new Producto(42, "HDD Externo", "Disco duro portátil de 4TB", 17999, 15));
        l.add(new Producto(43, "Micrófono", "Micrófono profesional para grabación", 14999, 7));
        l.add(new Producto(44, "Altavoz Inteligente", "Asistente de voz con altavoz integrado", 12999, 20));
        l.add(new Producto(45, "Antena Wi-Fi", "Amplificador de señal inalámbrico", 7999, 33));
        l.add(new Producto(46, "Climatizador", "Aire acondicionado portátil con control remoto", 29999, 5));
        l.add(new Producto(47, "Raspberry Pi", "Kit de desarrollo con Raspberry Pi 4", 12999, 19));
        l.add(new Producto(48, "Capturadora", "Placa de captura de video en alta resolución", 19999, 8));
        l.add(new Producto(49, "Smart Plug", "Enchufe inteligente compatible con asistentes virtuales", 3999,
                        42));
        l.add(new Producto(50, "Timbre Inteligente", "Timbre con cámara y conexión a Wi-Fi", 14999, 10));
        return l;
    }

    // Crea la lista con los 50 comentarios (la fecha se guarda como texto d/M/yyyy).
    @Override
    public ArrayList<Comentario> cargarComentarios() {
        ArrayList<Comentario> l = new ArrayList<>();
        l.add(new Comentario(1, "Laptop", "Juan Pérez", "Excelente rendimiento; muy rápida. ¡Me encanta!",
                        "1/05/2025"));
        l.add(new Comentario(2, "Smartphone", "Ana Gómez", "Buena cámara pero la batería dura poco.",
                        "3/05/2025"));
        l.add(new Comentario(3, "Tablet", "Carlos Ruiz", "No me gustó; pantalla de baja calidad.",
                        "5/05/2025"));
        l.add(new Comentario(4, "Auriculares", "Sofía Martínez",
                        "Sonido aceptable pero el material parece frágil.", "6/05/2025"));
        l.add(new Comentario(5, "Teclado", "Diego Fernández", "Muy buen teclado mecánico; excelente respuesta.",
                        "8/05/2025"));
        l.add(new Comentario(6, "Mouse", "Ana Gómez", "El sensor no es tan preciso como esperaba.",
                        "10/05/2025"));
        l.add(new Comentario(7, "Monitor", "Carlos Ruiz",
                        "Colores vibrantes y buena resolución. Muy satisfecho.", "12/05/2025"));
        l.add(new Comentario(8, "Impresora", "Juan Pérez", "Tarda mucho en imprimir; no me convence.",
                        "13/05/2025"));
        l.add(new Comentario(9, "Cámara", "Sofía Martínez", "Increíble calidad de imagen; fotos súper nítidas.",
                        "15/05/2025"));
        l.add(new Comentario(10, "Smartwatch", "Diego Fernández",
                        "Buena batería; pero la pantalla no es muy brillante.", "18/05/2025"));
        l.add(new Comentario(11, "Silla Gamer", "Lucía Rodríguez",
                        "Comodidad espectacular; perfecto para largas sesiones de juego.", "20/05/2025"));
        l.add(new Comentario(12, "Microondas", "Andrés Ramírez", "Calienta bien pero hace mucho ruido.",
                        "22/05/2025"));
        l.add(new Comentario(13, "Refrigerador", "María García", "Espacioso y enfría rápido; muy recomendado.",
                        "24/05/2025"));
        l.add(new Comentario(14, "Lavadora", "Javier Martínez", "Lava bien pero el ciclo es muy largo.",
                        "26/05/2025"));
        l.add(new Comentario(15, "Cafetera", "Carolina López", "Hace café delicioso; fácil de usar.",
                        "28/05/2025"));
        l.add(new Comentario(16, "Drone", "Daniel Castro", "Muy divertido pero la batería dura poco.",
                        "30/05/2025"));
        l.add(new Comentario(17, "Bocina Bluetooth", "Paola Herrera",
                        "Sonido potente y buena conexión Bluetooth.", "1/06/2025"));
        l.add(new Comentario(18, "Videocámara", "Esteban Rojas", "Perfecta para grabaciones profesionales.",
                        "3/06/2025"));
        l.add(new Comentario(19, "TV LED", "Fernanda Sánchez",
                        "Imagen excelente pero el sonido podría mejorar.", "5/06/2025"));
        l.add(new Comentario(20, "Batería Externa", "Camilo Torres", "Carga bien pero es un poco pesada.",
                        "7/06/2025"));
        l.add(new Comentario(21, "Disco Duro", "Gabriela Suárez",
                        "Gran capacidad de almacenamiento; funciona rápido.", "9/06/2025"));
        l.add(new Comentario(22, "Memoria USB", "Raúl Espinosa",
                        "Buen tamaño pero la velocidad de transferencia es baja.", "11/06/2025"));
        l.add(new Comentario(23, "Router", "Verónica Mendoza", "Señal potente; cubre toda la casa.",
                        "13/06/2025"));
        l.add(new Comentario(24, "Joystick", "Fabio Jiménez", "Comodo y resistente; ideal para gaming.",
                        "15/06/2025"));
        l.add(new Comentario(25, "Fuente de Poder", "Ricardo Vargas",
                        "Funciona bien pero los cables son muy cortos.", "17/06/2025"));
        l.add(new Comentario(26, "SSD", "Silvia Gómez", "Velocidad increíble; mi PC va mucho más rápido ahora.",
                        "19/06/2025"));
        l.add(new Comentario(27, "Altavoces", "Martín Aguilar", "Sonido envolvente; muy buena compra.",
                        "21/06/2025"));
        l.add(new Comentario(28, "Webcam", "Valentina Pérez", "Imagen clara pero el micrófono es deficiente.",
                        "23/06/2025"));
        l.add(new Comentario(29, "Procesador", "José Ramírez",
                        "Rendimiento impecable; ideal para gaming y diseño.", "25/06/2025"));
        l.add(new Comentario(30, "Motherboard", "Natalia Correa",
                        "Buenas prestaciones pero la instalación fue complicada.", "27/06/2025"));
        l.add(new Comentario(31, "Memoria RAM", "Julio Fernández",
                        "Expande muy bien el rendimiento del sistema.", "29/06/2025"));
        l.add(new Comentario(32, "Fuente Solar", "Amanda Castro",
                        "Energía confiable pero la batería es pequeña.", "1/07/2025"));
        l.add(new Comentario(33, "Control Remoto", "Pedro Duarte",
                        "Fácil de usar; reconoce muchos dispositivos.", "3/07/2025"));
        l.add(new Comentario(34, "Termostato", "Isabela Medina",
                        "Regula bien la temperatura; intuitivo de usar.", "5/07/2025"));
        l.add(new Comentario(35, "Smart Lock", "Oscar Rodríguez",
                        "Seguridad y tecnología en un solo dispositivo.", "7/07/2025"));
        l.add(new Comentario(36, "Proyector", "Cristina Vargas", "Imagen nítida pero requiere una sala oscura.",
                        "9/07/2025"));
        l.add(new Comentario(37, "Switch Ethernet", "Mario Hernández", "Buena velocidad de conexión; estable.",
                        "11/07/2025"));
        l.add(new Comentario(38, "Reloj Digital", "Sofía Ramírez",
                        "Pantalla atractiva pero la batería dura poco.", "13/07/2025"));
        l.add(new Comentario(39, "Luces LED", "Andrea Gutiérrez", "Buenas opciones de colores; buen diseño.",
                        "15/07/2025"));
        l.add(new Comentario(40, "Estabilizador", "Pablo Medina", "Protege bien contra variaciones de voltaje.",
                        "17/07/2025"));
        l.add(new Comentario(41, "Cargador Inalámbrico", "Patricia López",
                        "Carga rápido pero requiere posicionamiento preciso.", "19/07/2025"));
        l.add(new Comentario(42, "HDD Externo", "Gonzalo Espinoza", "Mucho espacio; resistente y confiable.",
                        "21/07/2025"));
        l.add(new Comentario(43, "Micrófono", "Elena Herrera",
                        "Calidad de sonido profesional; ideal para podcast.", "23/07/2025"));
        l.add(new Comentario(44, "Altavoz Inteligente", "Diego Soto",
                        "Responde bien a comandos de voz; útil en casa.", "25/07/2025"));
        l.add(new Comentario(45, "Antena Wi-Fi", "Miguel Rojas",
                        "Amplifica bien la señal pero el rango es limitado.", "27/07/2025"));
        l.add(new Comentario(46, "Climatizador", "Estefanía Carrillo", "Enfría rápido pero es algo ruidoso.",
                        "29/07/2025"));
        l.add(new Comentario(47, "Raspberry Pi", "Manuel Vargas",
                        "Perfecto para proyectos electrónicos y programación.", "31/07/2025"));
        l.add(new Comentario(48, "Capturadora", "Luisa Mejía",
                        "Ideal para streaming y grabaciones en alta calidad.", "2/08/2025"));
        l.add(new Comentario(49, "Smart Plug", "Victoria Torres",
                        "Muy práctico; fácil de conectar y configurar.", "4/08/2025"));
        l.add(new Comentario(50, "Timbre Inteligente", "Federico Montoya", "Buena cámara; útil para seguridad.",
                        "6/08/2025"));
        return l;
    }

    // =================================================================
    //  a) INICIALIZACION: construir los indices (mapas)
    // =================================================================

    // Clave = correo. Sirve para buscar un usuario rapido al iniciar sesion.
    @Override
    public HashMap<String, Usuario> indexarUsuariosPorCorreo(List<Usuario> usuarios) {
        HashMap<String, Usuario> mapa = new HashMap<>();
        if (usuarios == null) {
            return mapa;
        }
        for (Usuario u : usuarios) {
            mapa.put(u.getCorreoElectronico(), u);
        }
        return mapa;
    }

    // Clave = idProducto. LinkedHashMap mantiene el mismo orden de la lista.
    @Override
    public LinkedHashMap<Integer, Producto> indexarProductosPorId(List<Producto> productos) {
        LinkedHashMap<Integer, Producto> mapa = new LinkedHashMap<>();
        if (productos == null) {
            return mapa;
        }
        for (Producto p : productos) {
            mapa.put(p.getIdProducto(), p);
        }
        return mapa;
    }

    // Clave = fecha (LocalDate). Valor = lista de comentarios de ese dia.
    // Si la fecha del comentario es invalida, el comentario se omite.
    @Override
    public TreeMap<LocalDate, List<Comentario>> indexarComentariosPorFecha(List<Comentario> comentarios) {
        TreeMap<LocalDate, List<Comentario>> mapa = new TreeMap<>();
        if (comentarios == null) {
            return mapa;
        }
        for (Comentario c : comentarios) {
            LocalDate fecha = parsearFecha(c.getFecha());
            if (fecha == null) {
                continue; // fecha invalida: se salta este comentario
            }
            // Si ese dia aun no tiene lista, se crea; luego se agrega el comentario.
            if (!mapa.containsKey(fecha)) {
                mapa.put(fecha, new ArrayList<>());
            }
            mapa.get(fecha).add(c);
        }
        return mapa;
    }

    // =================================================================
    //  b) INICIAR SESION
    // =================================================================

    // Busca el correo en el mapa y compara la contrasena con equals()
    // (equals distingue mayusculas de minusculas). Si algo falla -> Optional.empty().
    @Override
    public Optional<Usuario> iniciarSesion(Map<String, Usuario> usuariosPorCorreo, String correo,
            String contrasena) {
        if (usuariosPorCorreo == null || correo == null || contrasena == null) {
            return Optional.empty();
        }
        Usuario u = usuariosPorCorreo.get(correo);
        if (u != null && contrasena.equals(u.getContrasena())) {
            return Optional.of(u);
        }
        return Optional.empty();
    }

    // =================================================================
    //  c) CONSULTAS DEL MENU
    // =================================================================

    // i. Productos con stock ESTRICTAMENTE menor que el umbral (stock 15 no entra).
    //    Solo lee, no modifica nada.
    @Override
    public List<Producto> productosBajoStock(Map<Integer, Producto> productosPorId, int umbral) {
        List<Producto> resultado = new ArrayList<>();
        if (productosPorId == null) {
            return resultado;
        }
        for (Producto p : productosPorId.values()) {
            if (p.getStock() < umbral) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    // Convierte texto "d/M/yyyy" a LocalDate. Si no es una fecha valida devuelve null.
    @Override
    public LocalDate parsearFecha(String texto) {
        if (texto == null) {
            return null;
        }
        try {
            return LocalDate.parse(texto.trim(), FORMATO);
        } catch (Exception e) {
            return null;
        }
    }

    // ii. Comentarios desde una fecha (inclusive) en adelante.
    //     tailMap(fecha, true) trae las entradas con clave >= fecha, ya ordenadas.
    @Override
    public List<Comentario> comentariosDesde(NavigableMap<LocalDate, List<Comentario>> comentariosPorFecha,
            LocalDate fecha) {
        List<Comentario> resultado = new ArrayList<>();
        if (comentariosPorFecha == null || fecha == null) {
            return resultado;
        }
        for (List<Comentario> delDia : comentariosPorFecha.tailMap(fecha, true).values()) {
            resultado.addAll(delDia);
        }
        return resultado;
    }

    // iii. Una linea por usuario con el nombre en MAYUSCULA y SIN la contrasena.
    @Override
    public List<String> listarUsuariosFormateados(List<Usuario> usuarios) {
        List<String> lineas = new ArrayList<>();
        if (usuarios == null) {
            return lineas;
        }
        for (Usuario u : usuarios) {
            lineas.add(u.getIdUsuario() + " | " + u.getNombre().toUpperCase()
                    + " | " + u.getCorreoElectronico()
                    + " | " + u.getDireccion()
                    + " | " + u.getMetodoDePago());
        }
        return lineas;
    }

    // =================================================================
    //  d) CARRITO DE COMPRAS
    // =================================================================

    // Id para un carrito nuevo = (mayor idCarrito existente) + 1. Si no hay carritos -> 1.
    @Override
    public int siguienteIdCarrito(Map<Integer, CarritoDeCompras> carritosPorUsuario) {
        int mayor = 0;
        if (carritosPorUsuario != null) {
            for (CarritoDeCompras c : carritosPorUsuario.values()) {
                if (c.getIdCarrito() > mayor) {
                    mayor = c.getIdCarrito();
                }
            }
        }
        return mayor + 1;
    }

    // Agrega UNA unidad de un producto al carrito del usuario.
    @Override
    public ResultadoCarrito agregarAlCarrito(Map<Integer, CarritoDeCompras> carritosPorUsuario,
            Map<Integer, Producto> productosPorId, Usuario usuario, int idProducto) {

        // 1. El producto debe existir.
        Producto producto = productosPorId.get(idProducto);
        if (producto == null) {
            return ResultadoCarrito.CARRITO_PRODUCTO_INVALIDO;
        }

        // 2. Debe haber stock. Si no hay, NO se crea carrito ni se cambia nada.
        if (producto.getStock() <= 0) {
            return ResultadoCarrito.CARRITO_SIN_STOCK;
        }

        // 3. Buscar el carrito del usuario (la clave del mapa es su idUsuario).
        //    Si no tiene, se crea uno nuevo con el siguiente id.
        CarritoDeCompras carrito = carritosPorUsuario.get(usuario.getIdUsuario());
        if (carrito == null) {
            carrito = new CarritoDeCompras(siguienteIdCarrito(carritosPorUsuario), usuario);
            carritosPorUsuario.put(usuario.getIdUsuario(), carrito);
        }

        // 4. Agregar el producto al carrito y descontar 1 del stock.
        carrito.getProductos().add(producto);
        producto.setStock(producto.getStock() - 1);

        // 5. Recalcular subtotal (suma de precios) e impuestos (subtotal * 19%).
        double subtotal = 0;
        for (Producto p : carrito.getProductos()) {
            subtotal += p.getPrecio();
        }
        carrito.setSubtotal(subtotal);
        carrito.setImpuestos(subtotal * TASA_IVA);

        return ResultadoCarrito.CARRITO_OK;
    }

    // =================================================================
    //  e) LISTAR PRODUCTOS DEL CARRITO
    // =================================================================

    // Busca el carrito con ese idCarrito y devuelve sus productos en el orden en que
    // se agregaron. Si no existe ese carrito, devuelve una lista vacia.
    @Override
    public List<Producto> listarProductosCarrito(Map<Integer, CarritoDeCompras> carritosPorUsuario, int idCarrito) {
        List<Producto> resultado = new ArrayList<>();
        if (carritosPorUsuario == null) {
            return resultado;
        }
        for (CarritoDeCompras c : carritosPorUsuario.values()) {
            if (c.getIdCarrito() == idCarrito) {
                resultado.addAll(c.getProductos());
                break;
            }
        }
        return resultado;
    }
}
