<!DOCTYPE html>
<html>
	<head>
		<title><g:if env="development">Grails Runtime Exception</g:if><g:else>GrailsOverflow - <g:message code="grow.error.title" /></g:else></title>
        <meta name="layout" content="questionLayout"/>
		<g:if env="development">
            <link rel="stylesheet" href="${resource(dir: 'css', file: 'errors.css')}" type="text/css">
        </g:if>

        <link href="${resource(dir: 'css', file: 'question.css')}" rel="stylesheet">

	</head>
	<body>

        <g:if env="development">
            <g:renderException exception="${exception}" />
        </g:if>
        <g:else>
			<div class="center"><strong>
                <g:if test="${errorContent != null}">
                    <g:message code="${errorContent}" />
                </g:if>
                <g:else>
                    <g:message code="grow.error.occurred" />
                </g:else>
            </strong></div>
        </g:else>
	</body>
</html>
