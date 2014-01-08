<!DOCTYPE html>
<html>
	<head>
		<title><g:if env="development">Grails Runtime Exception</g:if><g:else>GrailsOverflow - Error</g:else></title>
        <meta name="layout" content="questionLayout"/>
		<g:if env="development">
            <link rel="stylesheet" href="${resource(dir: 'css', file: 'errors.css')}" type="text/css">
        </g:if>

        <link href="${resource(dir: 'css', file: 'question.css')}" rel="stylesheet">

        <title>GrailsOverflow - Questions</title>

	</head>
	<body>

        <g:if env="development">
            <g:renderException exception="${exception}" />
        </g:if>
        <g:else>
			<div class="center"><strong>
                <g:if test="${errorContent != null}">
                    ${errorContent}
                </g:if>
                <g:else>
				    An error has occurred
                </g:else>
            </strong></div>
        </g:else>
	</body>
</html>
