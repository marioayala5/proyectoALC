package GestionArchivoBloque;

public class TestPytyvo {
    
    GestionarArchivo gestion;
    public TestPytyvo() {
        
        gestion = new GestionarArchivo();
//        gestion.procesarArchivo();
        gestion.procesarListaArchivo();
    }

    public static void main(String[] args) {
        new TestPytyvo();
    }

}
