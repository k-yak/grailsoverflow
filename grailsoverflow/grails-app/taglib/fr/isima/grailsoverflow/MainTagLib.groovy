package fr.isima.grailsoverflow

class MainTagLib {
    static namespace = "own"
    
    def textToParagraph = { attrs, body ->
        out << "<p>" << body().trim().replaceAll(/(\s*\n)+/, "<p></p>") << "</p>"
    }
}