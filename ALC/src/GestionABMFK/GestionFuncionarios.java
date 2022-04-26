package GestionABMFK;

import Conexion.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionFuncionarios {

    private final ConexionBD conexion; //me indica el paquete 
    private PreparedStatement ps;
    private String query, mensaje;
    private ResultSet rs;

    public GestionFuncionarios() {
        this.conexion = new ConexionBD();
    }

    public boolean agregarRegistro(Funcionarios funcionario) {
        try {
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            query = "INSERT INTO public.funcionarios(nro_ci, nombres, apellidos, id_ciudad, id_cargo)VALUES (?, ?, ?, ?, ?);";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setInt(1, funcionario.getNroCi());
            ps.setString(2, funcionario.getNombres());
            ps.setString(3, funcionario.getApellidos());
            ps.setInt(4, funcionario.getCiudad().getId());
            ps.setInt(5, funcionario.getCargo().getId());

            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(ConexionBD.TR.CONFIRMAR);
                mensaje = "Operación exitosa";
                return true;
            } else {
                conexion.Transaccion(ConexionBD.TR.CANCELAR);
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(GestionFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            mensaje = "Error durante la insercion del registro";
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestionFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean modificarRegistro(Funcionarios funcionario) {
        try {
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            query = "UPDATE public.funcionarios SET nro_ci=?, nombres=?, apellidos=?, id_ciudad=?, id_cargo=? WHERE id=?;";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setInt(1, funcionario.getNroCi());
            ps.setString(2, funcionario.getNombres());
            ps.setString(3, funcionario.getApellidos());
            ps.setInt(4, funcionario.getCiudad().getId());
            ps.setInt(5, funcionario.getCargo().getId());
            ps.setInt(6, funcionario.getId());

            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(ConexionBD.TR.CONFIRMAR);
                mensaje = "Operacion exitosa";
                return true;
            } else {
                conexion.Transaccion(ConexionBD.TR.CANCELAR);
                mensaje = "Error durante la modificacion del registro";
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(GestionFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestionFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean eliminarRegistro(Funcionarios funcionario) {
        try {
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            query = "DELETE FROM public.funcionarios WHERE id =?;";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setInt(1, funcionario.getId());

            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(ConexionBD.TR.CONFIRMAR);
                mensaje = "Operacion Exitosa";
                return true;
            } else {
                conexion.Transaccion(ConexionBD.TR.CANCELAR);
                mensaje = "Error durante la eliminacion del registro";
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(GestionFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestionFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<Funcionarios> recuperarFuncionario() {
        try {
            List<Funcionarios> lista = null;
            Funcionarios funcionario = null;
            query = "SELECT id, nro_ci, nombres, apellidos, id_ciudad, id_cargo FROM public.funcionarios;";
            ps = conexion.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            lista = new ArrayList<>();

            while (rs.next()) {
                funcionario = new Funcionarios();
                funcionario.setId(rs.getInt("id"));
                funcionario.setNroCi(rs.getInt("nro_ci"));
                funcionario.setNombres(rs.getString("nombres"));
                funcionario.setApellidos(rs.getString("apellidos"));
                funcionario.setCiudad(new Ciudad(rs.getInt("id_ciudad")));
                funcionario.setCargo(new Cargos(rs.getInt("id_cargo")));
                lista.add(funcionario);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(GestionFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestionFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Funcionarios recuperarRegistro(Integer id) {
        try {
            Funcionarios funcionario = null;
            query = "SELECT id, nro_ci, nombres, apellidos, id_ciudad, id_cargo FROM public.funcionarios where id =?;";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                funcionario = new Funcionarios();
                funcionario.setId(rs.getInt("id"));
                funcionario.setNroCi(rs.getInt("nro_ci"));
                funcionario.setNombres(rs.getString("nombres"));
                funcionario.setApellidos(rs.getString("apellidos"));
                funcionario.setCiudad(new Ciudad(rs.getInt("id_ciudad")));
                funcionario.setCargo(new Cargos(rs.getInt("id_cargo")));

            }
            return funcionario;
        } catch (SQLException ex) {
            Logger.getLogger(GestionFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestionFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
