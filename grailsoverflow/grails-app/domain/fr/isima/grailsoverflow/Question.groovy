package fr.isima.grailsoverflow

class Question extends Message {
    String status
    String title
    int views
    Date dateCreated
        
    static hasMany = [tags: Tag]

    static constraints = {
        title blank: false, unique: true
        content blank: false, maxSize: 1000
        status inList: [ "Unanswered", "Answered", "Accepted" ]
        views min: 0
    }
}
