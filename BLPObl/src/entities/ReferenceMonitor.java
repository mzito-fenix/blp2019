package entities;

import management.ObjectManager;

public class ReferenceMonitor {
    private static ReferenceMonitor referenceMonitor = null;
    private final ObjectManager objectManager;
//    private final LevelHandler levelHandler;

    public static String Read = "read";
    public static String Write = "write";
    public static String WhiteSpace = " ";
    
    public ReferenceMonitor() {
        objectManager = ObjectManager.getInstance();
//        levelHandler = LevelHandler.getInstance();
    }
    
     public static ReferenceMonitor getInstance() {
        if (referenceMonitor == null) {
            referenceMonitor = new ReferenceMonitor();
        }
        return referenceMonitor;
    }
     
     public void runInstruction(InstructionObject instruction) {
        switch (instruction.getType()) {
            case READ:
                executeRead(instruction.getSubjectName(), instruction.getObjectName());
                break;
            case WRITE:
                executeWrite(instruction.getSubjectName(), instruction.getObjectName(), instruction.getValue());
                break;
            case BAD_INSTRUCTION:
                System.out.println("Bad Instruction");
                break;
            default:
                break;
        }
    }
    
  public boolean executeRead(String subjectName, String objectName) {
      ObjectManager OM =ObjectManager.getInstance();
      return OM.read(subjectName, objectName);      
  }

public boolean executeWrite(String subjectName, String objectName, int value) {
      ObjectManager OM =ObjectManager.getInstance();
      
      System.out.println("Vamos a escribir");
      
      
      
      return OM.write(subjectName, objectName,value);      
  }
  
}
