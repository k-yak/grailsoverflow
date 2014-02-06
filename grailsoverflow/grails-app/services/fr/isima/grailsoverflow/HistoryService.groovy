package fr.isima.grailsoverflow

class HistoryService {
    static transactional = true

    def grailsApplication

    def addQuestion(User user, Question question) {
        def historyTitle = "grow.history.question"
        def historyContent = """
            <a href="/GrailsOverflow/question/show/${question.id}">
                ${question.title}
            </a>
        """
        def historyElement = new HistoryElement(title: historyTitle, content: historyContent).save(failOnError: true)
        user.addToHistory(historyElement)
        user.save(failOnError: true)
    }

    def addAnswer(User user, Answer answer) {
        def historyTitle = "grow.history.answer"
        def historyContent = """
            <a href="/GrailsOverflow/question/show/${answer.question.id}">
                ${answer.question.title}
            </a>
        """
        def historyElement = new HistoryElement(title: historyTitle, content: historyContent).save(failOnError: true)
        user.addToHistory(historyElement)
        user.save(failOnError: true)
    }

    def addAcceptAnswer(User user, Answer answer) {
        def historyTitle = "grow.history.acceptAnswer"
        def historyContent = """
            <a href="/GrailsOverflow/question/show/${answer.question.id}">
                ${answer.question.title}
            </a>
        """
        def historyElement = new HistoryElement(title: historyTitle, content: historyContent).save(failOnError: true)
        user.addToHistory(historyElement)
        user.save(failOnError: true)
    }

    def addMedal(User user, Medal medal) {
        def historyTitle = "grow.history.medal"
        def medalMessage = grailsApplication.getMainContext().getMessage(medal.title, [medal.value] as Object[], "", org.springframework.context.i18n.LocaleContextHolder.locale)
        def medalImage = ""

        if (medal.type == medal.BRONZE) {
            medalImage = "<img src=''/GrailsOverflow/static/images/medals/medal-bronze.png'' class='medals'>"
        } else if (medal.type == medal.SILVER) {
            medalImage = "<img src=''/GrailsOverflow/static/images/medals/medal-silver.png'' class='medals'>"
        } else if (medal.type == medal.GOLD) {
            medalImage = "<img src=''/GrailsOverflow/static/images/medals/medal-gold.png'' class='medals'>"
        }

        def historyContent = """
            <a href="">
               ${medalMessage} ${medalImage}
            </a>
        """

        def historyElement = new HistoryElement(title: historyTitle, content: historyContent).save(failOnError: true)
        user.addToHistory(historyElement)
        user.save(failOnError: true)
    }
}
