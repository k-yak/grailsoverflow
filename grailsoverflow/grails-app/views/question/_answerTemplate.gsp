<%@page import="fr.isima.grailsoverflow.User" %> 
<%@page import="fr.isima.grailsoverflow.Vote" %> 

<div class="row panel-body">
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
                <span class="glyphicon glyphicon-chevron-down"></span>
            </g:remoteLink>                    
        </div>
    </div>
    <div class="col-md-11">
        <blockquote>
            ${answer.content}
            <p></p>
            <small>Answered by <cite>${answer.user.displayName}</cite></small>
        </blockquote>
    </div>

</div>