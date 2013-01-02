/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package esh.dao;

import java.math.BigInteger;
import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.*;

/**
 * DAO genérico.
 * 
 * @author David Buzatto
 */
public class Dao<T extends Object> {
    
    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;
    
    public Dao( EntityManager entityManager ) {
        this.entityManager = entityManager;
        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
    }
    
    /**
     * Salva ou atualiza uma instância de uma classe mapeada na unidade
     * de persistência.
     *
     * @param obj Objeto a ser salvo ou atualizado.
     */
    public void salvar( T obj ) {
        
        EntityTransaction t = entityManager.getTransaction();
        t.begin();
        
        entityManager.persist( obj );
        
        t.commit();
        
    }
    
    /**
     * Exclui uma instância de uma classe mapeada na unidade de persistência.
     *
     * @param obj Objeto a ser excluído.
     */
    public void excluir( T obj ) {
        
        EntityTransaction t = entityManager.getTransaction();
        t.begin();
        
        entityManager.remove( obj );
        
        t.commit();
        
    }
    
    /**
     * Atualiza a os atributos e a hierarquia de uma instância de um objeto.
     *
     * @param obj Objeto a ser atualizado.
     */
    public void atualizar( T obj ) {
        
        EntityTransaction t = entityManager.getTransaction();
        t.begin();
        
        entityManager.refresh( obj );
        
        t.commit();
        
    }
    
    /**
     * Executa o rollback na transação ativa.
     */
    public void rollback() {
        EntityTransaction t = entityManager.getTransaction();
        if ( t.isActive() ) {
            t.rollback();
        }
    }

    /**
     * Carrega um objeto de uma determinada classe mapeada na unidade de
     * persistência, usando como base o identificador do objeto.
     *
     * @param klass Classe mapeada.
     * @param id Identificador do objeto da classe mapeada.
     * @return O objeto com o identificador especificado.
     */
    public T carregar( Class klass, Object id ) {
        return (T) entityManager.find( klass, id );
    }
    
    /**
     * Carrega um objeto de uma determinada classe mapeada na unidade de
     * persistência, usando como base o identificador do objeto.
     *
     * @param klass Classe mapeada.
     * @param id Identificador do objeto da classe mapeada.
     * @return O objeto com o identificador especificado.
     */
    public Object carregarSemGenerics( Class klass, Object id ) {
        return entityManager.find( klass, id );
    }

    /**
     * Carrega todos os objetos de uma determinada classe mapeada na unidade de
     * persistência.
     *
     * @param klass Classe mapeada.
     * @return Lista de objetos da classe mapeada.
     */
    public List<T> carregarTodos( Class klass ) {
        return (List<T>) entityManager.createQuery( 
                "from " + klass.getSimpleName() ).getResultList();
    }
    
    /**
     * Carrega todos os objetos de uma determinada classe mapeada na unidade de
     * persistência usando limite.
     *
     * @param klass Classe mapeada.
     * @return Lista de objetos da classe mapeada.
     */
    public List<T> carregarTodosNoIntervalo( Class klass, int inicio, int quantidade ) {
        
        Query query = entityManager.createQuery( "from " + klass.getSimpleName() );
        
        query.setFirstResult( inicio );
        query.setMaxResults( quantidade );
        
        return (List<T>) query.getResultList();
        
    }
    
    /**
     * Obtém a quantidade de registros de um a determinada entidade.
     * 
     * @param klass Classe mapeada.
     * @return Quantidade de registros da classe mapeada.
     */
    public int obterQuantidadeDeRegistros( Class klass ) {
        
        Query query = entityManager.createNativeQuery( "SELECT COUNT(*) FROM " + klass.getSimpleName() );
        return ( ( BigInteger ) query.getSingleResult() ).intValue();
        
    }
    
    /**
     * Cria uma namedQuery.
     *
     * @param namedQuery Nome da query a ser criada.
     * @return Query.
     */
    public Query createNamedQuery( String namedQuery ) {
        return entityManager.createNamedQuery( namedQuery );
    }

    /**
     * Executa uma query genérica.
     *
     * @param query Query a ser executada.
     * @return Lista de objetos resultantes.
     */
    public List query( String query ) {
        return entityManager.createQuery( query ).getResultList();
    }

    /**
     * Cria uma query genérica.
     *
     * @param query Query a ser criada.
     * @return Query criada.
     */
    public Query createQuery( String query ) {
        return entityManager.createQuery( query );
    }

    /**
     * Cria uma query usando criteria.
     * @return Uma criteria query.
     */
    public CriteriaQuery createCriteriaQuery() {
        return criteriaBuilder.createQuery();
    }

    /**
     * Cria uma query usando criteria.
     * @param type Tipo
     * @return Uma criteria query.
     */
    public CriteriaQuery createCriteriaQuery( Class type ) {
        return criteriaBuilder.createQuery( type );
    }

    /**
     * Cria uma tuple query.
     * @return Tuple query.
     */
    public CriteriaQuery createTupleQuery() {
        return criteriaBuilder.createTupleQuery();
    }
    
    /**
     * Obtém o gerenciados de entidades usado neste Dao.
     * 
     * @return Gerenriador de entidades usado no Dao.
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }
    
    /**
     * Fecha o gerenciador de entidades desse Dao.
     */
    public void fecharEntityManager() {
        entityManager.close();
    }
    
}
