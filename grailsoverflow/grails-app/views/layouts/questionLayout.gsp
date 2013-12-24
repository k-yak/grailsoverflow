<!DOCTYPE html>

<%@page import="fr.isima.grailsoverflow.User" %> 

<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> 
<html lang="en" class="no-js"><!--<![endif]-->
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title><g:layoutTitle default="Grails"/></title>

    <link href="${resource(dir: 'bootstrap/css', file: 'bootstrap.min.css')}" rel="stylesheet">

    <script type="${resource(dir: 'bootstrap/js', file: 'bootstrap.min.js')}"></script>
    <script type="${resource(dir: 'js', file: 'jquery.min.js')}"></script>

  <g:layoutHead/>
  <r:layoutResources />
</head>
<body>
  <div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
    <div class="container">
      <div class="navbar-header">
        <g:link class="navbar-brand" controller="question" action="index">
          GrailsOverflow
        </g:link>

        <ul class="nav navbar-nav">
          <li><g:link controller="question" action="index">Lastest</g:link></li>
          <li><g:link controller="unaccepted" action="index">Unaccepted</g:link></li>
        </ul>
      </div>
      <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav">
        </ul>
        <ul class="nav navbar-nav navbar-right">

          <g:if test="${User.isUserAuthenticated() == true}">
            <li>
              <a>Connected (${User.CurrentUser.email})</a>
            </li>
            <li>
            <g:link controller="authenticate" action="logout">
              logout
            </g:link>
            </li>
          </g:if>
          <g:else>
            <li>
            <oauth:connect provider="google" id="google-connect-link">Google Connection</oauth:connect>
            </li>
          </g:else>


        </ul>
      </div><!-- /.nav-collapse -->
    </div><!-- /.container -->
  </div><!-- /.navbar -->

  <div class="container">
    <g:layoutBody/>

    <hr>

    <footer>
      <p>© GrailsOverflow 2013</p>
    </footer>
  </div>
<g:javascript library="application"/>
<r:layoutResources />
</body>
</html>
