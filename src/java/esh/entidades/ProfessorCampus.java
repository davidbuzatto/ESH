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
public class ProfessorCampus implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    @NotEmpty
    @Length( max = 20 )
    private String apelido;
    
    @NotNull
    @NotEmpty
    @Length( min = 7, max = 7 )
    private String corFundo;
    
    @NotNull
    @NotEmpty
    @Length( min = 7, max = 7 )
    private String corBorda;
    
    @NotNull
    @NotEmpty
    @Length( min = 7, max = 7 )
    private String corLabel;
    
    @NotNull
    @ManyToOne
    private Professor professor;
    
    @NotNull
    @ManyToOne
    private Campus campus;

    public String getApelido() {
        return apelido;
    }

    public void setApelido( String apelido ) {
        this.apelido = apelido;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus( Campus campus ) {
        this.campus = campus;
    }

    public String getCorBorda() {
        return corBorda;
    }

    public void setCorBorda( String corBorda ) {
        this.corBorda = corBorda;
    }

    public String getCorFundo() {
        return corFundo;
    }

    public void setCorFundo( String corFundo ) {
        this.corFundo = corFundo;
    }

    public String getCorLabel() {
        return corLabel;
    }

    public void setCorLabel( String corLabel ) {
        this.corLabel = corLabel;
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
        final ProfessorCampus other = ( ProfessorCampus ) obj;
        if ( !Objects.equals( this.id, other.id ) ) {
            return false;
        }
        if ( !Objects.equals( this.professor, other.professor ) ) {
            return false;
        }
        if ( !Objects.equals( this.campus, other.campus ) ) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode( this.id );
        return hash;
    }

    @Override
    public String toString() {
        return "ProfessorCampus{" + "id=" + id + ", apelido=" + apelido + ", corFundo=" + corFundo + ", corBorda=" + corBorda + ", corLabel=" + corLabel + ", professor=" + professor + ", campus=" + campus + '}';
    }
    
}
