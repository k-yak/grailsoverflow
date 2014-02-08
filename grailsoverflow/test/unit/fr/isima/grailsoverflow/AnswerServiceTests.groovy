package fr.isima.grailsoverflow

import grails.test.mixin.*
import org.junit.Before

@TestFor(AnswerService)
@Mock([User, Question, Tag, Answer, HistoryElement])
class AnswerServiceTests {
    def answerService = new AnswerService()
    def user, q1, a1

    @Before
    void setUp() {
        user = new User(email: "testEmail", displayName: "testDisplayName").save(failOnError: true)
        q1 = new Question(title: 'test', content: "test", dateCreated: new Date(), user: user).save(failOnError: true)
        a1 = new Answer(content: "test", question: q1, user: user)

        answerService.sessionService = new SessionService()
        answerService.historyService = new HistoryService()
        answerService.medalService = new MedalService()
    }

    def testCreateAnswer() {
        def answer = answerService.createAnswer(a1.content, user, q1)

        assert answer.content == a1.content
        assert answer.question.title == q1.title
        assert answer.user.email == a1.user.email
    }


    def testAcceptAnswer() {
        def answer = answerService.createAnswer(a1.content, user, q1)

        assert answer.accepted == false

        answerService.acceptAnswer(answer.id)

        assert answer.accepted == true
    }

    def testEditAnswer() {
        def answer = answerService.createAnswer(a1.content, user, q1)

        answer = answerService.editAnswer(answer.id, "newContent")

        assert answer.content == "newContent"
    }

    def testDeleteAnswer() {
        def answer = answerService.createAnswer(a1.content, user, q1)

        answerService.deleteAnswer(answer.id, user)

        assert q1.answers.findAll { it.id == answer.id }.size() == 0
    }
}
