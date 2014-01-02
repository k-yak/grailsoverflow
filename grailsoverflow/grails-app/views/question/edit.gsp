<%@ page contentType="text/html;charset=UTF-8" %>

<%@page import="fr.isima.grailsoverflow.User" %>

<html>
    <head>
        <meta name="layout" content="questionLayout"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GrailsOverflow - Edit question</title>

        <link href="${resource(dir: 'css', file: 'question.css')}" rel="stylesheet">
        <link href="${resource(dir: 'css/tagit', file: 'jquery.tagit.css')}" rel="stylesheet">
        <link href="${resource(dir: 'css/tagit', file: 'tagit.ui-zendesk.css')}" rel="stylesheet">

        <g:javascript src="tagit/tag-it.js" />
        <g:javascript src="ckeditor/ckeditor.js" />

        <script>
            $(function(){
                var sampleTags = ['c', 'ant', 'c++', 'java', 'php', 'c#', 'groovy', 'jquery', 'grails', 'javascript', 'asp', 'ruby', 'python', 'scala', 'haskell', 'perl', 'erlang', 'apl', 'cobol', 'go', 'lua'];

                //-------------------------------
                // Single field
                //-------------------------------
                $('#tagit_singleFieldTags').tagit({
                    availableTags: sampleTags,
                    // This will make Tag-it submit a single form value, as a comma-delimited field.
                    singleField: true,
                    singleFieldNode: $('#tagit_singleFieldTags_value')
                });
            });
        </script>
    </head>
    <body>
        <!-- Security test -->
        <g:if test="${User.isUserAuthenticated() && User.getCurrentUserFromDB().isOwnerOfQuestion(question)}" >
            <div class="page-header">
                <h1>${question.title}<br />
                    <small>Edit your question</small>
                </h1>
            </div>
            <div id="js_contentRequired" style="display: none;" class="alert alert-danger">
                <a class="close">&times;</a>
                Your answer is empty.
            </div>
            <g:form action="editQuestion" id="${question.id}">
                <div class="form-group">
                    <label for="inputTitle">Title</label>
                    <input name="newQuestionTitle" type="text" class="form-control" id="inputTitle" placeholder="Question title ..." value="${question.title}" required>
                </div>
                <div class="form-group">
                    <label for="CKEditor">Content</label>
                    <textarea name="newQuestionContent" id="CKEditor" placeholder="Your question here ..." required><br />
                        ${question.content}
                    </textarea>
                </div>
                <div class="form-group">
                    <label for="tagit_singleFieldTags">Tags</label>
                    <input name="tags" id="tagit_singleFieldTags_value" value="${question.tagsToString()}" hidden="true">
                    <ul id="tagit_singleFieldTags"></ul>
                </div>
                <g:link style="text-decoration: none;" action="showQuestion" params='[question: "${question.id}"]'>
                    <button type="button" class="btn btn-default">Cancel</button>
                </g:link>
                <button type="submit" class="btn btn-primary">Accept</button>
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
</body>
</html>
