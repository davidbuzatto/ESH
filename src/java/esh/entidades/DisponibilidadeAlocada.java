/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package esh.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author David
 */
@Entity
public class DisponibilidadeAlocada implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    @ManyToOne
    private TipoAtividade tipoAtividade;
    
    @NotNull
    @ManyToOne
    private Disponibilidade disponibilidade;
    
    @NotNull
    @ManyToOne
    private HoraAtividadeModeloDisponibilidade horaAtividadeModeloDisponibilidade;

    public Disponibilidade getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade( Disponibilidade disponibilidade ) {
        this.disponibilidade = disponibilidade;
    }

    public HoraAtividadeModeloDisponibilidade getHoraAtividadeModeloDisponibilidade() {
        return horaAtividadeModeloDisponibilidade;
    }

    public void setHoraAtividadeModeloDisponibilidade( HoraAtividadeModeloDisponibilidade horaAtividadeModeloDisponibilidade ) {
        this.horaAtividadeModeloDisponibilidade = horaAtividadeModeloDisponibilidade;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public TipoAtividade getTipoAtividade() {
        return tipoAtividade;
    }

    public void setTipoAtividade( TipoAtividade tipoAtividade ) {
        this.tipoAtividade = tipoAtividade;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final DisponibilidadeAlocada other = ( DisponibilidadeAlocada ) obj;
        if ( !Objects.equals( this.id, other.id ) ) {
            return false;
        }
        if ( !Objects.equals( this.disponibilidade, other.disponibilidade ) ) {
            return false;
        }
        if ( !Objects.equals( this.horaAtividadeModeloDisponibilidade, other.horaAtividadeModeloDisponibilidade ) ) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode( this.id );
        return hash;
    }

    @Override
    public String toString() {
        return "DisponibilidadeAlocada{" + "id=" + id + ", tipoAtividade=" + tipoAtividade + ", disponibilidade=" + disponibilidade + ", horaAtividadeModeloDisponibilidade=" + horaAtividadeModeloDisponibilidade + '}';
    }
    
}
