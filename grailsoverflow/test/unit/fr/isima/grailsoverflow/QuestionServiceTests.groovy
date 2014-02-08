package fr.isima.grailsoverflow

import grails.test.mixin.*
import org.junit.Before

@TestFor(QuestionService)
@Mock([User, Question, Tag, Answer, HistoryElement])
class QuestionServiceTests {
    def questionService = new QuestionService()
    def user, q1, q2, a1

    @Before
    void setUp() {
        user = new User(email: "testEmail", displayName: "testDisplayName").save(failOnError: true)
        q1 = new Question(title: 'test', content: "test", dateCreated: new Date(), user: user).save(failOnError: true)
        q2 = new Question(title: 'test', content: "test", dateCreated: new Date(), user: user).save(failOnError: true)

        questionService.sessionService = new SessionService()
        questionService.answerService = new AnswerService()
        questionService.medalService = new MedalService()
        questionService.historyService = new HistoryService()

        questionService.answerService.sessionService = new SessionService()
        questionService.answerService.historyService = new HistoryService()
        questionService.answerService.medalService = new MedalService()
    }

    def testAnswerToQuestion() {
        questionService.answerToQuestion(q1.id, "testAnswer", user)

        assert !q1.answers.findAll{ it.content == "testAnswer" }.isEmpty()
    }

    def testTagsForQuestions() {
        def tag1 = new Tag(name: "test1").save(failOnError: true)
        def tag2 = new Tag(name: "test2").save(failOnError: true)
        def tag3 = new Tag(name: "test3").save(failOnError: true)

        q1.addToTags(tag1)
        q1.addToTags(tag2)

        q2.addToTags(tag3)

        def tags = questionService.tagsForQuestions([q1, q2], 3)

        assert tags.size() == 3
    }

    def testNewTagsFromString() {
        questionService.newTagsFromString(q1, "newTag")

        // Tags are case insensitives
        assert q1.tagsToString() == "newtag"
    }

    def testUpdateStatus() {
        assert q1.status == "grow.status.unanswered"

        a1 = questionService.answerToQuestion(q1.id, "Content", user)
        questionService.updateStatus(q1)

        assert q1.status == "grow.status.answered"

        questionService.answerService.acceptAnswer(a1.id)
        questionService.updateStatus(q1)

        assert q1.status == "grow.status.accepted"
    }
}
