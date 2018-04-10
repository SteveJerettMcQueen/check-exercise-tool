/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.maven.bean.helper;

import com.lms.maven.entity.Exercise;
import com.lms.maven.ejb.ExerciseFacade;
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
public class ExerciseItemConverter implements Converter, Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private ExerciseFacade exerciseFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.equals("")) {
            return null;
        }
        Long id = Long.parseLong(value);
        Exercise e = exerciseFacade.find(id);
        if (e == null) {
            throw new ConverterException(new FacesMessage("Exercise with number: " + value + " not found."));
        }
        return e;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Exercise) || (value == null)) {
            return null;
        }
        return Integer.toString(((Exercise) value).getExerId());
    }

}
