/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.maven.bean.controller;

import com.lms.maven.bean.model.ChapterBean;
import com.lms.maven.bean.model.ExerciseBean;
import com.lms.maven.ejb.ChapterFacade;
import com.lms.maven.entity.Chapter;
import com.lms.maven.entity.Exercise;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

/**
 *
 * @author Steve
 */
public class ExercisePicker {

    private String headerText;
    private Chapter chapterItem;
    private Exercise exerciseItem;
    private List<Chapter> chapterItems;
    private List<Exercise> exerciseItems;
    private String inputParamsLabel;
    private boolean inputParamsPanelRender;
    private String inputParamsRows;

    @Inject
    private ChapterBean chapterBean;

    @Inject
    private ExerciseBean exerciseBean;

    @EJB
    private ChapterFacade chapterFacade;

    @PostConstruct
    public void init() {
        chapterItems = chapterFacade.findAll();
        chapterItem = chapterItems.get(0);
        exerciseItems = chapterItems.get(0).getExercises();
        exerciseItem = exerciseItems.get(0);
        headerText = getHeaderText(exerciseItem.getExerName());
        setChapterBean(chapterItem);
        setExerciseBean(exerciseItem);
        setInputParamsFor(exerciseItem);
        inputParamsLabel = "Enter input data for the program (Sample data provided below. You may modify it.)";
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public Chapter getChapterItem() {
        return chapterItem;
    }

    public void setChapterItem(Chapter chapterItem) {
        this.chapterItem = chapterItem;
    }

    public Exercise getExerciseItem() {
        return exerciseItem;
    }

    public void setExerciseItem(Exercise exerciseItem) {
        this.exerciseItem = exerciseItem;
    }

    public List<Chapter> getChapterItems() {
        return chapterItems;
    }

    public void setChapterItems(List<Chapter> chapterItems) {
        this.chapterItems = chapterItems;
    }

    public List<Exercise> getExerciseItems() {
        return exerciseItems;
    }

    public void setExerciseItems(List<Exercise> exerciseItems) {
        this.exerciseItems = exerciseItems;
    }

    public String getInputParamsLabel() {
        return inputParamsLabel;
    }

    public void setInputParamsLabel(String inputParamsLabel) {
        this.inputParamsLabel = inputParamsLabel;
    }

    public boolean getInputParamsPanelRender() {
        return inputParamsPanelRender;
    }

    public void setInputParamsPanelRender(boolean inputParamsPanelRender) {
        this.inputParamsPanelRender = inputParamsPanelRender;
    }

    public String getInputParamsRows() {
        return inputParamsRows;
    }

    public void setInputParamsRows(String inputParamsRows) {
        this.inputParamsRows = inputParamsRows;
    }

    public void chapterItemValueChanged(ValueChangeEvent e) {
        chapterItem = (Chapter) (e.getNewValue());
        int index = chapterItems.indexOf(chapterItem);
        exerciseItems = chapterItems.get(index).getExercises();
    }

    public void exerciseItemValueChanged(ValueChangeEvent e) {
        exerciseItem = (Exercise) (e.getNewValue());
    }

    public void pickExercise() {
        setHeaderText(getHeaderText(exerciseItem.getExerName()));
        setChapterBean(chapterItem);
        setExerciseBean(exerciseItem);
        setInputParamsFor(exerciseItem);
    }

    private void setChapterBean(Chapter c) {
        chapterBean.setChapterId(c.getChapterId());
        chapterBean.setChapterName(c.getChapterName());
        chapterBean.setExercises(c.getExercises());
    }

    private void setExerciseBean(Exercise e) {
        exerciseBean.setChapterId(e.getChapterId());
        exerciseBean.setExerId(e.getExerId());
        exerciseBean.setExerName(e.getExerName());
        exerciseBean.setExerDescr(getExerDescr(e.getExerName()));
        exerciseBean.setInputParams(e.getInputParams());
        exerciseBean.setProgramCode(null);
        exerciseBean.setCorrectOutput(e.getCorrectOutput());
    }

    private void setInputParamsFor(Exercise e) {
        boolean isNotNull = (e.getInputParams() != null);
        inputParamsPanelRender = (isNotNull);
        if (isNotNull) {
            inputParamsRows = Integer.toString(e.getInputParams().split("\n").length);
        } else {
            inputParamsRows = "0";
        }
    }

    private String getHeaderText(String exerciseName) {
        return "CheckExercise: ".concat(exerciseName).concat(".java");
    }

    private String getExerDescr(String exerciseName) {
        return "/* Paste your " + exerciseName + " here and click Automatic Check.\n"
                + "For all programming projects, the numbers should be double \n"
                + "unless it is explicitly stated as integer.\n"
                + "If you get a java.util.InputMismatchException error, check if \n"
                + "your code used input.readInt(), but it should be input.readDouble().\n"
                + "For integers, use int unless it is explicitly stated as long. */";
    }
}
