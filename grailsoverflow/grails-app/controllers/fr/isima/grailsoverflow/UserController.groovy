package fr.isima.grailsoverflow

class UserController {

    def userService

    def index() {}

    def show() {
        // call userService to get the user corresponding to params.id
        User user = userService.getUserById(params.id)

        if(user == null)
        {
            //error user not found
            redirect(controller: "question", action: "index")
        }

        return [user: user]
    }

    def edit() {
        // call userService to get the user corresponding to params.id
        User user = userService.getUserById(params.id)

        if(session.user == null || session.user.email != user?.email)
        {
            redirect(controller: "question", action: "index")
        }

        return [user: user]
    }


}
