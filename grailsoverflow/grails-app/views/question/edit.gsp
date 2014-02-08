<%@ page contentType="text/html;charset=UTF-8" %>

<%@page import="fr.isima.grailsoverflow.User" %>

<html>
    <head>
        <meta name="layout" content="questionLayout"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GrailsOverflow - Edit question</title>

        <link href="${resource(dir: 'css', file: 'question.css')}" rel="stylesheet">
        <link href="${resource(dir: 'css/tagit', file: 'jquery.tagit.css')}" rel="stylesheet">
        <link href="${resource(dir: 'css/tagit', file: 'tagit.ui-zendesk.css')}" rel="stylesheet">

        <g:javascript src="tagit/tag-it.js" />
        <g:javascript src="ckeditor/ckeditor.js" />
        <g:javascript src="tagit_trigger.js" />
    </head>
    <body>
        <!-- Security test -->
        <g:if test="${session.user != null && ( session.user.isOwnerOfQuestion(question) || session.user.admin == true ) }" >
            <div class="page-header">
                <h1>${question.title}<br />
                    <small><g:message code="grow.question.edit.edit.your.question" /></small>
                </h1>
            </div>
            <div id="js_contentRequired" style="display: none;" class="alert alert-danger">
                <a class="close">&times;</a>
                <g:message code="grow.question.edit.answer.empty" />.
            </div>
            <g:form action="editQuestion" id="${question.id}">
                <div class="form-group">
                    <label for="inputTitle"><g:message code="grow.question.edit.title" /></label>
                    <input name="newQuestionTitle" type="text" class="form-control" id="inputTitle" placeholder="Question title ..." value="${question.title}" required>
                </div>
                <div class="form-group">
                    <label for="CKEditor"><g:message code="grow.question.edit.content" /></label>
                    <textarea name="newQuestionContent" id="CKEditor" placeholder="Your question here ..." required><br />
                        ${question.content}
                    </textarea>
                </div>
                <div class="form-group">
                    <label for="tagit_singleFieldTags"><g:message code="grow.question.edit.tags" /></label>
                    <input name="tags" id="tagit_singleFieldTags_value" value="${question.tagsToString()}" hidden="true">
                    <ul id="tagit_singleFieldTags"></ul>
                </div>
                <g:link style="text-decoration: none;" action="showQuestion" params='[question: "${question.id}"]'>
                    <button type="button" class="btn btn-default"><g:message code="grow.question.edit.cancel" /></button>
                </g:link>
                <button type="submit" class="btn btn-primary"><g:message code="grow.question.edit.accept" /></button>
            </g:form>

            <g:javascript src="ckeditor/ckeditor_trigger.js" />
        </g:if>
        <g:else>
            <own:redirectIndex />
        </g:else>

    <g:javascript src="fadeout_close.js" />
</body>
</html>
