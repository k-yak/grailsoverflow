package fr.isima.grailsoverflow

import groovy.transform.EqualsAndHashCode
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsWebRequest
import org.codehaus.groovy.grails.web.util.WebUtils

@EqualsAndHashCode
class User {
    String displayName
    String email
    int score = 0
    Set favoriteTags = []

    def isOwnerOfQuestion(def question) {
        question.user.email == email
    }
    
    def isOwnerOfAnswer(def answer) {
        answer.user.email == email
    }

    static hasMany = [questions: Question, favoriteTags: Tag]

    static constraints = {
        email blank: false, unique: true
    }
    
    static mapping = {
        questions cascade:"all,delete-orphan"
        favoriteTags lazy: false
    }
}
