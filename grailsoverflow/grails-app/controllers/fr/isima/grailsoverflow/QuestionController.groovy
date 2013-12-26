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
        def latesteForNeededTag = Question.findAllByIdInList(neededTag.questions*.id, [sort: "dateCreated", order: "desc", max: 100])
        def tags = Question.tagsForQuestions(latestQuestions)
        
        def map = [questionsToDisplay: latesteForNeededTag, completeQuestionList: latestQuestions, neededTag: neededTag, tags: tags, subtitle: subtitle]
        render(view: "index", model: map)
    }
    
    def showQuestion() {
        def question = Question.findById(params.question)
        
        return [question: question]
    }
    
    def answer() {
        def question = Question.findById(params.id)
        Answer answer = new Answer(
            content: params.answerContent,
            dateCreated: new Date(),
            user: User.CurrentUser,
            question: question
        )
        answer.save(failOnError: true)
        question.answer(answer)
        question.save(failOnError: true)
        
        redirect(uri: "/question/show/${params.id}")
    }
}
