<dt>
    <g:if test="${medal.type == fr.isima.grailsoverflow.Medal.BRONZE}">
        <g:img class="medals" dir="images/medals" file="medal-bronze.png" />
    </g:if>
    <g:if test="${medal?.type == fr.isima.grailsoverflow.Medal.SILVER}">
        <g:img class="medals" dir="images/medals" file="medal-silver.png" />
    </g:if>
    <g:if test="${medal?.type == fr.isima.grailsoverflow.Medal.GOLD}">
        <g:img class="medals" dir="images/medals" file="medal-gold.png" />
    </g:if>
</dt>
<dd>
    <g:message code="${medal.title}" args="${[medal.value]}"/>
</dd>