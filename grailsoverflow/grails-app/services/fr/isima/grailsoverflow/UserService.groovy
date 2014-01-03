package fr.isima.grailsoverflow

import org.codehaus.groovy.grails.web.servlet.mvc.GrailsWebRequest
import org.codehaus.groovy.grails.web.util.WebUtils

class UserService {
    def getSessionUser() {
        GrailsWebRequest webRequest = WebUtils.retrieveGrailsWebRequest()
        def session = webRequest.session

        session.user
    }
}
