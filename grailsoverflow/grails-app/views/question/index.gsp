<%@ page contentType="text/html;charset=UTF-8" %>

<%@page import="fr.isima.grailsoverflow.AppConfig; fr.isima.grailsoverflow.User" %>

<html>
    <head>
        <meta name="layout" content="questionLayout"/>
        <title><g:message code="grow.index.title" /></title>

        <link href="${resource(dir: 'css', file: 'question.css')}" rel="stylesheet">
    </head>

    <body>

        <div class="row row-offcanvas row-offcanvas-right">
            <div class="col-xs-12 col-sm-9">

                <g:if test="${messageContent != null}">
                    <div class="alert alert-${type}">
                        <g:message code="${messageContent}" />
                    </div>
                </g:if>

                <div class="page-header">
                    <h1><g:message code="grow.index.question" /> <small><g:message code="${subtitle}" /></small></h1>
                </div>
              
                <div class="row">
                    <g:render template="/question/questionTemplate" collection="${questionsToDisplay}" var="question"/>

                    <div class="center">
                        <g:set var="totalPages" value="${Math.ceil(completePaginationList.size() / AppConfig.MAX_QUESTION)}" />
                        <own:paginate controller="${controllerName}" action="index" max="${AppConfig.MAX_QUESTION}" total="${completePaginationList.size()}" prev="Previous" next="Next"/>
                    </div>
                </div>
            </div>


            <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" >
                <g:if test="${tags != null && !tags.isEmpty()}">
                    <div class="form-group">
                        <label for="sidebar"><g:message code="grow.index.related.tags" /></label>
                        <ul class="list-group">
                            <g:render template="/question/tagTemplate" collection="${tags}" var="tag"/>
                        </ul>
                    </div>
                </g:if>
                <g:if test="${session.user != null && session.user.favoriteTags != null && !session.user.favoriteTags.isEmpty()}">
                    <div class="form-group">
                        <label for="sidebar"><g:message code="grow.index.favorite.tags" /></label>
                        <ul class="list-group">
                            <g:render template="/question/tagTemplate" collection="${session.user.favoriteTags.take(AppConfig.MAX_TAGS)}" var="tag"/>
                        </ul>
                    </div>
                </g:if>
            </div>
        </div>

    <!--<div id="growImage">
        <g:img dir="images/background" file="seed_background.jpg" height="550px"/>
    </div>-->
    </body>
</html>

