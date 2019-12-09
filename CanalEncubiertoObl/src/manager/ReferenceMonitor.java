package manager;

import entities.InstruccionObjeto;
import entities.SecurityLevel;
import entities.SecurityObject;
import entities.SecuritySubject;
import entities.TipoInstruccion;
import files.LogFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import manager.ObjectManager;

public class ReferenceMonitor {

    public static ReferenceMonitor referenceMonitor = null;
    public static LogFile logFile = null;
    public static ObjectManager objectManager;
    public String sendedBits;
    private String fileRecordName = "";

    public static ReferenceMonitor getInstance() {
        if (referenceMonitor == null) {
            referenceMonitor = new ReferenceMonitor();
        }
        return referenceMonitor;
    }

    private ReferenceMonitor() {
        objectManager = ObjectManager.getInstance();
        logFile = LogFile.getInstance();
        sendedBits = "";
    }

    //crea el objecto, recibe el nombre del objeto y ademas el seclevel de quien lo crea
    public void createObject(String nombreObjeto, SecurityLevel secLevel) {
        try {
            if (!this.objectManager.CreateObject(nombreObjeto, secLevel)) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Objeto ya existe. Operacion invalida");
        }
    }

    //asumiendo que el objeto ya existe
    //asumiendo que el sujeto tiene acceso a write sobre el objeto
    public void destroyObject(String nombreObjeto, String nombreSujeto) {
        try {
            if (objectManager.ExistObject(nombreObjeto)) {

                //busco objeto
                SecurityObject actualObject = new SecurityObject();
                boolean foundObject = false;
                for (int i = 0; i < objectManager.getObjects().size() && !foundObject; i++) {
                    if (objectManager.getObjects().get(i).getName() == nombreObjeto) {
                        actualObject = objectManager.getObjects().get(i);
                        foundObject = true;
                    }
                }

                //busco sujeto
                SecuritySubject actualSubject = new SecuritySubject();
                boolean foundSubject = false;
                for (int i = 0; i < objectManager.getSubjects().size() && !foundSubject; i++) {
                    if (objectManager.getSubjects().get(i).getName() == nombreSujeto) {
                        actualSubject = objectManager.getSubjects().get(i);
                        foundSubject = true;
                    }
                }

                //true si sujeto tiene acceso a write sobre el objeto
                //level de objeto >= level de sujeto
                //entonces sujeto puede escribir en objeto
                if (objectManager.dominates(actualObject.getSecurityLevel(), actualSubject.getSecurityLevel())) {
                    objectManager.destroyObject(nombreObjeto);
                } else {
                    throw new Exception("Sujeto no tiene acceso de Write sobre objeto. Operacion invalida");
                }
            } else {
                throw new Exception("Objeto no existe. Operacion invalida");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
        }
    }

    public void RunInstuction(InstruccionObjeto instruccion) throws IOException {
        String actionLog = "";

        TipoInstruccion instructionCase = instruccion.getTipo();
        switch (instructionCase) {
            case RUN:
                this.ExecuteRUNAction(instruccion.getNombreSujeto());
                break;
            case READ:
                this.ExecuteREADAction(instruccion.getNombreObjeto(), instruccion.getNombreSujeto());
                break;
            case WRITE:
                this.ExecuteWRITEAction(instruccion.getNombreObjeto(), instruccion.getNombreSujeto(), instruccion.getValor());
                break;
            case CREATE:
                this.ExecuteCREATEAction(instruccion.getNombreObjeto(), instruccion.getNombreSujeto());
                break;
            case DESTROY:
                this.ExecuteDESTROYAction(instruccion.getNombreObjeto(), instruccion.getNombreSujeto());
                break;
            case BAD:
                this.ExecuteBADAction();
                break;
            default:
                break;
        }
    }

    public void ExecuteRUNAction(String subjectName) throws IOException {
        String actionLog = "";
        //la instruccion RUN de lyle le permite hacer lo que tenga que hacer
        //para registrar en su estado interno, agregar al byte que se acaba de crear, 
        //y sacar el byte si se ha recibido los 8 bits para ese byte.
        if (subjectName == "lyle" && sendedBits.length() == 8) {
            sendedBits = "";
            transferDataToOutput();
        }

        actionLog = "RUN " + subjectName;
        logFile.InsertLogLine(actionLog);

    }

    public void transferDataToOutput() throws IOException {
        byte[] byteToStore = new byte[1];
        byteToStore[0] = Byte.parseByte(sendedBits, 2);

        try {
            RecordFile.getInstance("Prueba//" + fileRecordName).record(byteToStore);
        } catch (Exception e) {
            File file = new File("Prueba//" + fileRecordName);
            file.createNewFile();
            RecordFile.getInstance("Prueba//" + fileRecordName).record(byteToStore);
        }
    }

    public void setFileRecordName(String name) {
        this.fileRecordName = name;
    }

    public void ExecuteREADAction(String objectName, String subjectName) throws IOException
    {
        int valueRead = 0;
        String actionLog = "";
        SecuritySubject subject = objectManager.findSubjectByName(subjectName);
        SecurityObject object = objectManager.findObjectByName(objectName);
        if (objectManager.dominates(subject.getSecurityLevel(), object.getSecurityLevel())) {
            subject.setTEMP(objectManager.read(subjectName,objectName));
            valueRead = subject.getTEMP();
        } else {
            subject.setTEMP(0);
            valueRead = subject.getTEMP();
        }
        
        sendedBits += valueRead;
        actionLog = "READ " + subjectName + " " + objectName;
        logFile.InsertLogLine(actionLog);
    }

    public void ExecuteWRITEAction(String objectName, String subjectName, int value) {
    }

    public void ExecuteCREATEAction(String objectName, String subjectName) {
    }

    public void ExecuteDESTROYAction(String objectName, String subjectName) {
    }

    public void ExecuteBADAction() {
    }
}
