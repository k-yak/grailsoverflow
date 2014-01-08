package fr.isima.grailsoverflow

import grails.converters.*;

class AuthenticateController {

   def sessionService
 
    def success() {
        sessionService.authenticate()

        def targetUri = session.targetUri ?: "/question/index"
        redirect(uri: targetUri)
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