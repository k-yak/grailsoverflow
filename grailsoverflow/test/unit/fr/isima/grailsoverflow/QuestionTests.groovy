package fr.isima.grailsoverflow

import grails.test.mixin.*
import grails.test.mixin.domain.DomainClassUnitTestMixin
import org.junit.*

@TestFor(Question)
@Mock([User, Tag, Question, Answer])
class QuestionTests {
    User testUser = new User(email: "testEmail", displayName: "testDisplayName")
    Tag testTag = new Tag(name: "groovy")

    void testAcceptedQuestion() {
        def question = new Question(
                title: "titleTest",
                content: "contentTest",
                dateCreated: new Date(),
                user: testUser
        )

        def answer = new Answer(
                content: "testContent",
                dateCreated: new Date(),
                user: testUser,
                question: question
        )

        question.answer(answer)

        assert question.acceptedAnswer() == null

        answer.accept()

        assert question.acceptedAnswer() == answer
    }

    void testStatus() {
        def question = new Question(
                title: "titleTest",
                content: "contentTest",
                dateCreated: new Date(),
                user: testUser
        )

        def answer = new Answer(
                content: "testContent",
                dateCreated: new Date(),
                user: testUser,
                question: question
        )

        assert question.status == "Unanswered"

        question.answer(answer)

        assert question.status == "Answered"

        answer.accept()

        assert question.status == "Accepted"
    }

}
