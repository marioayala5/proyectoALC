/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionArchivoBloque;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class GestionarArchivo {

    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    String urlArchivo, linea;
    String d[];

    public void procesarArchivo() {
        try {
            urlArchivo = "C:\\archivo\\pytyvo-lista05-2020-05-11-b.csv";
            archivo = new File(urlArchivo);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            int c = 1;
            GestionPytyvo gestion = new GestionPytyvo();
            Pytyvo pytyvo = null;
            while ((linea = br.readLine()) != null) {
                System.out.println("Linea " + linea);
                if (c > 1) {
                    // System.out.println("Linea " + c);
                    d = linea.split(",");
                    if (d.length == 4) {
                        pytyvo = new Pytyvo();
                        pytyvo.setCic(Integer.parseInt(d[0]));
                        pytyvo.setNombreApellido(d[1]);
                        pytyvo.setDepartamento(d[2]);
                        pytyvo.setDistrito(d[3]);

                        gestion.agregarRegistro(pytyvo);
//                        System.out.println( d[0] );
//                        System.out.println( d[1] );
//                        System.out.println( d[2] );
//                        System.out.println( d[3] );
                    }
                    //4484971;ADOLFO RAMON LOPEZ FRANCO;CENTRAL;LIMPIO

                }
                c++;
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestionarArchivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GestionarArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void procesarListaArchivo() {
        try {
            urlArchivo = "C:\\ALC Mario Ayala\\pytyvo-lista05-2020-05-11-b.csv";
            archivo = new File(urlArchivo);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            int c = 1;
            GestionPytyvo gestion = new GestionPytyvo();
            Pytyvo pytyvo = null;
            List<Pytyvo> lista = new ArrayList<>();
            while ((linea = br.readLine()) != null) {
                // System.out.println("Linea " + linea);
                if (c > 1) {
                    // System.out.println("Linea " + c);
                    d = linea.split(",");
                    if (d.length == 4) {
                        pytyvo = new Pytyvo();
                        pytyvo.setCic(Integer.parseInt(d[0]));
                        pytyvo.setNombreApellido(d[1]);
                        pytyvo.setDepartamento(d[2]);
                        pytyvo.setDistrito(d[3]);

                        lista.add(pytyvo);
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
            Logger.getLogger(GestionarArchivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GestionarArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
