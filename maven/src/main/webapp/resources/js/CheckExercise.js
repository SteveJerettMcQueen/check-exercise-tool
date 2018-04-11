/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global ace */

$(document).ready(function () {
    // Set ace editor
    var editor = ace.edit("editor");
    editor.setOptions({
        mode: "ace/mode/java",
        theme: "ace/theme/xcode",
//        theme: "ace/theme/github",
//        theme: "ace/theme/tommorrow",
//        theme: "ace/theme/pastel_on_dark",
//        theme: "ace/theme/tommorrow_night",
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
        var newCode = editor.getSession().getValue();
        hiddenProgramCode.val(newCode);
    });

//    $("textarea").css('background-color', '#ffffff');

    // Dark style
//    $("textarea").css('background-color', '#404040');
//    $("textarea").css('color', '#ffffff');

});

