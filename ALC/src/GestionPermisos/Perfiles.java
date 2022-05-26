package GestionPermisos;

import java.util.List;

public class Perfiles {

    private Integer id;
    private String descripcion;
    private List<Acciones> permisos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Acciones> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<Acciones> permisos) {
        this.permisos = permisos;
    }

    
}
