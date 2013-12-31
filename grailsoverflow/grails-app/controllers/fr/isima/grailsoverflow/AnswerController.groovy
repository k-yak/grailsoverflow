package fr.isima.grailsoverflow

class AnswerController {
    def accept() {
        Answer answer = Answer.get(params.id)
        def oldState = answer.accepted
		
        answer.question.answers.each() {
            it.reject()
        }
		
        if (oldState == false) {
            answer.accept()
        }
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
        
        if (User.getCurrentUserFromDB().isOwnerOfAnswer(answer)) {
            question.removeFromAnswers(answer)
            question.updateStatus()
            question.save(failOnError: true)
        }
        
        redirect(uri: "/question/show/${question.id}")    
    }
}
