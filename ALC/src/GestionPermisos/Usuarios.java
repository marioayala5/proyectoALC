package GestionPermisos;

public class Usuarios {

    private Integer id;
    private String usuario;
    private String clave;
    private Boolean estado;
    private Perfiles id_perfil;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Perfiles getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(Perfiles id_perfil) {
        this.id_perfil = id_perfil;
    }
    
    
}
