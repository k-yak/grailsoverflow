package fr.isima.grailsoverflow

class MessageController {
    def voteService

    def voteUp() {
        voteProcess(params.id, Vote.VOTE_UP)
    }

    def voteDown() {
        voteProcess(params.id, Vote.VOTE_DOWN)
    }

    def voteProcess(def id, def value) {
        def voteValue = voteService.processVote(id, value, session.user)

        render(text:"${voteValue}", contentType:'text/html')
    }
}
