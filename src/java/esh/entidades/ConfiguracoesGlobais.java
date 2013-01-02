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
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author David
 */
@Entity
public class ConfiguracoesGlobais implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    @NotEmpty
    @Length( max = 50 )
    private String emailSistema;
    
    @NotNull
    private Boolean producao;
    
    @NotNull
    private Boolean debug;

    public Boolean getDebug() {
        return debug;
    }

    public void setDebug( Boolean debug ) {
        this.debug = debug;
    }

    public String getEmailSistema() {
        return emailSistema;
    }

    public void setEmailSistema( String emailSistema ) {
        this.emailSistema = emailSistema;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public Boolean getProducao() {
        return producao;
    }

    public void setProducao( Boolean producao ) {
        this.producao = producao;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final ConfiguracoesGlobais other = ( ConfiguracoesGlobais ) obj;
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
        return "ConfiguracoesGlobais{" + "id=" + id + ", emailSistema=" + emailSistema + ", producao=" + producao + ", debug=" + debug + '}';
    }
    
}
