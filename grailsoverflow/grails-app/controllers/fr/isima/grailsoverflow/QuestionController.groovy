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
    
    def voteUp() {
        def question = Question.get(params.id)
        question.vote++
        question.save(failOnError: true)
        
        render(text:"${question.vote}", contentType:'text/html')
    }
    
    def voteDown() {
        def question = Question.get(params.id)
        question.vote--
        question.save(failOnError: true)
        
        render(text:"${question.vote}", contentType:'text/html')
    }
}
