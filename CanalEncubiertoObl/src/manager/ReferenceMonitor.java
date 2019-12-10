package manager;

import entities.InstruccionObjeto;
import entities.SecurityLevel;
import entities.SecurityObject;
import entities.SecuritySubject;
import entities.TipoInstruccion;
import fileAction.FileAction;
import files.FileStreamManager;
import files.LogFile;
import files.TextFileRecorder;
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
    private static RecordFile recordFile = null;
    private String textFileRecordingName = "test//log.txt";
    private FileStreamManager fileStreamManager = null;

    public static ReferenceMonitor getInstance() throws IOException {
        if (referenceMonitor == null) {
            referenceMonitor = new ReferenceMonitor();
        }
        return referenceMonitor;
    }

    private ReferenceMonitor() throws IOException {
        objectManager = ObjectManager.getInstance();
        logFile = LogFile.getInstance();
        logFile.CreateLog();
        sendedBits = "";
        recordFile = RecordFile.getInstance(sendedBits);
        fileStreamManager = FileStreamManager.getInstance();
    }

    //crea el objecto, recibe el nombre del objeto y ademas el seclevel de quien lo crea
    public void createObject(String nombreObjeto, String subjectName, SecurityLevel secLevel) throws IOException {
        String actionLog = "";
        try {
            boolean createObject = this.objectManager.CreateObject(nombreObjeto, secLevel);
            if (createObject) {
                actionLog = "CREATE " + subjectName + " " + nombreObjeto;
                logFile.InsertLogLine(actionLog);
            }
        } catch (Exception e) {
            System.out.println("Objeto ya existe. Operacion invalida");
        }

    }

    //asumiendo que el objeto ya existe
    //asumiendo que el sujeto tiene acceso a write sobre el objeto
    public void destroyObject(String nombreObjeto, String nombreSujeto) {
        String actionLog = "";
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
                    actionLog = "DESTROY " + nombreSujeto + " " + nombreObjeto;
                    logFile.InsertLogLine(actionLog);
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
            transferDataToOutput();
            sendedBits = "";
            
        }

        actionLog = "RUN " + subjectName;
        logFile.InsertLogLine(actionLog);

    }

    public void transferDataToOutput() throws IOException {
        byte[] byteToStore = new byte[1];
        byteToStore[0] = Byte.parseByte(sendedBits, 2);

        try {
            fileRecordName = fileStreamManager.getFileRecord();
            RecordFile.getInstance("test//" + fileRecordName).record(byteToStore,fileRecordName);
        } catch (Exception e) {
            File file = new File("test//" + fileRecordName);
            file.createNewFile();
            RecordFile.getInstance("test//" + fileRecordName).record(byteToStore, fileRecordName);
        }
    }

    public void setFileRecordName(String name) {
        this.fileRecordName = name;
    }

    public void ExecuteREADAction(String objectName, String subjectName) throws IOException {
        int valueRead = 0;
        String actionLog = "";
        SecuritySubject subject = objectManager.findSubjectByName(subjectName);
        SecurityObject object = objectManager.findObjectByName(objectName);
        if (objectManager.dominates(subject.getSecurityLevel(), object.getSecurityLevel())) {
            subject.setTEMP(objectManager.read(subjectName, objectName));
            valueRead = subject.getTEMP();
            actionLog = "READ " + subjectName + " " + objectName;
            logFile.InsertLogLine(actionLog);
        } else {
            subject.setTEMP(0);
            valueRead = subject.getTEMP();
        }

        sendedBits += valueRead;
    }

    public void ExecuteWRITEAction(String objectName, String subjectName, int value) throws IOException {
        String actionLog = "";
        SecuritySubject subject = objectManager.findSubjectByName(subjectName);
        SecurityObject object = objectManager.findObjectByName(objectName);
        boolean writeObject = false;
        if (objectManager.dominates(object.getSecurityLevel(), subject.getSecurityLevel())) {
            writeObject = objectManager.write(subjectName, objectName, value);
            if (writeObject) {
                actionLog = "WRITE " + subjectName + " " + objectName + " " + value;
                logFile.InsertLogLine(actionLog);
            }
        }
    }

    public void ExecuteCREATEAction(String objectName, String subjectName) throws IOException {
        SecuritySubject subject = objectManager.findSubjectByName(subjectName);
        this.createObject(objectName, subjectName, subject.getSecurityLevel());
    }

    public void ExecuteDESTROYAction(String objectName, String subjectName) {
        this.destroyObject(objectName, subjectName);
    }

    public void ExecuteBADAction() {
    }

    public void recordLastBits() throws IOException {
        if (sendedBits.length() > 0) {
            byte[] byteToStore = new byte[1];
            byteToStore[0] = Byte.parseByte(sendedBits, 2);
            sendedBits = "";
            recordFile.getInstance(fileRecordName).record(byteToStore, fileRecordName);
        }
        closeFiles();
    }

    public void closeFiles() throws IOException {
        TextFileRecorder.getInstance(textFileRecordingName).close();
        recordFile.getInstance(fileRecordName).close();
    }
}
