
import grailsoverflow.Question

class BootStrap {

    def init = { servletContext ->
        new Question(author:"Kevin", title:"How can I create  a Groovy Project ?").save()
        new Question(author:"Florian", title:"Can you explain Kevin how to do :( ?").save()
    }
    def destroy = {
    }
}
