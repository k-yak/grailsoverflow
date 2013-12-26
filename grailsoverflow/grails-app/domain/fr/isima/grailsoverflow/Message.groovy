package fr.isima.grailsoverflow

abstract class Message {
    String content
    Vote vote = new Vote(value: 0)
    Date dateCreated
    
    def hasVoted(User user) {
         vote.users.containsKey(user.email)
    }
    
    static constraints = {
    }
}

