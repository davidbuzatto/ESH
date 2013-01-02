/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package esh.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author David
 */
@Entity
public class Licenca implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    @Temporal( TemporalType.TIMESTAMP )
    private Date dataCompra;
    
    @NotNull
    private Double valor;
    
    @NotNull
    @Temporal( TemporalType.TIMESTAMP )
    private Date inicio;
    
    @NotNull
    @Temporal( TemporalType.TIMESTAMP )
    private Date fim;
    
    @NotNull
    private Boolean valida;
    
    @NotNull
    @ManyToOne
    private Campus campus;

    public Campus getCampus() {
        return campus;
    }

    public void setCampus( Campus campus ) {
        this.campus = campus;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra( Date dataCompra ) {
        this.dataCompra = dataCompra;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim( Date fim ) {
        this.fim = fim;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio( Date inicio ) {
        this.inicio = inicio;
    }

    public Boolean getValida() {
        return valida;
    }

    public void setValida( Boolean valida ) {
        this.valida = valida;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor( Double valor ) {
        this.valor = valor;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Licenca other = ( Licenca ) obj;
        if ( !Objects.equals( this.id, other.id ) ) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode( this.id );
        return hash;
    }

    @Override
    public String toString() {
        return "Licenca{" + "id=" + id + ", dataCompra=" + dataCompra + ", valor=" + valor + ", inicio=" + inicio + ", fim=" + fim + ", valida=" + valida + ", campus=" + campus + '}';
    }
    
}
