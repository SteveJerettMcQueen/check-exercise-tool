package com.lms.maven.entity;

import com.lms.maven.entity.Exercise;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-11T10:37:29")
@StaticMetamodel(Chapter.class)
public class Chapter_ { 

    public static volatile ListAttribute<Chapter, Exercise> exercises;
    public static volatile SingularAttribute<Chapter, Integer> chapterId;
    public static volatile SingularAttribute<Chapter, String> chapterName;

}