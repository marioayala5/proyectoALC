
package Conexion;


public enum TipoMotorBD {
    POSTGRESQL ("postgresql", "org.postgresql.Driver","laboratorio_alc","postgres","123456789","5432","localhost"),
    MYSQL ("mysql","com.mysql.jdbc.Driver","laboratorio_sc","root","1","3306","localhost");
    

    private final String motorBD;
    private final String controlador;
    private final String nombreBD;
    private final String usuarioBD;
    private final String claveBD;
    private final String puertoBD;
    private final String host;

    private TipoMotorBD(String motorBD, String controlador, String nombreBD, String usuarioBD, String claveBD, String puertoBD, String host) {
        this.motorBD = motorBD;
        this.controlador = controlador;
        this.nombreBD = nombreBD;
        this.usuarioBD = usuarioBD;
        this.claveBD = claveBD;
        this.puertoBD = puertoBD;
        this.host = host;
    }

    public String getMotorBD() {
        return motorBD;
    }

    public String getHost() {
        return host;
    }
    
    public String getControlador() {
        return controlador;
    }

    public String getNombreBD() {
        return nombreBD;
    }

    public String getUsuarioBD() {
        return usuarioBD;
    }

    public String getClaveBD() {
        return claveBD;
    }

    public String getPuertoBD() {
        return puertoBD;
    }
    
}
