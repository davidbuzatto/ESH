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
    @NamedQuery( name = "Cidade.findByNome", query = "from Cidade where nome like :nome" ),
    @NamedQuery( name = "Cidade.findByIdEstado", query = "from Cidade where estado.id = :idEstado" ),
    @NamedQuery( name = "Cidade.findByNomeEstado", query = "from Cidade where estado.nome like :nomeEstado" ),
    @NamedQuery( name = "Cidade.findBySiglaEstado", query = "from Cidade where estado.sigla like :siglaEstado" )
})
public class Cidade implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    @NotEmpty
    @Length( max = 60 )
    private String nome;
    
    @NotNull
    @ManyToOne
    private Estado estado;

    public Estado getEstado() {
        return estado;
    }

    public void setEstado( Estado estado ) {
        this.estado = estado;
    }

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

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Cidade other = ( Cidade ) obj;
        if ( !Objects.equals( this.id, other.id ) ) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode( this.id );
        return hash;
    }

    @Override
    public String toString() {
        return "Cidade{" + "id=" + id + ", nome=" + nome + ", estado=" + estado + '}';
    }
    
}
