
import grailsoverflow.Question

class BootStrap {

    def init = { servletContext ->
        new Question(author:"Kevin", title:"How can I create  a Groovy Project ?").save()
    }
    def destroy = {
    }
}
