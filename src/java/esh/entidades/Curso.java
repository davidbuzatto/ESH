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
public class Curso implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    @NotEmpty
    @Length( max = 40 )
    private String nome;
    
    @NotNull
    @ManyToOne
    private Area area;

    public Area getArea() {
        return area;
    }

    public void setArea( Area area ) {
        this.area = area;
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
        final Curso other = ( Curso ) obj;
        if ( !Objects.equals( this.id, other.id ) ) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode( this.id );
        return hash;
    }

    @Override
    public String toString() {
        return "Curso{" + "id=" + id + ", nome=" + nome + ", area=" + area + '}';
    }
    
}