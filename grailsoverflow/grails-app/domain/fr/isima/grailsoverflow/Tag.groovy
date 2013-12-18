package fr.isima.grailsoverflow

class Tag {
    String name
    
    static hasMany = [questions: Question, users: User]
    static belongsTo = Question

    static constraints = {
        name blank: false, unique: true
    }
        
    String toString() { return name }
}
