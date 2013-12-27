package fr.isima.grailsoverflow

class AnswerController {
	def accept() {
		Answer answer = Answer.get(params.id)
		def question = answer.question
		
		question.answers.each() {
			it.accepted = false
		}
		
		answer.accepted = true
		question.updateStatus()
		
		answer.save(failOnError: true)
		question.save(failOnError: true)
	}
}
