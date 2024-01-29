package com.bds.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.bds.model.Blog;
import com.bds.service.BlogService;

@Named @ViewScoped
public class BlogBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4371991865419674470L;
	@Inject
    private BlogService blogService;
    private List<Blog> blogs;
    private Blog blog = new Blog();
    private Blog selectedBlog;
    
    @PostConstruct
    public void init() {
    	blogs = blogService.list();
    }
    
    public void submit() {
    	blogService.create(blog);
    	blogs.add(blog);
    	blog = new Blog();
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Blog Added"));
    	PrimeFaces.current().ajax().update("form:messages", "form:dt-blog");
    }

    public void update() {
        blogService.update(selectedBlog);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Blog Updated"));
 
        PrimeFaces.current().executeScript("PF('manageBlogDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-blog");
    }    
    
    public void deleteBlog() {
        this.blogs.remove(this.selectedBlog);
        blogService.delete(this.selectedBlog);
        this.selectedBlog = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Blog Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-blog");
    }
    
	public List<Blog> getBlogs() {
		return blogs;
	}
	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	public Blog getSelectedBlog() {
		return selectedBlog;
	}
	public void setSelectedBlog(Blog selectedBlog) {
		this.selectedBlog = selectedBlog;
	}
    
    
}
