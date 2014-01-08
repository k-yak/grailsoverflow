package fr.isima.grailsoverflow

class Answer extends Message {
    boolean accepted = false
    def answerService

    def beforeDelete() {
        answerService.clearUserScoreForAnswer(this)
    }

    static hasMany = [comments: Comment]
    static belongsTo = [question: Question, user: User]
    
    static constraints = {
    }
}
