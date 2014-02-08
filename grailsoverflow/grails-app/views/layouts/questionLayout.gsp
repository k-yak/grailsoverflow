<!DOCTYPE html>

<%@page import="fr.isima.grailsoverflow.User" %> 

<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> 
<html lang="en" class="no-js" xmlns="http://www.w3.org/1999/html"><!--<![endif]-->
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title><g:layoutTitle default="Grails"/></title>

        <link rel="icon" type="image/png" href="${resource(dir: 'images', file: 'favicon.png')}" />
        <link type="text/css" href="${resource(dir: 'css/bootstrap', file: 'bootstrap.min.css')}" rel="stylesheet">
        <g:javascript src="jquery.min.js"/>
        <g:javascript src="jquery-ui.min.js"/>
        <g:javascript src="bootstrap/bootstrap.min.js" />
        <g:javascript src="fadeout_message.js" />
        
        <g:layoutHead/>
    <r:layoutResources />
</head>
<body>
    <div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
        <div class="container">
            <!-- Navigation bar -->
            <div class="navbar-header">
                <g:link class="navbar-brand" controller="question" action="index" style="font-family:'Airstream', Verdana, Geneva, Arial, Helvetica, sans-serif;">
                    <div class="glyphicon glyphicon-leaf"></div>GROW
                </g:link>

                <!-- Left side  -->
                <ul class="nav navbar-nav">
                    <li><g:link controller="question" action="index"  class="glyphicon glyphicon-tasks" title="${ message( code:'grow.questionlayout.latest' ) }" ><%--<g:message code="grow.questionlayout.latest" /> --%></g:link></li>
                    <li><g:link controller="unaccepted" action="index" class="glyphicon glyphicon-question-sign" title="${ message( code:'grow.questionlayout.unaccepted' ) }" ><%--<g:message code="grow.questionlayout.unaccepted" />--%></g:link></li>
                    <g:if test="${session.user != null}">
                        <li>
                            <g:link controller="question" action="add" class="glyphicon glyphicon-plus-sign" title="${ message( code:'grow.questionlayout.new.question' ) }">
                            <%--<g:message code="grow.questionlayout.new.question" />--%>
                            </g:link>
                        </li>
                    </g:if>
                </ul>
                </div>

                <!-- Right side -->
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <g:form style="width: 275px;" class="input-group navbar-form" url='[controller: "question", action: "search"]' method="get">
                                <input type="text" name="q" size="50" class="form-control" placeholder="Search" value="${params.q}" required>
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span></button>
                                </span>
                            </g:form>
                        </li>
                        <g:if test="${session.user != null}">
                            <li>
                                <g:link controller="user" action="show" params='[id: "${session.user.id}"]'>
                                    <img class="smallGravatar" src="https://www.gravatar.com/avatar/${session.user.email.encodeAsMD5()}?s=24&r=pg" alt="" />
                                    ${session.user.displayName} (${session.user.score} pts)
                                </g:link>
                            </li>
                            <li>
                                <g:set var="targetUri" value="${request.forwardURI - request.contextPath}" scope="session" />
                                <g:link controller="authenticate" action="logout" class="glyphicon glyphicon-off" title="${ message( code:'grow.questionlayout.logout' ) }">
                                <%--<g:message code="grow.questionlayout.logout" />--%>
                            </g:link>
                        </li>
                    </g:if>
                    <g:else>
                        <li>
                            <g:set var="targetUri" value="${request.forwardURI - request.contextPath}" scope="session" />
                            <oauth:connect provider="google" id="google-connect-link"><g:message code="grow.questionlayout.google.connection" /></oauth:connect>
                        </li>
                    </g:else>
                 </ul>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <own:displayMessage/>
    <g:layoutBody/>

    <hr>
    <footer>
        <p>© GrailsOverflow 2014</p>
    </footer>
</div>
<g:javascript library="application"/>
<r:layoutResources />
</body>
</html>
