<%@ page contentType="text/html;charset=UTF-8" %>

<%@page import="fr.isima.grailsoverflow.User" %>

<html>
<head>
    <meta name="layout" content="questionLayout"/>
    <title>GrailsOverflow - <g:message code="grow.question.add.new.question" /></title>

    <link href="${resource(dir: 'css', file: 'question.css')}" rel="stylesheet">
    <link href="${resource(dir: 'css/tagit', file: 'jquery.tagit.css')}" rel="stylesheet">
    <link href="${resource(dir: 'css/tagit', file: 'tagit.ui-zendesk.css')}" rel="stylesheet">

    <g:javascript src="tagit/tag-it.js" />
    <g:javascript src="ckeditor/ckeditor.js" />
    <g:javascript src="tagit_trigger.js" />
</head>

<body>
<div class="row row-offcanvas row-offcanvas-right">
    <div class="col-xs-12 col-sm-9">
        <div class="page-header">
            <h1>Question <small><g:message code="grow.question.add.new.question" /></small></h1>
        </div>

        <div class="row">
            <!-- Security test -->
            <g:if test="${session.user != null}" >
                <div id="js_contentRequired" style="display: none;" class="alert alert-danger">
                    <a class="close">&times;</a>
                    <g:message code="grow.question.add.question.empty" />
                </div>
                <g:form action="addQuestion">
                    <div class="form-group">
                        <label for="inputTitle"><g:message code="grow.question.add.title" /></label>
                        <input name="newQuestionTitle" type="text" class="form-control" id="inputTitle" placeholder="${ message( code:'grow.add.title' ) } ..." requiredtag>
                    </div>
                    <div class="form-group">
                        <label for="CKEditor"><g:message code="grow.question.add.content" /></label>
                        <textarea name="newQuestionContent" id="CKEditor" placeholder="${ message( code:'grow.add.question.content' ) } ..." required><br />
                        </textarea>
                    </div>
                    <div class="form-group">
                        <label for="tagit_singleFieldTags"><g:message code="grow.question.add.tags" /></label>
                        <input name="tags" id="tagit_singleFieldTags_value" value="" hidden="true">
                        <ul id="tagit_singleFieldTags"></ul>
                    </div>
                    <button type="submit" class="btn btn-default btn-lg">
                        <span class="glyphicon glyphicon-ok"></span> <g:message code="grow.question.add.post.question" />
                    </button>
                </g:form>

                <g:javascript src="ckeditor/ckeditor_trigger.js" />
            </g:if>
            <g:else>
                <own:redirectIndex />
            </g:else>

            <g:javascript src="fadeout_close.js" />
        </div>
    </div>

    <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" >
        <g:render template="/question/tagTemplate" collection="${tags}" var="tag"/>
    </div>
</div>
</body>
</html>

