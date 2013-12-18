
import fr.isima.grailsoverflow.Question
import fr.isima.grailsoverflow.Tag

class BootStrap {

    def init = { servletContext ->
        Tag groovy = new Tag(name: "groovy").save(failOnError: true)
        
        def question = new Question(
            title: "How to create a simple Controller in Groovy ?",
            summary: "Hi, \nI would like to know how to create a simple Controller in Groovy\n\
                      \n\
                      Thanks.",
            dateCreated: new Date(),
            status: "Unanswered"
        )
        question.addToTags(groovy)
        question.save(failOnError: true)
    }
    def destroy = {
    }
}
