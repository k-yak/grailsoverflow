package grailsoverflow

import fr.isima.grailsoverflow.Answer
import fr.isima.grailsoverflow.AppConfig
import fr.isima.grailsoverflow.Question
import fr.isima.grailsoverflow.User

class AnswerService {
    static transactional = true

    def questionService
    def sessionService

    def answerToQuestion(def questionId, def content, def currentUser) {
        def user = User.get(currentUser.id)
        def question = questionService.getQuestion(questionId)

        Answer answer = new Answer(
                content: content - "<p>&nbsp;</p>",
                dateCreated: new Date(),
                user: user,
                question: question
        )

        answer.user.score += AppConfig.ANSWER_SCORE

        user.save(failOnError: true)
        answer.save(failOnError: true)

        questionService.addAnswer(question, answer)
        sessionService.reloadUserSession()
    }

    def acceptAnswer(def answerId) {
        Answer answer = Answer.get(answerId)
        def oldState = answer.accepted

        answer.question.answers.each() {
            if (it.accepted) {
                it.user.score -= AppConfig.ACCEPT_ANSWER_SCORE
                it.accepted = false
            }
            questionService.updateStatus(it.question)

            it.save(failOnError: true)
        }

        if (oldState == false) {
            answer.accepted = true
            answer.user.score += AppConfig.ACCEPT_ANSWER_SCORE
            questionService.updateStatus(answer.question)

            answer.save(failOnError: true)
        }
        sessionService.reloadUserSession()
    }

    def clearUserScoreForAnswer(def answer) {
        // Remove user score for answer
        answer.user.score -= AppConfig.ANSWER_SCORE

        // Remove user score for votes
        answer.user.score -= answer.vote.value * AppConfig.VOTE_SCORE

        // Remove user score for accepted answer
        if (answer.accepted) {
            answer.user.score -= AppConfig.ACCEPT_ANSWER_SCORE
        }

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

        if (currentUser.isOwnerOfAnswer(answer)) {
            question.removeFromAnswers(answer)
            questionService.updateStatus(question)
            question.save(failOnError: true)
        }

        return question
    }
}
