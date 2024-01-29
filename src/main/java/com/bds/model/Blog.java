package com.bds.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "blog", schema = "bds_bloggers")
public class Blog {
    @Id
    @SequenceGenerator(name = "blog_id_seq", sequenceName = "blog_id_seq", allocationSize = 1, schema = "bds_bloggers")
    @GeneratedValue(strategy = SEQUENCE, generator = "blog_id_seq")
	private Integer id;
    @Column(name = "tittle")
	private String tittle;
    @Column(name = "description")
	private String description;
    
    @ManyToMany(mappedBy = "blogs")
    private Set<Reader> readers = new HashSet<>();
}
