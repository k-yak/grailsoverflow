package fr.isima.grailsoverflow

class AnswerController {
    def answerService
    def questionService

    def accept() {
        def answer = answerService.getAnswerById(params.id)
        def question = answer.question

        answerService.acceptAnswer(params.id)
        questionService.updateStatus(question)
    }

    def edit() {
        def answer = answerService.getAnswerById(params.answer)
        def question = answer.question

        return [question: question, answer: answer]
    }

    def editAnswer() {
        if (session.user == null) {
            redirect(controller: "question", action: "index")
        } else {
            def answer = answerService.editAnswer(params.id, params.newAnswerContent)

            redirect(uri: "/question/show/${answer.question.id}")
        }
    }
        
    def delete() {
        if (session.user == null) {
            redirect(controller: "question", action: "index")
        } else {
            def question = answerService.deleteAnswer(params.answer, session.user)
            questionService.updateStatus(question)

            redirect(uri: "/question/show/${question.id}")
        }
    }
}
