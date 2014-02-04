package fr.isima.grailsoverflow

class UserController {

    def userService
    def sessionService

    def show() {
        // call userService to get the user corresponding to params.id
        User user = userService.getUserById(params.id)

        if (user.email != session.user.email) {
            userService.addProfileVisit(user);
        }

        if(user == null) {
            //error user not found
            redirect(controller: "question", action: "index")
        }

        return [user: user]
    }

    def edit() {
        // call userService to get the user corresponding to params.id
        User user = userService.getUserById(params.id)

        if(session.user == null ||( session.user.email != user?.email && session.user.admin == false )) {
            sessionService.addMessage("danger", "grow.error.access.forbidden")
            log.warn "WARNING : Address ${request.getRemoteAddr()} try to edit user ${params.id} but do not have rights"
            redirect(controller: "question", action: "index")
        }

        return [user: user]
    }

    def ban() {
        User user = userService.getUserById(params.id)
        def textButton
        //auto-ban impossible
        if(session.user == null || session.user.admin == false || user.id == session.user.id ) {
            sessionService.addMessage("danger", "grow.error.access.forbidden")
            log.warn "WARNING : Address ${request.getRemoteAddr()} try to ban user ${params.id} but do not have rights"
            redirect(controller: "question", action: "index")
        }
        if(!user.ban)
        {
            textButton="Unban"
            userService.banUser(params.id)
        }
        else
        {
            textButton="Ban"
            userService.unbanUser(params.id)
        }

        render(text:textButton, contentType:'text/html')
    }

    //post call by form
    def editInfo() {
        if(session.user == null || (params.id != session.user.id && session.user.admin == false)) {
            sessionService.addMessage("danger", "grow.error.access.forbidden")
            log.warn "WARNING : Address ${request.getRemoteAddr()} try to edit user ${params.id} but do not have rights"
            redirect(controller: "question", action: "index")
        }

        userService.updateUser(session.user.id, params.displayName, params.website, params.location, params.tags)
        redirect(controller: "user", action: "show", id: params.id )
    }


}
