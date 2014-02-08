package fr.isima.grailsoverflow

class HistoryElement {
    /// Date of the action
    Date date = new Date()
    /// HTML content of the action
    String content
    /// Title of the action
    String title

    static constraints = {
        content blank: false
        date nullable: false
        title blank: false
    }
}
