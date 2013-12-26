package fr.isima.grailsoverflow

class AnswerController {
    def voteUp() {
        voteProcess(params.id, Vote.VOTE_UP)
    }
    
    def voteDown() {
        voteProcess(params.id, Vote.VOTE_DOWN)
    }
    
    def voteProcess(def id, def value) {
        def answer = Answer.get(id)
        
        if (User.isUserAuthenticated()) {     
            if (answer.hasVoted(User.CurrentUser)) {
                // Remove old vote 
                answer.vote.value -= answer.vote.getUserVote(User.CurrentUser)
                if (answer.vote.getUserVote(User.CurrentUser) == value) {
                    println "DEBUG : You have already voted (${User.CurrentUser.email} -> ${answer.vote.getUserVote(User.CurrentUser)})"
                } else {
                    println "DEBUG : Your vote changed"
                }
            }
            answer.vote.userVote(User.CurrentUser, value)
            answer.save(failOnError: true)
        } else {
            println "DEBUG : You must be logged in to vote"
        }
        
        render(text:"${answer.vote.value}", contentType:'text/html') 
    }
}
