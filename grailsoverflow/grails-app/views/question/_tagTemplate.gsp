<g:if test="${neededTag != null && neededTag.name == tag.name}">
    <g:set var="styleClass" value="list-group-item active"/>
</g:if>
<g:else>
    <g:set var="styleClass" value="list-group-item"/>
</g:else>

<g:link class="${styleClass}" controller="question" action="questionsForTags" params='[tag: "${tag.name}"]'>
    ${tag.name}
</g:link>