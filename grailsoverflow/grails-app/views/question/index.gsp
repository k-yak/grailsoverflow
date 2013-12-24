<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta name="layout" content="questionLayout"/>
        <title>GrailsOverflow - Questions</title>

        <link href="${resource(dir: 'css', file: 'question.css')}" rel="stylesheet">
    </head>

    <body>
        <div class="row row-offcanvas row-offcanvas-right">
            <div class="col-xs-12 col-sm-9">
                <div class="page-header">
                    <h1>Questions <small>${subtitle}</small></h1>
                </div>
              
                <div class="row">
                    <g:render template="/question/questionTemplate" collection="${questionsToDisplay}" var="question"/>
                </div>
            </div>

            <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" >
                <g:render template="/question/tagTemplate" collection="${tags}" var="tag"/>
            </div>
        </div>
    </body>
</html>

