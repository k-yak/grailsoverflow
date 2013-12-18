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
            user = new User(email: email, displayName: email).save(failOnError: true)
        }
        User.CurrentUser = user
        
        redirect(controller: "question", action: "index")
    }
}