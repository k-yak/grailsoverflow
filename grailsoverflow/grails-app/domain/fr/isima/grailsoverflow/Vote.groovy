package fr.isima.grailsoverflow

class Vote {
    static final int VOTE_UP = 1
    static final int VOTE_NONE = 0
    static final int VOTE_DOWN = -1
    
    int value = 0
    Map users = [:]

    def getUserVote(User user) {
        users[user.email]?.toInteger() ?: 0
    }

    static belongsTo = [message: Message]
    
    static constraints = {
        users lazy:false
    }
}
