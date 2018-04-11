package com.lms.maven.entity;

import com.lms.maven.entity.Chapter;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-11T10:37:29")
@StaticMetamodel(Exercise.class)
public class Exercise_ { 

    public static volatile SingularAttribute<Exercise, String> correctOutput;
    public static volatile SingularAttribute<Exercise, String> programCode;
    public static volatile SingularAttribute<Exercise, Chapter> chapterId;
    public static volatile SingularAttribute<Exercise, String> inputParams;
    public static volatile SingularAttribute<Exercise, String> exerDescr;
    public static volatile SingularAttribute<Exercise, String> exerName;
    public static volatile SingularAttribute<Exercise, Integer> exerId;

}