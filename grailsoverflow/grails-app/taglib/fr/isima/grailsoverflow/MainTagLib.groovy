package fr.isima.grailsoverflow

class MainTagLib {
    static namespace = "own"
    
    def redirectIndex = {
        response.sendRedirect("${request.contextPath}/question")
    }
}