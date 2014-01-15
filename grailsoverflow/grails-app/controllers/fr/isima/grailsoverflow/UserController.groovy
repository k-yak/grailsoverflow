package fr.isima.grailsoverflow

class UserController {

    def userService

    def show() {
        // call userService to get the user corresponding to params.id
        User user = userService.getUserById(params.id)

        if(user == null) {
            //error user not found
            redirect(controller: "question", action: "index")
        }

        return [user: user]
    }

    def edit() {
        // call userService to get the user corresponding to params.id
        User user = userService.getUserById(params.id)

        if(session.user == null || session.user.email != user?.email) {
            redirect(controller: "question", action: "index")
        }

        return [user: user]
    }

    //post call by form
    def editInfo() {
        if(session.user == null) {
            redirect(controller: "question", action: "index")
        }

        userService.updateUser(session.user.id, params.displayName, params.website, params.location)

        redirect(controller: "user", action: "show", id: session.user.id )
    }


}
