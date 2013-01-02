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
public class Sala implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    @NotEmpty
    @Length( max = 30 )
    private String nome;
    
    @NotNull
    private Long numero;
    
    @NotNull
    private Long andar;
    
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
    private Predio predio;

    public Long getAndar() {
        return andar;
    }

    public void setAndar( Long andar ) {
        this.andar = andar;
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

    public String getNome() {
        return nome;
    }

    public void setNome( String nome ) {
        this.nome = nome;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero( Long numero ) {
        this.numero = numero;
    }

    public Predio getPredio() {
        return predio;
    }

    public void setPredio( Predio predio ) {
        this.predio = predio;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Sala other = ( Sala ) obj;
        if ( !Objects.equals( this.id, other.id ) ) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode( this.id );
        return hash;
    }

    @Override
    public String toString() {
        return "Sala{" + "id=" + id + ", nome=" + nome + ", numero=" + numero + ", andar=" + andar + ", corFundo=" + corFundo + ", corBorda=" + corBorda + ", corLabel=" + corLabel + ", predio=" + predio + '}';
    }
    
}
