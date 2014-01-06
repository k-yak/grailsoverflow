package fr.isima.grailsoverflow

class MessageController {
    def voteUp() {
        voteProcess(params.id, Vote.VOTE_UP)
    }

    def voteDown() {
        voteProcess(params.id, Vote.VOTE_DOWN)
    }

    def voteProcess(def id, def value) {
        def message = Message.get(id)

        if (User.isUserAuthenticated()) {
            message.vote.changeUserVote(User.getCurrentUserFromDB(), value)
        } else {
            println "DEBUG : You must be logged in to vote"
        }

        render(text:"${message.vote.value}", contentType:'text/html')
    }
}
