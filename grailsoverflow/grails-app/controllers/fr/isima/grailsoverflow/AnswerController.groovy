package fr.isima.grailsoverflow

class AnswerController {
    def answerService
    def questionService
    def sessionService

    def accept() {
        def answer = answerService.getAnswerById(params.id)
        def question = answer.question

        answerService.acceptAnswer(params.id)
        questionService.updateStatus(question)
    }

    def edit() {
        def answer = answerService.getAnswerById(params.answer)
        def question = answer.question

        if (session.user == null || (!session.user.isOwnerOfAnswer(answer) && session.user.admin == false)) {
            log.warn "WARNING : Address ${request.getRemoteAddr()} try to edit answer ${params.answer} but do not have rights"
            sessionService.addMessage("danger", "You do not have right to do that!")
            redirect(controller: "question", action: "index")
        } else {
            return [question: question, answer: answer]
        }
    }

    def editAnswer() {
        if (session.user == null) {
            sessionService.addMessage("danger", "You do not have right to do that!")
            redirect(controller: "question", action: "index")
        } else {
            def answer = answerService.editAnswer(params.id, params.newAnswerContent)
            redirect(uri: "/question/show/${answer.question.id}")
        }
    }
        
    def delete() {
        if (session.user == null) {
            sessionService.addMessage("danger", "You do not have right to do that!")
            redirect(controller: "question", action: "index")
            log.warn "WARNING : Address ${request.getRemoteAddr()} try to delete answer ${params.answer} but do not have rights"
        } else {
            def question = answerService.deleteAnswer(params.answer, session.user)
            questionService.updateStatus(question)

            redirect(uri: "/question/show/${question.id}")
        }
    }
}
