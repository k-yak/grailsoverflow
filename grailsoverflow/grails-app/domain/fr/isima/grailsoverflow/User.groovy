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
        session.user
    }

    static transients = ['userService']
    static UserService userService
    static hasMany = [questions: Question, favoriteTags: Tag]
    
    static boolean isUserAuthenticated() {
        userService.getSessionUser() != null
    }

    static User getCurrentUserFromDB() {
        User.get(userService.getSessionUser().id)
    }
    
    static constraints = {
        email blank: false, unique: true
    }
    
    static mapping = {
        questions cascade:"all,delete-orphan"
    }
}
