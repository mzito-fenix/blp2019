package files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LogFile {
    private static LogFile file = null;
    public ArrayList<String> CommandList;
    private String logFile;
    
    public LogFile(){
        this.logFile = "";
    }
    
    public static LogFile getInstance(){
        if(file == null){
            file = new LogFile();
        }
        return file;
    }
    
    public void CreateLog()throws IOException{
        String path = new File(".").getAbsolutePath();
        path = path.substring(0,path.length() - 1);
        logFile = path + "\\logs\\"+ "log.log";
        System.out.println(logFile);
        FileWriter fileWrite = new FileWriter(logFile);
    }
    
    public void InsertLogLine(String line)throws IOException
    {
        FileWriter fileWrite = new FileWriter(logFile,true);
        fileWrite.write(line);
        fileWrite.write("\r\n");
        fileWrite.close();
        System.out.println(line);
    }
    
}
