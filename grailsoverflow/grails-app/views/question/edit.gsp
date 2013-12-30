<%@ page contentType="text/html;charset=UTF-8" %>

<%@page import="fr.isima.grailsoverflow.User" %>

<html>
    <head>
        <meta name="layout" content="questionLayout"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GrailsOverflow - Edit question</title>

        <link href="${resource(dir: 'css', file: 'question.css')}" rel="stylesheet">
        <g:javascript src="ckeditor/ckeditor.js" />
    </head>
    <body>
        <!-- Security test -->
        <g:if test="${User.isUserAuthenticated() && User.CurrentUser.isOwnerOfQuestion(question)}" >
            <div class="page-header">
                <h1>${question.title}<br />
                    <small>Edit your question</small>
                </h1>
            </div>
            <div id="js_contentRequired" style="display: none;" class="alert alert-danger">
                <a class="close">&times;</a>
                Your answer is empty.
            </div>
            <g:form action="editQuestion" id="${question.id}">
                <textarea name="newQuestionContent" id="CKEditor" placeholder="Your question here ..." required><br />
                    ${question.content}
                </textarea>
                <br />
                <g:link style="text-decoration: none;" action="showQuestion" params='[question: "${question.id}"]'>
                    <button type="button" class="btn btn-default">Cancel</button>
                </g:link>
                <button type="submit" class="btn btn-primary">Accept</button>
            </g:form>
            
            <script>
                CKEDITOR.replace('CKEditor').on('required', function( evt ) {
                    $("#js_contentRequired").fadeIn(1000);
                    evt.cancel(); // Prevent submit.
                });
            </script>
        </g:if>
        <g:else>
            <own:redirectIndex />
        </g:else>
            
        <script>
            $(".close").click(function() {
                $(this).parent().fadeOut(500);
                return false;
            });
        </script>
</body>
</html>
