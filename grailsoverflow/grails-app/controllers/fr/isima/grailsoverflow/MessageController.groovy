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
            if (message.hasVoted(User.getCurrentUserFromDB())) {
                // Remove old vote 
                message.vote.value -= message.vote.getUserVote(User.getCurrentUserFromDB())
                if (message.vote.getUserVote(User.getCurrentUserFromDB()) == value) {
                    // If the user has already voted this choice, remove the vote
                    message.vote.userVote(User.getCurrentUserFromDB(), Vote.VOTE_NONE)
                } else {
                     // Otherwise change the vote
                    message.vote.userVote(User.getCurrentUserFromDB(), value)
                }
            } else {
                message.vote.userVote(User.getCurrentUserFromDB(), value)
            }
        } else {
            println "DEBUG : You must be logged in to vote"
        }
        
        render(text:"${message.vote.value}", contentType:'text/html') 
    }
}
