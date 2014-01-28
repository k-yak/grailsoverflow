package fr.isima.grailsoverflow

class Question extends Message {
    String status = "grow.status.unanswered"
    String title
    int views
    Set answers = []

    def sortedAnswers() {
        answers.sort { a, b ->
            a.vote.value < b.vote.value ? 1 : -1
        }
    }

    def tagsToString() {
        def tagsBuilder = new StringBuffer()

        tags?.each() { tag ->
            tagsBuilder << tag.name << ','
        }

        String tagsString = tagsBuilder.toString()
        int lastCommaIndex = tagsString.lastIndexOf(",")

        if (lastCommaIndex != -1) {
            tagsString = tagsString.substring(0, lastCommaIndex)
        }

        return tagsString.toString()
    }

    static searchable = true
    static hasMany = [tags: Tag, answers: Answer, comments: Comment]
    static belongsTo = [user: User]

    static constraints = {
        title blank: false
        content blank: false, maxSize: 1000
        status inList: [ "grow.status.unanswered", "grow.status.answered", "grow.status.accepted" ]
        views min: 0
    }
    
    static mapping = {
        answers cascade:"all,delete-orphan"
    }
}
