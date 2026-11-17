// ---------------------------------------------------------------------
//  CONTRATO OFICIAL del Parcial II (Java). Lo entrega el profesor:
//  LOS ESTUDIANTES NO LO MODIFICAN. El autocalificador usa siempre esta version.
// ---------------------------------------------------------------------

import java.util.ArrayList;
import java.util.List;

/** Usuario de la plataforma. Los nombres de campo van sin tildes ni enie. */
public class Usuario {
    private int idUsuario;
    private String nombre;
    private String correoElectronico;
    private String contrasena;
    private String direccion;
    private String metodoDePago;

    public Usuario() {}

    public Usuario(int idUsuario, String nombre, String correoElectronico, String contrasena, String direccion, String metodoDePago) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.direccion = direccion;
        this.metodoDePago = metodoDePago;
    }


    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getMetodoDePago() { return metodoDePago; }
    public void setMetodoDePago(String metodoDePago) { this.metodoDePago = metodoDePago; }
}
