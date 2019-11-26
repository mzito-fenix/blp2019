package main;

import dominio.archivos.archivos;
import dominio.archivos.comando;
import entities.InstructionObject;
import entities.ReferenceMonitor;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import dominio.archivos.archivos;
import dominio.archivos.comando;
import entities.SecurityLevel;
import entities.SecurityObject;
import entities.SecuritySubject;
import java.io.IOException;

public class Main {

    public static archivos archivo = new archivos();
    public static ArrayList<SecuritySubject> sujetos = new ArrayList<SecuritySubject>();
    public static ArrayList<Object> objetos = new ArrayList<Object>();
    
    public static void main(String[] args) {
        try {
            ejecutarPrograma(args);
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    private static void ejecutarPrograma(String[] args) throws IOException {
        ArrayList<String> lineas = null;
        try{
            ReferenceMonitor referenceMonitor = ReferenceMonitor.getInstance();
            createSubject(new SecuritySubject("lyle", SecurityLevel.LOW));
            createSubject(new SecurityObject("hal", SecurityLevel.HIGH));
            referenceMonitor.createObject("lobj", SecurityLevel.LOW);
            referenceMonitor.createObject("hobj", SecurityLevel.HIGH);

            lineas = readFile(args);
            
             int n=0;
                String lineaActual;
                String comandoActual;
                comando Comando=new comando();
                for(n=0;n<lineas.size();n++){
                    lineaActual=lineas.get(n);                    
                    
                    if(Comando.Separar(lineaActual))
                    {
                        comandoActual=Comando.getComando();
                        if(Comando.EsValido(comandoActual, Comando.getParametro1(), Comando.getParametro2(), Comando.getParametro3()))
                        {
                            //hasta aca la sintaxis esta bien 
                            if(ControlSemantica(Comando)){
                                //si esta correcto hay que ejecutar
                                
                                Ejecutar(Comando);
                            }
                            else{
                                archivo.Loguear(comandoActual + "BAD_INSTRUCTION");
                            }
                        }
                        else                        
                        {
                            archivo.Loguear(comandoActual + " = No es comando válido");
                        }
                        
                    }
                    else
                    {
                        archivo.Loguear("Error");
                    }
                }              
            
            
//            String line = br.readLine(br);
//            while (line != null) {
//                InstructionObject instruction = InstructionObject.ValidateInstruction(line);
//                referenceMonitor.runInstruction(instruction);
//                printState();
//                line = br.readLine();
//            }
//            br.close();
//            System.out.println("");
//            System.out.println("Enter to exit...");
//            System.in.read();
//        } catch (Exception e) {
//            System.out.println("An error has occured. Please re try.");
//            if (br != null) {
//                br.close();
//            }
//            resetProgramStatus();
//            executeProgram();
        }
    }
    
    public static boolean ControlSemantica(comando Comando){
        //verificar si el objecto existe
        //verificar si el sujecto existe
        //verificar si el sujecto puede hacer la accion sobre el objeto
        return true;
    }
    
    public static void Ejecutar(comando Comando){
        //aca se ejecuta las acciones 
    }
    
    private static void createSubject(Object objeto){
       objetos.add(objeto);
    }
    
    private static void createObject(SecuritySubject sujeto){
        sujetos.add(sujeto);
    }

    private static ArrayList<String> readFile(String[] args) throws IOException {
        ArrayList<String> listaComandos = new ArrayList<String>();

        if (args.length != 1) {
            System.out.println("Debe ingresar el nombre del archivo a evaluar (con dirección absoluta)");
            System.exit(0);
        }

        String Archivo = args[0];
        int lineas = 0;
        if (Archivo.length() > 0) {
            
            archivo.CrearLog();
            archivo.Loguear("Abriendo archivo->" + Archivo);
            lineas = archivo.abrir(Archivo);
            if (lineas > 0) {

                listaComandos = archivo.ListaComandos;
            }
        }
        return listaComandos;
    }

}
