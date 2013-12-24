package fr.isima.grailsoverflow

class UnacceptedController {
    String subtitle = "Unaccepted questions"
    
    
    /**
     * Unaccepted questions
     */
    def index() { 
       def unacceptedQuestions = Question.findAllByStatusNotEqual("Accepted", [sort: "dateCreated", order: "desc", max: 100])
       def tags = Question.tagsForQuestions(unacceptedQuestions)

        def map =  [questionsToDisplay: unacceptedQuestions, completeQuestionList: unacceptedQuestions, tags: tags, subtitle: subtitle]
        render(view: "/question/index", model: map)
    }
    
    def questionsForTag() {
        def unacceptedQuestions = Question.findAllByStatusNotEqual("Accepted", [sort: "dateCreated", order: "desc"])
        def neededTag = Tag.findByName(params.tag)
        def unacceptedForNeededTag = Question.findAllByStatusNotEqualAndIdInList("Accepted", neededTag.questions*.id, [sort: "dateCreated", order: "desc", max: 100])
        def tags = Question.tagsForQuestions(unacceptedQuestions)
        
        def map = [questionsToDisplay: unacceptedForNeededTag, completeQuestionList: unacceptedQuestions, neededTag: neededTag, tags: tags, subtitle: subtitle]
        render(view: "/question/index", model: map)
    }
}
