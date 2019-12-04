/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio.archivos;

import entities.ReferenceMonitor;
import java.util.ArrayList;
import java.io.*;
import java.util.Calendar;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import management.ObjectManager;


public class archivos {
    private static archivos Archivo = null;
    
    public ArrayList<String> ListaComandos;
    private String archivoLog;

    private void archivos()
    {
        archivoLog="";

    }

    public static archivos getInstance()
    {
        if (Archivo == null) {
            Archivo = new archivos();
        }
        return Archivo;
    }
    
    
    public int abrir(String direccion)
    {
        int resultado=0;
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

      try {
         archivo = new File (direccion);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
         String linea;
         ListaComandos=new ArrayList<String>();
         while((linea=br.readLine())!=null)
         {
             ListaComandos.add(linea);
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
    
    
    public ArrayList<String> lista(String direccion)
    {
        return ListaComandos;
    }
    
    public void CrearLog() throws IOException
    {
        String path = new File(".").getAbsolutePath();
        path=path.substring(0,path.length()-1);
        archivoLog=path + "\\logs\\"+ NombreArchivo();
        System.out.println(archivoLog);
        FileWriter fichero=null;
        fichero=new FileWriter(archivoLog);   
    }
    
    public void Loguear(String linea) throws IOException
    {
        FileWriter fichero=null;
        fichero=new FileWriter(archivoLog,true);  
        fichero.write(linea);
        fichero.write("\r\n");
        fichero.close();
        System.out.println(linea);
    }
    
    private String NombreArchivo()
    {
        String respuesta="";
        Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        respuesta="Log_" + Integer.toString(año) + Integer.toString(mes) + Integer.toString(dia) + Integer.toString(hora)+Integer.toString(minuto) + Integer.toString(segundo)+".log";
        return respuesta;
    }
    
}
