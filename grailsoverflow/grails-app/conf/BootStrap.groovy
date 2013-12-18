
import fr.isima.grailsoverflow.Question
import fr.isima.grailsoverflow.Tag

class BootStrap {

    def init = { servletContext ->
        Tag groovy = new Tag(name: "groovy").save(failOnError: true)
        Tag grails = new Tag(name: "grails").save(failOnError: true)
        
        def question = new Question(
            title: "How to create a simple Controller in Groovy ?",
            content: "Hi, \nI would like to know how to create a simple Controller in Groovy\n\
                      \n\
                      Thanks.",
            dateCreated: new Date(),
            status: "Unanswered"
        )
        question.addToTags(groovy)
        question.save(failOnError: true)
        
        question = new Question(
            title: "Is a template difficult to implement in Grails ?",
            content: "Hi, \nI would like to know if it is difficult to implement a template in grails\n\
                      \n\
                      Thanks.",
            dateCreated: new Date(),
            status: "Unanswered"
        )
        question.addToTags(grails)
        question.save(failOnError: true)
    }
    def destroy = {
    }
}
