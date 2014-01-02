package fr.isima.grailsoverflow

class Question extends Message {
    String status = "Unanswered"
    String title
    int views
    Set answers = []
    
    def sortedAnswers() {
        answers.sort { a, b ->
            a.vote.value < b.vote.value ? 1 : -1
        }
    }
    
    def updateStatus() {
        if (answers.isEmpty()) {
            status = "Unanswered"
        } else if (acceptedAnswer() != null) {
            status = "Accepted"
        } else {
            status = "Answered"
        }
    }
    
    def answer(Answer answer) {
        answer.user.score += AppConfig.ANSWER_SCORE
        answer.user.save(failOnError: true)

        addToAnswers(answer)
        updateStatus()

        save(failOnError: true)
    }

    def acceptedAnswer() {
        answers.find() {
            it.accepted == true
        }
    }

    def clearUserScore() {
        // Remove user score for question
        user.score -= AppConfig.QUESTION_SCORE

        // Remove user score for votes
        user.score -= vote.value * AppConfig.VOTE_SCORE

        user.save(failOnError: true)
    }
    
    static def tagsForQuestions(def questions) {
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
        
    static hasMany = [tags: Tag, answers: Answer, comments: Comment]
    static belongsTo = [user: User]

    static constraints = {
        title blank: false
        content blank: false, maxSize: 1000
        status inList: [ "Unanswered", "Answered", "Accepted" ]
        views min: 0
    }
    
    static mapping = {
        answers cascade:"all,delete-orphan"
    }
}
