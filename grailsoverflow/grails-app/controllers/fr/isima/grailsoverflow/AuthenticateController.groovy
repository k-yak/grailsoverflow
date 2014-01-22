package fr.isima.grailsoverflow

import grails.converters.*;

class AuthenticateController {

   def sessionService
 
    def success() {
        sessionService.authenticate()

        if(session.user.ban == true)
        {
            log.warn "WARNING : User ${session.user.id} try to connect but has been banned"
            sessionService.addMessage("danger", "You have been banned!")
            sessionService.logout()
            redirect(uri: "/question/index")
        }
        else
        {
            def targetUri = session.targetUri ?: "/question/index"
            sessionService.addMessage("success", "Authentication succeeded!")
            redirect(uri: targetUri)
        }
    }

    def failure() {
        render(view: "/error", model: [errorContent: "Authentication error"])
    }
    
    def logout() {
        def targetUri = session.targetUri ?: "/question/index"

        sessionService.logout()
        redirect(uri: targetUri)
    }
}