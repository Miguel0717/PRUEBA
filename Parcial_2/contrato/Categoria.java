// ---------------------------------------------------------------------
//  CONTRATO OFICIAL del Parcial II (Java). Lo entrega el profesor:
//  LOS ESTUDIANTES NO LO MODIFICAN. El autocalificador usa siempre esta version.
// ---------------------------------------------------------------------

import java.util.ArrayList;
import java.util.List;

/** Se declara pero NO se evalua en este parcial. */
public class Categoria {
    private int idCategoria;
    private String nombre;

    public Categoria() {
    }

    public Categoria(int idCategoria, String nombre) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
