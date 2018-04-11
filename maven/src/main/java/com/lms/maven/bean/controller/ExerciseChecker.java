/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.maven.bean.controller;

import com.lms.maven.bean.helper.ProcessorBean;
import com.lms.maven.bean.model.ExerciseBean;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author sm6668
 */
public class ExerciseChecker {

    private String alertClass;
    private String responseText;
    private String messageLabel,
            inputParamLabel,
            yourOutputLabel,
            correctOutputLabel;
    private boolean responseRenderBlock,
            messageLabelRender,
            yourOutputLabelRender,
            correctOutputLabelRender;
    private boolean inputParamsRender,
            responseOutputTextAreaRender,
            correctOutputTextAreaRender;
    private String responseOutputTextRows,
            correctOutputTextRows,
            inputParamsRows;

    @Inject
    private ExerciseBean exerciseBean;

    @Inject
    private ProcessorBean processorBean;

    @PostConstruct
    public void init() {
        alertClass = "alert alert-info";
        messageLabel = "";
        inputParamLabel = initInputParamLabel();
        yourOutputLabel = "Your Output";
        correctOutputLabel = "Correct Output";
        responseRenderBlock = false;
        messageLabelRender = false;
        yourOutputLabelRender = false;
        correctOutputLabelRender = false;
        inputParamsRender = initInputParamsRender();
        responseOutputTextAreaRender = false;
        correctOutputTextAreaRender = false;
        responseOutputTextRows = "10";
        correctOutputTextRows = "10";
        inputParamsRows = initInputParamsRows();
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public String getAlertClass() {
        return alertClass;
    }

    public void setAlertClass(String alertClass) {
        this.alertClass = alertClass;
    }

    public String getMessageLabel() {
        return messageLabel;
    }

    public void setMessageLabel(String messageLabel) {
        this.messageLabel = messageLabel;
    }

    public String getInputParamLabel() {
        return inputParamLabel;
    }

    public void setInputParamLabel(String inputParamLabel) {
        this.inputParamLabel = inputParamLabel;
    }

    public String getYourOutputLabel() {
        return yourOutputLabel;
    }

    public void setYourOutputLabel(String yourOutputLabel) {
        this.yourOutputLabel = yourOutputLabel;
    }

    public String getCorrectOutputLabel() {
        return correctOutputLabel;
    }

    public void setCorrectOutputLabel(String correctOutputLabel) {
        this.correctOutputLabel = correctOutputLabel;
    }

    public boolean getYourOutputLabelRender() {
        return yourOutputLabelRender;
    }

    public void setYourOutputLabelRender(boolean yourOutputLabelRender) {
        this.yourOutputLabelRender = yourOutputLabelRender;
    }

    public boolean getCorrectOuputLabelRender() {
        return correctOutputLabelRender;
    }

    public boolean getInputParamsRender() {
        return inputParamsRender;
    }

    public void setInputParamsRender(boolean inputParamsRender) {
        this.inputParamsRender = inputParamsRender;
    }

    public void setCorrectOutputLabelRender(boolean correctOutputLabelRender) {
        this.correctOutputLabelRender = correctOutputLabelRender;
    }

    public boolean getResponseOutputTextAreaRender() {
        return responseOutputTextAreaRender;
    }

    public void setResponseOutputTextAreaRender(boolean responseOutputTextAreaRender) {
        this.responseOutputTextAreaRender = responseOutputTextAreaRender;
    }

    public boolean getCorrectOutputTextAreaRender() {
        return correctOutputTextAreaRender;
    }

    public void setCorrectOutputTextAreaRender(boolean correctOutputTextAreaRender) {
        this.correctOutputTextAreaRender = correctOutputTextAreaRender;
    }

    public boolean getResponseRenderBlock() {
        return responseRenderBlock;
    }

    public void setResponseRenderBlock(boolean responseRenderBlock) {
        this.responseRenderBlock = responseRenderBlock;
    }

    public boolean getMessageLabelRender() {
        return messageLabelRender;
    }

    public void setMessageLabelRender(boolean messageLabelRender) {
        this.messageLabelRender = messageLabelRender;
    }

    public String getResponseOutputTextRows() {
        return responseOutputTextRows;
    }

    public void setResponseOutputTextRows(String responseOutputTextRows) {
        this.responseOutputTextRows = responseOutputTextRows;
    }

    public String getCorrectOutputTextRows() {
        return correctOutputTextRows;
    }

    public void setCorrectOutputTextRows(String correctOutputTextRows) {
        this.correctOutputTextRows = correctOutputTextRows;
    }

    public String getInputParamsRows() {
        return inputParamsRows;
    }

    public void setInputParamsRows(String inputParamsRows) {
        this.inputParamsRows = inputParamsRows;
    }

    public String compileRun() {
        String[] output = processorBean.executeProgram();
        String code = output[0];
        String programOutput = output[1];
        String rows = getRows(programOutput.split("\n"));

        setAlertClass(getCompileRunAlertClassType(code));
        setResponseRenderBlock(true);
        setMessageLabelRender(true);
        setResponseOutputTextAreaRender(true);
        setResponseOutputTextRows(rows);
        setResponseText(getCompileRunResponse(output));
        return "CheckExercise";
    }

    public String autoCheckProgram() {
        String[] output = processorBean.executeProgram();
        String programOutput = output[1];
        String rows = getRows(programOutput.split("\n"));

        String correctOutput = exerciseBean.getCorrectOutput();
        String rows2 = getRows(correctOutput.split("\n"));

        if (isCorrect(programOutput, correctOutput)) {
            setAlertClass(getCheckProgramResponseAlertClassType(true));
            setResponseRenderBlock(true);
            setMessageLabelRender(true);
            setYourOutputLabelRender(false);
            setCorrectOutputLabelRender(false);
            setResponseOutputTextAreaRender(false);
            setCorrectOutputTextAreaRender(false);
        } else {
            setResponseText(getCheckProgramResponse(output));
            setAlertClass(getCheckProgramResponseAlertClassType(false));
            setResponseRenderBlock(true);
            setMessageLabelRender(true);
            setResponseOutputTextRows(rows);
            setCorrectOutputTextRows(rows2);
            setYourOutputLabelRender(true);
            setCorrectOutputLabelRender(true);
            setResponseOutputTextAreaRender(true);
            setCorrectOutputTextAreaRender(true);
        }

        return "CheckExercise";
    }

    private String getCompileRunResponse(String[] output) {
        String code = output[0];
        String programOutput = output[1];
        switch (code) {
            case "0":
            case "1":
                setMessageLabel("Error: Compilation Unsuccessful!");
                return programOutput;
            case "2":
                setMessageLabel("Compile & Run Successful!");
                return programOutput;
            case "3":
                setMessageLabel("Error: Runtime Unsuccessful!");
                return programOutput;
            case "4":
            default:
                setMessageLabel("Warning! Program Took Too Long To Run!");
                return programOutput;
        }
    }

    private String getCheckProgramResponse(String[] output) {
        return output[1];
    }

    private String getCompileRunAlertClassType(String code) {
        switch (code) {
            case "0":
            case "1":
            case "3":
                return "alert alert-danger";
            case "2":
                return "alert alert-success";
            case "4":
            default:
                return "alert alert-warning";
        }
    }

    private String getCheckProgramResponseAlertClassType(boolean isSuccess) {
        if (isSuccess) {
            setMessageLabel("Your Program Is Correct!");
            return "alert alert-success";
        } else {
            setMessageLabel("Your Program Is Incorrect!");
            return "alert alert-danger";
        }
    }

    private String getRows(String[] text) {
        return Integer.toString(text.length + 1);
    }

    private boolean isCorrect(String str, String str2) {
        String trimmedStr = str.trim();
        String trimmedStr2 = str2.trim();
        String removedWhiteSpaceStr = trimmedStr.replaceAll("\\s", "");
        String removedWhiteSpaceStr2 = trimmedStr2.replaceAll("\\s", "");
        return Objects.equals(removedWhiteSpaceStr, removedWhiteSpaceStr2);
    }

    private String initInputParamLabel() {
        return "Enter input data for the program (Sample data provided below. You may modify it.)";
    }

    private boolean initInputParamsRender() {
        return (exerciseBean.getInputParams() != null);
    }

    private String initInputParamsRows() {
        String inputParams = exerciseBean.getInputParams();
        if (inputParams != null) {
            return Integer.toString(inputParams.split("\n").length);
        } else {
            return "1";
        }
    }

}
