/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package esh.entidades;

import esh.enumeracoes.DiaSemana;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author David
 */
@Entity
public class HoraAtividade implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    @NotEmpty
    @Length( max = 30 )
    private String descricao;
    
    @NotNull
    @Temporal( TemporalType.TIMESTAMP )
    private Date inicio;
    
    @NotNull
    @Temporal( TemporalType.TIMESTAMP )
    private Date fim;
    
    @NotNull
    @Enumerated( EnumType.ORDINAL )
    private DiaSemana diaSemana;
    
    @NotNull
    @ManyToOne
    private Campus campus;

    public Campus getCampus() {
        return campus;
    }

    public void setCampus( Campus campus ) {
        this.campus = campus;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao( String descricao ) {
        this.descricao = descricao;
    }

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana( DiaSemana diaSemana ) {
        this.diaSemana = diaSemana;
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

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final HoraAtividade other = ( HoraAtividade ) obj;
        if ( !Objects.equals( this.id, other.id ) ) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode( this.id );
        return hash;
    }

    @Override
    public String toString() {
        return "HoraAtividade{" + "id=" + id + ", descricao=" + descricao + ", inicio=" + inicio + ", fim=" + fim + ", campus=" + campus + '}';
    }
    
}
