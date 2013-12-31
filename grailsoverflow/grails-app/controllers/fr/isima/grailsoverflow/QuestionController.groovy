package fr.isima.grailsoverflow

class QuestionController {
    String subtitle = "Latest questions"
    
    /**
     * Latest questions
     */
    def index() { 
        def latestQuestions = Question.list(sort: "dateCreated", order: "desc", max: 100)
        def tags = Question.tagsForQuestions(latestQuestions)
        
        return [questionsToDisplay: latestQuestions, completeQuestionList: latestQuestions, tags: tags, subtitle: subtitle]
    }
    
    def questionsForTag() { 
        def latestQuestions = Question.list(sort: "dateCreated", order: "desc", max: 100)
        def neededTag = Tag.findByName(params.tag)
        def latestForNeededTag = Question.findAllByIdInList(neededTag.questions*.id, [sort: "dateCreated", order: "desc", max: 100])
        def tags = Question.tagsForQuestions(latestQuestions)
        
        def map = [questionsToDisplay: latestForNeededTag, completeQuestionList: latestQuestions, neededTag: neededTag, tags: tags, subtitle: subtitle]
        render(view: "index", model: map)
    }
    
    def showQuestion() {
        def question = Question.findById(params.question)
        
        return [question: question]
    }
    
    def edit() {
        def question = Question.findById(params.question)
        
        return [question: question]
    }
    
    def editQuestion() {
        def question = Question.findById(params.id)
        
        question.content = params.newQuestionContent  - "<p>&nbsp;</p>"
        question.save(failOnError: true)
        
        redirect(uri: "/question/show/${question.id}")
    }

    def delete() {
        def question = Question.findById(params.question)
        
        if (User.isUserAuthenticated() && User.getCurrentUserFromDB().isOwnerOfQuestion(question)) {
            User user = question.user

            question.clearUserScore()
            user.removeFromQuestions(question)

            user.save(failOnError: true)
        }
        
        redirect(controller: "question", action: "index")
    }
    
    def answer() {
        def question = Question.findById(params.id)

        Answer answer = new Answer(
            content: params.answerContent - "<p>&nbsp;</p>",
            dateCreated: new Date(),
            user: User.getCurrentUserFromDB(),
            question: question
        )

        answer.save(failOnError: true)
        question.answer(answer)
        
        redirect(uri: "/question/show/${params.id}")
    }
}
