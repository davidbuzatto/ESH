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
    @NamedQuery( name = "Campus.findByNome", query = "from Campus where nome like :nome" ),
    @NamedQuery( name = "Campus.findByNomeInstituicao", query = "from Campus where instituicao.nome like :nomeInstituicao" ),
    @NamedQuery( name = "Campus.findByNomeCidade", query = "from Campus where cidade.nome like :nomeCidade" )
})
public class Campus implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    @NotEmpty
    @Length( max = 100 )
    private String nome;
    
    @NotNull
    @ManyToOne
    private Instituicao instituicao;
    
    @NotNull
    @ManyToOne
    private Cidade cidade;

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade( Cidade cidade ) {
        this.cidade = cidade;
    }

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
        final Campus other = ( Campus ) obj;
        if ( !Objects.equals( this.id, other.id ) ) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode( this.id );
        return hash;
    }

    @Override
    public String toString() {
        return "Campus{" + "id=" + id + ", nome=" + nome + ", instituicao=" + instituicao + ", cidade=" + cidade + '}';
    }
    
}
