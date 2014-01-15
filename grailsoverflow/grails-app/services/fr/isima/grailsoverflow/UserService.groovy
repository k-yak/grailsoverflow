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
}
