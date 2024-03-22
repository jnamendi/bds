package com.bds.converter;

import java.util.Iterator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.bds.model.Blog;
import com.bds.service.BlogService;

@FacesConverter("blogConverter")
public class BlogConverter implements Converter {
	@Override
   public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
	   System.out.println(value);
      if (value != null && !value.equals("")) {
    	 BlogService blogService = (BlogService)facesContext.getExternalContext().getApplicationMap().get("blogService");
         Blog rtnValue = null;
         Iterator var7 = blogService.list().iterator();

         while(var7.hasNext()) {
        	 Blog blog = (Blog)var7.next();
            if (value.equals(blog.getId().toString())) {
               rtnValue = blog;
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
      if (object != null && object instanceof Blog) {
    	  Blog blog = (Blog)object;
         return blog.getId().toString();
      } else {
         return null;
      }
   }
}