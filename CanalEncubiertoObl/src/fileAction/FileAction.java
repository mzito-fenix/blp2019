package fileAction;

import java.util.ArrayList;
import java.io.*;
import java.util.Calendar;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class FileAction {

    private static FileAction File = null;

    public BufferedReader ListaComandos;

    private void FileAction() {
    }

    public static FileAction getInstance() {
        if (File == null) {
            File = new FileAction();
        }
        return File;
    }

    public BufferedReader readHLSequenceFile() {
        try {
            String message = "Ingrese nombre de archivo que contiene la secuencia H y L junto con su extension (secuencia.txt): ";
            System.out.println(message);
            Scanner scanner = new Scanner(System.in);
            String inputString = scanner.nextLine();
            BufferedReader br = new BufferedReader(new FileReader("test/" + inputString));
            return br;
        } catch (Exception e) {
            String errorMessage = "Ingrese correctamente los datos";
            System.out.println(errorMessage);
            return readHLSequenceFile();
        }
    }
}
