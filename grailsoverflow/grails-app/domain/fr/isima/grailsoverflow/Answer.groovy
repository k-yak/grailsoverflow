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

    def beforeDelete() {
        // Remove user score for answer
        user.score -= AppConfig.ANSWER_SCORE

        // Remove user score for votes
        user.score -= vote.value * AppConfig.VOTE_SCORE

        // Remove user score for accepted answer
        if (accepted) {
            user.score -= AppConfig.ACCEPT_ANSWER_SCORE
        }

        user.save(failOnError: true)
    }

    static hasMany = [comments: Comment]
    static belongsTo = [question: Question, user: User]
    
    static constraints = {
    }
}
