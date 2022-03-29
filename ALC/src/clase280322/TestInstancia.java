package clase280322;

import java.util.Date;

public class TestInstancia {

    private Clientes cliente;

    public TestInstancia() {
        cliente = new Clientes();

        cliente.setId(001);
        cliente.setNroDoc(5047874);
        cliente.setNombre("Hernan");
        cliente.setApellido("Allende");
        cliente.setFecNac(new Date("1993/01/27"));
        cliente.setSexo(Persona.SEXO.I);

        System.out.println("ID: " + cliente.getId());
        System.out.println("Nombre: " + cliente.getNombre());
        System.out.println("Apellido: " + cliente.getApellido());
        System.out.println("CI: " + cliente.getNroDoc());
        System.out.println("SEXO: " + cliente.getSexo());
        System.out.println("FECHA NACIMIENTO: " + cliente.getFecNac());

    }

    public static void main(String[] args) {
        new TestInstancia();
    }

}
