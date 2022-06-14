package GestionRucHilos;

import GestionRUC.GestionRuc;
import GestionRUC.Ruc;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionHilos implements Runnable {

    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    String urlArchivo, linea;
    String d[];
    int bandera;

    public GestionHilos(int banderaArchivo) {
        this.bandera = banderaArchivo;
    }
    
    
    @Override
    public void run() {
        procesarListaArchivo(bandera);

    }

    public void procesarListaArchivo(int banderaArchivo) {

        try {
            switch (banderaArchivo) {
                case 1:
                    //archivo del ruc1
                    urlArchivo = "C:\\Users\\Usuario\\Downloads\\ruc\\ruc1.txt";
                    break;
                case 2:
                    //archivo del ruc2
                    urlArchivo = "C:\\Users\\Usuario\\Downloads\\ruc\\ruc2.txt";
                    break;
                default:
                    //archivo del ruc0
                    urlArchivo = "C:\\Users\\Usuario\\Downloads\\ruc\\ruc0.txt";
            }
            
            archivo = new File(urlArchivo);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            int c = 1;
            GestionRuc gestion = new GestionRuc();
            Ruc ruc = null;
            List<Ruc> lista = new ArrayList<>();
            while ((linea = br.readLine()) != null) {
                // System.out.println("Linea " + linea);
                if (c > 1) {
                    // System.out.println("Linea " + c);
                    d = linea.split(";");
                    if (d.length == 4) {
                        ruc = new Ruc();
                        ruc.setNroRuc(d[0]);
                        ruc.setRazonSocial(d[1]);
                        ruc.setDv(Integer.parseInt(d[2]));
                        ruc.setRucAnterior(d[3]);

                        lista.add(ruc);
//                        System.out.println( d[0] );
//                        System.out.println( d[1] );
//                        System.out.println( d[2] );
//                        System.out.println( d[3] );
                    }
                    //4484971;ADOLFO RAMON LOPEZ FRANCO;CENTRAL;LIMPIO

                }
                c++;
            }
            gestion.agregar(lista);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestionHilos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GestionHilos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
