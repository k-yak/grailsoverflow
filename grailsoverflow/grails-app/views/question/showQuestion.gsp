<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta name="layout" content="questionLayout"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GrailsOverflow - ${question.title}</title>

        <link href="${resource(dir: 'css', file: 'question.css')}" rel="stylesheet">
    </head>

    <body>
        <div class="jumbotron">
            <h1>${question.title}</h1>
            <own:textToParagraph>${question.content}</own:textToParagraph>
            <br />
            <span>Tags :</span><br />
            <g:each in="${question.tags}" var="tag">
                <li>${tag.name}</li>
                </g:each>
            <br />
            <p>
                <a class="btn btn-lg btn-primary" href="" role="button">Answer »</a>
            </p>
        </div>
    </body>
</html>

