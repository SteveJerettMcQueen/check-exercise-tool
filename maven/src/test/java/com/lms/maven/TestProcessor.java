/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.maven;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author sm6668
 */
public class TestProcessor {

    public static void main(String[] args) {

        String[] dataSet = new String[]{
            "Exercise01_01",
            "public class Exercise01_01 {\n"
            + "  public static void main(String[] args) {\n"
            + "    System.out.println(\"Welcome to Java\");\n"
            + "    System.out.println(\"Welcome to Computer Science\");\n"
            + "    System.out.println(\"Programming is fun\");\n"
            + "  }\n"
            + "}",
            null
        };

        String[] dataSet2 = new String[]{
            "Exercise01_08",
            "public class Exercise01_08 {\n"
            + "  public static void main(String[] args) {\n"
            + "  	// Display area\n"
            + "    System.out.println(5.5 * 5.5 * 3.14159);\n"
            + "\n"
            + "    // Display perimeter\n"
            + "    System.out.println(2 * 5.5 * 3.14159);\n"
            + "  }\n"
            + "}",
            null
        };

        String[] dataSet3 = new String[]{
            "Exercise02_01",
            "import java.util.Scanner;\n"
            + "public class Exercise02_01 {\n"
            + "  // Main method\n"
            + "  public static void main(String[] args) {\n"
            + "    Scanner input = new Scanner(System.in);\n"
            + "    // Enter a temperature in Celsius\n"
            + "    System.out.print(\"Enter a temperature in Celsius: \");\n"
            + "    double celsius = input.nextDouble();\n"
            + "    System.out.println(celsius);\n"
            + " \n"
            + "    // Convert it to Fahrenheit\n"
            + "    double fahrenheit = (9.0 / 5) * celsius + 32;\n"
            + " \n"
            + "    // Display the result\n"
            + "    System.out.println(celsius + \" Celsius is \" +\n"
            + "      fahrenheit + \" Fahrenheit\");\n"
            + "  }\n"
            + "}",
            "100"
        };

        ArrayList<String[]> contents = new ArrayList();
        contents.add(dataSet);
        contents.add(dataSet2);
        contents.add(dataSet3);

        Processor process = new Processor(new File("").getAbsolutePath());
        String[] output;
        for (int i = 0; i < contents.size(); i++) {
            String[] ds = contents.get(i);
            output = process.executeProgram(ds[0], ds[1], ds[2]);
            System.out.println(Arrays.toString(output));
        }
    }

}
