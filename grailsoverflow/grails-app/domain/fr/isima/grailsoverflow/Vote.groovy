package fr.isima.grailsoverflow

class Vote {
    static final int VOTE_UP = 1
    static final int VOTE_DOWN = -1
    
    int value = 0
    
    def userVote(User user, int value) {
        users[user.email] = value.toString()
    }
    
    def getUserVote(User user) {
        users[user.email].toInteger()
    }

    static hasOne = [users: Map]
    static belongsTo = Question
    
    static constraints = {
    }
}
