package fr.isima.grailsoverflow

import grails.test.mixin.*
import org.junit.*

@TestFor(HistoryService)
@Mock([User, Question, Tag, Answer, HistoryElement])
class HistoryServiceTests {
    def historyService = new HistoryService()
    def user, q1, a1

    @Before
    void setUp() {
        user = new User(email: "testEmail", displayName: "testDisplayName").save(failOnError: true)
        q1 = new Question(title: 'test', content: "test", dateCreated: new Date(), user: user).save(failOnError: true)
        a1 = new Answer(content: "test", question: q1, user: user)
    }

    def testAddAcceptAnswer() {
        assert user.history == null || user.history?.size() == 0

        historyService.addAcceptAnswer(user, a1)

        assert user.history.size() == 1

        historyService.removeAcceptAnswer(user, a1)

        assert user.history == null || user.history?.size() == 0
    }
}
