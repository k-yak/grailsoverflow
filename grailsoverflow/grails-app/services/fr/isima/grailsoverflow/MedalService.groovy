package fr.isima.grailsoverflow

class MedalService {

    def testVisitMedals(User user) {
        if (user.profileView == AppConfig.BRONZE_VISITS) {
            user.addToMedals(new Medal(Medal.BRONZE, "grow.medal.bronzeVisits", AppConfig.BRONZE_VISITS))
        } else if (user.profileView == AppConfig.SILVER_VISITS) {
            user.addToMedals(new Medal(Medal.SILVER, "grow.medal.silverVisits", AppConfig.SILVER_VISITS))
        } else if (user.profileView == AppConfig.GOLD_VISITS) {
            user.addToMedals(new Medal(Medal.GOLD, "grow.medal.goldVisits", AppConfig.GOLD_VISITS))
        }
    }

    def testScoreMedals(User user) {
        Medal medal;
        if (user.score >= AppConfig.GOLD_SCORE) {
            medal = new Medal(Medal.GOLD, "grow.medal.goldScore", AppConfig.GOLD_SCORE)
            if (!user.haveMedal(medal))
                user.addToMedals(medal)
        } else if (user.score >= AppConfig.SILVER_SCORE) {
            medal = new Medal(Medal.SILVER, "grow.medal.silverScore", AppConfig.SILVER_SCORE)
            if (!user.haveMedal(medal))
                user.addToMedals(medal)
        } else if (user.score >= AppConfig.BRONZE_SCORE) {
            medal = new Medal(Medal.BRONZE, "grow.medal.bronzeScore", AppConfig.BRONZE_SCORE)
            if (!user.haveMedal(medal))
                user.addToMedals(medal)
        }
    }

    def testConnectionsMedals(User user) {
        Medal medal;
        if (user.connectionCounter >= AppConfig.GOLD_CONNECTIONS) {
            medal = new Medal(Medal.GOLD, "grow.medal.goldConnections", AppConfig.GOLD_CONNECTIONS)
            if (!user.haveMedal(medal))
                user.addToMedals(medal)
        } else if (user.connectionCounter >= AppConfig.SILVER_CONNECTIONS) {
            medal = new Medal(Medal.SILVER, "grow.medal.silverConnections", AppConfig.SILVER_CONNECTIONS)
            if (!user.haveMedal(medal))
                user.addToMedals(medal)
        } else if (user.connectionCounter >= AppConfig.BRONZE_CONNECTIONS) {
            medal = new Medal(Medal.BRONZE, "grow.medal.bronzeConnections", AppConfig.BRONZE_CONNECTIONS)
            if (!user.haveMedal(medal))
                user.addToMedals(medal)
        }
    }

    def testQuestionsMedals(User user) {
        Medal medal;
        if (user.questions.size() >= AppConfig.GOLD_QUESTIONS) {
            medal = new Medal(Medal.GOLD, "grow.medal.goldQuestions", AppConfig.GOLD_QUESTIONS)
            if (!user.haveMedal(medal))
                user.addToMedals(medal)
        } else if (user.questions.size() >= AppConfig.SILVER_QUESTIONS) {
            medal = new Medal(Medal.SILVER, "grow.medal.silverQuestions", AppConfig.SILVER_QUESTIONS)
            if (!user.haveMedal(medal))
                user.addToMedals(medal)
        } else if (user.questions.size() >= AppConfig.BRONZE_QUESTIONS) {
            medal = new Medal(Medal.BRONZE, "grow.medal.bronzeQuestions", AppConfig.BRONZE_QUESTIONS)
            if (!user.haveMedal(medal))
                user.addToMedals(medal)
        }
    }
}
