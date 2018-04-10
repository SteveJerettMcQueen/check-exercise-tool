/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Steve
 * Created: Mar 24, 2018
 */

INSERT INTO Chapter(chapterId, chapterName) 
VALUES (1,'Chapter 1');

INSERT INTO Exercise(exerId, chapterId, exerName, inputParams, correctOutput)
VALUES (1, 1, 'Exercise01_01', null, 'Welcome to Java
Welcome to Computer Science
Programming is fun');

INSERT INTO Exercise(exerId, chapterId, exerName, inputParams, correctOutput)
VALUES (2, 1, 'Exercise01_02', null, 'Welcome to Java
Welcome to Java
Welcome to Java
Welcome to Java
Welcome to Java');

INSERT INTO Exercise(exerId, chapterId, exerName, inputParams, correctOutput)
VALUES (3, 1, 'Exercise01_03', null, '    J     A     V     V    A     
    J    A A     V   V    A A    
J   J   AAAAA     V V    AAAAA   
 J J   A     A     V    A     A  ');

INSERT INTO Exercise(exerId, chapterId, exerName, inputParams, correctOutput)
VALUES (4, 1, 'Exercise01_04', null, 'a      a^2    a^3
1      1      1
2      4      8
3      9      27
4      16     64');

--------------------------------------------------------------------------------

INSERT INTO Chapter(chapterId, chapterName) 
VALUES (2,'Chapter 2');

INSERT INTO Exercise(exerId, chapterId, exerName, inputParams, correctOutput)
VALUES (5, 2,'Exercise02_01Extraa', '4.25 7.26', 'Enter the width and height of a rectangle: 4.25
7.26
The perimeter is 23.02
The area is 30.855
The length of the diagonal is 8.412496656760108');

INSERT INTO Exercise(exerId, chapterId, exerName, inputParams, correctOutput)
VALUES (6, 2, 'Exercise02_01Extrab', '1 2', 'Enter the width and height of a rectangle: 1.0
2.0
The perimeter is 6.0
The area is 2.0
The length of the diagonal is 2.23606797749979');

--------------------------------------------------------------------------------

INSERT INTO Chapter(chapterId, chapterName) 
VALUES (3,'Chapter 3');

INSERT INTO Exercise(exerId, chapterId, exerName, inputParams, correctOutput)
VALUES (7,3, 'Exercise03_01a', '1.0 3 1', 'Enter a, b, c: 1.0
3.0
1.0
The equation has two roots -0.3819660112501051 and -2.618033988749895');

INSERT INTO Exercise(exerId, chapterId, exerName, inputParams, correctOutput)
VALUES (8,3, 'Exercise03_01b', '1 2.0 1', 'Enter a, b, c: 1.0
2.0
1.0
The equation has one root -1.0');

INSERT INTO Exercise(exerId, chapterId, exerName, inputParams, correctOutput)
VALUES (9,3, 'Exercise03_01c', '1 2 3', 'Enter a, b, c: 1.0
2.0
3.0
The equation has no real roots');

INSERT INTO Exercise(exerId, chapterId, exerName, inputParams, correctOutput)
VALUES (10,3, 'Exercise03_02a', '5', 'What is 7 + 9 + 1? 5
7 + 9 + 1 = 5 is false');

INSERT INTO Exercise(exerId, chapterId, exerName, inputParams, correctOutput)
VALUES (11,3, 'Exercise03_02b', '20', 'What is 5 + 5 + 5? 20
5 + 5 + 5 = 20 is false');