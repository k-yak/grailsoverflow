package fr.isima.grailsoverflow

abstract class Message {
    String content
    Vote vote = new Vote(value: 0)
    
    def hasVoted(User user) {
         vote.users.containsKey(user.email)
    }
}

