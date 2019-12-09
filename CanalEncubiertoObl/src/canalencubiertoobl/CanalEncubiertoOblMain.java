package canalencubiertoobl;

import manager.ReferenceMonitor;
import entities.SecurityLevel;
import files.FileStreamManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class CanalEncubiertoOblMain {

    private static long bytesRead;
    private static File fileToTransfer = null;
    private static InputStream fileToTransferInputStream = null;
    private static String outputFileName = "";

    public static void main(String[] args) {
        try {
            ejecutarPrograma();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error");
        }
    }

    public CanalEncubiertoOblMain() {
    }

    public static void ejecutarPrograma() throws IOException {
        try {
            String error = "";
            //crea los sujetos
            ReferenceMonitor referenceMonitor = ReferenceMonitor.getInstance();
            crearSujeto("lyle", SecurityLevel.LOW);
            crearSujeto("hal", SecurityLevel.HIGH);
            
            //crea archivos de flujo de transferencia de datos
            FileStreamManager fileStreamManager = FileStreamManager.getInstance();
            fileToTransfer = fileStreamManager.createFileStreamFileToTransfer().getValue1();
            fileToTransferInputStream = fileStreamManager.createFileStreamFileToTransfer().getValue2();
            error = fileStreamManager.createFileStreamFileToTransfer().getValue0();
            while (error != "") {
                System.out.println(error);
                fileToTransfer = fileStreamManager.createFileStreamFileToTransfer().getValue1();
                fileToTransferInputStream = fileStreamManager.createFileStreamFileToTransfer().getValue2();
            }
            
            if(fileCorrectData()){
                outputFileName = fileStreamManager.getOutputFileName();
                referenceMonitor.setFileRecordName(outputFileName);
                System.out.println("Trabajando. Puede demorar unos segundos...");
                //llamada a las cosas que estoy haciendo de los archivos
                
            }
            else{
                //finaliza el programa
                String message = "Programa finalizado." + "\n" + "Presione ENTER para salir";
                //ver que ejecuta mas tarde
            }
            
            
            

        } catch (Exception e) {
            System.out.println(e.getMessage().toString());

        }

    }
    
    public static boolean fileCorrectData(){
        return (fileToTransfer != null && fileToTransferInputStream != null);
    }

    public static void obtenerSecuenciaChar(BufferedReader sec) {

    }

    public static char[] leerBytes(File archivo, InputStream inputStreamArchivo) throws IOException {
        return null;
    }

    public static BufferedReader leerNombreArchivo() {
        return null;
    }

    public static void crearSujeto(String nombre, SecurityLevel securityLevel) {

    }

    public static boolean existeSujeto(String nombre) {
        return false;
    }

    public void obtenerNombreArchivo() {

    }
}
