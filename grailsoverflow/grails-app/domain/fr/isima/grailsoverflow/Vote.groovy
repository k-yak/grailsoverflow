package fr.isima.grailsoverflow

class Vote {
    static final int VOTE_UP = 1
    static final int VOTE_NONE = 0
    static final int VOTE_DOWN = -1
    
    int value = 0
    Map users = [:]
    
    def userVote(User user, int value) {
        def oldValue = users[user.email] ?: '0'

        this.value += value
        users[user.email] = value.toString()

        // Manage message user vote
        switch (oldValue.toInteger()) {
            case 0:
                message.user.score += AppConfig.VOTE_SCORE * value
                break;
            case 1:
                message.user.score -= AppConfig.VOTE_SCORE
                if (value == -1) {
                    message.user.score -= AppConfig.VOTE_SCORE
                }
                break;
            case -1:
                message.user.score += AppConfig.VOTE_SCORE
                if (value == 1) {
                    message.user.score += AppConfig.VOTE_SCORE
                }
                break;
        }
        message.user.save(failOnError: true)
        message.save(failOnError: true)
    }
    
    def getUserVote(User user) {
        users[user.email]?.toInteger() ?: 0
    }

    static belongsTo = [message: Message]
    
    static constraints = {
    }
}
