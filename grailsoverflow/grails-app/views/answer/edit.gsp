<%@ page contentType="text/html;charset=UTF-8" %>

<%@page import="fr.isima.grailsoverflow.User" %>

<html>
<head>
    <meta name="layout" content="questionLayout"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>GrailsOverflow - <g:message code="grow.edit.edit.anwer" /></title>

    <link href="${resource(dir: 'css', file: 'question.css')}" rel="stylesheet">
    <g:javascript src="ckeditor/ckeditor.js" />
</head>
<body>
<!-- Security test -->
<g:if test="${session.user != null && ( session.user.isOwnerOfAnswer(answer) || session.user.admin)}" >
    <div class="page-header">
        <h1>Question : ${question.title}<br />
            <small><g:message code="grow.edit.edit.edit.anwer" /></small>
        </h1>
    </div>
    <div id="js_contentRequired" style="display: none;" class="alert alert-danger">
        <a class="close">&times;</a>
        <g:message code="grow.edit.edit.empty" />.
    </div>
    <g:form action="editAnswer" id="${answer.id}">
        <textarea name="newAnswerContent" id="CKEditor" placeholder="Your answer here ..." required><br />
            ${answer.content}
        </textarea>
        <br />
        <g:link style="text-decoration: none;" controller="question" action="showQuestion" params='[question: "${question.id}"]'>
            <button type="button" class="btn btn-default"><g:message code="grow.edit.edit.cancel" /></button>
        </g:link>
            <button type="submit" class="btn btn-primary"><g:message code="grow.edit.edit.accept" /></button>
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
