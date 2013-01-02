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
public class ModeloDisponibilidade implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    @NotEmpty
    @Length( max = 40 )
    private String descricao;
    
    @NotNull
    private Long ano;
    
    @NotNull
    private Long semestre;
    
    @NotNull
    @ManyToOne
    private Campus campus;

    public Long getAno() {
        return ano;
    }

    public void setAno( Long ano ) {
        this.ano = ano;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus( Campus campus ) {
        this.campus = campus;
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

    public Long getSemestre() {
        return semestre;
    }

    public void setSemestre( Long semestre ) {
        this.semestre = semestre;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final ModeloDisponibilidade other = ( ModeloDisponibilidade ) obj;
        if ( !Objects.equals( this.id, other.id ) ) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode( this.id );
        return hash;
    }

    @Override
    public String toString() {
        return "ModeloDisponibilidade{" + "id=" + id + ", descricao=" + descricao + ", ano=" + ano + ", semestre=" + semestre + ", campus=" + campus + '}';
    }
    
}
