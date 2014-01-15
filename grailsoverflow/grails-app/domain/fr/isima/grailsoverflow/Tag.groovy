package fr.isima.grailsoverflow

class Tag {
    String name

    String toString() { 
        return name 
    }
    
    static hasMany = [questions: Question, users: User]
    static belongsTo = [Question, User]

    static constraints = {
        name blank: false, unique: true
    }
}
