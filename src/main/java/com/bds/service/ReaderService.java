package com.bds.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bds.model.Blog;
import com.bds.model.Reader;

@Stateless
public class ReaderService {
    @PersistenceContext
    private EntityManager entityManager;
    
    public void create(Reader reader) {
        entityManager.persist(reader);
    }
    
    public void update(Reader reader) {
    	Reader readerToUpdate= (Reader)entityManager.find(Reader.class , reader.getId());
    	String sql = "update Reader set name = ?1 where id=?2";
    	entityManager.createQuery(sql).setParameter(1, reader.getName()).setParameter(2, reader.getId())
        .executeUpdate();
        entityManager.refresh(readerToUpdate);
    }

    public List<Reader> list() {
        return entityManager
            .createQuery("FROM Reader r", Reader.class)
            .getResultList();
    }
    
    public void delete(Reader reader) {
    	String sql = "Delete Reader where id=?1";
    	entityManager.createQuery(sql).setParameter(1, reader.getId())
        .executeUpdate();
    }
    
    public void addBlog(Reader reader, Blog blog) {
    	String sql = "Insert into bds_bloggers.blog_reader values (?1,?2) ";
    	entityManager.createNativeQuery(sql).setParameter(1, reader.getId()).setParameter(2, blog.getId())
        .executeUpdate();
    }
    
    public void addBlog(int reader, int blog) {
    	String sql = "Insert into bds_bloggers.blog_reader values (?1,?2) ";
    	entityManager.createNativeQuery(sql).setParameter(1, reader).setParameter(2,blog)
        .executeUpdate();
    }
    
    public void removeReaderFromBlog(int reader, int blog) {
    	String sql = "Delete from bds_bloggers.blog_reader where reader_id=?1 and blog_id = ?2 ";
    	entityManager.createNativeQuery(sql).setParameter(1, reader).setParameter(2,blog)
        .executeUpdate();
    }
}
