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
public class TipoAtividade implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    @NotEmpty
    @Length( max = 30 )
    private String descricao;
    
    @NotNull
    @NotEmpty
    @Length( min = 2, max = 6 )
    private String sigla;
    
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
    private Campus campus;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao( String descricao ) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
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
        final TipoAtividade other = ( TipoAtividade ) obj;
        if ( !Objects.equals( this.id, other.id ) ) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode( this.id );
        return hash;
    }

    @Override
    public String toString() {
        return "TipoAtividade{" + "id=" + id + ", descricao=" + descricao + ", sigla=" + sigla + ", corFundo=" + corFundo + ", corBorda=" + corBorda + ", corLabel=" + corLabel + ", campus=" + campus + '}';
    }
    
}
