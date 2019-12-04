///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package blpobl;
//
///**
// *
// * @author Marcela Ferraz - Mauricio Zito
// */
//import dominio.archivos.archivos;
//import dominio.archivos.comando;
//import entities.InstructionObject;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class BLPObl {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) throws IOException {
//
//       if(args.length != 1){
//            System.out.println("Debe ingresar el nombre del archivo a evaluar (con dirección absoluta)");
//            System.exit(0);
//        }
//        
//        String Archivo=args[0];
//        int lineas=0;
//        if(Archivo.length()>0)
//        {
//            
//            //InstructionObject IObj = new InstructionObject();
//            archivos archivo =new archivos();
//            archivo.CrearLog();
//            archivo.Loguear("Abriendo archivo->" + Archivo);
//            lineas=archivo.abrir(Archivo);
//            if(lineas>0){
//                ArrayList<String> listaComandos;
//                listaComandos=archivo.ListaComandos;
//                int n=0;
//                String lineaActual;
//                String comandoActual;
//                comando Comando=new comando();
//                for(n=0;n<lineas;n++){
//                    lineaActual=listaComandos.get(n);                    
//                    
//                    if(Comando.Separar(lineaActual))
//                    {
//                        comandoActual=Comando.getComando();
////                        if(Comando.EsValido(comandoActual))
////                        {
////                            IObj.ejecutar(comandoActual,Comando.getParametro1(),Comando.getParametro2());
////                            archivo.Loguear(comandoActual + " = OK");
////                        }
////                        else                        
////                        {
////                            archivo.Loguear(comandoActual + " = No es comando válido");
////                        }
//                        
//                    }
//                    else
//                    {
//                        archivo.Loguear("Error");
//                    }
//                }                
//            }
//            else
//            {
//                archivo.Loguear("El archivo estaba vacío");
//            }
//        }
//        System.out.println("Proceso finalizado");
//        
//    }
//    
//}



///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package blpobl;
//
///**
// *
// * @author Marcela Ferraz - Mauricio Zito
// */
//import dominio.archivos.archivos;
//import dominio.archivos.comando;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class BLPObl {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) throws IOException {
//
//       if(args.length != 1){
//            System.out.println("Debe ingresar el nombre del archivo a evaluar (con dirección absoluta)");
//            System.exit(0);
//        }
//        
//        String Archivo=args[0];
//        int lineas=0;
//        if(Archivo.length()>0)
//        {
//            archivos archivo =new archivos();
//            archivo.CrearLog();
//            archivo.Loguear("Abriendo archivo->" + Archivo);
//            lineas=archivo.abrir(Archivo);
//            if(lineas>0){
//                ArrayList<String> listaComandos;
//                listaComandos=archivo.ListaComandos;
//                int n=0;
//                String lineaActual;
//                String comandoActual;
//                comando Comando=new comando();
//                for(n=0;n<lineas;n++){
//                    lineaActual=listaComandos.get(n);                    
//                    
//                    if(Comando.Separar(lineaActual))
//                    {
//                        comandoActual=Comando.getComando();
//                        if(Comando.EsValido(comandoActual))
//                        {
//                            archivo.Loguear(comandoActual + " = OK");
//                        }
//                        else                        
//                        {
//                            archivo.Loguear(comandoActual + " = No es comando válido");
//                        }
//                        
//                    }
//                    else
//                    {
//                        archivo.Loguear("Error");
//                    }
//                }                
//            }
//            else
//            {
//                archivo.Loguear("El archivo estaba vacío");
//            }
//        }
//        System.out.println("Proceso finalizado");
//        
//    }
//    
//}