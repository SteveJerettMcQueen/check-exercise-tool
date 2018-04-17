/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.maven.bean.controller;

import com.lms.maven.bean.helper.ProcessorBean;
import com.lms.maven.bean.model.ExerciseBean;
import static com.lms.maven.util.Util.symDiff;
import static com.lms.maven.util.Util.convertToJavaScriptArray;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author sm6668
 */
public class ExerciseChecker {

    private String responseText, differentWords;
    private String alertClass, iconClass;
    private String messageLabel, yourOutputLabel, correctOutputLabel;
    private boolean responseRenderBlock;
    private boolean panelRenderBlock;
    private boolean messagePanelHeaderRender;
    private boolean yourOutputPanelHeaderRender;
    private boolean correctOutputPanelHeaderRender;
    private boolean recommendationPanelRender;
    private boolean responseOutputPanelBodyRender;
    private boolean correctOutputPanelBodyRender;

    @Inject
    private ExerciseBean exerciseBean;

    @Inject
    private ProcessorBean processorBean;

    @PostConstruct
    public void init() {
        alertClass = "info";
        yourOutputLabel = "Your Output";
        correctOutputLabel = "Correct Output";
        recommendationPanelRender = true;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public String getDifferentWords() {
        return differentWords;
    }

    public void setDifferentWords(String differentWords) {
        this.differentWords = differentWords;
    }

    public String getAlertClass() {
        return alertClass;
    }

    public void setAlertClass(String alertClass) {
        this.alertClass = alertClass;
    }

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    public String getMessageLabel() {
        return messageLabel;
    }

    public void setMessageLabel(String messageLabel) {
        this.messageLabel = messageLabel;
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

    public boolean getYourOutputPanelHeaderRender() {
        return yourOutputPanelHeaderRender;
    }

    public void setYourOutputPanelHeaderRender(boolean yourOutputPanelHeaderRender) {
        this.yourOutputPanelHeaderRender = yourOutputPanelHeaderRender;
    }

    public boolean getCorrectOuputPanelHeaderRender() {
        return correctOutputPanelHeaderRender;
    }

    public boolean getRecommendationPanelRender() {
        return recommendationPanelRender;
    }

    public void setRecommendationPanelRender(boolean recommendationPanelRender) {
        this.recommendationPanelRender = recommendationPanelRender;
    }

    public void setCorrectOutputPanelHeaderRender(boolean correctOutputPanelHeaderRender) {
        this.correctOutputPanelHeaderRender = correctOutputPanelHeaderRender;
    }

    public boolean getResponseOutputPanelBodyRender() {
        return responseOutputPanelBodyRender;
    }

    public void setResponseOutputPanelBodyRender(boolean responseOutputPanelBodyRender) {
        this.responseOutputPanelBodyRender = responseOutputPanelBodyRender;
    }

    public boolean getCorrectOutputPanelBodyRender() {
        return correctOutputPanelBodyRender;
    }

    public void setCorrectOutputPanelBodyRender(boolean correctOutputPanelBodyRender) {
        this.correctOutputPanelBodyRender = correctOutputPanelBodyRender;
    }

    public boolean getResponseRenderBlock() {
        return responseRenderBlock;
    }

    public void setResponseRenderBlock(boolean responseRenderBlock) {
        this.responseRenderBlock = responseRenderBlock;
    }

    public boolean getPanelRenderBlock() {
        return panelRenderBlock;
    }

    public void setPanelRenderBlock(boolean panelRenderBlock) {
        this.panelRenderBlock = panelRenderBlock;
    }

    public boolean getMessagePanelHeaderRender() {
        return messagePanelHeaderRender;
    }

    public void setMessagePanelHeaderRender(boolean messagePanelHeaderRender) {
        this.messagePanelHeaderRender = messagePanelHeaderRender;
    }

    public void compileRun() {
        String[] output = processorBean.executeProgram();
        String code = output[0];
        String programOutput = output[1];
        switch (code) {
            case "1":
                setAlertClass("danger");
                setIconClass("times-circle-o");
                setMessageLabel("Error: Compilation Unsuccessful!");
                break;
            case "2":
                setAlertClass("success");
                setIconClass("check-square-o");
                setMessageLabel("Compile & Run Successful!");
                break;
            case "4":
                if (programOutput.equals("Process Terminated")) {
                    setAlertClass("warning");
                    setIconClass("warning");
                    setMessageLabel("Warning: Program Took Too Long To Run!");
                } else {
                    setAlertClass("danger");
                    setIconClass("times-circle-o");
                    setMessageLabel("Runtime Error!");
                }
                break;
            default:
                setAlertClass("danger");
                setIconClass("question-circle-o");
                setMessageLabel("Error!");
                break;
        }

        setResponseText(programOutput);
        setResponseRenderBlock(true);
        setPanelRenderBlock(true);
        setMessagePanelHeaderRender(true);
        setResponseOutputPanelBodyRender(true);
    }

    public void autoCheckProgram() {
        String programOutput = processorBean.executeProgram()[1];
        String correctOutput = exerciseBean.getCorrectOutput();
        Set<String> a = new HashSet(Arrays.asList(programOutput.trim().split("\\s")));
        Set<String> b = new HashSet(Arrays.asList(correctOutput.trim().split("\\s")));
        Set<String> result = symDiff(a, b);
        boolean isCorrect = result.isEmpty();
        if (isCorrect) {
            setAlertClass("success");
            setIconClass("check-square-o");
            setMessageLabel("Your Program Is Correct!");
            setResponseRenderBlock(true);
            setPanelRenderBlock(false);
            setMessagePanelHeaderRender(true);
            setYourOutputPanelHeaderRender(false);
            setCorrectOutputPanelHeaderRender(false);
            setResponseOutputPanelBodyRender(false);
            setCorrectOutputPanelBodyRender(false);
            setRecommendationPanelRender(false);
        } else {
            setResponseText(programOutput);
            setDifferentWords(convertToJavaScriptArray(result).toString());
            setAlertClass("danger");
            setIconClass("times-circle-o");
            setMessageLabel("Your Program Is Incorrect!");
            setResponseRenderBlock(true);
            setPanelRenderBlock(true);
            setMessagePanelHeaderRender(true);
            setYourOutputPanelHeaderRender(true);
            setCorrectOutputPanelHeaderRender(true);
            setResponseOutputPanelBodyRender(true);
            setCorrectOutputPanelBodyRender(true);
            setRecommendationPanelRender(false);
        }
    }

}
