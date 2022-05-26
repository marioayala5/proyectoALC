package GestionPermisos;

import Conexion.ConexionBD;
import GestionABMFK.GestionFuncionarios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionPerfil {

    private final ConexionBD conexion; //me indica el paquete 
    private PreparedStatement ps;
    private String query, mensaje;
    private ResultSet rs;

    public GestionPerfil() {
        this.conexion = new ConexionBD();
    }

    public Boolean agregarRegistro(Perfiles perfiles) {
        try {
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            query = "INSERT INTO public.perfiles(descripcion) VALUES (?);";
            ps = conexion.obtenerConexion().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, perfiles.getDescripcion());

            if (ps.executeUpdate() > 0) {
                Integer idGenerado = 0;
                rs = ps.getGeneratedKeys();
                if (rs.next() == true) {
                    idGenerado = rs.getInt("id");

                    for (Acciones permiso : perfiles.getPermisos()) {
                        query = "INSERT INTO public.permisos(id_perfil, id_accion) VALUES (?, ?);";
                        ps = conexion.obtenerConexion().prepareStatement(query);
                        ps.setInt(1, idGenerado);
                        ps.setInt(2, permiso.getId());
                        if (ps.executeUpdate() <= 0) {
                            mensaje = "Sin resultado";
                            conexion.Transaccion(ConexionBD.TR.CANCELAR);
                            return false;
                        }
                    }
                    conexion.Transaccion(ConexionBD.TR.CONFIRMAR);
                    mensaje = "Operación exitosa";
                    return true;
                } else {
                    mensaje = "Sin resultado";
                    conexion.Transaccion(ConexionBD.TR.CANCELAR);
                    return false;
                }
            } else {
                mensaje = "Sin resultado";
                conexion.Transaccion(ConexionBD.TR.CANCELAR);
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(GestionPerfil.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            mensaje = "Error durante la insercion del registro";
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestionPerfil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String getMensaje() {
        return mensaje;
    }
}
