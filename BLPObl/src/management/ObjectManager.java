package management;
import java.util.ArrayList;
import entities.SecurityObject;
import entities.SecuritySubject;

public class ObjectManager {
    private ArrayList<SecurityObject> Objects;
    private ArrayList<SecuritySubject> Subject;
    private static ObjectManager ObjectManager = null;
    
    //clase singleton
    private ObjectManager()
    {
        this.Objects = new ArrayList<SecurityObject>();
    }
    
    public static ObjectManager getInstance()
    {
       if (ObjectManager == null) {
            ObjectManager = new ObjectManager();
        }
        return ObjectManager;
    }
    
    public ArrayList<SecurityObject> getObjects()
    {
        return this.Objects;
    }
    
    
    
    //lee y escribe objetos por su nombre
    public int read(String subjectName, String objectName)
    {
        return 0;
    }
    
    public int write(String subjectName, String objectName, int val)
    {
        return 0;
    }
}
