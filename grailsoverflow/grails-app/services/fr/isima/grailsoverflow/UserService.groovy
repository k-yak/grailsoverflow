package fr.isima.grailsoverflow

class UserService {
    def sessionService

    def getUserById(def userId) {
        User.findById(userId)
    }

    def getUserByEmail(def email) {
        User.findByEmail(email)
    }

    def createUser(def email, def displayName) {
        new User(email: email, displayName: displayName).save(failOnError: true)
    }

    def updateUser(def userId, def displayName, def website, def location, def stringTags) {
        User user = getUserById(userId)

        user.setDisplayName(displayName)
        user.setWebsite(website)
        user.setLocation(location)

        newFavoriteTagsFromString(user, stringTags)

        sessionService.reloadUserSession()
    }

    def newFavoriteTagsFromString(User user, String tagsAsString) {
        user.favoriteTags = []

        if (!tagsAsString.isEmpty()) {
            def newTags = tagsAsString.split(',')
            newTags.each() { tagName ->
                tagName = tagName.toLowerCase()
                Tag tag = Tag.findByName(tagName) ?: new Tag(name: tagName).save(failOnError: true)
                user.addToFavoriteTags(tag)
            }
        }

        user.save(failOnError: true)
    }

    def banUser(def userId) {
        User user = getUserById(userId)
        user.ban = true
        user.save()
    }

    def unbanUser(def userId) {
        User user = getUserById(userId)
        user.ban = false
        user.save()
    }

    def testVisitMedals(User user) {
        if (user.profileView == AppConfig.BRONZE_VISITS) {
            user.addToMedals(new Medal(Medal.BRONZE, "grow.medal.bronzeVisits"))
        } else if (user.profileView == AppConfig.SILVER_VISITS) {
            user.addToMedals(new Medal(Medal.SILVER, "grow.medal.silverVisits"))
        } else if (user.profileView == AppConfig.GOLD_VISITS) {
            user.addToMedals(new Medal(Medal.GOLD, "grow.medal.goldVisits"))
        }
    }

    def testScoreMedals(User user) {
        Medal medal;
        if (user.score >= AppConfig.BRONZE_SCORE) {
            medal = new Medal(Medal.BRONZE, "grow.medal.bronzeScore")
            if (!user.haveMedal(medal))
                user.addToMedals(medal)
        } else if (user.score >= AppConfig.SILVER_SCORE) {
            medal = new Medal(Medal.SILVER, "grow.medal.silverScore")
            if (!user.haveMedal(medal))
                user.addToMedals(medal)
        } else if (user.score >= AppConfig.GOLD_SCORE) {
            medal = new Medal(Medal.GOLD, "grow.medal.goldScore")
            if (!user.haveMedal(medal))
                user.addToMedals(medal)
        }
    }

    def addProfileVisit(User user) {
        ++user.profileView;

        testVisitMedals(user)

        user.save(failOnError: true)
        sessionService.reloadUserSession()
    }
}
