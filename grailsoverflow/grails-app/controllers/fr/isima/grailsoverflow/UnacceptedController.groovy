package fr.isima.grailsoverflow

class UnacceptedController {
    def questionService
    def tagService

    String subtitle = "Unaccepted questions"
    
    /**
     * Unaccepted questions
     */
    def index() {
        def offset = params?.offset ?: 0
        def max = params?.max ?: AppConfig.MAX_QUESTION

        def completeQuestionList = questionService.getUnacceptedQuestions(0, 100)
        def unacceptedQuestions = questionService.getUnacceptedQuestions(offset, max)
        def tags = questionService.tagsForQuestions(completeQuestionList)

        def map =  [questionsToDisplay: unacceptedQuestions, completeQuestionList: completeQuestionList, completePaginationList: completeQuestionList, tags: tags, subtitle: subtitle]
        render(view: "/question/index", model: map)
    }
    
    def questionsForTag() {
        def offset = params?.offset ?: 0
        def max = params?.max ?: AppConfig.MAX_QUESTION

        def unacceptedQuestions = questionService.getUnacceptedQuestions(0, 100)
        def neededTag = tagService.getTagByName(params.tag)
        def unacceptedForNeededTag = questionService.getUnacceptedQuestionsInList(neededTag.questions*.id, 0, 100)
        def completePaginationList = questionService.getUnacceptedQuestionsInList(neededTag.questions*.id, offset, max)
        def tags = questionService.tagsForQuestions(unacceptedQuestions)
        
        def map = [questionsToDisplay: unacceptedForNeededTag, completeQuestionList: unacceptedQuestions, completePaginationList: completePaginationList, neededTag: neededTag, tags: tags, subtitle: subtitle]
        render(view: "/question/index", model: map)
    }
}
