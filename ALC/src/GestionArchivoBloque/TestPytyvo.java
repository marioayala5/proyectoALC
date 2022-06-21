package GestionArchivoBloque;

public class TestPytyvo {

    private GestionarArchivo gestion;
    private GestionPytyvo gestionPytyvo;

    public TestPytyvo() {

        //Para la carga masiva
//        gestion = new GestionarArchivo();
//        gestion.procesarArchivo();
//        gestion.procesarListaArchivo();
        //Para la impresion masiva
//        gestionPytyvo = new GestionPytyvo();
//        for (Pytyvo p : gestionPytyvo.recuperarRegistros()) {
//            System.out.println("id : " + p.getId());
//            System.out.println("cic : " + p.getCic());
//            System.out.println("Nombre Apellido : " + p.getNombreApellido());
//            System.out.println("Departamento: " + p.getDepartamento());
//            System.out.println("Distrito : " + p.getDistrito());
//        }
        //Para recuperar un solo registro
        gestionPytyvo = new GestionPytyvo();
        for (Pytyvo p : gestionPytyvo.recuperarRegistro(534278)) {
            System.out.println("id : " + p.getId());
            System.out.println("cic : " + p.getCic());
            System.out.println("Nombre Apellido : " + p.getNombreApellido());
            System.out.println("Departamento: " + p.getDepartamento());
            System.out.println("Distrito : " + p.getDistrito());
        }
    }

    public static void main(String[] args) {
        new TestPytyvo();
    }

}
