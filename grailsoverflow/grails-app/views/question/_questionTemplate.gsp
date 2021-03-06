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
                <small><g:message code="grow.question.showQuestion.asked" /> ${question.dateCreated.format('dd MMM yyyy')} <g:message code="grow.question.showQuestion.at" /> ${question.dateCreated.format('HH:mm')} <g:message code="grow.question.showQuestion.by" />
                    <g:link class="userLink" controller="user" action="show" params='[id: "${question.user.id}"]'>${question.user.displayName}</g:link></small>
            </div>
        </div>
        
        <ul class="list-group">
            <li class="list-group-item"><b><g:message code="grow.question.showQuestion.status" /> :</b> <g:message code="${question.status}"/></li>
            <li class="list-group-item"><b><g:message code="grow.question.showQuestion.tags" /> :</b>
                <g:each in="${question.tags}" var="tag">
                    <g:link style="text-decoration: none" action="questionsForTag" params='[tag: "${tag.name}"]'>
                        <span class="label label-primary" >${tag.name}</span>
                    </g:link>
                </g:each>
            </li>
        </ul>
    </div>
</div>