package fr.isima.grailsoverflow

import grails.test.mixin.*
import grails.test.mixin.domain.DomainClassUnitTestMixin
import org.junit.*

@TestFor(Question)
@Mock([User, Tag, Question, Answer])
class QuestionTests {

    User testUser = new User(email: "testEmail", displayName: "testDisplayName")
    Tag testTag = new Tag(name: "testTag")
    Tag testTag2 = new Tag(name: "testTag2")

    void testTagsToString() {
        def question = new Question(title: "titleTest", content: "contentTest", dateCreated: new Date(), user: testUser)
        question.addToTags(testTag)
        question.addToTags(testTag2)

        assert (question.tagsToString() == "${testTag.name},${testTag2.name}") ||
               (question.tagsToString() == "${testTag2.name},${testTag.name}")
    }

    void testVoteSortedAnswers() {
        def question = new Question(title: "titleTest", content: "contentTest", dateCreated: new Date(), user: testUser)

        def answer1 = new Answer(content: "answer1", dateCreated: new Date(), user: testUser, question: question)
        def answer2 = new Answer(content: "answer2", dateCreated: new Date(), user: testUser, question: question)

        question.addToAnswers(answer2)
        question.addToAnswers(answer1)

        assert question.answers.size() == 2
        assert question.sortedAnswers().size() == 2

        answer1.vote.value = 2
        answer2.vote.value = 1

        assert question.sortedAnswers().first().content == "answer1"

        answer1.vote.value = 1
        answer2.vote.value = 2

        assert question.sortedAnswers().first().content == "answer2"
    }
}
