package files;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class WriteFile {
    private static WriteFile writeFile = null;
    private BufferedWriter outBuffer;
    private String path;
    
    private WriteFile(String path) throws IOException{
        this.path = path;
    }
    
    public static WriteFile getInstance(String path) throws IOException {
        if (writeFile == null) {
            writeFile = new WriteFile(path);
        }
        return writeFile;
    }
    
    public boolean writeFileLines(String line){
        boolean canWrite = true;
        try{
            outBuffer = new BufferedWriter(new FileWriter(path, true));
            outBuffer.write(line);
            outBuffer.newLine();
            close();
        }catch(IOException e){
            canWrite = false;
        }
        return canWrite;
    }
    
    public boolean close(){
        boolean result = true;
        try{
            outBuffer.flush();
            outBuffer.close();
        }catch(Exception e){
            result = false;
        }
        return result;
    }
}
