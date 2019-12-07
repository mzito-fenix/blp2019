
package entities;

public class InstruccionObjeto {
    private TipoInstruccion tipo;
    private String nombreSujeto;
    private String nombreObjeto;
    private int valor;
    
    public InstruccionObjeto(TipoInstruccion _tipo){
        this.nombreObjeto = "";
        this.nombreSujeto = "";
        this.tipo = _tipo;
        this.valor = 0;
    }
    
    public InstruccionObjeto(TipoInstruccion _tipo, String _nombreSujeto){
        this.tipo = _tipo;
        this.nombreSujeto = _nombreSujeto;
        this.valor = 0;
        this.nombreObjeto = "";
    }
    
    public InstruccionObjeto(TipoInstruccion _tipo, String _nombreSujeto, String _nombreObjeto){
        this.valor = 0;
        this.tipo = _tipo;
        this.nombreObjeto = _nombreObjeto;
        this.nombreSujeto = _nombreSujeto;
    }
    
    public InstruccionObjeto(TipoInstruccion _tipo, String _nombreSujeto, String _nombreObjeto, int _valor){
        this.valor = _valor;
        this.tipo = _tipo;
        this.nombreObjeto = _nombreObjeto;
        this.nombreSujeto = _nombreSujeto;
    }

    public TipoInstruccion getTipo() {
        return tipo;
    }

    public void setTipo(TipoInstruccion tipo) {
        this.tipo = tipo;
    }

    public String getNombreSujeto() {
        return nombreSujeto;
    }

    public void setNombreSujeto(String nombreSujeto) {
        this.nombreSujeto = nombreSujeto;
    }

    public String getNombreObjeto() {
        return nombreObjeto;
    }

    public void setNombreObjeto(String nombreObjeto) {
        this.nombreObjeto = nombreObjeto;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    public static InstruccionObjeto validarInstruccions(String linea){
        return null;
    }
}
