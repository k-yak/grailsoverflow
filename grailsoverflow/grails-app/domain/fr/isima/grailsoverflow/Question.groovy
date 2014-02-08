package fr.isima.grailsoverflow

class Question extends Message {
    /// Status of the question
    String status = "grow.status.unanswered"
    /// Title of the question
    String title
    /// Answers related to the question
    Set answers = []

    /**
     * Sort question answers by votes
     *
     * @return List of answer sorted by votes
     */
    def sortedAnswers() {
        answers.sort { a, b ->
            a.vote.value < b.vote.value ? 1 : -1
        }
    }

    /**
     * Convert all tags into a comma separated string
     *
     * @return Tags as a string
     */
    def tagsToString() {
        def tagsBuilder = new StringBuffer()

        // Create string
        tags?.each() { tag ->
            tagsBuilder << tag.name << ','
        }
        String tagsString = tagsBuilder.toString()

        // Remove last comma
        int lastCommaIndex = tagsString.lastIndexOf(",")
        if (lastCommaIndex != -1) {
            tagsString = tagsString.substring(0, lastCommaIndex)
        }

        return tagsString.toString()
    }

    // A question is searchable, it means that it could be retrieve by the search plugin
    static searchable = true
    static hasMany = [tags: Tag, answers: Answer]
    static belongsTo = [user: User]

    static constraints = {
        title blank: false
        content blank: false, maxSize: 1000
        status inList: [ "grow.status.unanswered", "grow.status.answered", "grow.status.accepted" ]
    }

    // Delete on cascade
    static mapping = {
        answers cascade:"all,delete-orphan"
    }
}
