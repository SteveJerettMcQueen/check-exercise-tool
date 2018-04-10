/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.maven.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Steve
 */
@Entity
@Table(catalog = "", schema = "LMS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chapter.findAll", query = "SELECT c FROM Chapter c")
    ,
    @NamedQuery(name = "Chapter.findByChapterId", query = "SELECT c FROM Chapter c WHERE c.chapterId = :chapterId")
    ,
    @NamedQuery(name = "Chapter.findByChapterName", query = "SELECT c FROM Chapter c WHERE c.chapterName = :chapterName")})
public class Chapter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer chapterId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 255)
    private String chapterName;
    @OneToMany(mappedBy = "chapterId", fetch = FetchType.EAGER)
    private List<Exercise> exercises;

    public Chapter() {
    }

    public Chapter(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public Chapter(Integer chapterId, String chapterName) {
        this.chapterId = chapterId;
        this.chapterName = chapterName;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    @XmlTransient
    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chapterId != null ? chapterId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chapter)) {
            return false;
        }
        Chapter other = (Chapter) object;
        if ((this.chapterId == null && other.chapterId != null) || (this.chapterId != null && !this.chapterId.equals(other.chapterId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s[chapterId=%d]", getClass().getSimpleName(), getChapterId());
    }

}
