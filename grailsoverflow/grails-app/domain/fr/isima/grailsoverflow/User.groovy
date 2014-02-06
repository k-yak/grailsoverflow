package fr.isima.grailsoverflow

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class User {
    String displayName
    String realName
    String email
    String website
    String location
    int score = 0
    int connectionCounter = 0
    Date lastVisit = new Date()
    int profileView = 0
    boolean admin = false
    boolean ban = false
    Set favoriteTags = []

    def favoriteTagsToString() {
        def tagsBuilder = new StringBuffer()

        favoriteTags?.each() { tag ->
            tagsBuilder << tag.name << ','
        }

        String tagsString = tagsBuilder.toString()
        int lastCommaIndex = tagsString.lastIndexOf(",")

        if (lastCommaIndex != -1) {
            tagsString = tagsString.substring(0, lastCommaIndex)
        }

        return tagsString.toString()
    }

    def isOwnerOfQuestion(def question) {
        question.user.email == email
    }
    
    def isOwnerOfAnswer(def answer) {
        answer.user.email == email
    }

    def getMedalsOfType(int type) {
        int count = 0;

       medals.each() { medal ->
            if (medal.getType() == type) {
                ++count
            }
        }

        return count
    }

    def haveMedal(Medal medal) {
        boolean found = false

        if (medals != null && medals.contains(medal))
            found = true;

        return found
    }

    static hasMany = [questions: Question, favoriteTags: Tag, medals: Medal, history: HistoryElement]

    static constraints = {
        email blank: false, unique: true
        realName nullable: true
        website nullable: true
        lastVisit blank: false
        location nullable: true
    }
    
    static mapping = {
        questions cascade:"all,delete-orphan"
        favoriteTags lazy: false
    }
}
