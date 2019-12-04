package entities;

import dominio.archivos.archivos;
import java.io.IOException;
import static main.Main.archivo;
import management.ObjectManager;

public class ReferenceMonitor {
    public static ReferenceMonitor referenceMonitor = null;
    
     public static ReferenceMonitor getInstance() {
        if (referenceMonitor == null) {
            referenceMonitor = new ReferenceMonitor();
        }
        return referenceMonitor;
    }

    public ReferenceMonitor() {

    }
     
     public void runInstruction(InstructionObject instruction) throws IOException {
        archivos ArchivoLog = archivos.getInstance();
        
        switch (instruction.getType()) {
            case READ:
                //ArchivoLog.Loguear(instruction.getSubjectName()+ instruction.getObjectName()+
                //        instruction.getValue());
                //ArchivoLog.Loguear("Antes de leer");                
                if(!executeRead(instruction.getSubjectName(), instruction.getObjectName()))
                    ArchivoLog.Loguear("ERROR PERMISOS");
                break;
            case WRITE:
                ArchivoLog.Loguear(instruction.getSubjectName()+ instruction.getObjectName()+
                        instruction.getValue());
                if(!executeWrite(instruction.getSubjectName(), instruction.getObjectName(), instruction.getValue()))
                    ArchivoLog.Loguear("ERROR PERMISOS");
                break;
            case BAD_INSTRUCTION:
                ArchivoLog.Loguear("BAD_INSTRUCTION");
                break;
            default:
                break;
        }
    }
    
  public boolean executeRead(String subjectName, String objectName) {
      ObjectManager OM =ObjectManager.getInstance();      
      boolean resultado=true;
      //Propiedad simple : el sujeto solo puede leer de su nivel hacia abajo
      
      if(this.RelacionDeNivel(subjectName, objectName)==3||this.RelacionDeNivel(subjectName, objectName)==1)
      {
          resultado=OM.read(subjectName, objectName);
      }
      return resultado;      
  }

public boolean executeWrite(String subjectName, String objectName, int value) throws IOException {
        boolean resultado=true;
        ObjectManager OM =ObjectManager.getInstance();      
        //Propiedad *: El sujeto solo puede escribir de su nivel hacia arriba

      if(this.RelacionDeNivel(subjectName, objectName)==3||this.RelacionDeNivel(subjectName, objectName)==1)
      {
          resultado=OM.write(subjectName, objectName,value);
      }
      return resultado;      
  }
  
private int RelacionDeNivel(String subjectName, String objectName){
    int resultado=-1;
    //1=sujeto es mayor que objeto
    //2=objeto es mayor que sujeto
    //3=son del mismo nivel
    //-1 = no se pudo comparar
      ObjectManager OM =ObjectManager.getInstance();
      
      SecuritySubject sujeto=new SecuritySubject();
      sujeto=OM.findSubjectByName(subjectName);
      
      SecurityObject objeto=new SecurityObject();
      objeto=OM.findObjectByName(objectName);
    
      if(sujeto.getSecurityLevel()==objeto.getSecurityLevel())
          resultado=3;
      
      if(sujeto.getSecurityLevel()==SecurityLevel.HIGH&&objeto.getSecurityLevel()==SecurityLevel.LOW)
          resultado=3;
            
      if(sujeto.getSecurityLevel()==SecurityLevel.LOW&&objeto.getSecurityLevel()==SecurityLevel.HIGH)
          resultado=2;
      
      return resultado;
}
       
}
