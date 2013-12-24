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
            println "New User created : " + email
            user = new User(email: email, displayName: email).save(failOnError: true)
        } else {
            println "User " + email + " found"
        }
        User.CurrentUser = user
        
        redirect(controller: "question", action: "index")
    }
    
    def logout() {
        session.invalidate()
        User.CurrentUser = null
        
        redirect(controller: "question", action: "index")
    }
}