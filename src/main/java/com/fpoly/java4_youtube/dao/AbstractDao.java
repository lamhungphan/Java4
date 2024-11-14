package com.fpoly.java4_youtube.dao;

import java.util.List;
import com.fpoly.java4_youtube.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;


public class AbstractDao<EntityType> {
    public static final EntityManager entityManager = JpaUtil.getEntityManager();

//	@SuppressWarnings("deprecation")
//	@Override
//	protected void finalize() throws Throwable {
//		entityManager.close();
//		super.finalize();
//	}

    public EntityType findById(Class<EntityType> clazz, Integer id) {
        return entityManager.find(clazz, id);
    }

    // Ko phải entity nào cũng có thuộc tính isActive (History)
    public List<EntityType> findAll(Class<EntityType> clazz, boolean existIsActive) {
        String entityName = clazz.getSimpleName();
        StringBuilder sql = new StringBuilder();

        // SELECT o FROM entity o WHERE isActive = 1;
        sql.append("SELECT o FROM ").append(entityName).append(" o");

        if (existIsActive == true) {
            sql.append(" WHERE isActive = 1");
        }
        TypedQuery<EntityType> query = entityManager.createQuery(sql.toString(), clazz);
        return query.getResultList();
    }

    public List<EntityType> findAll(Class<EntityType> clazz, boolean existIsActive, int pageNumber, int pageSize) {
        String entityName = clazz.getSimpleName();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT o FROM ").append(entityName).append(" o");
        if (existIsActive == true) {
            sql.append(" WHERE isActive = 1");
        }
        TypedQuery<EntityType> query = entityManager.createQuery(sql.toString(), clazz);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public EntityType findOne(Class<EntityType> clazz, String sql, Object... params) {
        TypedQuery<EntityType> query = entityManager.createQuery(sql, clazz);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        List<EntityType> result = query.getResultList();
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    public List<EntityType> findMany(Class<EntityType> clazz, String sql, Object... params) {
        TypedQuery<EntityType> query = entityManager.createQuery(sql, clazz);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> findManyByNativeQuery(Class<EntityType> clazz, String sql, Object... params) {
        Query query = entityManager.createNativeQuery(sql, clazz);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return query.getResultList();
    }

    public EntityType create(EntityType entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            System.out.println("Create succeed");
            return entity;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Cannot create entity" + entity.getClass().getSimpleName());
            throw new RuntimeException(e);
        }
    }

    public EntityType update(EntityType entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
            System.out.println("Update succeed");
            return entity;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Cannot update entity" + entity.getClass().getSimpleName());
            throw new RuntimeException(e);
        }
    }

    public EntityType delete(EntityType entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
            System.out.println("Delete succeed");
            return entity;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Cannot delete entity" + entity.getClass().getSimpleName());
            throw new RuntimeException(e);
        }
    }
}
