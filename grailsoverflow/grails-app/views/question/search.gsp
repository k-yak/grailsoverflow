<%@ page contentType="text/html;charset=UTF-8" %>

<%@page import="fr.isima.grailsoverflow.AppConfig; fr.isima.grailsoverflow.User; fr.isima.grailsoverflow.Question" %>

<html>
<head>
    <meta name="layout" content="questionLayout"/>
    <title>GrailsOverflow - <g:message code="grow.question.search.search" /></title>

    <link href="${resource(dir: 'css', file: 'question.css')}" rel="stylesheet">
</head>

<body>
<div class="row row-offcanvas row-offcanvas-right">
    <div class="col-xs-12 col-sm-12">
        <div class="page-header">
            <h1><g:message code="grow.question.search.questions" /> <small><g:message code="grow.question.search.search" /></small></h1>
        </div>

        <div class="row">
            <g:if test="${searchedQuestions?.results != null}" >
                <g:if test="${!searchedQuestions.results.isEmpty()}">
                    <g:each var="question" in="${searchedQuestions.results}">
                        <g:render template="/question/questionTemplate" bean="${Question.get(question.id)}" var="question"/>
                    </g:each>

                    <div class="center">
                        <g:set var="totalPages" value="${Math.ceil(searchedQuestions.total / searchedQuestions.max)}" />
                        <own:paginate controller="${controllerName}" params="[q: params.q]" action="${actionName}" max="${searchedQuestions.max}" total="${searchedQuestions.total}" prev="Previous" next="Next"/>
                    </div>
                </g:if>
                <g:else>
                    <div class="center"><g:message code="grow.question.search.no.match" /></div>
                </g:else>
            </g:if>
            <g:else>
                <div class="center"><g:message code="grow.question.search.no.match" /></div>
            </g:else>
        </div>
    </div>
</div>
</body>
</html>

