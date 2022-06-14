package GestionRucHilos;

public class TestHilos {

    Thread tareaCero;
    Thread tareaUno;
    Thread tareaDos;

    public TestHilos() {
        tareaUno = new Thread(new GestionHilos(1));
        tareaUno.start();

//        tareaDos = new Thread(new GestionHilos(2));
//        tareaDos.run();
//
//        tareaCero = new Thread(new GestionHilos(0));
//        tareaCero.start();
    }

    public static void main(String[] args) {
        new TestHilos();
    }
}
