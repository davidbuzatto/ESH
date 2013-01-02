/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package esh.uteis;

/**
 * Parâmetros de uma consulta.
 * 
 * @author David Buzatto
 */
public class ParametroConsulta {
    
    private String campo;
    private String valor;
    private String tipo;

    public String getCampo() {
        return campo;
    }

    public void setCampo( String campo ) {
        this.campo = campo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo( String tipo ) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor( String valor ) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "TipoConsulta{" + "campo=" + campo + ", valor=" + valor + ", tipo=" + tipo + '}';
    }
    
}
