package com.bds.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.bds.model.Blog;
import com.bds.model.Reader;
import com.bds.service.BlogService;
import com.bds.service.ReaderService;

@Named @ViewScoped
public class BlogReaderBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    private ReaderService readerService;
    private List<Reader> readers;
	@Inject
    private BlogService blogService;
    private List<Blog> blogs;
    private Blog selectedBlog;
    //for inputs
    private Blog selectedBlogInput;
    private Reader selectedReaderInput;
    
    @PostConstruct
    public void init() {
    	readers = readerService.list();
    	blogs = blogService.list();
    }
    
    public void submit() {
    	System.out.println(" Reader" + selectedReaderInput.getId());
    	System.out.println(" Blog" + selectedBlogInput.getId());
    	readerService.addBlog(selectedReaderInput, selectedBlogInput);
    	blogs = blogService.list();
    }
    
    public void delete() {
    	
    }

	public List<Reader> getReaders() {
		return readers;
	}

	public void setReaders(List<Reader> readers) {
		this.readers = readers;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	public Blog getSelectedBlog() {
		return selectedBlog;
	}

	public void setSelectedBlog(Blog selectedBlog) {
		this.selectedBlog = selectedBlog;
	}

	public Blog getSelectedBlogInput() {
		return selectedBlogInput;
	}

	public void setSelectedBlogInput(Blog selectedBlogInput) {
		this.selectedBlogInput = selectedBlogInput;
	}

	public Reader getSelectedReaderInput() {
		return selectedReaderInput;
	}

	public void setSelectedReaderInput(Reader selectedReaderInput) {
		this.selectedReaderInput = selectedReaderInput;
	}
	
	
    
}
