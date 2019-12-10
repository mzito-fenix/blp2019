package manager;

import java.io.FileOutputStream;
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
}
