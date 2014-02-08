package fr.isima.grailsoverflow

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class User {
    /// Display name of the user
    String displayName
    /// Email of the user
    String email
    /// Website of the user
    String website
    /// Location of the user
    String location
    /// Global score of the user
    int score = 0
    /// Counter of
    int connectionCounter = 0
    /// Last connexion
    Date lastVisit = new Date()
    /// Number of view of the user profile
    int profileView = 0
    /// Is the user admin
    boolean admin = false
    /// Is the user banned
    boolean ban = false
    /// List of favorite tags
    Set favoriteTags = []

    /**
     * Convert the list of tags into a comma separated string
     *
     * @see tagit javascript library
     * @return Tags as string
     */
    def favoriteTagsToString() {
        def tagsBuilder = new StringBuffer()

        // Create string
        favoriteTags?.each() { tag ->
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

    /**
     * Is the user owner of a question
     *
     * @param question Question to test
     * @return True if the user is owner, false otherwise
     */
    def isOwnerOfQuestion(def question) {
        question.user.email == email
    }

    /**
     * Is the user owner of an answer
     *
     * @param answer Answer to test
     * @return True if the user is owner, false otherwise
     */
    def isOwnerOfAnswer(def answer) {
        answer.user.email == email
    }

    /**
     * Count the number of medals with a specified type
     *
     * @see Medal
     * @param type Type of the medal to test
     * @return The number of medals found
     */
    def countMedalsOfType(int type) {
        int count = 0;

       medals.each() { medal ->
            if (medal.getType() == type) {
                ++count
            }
        }

        return count
    }

    /**
     * Test if a user have a specified medal
     *
     * @param medal Medal to find
     * @return True if the user have the medal, false otherwise
     */
    def haveMedal(Medal medal) {
        boolean found = false

        if (medals != null && medals.contains(medal))
            found = true;

        return found
    }

    static hasMany = [questions: Question, favoriteTags: Tag, medals: Medal, history: HistoryElement]

    static constraints = {
        email blank: false, unique: true
        website nullable: true
        lastVisit blank: false
        location nullable: true
    }
    
    static mapping = {
        questions cascade:"all,delete-orphan"
        favoriteTags lazy: false
    }
}
