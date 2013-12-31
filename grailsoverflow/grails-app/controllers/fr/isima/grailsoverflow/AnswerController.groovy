package fr.isima.grailsoverflow

class AnswerController {
    def accept() {
        Answer answer = Answer.get(params.id)
        def question = answer.question
        def oldState = answer.accepted
		
        question.answers.each() {
            it.accepted = false
        }
		
        if (oldState == false)
        answer.accepted = true
                    
        question.updateStatus()
		
        answer.save(failOnError: true)
        question.save(failOnError: true)
    }

    def edit() {
        def answer = Answer.findById(params.answer)
        def question = answer.question

        return [question: question, answer: answer]
    }

    def editAnswer() {
        def answer = Answer.findById(params.id)

        answer.content = params.newAnswerContent  - "<p>&nbsp;</p>"
        answer.save(failOnError: true)

        redirect(uri: "/question/show/${answer.question.id}")
    }

        
    def delete() {
        def answer = Answer.findById(params.answer)
        def question = answer.question
        
        if (User.CurrentUser.isOwnerOfAnswer(answer)) {
            answer.user.score -= AppConfig.ANSWER_SCORE
            answer.user.save(failOnError: true)

            question.removeFromAnswers(answer)
            question.updateStatus()
            question.save(failOnError: true)
        }
        
        redirect(uri: "/question/show/${question.id}")    
    }
}
