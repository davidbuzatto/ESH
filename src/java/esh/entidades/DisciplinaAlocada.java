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
public class DisciplinaAlocada implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    @ManyToOne
    private DisciplinaProfessor disciplinaProfessor;
    
    @ManyToOne
    private HoraAtividadeTurnoHorario horaAtividadeTurnoHorario;

    public DisciplinaProfessor getDisciplinaProfessor() {
        return disciplinaProfessor;
    }

    public void setDisciplinaProfessor( DisciplinaProfessor disciplinaProfessor ) {
        this.disciplinaProfessor = disciplinaProfessor;
    }

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

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final DisciplinaAlocada other = ( DisciplinaAlocada ) obj;
        if ( !Objects.equals( this.id, other.id ) ) {
            return false;
        }
        if ( !Objects.equals( this.disciplinaProfessor, other.disciplinaProfessor ) ) {
            return false;
        }
        if ( !Objects.equals( this.horaAtividadeTurnoHorario, other.horaAtividadeTurnoHorario ) ) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode( this.id );
        return hash;
    }

    @Override
    public String toString() {
        return "DisciplinaAlocada{" + "id=" + id + ", disciplinaProfessor=" + disciplinaProfessor + ", horaAtividadeTurnoHorario=" + horaAtividadeTurnoHorario + '}';
    }
    
}
