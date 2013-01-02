/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package esh.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author David
 */
@Entity
@NamedQueries({
    @NamedQuery( name = "Estado.findByNome", query = "from Estado where nome like :nome" ),
    @NamedQuery( name = "Estado.findBySigla", query = "from Estado where sigla like :sigla" ),
    @NamedQuery( name = "Estado.findByIdPais", query = "from Estado where pais.id = :idPais" ),
    @NamedQuery( name = "Estado.findByNomePais", query = "from Estado where pais.nome like :nomePais" )
})
public class Estado implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    @NotEmpty
    @Length( max = 60 )
    private String nome;
    
    @NotNull
    @NotEmpty
    @Column( unique = true )
    @Length( min = 2, max = 4 )
    private String sigla;
    
    @NotNull
    @ManyToOne
    private Pais pais;

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

    public Pais getPais() {
        return pais;
    }

    public void setPais( Pais pais ) {
        this.pais = pais;
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
        final Estado other = ( Estado ) obj;
        if ( !Objects.equals( this.id, other.id ) ) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode( this.id );
        return hash;
    }

    @Override
    public String toString() {
        return "Estado{" + "id=" + id + ", nome=" + nome + ", sigla=" + sigla + ", pais=" + pais + '}';
    }
    
}
