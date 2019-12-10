package manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RecordFile {
    private static RecordFile fileRecorder = null;
    private String filePath;
    private FileOutputStream fileOutputStream;
    
    private RecordFile(String path)
    {
        filePath = path;
    }
    
     public static RecordFile getInstance(String path) throws IOException {
        if (fileRecorder == null) {
            fileRecorder = new RecordFile(path);
        }
        return fileRecorder;
    }
     
     public boolean record(byte[] byteToRecord,String path)
     {
         boolean result = true;
        try {
            fileOutputStream = new FileOutputStream("test//" + path, true);
            fileOutputStream.write(byteToRecord);
            close();
        } catch (IOException ex) {
            result = false;
        }
        return result;
     }
    
      public boolean close() {
        boolean result = true;
        try{
            fileOutputStream.flush();
            fileOutputStream.close();
        }catch(Exception e){
            result = false;
        }
        return result;
    }
      
       public void clearFile() throws IOException {
        File inputFile = new File("myFile.txt");
        File tempFile = new File("myTempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader("test/" + fileOutputStream));
        BufferedWriter writer = new BufferedWriter(new FileWriter("test/" + fileOutputStream));

        String lineToRemove = "bbb";
        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if (trimmedLine.equals(lineToRemove)) {
                continue;
            }
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        boolean successful = tempFile.renameTo(inputFile);
    }
}
