package fr.isima.grailsoverflow

abstract class Message {
    String content
    Vote vote = new Vote(value: 0, message: this)
    Date dateCreated
    
    def hasVoted(User user) {
         vote.users.containsKey(user.email)
    }

    static hasOne = [vote: Vote]
    static belongsTo = [user: User]
    
    static constraints = {
        content type: 'text', maxSize: 10000000 
    }
}

