package fr.isima.grailsoverflow

class UserService {

    def getUserById(def userId) {
        User.findById(userId)
    }

    def getUserByEmail(def email) {
        User.findByEmail(email)
    }

    def createUser(def email, def displayName) {
        new User(email: email, displayName: displayName).save(failOnError: true)
    }

    def updateUser(def userId, def displayName, def website, def location) {
        User user = getUserById(userId)

        user.setDisplayName(displayName)
        user.setWebsite(website)
        user.setLocation(location)

        user.save(failOnError: true)
    }
}
