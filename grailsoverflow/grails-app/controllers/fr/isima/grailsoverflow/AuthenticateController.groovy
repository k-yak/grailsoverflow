package fr.isima.grailsoverflow

import org.scribe.model.Token
import org.scribe.oauth.OAuthService

class AuthenticateController {

   OAuthService oauthService 
 
    def success() { 
        
        Token googleAccessToken = session[oauthService.findSessionKeyForAccessToken('google')] 
        def userInfo = oauthService.getGoogleResource(googleAccessToken, 'https://www.googleapis.com/oauth2/v1/userinfo') 
        def mail = JSON.parse(userInfo.body) 
        render "Authenticated as $mail" 
    }
    
    def index() { 
        render "Auth index"
    }
}