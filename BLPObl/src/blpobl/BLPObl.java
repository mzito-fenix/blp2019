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
public class BLPObl {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

       if(args.length != 1){
            System.out.println("Debe ingresar el nombre del archivo a evaluar (con dirección absoluta)");
            System.exit(0);
        }
        
        String Archivo=args[0];
        
        System.out.println(Archivo);
    }
    
}
