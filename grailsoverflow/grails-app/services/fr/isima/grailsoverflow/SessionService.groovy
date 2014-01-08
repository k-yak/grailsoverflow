package fr.isima.grailsoverflow

import org.codehaus.groovy.grails.web.servlet.mvc.GrailsWebRequest

class SessionService {
    def oauthService

    def authenticate() {
        GrailsWebRequest webRequest = WebUtils.retrieveGrailsWebRequest()
        def session = webRequest.session

        def googleAccessToken = session[oauthService.findSessionKeyForAccessToken('google')]
        def userInfo = oauthService.getGoogleResource(googleAccessToken, 'https://www.googleapis.com/oauth2/v1/userinfo')
        def email = JSON.parse(userInfo.body).email

        User user = User.findByEmail(email)
        if (!user) {
            log.info "DEBUG : New User created : " + email
            user = new User(email: email, displayName: email).save(failOnError: true)
        } else {
            log.info "DEBUG : User " + email + " found"
        }
        session.user = user
    }

    def logout() {
        GrailsWebRequest webRequest = WebUtils.retrieveGrailsWebRequest()
        def session = webRequest.session

        session.user = null
        def targetUri = session.targetUri ?: "/question/index"

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
