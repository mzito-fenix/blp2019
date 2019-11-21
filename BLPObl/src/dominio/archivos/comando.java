/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio.archivos;

/**
 *
 * @author mzito
 */
public class comando {

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public String getParametro1() {
        return parametro1;
    }

    public void setParametro1(String parametro1) {
        this.parametro1 = parametro1;
    }

    public String getParametro2() {
        return parametro2;
    }

    public void setParametro2(String parametro2) {
        this.parametro2 = parametro2;
    }

    public String getParametro3() {
        return parametro3;
    }

    public void setParametro3(String parametro3) {
        this.parametro3 = parametro3;
    }
    private String comando;
    private String parametro1;
    private String parametro2;
    private String parametro3;

   
    
    public boolean Separar(String linea)
    {
        boolean devolucion=true;
        
        try {
             String[] resultado= linea.split(" ");
              this.comando= resultado[0];
              this.parametro1= resultado[1];
              this.parametro2= resultado[2];
              
              //no todos tienen que tener mas de dos parametros
              try{
                this.parametro3= resultado[3];
              }
              catch (Exception ex)
              {
                  
              }
            }
        catch (Exception e) {
            devolucion=false;
        }  
        return devolucion;
    }
    
    
    public boolean EsValido(String comando) {
        String [] comandosValidos = {"CREATE","WRITE","READ","DESTROY","RUN"};
        boolean resultado=false;
        int n;
        comando=comando.toUpperCase();
        String comandoActual;
        for(n=0;n<comandosValidos.length;n++){
            comandoActual=comandosValidos[n].toUpperCase();
            if(comando.equals(comandoActual))
               resultado=true;
        }
        return resultado;
      }
}
