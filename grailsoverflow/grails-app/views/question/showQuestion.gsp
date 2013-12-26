<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="fr.isima.grailsoverflow.User" %> 

<html>
    <head>
        <meta name="layout" content="questionLayout"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GrailsOverflow - ${question.title}</title>

        <link href="${resource(dir: 'css', file: 'question.css')}" rel="stylesheet">
    </head>

    <body>
        <g:if test="${User.isUserAuthenticated() == false}">
            <div id="js_voteLogin" style="display: none;" class="alert alert-danger">
                <a class="close">&times;</a>
                <g:set var="targetUri" value="${request.forwardURI - request.contextPath}" scope="session" />
                You must be <oauth:connect class="alert-link" provider="google" id="google-connect-link">logged in</oauth:connect> to vote.
            </div>
        </g:if> 
        
        <div class="alert alert-info">
            <strong>+100</strong> to anyone that answer this question <a href="" class="alert-link">Try your luck!</a>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2>${question.title}</h2>
            </div>
            <div class="row panel-body">
                <div class="col-md-1">
                    <div id="vote">
                        <g:remoteLink class="vote" controller="question" action="voteUp" update="voteContent" id="${question.id}">
                            <span class="glyphicon glyphicon-chevron-up"></span><br />
                        </g:remoteLink>
                        <span id="voteContent">${question.vote.value}</span><br />
                        <g:remoteLink class="vote" controller="question" action="voteDown" update="voteContent" id="${question.id}">
                            <span class="glyphicon glyphicon-chevron-down"></span>
                        </g:remoteLink>                    
                    </div>
                </div>
                <div class="col-md-11">
                    <blockquote>
                        <p><own:textToParagraph>${question.content}</own:textToParagraph></p>
                        <small>Asked by <cite>${question.user.displayName}</cite></small>
                    </blockquote>
                </div>

            </div>
            <ul class="list-group">
                <li class="list-group-item"><b>Status :</b> ${question.status}</li>
                <li class="list-group-item"><b>Tags :</b>
                    <g:each in="${question.tags}" var="tag">
                        <g:link class="${styleClass}" style="text-decoration: none" action="questionsForTag" params='[tag: "${tag.name}"]'>
                            <span class="label label-primary" >${tag.name}</span>
                        </g:link>
                    </g:each>
                </li>
            </ul>
        </div>
      <script>
        $(".vote").click(function() {
            $("#js_voteLogin").fadeIn(1000);
            return false;
        });
        $(".close").click(function() {
            $(this).parent().fadeOut(500);
            return false;
        });
      </script>
    </body>
</html>

