/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author mzito
 */
public class comando {


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
    private OperationType comando;

    public OperationType getComando() {
        return comando;
    }

    public void setComando(OperationType comando) {
        this.comando = comando;
    }
    private String parametro1;
    private String parametro2;
    private String parametro3;

    public boolean Separar(String linea) {
        boolean devolucion = true;

        try {
            String[] resultado = linea.split(" ");
            this.comando = this.resolverOperacion(resultado[0]);
            this.parametro1 = resultado[1];
            this.parametro2 = resultado[2];

            this.parametro1= parametro1.toUpperCase();
            this.parametro2= parametro2.toUpperCase();
            
            //no todos tienen que tener mas de dos parametros
            try {
                this.parametro3 = resultado[3];
            } catch (Exception ex) {

            }
        } catch (Exception e) {
            devolucion = false;
        }
        return devolucion;
    }

    public boolean EsValido(String comando, String param1, String param2, String param3) {
        String[] comandosValidos = {"CREATE", "WRITE", "READ", "DESTROY", "RUN"};
        boolean resultado = false;
        int n;
        comando = comando.toUpperCase();
        String comandoActual;
        for (n = 0; n < comandosValidos.length; n++) {
            comandoActual = comandosValidos[n].toUpperCase();
            if (comando.equals(comandoActual)) {
                resultado = true;
            }
        }
        if (resultado) {
            if (comando.compareTo("READ") == 0) {
                resultado = (param1 != "" && param2 != "");
            }
            if (comando.compareTo("WRITE") == 0) {
                resultado = (param1 != "" && param2 != "" && param3 != "");
            }            
        }
        
        return resultado;
    }
    
    private OperationType resolverOperacion(String Nombre)
    {
        Nombre = Nombre.toUpperCase();
        System.out.println(Nombre);
        if (Nombre.compareTo("READ") == 0){
                return OperationType.READ;
        }
        else if (Nombre.compareTo("WRITE") == 0) {
                return OperationType.WRITE;
        }
        else
            return OperationType.BAD_INSTRUCTION;
    }
    
}
