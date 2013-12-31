package fr.isima.grailsoverflow

import groovy.transform.EqualsAndHashCode

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
    
    static hasMany = [questions: Question]
    
    // Static block
    static scope = "session"
    static User CurrentUser
    
    static {
        CurrentUser = null
    }
    
    static boolean isUserAuthenticated() {
        CurrentUser != null
    }

    static User getCurrentUserFromDB() {
        User.get(CurrentUser.id)
    }
    
    static constraints = {
        email blank: false, unique: true
    }
    
    static mapping = {
        questions cascade:"all,delete-orphan"
    }
}
