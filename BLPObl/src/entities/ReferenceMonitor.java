//Clase: Encargado del control de niveles de seguridad, aplicación de reglas y ejecución de las operaciones
//Motivo: Obligatorio de Aspectos de Seguridad 2019 - segundo semestre
//Autores: Ferraz (200112) - Zito (111725)

package entities;

import archivos.archivos;
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

        //Verifico la existencia de sujeto y objeto
        ObjectManager OM =ObjectManager.getInstance();      
        boolean existeObjeto=OM.ExistObject(instruction.getObjectName());
        boolean existeSujeto=OM.ExistSubject(instruction.getSubjectName());
        
        if(existeObjeto&&existeSujeto){
            switch (instruction.getType()) {
                case READ:
                    if(!executeRead(instruction.getSubjectName(), instruction.getObjectName()))
                        ArchivoLog.Loguear("ERROR PERMISOS");
                    break;
                case WRITE:
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
        else
        {
            if(!existeObjeto)
                ArchivoLog.Loguear("ERROR: No existe objeto");
            if(!existeSujeto)
                ArchivoLog.Loguear("ERROR: No existe sujeto");
            
        }
    }
    
  public boolean executeRead(String subjectName, String objectName) {
      ObjectManager OM =ObjectManager.getInstance();      
      boolean resultado=true;
      //Propiedad simple : el sujeto solo puede leer de su nivel hacia abajo
      int relNivel=this.RelacionDeNivel(subjectName, objectName);
      if(relNivel==3||relNivel==1)
      {
          resultado=OM.read(subjectName, objectName);
      }
      return resultado;      
  }

public boolean executeWrite(String subjectName, String objectName, int value) throws IOException {
        boolean resultado=true;
        ObjectManager OM =ObjectManager.getInstance();      
        //Propiedad *: El sujeto solo puede escribir de su nivel hacia arriba

        int relNivel=this.RelacionDeNivel(subjectName, objectName);
      if(relNivel==3||relNivel==2)
      {
          resultado=OM.write(subjectName, objectName,value);
      }
      return resultado;      
  }
  
private int RelacionDeNivel(String subjectName, String objectName){
    int resultado=-1;
    //1=sujeto es mayor que objeto -> leer
    //2=objeto es mayor que sujeto -> write
    //3=son del mismo nivel -> leer o escrbir
    //-1 = no se pudo comparar
      ObjectManager OM =ObjectManager.getInstance();
      
      SecuritySubject sujeto=new SecuritySubject();
      sujeto=OM.findSubjectByName(subjectName);
      
      SecurityObject objeto=new SecurityObject();
      objeto=OM.findObjectByName(objectName);

      if(sujeto.getSecurityLevel()==SecurityLevel.HIGH&&objeto.getSecurityLevel()==SecurityLevel.LOW)
          resultado=1;

      if(objeto.getSecurityLevel()==SecurityLevel.HIGH&&sujeto.getSecurityLevel()==SecurityLevel.LOW)
          resultado=2;

      if(sujeto.getSecurityLevel()==objeto.getSecurityLevel())
          resultado=3;
      return resultado;
}
       
}
