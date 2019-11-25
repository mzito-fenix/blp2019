package management;
import java.util.ArrayList;
import entities.Object;

public class ObjectManager {
    private ArrayList<Object> Objects;
    private static ObjectManager ObjectManager = null;
    
    //clase singleton
    private ObjectManager()
    {
        this.Objects = new ArrayList<Object>();
    }
    
    public static ObjectManager getInstance()
    {
       if (ObjectManager == null) {
            ObjectManager = new ObjectManager();
        }
        return ObjectManager;
    }
    
    public ArrayList<Object> getObjects()
    {
        return this.Objects;
    }
    
    //lee y escribe objetos por su nombre
    public void read(String objectName)
    {
    }
    
    public void write(String objectName)
    {
    }
}
