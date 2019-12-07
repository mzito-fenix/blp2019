package canalencubiertoobl;

import entities.ReferenceMonitor;
import entities.SecurityLevel;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CanalEncubiertoOblMain {
    
    private static long bytesRead;

    public static void main(String[] args) {
        try{
            ejecutarPrograma();
        }
        catch(Exception e){
            System.out.println("Ha ocurrido un error");
        }
    }
    
    public CanalEncubiertoOblMain(){
    }
    
    public static void ejecutarPrograma()throws IOException {
        try{
            //crea los sujetos
             ReferenceMonitor referenceMonitor = ReferenceMonitor.getInstance();
             crearSujeto("lyle", SecurityLevel.LOW);
             crearSujeto("hal", SecurityLevel.HIGH);
//             createInputStream();
        }
        catch(Exception e){
        }
    
    }
    
    public static void obtenerSecuenciaChar(BufferedReader sec){
        
    }
    
    public static char[] leerBytes(File archivo, InputStream inputStreamArchivo) throws IOException{
        return null;
    }
    
    public static void crearInputStream(){
    
    }
    
    public static BufferedReader leerNombreArchivo(){
        return null;
    }
    
    public static void crearSujeto(String nombre,SecurityLevel securityLevel ){

}
    
    public static boolean existeSujeto(String nombre){
        return false;
    }
    
    public void obtenerNombreArchivo(){
    
    }
}
