package fr.isima.grailsoverflow

class MainTagLib {
    static namespace = "own"
    
    def redirectIndex = {
        response.sendRedirect("${request.contextPath}/question")
    }

    def oneLineContent = { attrs ->
        def content = attrs.content

        content = content.replaceAll("<p>", " ").replaceAll("</p>", " ").replaceAll("<br />", " ")
        content = content.trim().replaceAll(" +", " ")

        out << content
    }
}