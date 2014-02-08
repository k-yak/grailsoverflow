package fr.isima.grailsoverflow

import grails.test.mixin.*
import org.junit.*

@TestFor(AnswerController)
@Mock([User, Question, Tag, Answer])
class AnswerControllerTests {
    @Before
    void setUp() {
        def user = new User(email: "testEmail", displayName: "testDisplayName").save(failOnError: true)
        def q1 = new Question(title: 'test', content: "test", dateCreated: new Date(), user: user).save(failOnError: true)
        def q2 = new Question(title: 'test', content: "test", dateCreated: new Date(), user: user).save(failOnError: true)
        def a1 = new Answer(content: "test", question: q1, user: user).save(failOnError: true)

        this.controller.params.answer = a1.id

        this.controller.answerService = new AnswerService()
        this.controller.sessionService = new SessionService()
    }

    def testEditNoSessionUser() {
        this.controller.edit()

        assert this.controller.response.redirectUrl == "/question/index"
    }

    def testEditAnswerNoSessionUser() {
        this.controller.editAnswer()

        assert this.controller.response.redirectUrl == "/question/index"
    }
}
