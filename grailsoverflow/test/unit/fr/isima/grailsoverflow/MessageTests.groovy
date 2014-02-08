package fr.isima.grailsoverflow

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

@TestFor(Tag)
class MessageTests {

    void testHasVoted() {
        User voteUser = new User(email: "testEmail", displayName: "testDisplayName")
        User testUser = new User(email: "testEmail2", displayName: "testDisplayName")

        def question = new Question(title: "test", content: "test", user: voteUser, dateCreated: new Date())
        question.vote.users[voteUser.email] = Vote.VOTE_UP

        assert question.hasVoted(voteUser) == true
        assert question.hasVoted(testUser) == false
    }
}
