package com.bds.converter;

import java.util.Iterator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.bds.model.Reader;
import com.bds.service.ReaderService;

@FacesConverter("readerConverter")
public class ReaderConverter implements Converter {
   @Override
   public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
	   System.out.println(value);
      if (value != null && !value.equals("")) {
    	  ReaderService readerService = (ReaderService)facesContext.getExternalContext().getApplicationMap().get("readerService");
         Reader rtnValue = null;
         Iterator var7 = readerService.list().iterator();

         while(var7.hasNext()) {
        	 Reader reader = (Reader)var7.next();
            if (value.equals(reader.getId().toString())) {
               rtnValue = reader;
               break;
            }
         }

         System.out.println(rtnValue);
         return rtnValue;
      } else {
         return null;
      }
   }

   @Override
   public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
	   System.out.println(object);
      if (object != null && object instanceof Reader) {
    	  Reader reader = (Reader)object;
         return reader.getId().toString();
      } else {
         return null;
      }
   }
}