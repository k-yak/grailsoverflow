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
            if (message.hasVoted(User.CurrentUser)) {
                // Remove old vote 
                message.vote.value -= message.vote.getUserVote(User.CurrentUser)
                if (message.vote.getUserVote(User.CurrentUser) == value) {
                    // If the user has already voted this choice, remove the vote
                    message.vote.userVote(User.CurrentUser, Vote.VOTE_NONE)
                } else {
                     // Otherwise change the vote
                    message.vote.userVote(User.CurrentUser, value)
                }
            } else {
                message.vote.userVote(User.CurrentUser, value)
            }
            message.save(failOnError: true)
        } else {
            println "DEBUG : You must be logged in to vote"
        }
        
        render(text:"${message.vote.value}", contentType:'text/html') 
    }
}
