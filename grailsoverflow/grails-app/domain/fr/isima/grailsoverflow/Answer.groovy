package fr.isima.grailsoverflow

class Answer extends Message {

    static hasMany = [comments: Comment]
    static belongsTo = [question: Question]
    
    static constraints = {
    }
}
