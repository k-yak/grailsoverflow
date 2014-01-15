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
        <div class="col-xs-18 col-sm-12">

            <div class="page-header">
                <div class="row">
                    <div class="col-xs-10 col-sm-11">
                        <h1>User <small>${user.displayName}</small></h1>
                    </div>
                    <div class="col-xs-3 col-sm-1">
                        <g:if test="${session.user?.id == user.id}">
                            <g:link controller="user" action="edit" params='[id: "${session.user.id}"]'>
                                <button type="button" class="btn btn-default rightButton">
                                    <span class="glyphicon glyphicon-pencil"></span>
                                     Edit
                                </button>
                            </g:link>
                        </g:if>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-4">
                    <img class="avatar" src="https://www.gravatar.com/avatar/${user.email.encodeAsMD5()}?s=128&r=pg" alt="" />
                    <p class="score">${user.score}</p>
                </div>

                <div class="col-md-8">
                    <table class="table">
                        <tbody>
                        <tr class="info-title">
                            <td>bio</td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>website</td>
                            <td>${user.website}</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>location</td>
                            <td>${user.location}</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>email</td>
                            <td>${user.email}</td>
                        </tr>
                        <tr class="info-title">
                            <td>visits</td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>last visit</td>
                            <td>${user.lastVisit.format('dd MMM yyyy')}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </div>
</body>
</html>