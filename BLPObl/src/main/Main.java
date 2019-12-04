package main;

import dominio.archivos.archivos;
import entities.comando;
import entities.InstructionObject;
import entities.ReferenceMonitor;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import dominio.archivos.archivos;
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
        ObjectManager sys=ObjectManager.getInstance();
        
        //Si no se reciben parametros, finaliza la ejecución
        if (args.length <2) {
            System.out.println("Debe ingresar el nombre del archivo a evaluar (con dirección absoluta)");
            System.exit(0);
        }
        
        //Parametro 1= P1 o P2 (si es Parte 1 o Parte 2)
        //Si es P1
        //Parametro 2= Archivo con la lista de instrucciones
        
        //Si es P2
        //Parametro 2=
        
        
        SecurityLevel low = SecurityLevel.LOW;
        SecurityLevel high = SecurityLevel.HIGH;
        
        
       boolean res1=sys.createSubject("lyle", low);
       boolean res2=sys.createSubject("hal", high);

       boolean res3=sys.CreateObject("Lobj", low);
       boolean res4=sys.CreateObject("Hobj", high);
       
       System.out.println("Estado Inicial");
       sys.listSubjects();
       sys.listObjects();

       
        String ParteObl = args[0]; //Que parte del obligatorio desea ejecutar
        System.out.println(ParteObl);
        if(ParteObl.compareTo("P1")==0)
        {
            String Archivo=args[1];
            ejecutarParte1(Archivo);
        }
        
        if(ParteObl.compareTo("P2")==0)
        {
            ejecutarParte2();
        }
        
        System.out.println("Estado Final");
        sys.listSubjects();
        sys.listObjects();
        
    }

    private static void ejecutarParte1(String Archivo) throws IOException{
        ObjectManager OM=ObjectManager.getInstance();
        ArrayList<String> listaComandos = new ArrayList<String>();
        archivos archivo =new archivos();
        archivo.CrearLog();
        archivo.Loguear("Abriendo archivo->" + Archivo);
        int lineas=archivo.abrir(Archivo);
        if(lineas>0){
                listaComandos=archivo.ListaComandos;
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
                            
                            Ejecutar(Comando);
                            archivo.Loguear(comandoActual + " = OK");
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
            }
            else
            {
                archivo.Loguear("El archivo estaba vacío");
            }
    }
    

    private static void ejecutarParte2(){
        
    }

    
    public static boolean ControlSemantica(comando Comando){
        //verificar si el objecto existe
        //verificar si el sujecto existe
        //verificar si el sujecto puede hacer la accion sobre el objeto
        return true;
    }
    
    public static void Ejecutar(comando Comando){
        //aca se ejecuta las acciones 
                
        ReferenceMonitor RM=new ReferenceMonitor();
        String param1=Comando.getParametro1();
        String param2=Comando.getParametro2();       
        String param3=Comando.getParametro3();        
        
        InstructionObject IObj=new InstructionObject(Comando.getComando(),param1,param2,Integer.parseInt(param3));
        
        RM.runInstruction(IObj);
        
        
        
    }
}
