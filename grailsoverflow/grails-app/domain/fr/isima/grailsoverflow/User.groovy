package fr.isima.grailsoverflow

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class User {
    String displayName
    String email
    
    def isOwnerOfQuestion(def question) {
        // We must do this due to static User
        User user = User.get(id)
        
        return user?.questions*.id.contains(question.id) ?: false
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
    
    static constraints = {
        email blank: false, unique: true
    }
}
