package entities;
public class Subject {
    
    private String Name;
    private int TEMP;
    
    public Subject()
    {
        this.Name = "";
        this.TEMP = 0;
    }
    
    public Subject(String name, int temp)
    {
        this.Name = name;
        this.TEMP = temp;
    }
    
    public String getName() {
        return this.Name;
    }

    public void setName(String name) {
        this.Name = name;
    }
    
    public int getTEMP() {
        return this.TEMP;
    }

    public void setTEMP(int temp) {
        this.TEMP = temp;
    }
    
    
}
