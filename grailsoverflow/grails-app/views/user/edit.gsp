<%@ page contentType="text/html;charset=UTF-8" %>

<%@page import="fr.isima.grailsoverflow.AppConfig; fr.isima.grailsoverflow.User" %>

<html>
<head>
    <meta name="layout" content="questionLayout"/>
    <title>GrailsOverflow - User</title>

    <link type="text/css" href="${resource(dir: 'css', file: 'question.css')}" rel="stylesheet">
    <link type="text/css" href="${resource(dir: 'css', file: 'user.css')}" rel="stylesheet">
</head>

<body>
<div class="row row-offcanvas row-offcanvas-right">
    <div class="col-xs-12 col-sm-9">
        <div class="page-header">
            <h1><small>${user.displayName}</small></h1>
            <g:link class="subtitle-right" controller="user" action="show" params='[id: "${session.user.id}"]'>show</g:link>
        </div>

        <div class="row">
            <g:form action="editInfo">
                <div class="form-group">
                    <label for="displayName">Display name</label>
                    <input name="displayName" type="text" class="form-control" id="displayName" value="${user.displayName}" placeholder="Your display name ...">
                </div>
                <div class="form-group">
                    <label for="website">Website</label>
                    <input name="website" type="text" class="form-control" id="website" value="${user.website}" placeholder="Your website ...">
                </div>
                <div class="form-group">
                    <label for="location">Location</label>
                    <input name="location" type="text" class="form-control" id="location" value="${user.location}" placeholder="Your location ...">
                </div>

                <button type="submit" class="btn btn-default btn-lg">
                    <span class="glyphicon glyphicon-ok"></span> Save profile
                </button>
            </g:form>
        </div>
    </div>
</div>
</body>
</html>