package management;
import entities.SecurityLevel;
import java.util.ArrayList;
import entities.SecurityObject;
import entities.SecuritySubject;
import java.util.HashSet;
import java.util.Set;

public class ObjectManager {
    private ArrayList<SecurityObject> Objects;
    private ArrayList<SecuritySubject> Subjects;
    private static ObjectManager ObjectManager = null;
    
    //clase singleton
    private ObjectManager()
    {
        this.Objects = new ArrayList<SecurityObject>();
        this.Subjects = new ArrayList<SecuritySubject>();
    }
    
    public static ObjectManager getInstance()
    {
       if (ObjectManager == null) {
            ObjectManager = new ObjectManager();
        }
        return ObjectManager;
    }
    
    public ArrayList<SecurityObject> getObjects()
    {
        return this.Objects;
    }
    
    public ArrayList<SecuritySubject> getSubjects()
    {
        return this.Subjects;
    }
    

    public boolean CreateObject(String Nombre,SecurityLevel nivel)
    {
        boolean resultado=true;
        Nombre=Nombre.toUpperCase();
        try{
            if(!ExistObject(Nombre))
            {
                SecurityObject newObject=new SecurityObject();
                newObject.setName(Nombre);
                newObject.setSecurityLevel(nivel);
                newObject.setValue(0);
                Objects.add(newObject);
            }
        }
        catch(Exception e){
         resultado=false;
      }
        
      return resultado;  
    }

    
    
    private boolean ExistSubject(String Nombre)
    {
        boolean resultado=false;
        SecuritySubject resultadoBusqueda=new SecuritySubject();
        resultadoBusqueda=findSubjectByName(Nombre);
        if(resultadoBusqueda.getName().length()>0)
            resultado=true;

        return resultado;
    }
    
    private boolean ExistObject(String Nombre)
    {
        boolean resultado=false;
        SecurityObject resultadoBusqueda=new SecurityObject();
        resultadoBusqueda=findObjectByName(Nombre);
        if(resultadoBusqueda.getName().length()>0)
            resultado=true;

        return resultado;
    }
    
    //lee y escribe objetos por su nombre
    public boolean read(String subjectName, String objectName)
    {
        System.out.println("VOY A EJECUTAR EL READ CON ESTOS PARAMETROS");
        System.out.println(subjectName);
        System.out.println(objectName);
        System.out.println("----------------------------------------------");
        
        boolean resultado=false;
        if(ExistObject(objectName)){
            //Busco el objeto y de encontrarlo, me quedo con el valor
            SecurityObject objetoBuscado=new SecurityObject();
            objetoBuscado=findObjectByName(objectName);
            if(ExistSubject(subjectName)){
                for(int x=0;x< Subjects.size();x++) {
                    if(subjectName.compareTo(Subjects.get(x).getName())==0){            
                       Subjects.get(x).setTEMP(objetoBuscado.getValue());
                       resultado=true;
                    }
                  }        
            }          
        }        
        return resultado;
    }
    
    public boolean write(String subjectName, String objectName, int value)
    {
        System.out.println("VOY A EJECUTAR EL WRITE CON ESTOS PARAMETROS");
        System.out.println(subjectName);
        System.out.println(objectName);
        System.out.println(value);
        System.out.println("----------------------------------------------");
        
        boolean resultado=false;
        if(ExistObject(objectName)){
            if(ExistSubject(subjectName)){
                for(int x=0;x< Objects.size();x++) {
                    if(objectName.compareTo(Objects.get(x).getName())==0){            
                        System.out.println("********* Encontre el objeto");
                       Objects.get(x).setValue(value);
                       resultado=true;
                    }
                  }   
            }      
            else {System.out.println("No Encontre el sujeto");}
        }        
        else {System.out.println("No Encontre el objeto");}
        return resultado;

    }

    private SecuritySubject findSubjectByName(String Nombre) {
        SecuritySubject resultado=new SecuritySubject();
        for(int x=0;x< Subjects.size();x++) {
            if(Nombre.compareTo(Subjects.get(x).getName())==0)            
                resultado=Subjects.get(x);
          }        
        return resultado;
    }
    
    private SecurityObject findObjectByName(String Nombre) {
        SecurityObject resultado=new SecurityObject();
        for(int x=0;x< Objects.size();x++) {
            if(Nombre.compareTo(Objects.get(x).getName())==0)          
               {
                 resultado=Objects.get(x);
               }
          }        
       
        return resultado;    }

    public boolean createSubject(String Nombre, SecurityLevel nivel) {
        boolean resultado=true;
        Nombre=Nombre.toUpperCase();
        try{
            if(!ExistSubject(Nombre))
            {
                SecuritySubject newSubject=new SecuritySubject();
                newSubject.setName(Nombre);
                newSubject.setSecurityLevel(nivel);
                newSubject.setTEMP(0);
                Subjects.add(newSubject);
            }
        }
        catch(Exception e){
         resultado=false;
      }
        
      return resultado;  
    }
    
    public void listSubjects(){
        System.out.println("LISTA DE SUJETOS");
        for(int x=0;x< Subjects.size();x++) {
            System.out.println("Name :" + Subjects.get(x).getName());
            System.out.println("Nivel:" + Subjects.get(x).getSecurityLevel().toString());
            System.out.println("Temp:" + Subjects.get(x).getTEMP());
            System.out.println("******************************");
          }                
    }
    
    public void listObjects(){
        System.out.println("LISTA DE OBJETOS");
        for(int x=0;x< Objects.size();x++) {
            System.out.println("Name :" + Objects.get(x).getName());
            System.out.println("Nivel:" + Objects.get(x).getSecurityLevel().toString());
            System.out.println("Valor:" + Objects.get(x).getValue());
            System.out.println("******************************");
          }        
    }
    
    
}
