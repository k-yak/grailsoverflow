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
                        <div class="col-6 col-sm-6 col-lg-4">
                            <h2>Heading</h2>
                            <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                            <p><a class="btn btn-default" href="./Off Canvas Template for Bootstrap_files/Off Canvas Template for Bootstrap.html" role="button">View details »</a></p>
                        </div>
                    </div>
                </div>

                <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" >
                    <div class="">
                        <a href="" class="list-group-item">Link</a>
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

