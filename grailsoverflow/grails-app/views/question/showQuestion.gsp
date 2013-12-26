<%@ page contentType="text/html;charset=UTF-8" %>

<%@page import="fr.isima.grailsoverflow.User" %> 
<%@page import="fr.isima.grailsoverflow.Vote" %> 

<html>
    <head>
        <meta name="layout" content="questionLayout"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GrailsOverflow - ${question.title}</title>

        <link href="${resource(dir: 'css', file: 'question.css')}" rel="stylesheet">
        <g:javascript src="ckeditor/ckeditor.js" />
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
            <strong>+100</strong> to anyone that answer this question. 
            <g:if test="${User.isUserAuthenticated() == true}">
                <span class="alert-link">Try your luck!</span>
            </g:if>
            <g:else>
                <g:set var="targetUri" value="${request.forwardURI - request.contextPath}" scope="session" />
                <oauth:connect class="alert-link" provider="google" id="google-connect-link">Sign In!</oauth:connect>
            </g:else>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2>${question.title}</h2>
            </div>
            <div class="row panel-body">
                <div class="col-md-1">
                    <div id="vote">
                        <g:set var="upArrowStyle" value="vote" />
                        <g:if test="${User.isUserAuthenticated() == true && question.vote.getUserVote(User.CurrentUser) == Vote.VOTE_UP}">
                            <g:set var="upArrowStyle" value="vote selected"  />
                        </g:if>
                        <g:remoteLink class="${upArrowStyle}" controller="message" action="voteUp" update="voteContent" id="${question.id}">
                            <span  class="glyphicon glyphicon-chevron-up"></span><br />
                        </g:remoteLink>
                        <span id="voteContent">${question.vote.value}</span><br />
                        <g:set var="downArrowStyle" value="vote"/>
                        <g:if test="${User.isUserAuthenticated() == true && question.vote.getUserVote(User.CurrentUser) == Vote.VOTE_DOWN}">
                            <g:set var="downArrowStyle" value="vote selected"/>
                        </g:if>
                        <g:remoteLink id="voteDown" class="${downArrowStyle}" controller="message" action="voteDown" update="voteContent" id="${question.id}">
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
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2>${question.answers.size()} answer<g:if test="${question.answers.size() > 1}">s</g:if></h2>
            </div>
            <g:render template="/question/answerTemplate" collection="${question.sortedAnswers()}" var="answer"/>
        </div>
        <g:if test="${User.isUserAuthenticated() == true}">
            <div id="js_contentRequired" style="display: none;" class="alert alert-danger">
                <a class="close">&times;</a>
                Your answer is empty.
            </div>
            <g:form name="myForm" action="answer" id="${question.id}">
                <textarea name="answerContent" id="CKEditor" placeholder="Your answer here ..." required></textarea><br />
                <button type="submit" class="btn btn-default btn-lg">
                    <span class="glyphicon glyphicon-ok"></span> Post your answer
                </button>
            </g:form>
            <script>
                CKEDITOR.replace('CKEditor').on('required', function( evt ) {
                    $("#js_contentRequired").fadeIn(1000);
                    evt.cancel(); // Prevent submit.
                });
                
                
                $("#vote a").click(function() {
                    var shouldSelect = true;
                    
                    if (this.classList.contains('selected'))
                        shouldSelect = false;
                      
                    $(this).parent().find('a').each(function(){
                        $(this).removeClass('selected');
                    });
                    
                    if (shouldSelect)
                        $(this).addClass('selected');
                });
            </script>
        </g:if>
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

