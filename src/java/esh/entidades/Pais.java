/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package esh.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author David
 */
@Entity
@NamedQueries({
    @NamedQuery( name = "Pais.findByNome", query = "from Pais where nome like :nome" ),
    @NamedQuery( name = "Pais.findBySigla", query = "from Pais where sigla like :sigla" )
})
public class Pais implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    @NotEmpty
    @Length( max = 100 )
    private String nome;
    
    @NotNull
    @NotEmpty
    @Column( unique = true )
    @Length( min = 2, max = 4 )
    private String sigla;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome( String nome ) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla( String sigla ) {
        this.sigla = sigla;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Pais other = ( Pais ) obj;
        if ( !Objects.equals( this.id, other.id ) ) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode( this.id );
        return hash;
    }

    @Override
    public String toString() {
        return "Pais{" + "id=" + id + ", nome=" + nome + '}';
    }
    
}
