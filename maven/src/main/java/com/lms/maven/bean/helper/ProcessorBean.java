/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.maven.bean.helper;

import com.lms.maven.bean.model.ExerciseBean;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author sm6668
 */
@Named
@RequestScoped
public class ProcessorBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String INPUT_FILE = "input.txt";
    private static final long TIME_OUT = 10;

    private String directory;

    @Inject
    private ExerciseBean exerciseBean;

    @PostConstruct
    public void init() {
        //Initializes directory to the server binary tomee
        directory = new File("").getAbsolutePath();
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String[] executeProgram() {

        String programName = exerciseBean.getExerName();
        String inputParams = exerciseBean.getInputParams();
        String programCode = exerciseBean.getProgramCode();

        String[] output;
        try {
            String programFile = programName.concat(".java");
            String className = programFile.substring(0, programFile.indexOf("."));
            createFile(programFile, programCode);
            if (inputParams != null) {
                createFile(INPUT_FILE, inputParams);
            }

            ProcessBuilder pb = new ProcessBuilder();
            Process compileProc = compile(programFile, pb);
            compileProc.waitFor(TIME_OUT, TimeUnit.SECONDS);
            compileProc.destroy();
            String err = output(compileProc.getErrorStream());
            switch (compileProc.waitFor()) {
                case 0:
                    File inputFile = new File(INPUT_FILE);
                    if (inputFile.exists()) {
                        pb.redirectInput(ProcessBuilder.Redirect.from(inputFile));
                    }
                    Process runProc = run(className, pb);
                    runProc.waitFor(TIME_OUT, TimeUnit.SECONDS);
                    runProc.destroy();
                    String out2 = output(runProc.getInputStream());
                    String err2 = output(runProc.getErrorStream());
                    switch (runProc.waitFor()) {
                        case 0:
                            output = new String[]{"2", out2};
                            break;
                        case 2:
                            output = new String[]{"3", err2};
                            break;
                        default:
                            if (!err2.isEmpty()) {
                                output = new String[]{"4", err2};
                            } else {
                                output = new String[]{"4", "Process Terminated"};
                            }
                            break;
                    }
                    break;
                case 2:
                    output = new String[]{"0", err};
                    break;
                default:
                    output = new String[]{"1", err};
                    break;
            }
            deleteFile(className.concat(".class"));
            deleteFile(directory.concat("\\").concat(programFile));
            deleteFile(INPUT_FILE);
        } catch (Exception ex) {
            output = new String[]{"5", ex.getMessage()};
        }
        return output;
    }

    private Process compile(String javaFile, ProcessBuilder pb) throws Exception {
        pb.command(new String[]{"javac", javaFile});
        return pb.start();
    }

    private Process run(String className, ProcessBuilder pb) throws Exception {
        pb.command(new String[]{"java", "-classpath", directory.concat("\\"), className});
        return pb.start();
    }

    private String output(InputStream in) throws Exception {
        StringBuilder sb = new StringBuilder();
        InputStreamReader insr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(insr);
        String line;
        String seperator = System.getProperty("line.separator");
        while ((line = br.readLine()) != null) {
            sb.append(line).append(seperator);
        }
        return sb.toString();
    }

    private void deleteFile(String name) throws Exception {
        Files.deleteIfExists(Paths.get(name));
    }

    private void createFile(String name, String text) throws Exception {
        Files.write(Paths.get(name), Arrays.asList(text.split("\n")),
                StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.WRITE
        );
    }
}
