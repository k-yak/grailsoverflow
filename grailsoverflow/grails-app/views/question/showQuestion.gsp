<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GrailsOverflow - ${question.title}</title>

        <link href="${resource(dir: 'bootstrap/css', file: 'bootstrap.min.css')}" rel="stylesheet">
        <link href="${resource(dir: 'css', file: 'question.css')}" rel="stylesheet">

        <script type="${resource(dir: 'bootstrap/js', file: 'bootstrap.min.js')}"></script>
    </head>

    <body>
    <!-- Fixed navbar -->
        <div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
        </div>

        <div class="container">

      <!-- Main component for a primary marketing message or call to action -->
            <div class="jumbotron">
                <h1>${question.title}</h1>
                <p>${question.content}</p>
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

        </div> <!-- /container -->
    </body>
</html>

