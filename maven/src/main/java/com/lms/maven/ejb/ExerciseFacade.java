/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.maven.ejb;

import com.lms.maven.entity.Exercise;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Steve
 */
@Stateless
public class ExerciseFacade extends AbstractFacade<Exercise> {

    @PersistenceContext(unitName = "lms_derby_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExerciseFacade() {
        super(Exercise.class);
    }

}
