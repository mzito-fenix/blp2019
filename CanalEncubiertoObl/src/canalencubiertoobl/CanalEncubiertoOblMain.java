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
            System.out.println("FIN DE EJECUCION");
        }
    }

    public CanalEncubiertoOblMain() {}

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
                data = FileStreamManager.createFileStreamFileToTransfer();
                error = data.getValue0();
                fileToTransfer = data.getValue1();
                fileToTransferInputStream = data.getValue2();
            }

            if (fileCorrectData()) {
                outputFileName = fileStreamManager.getOutputFileName();
                fileStreamManager.clearFile();
                referenceMonitor.setFileRecordName(outputFileName);
                
                System.out.println("\n");
                System.out.println("ESPERE...");
                System.out.println("\n");

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

    public static void crearSujeto(String nombre, SecurityLevel securityLevel) {
        objectManager.createSubject(nombre, securityLevel);
    }
}
