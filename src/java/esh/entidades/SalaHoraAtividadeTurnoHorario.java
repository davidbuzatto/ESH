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
public class SalaHoraAtividadeTurnoHorario implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    @ManyToOne
    private Sala sala;
    
    @NotNull
    @ManyToOne
    private HoraAtividadeTurnoHorario horaAtividadeTurnoHorario;

    public HoraAtividadeTurnoHorario getHoraAtividadeTurnoHorario() {
        return horaAtividadeTurnoHorario;
    }

    public void setHoraAtividadeTurnoHorario( HoraAtividadeTurnoHorario horaAtividadeTurnoHorario ) {
        this.horaAtividadeTurnoHorario = horaAtividadeTurnoHorario;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala( Sala sala ) {
        this.sala = sala;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final SalaHoraAtividadeTurnoHorario other = ( SalaHoraAtividadeTurnoHorario ) obj;
        if ( !Objects.equals( this.id, other.id ) ) {
            return false;
        }
        if ( !Objects.equals( this.sala, other.sala ) ) {
            return false;
        }
        if ( !Objects.equals( this.horaAtividadeTurnoHorario, other.horaAtividadeTurnoHorario ) ) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode( this.id );
        return hash;
    }

    @Override
    public String toString() {
        return "SalaHoraAtividadeTurnoHorario{" + "id=" + id + ", sala=" + sala + ", horaAtividadeTurnoHorario=" + horaAtividadeTurnoHorario + '}';
    }
    
}
