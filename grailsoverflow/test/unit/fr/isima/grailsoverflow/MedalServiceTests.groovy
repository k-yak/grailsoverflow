package fr.isima.grailsoverflow

import grails.test.mixin.*
import org.junit.*

@TestFor(MedalService)
@Mock([User, Question, Tag, Answer, HistoryElement, Medal])
class MedalServiceTests {
    def medalService = new MedalService()

    @Before
    void setUp() {
        medalService.historyService = new HistoryService()
    }

    def testVisitMedals() {
        def user = new User(email: "testVisitMedals", displayName: "testVisitMedals").save(failOnError: true)

        user.profileView = AppConfig.BRONZE_VISITS
        assert user.countMedalsOfType(Medal.BRONZE) == 0
        medalService.testVisitMedals(user)

        user.profileView = AppConfig.SILVER_VISITS
        assert user.countMedalsOfType(Medal.SILVER) == 0
        medalService.testVisitMedals(user)

        user.profileView = AppConfig.GOLD_VISITS
        assert user.countMedalsOfType(Medal.GOLD) == 0
        medalService.testVisitMedals(user)

        assert user.countMedalsOfType(Medal.BRONZE) == 1
        assert user.countMedalsOfType(Medal.SILVER) == 1
        assert user.countMedalsOfType(Medal.GOLD) == 1
    }

    def testScoreMedals() {
        def user = new User(email: "testScoreMedals", displayName: "testScoreMedals").save(failOnError: true)

        user.score = AppConfig.BRONZE_SCORE
        assert user.countMedalsOfType(Medal.BRONZE) == 0
        medalService.testScoreMedals(user)

        user.score = AppConfig.SILVER_SCORE
        assert user.countMedalsOfType(Medal.SILVER) == 0
        medalService.testScoreMedals(user)

        user.score = AppConfig.GOLD_SCORE
        assert user.countMedalsOfType(Medal.GOLD) == 0
        medalService.testScoreMedals(user)

        assert user.countMedalsOfType(Medal.BRONZE) == 1
        assert user.countMedalsOfType(Medal.SILVER) == 1
        assert user.countMedalsOfType(Medal.GOLD) == 1
    }

    def testConnectionsMedals() {
        def user = new User(email: "testConnectionsMedals", displayName: "testConnectionsMedals").save(failOnError: true)

        user.connectionCounter = AppConfig.BRONZE_CONNECTIONS
        assert user.countMedalsOfType(Medal.BRONZE) == 0
        medalService.testConnectionsMedals(user)

        user.connectionCounter = AppConfig.SILVER_CONNECTIONS
        assert user.countMedalsOfType(Medal.SILVER) == 0
        medalService.testConnectionsMedals(user)

        user.connectionCounter = AppConfig.GOLD_CONNECTIONS
        assert user.countMedalsOfType(Medal.GOLD) == 0
        medalService.testConnectionsMedals(user)

        assert user.countMedalsOfType(Medal.BRONZE) == 1
        assert user.countMedalsOfType(Medal.SILVER) == 1
        assert user.countMedalsOfType(Medal.GOLD) == 1
    }
}
