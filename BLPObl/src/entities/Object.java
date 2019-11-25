package entities;

public class Object {
    private String Name;
    private int Value;
    
    public Object(String name, int value)
    {
        this.Name = name;
        this.Value = value;
    }
    
    public Object()
    {
        this.Name = "";
        this.Value = 0;
    }
    
     public String getName() {
        return this.Name;
    }

    public void setName(String name) {
        this.Name = name;
    }
    
    public int getValue() {
        return this.Value;
    }

    public void setValue(int value) {
        this.Value = value;
    }
}
