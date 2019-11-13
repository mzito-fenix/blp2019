/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blpobl;

/**
 *
 * @author Marcela Ferraz - Mauricio Zito
 */
import dominio.archivos.archivos;
import java.io.IOException;

public class BLPObl {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

       if(args.length != 1){
            System.out.println("Debe ingresar el nombre del archivo a evaluar (con dirección absoluta)");
            System.exit(0);
        }
        
        String Archivo=args[0];
int lineas=0;
        if(Archivo.length()>0)
        {
            archivos archivo =new archivos();
            archivo.CrearLog();
            //lineas=archivo.abrir(Archivo);
            
            
        }
        //System.out.println(Archivo);
        //System.out.println(lineas);
    }
    
}
