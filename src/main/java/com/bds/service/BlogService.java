package com.bds.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bds.model.Blog;

@Stateless
public class BlogService {
    @PersistenceContext
    private EntityManager entityManager;
    
    public void create(Blog blog) {
        entityManager.persist(blog);
    }
    
    public void update(Blog blog) {
    	Blog blogToUpdate= (Blog)entityManager.find(Blog.class , blog.getId());
    	String sql = "update Blog set tittle = ?1, description = ?2 where id=?3";
    	entityManager.createQuery(sql).setParameter(1, blog.getTittle()).setParameter(2, blog.getDescription()).setParameter(3, blog.getId())
        .executeUpdate();
        entityManager.refresh(blogToUpdate);
    }

    public List<Blog> list() {
        return entityManager
            .createQuery("FROM Blog b", Blog.class)
            .getResultList();
    }
    
    public void delete(Blog blog) {
    	String sql = "Delete Blog where id=?1";
    	entityManager.createQuery(sql).setParameter(1, blog.getId())
        .executeUpdate();
    }
}
