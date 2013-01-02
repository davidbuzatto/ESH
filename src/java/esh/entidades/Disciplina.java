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
public class Disciplina implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    @NotEmpty
    @Length( max = 40 )
    private String nome;
    
    @NotNull
    @NotEmpty
    @Length( min = 2, max = 6 )
    private String sigla;
    
    @NotNull
    private Long cargaSemanal;
    
    @NotNull
    @ManyToOne
    private SerieSemestreModulo serieSemestreModulo;

    public Long getCargaSemanal() {
        return cargaSemanal;
    }

    public void setCargaSemanal( Long cargaSemanal ) {
        this.cargaSemanal = cargaSemanal;
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

    public SerieSemestreModulo getSerieSemestreModulo() {
        return serieSemestreModulo;
    }

    public void setSerieSemestreModulo( SerieSemestreModulo serieSemestreModulo ) {
        this.serieSemestreModulo = serieSemestreModulo;
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
        final Disciplina other = ( Disciplina ) obj;
        if ( !Objects.equals( this.id, other.id ) ) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode( this.id );
        return hash;
    }

    @Override
    public String toString() {
        return "Disciplina{" + "id=" + id + ", nome=" + nome + ", sigla=" + sigla + ", cargaSemanal=" + cargaSemanal + ", serieSemestreModulo=" + serieSemestreModulo + '}';
    }
    
}
