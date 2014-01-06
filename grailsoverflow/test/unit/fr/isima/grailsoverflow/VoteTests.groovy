package fr.isima.grailsoverflow

import grails.test.mixin.*
import org.junit.*

@TestFor(Vote)
@Mock([User, Question])
class VoteTests {
    void testChangeUserVote() {
        User testUser = new User(email: "testEmail", displayName: "testDisplayName")
        def question = new Question(title: "titleTest", content: "contentTest", dateCreated: new Date(), user: testUser)

        assert testUser.score == 0
        assert question.vote.getUserVote(testUser) == Vote.VOTE_NONE

        // Vote up
        question.vote.changeUserVote(testUser, Vote.VOTE_UP)
        assert testUser.score == AppConfig.VOTE_SCORE
        assert question.vote.getUserVote(testUser) == Vote.VOTE_UP

        // Vote up again, should return to VOTE_NONE value
        question.vote.changeUserVote(testUser, Vote.VOTE_UP)
        assert testUser.score == 0
        assert question.vote.getUserVote(testUser) == Vote.VOTE_NONE

        // Vote down
        question.vote.changeUserVote(testUser, Vote.VOTE_DOWN)
        assert testUser.score == -1 * AppConfig.VOTE_SCORE
        assert question.vote.getUserVote(testUser) == Vote.VOTE_DOWN

        // Vote up
        question.vote.changeUserVote(testUser, Vote.VOTE_UP)
        assert testUser.score == AppConfig.VOTE_SCORE
        assert question.vote.getUserVote(testUser) == Vote.VOTE_UP
    }
}
