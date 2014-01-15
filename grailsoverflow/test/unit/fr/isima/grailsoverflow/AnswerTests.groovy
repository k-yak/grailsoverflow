package fr.isima.grailsoverflow

import grails.test.mixin.*
import org.junit.*

@TestFor(Answer)
@Mock([User, Question, Answer])
class AnswerTests {
    User testUser = new User(email: "testEmail", displayName: "testDisplayName")

    def beforeDeleteTest() {
        def question = new Question(title: "titleTest", content: "contentTest", dateCreated: new Date(), user: testUser)

        def answer1 = new Answer(content: "answer1", dateCreated: new Date(), user: testUser, question: question)

        question.addToAnswers(answer2)
        question.addToAnswers(answer1)
    }
}
