package fr.isima.grailsoverflow

class QuestionController {
    static scaffold = Question
    
    def index() { 
        def questions = Question.list(sort: "dateCreated", order: "desc")
        def tags = Tag.list(sort: "name", order: "asc")
        
        return [questions: questions, tags: tags]
    }
}
