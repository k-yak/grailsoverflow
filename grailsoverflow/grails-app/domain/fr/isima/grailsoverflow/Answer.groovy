package fr.isima.grailsoverflow

class Answer extends Message {
    /// Question accepted by user
    boolean accepted = false

    static belongsTo = [question: Question, user: User]
    
    static constraints = {
    }
}
