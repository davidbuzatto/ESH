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
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author David
 */
@Entity
public class ProfessorInstituicao implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    @NotEmpty
    @Length( max = 20 )
    private String prontuario;
    
    @NotNull
    @ManyToOne
    private Professor professor;
    
    @NotNull
    @ManyToOne
    private Instituicao instituicao;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao( Instituicao instituicao ) {
        this.instituicao = instituicao;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor( Professor professor ) {
        this.professor = professor;
    }

    public String getProntuario() {
        return prontuario;
    }

    public void setProntuario( String prontuario ) {
        this.prontuario = prontuario;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final ProfessorInstituicao other = ( ProfessorInstituicao ) obj;
        if ( !Objects.equals( this.id, other.id ) ) {
            return false;
        }
        if ( !Objects.equals( this.professor, other.professor ) ) {
            return false;
        }
        if ( !Objects.equals( this.instituicao, other.instituicao ) ) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode( this.id );
        return hash;
    }

    @Override
    public String toString() {
        return "ProfessorInstituicao{" + "id=" + id + ", prontuario=" + prontuario + ", professor=" + professor + ", instituicao=" + instituicao + '}';
    }
    
}
