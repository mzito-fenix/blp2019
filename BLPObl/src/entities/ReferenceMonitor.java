package entities;

import management.ObjectManager;

public class ReferenceMonitor {
    private static ReferenceMonitor referenceMonitor = null;
    private final ObjectManager objectManager;
//    private final LevelHandler levelHandler;

    public static String Read = "read";
    public static String Write = "write";
    public static String WhiteSpace = " ";
    
    private ReferenceMonitor() {
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
//                executeRead(instruction.getSubjectName(), instruction.getObjectName());
                break;
            case WRITE:
//                executeWrite(instruction.getSubjectName(), instruction.getObjectName(), instruction.getValue());
                break;
            case BAD_INSTRUCTION:
                System.out.println("Bad Instruction");
                break;
            default:
                break;
        }
    }
    
//  public int executeRead(String subjectName, String objectName) {
//        Subject sujeto = Main.systemSubjects.get(SecureSystem.systemSubjects.
//                indexOf(new Subject(subjectName)));
//        Object object = objectManager.getObjects().get(objectManager.
//                getObjects().indexOf(new Object(objectName)));
//        System.out.println(subjectName + " reads " + objectName);
//        if (levelHandler.dominates(subject.getLevel(), object.getLevel())) {
//            subject.setReadValue(objectManager.read(objectName));
//            return subject.getTemp();
//        } else {
//            subject.setReadValue(0);
//            return subject.getTemp();
//        }
//    }
//  
//   public void executeWrite(String subjectName, String objectName, int value) {
//        Subject subject = SecureSystem.systemSubjects.get(SecureSystem.systemSubjects.
//                indexOf(new Subject(subjectName)));
//        Object object = objectManager.getObjects().get(objectManager.
//                getObjects().indexOf(new Object(objectName)));
//        System.out.println(subject.getName() + " writes value " + value + " to " + object.getName());
//        if (levelHandler.dominates(object.getLevel(), subject.getLevel())) {
//            objectManager.write(object.getName(), value);
//        }
//    }
    
    
}
