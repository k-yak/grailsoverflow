package fr.isima.grailsoverflow

import org.compass.core.engine.SearchEngineQueryParseException

class QuestionController {
    def searchableService
    def questionService
    def tagService
    def sessionService
    def userService

    String subtitle = "grow.subtitle.latest.questions"
    
    /// Latest questions
    def index() {
        def offset = params?.offset ?: 0
        def max = params?.max ?: AppConfig.MAX_QUESTION

        def latestQuestionsPaginate = questionService.getLatestQuestions(offset, max)
        def completeQuestionList = questionService.getLatestQuestions(0, 100)
        def tags = questionService.tagsForQuestions(completeQuestionList, AppConfig.MAX_TAGS)
        def user = userService.getUserById(session.user?.id)
        
        return [questionsToDisplay: latestQuestionsPaginate, completeQuestionList: completeQuestionList, completePaginationList: completeQuestionList, tags: tags, subtitle: subtitle, user: user]
    }

    /// Questions for a choosen tag
    def questionsForTag() {
        def offset = params?.offset ?: 0
        def max = params?.max ?: AppConfig.MAX_QUESTION

        def completeQuestionList = questionService.getLatestQuestions(0, 100)
        def neededTag = tagService.getTagByName(params.tag)
        def latestForNeededTag = questionService.getLatestQuestionsInList(neededTag.questions*.id , 0, 100)
        def completePaginationList = questionService.getLatestQuestionsInList(neededTag.questions*.id, offset, max)
        def tags = questionService.tagsForQuestions(completeQuestionList, AppConfig.MAX_TAGS)
        def user = userService.getUserById(session.user?.id)
        
        def map = [questionsToDisplay: latestForNeededTag, completeQuestionList: completeQuestionList, completePaginationList: completePaginationList, neededTag: neededTag, tags: tags, subtitle: subtitle, user: user]
        render(view: "index", model: map)
    }
    
    def showQuestion() {
        def question = questionService.getQuestion(params.question)
        
        return [question: question]
    }
    
    def edit() {
        def question = questionService.getQuestion(params.question)

        if (session.user == null || (!session.user.isOwnerOfQuestion(question)  && session.user.admin == false) ) {
            log.warn "WARNING : Address ${request.getRemoteAddr()} try to edit question ${params.question} but do not have rights"
            sessionService.addMessage("danger", "grow.error.access.forbidden")
            redirect(controller: "question", action: "index")
        } else {
            return [question: question]
        }
    }

    /// @see Searchable plugin
    def search() {
        def error = false
        def searchedQuestions = null

        log.info "DEBUG : Searching entry '${params.q}' ..."

        if (!params.q?.trim()) {
            error = true
        }
        else {
            try {
                // .results .offset .total .max can be used on searchedQuestions
                searchedQuestions = searchableService.search(params.q, params)
                log.info "DEBUG : Found ${searchedQuestions.results.size()} results"
            } catch (SearchEngineQueryParseException ex) {
                log.error "ERROR : Search error"
                error = true
            }
        }

        return [searchedQuestions: searchedQuestions, error: error]
    }
    
    def editQuestion() {
        def question = questionService.getQuestion(params.id)

        if (session.user == null || (!session.user.isOwnerOfQuestion(question)  && session.user.admin == false) ) {
            log.warn "WARNING : Address ${request.getRemoteAddr()} try to edit question ${params.id} but do not have rights"
            sessionService.addMessage("danger", "grow.error.access.forbidden")
            redirect(controller: "question", action: "index")
        } else {
            questionService.editQuestion(params.id, params.newQuestionTitle, params.newQuestionContent, params.tags)

            redirect(uri: "/question/show/${params.id}")
        }
    }

    def delete() {
        def question = questionService.getQuestion(params.question)

        if (session.user != null && (session.user.isOwnerOfQuestion(question)  || session.user.admin == true)) {
            questionService.deleteQuestion(params.question, session.user)
        } else {
            log.warn "WARNING : Address ${request.getRemoteAddr()} try to delete question ${params.question} but do not have rights"
            sessionService.addMessage("danger", "grow.error.access.forbidden")
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
        def tags = questionService.tagsForQuestions(latestQuestions, AppConfig.MAX_TAGS)

        [tags: tags, completeQuestionList: latestQuestions]
    }
}
