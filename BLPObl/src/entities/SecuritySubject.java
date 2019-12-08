//Clase: Representación de un Sujeto del modelo Bell Lapadula
//Motivo: Obligatorio de Aspectos de Seguridad 2019 - segundo semestre
//Autores: Ferraz (200112) - Zito (111725)


package entities;
public class SecuritySubject {
    
    private String Name;
    private SecurityLevel securityLevel;
    private int TEMP;
    
    public SecuritySubject()
    {
        this.Name = "";
        this.TEMP = 0;
        this.securityLevel = securityLevel.NOTHING;
    }
    
    public SecuritySubject(String name,SecurityLevel secLevel)
    {
        this.Name = name;
        this.securityLevel = secLevel;
    }

    public SecurityLevel getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(SecurityLevel securityLevel) {
        this.securityLevel = securityLevel;
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
