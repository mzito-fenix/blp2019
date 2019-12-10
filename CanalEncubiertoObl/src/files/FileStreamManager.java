package files;

import entities.InstruccionObjeto;
import entities.TipoInstruccion;
import fileAction.FileAction;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import manager.ReferenceMonitor;
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
    private char HLSequence;
    private int HLCharPosition = 0;
    private String Turn = "hal";
    private ReferenceMonitor ReferenceMonitor;

    public static FileStreamManager getInstance() {
        if (fileStream == null) {
            fileStream = new FileStreamManager();
        }
        return fileStream;
    }

    private FileStreamManager() {
    }

    public static Triplet<String, File, InputStream> createFileStreamFileToTransfer() {
        try {
            String message = "Ingrese nombre de archivo que contiene el mensaje junto con su extension (mensaje.txt) : ";
            System.out.println(message);
            Scanner scanner = new Scanner(System.in);
            String inputPath = scanner.nextLine();
            fileToTransfer = new File("test/" + inputPath);
            fileToTransferInputStream = new FileInputStream(fileToTransfer);
        } catch (Exception e) {
            return new Triplet<String, File, InputStream>("error", fileToTransfer, fileToTransferInputStream);

        }
        return new Triplet<String, File, InputStream>("", fileToTransfer, fileToTransferInputStream);
    }

    
    public String getFileRecord(){
        return outputFileName;
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
        ReferenceMonitor = ReferenceMonitor.getInstance();
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

        }
    }

    public void ExecuteDataReader(BufferedReader b) throws IOException {
        while (!EmptyData(readDataOnBits)) {
            for (int i = 0; i < readDataOnBits.length; i++) {
                getSequenceChar(b);

                if (Turn == "hal" && HLSequence == 'h') {
                    HalExecution(readDataOnBits[i], b);
                    getSequenceChar(b);
                }
                if (Turn == "lyle" && HLSequence == 'l') {
                    LyleExecution();
                }
            }
            transferData();
        }
        ReferenceMonitor.recordLastBits();
    }

    public void HalExecution(char tranferBit, BufferedReader b) throws IOException {
        ReferenceMonitor.RunInstuction(new InstruccionObjeto(TipoInstruccion.RUN, "hal"));
        if (tranferBit == '0') {
            ReferenceMonitor.RunInstuction(new InstruccionObjeto(TipoInstruccion.CREATE, "hal", "obj"));
        }
        Turn = "lyle";

    }

    private void getSequenceChar(BufferedReader b) throws IOException {
        int lineLength = HLSequenceLine.length();
        while ((Turn == "hal" && HLSequence != 'h') || (Turn == "lyle" && HLSequence != 'l')) {
            if (HLSequenceLine == null || HLCharPosition == HLSequenceLine.length()) {
                HLSequenceLine = b.readLine();
                HLSequenceLine = HLSequenceLine.toLowerCase();
                HLSequenceLine = HLSequenceLine.replaceAll("\t", " ");
                HLSequenceLine = HLSequenceLine.replaceAll(" +", " ").trim();
                HLCharPosition = 0;
            }
            HLSequence = HLSequenceLine.charAt(HLCharPosition);
            HLCharPosition++;
        }
    }

public void LyleExecution() throws IOException {
        ReferenceMonitor.RunInstuction(new InstruccionObjeto(TipoInstruccion.CREATE, "lyle", "obj"));
        ReferenceMonitor.RunInstuction(new InstruccionObjeto(TipoInstruccion.WRITE, "lyle", "obj", 1));
        ReferenceMonitor.RunInstuction(new InstruccionObjeto(TipoInstruccion.READ, "lyle", "obj"));
        ReferenceMonitor.RunInstuction(new InstruccionObjeto(TipoInstruccion.DESTROY, "lyle", "obj"));
        ReferenceMonitor.RunInstuction(new InstruccionObjeto(TipoInstruccion.RUN, "lyle"));

        Turn = "hal";
    }

    public boolean EmptyData(char[] data) {
        return data.length == 0;
    }

    public void PrepareHLLine(BufferedReader bufferedReaderFile) throws IOException {
        try {
            HLSequenceLine = "";
            HLSequenceLine = bufferedReaderFile.readLine();
            HLSequenceLine = HLSequenceLine.toLowerCase();
            HLSequenceLine = HLSequenceLine.replaceAll("\t", " ");
            HLSequenceLine = HLSequenceLine.replaceAll(" +", " ").trim();
            HLSequenceLine = HLSequenceLine.replaceAll(" ", "");
        } catch (Exception e) {
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
