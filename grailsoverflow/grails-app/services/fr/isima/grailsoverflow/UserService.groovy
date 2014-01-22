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
        def tags = user.favoriteTags

        if (!tagsAsString.isEmpty()) {
            tags?.clear()

            def newTags = tagsAsString.split(',')
            newTags.each() { tagName ->
                tagName = tagName.toLowerCase()
                Tag tag = Tag.findByName(tagName) ?: new Tag(name: tagName).save(failOnError: true)
                user.addToFavoriteTags(tag)
            }
        }

        user.save(failOnError: true)
    }
}
