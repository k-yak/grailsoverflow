<%@ page contentType="text/html;charset=UTF-8" %>

<%@page import="fr.isima.grailsoverflow.AppConfig; fr.isima.grailsoverflow.User" %>

<html>
<head>
    <meta name="layout" content="questionLayout"/>
    <title>GrailsOverflow - Questions</title>

    <link href="${resource(dir: 'css', file: 'question.css')}" rel="stylesheet">
</head>

<body>
<div class="row row-offcanvas row-offcanvas-right">
    <div class="col-xs-12 col-sm-9">
        <div class="page-header">
            <h1><small>${user.displayName}</small></h1>
        </div>

        <div class="row">
            <g:render template="/question/questionTemplate" collection="${questionsToDisplay}" var="question"/>

            <div class="center">
            </div>
        </div>
    </div>


    <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" >
        <g:if test="${tags != null && !tags.isEmpty()}">
            <div class="form-group">
                <label for="sidebar">Related tags</label>
                <ul class="list-group">
                    <g:render template="/question/tagTemplate" collection="${tags}" var="tag"/>
                </ul>
            </div>
        </g:if>
        <g:if test="${session.user != null && session.user.favoriteTags != null && !session.user.favoriteTags.isEmpty()}">
            <div class="form-group">
                <label for="sidebar">Favorite tags</label>
                <ul class="list-group">
                    <g:render template="/question/tagTemplate" collection="${session.user.favoriteTags}" var="tag"/>
                </ul>
            </div>
        </g:if>
    </div>
</div>
</body>
</html>

