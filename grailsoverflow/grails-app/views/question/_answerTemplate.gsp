<%@page import="fr.isima.grailsoverflow.User" %> 
<%@page import="fr.isima.grailsoverflow.Vote" %>

<div class="row panel-body list-group">
    <div class="col-md-1 col-sm-2 col-xs-2">
        <!-- Vote panel -->
        <div id="vote">
            <g:set var="upArrowStyle" value="vote" />
            <g:if test="${session.user != null && answer.vote.getUserVote(session.user) == Vote.VOTE_UP}">
                <g:set var="upArrowStyle" value="vote selected"  />
            </g:if>
            <g:remoteLink class="${upArrowStyle}" controller="message" action="voteUp" update="answer_voteContent_${answer.id}" id="${answer.id}">
                <span class="glyphicon glyphicon-chevron-up"></span><br />
            </g:remoteLink>
            <span id="answer_voteContent_${answer.id}">${answer.vote.value}</span><br />
            <g:set var="downArrowStyle" value="vote" />
            <g:if test="${session.user != null && answer.vote.getUserVote(session.user) == Vote.VOTE_DOWN}">
                <g:set var="downArrowStyle" value="vote selected"  />
            </g:if>
            <g:remoteLink class="${downArrowStyle}" controller="message" action="voteDown" update="answer_voteContent_${answer.id}" id="${answer.id}">
                <span class="glyphicon glyphicon-chevron-down"></span><br />
            </g:remoteLink>

            <!-- Accepted answer panel -->
            <g:set var="tickStyle" value="gray-tick" />
            <g:if test="${answer.accepted == true}">
                <g:set var="tickStyle" value="gray-tick selected" />
            </g:if>
            <g:if test="${session.user != null && question.user.email == session.user.email}">
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
    <div id="answerContent" class="col-md-11 col-sm-4 col-xs-10">
        <blockquote>
            ${answer.content}
            <small>Answered ${answer.dateCreated.format('dd MMM yyyy')} at ${answer.dateCreated.format('HH:mm')} by
            <g:link class="userLink" controller="user" action="show" params='[id: "${answer.user.id}"]'>${answer.user.displayName}</g:link></small>

             <!-- Edit/Delete panel -->
            <g:if test="${session.user != null && session.user.isOwnerOfAnswer(answer)}" >
                <br />
                <g:link style="text-decoration: none;" controller="answer" action="edit" params='[answer: "${answer.id}"]'>
                    <button type="button" class="btn btn-default btn-xs">Edit</button>
                </g:link>
                <g:link style="text-decoration: none;" controller="answer" action="delete" params='[answer: "${answer.id}"]'>
                    <button type="button" class="btn btn-danger btn-xs">Delete</button>
                </g:link>
            </g:if>
        </blockquote>
    </div>
</div>
<div id="answerSeparator">
    <hr />
</div>