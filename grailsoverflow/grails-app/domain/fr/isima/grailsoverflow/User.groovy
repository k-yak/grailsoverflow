package fr.isima.grailsoverflow

import groovy.transform.EqualsAndHashCode
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsWebRequest
import org.codehaus.groovy.grails.web.util.WebUtils

@EqualsAndHashCode
class User {
    String displayName
    String email
    int score = 0
    
    def isOwnerOfQuestion(def question) {
        question.user.email == email
    }
    
    def isOwnerOfAnswer(def answer) {
        answer.user.email == email
    }

    static transients = ['userService']
    static UserService userService
    static hasMany = [questions: Question, favoriteTags: Tag]
    
    static boolean isUserAuthenticated() {
        GrailsWebRequest webRequest = WebUtils.retrieveGrailsWebRequest()
        def session = webRequest.session

        session.user != null
        //userService.getSessionUser() != null
    }

    static User getCurrentUserFromDB() {
        GrailsWebRequest webRequest = WebUtils.retrieveGrailsWebRequest()
        def session = webRequest.session

        User.get(session.user.id)
        //User.get(userService.getSessionUser().id)
    }
    
    static constraints = {
        email blank: false, unique: true
    }
    
    static mapping = {
        questions cascade:"all,delete-orphan"
    }
}
