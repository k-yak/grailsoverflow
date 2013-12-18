package fr.isima.grailsoverflow

class QuestionController {
    static scaffold = Question
    
    def index() { 
        def questions = Question.list(sort: "dateCreated", order: "desc")
        
        return [questions: questions]
    }
}
