// ---------------------------------------------------------------------
//  CONTRATO OFICIAL del Parcial II (Java). Lo entrega el profesor:
//  LOS ESTUDIANTES NO LO MODIFICAN. El autocalificador usa siempre esta version.
// ---------------------------------------------------------------------

import java.util.ArrayList;
import java.util.List;

/** Comentario. producto y usuario guardan el NOMBRE (texto) como en la tabla; fecha en formato d/M/yyyy, p. ej. "1/05/2025". */
public class Comentario {
    private int idComentario;
    private String producto;
    private String usuario;
    private String comentario;
    private String fecha;

    public Comentario() {}

    public Comentario(int idComentario, String producto, String usuario, String comentario, String fecha) {
        this.idComentario = idComentario;
        this.producto = producto;
        this.usuario = usuario;
        this.comentario = comentario;
        this.fecha = fecha;
    }


    public int getIdComentario() { return idComentario; }
    public void setIdComentario(int idComentario) { this.idComentario = idComentario; }

    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
}
