package fr.isima.grailsoverflow

class Tag {
    /// Name of the tag
    String name
    /// List of questions that got this tag
    Set questions = []

    String toString() { 
        return name 
    }
    
    static hasMany = [questions: Question, users: User]
    static belongsTo = [Question, User]

    static constraints = {
        name blank: false, unique: true
    }

    static mapping = {
        questions lazy: false
    }
}
