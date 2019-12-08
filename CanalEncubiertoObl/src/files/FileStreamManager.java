package files;

import fileAction.FileAction;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import org.javatuples.Triplet;

public class FileStreamManager {

    public static FileStreamManager fileStream = null;
    public static File fileToTransfer = null;
    private static InputStream fileToTransferInputStream = null;
    private static String outputFileName = "";
    private long actuallyBytesRecieved = 0;
    private char[] readDataOnBits = new char[0];
    private String HLSequenceLine = "";
    private FileAction FileActions = null;

    public static FileStreamManager getInstance() {
        if (fileStream == null) {
            fileStream = new FileStreamManager();
        }
        return fileStream;
    }

    private FileStreamManager() {
    }

    public Triplet<String, File, InputStream> createFileStreamFileToTransfer() throws IOException {
        try {
            String message = "Ingrese nombre de archivo al cual se desea enviar el mensaje encubierto: ";
            System.out.println(message);

            String inputPath = new Scanner(System.in).nextLine();
            fileToTransfer = new File("Pruebas/" + inputPath);
            fileToTransferInputStream = new FileInputStream(fileToTransfer);
        } catch (Exception e) {
            return new Triplet<String, File, InputStream>("error", fileToTransfer, fileToTransferInputStream);

        }
        return new Triplet<String, File, InputStream>("", fileToTransfer, fileToTransferInputStream);
    }

    public String getOutputFileName() {
        String fileName = fileToTransfer.getName();
        String[] aux = fileName.split("\\.");
        outputFileName = "";
        for (int i = 0; i < aux.length; i++) {
            if (i == (aux.length - 1)) {
                outputFileName += "salida." + aux[i];
            } else {
                outputFileName += aux[i] + ".";
            }
        }
        return outputFileName;
    }

    public void transferData() throws IOException {
        long fileLength = fileToTransfer.length();

        if (actuallyBytesRecieved < fileLength) {

            int countOfBytes = 100;
            byte[] arrayOfBytes = new byte[countOfBytes];
            int numberOfBytes = fileToTransferInputStream.read(arrayOfBytes);

            if (numberOfBytes != arrayOfBytes.length) {

            } else if (numberOfBytes != -1) {
                actuallyBytesRecieved += numberOfBytes;
            } else {
                actuallyBytesRecieved += arrayOfBytes.length;
            }

            //codigo para pasar de ARRAY DE BYTES -> ARRAY DE BITS
            readDataOnBits = LoadByteArrayToBitsArray(arrayOfBytes);

            //leer lineas de archivo
            FileActions = FileAction.getInstance();
            BufferedReader bufferedReaderFile = FileActions.readHLSequenceFile();
            PrepareHLLine(bufferedReaderFile);
            
            

        }
    }

    public void PrepareHLLine(BufferedReader bufferedReaderFile) throws IOException {
        try {
            HLSequenceLine = "";
            HLSequenceLine = bufferedReaderFile.readLine();
            HLSequenceLine = HLSequenceLine.toLowerCase();
//            HLSequenceLine = HLSequenceLine.replaceAll("\t", " ");
//            HLSequenceLine = HLSequenceLine.replaceAll(" +", " ").trim();
            HLSequenceLine = HLSequenceLine.replaceAll(" ", "");
        }
        catch(Exception e){
            System.out.print("Error de lectura de archivo");
        }

    }

    public static char[] LoadByteArrayToBitsArray(byte[] arrayOfBytes) {

        final char[] bits = new char[8 * arrayOfBytes.length];
        for (int i = 0; i < arrayOfBytes.length; i++) {
            final byte byteval = arrayOfBytes[i];
            int bytei = i << 3;
            int mask = 0x1;
            for (int j = 7; j >= 0; j--) {
                final int bitval = byteval & mask;
                if (bitval == 0) {
                    bits[bytei + j] = '0';
                } else {
                    bits[bytei + j] = '1';
                }
                mask <<= 1;
            }
        }
        return bits;
    }

}
