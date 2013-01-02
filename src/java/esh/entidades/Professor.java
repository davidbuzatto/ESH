/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package esh.entidades;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author David
 */
@Entity
public class Professor implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    @NotEmpty
    @Length( max = 40 )
    private String nome;
    
    @NotNull
    @NotEmpty
    @Length( max = 50 )
    private String sobrenome;
    
    @NotNull
    @NotEmpty
    @Length( max = 20 )
    private String telefoneResidencial;
    
    @NotNull
    @NotEmpty
    @Length( max = 20 )
    private String telefoneCelular;
    
    @NotNull
    @ManyToMany
    private Set<Area> areas;

    public Set<Area> getAreas() {
        return areas;
    }

    public void setAreas( Set<Area> areas ) {
        this.areas = areas;
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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome( String sobrenome ) {
        this.sobrenome = sobrenome;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular( String telefoneCelular ) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getTelefoneResidencial() {
        return telefoneResidencial;
    }

    public void setTelefoneResidencial( String telefoneResidencial ) {
        this.telefoneResidencial = telefoneResidencial;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Professor other = ( Professor ) obj;
        if ( !Objects.equals( this.id, other.id ) ) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode( this.id );
        return hash;
    }

    @Override
    public String toString() {
        return "Professor{" + "id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", telefoneResidencial=" + telefoneResidencial + ", telefoneCelular=" + telefoneCelular + ", areas=" + areas + '}';
    }
    
}
