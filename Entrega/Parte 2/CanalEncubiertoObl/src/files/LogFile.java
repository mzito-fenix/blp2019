package files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
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
        logFile = path + "\\test\\"+ "log.log";
        clearFile();
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
    
    public void clearFile() throws IOException {
        File inputFile = new File("myFile.txt");
        File tempFile = new File("myTempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(logFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(logFile));

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
