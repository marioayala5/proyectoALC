package GestionPermisos;

import java.util.ArrayList;
import java.util.List;

public class TestGestionPermiso {

    private List<Acciones> acciones;
    private Perfiles perfiles;
    private GestionPerfil gestion;

    public TestGestionPermiso() {
        acciones = new ArrayList<>();
        acciones.add(new Acciones(1));
        acciones.add(new Acciones(2));
        acciones.add(new Acciones(3));
        acciones.add(new Acciones(4));
        acciones.add(new Acciones(5));
        acciones.add(new Acciones(6));

        perfiles = new Perfiles();
        perfiles.setDescripcion("Test de Perfil");
        perfiles.setPermisos(acciones);

        gestion = new GestionPerfil();
        gestion.agregarRegistro(perfiles);
        System.out.println(gestion.getMensaje());
    }

    public static void main(String[] args) {
        new TestGestionPermiso();
    }

}
