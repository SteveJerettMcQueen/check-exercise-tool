/**
 * Author:  sm6668
 * Created: Mar 21, 2018
 */
-- DROP TABLE Exercise;
-- DROP TABLE Chapter;

CREATE TABLE Chapter(
    chapterId INT NOT NULL AUTO_INCREMENT,
    chapterName VARCHAR(255) NOT NULL,
    PRIMARY KEY(chapterId)
)ENGINE=INNODB;

CREATE TABLE Exercise(
    exerId INT NOT NULL AUTO_INCREMENT,
    chapterId INT,
    exerName VARCHAR(255) NOT NULL,
    exerDescr VARCHAR(255),
    programCode VARCHAR(5000),
    inputParams VARCHAR(2500),
    correctOutput VARCHAR(5000) NOT NULL,
    PRIMARY KEY(exerId),
    FOREIGN KEY(chapterId) REFERENCES Chapter(chapterId) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE
)ENGINE=INNODB;



