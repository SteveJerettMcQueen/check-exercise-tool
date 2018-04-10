/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.maven.bean.model;

import com.lms.maven.entity.Chapter;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Steve
 */
@Named
@SessionScoped
public class ExerciseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer exerId;
    private String exerName;
    private String exerDescr;
    private String programCode;
    private String inputParams;
    private String correctOutput;
    private Chapter chapterId;

    public Integer getExerId() {
        return exerId;
    }

    public void setExerId(Integer exerId) {
        this.exerId = exerId;
    }

    public String getExerName() {
        return exerName;
    }

    public void setExerName(String exerName) {
        this.exerName = exerName;
    }

    public String getExerDescr() {
        return exerDescr;
    }

    public void setExerDescr(String exerDescr) {
        this.exerDescr = exerDescr;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getInputParams() {
        return inputParams;
    }

    public void setInputParams(String inputParams) {
        this.inputParams = inputParams;
    }

    public String getCorrectOutput() {
        return correctOutput;
    }

    public void setCorrectOutput(String correctOutput) {
        this.correctOutput = correctOutput;
    }

    public Chapter getChapterId() {
        return chapterId;
    }

    public void setChapterId(Chapter chapterId) {
        this.chapterId = chapterId;
    }
}
