package fr.isima.grailsoverflow

class QuestionService {
    static transactional = true

    def sessionService
    def answerService

    def answerToQuestion(def questionId, def content, def currentUser) {
        def user = User.get(currentUser.id)
        def question = getQuestion(questionId)
        def answer = answerService.createAnswer(content, user, question)

        answer.user.score += AppConfig.ANSWER_SCORE
        user.save(failOnError: true)

        addAnswer(question, answer)
        sessionService.reloadUserSession()

        return answer
    }

    def afterInsertActions(def question) {
        question.user.score += AppConfig.QUESTION_SCORE

        question.user.save(failOnError: true)
        sessionService.reloadUserSession()
    }

    def getUnacceptedQuestions(def offset, def max) {
        Question.findAllByStatusNotEqual("Accepted", [sort: "dateCreated", order: "desc", offset: offset, max: max])
    }

    def getUnacceptedQuestionsInList(def idList, def offset, def max) {
        Question.findAllByStatusNotEqualAndIdInList("Accepted", idList, [sort: "dateCreated", order: "desc", offset: offset, max: max])
    }

    def getLatestQuestions(def offset, def max) {
        Question.list(sort: "dateCreated", order: "desc", offset: offset, max: max)
    }

    def tagsForQuestions(List<Question> questions) {
        def tags = []

        for (def question in questions) {
            for (def tag in question.tags) {
                tags << tag
            }
        }
        tags = tags.unique().sort { a, b ->
            a.questions.size() < b.questions.size() ? 1 : -1
        }
    }

    def getLatestQuestionsInList(def idList, int offset, int max) {
        Question.findAllByIdInList(idList, [sort: "dateCreated", order: "desc", offset: offset, max: max])
    }

    def getQuestion(def questionId) {
        Question.findById(questionId)
    }

    def newTagsFromString(Question question, String tagsAsString) {
        question.tags = []

        if (!tagsAsString.isEmpty()) {
            def newTags = tagsAsString.split(',')
            newTags.each() { tagName ->
                tagName = tagName.toLowerCase()
                Tag tag = Tag.findByName(tagName) ?: new Tag(name: tagName).save(failOnError: true)
                question.addToTags(tag)
            }
        }

        question.save(failOnError: true)
    }

    def editQuestion(def id, String title, String content, String tags) {
        def question = getQuestion(id)

        question.title = title
        question.content = content  - "<p>&nbsp;</p>"

        // Manage tags
        newTagsFromString(question, tags)
    }


    def beforeDeleteActions(Question question) {
        // Remove user score for question
        question.user.score -= AppConfig.QUESTION_SCORE

        // Remove user score for votes
        question.user.score -= question.vote.value * AppConfig.VOTE_SCORE

        question.user.save(failOnError: true)
        sessionService.reloadUserSession()
    }

    def deleteQuestion(def questionId, User currentUser) {
        def question = getQuestion(questionId)

        if (currentUser != null && (currentUser.isOwnerOfQuestion(question) || currentUser.admin == true)) {
            User user = question.user

            question.answers.each() { answer ->
                answerService.beforeDeleteActions(answer)
            }

            log.info "DEBUG : Question ${questionId} deleted by user ${currentUser.id} (${currentUser.email})"
            beforeDeleteActions(question)
            user.removeFromQuestions(question)

            user.save(failOnError: true)
            sessionService.reloadUserSession()
        }
    }

    def addQuestion(String title, String content, String tags, User currentUser) {
        def user = User.get(currentUser.id)

        // Manage question
        Question question = new Question(
                title: title,
                content: content - "<p>&nbsp;</p>",
                dateCreated: new Date(),
                user: user
        ).save(failOnError: true)

        // Manage tags
        newTagsFromString(question, tags)

        user.addToQuestions(question)

        afterInsertActions(question);
        sessionService.reloadUserSession()

        return question
    }

    def addAnswer(Question question, Answer answer) {
        question.addToAnswers(answer)
        updateStatus(question)

        question.save(failOnError: true)
    }

    def updateStatus(Question question) {
        if (question.answers.isEmpty()) {
            question.status = "grow.status.unanswered"
        } else if (getAcceptedAnswerForQuestion(question) != null) {
            question.status = "grow.status.accepted"
        } else {
            question.status = "grow.status.answered"
        }

        question.save(failOnError: true)
    }

    def getAcceptedAnswerForQuestion(Question question) {
        question.answers.find() {
            it.accepted == true
        }
    }
}
