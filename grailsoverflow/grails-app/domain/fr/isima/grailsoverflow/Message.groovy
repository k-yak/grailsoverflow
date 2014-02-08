package fr.isima.grailsoverflow

abstract class Message {
    /// Content of the message (question / answer)
    String content
    /// All votes of the message
    Vote vote = new Vote(value: 0, message: this)
    /// Creation date of the message (auto fill)
    Date dateCreated

    /**
     * Test if a user has voted for this message
     *
     * @param user User to test
     * @return True if the user has voted, false otherwise
     */
    def hasVoted(User user) {
         vote.users.containsKey(user.email)
    }

    static hasOne = [vote: Vote]
    static belongsTo = [user: User]
    
    static constraints = {
        content type: 'text', maxSize: 5000
    }
}

