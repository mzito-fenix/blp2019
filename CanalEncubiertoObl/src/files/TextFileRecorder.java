package files;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class TextFileRecorder {
    private BufferedWriter out;
    private static TextFileRecorder textFileRecorder = null;
    private String path;
    
    private TextFileRecorder(String path) throws IOException{
        this.path = path;
    }
    
    public static TextFileRecorder getInstance(String path) throws IOException {
        if (textFileRecorder == null) {
            textFileRecorder = new TextFileRecorder(path);
        }
        return textFileRecorder;
    }
    
    public boolean record(String line){
        boolean result = true;
        try{
            out = new BufferedWriter(new FileWriter(path, true));
            out.write(line);
            out.newLine();
            close();
        }catch(IOException e){
            result = false;
        }
        return result;
    }
    
    public boolean close(){
        boolean result = true;
        try{
            out.flush();
            out.close();
        }catch(Exception e){
            result = false;
        }
        return result;
    }
}
