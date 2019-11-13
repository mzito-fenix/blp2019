/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio.archivos;

import java.util.ArrayList;
import java.io.*;

/**
 *
 * @author mzito
 */
public class archivos {
    private ArrayList<String> ListaComandos;
    
    public int abrir(String direccion)
    {
        int resultado=0;
        File archivo = null;
        FileReader fr = null;
         BufferedReader br = null;

      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File (direccion);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
         String linea;
         while((linea=br.readLine())!=null)
         {
             System.out.println(linea);
             //Agregar la linea en el arraylist
             resultado++;
         }
            
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
        return resultado;
    }    
}
