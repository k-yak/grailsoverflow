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

    void testNewTagsFromString() {
        def question = new Question(title: "titleTest", content: "contentTest", dateCreated: new Date(), user: testUser)

        question.newTagsFromString("tag1,tag1,tag2,tag2")

        assert question.tags.size() == 2
        assert question.tags*.name.contains("tag1")
        assert question.tags*.name.contains("tag2")
    }

    void testTagsForQuestions() {
        def question = new Question(title: "titleTest", content: "contentTest", dateCreated: new Date(), user: testUser)
        question.addToTags(testTag)
        question.addToTags(testTag2)

        def tags = Question.tagsForQuestions(question)

        assert tags.size() == 2
        assert tags*.name.contains("testTag")
        assert tags*.name.contains("testTag2")
    }

    void testTagsToString() {
        def question = new Question(title: "titleTest", content: "contentTest", dateCreated: new Date(), user: testUser)
        question.addToTags(testTag)
        question.addToTags(testTag2)

        assert (question.tagsToString() == "${testTag.name},${testTag2.name}") ||
               (question.tagsToString() == "${testTag2.name},${testTag.name}")
    }

    void testClearUserScore() {
        def question = new Question(title: "titleTest", content: "contentTest", dateCreated: new Date(), user: testUser)

        question.user.score += AppConfig.QUESTION_SCORE
        question.vote.value = 5
        question.user.score += AppConfig.VOTE_SCORE * question.vote.value

        question.clearUserScore()

        assert question.user.score == 0
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

    void testAcceptedQuestion() {
        def question = new Question(title: "titleTest", content: "contentTest", dateCreated: new Date(), user: testUser)
        def answer = new Answer(content: "testContent", dateCreated: new Date(), user: testUser, question: question)

        question.addToAnswers(answer)
        question.answer(answer)

        assert question.acceptedAnswer() == null

        answer.accept()

        assert question.acceptedAnswer() == answer
    }

    void testStatus() {
        def question = new Question(title: "titleTest", content: "contentTest", dateCreated: new Date(), user: testUser)
        def answer = new Answer(content: "testContent", dateCreated: new Date(), user: testUser, question: question)

        assert question.status == "Unanswered"

        question.answer(answer)
        question.addToAnswers(answer)

        assert question.status == "Answered"

        answer.accept()

        assert question.status == "Accepted"
    }

}
