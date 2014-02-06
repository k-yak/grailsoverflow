package fr.isima.grailsoverflow

class HistoryElement {
    Date date = new Date()
    String content
    String title

    static constraints = {
        content blank: false
        date nullable: false
        title blank: false
    }
}
