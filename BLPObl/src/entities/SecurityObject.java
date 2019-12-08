//Clase: Representación de un Objeto del modelo Bell Lapadula
//Motivo: Obligatorio de Aspectos de Seguridad 2019 - segundo semestre
//Autores: Ferraz (200112) - Zito (111725)


package entities;

public class SecurityObject {
    private String Name;
    private int Value;
    private SecurityLevel securityLevel;

    public SecurityLevel getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(SecurityLevel securityLevel) {
        this.securityLevel = securityLevel;
    }
    
    public SecurityObject(String name, SecurityLevel securityLevel)
    {
        this.Name = name;
        this.securityLevel = securityLevel;
    }
    
    public SecurityObject()
    {
        this.Name = "";
        this.Value = 0;
        this.securityLevel = securityLevel.NOTHING;
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
