<%@ page contentType="text/html;charset=UTF-8" %>

<%@page import="fr.isima.grailsoverflow.User" %>

<html>
<head>
    <meta name="layout" content="questionLayout"/>
    <title>GrailsOverflow - New question</title>

    <link href="${resource(dir: 'css', file: 'question.css')}" rel="stylesheet">
    <g:javascript src="ckeditor/ckeditor.js" />
</head>

<body>
<div class="row row-offcanvas row-offcanvas-right">
    <div class="col-xs-12 col-sm-9">
        <div class="page-header">
            <h1>Question <small>New question</small></h1>
        </div>

        <div class="row">
            <!-- Security test -->
            <g:if test="${User.isUserAuthenticated()}" >
                <div id="js_contentRequired" style="display: none;" class="alert alert-danger">
                    <a class="close">&times;</a>
                    Your question is empty.
                </div>
                <g:form action="addQuestion">
                    <textarea name="newQuestionContent" id="CKEditor" placeholder="Your question here ..." required><br />
                    </textarea>
                    <br />
                    <button type="submit" class="btn btn-default btn-lg">
                        <span class="glyphicon glyphicon-ok"></span> Post your question
                    </button>
                </g:form>

                <script>
                    CKEDITOR.replace('CKEditor').on('required', function( evt ) {
                        $("#js_contentRequired").fadeIn(1000);
                        evt.cancel(); // Prevent submit.
                    });
                </script>
            </g:if>
            <g:else>
                <own:redirectIndex />
            </g:else>

            <script>
                $(".close").click(function() {
                    $(this).parent().fadeOut(500);
                    return false;
                });
            </script>
        </div>
    </div>

    <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" >
        <g:render template="/question/tagTemplate" collection="${tags}" var="tag"/>
    </div>
</div>
</body>
</html>

