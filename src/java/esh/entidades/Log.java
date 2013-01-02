/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package esh.entidades;

import esh.enumeracoes.TipoOperacaoLog;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class Log implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    @NotEmpty
    @Length( max = 200 )
    private String descricao;
    
    @NotNull
    @Enumerated( EnumType.ORDINAL )
    private TipoOperacaoLog tipoOperacao;
    
    @NotNull
    @ManyToOne
    private Usuario usuario;

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

    public TipoOperacaoLog getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao( TipoOperacaoLog tipoOperacao ) {
        this.tipoOperacao = tipoOperacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario( Usuario usuario ) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Log other = ( Log ) obj;
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
        return "Log{" + "id=" + id + ", descricao=" + descricao + ", tipoOperacao=" + tipoOperacao + ", usuario=" + usuario + '}';
    }
    
}
