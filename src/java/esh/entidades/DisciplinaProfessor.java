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
public class DisciplinaProfessor implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    @ManyToOne
    private Disciplina disciplina;
    
    @NotNull
    @ManyToOne
    private Professor professor;
    
    @NotNull
    @ManyToOne
    private Horario horario;

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina( Disciplina disciplina ) {
        this.disciplina = disciplina;
    }

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

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor( Professor professor ) {
        this.professor = professor;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final DisciplinaProfessor other = ( DisciplinaProfessor ) obj;
        if ( !Objects.equals( this.id, other.id ) ) {
            return false;
        }
        if ( !Objects.equals( this.disciplina, other.disciplina ) ) {
            return false;
        }
        if ( !Objects.equals( this.professor, other.professor ) ) {
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
        return "DisciplinaProfessor{" + "id=" + id + ", disciplina=" + disciplina + ", professor=" + professor + ", horario=" + horario + '}';
    }
    
}
