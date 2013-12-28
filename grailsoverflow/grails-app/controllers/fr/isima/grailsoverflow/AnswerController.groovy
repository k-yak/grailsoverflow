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
        
    def delete() {
        def answer = Answer.findById(params.answer)
        def question = answer.question
        
        if (User.CurrentUser.isOwnerOfAnswer(answer)) {
            question.removeFromAnswers(answer)
            question.updateStatus()
            question.save(failOnError: true)
        }
        
        redirect(uri: "/question/show/${question.id}")    
    }
}
