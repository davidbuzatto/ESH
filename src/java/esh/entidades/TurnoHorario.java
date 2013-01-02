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
public class TurnoHorario implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    private Long ordem;
    
    @NotNull
    @ManyToOne
    private Turno turno;
    
    @NotNull
    @ManyToOne
    private Horario horario;

    public Horario getHorario() {
        return horario;
    }

    public void setHorario( Horario horario ) {
        this.horario = horario;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public Long getOrdem() {
        return ordem;
    }

    public void setOrdem( Long ordem ) {
        this.ordem = ordem;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno( Turno turno ) {
        this.turno = turno;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final TurnoHorario other = ( TurnoHorario ) obj;
        if ( !Objects.equals( this.id, other.id ) ) {
            return false;
        }
        if ( !Objects.equals( this.turno, other.turno ) ) {
            return false;
        }
        if ( !Objects.equals( this.horario, other.horario ) ) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode( this.id );
        return hash;
    }

    @Override
    public String toString() {
        return "TurnoHorario{" + "id=" + id + ", ordem=" + ordem + ", turno=" + turno + ", horario=" + horario + '}';
    }
    
}
