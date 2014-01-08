package fr.isima.grailsoverflow

import grails.converters.*;

class AuthenticateController {

   def oauthService
 
    def success() {
        def googleAccessToken = session[oauthService.findSessionKeyForAccessToken('google')]
        def userInfo = oauthService.getGoogleResource(googleAccessToken, 'https://www.googleapis.com/oauth2/v1/userinfo') 
        def email = JSON.parse(userInfo.body).email
        
        User user = User.findByEmail(email) 
        if (!user) {
            println "DEBUG : New User created : " + email
            user = new User(email: email, displayName: email).save(failOnError: true)
        } else {
            println "DEBUG : User " + email + " found"
        }
        session.user = user

        def targetUri = session.targetUri ?: "/question/index"
        redirect(uri: targetUri)
    }

    def failure() {
        render(view: "/error", model: [errorContent: "Authentication error"])
    }
    
    def logout() {
        session.user = null
        def targetUri = session.targetUri ?: "/question/index"
        
        session.invalidate()
        redirect(uri: targetUri)
    }
}