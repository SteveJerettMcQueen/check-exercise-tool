/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global ace, hljs */

$(document).ready(function () {
    // Set ace editor
    var editor = ace.edit("editor");
    editor.setOptions({
        mode: "ace/mode/java",
        theme: "ace/theme/xcode",
        fontSize: "12pt",
        firstLineNumber: 1,
        readOnly: false,
        fadeFoldWidgets: true,
        hScrollBarAlwaysVisible: false,
        vScrollBarAlwaysVisible: false,
        animatedScroll: true
    });

    // Set the new value for jsf form submission
    var hiddenProgramCode = $("#exerCheckForm\\:hiddenProgramCode");
    editor.getSession().on("change", function () {
        var newCode = editor.getSession().getValue();
        hiddenProgramCode.val(newCode);
    });
});

