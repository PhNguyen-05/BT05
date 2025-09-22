package vn.iotstar.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.iotstar.configs.JPAConfigs;
import vn.iotstar.dao.IVideoDao;
import vn.iotstar.entity.Video;

public class VideoDao implements IVideoDao {
    @Override
    public void insert(Video video) {
        EntityManager em = JPAConfigs.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(video);
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) trans.rollback();
            throw e;
        } finally { em.close(); }
    }

    @Override
    public void update(Video video) {
        EntityManager em = JPAConfigs.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(video);
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) trans.rollback();
            throw e;
        } finally { em.close(); }
    }

    @Override
    public void delete(String videoId) throws Exception {
        EntityManager em = JPAConfigs.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            Video v = em.find(Video.class, videoId);
            if (v != null) em.remove(v);
            else throw new Exception("Video not found");
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) trans.rollback();
            throw e;
        } finally { em.close(); }
    }

    @Override
    public Video findById(String videoId) {
        EntityManager em = JPAConfigs.getEntityManager();
        try { return em.find(Video.class, videoId); }
        finally { em.close(); }
    }

    @Override
    public List<Video> findAll() {
        EntityManager em = JPAConfigs.getEntityManager();
        try {
            return em.createNamedQuery("Video.findAll", Video.class).getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<Video> findByTitle(String title) {
        EntityManager em = JPAConfigs.getEntityManager();
        try {
            String jpql = "SELECT v FROM Video v WHERE v.title LIKE :t";
            return em.createQuery(jpql, Video.class)
                     .setParameter("t", "%" + title + "%")
                     .getResultList();
        } finally { em.close(); }
    }

    @Override
    public int count() {
        EntityManager em = JPAConfigs.getEntityManager();
        try {
            return ((Long) em.createQuery("SELECT COUNT(v) FROM Video v").getSingleResult()).intValue();
        } finally { em.close(); }
    }

    @Override
    public List<Video> findAll(int page, int pageSize) {
        EntityManager em = JPAConfigs.getEntityManager();
        try {
            return em.createNamedQuery("Video.findAll", Video.class)
                     .setFirstResult(page * pageSize)
                     .setMaxResults(pageSize)
                     .getResultList();
        } finally { em.close(); }
    }
    

}
