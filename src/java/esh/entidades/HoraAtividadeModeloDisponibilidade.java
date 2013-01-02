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
public class HoraAtividadeModeloDisponibilidade implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    private Long ordem;
    
    @NotNull
    @ManyToOne
    private HoraAtividade horaAtividade;
    
    @NotNull
    @ManyToOne
    private ModeloDisponibilidade modeloDisponibilidade;

    public HoraAtividade getHoraAtividade() {
        return horaAtividade;
    }

    public void setHoraAtividade( HoraAtividade horaAtividade ) {
        this.horaAtividade = horaAtividade;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public ModeloDisponibilidade getModeloDisponibilidade() {
        return modeloDisponibilidade;
    }

    public void setModeloDisponibilidade( ModeloDisponibilidade modeloDisponibilidade ) {
        this.modeloDisponibilidade = modeloDisponibilidade;
    }

    public Long getOrdem() {
        return ordem;
    }

    public void setOrdem( Long ordem ) {
        this.ordem = ordem;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final HoraAtividadeModeloDisponibilidade other = ( HoraAtividadeModeloDisponibilidade ) obj;
        if ( !Objects.equals( this.id, other.id ) ) {
            return false;
        }
        if ( !Objects.equals( this.horaAtividade, other.horaAtividade ) ) {
            return false;
        }
        if ( !Objects.equals( this.modeloDisponibilidade, other.modeloDisponibilidade ) ) {
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
        return "HoraAtividadeModeloDisponibilidade{" + "id=" + id + ", ordem=" + ordem + ", horaAtividade=" + horaAtividade + ", modeloDisponibilidade=" + modeloDisponibilidade + '}';
    }
    
}
