<div class="col-6 col-sm-6 col-lg-4">
    <g:link controller="question" action="showQuestion" params='[question: "${question.id}"]'>
        <h2>${question.title}</h2>
    </g:link>
    <own:textToParagraph>${question.content}</own:textToParagraph>
    <span>Status : ${question.status}</span><br />
    <span>Tags :</span><br />
    <g:each in="${question.tags}" var="tag">
        <li>${tag.name}</li>
    </g:each>
</div>