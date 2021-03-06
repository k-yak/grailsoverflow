package fr.isima.grailsoverflow

class AnswerService {
    static transactional = true

    def sessionService
    def medalService
    def historyService

    def createAnswer(def content, def user, def question) {
        new Answer(
            content: content - "<p>&nbsp;</p>",
            dateCreated: new Date(),
            user: user,
            question: question
        ).save(failOnError: true)
    }

    def acceptAnswer(def answerId) {
        Answer answer = Answer.get(answerId)
        def oldState = answer.accepted

        // Remove old accepted
        answer.question.answers.each() {
            if (it.accepted) {
                it.user.score -= AppConfig.ACCEPT_ANSWER_SCORE
                it.accepted = false
                historyService.removeAcceptAnswer(it.user, answer)
            }

            it.save(failOnError: true)
        }

        // Accept new answer
        if (oldState == false) {
            answer.accepted = true
            answer.user.score += AppConfig.ACCEPT_ANSWER_SCORE
            historyService.addAcceptAnswer(answer.user, answer)

            answer.save(failOnError: true)
        }

        // Manage score medals
        medalService.testScoreMedals(answer.user)

        answer.user.save(failOnError: true)
        sessionService.reloadUserSession()
    }

    def beforeDeleteActions(def answer) {
        // Remove user score for answer
        answer.user.score -= AppConfig.ANSWER_SCORE

        // Remove user score for votes
        answer.user.score -= answer.vote.value * AppConfig.VOTE_SCORE

        // Remove user score for accepted answer
        if (answer.accepted) {
            answer.user.score -= AppConfig.ACCEPT_ANSWER_SCORE
        }

        medalService.testScoreMedals(answer.user);
        answer.user.save(failOnError: true)
        sessionService.reloadUserSession()
    }

    def getAnswerById(def answerId) {
        Answer.findById(answerId)
    }

    def editAnswer(def answerId, def content) {
        def answer = getAnswerById(answerId)

        answer.content = content  - "<p>&nbsp;</p>"
        answer.save(failOnError: true)

        return answer
    }

    def deleteAnswer(def answerId, def currentUser) {
        def answer = getAnswerById(answerId)
        def question = answer.question

        if (currentUser.isOwnerOfAnswer(answer) || currentUser.admin == true) {
            log.info "DEBUG : Answer ${answerId} deleted by user ${currentUser.id} (${currentUser.email})"
            beforeDeleteActions(answer)
            question.removeFromAnswers(answer)
        }

        return question
    }
}
