package fr.isima.grailsoverflow

class Question extends Message {
    String status
    String title
    int views
    
    def sortedAnswers() {
        answers.sort { a, b ->
            a.vote.value < b.vote.value ? 1 : -1
        }
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
        title blank: false, unique: true
        content blank: false, maxSize: 1000
        status inList: [ "Unanswered", "Answered", "Accepted" ]
        views min: 0
    }
}
