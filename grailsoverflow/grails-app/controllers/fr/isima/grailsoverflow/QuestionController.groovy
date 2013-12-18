package fr.isima.grailsoverflow

class QuestionController {
    static scaffold = Question
    
    def index() { 
        def questions = Question.list(sort: "dateCreated", order: "desc")
        def tags = Tag.list(sort: "name", order: "asc")
        
        return [questions: questions, tags: tags]
    }
    
    def questionsForTags() {
        def neededTag = Tag.findByName(params.name)
        def questions = neededTag.questions
        def tags = Tag.list(sort: "name", order: "asc")
        
        def map = [questions: questions, neededTag: neededTag, tags: tags]
        render(view: "index", model: map)
    }
    
    def showQuestion() {
        def question = Question.findByTitle(params.title)
        
        return [question: question]
    }
}
