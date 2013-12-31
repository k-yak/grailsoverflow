package fr.isima.grailsoverflow

class Answer extends Message {
    boolean accepted = false

    def accept() {
        accepted = true
        user.score += AppConfig.ACCEPT_ANSWER_SCORE
        question.updateStatus()

        question.save(failOnError: true)
        save(failOnError: true)
    }

    def reject() {
        if (accepted) {
            user.score -= AppConfig.ACCEPT_ANSWER_SCORE
            question.updateStatus()
            accepted = false
        }

        question.updateStatus()

        question.save(failOnError: true)
        save(failOnError: true)
    }

    static hasMany = [comments: Comment]
    static belongsTo = [question: Question, user: User]
    
    static constraints = {
    }
}
