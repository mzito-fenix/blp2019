package entities;

import manager.ObjectManager;

public class ReferenceMonitor {
    public static ReferenceMonitor referenceMonitor = null;
    public static ObjectManager objectManager;
    public String sendedBits;
    
    public static ReferenceMonitor getInstance(){
        if(referenceMonitor == null){
            referenceMonitor = new ReferenceMonitor();
        }
        return referenceMonitor;
    }
    
    public ReferenceMonitor() {
        objectManager = ObjectManager.getInstance();
        sendedBits = "";
    }
    
    //crea el objecto, recibe el nombre del objeto y ademas el seclevel de quien lo crea
    public void createObject(String nombreObjeto, SecurityLevel secLevel){
        try{
            if(!this.objectManager.CreateObject(nombreObjeto, secLevel)){
                throw new Exception();
            }
        }
        catch(Exception e){
            System.out.println("Objeto ya existe. Operacion invalida");
        }
    }
    
    //asumiendo que el objeto ya existe
    //asumiendo que el sujeto tiene acceso a write sobre el objeto
    public void destroyObject(String nombreObjeto, String nombreSujeto){
        try{
            if(objectManager.ExistObject(nombreObjeto)){
                
                //busco objeto
                SecurityObject actualObject = new SecurityObject();
                boolean foundObject = false;
                for(int i = 0; i < objectManager.getObjects().size() && !foundObject; i++){
                    if(objectManager.getObjects().get(i).getName() == nombreObjeto){
                        actualObject = objectManager.getObjects().get(i);
                        foundObject = true;
                    }
                }
                
                //busco sujeto
                SecuritySubject actualSubject = new SecuritySubject();
                boolean foundSubject = false;
                for(int i = 0; i < objectManager.getSubjects().size() && !foundSubject; i++){
                    if(objectManager.getSubjects().get(i).getName() == nombreSujeto){
                        actualSubject = objectManager.getSubjects().get(i);
                        foundSubject = true;
                    }
                }
                
                //true si sujeto tiene acceso a write sobre el objeto
                //level de objeto >= level de sujeto
                //entonces sujeto puede escribir en objeto
                if(objectManager.dominates(actualObject.getSecurityLevel(),actualSubject.getSecurityLevel())){
                    objectManager.destroyObject(nombreObjeto);
                }
                else{
                    throw new Exception("Sujeto no tiene acceso de Write sobre objeto. Operacion invalida");
                }
            }
            else{
                throw new Exception("Objeto no existe. Operacion invalida");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage().toString());
        }
    }
}
