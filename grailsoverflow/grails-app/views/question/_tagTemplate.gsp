<g:if test="${neededTag != null && neededTag.name == tag.name}">
    <g:set var="styleClass" value="list-group-item active"/>
</g:if>
<g:else>
    <g:set var="styleClass" value="list-group-item"/>
</g:else>

<g:link class="${styleClass}" action="questionsForTag" params='[tag: "${tag.name}"]'>
    <span class="badge pull-right">${tag.questions.count{ completeQuestionList*.id.contains(it.id) }} / ${tag.questions.size()}</span>
    ${tag.name}
</g:link>