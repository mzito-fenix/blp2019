package entities;

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
        switch (instruction.getType()) {
            case READ:
                executeRead(instruction.getSubjectName(), instruction.getObjectName());
                break;
            case WRITE:
                executeWrite(instruction.getSubjectName(), instruction.getObjectName(), instruction.getValue());
                break;
            case BAD_INSTRUCTION:
                archivo.Loguear("BAD_INSTRUCTION");
                break;
            default:
                break;
        }
    }
    
  public boolean executeRead(String subjectName, String objectName) {
      ObjectManager OM =ObjectManager.getInstance();
      return OM.read(subjectName, objectName);      
  }

public boolean executeWrite(String subjectName, String objectName, int value) throws IOException {
      ObjectManager OM =ObjectManager.getInstance();      
      return OM.write(subjectName, objectName,value);      
  }
  
}
