package fr.isima.grailsoverflow

class Answer extends Message {
    boolean accepted = false

    static hasMany = [comments: Comment]
    static belongsTo = [question: Question, user: User]
    
    static constraints = {
    }
}
