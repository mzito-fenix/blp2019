package entities;

//"se leeran las lineas sucesivas del archivo y 

import dominio.archivos.comando;

//se analiza cada una en un objeto de la clase InstructionObject" 
public class InstructionObject {
    private OperationType type;
    private String subjectName;
    private String objectName;
    private int value;
    private comando comando;

    public comando getComando() {
        return comando;
    }

    public void setComando(comando comando) {
        this.comando = comando;
    }

    private InstructionObject(OperationType instructionType, String subjectName, String objectName, int value) {
        this.type = instructionType;
        this.subjectName = subjectName;
        this.objectName = objectName;
        this.value = value;
        this.comando = new comando();
    }

    private InstructionObject(OperationType instructionType) {
        this.type = instructionType;
        this.comando = new comando();
    }

    private InstructionObject(OperationType instructionType, String subjectName, String objectName) {
        this.type = instructionType;
        this.subjectName = subjectName;
        this.objectName = objectName;
        this.comando = new comando();
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
    
    public static InstructionObject ValidateInstruction(String line) {
//        comando.EsValido(line);
        return null;
    }
}
