<%@ page contentType="text/html;charset=UTF-8" %>

<%@page import="fr.isima.grailsoverflow.Medal; fr.isima.grailsoverflow.AppConfig; fr.isima.grailsoverflow.User" %>

<html>
<head>
    <meta name="layout" content="questionLayout"/>
    <title>GrailsOverflow - <g:message code="grow.user.user" /></title>

    <link type="text/css" href="${resource(dir: 'css', file: 'question.css')}" rel="stylesheet">
    <link type="text/css" href="${resource(dir: 'css', file: 'user.css')}" rel="stylesheet">
</head>

<body>
    <div class="row row-offcanvas row-offcanvas-right">
        <div class="col-xs-18 col-sm-12">

            <div class="page-header">
                <div class="row">
                    <div class="col-xs-9 col-sm-9">
                        <h1><g:message code="grow.user.user" /> <small>${user.displayName}</small></h1>
                    </div>
                    <div class="col-xs-3 col-sm-3 rightButton">
                        <g:if test="${session.user!=null && session.user.admin == true && session.user.id != user.id}">
                            <g:remoteLink controller="user" action="ban" params='[id: "${user.id}"]' style="text-decoration:none;" update="banStatus">
                                <button type="button" class="btn btn-default">
                                    <span class="glyphicon glyphicon-minus-sign"></span>
                                    <span id="banStatus">
                                        <g:if test="${user.ban}">
                                            <g:message code="grow.user.unban" />
                                        </g:if>
                                        <g:else>
                                            <g:message code="grow.user.ban" />
                                        </g:else>
                                    </span>
                                </button>
                            </g:remoteLink>
                        </g:if>
                        <g:if test="${session.user?.id == user.id || session.user?.admin == true}">
                            <g:link controller="user" action="edit" params='[id: "${user.id}"]'>
                                <button type="button" class="btn btn-default">
                                    <span class="glyphicon glyphicon-pencil"></span>
                                    <g:message code="grow.user.edit" />
                                </button>
                            </g:link>
                        </g:if>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-4">
                    <a href="http://gravatar.com">
                        <img class="avatar" src="https://www.gravatar.com/avatar/${user.email.encodeAsMD5()}?s=128&r=pg" alt="" />
                    </a>
                    <g:if test="${user.admin}">
                        <p class="admin"><g:message code="grow.user.admin" /></p>
                    </g:if>
                    <g:if test="${user.ban}">
                        <p class="admin"><g:message code="grow.user.banned" /></p>
                    </g:if>
                    <g:if test="${user.email == "daniel.petisme@gmail.com" }">
                        <p class="admin"><g:message code="grow.user.private.joke" />.</p>
                    </g:if>
                    <p class="score">${user.score}</p>
                </div>

                <div class="col-md-8">
                    <table class="table">
                        <tbody>
                        <tr class="info-title">
                            <td><g:message code="grow.user.bio" /></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><g:message code="grow.user.website" /></td>
                            <td>${user.website}</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><g:message code="grow.user.location" /></td>
                            <td>${user.location}</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><g:message code="grow.user.email" /></td>
                            <td>${user.email}</td>
                        </tr>
                        <tr class="info-title">
                            <td><g:message code="grow.user.visits" /></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><g:message code="grow.user.last.visit" /></td>
                            <td>${user.lastVisit.format('dd MMM yyyy')}</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><g:message code="grow.user.last.profileView" /></td>
                            <td>${user.profileView}</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><g:message code="grow.user.last.connectionCounter" /></td>
                            <td>${user.connectionCounter}</td>
                        </tr>
                        <tr class="info-title">
                            <td><g:message code="grow.user.favorite.tags" /></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><g:message code="grow.user.tags" /></td>
                            <td>
                                <g:each in="${user.favoriteTags}" var="tag">
                                    <g:link style="text-decoration: none" controller="question" action="questionsForTag" params='[tag: "${tag.name}"]'>
                                        <span class="label label-primary" >${tag.name}</span>
                                    </g:link>
                                </g:each>
                            </td>
                        </tr>
                        <tr class="info-title">
                            <td><g:message code="grow.user.yourmedals" /></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><g:message code="grow.user.medals" /></td>
                            <td>
                                ${user.getMedalsOfType(Medal.BRONZE)}<g:img class="medals" dir="images/medals" file="medal-bronze.png" />
                                ${user.getMedalsOfType(Medal.SILVER)}<g:img class="medals" dir="images/medals" file="medal-silver.png" />
                                ${user.getMedalsOfType(Medal.GOLD)}<g:img  class="medals" dir="images/medals" file="medal-gold.png" />
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-9 col-sm-6">
                <div class="page-header">
                    <h3><g:message code="grow.user.lastMedals" /></h3>
                </div>
                <dl class="dl-horizontal">
                    <g:render template="/user/medalTemplate" collection="${user.medals}" var="medal"/>
                </dl>
            </div>
            <div class="col-xs-9 col-sm-6">
                <div class="page-header">
                    <h3><g:message code="grow.user.history" /></h3>
                </div>
                <dl class="dl-horizontal">
                    <g:render template="/user/historyTemplate" collection="${userHistory}" var="historyElement"/>
                </dl>
            </div>
        </div>
    </div>
</body>
</html>