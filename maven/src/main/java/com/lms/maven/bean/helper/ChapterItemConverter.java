/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.maven.bean.helper;

import com.lms.maven.ejb.ChapterFacade;
import com.lms.maven.entity.Chapter;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * @author sm6668
 */
public class ChapterItemConverter implements Converter, Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private ChapterFacade chapterFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.equals("")) {
            return null;
        }
        Long id = Long.parseLong(value);
        Chapter c = chapterFacade.find(id);
        if (c == null) {
            throw new ConverterException(new FacesMessage("Chapter with number: " + value + " not found."));
        }
        return c;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Chapter) || (value == null)) {
            return null;
        }
        return Integer.toString(((Chapter) value).getChapterId());
    }

}
