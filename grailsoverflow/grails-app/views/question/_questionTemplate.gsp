<!-- <div class="col-6 col-sm-6"> -->
<div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <g:link controller="question" action="showQuestion" params='[question: "${question.id}"]'>
                <h4>${question.title}</h4>
            </g:link>
        </div>
        <div class="panel-body">
            <p>
                <own:oneLineContent content="${question.content}" />
            </p>
            <div style="float: right; color: darkgray;">
                <small>Asked ${question.dateCreated.format('dd MMM yyyy')} at ${question.dateCreated.format('HH:mm')} by ${question.user.displayName}</small>
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
</div>