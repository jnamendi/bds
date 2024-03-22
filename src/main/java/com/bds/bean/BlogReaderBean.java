package com.bds.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
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
    private Reader selectedReader;
    //for inputs
    private Blog selectedBlogInput;
    private Reader selectedReaderInput;
    
    private SelectItem[] readerItems;
    private SelectItem[] blogItems;
    private String selectedBlogInput2;
    private String selectedReaderInput2;
    
    @PostConstruct
    public void init() {
    	readers = readerService.list();
    	blogs = blogService.list();
    	readerItems = new SelectItem[readers.size()];
    	int i = 0;
    	for( Reader r: readers) {
    		readerItems[i] = new SelectItem(r.getId()+"",r.getName());
    		i++;
    	}
    	blogItems = new SelectItem[blogs.size()];
    	i = 0;
    	for( Blog b: blogs) {
    		blogItems[i] = new SelectItem( b.getId()+"",b.getTittle());
    		i++;
    	}
    }
    
    public void submit() {
    	FacesContext facesContext = FacesContext.getCurrentInstance();
    	readerService.addBlog(Integer.parseInt(selectedReaderInput2), Integer.parseInt(selectedBlogInput2));
    	blogs = blogService.list();
    	facesContext.addMessage((String)null, new FacesMessage("Reader "+selectedReaderInput2+" added to blog " +selectedBlogInput2));
    }
    
    public void delete() {
    	FacesContext facesContext = FacesContext.getCurrentInstance();
    	readerService.removeReaderFromBlog(selectedReader.getId(), selectedBlog.getId());
    	blogs = blogService.list();
    	facesContext.addMessage((String)null, new FacesMessage("Reader " +selectedReader.getName()+ " removed from blog " + selectedBlog.getTittle()));
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

	public ReaderService getReaderService() {
		return readerService;
	}

	public void setReaderService(ReaderService readerService) {
		this.readerService = readerService;
	}

	public BlogService getBlogService() {
		return blogService;
	}

	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}

	public SelectItem[] getReaderItems() {
		return readerItems;
	}

	public void setReaderItems(SelectItem[] readerItems) {
		this.readerItems = readerItems;
	}

	public String getSelectedBlogInput2() {
		return selectedBlogInput2;
	}

	public void setSelectedBlogInput2(String selectedBlogInput2) {
		this.selectedBlogInput2 = selectedBlogInput2;
	}

	public String getSelectedReaderInput2() {
		return selectedReaderInput2;
	}

	public void setSelectedReaderInput2(String selectedReaderInput2) {
		this.selectedReaderInput2 = selectedReaderInput2;
	}

	public SelectItem[] getBlogItems() {
		return blogItems;
	}

	public void setBlogItems(SelectItem[] blogItems) {
		this.blogItems = blogItems;
	}

	public Reader getSelectedReader() {
		return selectedReader;
	}

	public void setSelectedReader(Reader selectedReader) {
		this.selectedReader = selectedReader;
	}
    
}
