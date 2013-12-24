<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="questionLayout"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>GrailsOverflow - ${question.title}</title>

    <link href="${resource(dir: 'css', file: 'question.css')}" rel="stylesheet">
  </head>

  <body>
    <div class="alert alert-warning">
      <strong>+100</strong> to anyone that answer this question <a href="" class="alert-link">Try your luck!</a>
    </div>

    <div class="panel panel-default">
      <div class="panel-heading">
        <h2>${question.title}</h2>
      </div>
      <div class="panel-body">
        <blockquote>
          <p><own:textToParagraph>${question.content}</own:textToParagraph></p>
          <small>Answered by <cite>${question.user.displayName}</cite></small>
        </blockquote>
      </div>
      <ul class="list-group">
        <li class="list-group-item"><b>Status :</b> ${question.status}</li>
        <li class="list-group-item"><b>Tags :</b>
        <g:each in="${question.tags}" var="tag">
          <g:link class="${styleClass}" style="text-decoration: none" action="questionsForTag" params='[tag: "${tag.name}"]'>
            <span class="label label-primary" >${tag.name}</span>
          </g:link>
        </g:each>
        </li>
      </ul>
    </div>
  </body>
</html>

