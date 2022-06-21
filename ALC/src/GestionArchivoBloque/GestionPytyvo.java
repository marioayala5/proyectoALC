package GestionArchivoBloque;

import Conexion.ConexionBD;
import GestionPermisos.Acciones;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionPytyvo {

    private final ConexionBD conexion; //me indica el paquete 
    private PreparedStatement ps;
    private String query, mensaje;
    private ResultSet rs;

    public GestionPytyvo() {
        this.conexion = new ConexionBD();
    }

    public Boolean agregarRegistro(Pytyvo pytyvo) {
        try {
            conexion.Transaccion(ConexionBD.TR.INICIAR);
            query = "INSERT INTO public.pytyvo(cic, nombreapellido, departamento, distrito) VALUES (?, ?, ?, ?);";
            ps = conexion.obtenerConexion().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, pytyvo.getCic());
            ps.setString(2, pytyvo.getNombreApellido());
            ps.setString(3, pytyvo.getDepartamento());
            ps.setString(4, pytyvo.getDistrito());

            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(ConexionBD.TR.CONFIRMAR);
                mensaje = "Operación exitosa";
                return true;
            } else {
                conexion.Transaccion(ConexionBD.TR.CANCELAR);
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(GestionPytyvo.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(ConexionBD.TR.CANCELAR);
            mensaje = "Error durante la insercion del registro";
            return false;
        } finally {
            try {
//                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestionPytyvo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean agregar(List<Pytyvo> lista) {
        int bloqueAgru = 100;
        int cont = 1;
        String cadena = "", query = "";
        int canReg = lista.size();
        try {
            for (Pytyvo py : lista) {
                String dato = "(" + py.getCic() + ",'" + py.getNombreApellido() + "','" + py.getDepartamento() + "','" + py.getDistrito() + "')";
                cadena += (cadena.isEmpty() ? dato : "," + dato);
                if (cont == bloqueAgru) {
                    query = "INSERT INTO public.pytyvo(cic, nombreapellido, departamento, distrito)"
                            + " VALUES " + cadena + " ; ";
                    ps = conexion.obtenerConexion().prepareStatement(query);
                    ps.executeUpdate();
                    cadena = "";
                    cont = 0;
                }
                cont++;
            }
            if (cont > 0) {
                query = "INSERT INTO public.pytyvo(cic, nombreapellido, departamento, distrito)"
                        + " VALUES " + cadena + " ; ";
                ps = conexion.obtenerConexion().prepareStatement(query);
                ps.executeUpdate();
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Pytyvo> recuperarRegistros() {
        try {
            List<Pytyvo> lista = new ArrayList<>();
            Pytyvo registro;
            query = "SELECT id, cic, nombreapellido, departamento, distrito FROM public.pytyvo;";
            ps = conexion.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                registro = new Pytyvo();
                registro.setId(rs.getInt("id"));
                registro.setCic(rs.getInt("cic"));
                registro.setNombreApellido(rs.getString("nombreapellido"));
                registro.setDepartamento(rs.getString("departamento"));
                registro.setDistrito(rs.getString("distrito"));

                lista.add(registro);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(GestionPytyvo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestionPytyvo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public List<Pytyvo> recuperarRegistro(Integer id) {
        try {
            Pytyvo registro;
            List<Pytyvo> pytyvo;
            query = "SELECT id, cic, nombreapellido, departamento, distrito FROM public.pytyvo where id=?;";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            pytyvo = new ArrayList<>();
            while (rs.next()) {
                registro = new Pytyvo();
                registro.setId(rs.getInt("id"));
                registro.setCic(rs.getInt("cic"));
                registro.setNombreApellido(rs.getString("nombreapellido"));
                registro.setDepartamento(rs.getString("departamento"));
                registro.setDistrito(rs.getString("distrito"));
                pytyvo.add(registro);
            }
            return pytyvo ;
        } catch (SQLException ex) {
            Logger.getLogger(GestionPytyvo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestionPytyvo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
