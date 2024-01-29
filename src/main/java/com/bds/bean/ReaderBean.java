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
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;

import com.bds.service.ReaderService;
import com.bds.model.Reader;


@Named @ViewScoped
public class ReaderBean implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5398598235645104177L;
	@Inject
    private ReaderService readerService;
    private List<Reader> readers;
    private Reader reader = new Reader();
    private Reader selectedReader;
    
    @PostConstruct
    public void init() {
    	readers = readerService.list();
    }
    
    public void submit() {
    	readerService.create(reader);
    	readers.add(reader);
    	reader = new Reader();
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Reader Added"));
    	PrimeFaces.current().ajax().update("form:messages", "form:dt-reader");
    }


    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        DataTable o = (DataTable)event.getSource();
        Reader rEdit = (Reader)o.getRowData();
        
        if (newValue != null && !newValue.equals(oldValue)) {
        	readerService.update(rEdit);
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Reader updated"));
        	PrimeFaces.current().ajax().update("form:messages", "form:dt-reader");
        }
    }
    
    public void deleteReader() {
        this.readers.remove(this.selectedReader);
        readerService.delete(this.selectedReader);
        this.selectedReader = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Reader Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reader");
    }
    
	public List<Reader> getReaders() {
		return readers;
	}

	public void setReaders(List<Reader> readers) {
		this.readers = readers;
	}

	public Reader getReader() {
		return reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	public Reader getSelectedReader() {
		return selectedReader;
	}

	public void setSelectedReader(Reader selectedReader) {
		this.selectedReader = selectedReader;
	}
	
	
    
}
