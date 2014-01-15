package fr.isima.grailsoverflow

import org.compass.core.engine.SearchEngineQueryParseException

class QuestionController {
    def searchableService
    def questionService
    def tagService
    def answerService
    def sessionService

    String subtitle = "Latest questions"
    
    /**
     * Latest questions
     */
    def index() {
        def offset = params?.offset ?: 0
        def max = params?.max ?: AppConfig.MAX_QUESTION

        def latestQuestionsPaginate = questionService.getLatestQuestions(offset, max)
        def completeQuestionList = questionService.getLatestQuestions(0, 100)
        def tags = questionService.tagsForQuestions(completeQuestionList)
        
        return [questionsToDisplay: latestQuestionsPaginate, completeQuestionList: completeQuestionList, completePaginationList: completeQuestionList, tags: tags, subtitle: subtitle]
    }
    
    def questionsForTag() {
        def offset = params?.offset ?: 0
        def max = params?.max ?: AppConfig.MAX_QUESTION

        def completeQuestionList = questionService.getLatestQuestions(0, 100)
        def neededTag = tagService.getTagByName(params.tag)
        def latestForNeededTag = questionService.getLatestQuestionsInList(neededTag.questions*.id , 0, 100)
        def completePaginationList = questionService.getLatestQuestionsInList(neededTag.questions*.id, offset, max)
        def tags = questionService.tagsForQuestions(completeQuestionList)
        
        def map = [questionsToDisplay: latestForNeededTag, completeQuestionList: completeQuestionList, completePaginationList: completePaginationList, neededTag: neededTag, tags: tags, subtitle: subtitle]
        render(view: "index", model: map)
    }
    
    def showQuestion() {
        def question = questionService.getQuestion(params.question)
        
        return [question: question]
    }
    
    def edit() {
        def question = questionService.getQuestion(params.question)

        return [question: question]
    }

    def search() {
        def error = false
        def searchedQuestions = null

        if (!params.q?.trim()) {
            error = true
        }
        else {
            try {
                // .results .offset .total .max can be used on searchedQuestions
                searchedQuestions = searchableService.search(params.q, params)
            } catch (SearchEngineQueryParseException ex) {
                error = true
            }
        }

        return [searchedQuestions: searchedQuestions, error: error]
    }
    
    def editQuestion() {
        def question = questionService.getQuestion(params.id)

        if (session.user == null || !session.user.isOwnerOfQuestion(question)) {
            redirect(controller: "question", action: "index")
        } else {
            questionService.editQuestion(params.id, params.newQuestionTitle, params.newQuestionContent, params.tags)

            redirect(uri: "/question/show/${params.id}")
        }
    }

    def delete() {
        def question = questionService.getQuestion(params.question)

        if (session.user != null && session.user.isOwnerOfQuestion(question)) {
            questionService.deleteQuestion(params.question, session.user)
        }
        
        redirect(controller: "question", action: "index")
    }

    def addQuestion() {
        if (session.user == null) {
            redirect(controller: "question", action: "index")
        } else {
            def question = questionService.addQuestion(params.newQuestionTitle, params.newQuestionContent, params.tags, session.user)
            redirect(uri: "/question/show/${question.id}")
        }
    }
    
    def answer() {
        if (session.user == null) {
            redirect(controller: "question", action: "index")
        } else {
            questionService.answerToQuestion(params.id, params.answerContent, session.user)

            redirect(uri: "/question/show/${params.id}")
        }
    }

    def add() {
        def latestQuestions = questionService.getLatestQuestions(0, 100)
        def tags = questionService.tagsForQuestions(latestQuestions)

        [tags: tags, completeQuestionList: latestQuestions]
    }
}
