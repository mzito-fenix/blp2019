package entities;

//"se leeran las lineas sucesivas del archivo y 
public class InstructionObject {
    public boolean ejecutar(String comando, String Parametro1, String Parametro2)
    {
        return true;
    }
    
    private OperationType type;
    private String subjectName;
    private String objectName;
    private int value;


    public InstructionObject(OperationType instructionType, String subjectName, String objectName, int value) {
        this.type = instructionType;
        this.subjectName = subjectName;
        this.objectName = objectName;
        this.value = value;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    
}
