package fr.isima.grailsoverflow

class UserService {

    def getUserById(def userId) {
        User.findById(userId)
    }
}
