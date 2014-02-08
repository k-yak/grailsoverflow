package fr.isima.grailsoverflow

class VoteService {
    static transactional = true

    def sessionService
    def medalService

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

        medalService.testScoreMedals(message.user);

        message.user.save(failOnError: true)
        message.save(failOnError: true)
        message.vote.save(failOnError: true)
        sessionService.reloadUserSession()
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

    def processVote(def messageId, int value, User currentUser) {
        Message message = Message.get(messageId)

        if (currentUser != null) {
            changeUserVote(message, currentUser, value)
        } else {
            if (currentUser == null) {
                log.info "DEBUG : Unlogged uer tried to vote but is not logged in"
            } else {
                log.info "DEBUG : User ${currentUser.email} tried to vote but is not logged in"
            }
        }

        return message.vote.value
    }
}
