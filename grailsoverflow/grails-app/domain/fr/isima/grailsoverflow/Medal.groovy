package fr.isima.grailsoverflow

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Medal {
    /// Available medal types
    static final int GOLD = 0
    static final int SILVER = 1
    static final int BRONZE = 2

    /// Current type
    int type = BRONZE
    /// Title of the medal
    String title
    /// Score needed to get the medal
    int value

    Medal(def type, def title, def value) {
        this.type = type
        this.title = title
        this.value = value
    }

    static constraints = {
        title blank: false
    }
}
