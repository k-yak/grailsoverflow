<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GrailsOverflow - Questions</title>

        <link href="${resource(dir: 'bootstrap/css', file: 'bootstrap.min.css')}" rel="stylesheet">
        <link href="${resource(dir: 'css', file: 'question.css')}" rel="stylesheet">

        <script type="${resource(dir: 'bootstrap/js', file: 'bootstrap.min.js')}"></script>
    </head>

    <body>
        <div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
        </div>

        <div class="container">
            <div class="row row-offcanvas row-offcanvas-right">
                <div class="col-xs-12 col-sm-9">
                    <p class="pull-right visible-xs">
                        <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
                    </p>
                    <div class="jumbotron">
                        <h1>Questions</h1>
                        <p>List of questions</p>
                    </div>
                    <div class="row">
                        <g:render template="questionTemplate" collection="${questions}" var="question"/>
                    </div>
                </div>

                <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" >
                    <div class="">
                        <g:render template="tagTemplate" collection="${tags}" var="tag"/>
                    </div>
                </div>
            </div>

            <hr>

            <footer>
                <p>© GrailsOverflow 2013</p>
            </footer>

        </div><!--/.container-->
    </body>
</html>

