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
}
