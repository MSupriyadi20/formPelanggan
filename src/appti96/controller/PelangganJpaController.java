/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appti96.controller;

import appti96.controller.exceptions.NonexistentEntityException;
import appti96.controller.exceptions.PreexistingEntityException;
import appti96.models.Pelanggan;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ASUS
 */
public class PelangganJpaController implements Serializable {

    public PelangganJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pelanggan pelanggan) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pelanggan);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPelanggan(pelanggan.getKodepelanggan()) != null) {
                throw new PreexistingEntityException("Pelanggan " + pelanggan + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pelanggan pelanggan) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pelanggan = em.merge(pelanggan);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = pelanggan.getKodepelanggan();
                if (findPelanggan(id) == null) {
                    throw new NonexistentEntityException("The pelanggan with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pelanggan pelanggan;
            try {
                pelanggan = em.getReference(Pelanggan.class, id);
                pelanggan.getKodepelanggan();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pelanggan with id " + id + " no longer exists.", enfe);
            }
            em.remove(pelanggan);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pelanggan> findPelangganEntities() {
        return findPelangganEntities(true, -1, -1);
    }

    public List<Pelanggan> findPelangganEntities(int maxResults, int firstResult) {
        return findPelangganEntities(false, maxResults, firstResult);
    }

    private List<Pelanggan> findPelangganEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pelanggan.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Pelanggan findPelanggan(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pelanggan.class, id);
        } finally {
            em.close();
        }
    }

    public int getPelangganCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pelanggan> rt = cq.from(Pelanggan.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
