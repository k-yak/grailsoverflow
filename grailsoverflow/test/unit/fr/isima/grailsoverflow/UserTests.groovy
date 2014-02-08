package fr.isima.grailsoverflow

import grails.test.mixin.*
import org.junit.*

@TestFor(User)
@Mock([User, Tag, Question, Medal])
class UserTests {
    def user = new User(email: "testEmail", displayName: "testDisplayName")
    def user2 = new User(email: "testEmail2", displayName: "testDisplayName")
    def question = new Question(title: "test", content: "test", user: user, dateCreated: new Date())
    def question2 = new Question(title: "test", content: "test", user: user2, dateCreated: new Date())
    def answer = new Answer(content: "test", user: user, question: question)
    def answer2 = new Answer(content: "test", user: user2, question: question)
    Tag testTag = new Tag(name: "testTag")
    Tag testTag2 = new Tag(name: "testTag2")
    def bronze1 = new Medal(type: Medal.BRONZE, title: "bronze1")
    def bronze2 = new Medal(type: Medal.BRONZE, title: "bronze2")

    void testFavoriteTagsToString() {
        user.addToFavoriteTags(testTag)
        user.addToFavoriteTags(testTag2)

        assert (user.favoriteTagsToString() == "${testTag.name},${testTag2.name}") ||
                (user.favoriteTagsToString() == "${testTag2.name},${testTag.name}")
    }

    void testIsOwnerOfQuestion() {
        user.addToQuestions(question)

        assert user.isOwnerOfQuestion(question) == true
        assert user.isOwnerOfQuestion(question2) == false
    }

    void testIsOwnerOfAnswer() {
        user.addToQuestions(question)

        assert user.isOwnerOfAnswer(answer) == true
        assert user.isOwnerOfAnswer(answer2) == false
    }

    void testCountMedalOfType() {
        user.addToMedals(bronze1)
        user.addToMedals(bronze2)

        assert user.countMedalsOfType(Medal.BRONZE) == 2
        assert user.countMedalsOfType(Medal.SILVER) == 0
    }
}
