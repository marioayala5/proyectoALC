package GestionRUC;

import Conexion.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GestionRuc {

    private final ConexionBD conexion; //me indica el paquete 
    private PreparedStatement ps;
    private String query, mensaje;
    private ResultSet rs;

    public GestionRuc() {
        this.conexion = new ConexionBD();
    }

    public boolean agregar(List<Ruc> lista) {
        int bloqueAgru = 100;
        int cont = 1;
        String cadena = "", query = "";
        int canReg = lista.size();
        try {
            for (Ruc r : lista) {
                String dato = "('" + r.getNroRuc() + "','" + r.getRazonSocial() + "'," + r.getDv() + ",'" + r.getRucAnterior()+ "')";
                cadena += (cadena.isEmpty() ? dato : "," + dato);
                if (cont == bloqueAgru) {
                    query = "INSERT INTO public.ruc(nro_ruc, razon_social, dv, ruc_anterior)"
                            + " VALUES " + cadena + " ; ";
                    ps = conexion.obtenerConexion().prepareStatement(query);
                    ps.executeUpdate();
                    cadena = "";
                    cont = 0;
                }
                cont++;
            }
            if (cont > 0) {
                query = "INSERT INTO public.ruc(nro_ruc, razon_social, dv, ruc_anterior)"
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
}
