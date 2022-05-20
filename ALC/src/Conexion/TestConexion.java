
package Conexion;

import ABM.GestionLunes;
import ABM.Lunes;


public class TestConexion {
    private final Lunes registro;
    private final GestionLunes gestionLunes;

    public TestConexion() {
        registro = new Lunes();
        registro.setDescripcion("Lunes 18/04/2022 MODIFICADO");
        registro.setComentario("Test de Inserción/MODIFICACION");
        registro.setId(2);
        
        gestionLunes = new GestionLunes();
        gestionLunes.modificarRegistro(registro);
        System.out.println(gestionLunes.getMensaje());          
    }
    public static void main(String[] args) {
        new TestConexion();
    }
    
}
