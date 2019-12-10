package main;

import archivos.archivos;
import entities.comando;
import entities.InstructionObject;
import entities.ReferenceMonitor;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import archivos.archivos;
import entities.comando;
import entities.SecurityLevel;
import entities.SecurityObject;
import entities.SecuritySubject;
import java.io.IOException;
import management.ObjectManager;

public class Main {

    public static archivos archivo = new archivos();
    public static ArrayList<SecuritySubject> sujetos = new ArrayList<SecuritySubject>();
    public static ArrayList<Object> objetos = new ArrayList<Object>();
    ArrayList<String> lineas = null;
    
    public static void main(String[] args) {
        try {
            ejecutarPrograma(args);            
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    private static void ejecutarPrograma(String[] args) throws IOException {
        archivos archivoLog =archivos.getInstance();
        archivoLog.CrearLog();
        
        ObjectManager sys=ObjectManager.getInstance();
        
        //Si no se reciben parametros, finaliza la ejecución
        if (args.length <1) {
            archivoLog.Loguear("Debe ingresar el nombre del archivo a evaluar (con dirección absoluta)");
            System.exit(0);
        }
                
        
        SecurityLevel low = SecurityLevel.LOW;
        SecurityLevel high = SecurityLevel.HIGH;
        
        
       boolean res1=sys.createSubject("lyle", low);
       boolean res2=sys.createSubject("hal", high);

       boolean res3=sys.CreateObject("Lobj", low);
       boolean res4=sys.CreateObject("Hobj", high);
       
       archivoLog.Loguear("#### Estado Inicial");
       sys.listSubjects();
       sys.listObjects();
       String Archivo=args[0];
       ejecutarParte1(Archivo);
        
       archivoLog.Loguear("#### Estado Final");
        sys.listSubjects();
        sys.listObjects();
        
    }

    private static void ejecutarParte1(String Archivo) throws IOException{
        ObjectManager OM=ObjectManager.getInstance();
        ArrayList<String> listaComandos = new ArrayList<String>();
        archivos archivoLog =archivos.getInstance();
        archivoLog.Loguear("Abriendo archivo->" + Archivo);
        int lineas=archivoLog.abrir(Archivo);        
        if(lineas>0){
                listaComandos=archivoLog.ListaComandos;
                int n=0;
                String lineaActual;
                String comandoActual;
                comando Comando=new comando();
                for(n=0;n<lineas;n++){
                    lineaActual=listaComandos.get(n);
                    if(Comando.Separar(lineaActual)){
                        comandoActual=Comando.getComando().toString();
                        if(Comando.EsValido(Comando.getComando().toString(),Comando.getParametro1(),Comando.getParametro2(),Comando.getParametro3()))
                        {
                            EjecutarLinea(Comando);
                            archivoLog.Loguear(comandoActual );
                        }
                        else                        
                        {
                            archivoLog.Loguear("BAD_INSTRUCTION");
                        }                        
                    }
                    else
                    {
                        archivoLog.Loguear("BAD_INSTRUCTION");
                    }                    
                }
            }
            else
            {
                archivoLog.Loguear("El archivo estaba vacío");
            }
    }
    

        
    public static void EjecutarLinea(comando Comando) throws IOException{            
        ReferenceMonitor RM=new ReferenceMonitor();
        String param1=Comando.getParametro1();
        String param2=Comando.getParametro2();       
        String param3;
        int valor=-1;
        
        try{
            param3=Comando.getParametro3();        
            valor=Integer.parseInt(param3);
        }
        catch(Exception e){
         param3="";
      }
        
        InstructionObject IObj=new InstructionObject(Comando.getComando(),param1,param2,valor);        
        RM.runInstruction(IObj);
        
    }
}
