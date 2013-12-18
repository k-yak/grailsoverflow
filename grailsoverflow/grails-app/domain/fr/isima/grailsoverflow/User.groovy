package fr.isima.grailsoverflow

class User {
    String username
    String displayName
    String email
    
    static constraints = {
        username blank: false, unique: true
    }
}
