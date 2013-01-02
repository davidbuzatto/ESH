/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package esh.entidades;

import esh.enumeracoes.TipoUsuario;
import esh.gson.ExcluirJSON;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.jasypt.hibernate4.type.EncryptedStringType;

/**
 *
 * @author David
 */
@TypeDef( name = "encryptedString", 
        typeClass = EncryptedStringType.class, 
        parameters = {
        @Parameter( name = "encryptorRegisteredName", value = "strongHibernateStringEncryptor" )
})
@NamedQueries({
    @NamedQuery( name = "Usuario.findByEmail", query = "from Usuario where email = :email" )
})
@Entity
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    @NotEmpty
    @Column( unique = true )
    @Length( max = 70 )
    private String email;
    
    @NotNull
    @NotEmpty
    @Length( max = 60 )
    @Type( type = "encryptedString" )
    @ExcluirJSON
    private String senha;
    
    @NotNull
    @NotEmpty
    @Length( max = 40 )
    private String nome;
    
    @NotNull
    @NotEmpty
    @Length( max = 50 )
    private String sobrenome;
    
    @NotNull
    private Boolean ativo;
    
    @NotNull
    @Enumerated( EnumType.ORDINAL )
    private TipoUsuario tipo;
    
    @ManyToOne
    private Campus campus;
    
    @ManyToOne
    private Area area;
    
    @ManyToOne
    private Curso curso;
    
    @ManyToOne
    private Professor professor;

    public Area getArea() {
        return area;
    }

    public void setArea( Area area ) {
        this.area = area;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo( Boolean ativo ) {
        this.ativo = ativo;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus( Campus campus ) {
        this.campus = campus;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso( Curso curso ) {
        this.curso = curso;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
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

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor( Professor professor ) {
        this.professor = professor;
    }
    
    public String getSenha() {
        return senha;
    }

    public void setSenha( String senha ) {
        this.senha = senha;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome( String sobrenome ) {
        this.sobrenome = sobrenome;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo( TipoUsuario tipo ) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Usuario other = ( Usuario ) obj;
        if ( !Objects.equals( this.id, other.id ) ) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode( this.id );
        return hash;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", email=" + email + ", senha=" + senha + ", nome=" + nome + ", sobrenome=" + sobrenome + ", ativo=" + ativo + ", tipo=" + tipo + ", campus=" + campus + ", area=" + area + ", curso=" + curso + ", professor=" + professor + '}';
    }
    
}
