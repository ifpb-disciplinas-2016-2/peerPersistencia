/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.beerpersistencia;



import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author laerton
 */
public class GenericPersiste<T> {

    
    
    
    
    public T create(T obj) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DAC-PU");
        EntityManager em = emf.createEntityManager(); 
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
        em.close();
        emf.close();
        return obj;
    }

   
    
    public void edit(T obj) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DAC-PU");
        EntityManager em = emf.createEntityManager(); 
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }



    
    public void destroy(Class<T> classe, Long id) {
        T f = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DAC-PU");
        EntityManager em = emf.createEntityManager(); 
        try {
            f = this.findEntity(classe, id);
        } catch (Exception ex) {
            Logger.getLogger(GenericPersiste.class.getName()).log(Level.SEVERE, null, ex);
        }
        em.getTransaction().begin();
        em.remove(em.merge(f));
        em.getTransaction().commit();
        em.close();
        emf.close();
        
    }

    
    public T findEntity(Class<T> classe, Long id) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DAC-PU");
        EntityManager em = emf.createEntityManager(); 
        
        T find = em.find(classe, id);
        if (find == null){
            throw new Exception("Dados não localizados");
        }
        em.close();
        emf.close();
        return find;

    }

    
    public List<T> findEntities(Class<T> classe) {
        Query resposta;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DAC-PU");
        EntityManager em = emf.createEntityManager(); 
        resposta = em.createQuery("Select e from " + classe.getName() +" as e", classe);
        em.close();
        emf.close();
        return resposta.getResultList();
    }

    
    public List<T> findEntities(Class<T> classe, int maxResults, int firstResult) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DAC-PU");
        EntityManager em = emf.createEntityManager(); 
        Query q = em.createQuery("Select e from " + classe.getName() +" as e", classe);
        q.setMaxResults(maxResults);
        q.setFirstResult(firstResult);
        em.close();
        emf.close();
        return q.getResultList();
    }

    
    public int getEntityCount(Class<T> classe) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DAC-PU");
        EntityManager em = emf.createEntityManager(); 
        Query q = em.createQuery("Select e from " + classe.getName() +" as e", classe);
        em.close();
        emf.close();
        return ((Long) q.getSingleResult()).intValue();
    }

    
    
}
