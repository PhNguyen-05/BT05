
package vn.iotstar.dao.impl;

import java.util.List;
import jakarta.persistence.*;
import vn.iotstar.configs.JPAConfigs;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.entity.User;

public class UserDao implements IUserDao {

    @Override
    public void insert(User user) {
        EntityManager em = JPAConfigs.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(user);
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(User user) {
        EntityManager em = JPAConfigs.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(int userId) throws Exception {
        EntityManager em = JPAConfigs.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            User u = em.find(User.class, userId);
            if (u != null) em.remove(u);
            else throw new Exception("User not found");
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public User findById(int userId) {
        EntityManager em = JPAConfigs.getEntityManager();
        try {
            return em.find(User.class, userId);
        } finally {
            em.close();
        }
    }

    @Override
    public List<User> findAll() {
        EntityManager em = JPAConfigs.getEntityManager();
        try {
            return em.createNamedQuery("User.findAll", User.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<User> findByUsername(String username) {
        EntityManager em = JPAConfigs.getEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.username LIKE :name";
            return em.createQuery(jpql, User.class)
                     .setParameter("name", "%" + username + "%")
                     .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public int count() {
        EntityManager em = JPAConfigs.getEntityManager();
        try {
            return ((Long) em.createQuery("SELECT COUNT(u) FROM User u").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    @Override
    public List<User> findAll(int page, int pageSize) {
        EntityManager em = JPAConfigs.getEntityManager();
        try {
            return em.createNamedQuery("User.findAll", User.class)
                     .setFirstResult(page * pageSize)
                     .setMaxResults(pageSize)
                     .getResultList();
        } finally {
            em.close();
        }
    }
}
