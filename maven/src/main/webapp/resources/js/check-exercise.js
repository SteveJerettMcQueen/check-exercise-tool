/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global ace, hljs */

$(document).ready(function () {
    loadAceEditor();
});

function loadAceEditor() {
    var editor = ace.edit("editor");
    editor.setOptions({
        mode: "ace/mode/java",
        theme: "ace/theme/xcode", //"ace/theme/github","ace/theme/tommorrow","ace/theme/pastel_on_dark","ace/theme/tommorrow_night",
        fontSize: "12pt",
        firstLineNumber: 1,
        readOnly: false,
        fadeFoldWidgets: true,
        hScrollBarAlwaysVisible: false,
        vScrollBarAlwaysVisible: false,
        animatedScroll: true
    });

    // Set the new value for jsf form submission
    var hiddenProgramCode = $("#exer-check-form\\:hidden-program-code");
    editor.getSession().on("change", function () {
        hiddenProgramCode.val(editor.getSession().getValue());
    });
}

function highlightText(data) {
    switch (data.status) {
        case "begin":
            disableFormControls(true);
            break;
        case "complete":
            disableFormControls(false);
            break;
        case "success":
            loadHighlightJS();
            break;
    }
}

function markDifferentWords(data) {
    var differentWords = $("#exer-check-form\\:hidden-different-words");
    var responseOutputText = $("#exer-check-form\\:response-output-text");
    var correctOutputText = $("#exer-check-form\\:correct-output-text");
    switch (data.status) {
        case "begin":
            disableFormControls(true);
            break;
        case "success":
            try {
                var words = JSON.parse(differentWords.val());
                new Mark(responseOutputText).mark(words, {
                    element: "mark",
                    className: "highlight-your-output"
                });

                new Mark(correctOutputText).mark(words, {
                    element: "mark",
                    className: "highlight-correct-output"
                });
            } catch (error) {
                console.log("No values to mark!");
            }
            break;
        case "complete":
            disableFormControls(false);
            break;
    }
}

function disableFormControls(disable) {
    $("#exer-picker-form :input").attr("disabled", disable);
    $("#exer-check-form :input").attr("disabled", disable);
}

function loadHighlightJS() {
    $('pre code').each(function (i, block) {
        hljs.highlightBlock(block);
    });
}