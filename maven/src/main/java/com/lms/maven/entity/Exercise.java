/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.maven.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Steve
 */
@Entity
@Table(catalog = "", schema = "LMS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exercise.findAll", query = "SELECT e FROM Exercise e")
    ,
    @NamedQuery(name = "Exercise.findByExerId", query = "SELECT e FROM Exercise e WHERE e.exerId = :exerId")
    ,
    @NamedQuery(name = "Exercise.findByExerName", query = "SELECT e FROM Exercise e WHERE e.exerName = :exerName")
    ,
    @NamedQuery(name = "Exercise.findByExerDescr", query = "SELECT e FROM Exercise e WHERE e.exerDescr = :exerDescr")
    ,
    @NamedQuery(name = "Exercise.findByProgramCode", query = "SELECT e FROM Exercise e WHERE e.programCode = :programCode")
    ,
    @NamedQuery(name = "Exercise.findByInputParams", query = "SELECT e FROM Exercise e WHERE e.inputParams = :inputParams")
    ,
    @NamedQuery(name = "Exercise.findByCorrectOutput", query = "SELECT e FROM Exercise e WHERE e.correctOutput = :correctOutput")})
public class Exercise implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer exerId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 255)
    private String exerName;
    @Size(max = 255)
    @Column(length = 255)
    private String exerDescr;
    @Size(max = 5000)
    @Column(length = 5000)
    private String programCode;
    @Size(max = 255)
    @Column(length = 255)
    private String inputParams;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1500)
    @Column(nullable = false, length = 1500)
    private String correctOutput;
    @JoinColumn(name = "chapterId", referencedColumnName = "chapterId")
    @ManyToOne(fetch = FetchType.EAGER)
    private Chapter chapterId;

    public Exercise() {
    }

    public Exercise(Integer exerId) {
        this.exerId = exerId;
    }

    public Exercise(Integer exerId, String exerName, String correctOutput) {
        this.exerId = exerId;
        this.exerName = exerName;
        this.correctOutput = correctOutput;
    }

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (exerId != null ? exerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exercise)) {
            return false;
        }
        Exercise other = (Exercise) object;
        if ((this.exerId == null && other.exerId != null) || (this.exerId != null && !this.exerId.equals(other.exerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s[exerId=%d]", getClass().getSimpleName(), getExerId());
    }

}
