package clase280322;

import java.util.Date;

public class TestInstancia {

    private Persona persona;

    public TestInstancia() {
        persona = new Persona();

        persona.setId(001);
        persona.setNroDoc(5047874);
        persona.setNombre("Mario");
        persona.setApellido("Ayala");
        persona.setFecNac(new Date("1994/06/20"));
        persona.setSexo(Persona.SEXO.M);

        System.out.println("ID: " + persona.getId());
        System.out.println("Nombre: " + persona.getNombre());
        System.out.println("Apellido: " + persona.getApellido());
        System.out.println("CI: " + persona.getNroDoc());
        System.out.println("SEXO: " + persona.getSexo());
        System.out.println("FECHA NACIMIENTO: " + persona.getFecNac());

    }

    public static void main(String[] args) {
        new TestInstancia();
    }

}
