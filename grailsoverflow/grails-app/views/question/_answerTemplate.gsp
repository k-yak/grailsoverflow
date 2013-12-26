<div class="row panel-body">
    <div class="col-md-1">
        <div id="vote">
            <g:remoteLink class="vote" controller="answer" action="voteUp" update="answer_voteContent_${answer.id}" id="${answer.id}">
                <span class="glyphicon glyphicon-chevron-up"></span><br />
            </g:remoteLink>
            <span id="answer_voteContent_${answer.id}">${answer.vote.value}</span><br />
            <g:remoteLink class="vote" controller="answer" action="voteDown" update="answer_voteContent_${answer.id}" id="${answer.id}">
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