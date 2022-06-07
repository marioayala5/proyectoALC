package GestionRUC;

public class TestRuc {

    GestionarRuc gestion;

    public TestRuc() {
        gestion = new GestionarRuc();
        gestion.procesarListaArchivo();
    }
    
    
    
    public static void main(String[] args) {
        new TestRuc();
    }
    
}
