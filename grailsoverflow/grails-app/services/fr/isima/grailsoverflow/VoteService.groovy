package fr.isima.grailsoverflow

class VoteService {
    static transactional = false

    def sessionService

    def userVote(Message message, User user, int newValue) {
        def oldValue = message.vote.getUserVote(user)

        message.vote.value += newValue
        message.vote.users[user.email] = newValue.toString()

        // Manage message user vote
        switch (oldValue) {
            case 0:
                message.user.score += AppConfig.VOTE_SCORE * newValue
                break;
            case 1:
                message.user.score -= AppConfig.VOTE_SCORE
                if (newValue == -1) {
                    message.user.score -= AppConfig.VOTE_SCORE
                }
                break;
            case -1:
                message.user.score += AppConfig.VOTE_SCORE
                if (newValue == 1) {
                    message.user.score += AppConfig.VOTE_SCORE
                }
                break;
        }
        message.user.save(failOnError: true)
        message.save(failOnError: true)
        message.vote.save(failOnError: true)
        sessionService.reloadUserSession()

        User other = User.findById(user.id)
        Message otherMessage = Message.findById(message.id)
    }

    def changeUserVote(Message message, User user, int newValue) {
        def oldValue = message.vote.getUserVote(user)

        // Remove old vote
        message.vote.value -= oldValue

        if (oldValue == newValue) {
            // If the user has already voted this choice, remove the vote
            userVote(message, user, Vote.VOTE_NONE)
        } else {
            // Otherwise change the vote
            userVote(message, user, newValue)
        }
    }

    def processVote(def id, int value, User currentUser) {
        Message message = Message.get(id)

        if (currentUser != null) {
            changeUserVote(message, currentUser, value)
        } else {
            log.info "DEBUG : You must be logged in to vote"
        }

        return message.vote.value
    }
}
