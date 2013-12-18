<div class="col-6 col-sm-6 col-lg-4">
    <g:link action="showQuestion" params='[title: "${question.title}"]'>
        <h2>${question.title}</h2>
    </g:link>
    <p>${question.content}</p>
    <span>Status : ${question.status}</span><br />
    <span>Tags :</span><br />
    <g:each in="${question.tags}" var="tag">
        <li>${tag.name}</li>
    </g:each>
</div>