package com.bds.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "reader", schema = "bds_bloggers")
public class Reader {
    @Id
    @SequenceGenerator(name = "reader_id_seq", sequenceName = "reader_id_seq", allocationSize = 1 , schema = "bds_bloggers")
    @GeneratedValue(strategy = SEQUENCE, generator = "reader_id_seq")
	private Integer id;
    @Column(name = "name")
	private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(    		  
    		  name = "blog_reader", 
    		  joinColumns = @JoinColumn(name = "reader_id"), 
    		  inverseJoinColumns = @JoinColumn(name = "blog_id"),
    		  schema = "bds_bloggers")
    Set<Blog> blogs = new HashSet<>();


    public void addBlog(Blog blog) {
      this.blogs.add(blog);
      blog.getReaders().add(this);
    }
    
    public void removeBlog(long blogId) {
      Blog blog = this.blogs.stream().filter(b -> b.getId() == blogId).findFirst().orElse(null);
      if (blog != null) {
        this.blogs.remove(blog);
        blog.getReaders().remove(this);
      }
    }
	
}
