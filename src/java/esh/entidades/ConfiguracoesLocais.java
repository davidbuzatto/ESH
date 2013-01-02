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
public class ConfiguracoesLocais implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    @NotEmpty
    @Length( max = 50 )
    private String caminhoServidorLDAP;
    
    @NotNull
    @ManyToOne
    private Campus campus;

    public String getCaminhoServidorLDAP() {
        return caminhoServidorLDAP;
    }

    public void setCaminhoServidorLDAP( String caminhoServidorLDAP ) {
        this.caminhoServidorLDAP = caminhoServidorLDAP;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus( Campus campus ) {
        this.campus = campus;
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
        final ConfiguracoesLocais other = ( ConfiguracoesLocais ) obj;
        if ( !Objects.equals( this.id, other.id ) ) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode( this.id );
        return hash;
    }

    @Override
    public String toString() {
        return "ConfiguracoesLocais{" + "id=" + id + ", caminhoServidorLDAP=" + caminhoServidorLDAP + ", campus=" + campus + '}';
    }
    
}
