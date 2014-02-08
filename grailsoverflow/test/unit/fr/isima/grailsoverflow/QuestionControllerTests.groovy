package fr.isima.grailsoverflow

import grails.test.mixin.*
import org.junit.*

@TestFor(QuestionController)
@Mock([User, Question, Tag])
class QuestionControllerTests {
    def q1
    def q2

    @Before
    void setUp() {
        Tag testTag = new Tag(name: "testTag").save(failOnError: true)
        def user = new User(email: "testEmail", displayName: "testDisplayName").save(failOnError: true)
        q1 = new Question(title: 'test', content: "test", dateCreated: new Date(), user: user).save(failOnError: true)
        q2 = new Question(title: 'test', content: "test", dateCreated: new Date(), user: user).save(failOnError: true)

        q1.addToTags(testTag)
        q1.save(failOnError: true)

        this.controller.params.offset = 0
        this.controller.params.max = 2
        this.controller.params.tag = testTag.name
        this.controller.params.question = q1.id

        this.controller.questionService = new QuestionService()
        this.controller.sessionService = new SessionService()
        this.controller.userService = new UserService()
        this.controller.tagService = new TagService()
    }

    def testIndex() {
        // [questionsToDisplay: latestQuestionsPaginate, completeQuestionList: completeQuestionList, completePaginationList: completeQuestionList, tags: tags, subtitle: subtitle, user: user]
        def model = this.controller.index()

        assert model['questionsToDisplay']?.size() == 2
    }

    def testShowQuestion() {
        def model = this.controller.showQuestion()

        assert model['question'] == q1
    }

    def testAnswerNoSessionUser() {
        this.controller.answer()

        assert this.controller.response.redirectUrl == "/question/index"
    }

    def testAddQuestionNoSessionUser() {
        this.controller.addQuestion()

        assert this.controller.response.redirectUrl == "/question/index"
    }
}
