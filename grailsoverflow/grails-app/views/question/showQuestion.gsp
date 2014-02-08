<%@ page contentType="text/html;charset=UTF-8" %>

<%@page import="fr.isima.grailsoverflow.User" %>
<%@page import="fr.isima.grailsoverflow.Vote" %>
<%@page import="fr.isima.grailsoverflow.AppConfig" %>

<html>
    <head>
        <meta name="layout" content="questionLayout"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GrailsOverflow - ${question.title}</title>

        <link href="${resource(dir: 'css', file: 'question.css')}" rel="stylesheet">
        <g:javascript src="ckeditor/ckeditor.js" />
    </head>

    <body>
        <!-- Vote logged in error -->
        <g:if test="${session.user == null}">
            <div id="js_voteLogin" style="display: none;" class="alert alert-danger">
                <a class="close">&times;</a>
                <g:set var="targetUri" value="${request.forwardURI - request.contextPath}" scope="session" />
                <g:message code="grow.question.showQuestion.you.must.be" /> <oauth:connect class="alert-link" provider="google" id="google-connect-link"><g:message code="grow.question.showQuestion.logged.in" /></oauth:connect> <g:message code="grow.question.showQuestion.to.vote" />.
            </div>
        </g:if>

        <!-- Answer points message -->
        <g:if test="${question.status != 'grow.status.accepted'}">
            <div class="alert alert-info">
                <strong>+${AppConfig.ANSWER_SCORE}</strong> <g:message code="grow.question.showQuestion.give.point" />.
                <g:if test="${session.user != null}">
                    <span class="alert-link">Try your luck!</span>
                </g:if>
                <g:else>
                    <g:set var="targetUri" value="${request.forwardURI - request.contextPath}" scope="session" />
                    <oauth:connect class="alert-link" provider="google" id="google-connect-link"><g:message code="grow.question.showQuestion.sign.in" />!</oauth:connect>
                    </g:else>
            </div>
        </g:if>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2>${question.title}</h2>
            </div>
            <div class="row panel-body">
                <div class="col-md-1 col-sm-2 col-xs-2">

                    <!-- Vote panel -->
                    <div id="vote">
                        <g:set var="upArrowStyle" value="vote" />
                        <g:if test="${session.user != null && question.vote.getUserVote(session.user) == Vote.VOTE_UP}">
                            <g:set var="upArrowStyle" value="vote selected"  />
                        </g:if>
                        <g:remoteLink class="${upArrowStyle}" controller="message" action="voteUp" update="voteContent" id="${question.id}">
                            <span class="glyphicon glyphicon-chevron-up"></span><br />
                        </g:remoteLink>
                        <span id="voteContent">${question.vote.value}</span><br />
                        <g:set var="downArrowStyle" value="vote"/>
                        <g:if test="${session.user != null && question.vote.getUserVote(session.user) == Vote.VOTE_DOWN}">
                            <g:set var="downArrowStyle" value="vote selected"/>
                        </g:if>
                        <g:remoteLink class="${downArrowStyle}" controller="message" action="voteDown" update="voteContent" id="${question.id}">
                            <span class="glyphicon glyphicon-chevron-down"></span>
                        </g:remoteLink>
                    </div>
                </div>

                <!-- Question panel -->
                <div class="col-md-11 col-sm-4 col-xs-10">
                    <blockquote>
                        <p>${question.content}</p>
                        <small><g:message code="grow.question.showQuestion.asked" /> ${question.dateCreated.format('dd MMM yyyy')} <g:message code="grow.question.showQuestion.at" /> ${question.dateCreated.format('HH:mm')} <g:message code="grow.question.showQuestion.by" />
                            <g:link class="userLink" controller="user" action="show" params='[id: "${question.user.id}"]'>${question.user.displayName}</g:link></small>

                        <!-- Edit/Delete panel -->
                            <g:if test="${session.user != null && (session.user.isOwnerOfQuestion(question) || session.user.admin == true)}" >
                            <br />
                            <g:link style="text-decoration: none;" action="edit" params='[question: "${question.id}"]'>
                                <button type="button" class="btn btn-default btn-xs"><g:message code="grow.question.showQuestion.edit" /></button>
                            </g:link>
                            <g:link style="text-decoration: none;" action="delete" params='[question: "${question.id}"]'>
                                <button type="button" class="btn btn-danger btn-xs"><g:message code="grow.question.showQuestion.delete" /></button>
                            </g:link>
                        </g:if>
                    </blockquote>
                </div>
            </div>
            <ul class="list-group">
                <li class="list-group-item"><b><g:message code="grow.question.showQuestion.status" /> :</b> <g:message code="${question.status}"/></li>
                <li class="list-group-item"><b><g:message code="grow.question.showQuestion.tags" /> :</b>
                    <g:each in="${question.tags}" var="tag">
                        <g:link class="${styleClass}" style="text-decoration: none" action="questionsForTag" params='[tag: "${tag.name}"]'>
                            <span class="label label-primary" >${tag.name}</span>
                        </g:link>
                    </g:each>
                </li>
            </ul>
        </div>

        <!-- Answers panel -->
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2>${question.answers.size()} <g:message code="grow.question.showQuestion.answer" /><g:if test="${question.answers.size() > 1}">s</g:if></h2>
                </div>
            <g:render template="/question/answerTemplate" collection="${question.sortedAnswers()}" var="answer" />
        </div>

        <!-- Answer textarea  -->
        <g:if test="${session.user != null}">
            <div id="js_contentRequired" style="display: none;" class="alert alert-danger">
                <a class="close">&times;</a>
                <g:message code="grow.question.showQuestion.answer.empty" />
            </div>
            <g:form action="answer" id="${question.id}">
                <textarea name="answerContent" id="CKEditor" placeholder="Your answer here ..." required></textarea><br />
                <button type="submit" class="btn btn-default btn-lg">
                    <span class="glyphicon glyphicon-ok"></span> <g:message code="grow.question.showQuestion.post.answer" />
                </button>
            </g:form>
            <g:javascript src="ckeditor/ckeditor_trigger.js" />
            <g:javascript src="vote_accept.js" />
        </g:if>
        <g:javascript src="fadeout_close.js" />
        <g:javascript src="fadeout_vote.js" />
    </body>
</html>

