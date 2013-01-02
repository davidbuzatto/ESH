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
public class Disponibilidade implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    @ManyToOne
    private Professor professor;
    
    @NotNull
    @ManyToOne
    private ModeloDisponibilidade modeloDisponibilidade;

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
        final Disponibilidade other = ( Disponibilidade ) obj;
        if ( !Objects.equals( this.id, other.id ) ) {
            return false;
        }
        if ( !Objects.equals( this.professor, other.professor ) ) {
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
        hash = 89 * hash + Objects.hashCode( this.id );
        return hash;
    }

    @Override
    public String toString() {
        return "Disponibilidade{" + "id=" + id + ", professor=" + professor + ", modeloDisponibilidade=" + modeloDisponibilidade + '}';
    }
    
}
