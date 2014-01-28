<%@ page contentType="text/html;charset=UTF-8" %>

<%@page import="fr.isima.grailsoverflow.AppConfig; fr.isima.grailsoverflow.User" %>

<html>
<head>
    <meta name="layout" content="questionLayout"/>
    <title>GrailsOverflow - <g:message code="grow.user.user" /></title>

    <link type="text/css" href="${resource(dir: 'css', file: 'question.css')}" rel="stylesheet">
    <link type="text/css" href="${resource(dir: 'css', file: 'user.css')}" rel="stylesheet">
    <link href="${resource(dir: 'css/tagit', file: 'jquery.tagit.css')}" rel="stylesheet">
    <link href="${resource(dir: 'css/tagit', file: 'tagit.ui-zendesk.css')}" rel="stylesheet">

    <g:javascript src="tagit/tag-it.js" />

    <script>
        $(function(){
            var sampleTags = ['c', 'ant', 'c++', 'java', 'php', 'c#', 'groovy', 'jquery', 'grails', 'javascript', 'asp', 'ruby', 'python', 'scala', 'haskell', 'perl', 'erlang', 'apl', 'cobol', 'go', 'lua'];

            //-------------------------------
            // Single field
            //-------------------------------
            $('#tagit_singleFieldTags').tagit({
                availableTags: sampleTags,
                // This will make Tag-it submit a single form value, as a comma-delimited field.
                singleField: true,
                singleFieldNode: $('#tagit_singleFieldTags_value')
            });
        });
    </script>
</head>

<body>
<div class="row row-offcanvas row-offcanvas-right">
    <div class="col-xs-18 col-sm-12">
        <div class="page-header">
            <div class="row">
                <div class="col-xs-10 col-sm-11">
                    <h1><g:message code="grow.user.user" /> <small>${user.displayName}</small></h1>
                </div>
                <div class="col-xs-3 col-sm-1">
                    <g:link controller="user" action="show" params='[id: "${user.id}"]'>
                        <button type="button" class="btn btn-default rightButton">
                            <span class="glyphicon glyphicon-user"></span>
                            <g:message code="grow.user.show" />
                        </button>
                    </g:link>
                </div>
            </div>
        </div>

        <div class="row">
            <g:form action="editInfo">

                <input name="id" type="hidden" class="form-control" id="id" value="${user.id}">

                <div class="form-group">
                    <label for="displayName"><g:message code="grow.user.display.name" /></label>
                    <input name="displayName" type="text" class="form-control" id="displayName" value="${user.displayName}" placeholder="${ message( code:'grow.user.your.display.name' ) } ...">
                </div>
                <div class="form-group">
                    <label for="website"><g:message code="grow.user.website" /></label>
                    <input name="website" type="text" class="form-control" id="website" value="${user.website}" placeholder="${ message( code:'grow.user.your.website' ) } ...">
                </div>
                <div class="form-group">
                    <label for="location"><g:message code="grow.user.location" /></label>
                    <input name="location" type="text" class="form-control" id="location" value="${user.location}" placeholder="${ message( code:'grow.user.your.location' ) } ...">
                </div>
                <div class="form-group">
                    <label for="tagit_singleFieldTags"><g:message code="grow.user.tags" /></label>
                    <input name="tags" id="tagit_singleFieldTags_value" value="${user.favoriteTagsToString()}" hidden="true">
                    <ul id="tagit_singleFieldTags"></ul>
                </div>

                <button type="submit" class="btn btn-default btn-lg">
                    <span class="glyphicon glyphicon-ok"></span> <g:message code="grow.user.save.profile" />
                </button>
            </g:form>
        </div>
    </div>
</div>
</body>
</html>