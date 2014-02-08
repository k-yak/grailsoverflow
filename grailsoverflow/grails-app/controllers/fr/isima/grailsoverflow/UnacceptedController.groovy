package fr.isima.grailsoverflow

class UnacceptedController {
    def questionService
    def tagService
    def userService

    String subtitle = "grow.subtitle.unnacepted.questions"
    
    /// Unaccepted questions
    def index() {
        def offset = params?.offset ?: 0
        def max = params?.max ?: AppConfig.MAX_QUESTION

        def completeQuestionList = questionService.getUnacceptedQuestions(0, 100)
        def unacceptedQuestions = questionService.getUnacceptedQuestions(offset, max)
        def tags = questionService.tagsForQuestions(completeQuestionList, AppConfig.MAX_TAGS)
        def user = userService.getUserById(session.user?.id)

        def map =  [questionsToDisplay: unacceptedQuestions, completeQuestionList: completeQuestionList, completePaginationList: completeQuestionList, tags: tags, subtitle: subtitle, user: user]
        render(view: "/question/index", model: map)
    }
    
    def questionsForTag() {
        def offset = params?.offset ?: 0
        def max = params?.max ?: AppConfig.MAX_QUESTION

        def unacceptedQuestions = questionService.getUnacceptedQuestions(0, 100)
        def neededTag = tagService.getTagByName(params.tag)
        def unacceptedForNeededTag = questionService.getUnacceptedQuestionsInList(neededTag.questions*.id, 0, 100)
        def completePaginationList = questionService.getUnacceptedQuestionsInList(neededTag.questions*.id, offset, max)
        def tags = questionService.tagsForQuestions(unacceptedQuestions, AppConfig.MAX_TAGS)
        def user = userService.getUserById(session.user?.id)
        
        def map = [questionsToDisplay: unacceptedForNeededTag, completeQuestionList: unacceptedQuestions, completePaginationList: completePaginationList, neededTag: neededTag, tags: tags, subtitle: subtitle, user: user]
        render(view: "/question/index", model: map)
    }
}
