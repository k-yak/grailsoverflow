package fr.isima.grailsoverflow

class UnacceptedController {
    String subtitle = "Unaccepted questions"
    
    
    /**
     * Unaccepted questions
     */
    def index() {
        def offset = params?.offset ?: 0
        def max = params?.max ?: AppConfig.MAX_QUESTION

        def completeQuestionList = Question.findAllByStatusNotEqual("Accepted", [sort: "dateCreated", order: "desc", max: 100])
        def unacceptedQuestions = Question.findAllByStatusNotEqual("Accepted", [sort: "dateCreated", order: "desc", offset: offset, max: max])
        def tags = Question.tagsForQuestions(completeQuestionList)

        def map =  [questionsToDisplay: unacceptedQuestions, completeQuestionList: completeQuestionList, completePaginationList: completeQuestionList, tags: tags, subtitle: subtitle]
        render(view: "/question/index", model: map)
    }
    
    def questionsForTag() {
        def offset = params?.offset ?: 0
        def max = params?.max ?: AppConfig.MAX_QUESTION

        def unacceptedQuestions = Question.findAllByStatusNotEqual("Accepted", [sort: "dateCreated", order: "desc"])
        def neededTag = Tag.findByName(params.tag)
        def unacceptedForNeededTag = Question.findAllByStatusNotEqualAndIdInList("Accepted", neededTag.questions*.id, [sort: "dateCreated", order: "desc", max: 100])
        def completePaginationList = Question.findAllByStatusNotEqualAndIdInList("Accepted", neededTag.questions*.id, [sort: "dateCreated", order: "desc", offset: offset, max: max])
        def tags = Question.tagsForQuestions(unacceptedQuestions)
        
        def map = [questionsToDisplay: unacceptedForNeededTag, completeQuestionList: unacceptedQuestions, completePaginationList: completePaginationList, neededTag: neededTag, tags: tags, subtitle: subtitle]
        render(view: "/question/index", model: map)
    }
}
