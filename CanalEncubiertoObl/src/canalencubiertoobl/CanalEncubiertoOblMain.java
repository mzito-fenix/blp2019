package canalencubiertoobl;

import manager.ReferenceMonitor;
import entities.SecurityLevel;
import fileAction.FileAction;
import files.FileStreamManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import manager.ObjectManager;
import org.javatuples.Triplet;

public class CanalEncubiertoOblMain {

    private static long bytesRead;
    private static File fileToTransfer = null;
    private static InputStream fileToTransferInputStream = null;
    private static String outputFileName = "";
    private FileStreamManager fileStreamManager = null;
    private static FileAction FileActions = null;
    public static Triplet<String, File, InputStream> data = null;
    private static ObjectManager objectManager = null;

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
            objectManager = ObjectManager.getInstance();
            
            crearSujeto("lyle", SecurityLevel.LOW);
            crearSujeto("hal", SecurityLevel.HIGH);

            //crea archivos de flujo de transferencia de datos
            
            FileStreamManager fileStreamManager = FileStreamManager.getInstance();
            data = FileStreamManager.createFileStreamFileToTransfer();
            fileToTransfer = data.getValue1();
            fileToTransferInputStream = data.getValue2();
            error = data.getValue0();
            while (error != "") {
                System.out.println(error);
                fileToTransfer = fileStreamManager.createFileStreamFileToTransfer().getValue1();
                fileToTransferInputStream = fileStreamManager.createFileStreamFileToTransfer().getValue2();
            }

            if (fileCorrectData()) {
                outputFileName = fileStreamManager.getOutputFileName();
                referenceMonitor.setFileRecordName(outputFileName);
                System.out.println("Trabajando. Puede demorar unos segundos...");
                //llamada a las cosas que estoy haciendo de los archivos

                fileStreamManager = FileStreamManager.getInstance();
                fileStreamManager.transferData(); 
                FileActions = FileAction.getInstance();
                BufferedReader bufferedReaderFile = FileActions.readHLSequenceFile();
                fileStreamManager.PrepareHLLine(bufferedReaderFile);
                fileStreamManager.ExecuteDataReader(bufferedReaderFile);

            } else {
                String message = "Programa finalizado." + "\n" + "Presione ENTER para salir";
            }

        } catch (Exception e) {
            System.out.println(e.getMessage().toString());

        }

    }

    public static boolean fileCorrectData() {
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
        objectManager.createSubject(nombre, securityLevel);
    }

    public static boolean existeSujeto(String nombre) {
        return false;
    }

    public void obtenerNombreArchivo() {

    }
}
