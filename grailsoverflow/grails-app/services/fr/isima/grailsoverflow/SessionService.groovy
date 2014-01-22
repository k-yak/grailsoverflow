package fr.isima.grailsoverflow

import grails.converters.JSON
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsWebRequest
import org.codehaus.groovy.grails.web.util.WebUtils

class SessionService {
    def oauthService

    def authenticate() {
        GrailsWebRequest webRequest = WebUtils.retrieveGrailsWebRequest()
        def session = webRequest.session

        def googleAccessToken = session[oauthService.findSessionKeyForAccessToken('google')]
        //def userInfo = oauthService.getGoogleResource(googleAccessToken, 'https://www.googleapis.com/oauth2/v1/userinfo')
        def userInfo = oauthService.getGoogleResource(googleAccessToken, 'https://www.googleapis.com/plus/v1/people/me')

        def email = JSON.parse(userInfo.body).emails[0].value
        def displayName = JSON.parse(userInfo.body).displayName
        def website = JSON.parse(userInfo.body).urls[0]?.value ?: ""


        User user = User.findByEmail(email);
        if (!user) {
            log.info "DEBUG : New User created : " + email
            user = new User(email: email, displayName: displayName, website: website).save(failOnError: true)
        }
        log.info "DEBUG : User " + email + " authenticated"

        user.lastVisit = new Date()
        user.save(failOnError: true)

        session.user = user
    }

    def logout() {
        GrailsWebRequest webRequest = WebUtils.retrieveGrailsWebRequest()
        def session = webRequest.session

        session.user = null

        session.invalidate()
    }

    def reloadUserSession() {
        try {
            GrailsWebRequest webRequest = WebUtils.retrieveGrailsWebRequest()
            def session = webRequest.session

            if (session.user != null) {
                session.user = User.get(session.user.id);
            }
        } catch (IllegalStateException e) {
            log.error "ERROR: GrailsWebRequest isn't available, any session instanciated ?"
            log.error "ERROR: This error could be due to the bootstrap initialization (no session at this step)"
        }
    }
}
