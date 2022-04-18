package ABM;

import Conexion.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionLunes {

    private final Conexion.ConexionBD conexion; //me indica el paquete 
    private PreparedStatement ps;
    private String query, mensaje;

    public GestionLunes() {
        conexion = new ConexionBD();
    }

    public boolean agregarRegistro(Lunes registro) {
        try {
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            query = "INSERT INTO public.lunes(descripcion, comentario)\n"
                    + "VALUES (?, ?)";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setString(1, registro.getDescripcion());
            ps.setString(2, registro.getComentario());
            if (ps.executeUpdate() > 0) {
                mensaje = "Registro Insertado en forma exitosa!";
                conexion.Transaccion(ConexionBD.TR.CONFIRMAR);
                return true;
            } else {
                mensaje = "No se pudo realizar el registro";
                conexion.Transaccion(ConexionBD.TR.CANCELAR);
                return false;
            }

        } catch (SQLException ex) {
            mensaje = "Error durante la Inserción del Registro " + ex.getMessage();
            Logger.getLogger(GestionLunes.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            return false;

        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestionLunes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean modificarRegistro(Lunes registro) {
        try {
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            query = "UPDATE public.lunes SET descripcion=?, comentario=? WHERE id=?";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setString(1, registro.getDescripcion());
            ps.setString(2, registro.getComentario());
            ps.setInt(3, registro.getId());
            if (ps.executeUpdate() > 0) {
                mensaje = "Registro Actualizado en forma exitosa!";
                conexion.Transaccion(ConexionBD.TR.CONFIRMAR);
                return true;
            } else {
                mensaje = "No se pudo realizar la actualizacion";
                conexion.Transaccion(ConexionBD.TR.CANCELAR);
                return false;
            }

        } catch (SQLException ex) {
            mensaje = "Error durante la Actualizacion del Registro " + ex.getMessage();
            Logger.getLogger(GestionLunes.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            return false;

        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestionLunes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean eliminarRegistro(Lunes registro) {
        try {
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            query = "DELETE FROM public.lunes WHERE id =?";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setInt(1, registro.getId());
            if (ps.executeUpdate() > 0) {
                mensaje = "Registro eliminado en forma exitosa!";
                conexion.Transaccion(ConexionBD.TR.CONFIRMAR);
                return true;
            } else {
                mensaje = "No se pudo realizar la eliminacion";
                conexion.Transaccion(ConexionBD.TR.CANCELAR);
                return false;
            }

        } catch (SQLException ex) {
            mensaje = "Error durante la eliminacion del Registro " + ex.getMessage();
            Logger.getLogger(GestionLunes.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            return false;

        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestionLunes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String getMensaje() {
        return mensaje;
    }
}
