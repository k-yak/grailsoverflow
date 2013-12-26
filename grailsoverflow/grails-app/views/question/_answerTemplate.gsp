<%@page import="fr.isima.grailsoverflow.User" %> 
<%@page import="fr.isima.grailsoverflow.Vote" %> 

<div class="row panel-body list-group">
    <div class="col-md-1">
        <div id="vote">
            <g:set var="upArrowStyle" value="vote" />
            <g:if test="${User.isUserAuthenticated() == true && answer.vote.getUserVote(User.CurrentUser) == Vote.VOTE_UP}">
                <g:set var="upArrowStyle" value="vote selected"  />
            </g:if>
            <g:remoteLink class="${upArrowStyle}" controller="message" action="voteUp" update="answer_voteContent_${answer.id}" id="${answer.id}">
                <span class="glyphicon glyphicon-chevron-up"></span><br />
            </g:remoteLink>
            <span id="answer_voteContent_${answer.id}">${answer.vote.value}</span><br />
            <g:set var="downArrowStyle" value="vote" />
            <g:if test="${User.isUserAuthenticated() == true && answer.vote.getUserVote(User.CurrentUser) == Vote.VOTE_DOWN}">
                <g:set var="downArrowStyle" value="vote selected"  />
            </g:if>
            <g:remoteLink class="${downArrowStyle}" controller="message" action="voteDown" update="answer_voteContent_${answer.id}" id="${answer.id}">
                <span class="glyphicon glyphicon-chevron-down"></span><br />
            </g:remoteLink>
            <g:set var="tickStyle" value="gray-tick" />
            <g:if test="${answer.accepted == true}">
                <g:set var="tickStyle" value="gray-tick selected" />
            </g:if>
            <g:if test="${question.user == User.CurrentUser}">
                <div id="answerTick">
                    <hr />
                    <g:remoteLink class="${tickStyle}" controller="answer" action="accept" update="" id="${answer.id}">
                        <span class="glyphicon glyphicon-ok tick"></span>
                    </g:remoteLink>
                </div>
            </g:if>
            <g:elseif test="${answer.accepted == true}">
                <div id="answerTick">
                    <hr />
                    <g:remoteLink class="gray-tick selected">
                        <span class="glyphicon glyphicon-ok tick"></span>
                    </g:remoteLink>
                </div>
            </g:elseif>
        </div>
    </div>
    <div id="answerContent" class="col-md-11">
        <blockquote>
            ${answer.content}
            <p></p>
            <small>Answered by <cite>${answer.user.displayName}</cite></small>
        </blockquote>
    </div>
</div>
<div id="answerSeparator">
    <hr />
</div>