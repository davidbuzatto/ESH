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
public class Horario implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    private Long ano;
    
    @NotNull
    private Long semestre;
    
    @NotNull
    @ManyToOne
    private SerieSemestreModulo serieSemestreModulo;

    public Long getAno() {
        return ano;
    }

    public void setAno( Long ano ) {
        this.ano = ano;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public Long getSemestre() {
        return semestre;
    }

    public void setSemestre( Long semestre ) {
        this.semestre = semestre;
    }

    public SerieSemestreModulo getSerieSemestreModulo() {
        return serieSemestreModulo;
    }

    public void setSerieSemestreModulo( SerieSemestreModulo serieSemestreModulo ) {
        this.serieSemestreModulo = serieSemestreModulo;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Horario other = ( Horario ) obj;
        if ( !Objects.equals( this.id, other.id ) ) {
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
        return "Horario{" + "id=" + id + ", ano=" + ano + ", semestre=" + semestre + ", serieSemestreModulo=" + serieSemestreModulo + '}';
    }
    
}
