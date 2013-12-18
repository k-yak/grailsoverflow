<g:if test="${neededTag != null && neededTag.name == tag.name}">
    <g:set var="styleClass" value="list-group-item active"/>
</g:if>
<g:else>
    <g:set var="styleClass" value="list-group-item"/>
</g:else>

<g:link class="${styleClass}" action="questionsForTags" params='[name: "${tag.name}"]'>
    ${tag.name}
</g:link>