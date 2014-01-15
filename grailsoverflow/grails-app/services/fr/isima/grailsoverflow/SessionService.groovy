package fr.isima.grailsoverflow

import grails.converters.JSON
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsWebRequest
import org.codehaus.groovy.grails.web.util.WebUtils

class SessionService {
    def oauthService
    def userService

    def authenticate() {
        GrailsWebRequest webRequest = WebUtils.retrieveGrailsWebRequest()
        def session = webRequest.session

        def googleAccessToken = session[oauthService.findSessionKeyForAccessToken('google')]
        def userInfo = oauthService.getGoogleResource(googleAccessToken, 'https://www.googleapis.com/oauth2/v1/userinfo')
        def email = JSON.parse(userInfo.body).email

        User user = userService.getUserByEmail(email);
        if (!user) {
            log.info "DEBUG : New User created : " + email
            user = userService.createUser(email, email)
        } else {
            log.info "DEBUG : User " + email + " found"
        }
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
        }
    }
}
