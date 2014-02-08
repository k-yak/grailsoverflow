package fr.isima.grailsoverflow

class Vote {
    /// Available vote options
    static final int VOTE_UP = 1
    static final int VOTE_NONE = 0
    static final int VOTE_DOWN = -1

    /// Current value of the vote
    int value = 0
    /// User that have voted
    Map users = [:]

    def getUserVote(User user) {
        users[user.email]?.toInteger() ?: 0
    }

    static belongsTo = [message: Message]
    
    static constraints = {
        users lazy:false
    }
}
