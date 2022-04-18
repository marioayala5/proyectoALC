
package Conexion;

import ABM.GestionLunes;
import ABM.Lunes;


public class TestConexion {
    private final Lunes registro;
    private final GestionLunes gestionLunes;

    public TestConexion() {
        registro = new Lunes();
        registro.setDescripcion("Lunes 18/04/2022");
        registro.setComentario("Test de Inserción");
        
        gestionLunes = new GestionLunes();
        gestionLunes.agregarRegistro(registro);
        System.out.println(gestionLunes.getMensaje());
               
    }


    
    public static void main(String[] args) {
        new TestConexion();
    }
    
}
