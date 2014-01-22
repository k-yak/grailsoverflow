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
        def userInfo = oauthService.getGoogleResource(googleAccessToken, 'https://www.googleapis.com/oauth2/v1/userinfo')
        def email = JSON.parse(userInfo.body).email

        User user = User.findByEmail(email);
        if (!user) {
            log.info "DEBUG : New User created : " + email
            user = new User(email: email, displayName: email).save(failOnError: true)
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

    def     addMessage(def type, def message) {
        try {
            GrailsWebRequest webRequest = WebUtils.retrieveGrailsWebRequest()
            def session = webRequest.session

            if (session != null) {
                session.message = message;
                session.type = type;
            }
        } catch (IllegalStateException e) {
            log.error "ERROR: GrailsWebRequest isn't available, any session instanciated ?"
            log.error "ERROR: This error could be due to the bootstrap initialization (no session at this step)"
        }
    }

    def getMessage()
    {
        try {
            GrailsWebRequest webRequest = WebUtils.retrieveGrailsWebRequest()
            def session = webRequest.session

            if (session != null) {
                return session?.message
            }
        } catch (IllegalStateException e) {
            log.error "ERROR: GrailsWebRequest isn't available, any session instanciated ?"
            log.error "ERROR: This error could be due to the bootstrap initialization (no session at this step)"
        }
    }

    def getType()
    {
        try {
            GrailsWebRequest webRequest = WebUtils.retrieveGrailsWebRequest()
            def session = webRequest.session

            if (session != null) {
                return session?.type
            }
        } catch (IllegalStateException e) {
            log.error "ERROR: GrailsWebRequest isn't available, any session instanciated ?"
            log.error "ERROR: This error could be due to the bootstrap initialization (no session at this step)"
        }
    }

    def clearMessage()
    {
        try {
            GrailsWebRequest webRequest = WebUtils.retrieveGrailsWebRequest()
            def session = webRequest.session

            if (session != null) {
                session.type = null
                session.message = null
            }
        } catch (IllegalStateException e) {
            log.error "ERROR: GrailsWebRequest isn't available, any session instanciated ?"
            log.error "ERROR: This error could be due to the bootstrap initialization (no session at this step)"
        }
    }
}
