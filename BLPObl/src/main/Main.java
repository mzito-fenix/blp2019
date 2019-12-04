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
    ArrayList<String> lineas = null;
    
    public static void main(String[] args) {
        try {
            ejecutarPrograma(args);            
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    private static void ejecutarPrograma(String[] args) throws IOException {
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
        
        
        //ESTE CODIGO LO PIDE LA LETRA, ES PARA CARGAR 2 OBJETOS Y PROBAR
        //        // LOW and HIGH are constants defined in the SecurityLevel
        //// class, such that HIGH dominates LOW.
        //SecurityLevel low = SecurityLevel.LOW;
        //SecurityLevel high = SecurityLevel.HIGH;
        //// We add two subjects, one high and one low.
        //sys.createSubject("lyle", low);
        //sys.createSubject("hal", high);
        //// We add two objects, one high and one low.
        //sys.getReferenceMonitor().createNewObject("Lobj", low);
        //sys.getReferenceMonitor().createNewObject("Hobj", high);

        
        
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
    }

    private static void ejecutarParte1(String Archivo) throws IOException{
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
                        comandoActual=Comando.getComando();
                        if(Comando.EsValido(Comando.getComando(),Comando.getParametro1(),Comando.getParametro2(),Comando.getParametro3()))
                        {
                            //IObj.ejecutar(comandoActual,Comando.getParametro1(),Comando.getParametro2());
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
    }
    
    private static void createSubject(Object objeto){
       objetos.add(objeto);
    }
    
    private static void createObject(SecuritySubject sujeto){
        sujetos.add(sujeto);
    }

//    private static ArrayList<String> readFile(String[] args) throws IOException {
//
//        
//        
//    }

}
