package fr.isima.grailsoverflow

class UserService {
    static transactional = true

    def sessionService
    def medalService

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

    def addProfileVisit(User user) {
        ++user.profileView;

        medalService.testVisitMedals(user)

        user.save(failOnError: true)
        sessionService.reloadUserSession()
    }

    def getUserHistory(User user, int historyCount) {
        def history = user.history.sort { a, b ->
            a.date < b.date ? 1 : -1
        }

        history.take(historyCount)
    }
}