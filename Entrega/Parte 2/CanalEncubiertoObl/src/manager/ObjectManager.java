package manager;

import entities.SecurityLevel;
import java.util.ArrayList;
import entities.SecurityObject;
import entities.SecuritySubject;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ObjectManager {

    private ArrayList<SecurityObject> Objects;
    private ArrayList<SecuritySubject> Subjects;
    private static ObjectManager ObjectManager = null;

    //clase singleton
    private ObjectManager() {
        this.Objects = new ArrayList<SecurityObject>();
        this.Subjects = new ArrayList<SecuritySubject>();
    }

    public static ObjectManager getInstance() {
        if (ObjectManager == null) {
            ObjectManager = new ObjectManager();
        }
        return ObjectManager;
    }

    public ArrayList<SecurityObject> getObjects() {
        return this.Objects;
    }

    public ArrayList<SecuritySubject> getSubjects() {
        return this.Subjects;
    }

    public boolean CreateObject(String Nombre, SecurityLevel nivel) {
        boolean resultado = true;
        try {
            if (!ExistObject(Nombre)) {
                SecurityObject newObject = new SecurityObject();
                newObject.setName(Nombre);
                newObject.setSecurityLevel(nivel);
                newObject.setValue(0);
                Objects.add(newObject);
            } else {
                resultado = false;
            }
        } catch (Exception e) {
            resultado = false;
        }

        return resultado;
    }

    public boolean ExistSubject(String Nombre) {
        boolean resultado = false;
        SecuritySubject resultadoBusqueda = new SecuritySubject();
        resultadoBusqueda = findSubjectByName(Nombre);
        if (resultadoBusqueda.getName().length() > 0) {
            resultado = true;
        }

        return resultado;
    }

    public boolean ExistObject(String Nombre) {
        boolean resultado = false;
        SecurityObject resultadoBusqueda = new SecurityObject();
        resultadoBusqueda = findObjectByName(Nombre);
        if (resultadoBusqueda.getName().length() > 0) {
            resultado = true;
        }

        return resultado;
    }

    public boolean dominates(SecurityLevel level1, SecurityLevel level2) {
        if (level1 == SecurityLevel.LOW && level2 == SecurityLevel.HIGH) {
            return false;
        }
        return true;
    }

    public void destroyObject(String nombreObjeto) {
        SecurityObject object = findObjectByName(nombreObjeto);
        Objects.remove(object);

    }

    //lee y escribe objetos por su nombre
    public int read(String subjectName, String objectName) {
        int resultado = 0;
        if (ExistObject(objectName)) {
            //Busco el objeto y de encontrarlo, me quedo con el valor
            SecurityObject objetoBuscado = new SecurityObject();
            objetoBuscado = findObjectByName(objectName);
            if (ExistSubject(subjectName)) {
                for (int x = 0; x < Subjects.size(); x++) {
                    if (subjectName.compareTo(Subjects.get(x).getName()) == 0) {
                        Subjects.get(x).setTEMP(objetoBuscado.getValue());
                        resultado = objetoBuscado.getValue();
                    }
                }
            }
        }
        return resultado;
    }

    public boolean write(String subjectName, String objectName, int value) throws IOException {
        boolean resultado = false;
        if (ExistObject(objectName)) {
            if (ExistSubject(subjectName)) {
                for (int x = 0; x < Objects.size(); x++) {
                    if (objectName.compareTo(Objects.get(x).getName()) == 0) {
                        Objects.get(x).setValue(value);
                        resultado = true;
                    }
                }
            }
        }
        return resultado;
    }

    public SecuritySubject findSubjectByName(String Nombre) {
        SecuritySubject resultado = new SecuritySubject();
        for (int x = 0; x < Subjects.size(); x++) {
            if (Nombre.compareTo(Subjects.get(x).getName()) == 0) {
                resultado = Subjects.get(x);
            }
        }
        return resultado;
    }

    public SecurityObject findObjectByName(String Nombre) {
        SecurityObject resultado = new SecurityObject();
        for (int x = 0; x < Objects.size(); x++) {
            if (Nombre.compareTo(Objects.get(x).getName()) == 0) {
                resultado = Objects.get(x);
            }
        }

        return resultado;
    }

    public boolean createSubject(String Nombre, SecurityLevel nivel) {
        boolean resultado = true;
        try {
            if (!ExistSubject(Nombre)) {
                SecuritySubject newSubject = new SecuritySubject();
                newSubject.setName(Nombre);
                newSubject.setSecurityLevel(nivel);
                newSubject.setTEMP(0);
                Subjects.add(newSubject);
            }
        } catch (Exception e) {
            resultado = false;
        }

        return resultado;
    }
}
