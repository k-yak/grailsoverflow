package fr.isima.grailsoverflow

class User {
    String displayName
    String email
    
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
