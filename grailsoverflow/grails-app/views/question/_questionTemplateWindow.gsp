<div class="box" style="background: #f5f5f5;
-moz-border-radius: 6px;
-webkit-border-radius: 6px;
border-radius: 6px;
width: 250px;
height: 380px;
margin: 0 2% 2% 0;
float: left;
overflow: hidden;
position: relative;">
    <img src="http://www.unheap.com/wp-content/uploads/ladda-bootstrap.png" alt="">
    <div class="inner-content" style="margin:10px;">
        <h4><g:link controller="question" action="showQuestion" params='[question: "${question.id}"]' style="
        color: #606060;
        font-family: Gotham, 'proxima-nova', 'Helvetica Neue', Helvetica, Arial, sans-serif;
        font-size: 0.9em;
        letter-spacing: 0.1em;
        text-transform: uppercase;
        ">${question.title}
        </g:link></h4>
        <p style="font-family: 'proxima-nova', 'Lucida Grande', 'Helvetica Neue', Helvetica, Arial, sans-serif;
            color: #838383;
            margin-top: 6px;
            font-size: 13px;
            line-height: 20px;
            height: 60px;">Easily add a UI widget with a feedback form which slides from the side of the screen.</p>
    </div>
    <div class="author-twitter-handle" style="padding: 0 0 0 17px;right: 14px;
    bottom: 50px;position: absolute;">
        <g:link class="userLink" controller="user" action="show" style="color: #8bb8bb;font-size: 0.9em; float:right;" params='[id: "${question.user.id}"]'>${question.user.displayName}</g:link>
    </div>
    <div class="overlay"  onmouseover="document.getElementById('toggle-${question.id}').style.display = 'block'"  onmouseout="document.getElementById('toggle-${question.id}').style.display = 'none'"  style="text-align: center;
    -moz-border-radius: 6px 6px 0 0;
    -webkit-border-radius: 6px 6px 0 0;
    border-radius: 6px 6px 0 0;
    width: 250px;
    height: 141px;
    position: absolute;
    top: 0;
    left: 0;">
        <div id="toggle-${question.id}" class="plugin-stats" style="
        display: none;
        text-align: left;
        background: #3f12b2;
        color: #dedede;
        border-top: 1px solid #f2f2f2;
        width: 250px;
        height: 33px;
        position: absolute;
        left: 0;
        bottom: 0px;
        z-index: 1;
        padding-left: 5px;
        padding-right: 5px;">
            <div class="data" style="list-style-type:none;">
                <a class="wpfp-link not-logged-in" style="
                color: #8c8c8c;
                font-weight: bold;
                text-align: center;
                background: #e2e2e2;
                -moz-border-radius: 3px;
                -webkit-border-radius: 3px;
                border-radius: 3px;
                width: 25px;
                padding: 1px;
                height: 20px;
                line-height: 20px;
                margin: 5px 12px 0 0;
                display: inline-block;
                position: relative;
                -moz-transition: .3s ease-in-out;
                -webkit-transition: .3s ease-in-out;
                transition: .3s ease-in-out;">
                <g:if test="${question.status == 'grow.status.unanswered'}">
                    <div class="glyphicon glyphicon-question-sign"> </div>
                </g:if>
                <g:if test="${question.status == 'grow.status.answered'}">
                    <div class="glyphicon glyphicon-registration-mark"> </div>
                </g:if>
                <g:if test="${question.status == 'grow.status.accepted'}">
                    <div class="glyphicon glyphicon-chevron-down"> </div>
                </g:if>
                </a>
                <div style="color: #dedede;float: right;margin-top: 4px;font-size: 0.7em;">${question.dateCreated.format('dd MMM yyyy')}</div>
                <div class="glyphicon glyphicon-thumbs-up" style="padding: 7px;"><span style="margin: 3px;">${question.vote.value}</span></div>
                <div class="glyphicon glyphicon glyphicon-bullhorn" style="padding: 7px;"><span style="margin: 3px;">${question.answers.size()}</span></div>
            </div>
        </div>
    </div>
    <div>
    <ul class="list-group">
        <li class="list-group-item" style="position: absolute;
        bottom: 0;
        width: 100%;"><b><g:message code="grow.question.showQuestion.tags" style="bottom: 0;"/> :</b>
            <g:each in="${question.tags}" var="tag">
                <g:link style="text-decoration: none" action="questionsForTag" params='[tag: "${tag.name}"]'>
                    <span class="label label-primary" >${tag.name}</span>
                </g:link>
            </g:each>
        </li>
    </ul>
    </div>
</div>