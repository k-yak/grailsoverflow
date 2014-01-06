import fr.isima.grailsoverflow.AppConfig
import fr.isima.grailsoverflow.Question
import fr.isima.grailsoverflow.Tag
import fr.isima.grailsoverflow.User
import fr.isima.grailsoverflow.Vote
import fr.isima.grailsoverflow.Answer
import grails.util.GrailsUtil

class BootStrap {

    def init = { servletContext ->
        switch (GrailsUtil.environment) {
        case "development":
            User admin = new User(email: "kevin.renella@gmail.com", displayName: "kevin.renella@gmail.com").save(failOnError: true)
            User floyd = new User(email: "florian.rotagnon@gmail.com", displayName: "florian.rotagnon@gmail.com").save(failOnError: true)
        
            Tag groovy = new Tag(name: "groovy").save(failOnError: true)
            Tag unused = new Tag(name: "unused").save(failOnError: true)
            Tag grails = new Tag(name: "grails").save(failOnError: true)
            Tag jenkins = new Tag(name: "jenkins").save(failOnError: true)
        
            def question = new Question(
                title: "How to create a simple Controller in Groovy ?",
                content: "<p>Hi,</p>\
                      <p></p>\
                      <p>I would like to know how to create a simple Controller in Groovy</p>\
                      <p></p>\
                      <p>Thanks.</p>",
                dateCreated: new Date(),
                user: floyd
            )
            question.user.score += AppConfig.QUESTION_SCORE
            question.addToTags(groovy)
            question.save(failOnError: true)
        
            question = new Question(
                title: "Is Groovy a good way deploy a web app ?",
                content: "<p>Hi,</p>\
                      <p></p>\
                      <p>I would like to know if groovy was a good way to deploy a web app</p>\
                      <p></p>\
                      <p>Thanks.</p>",
                dateCreated: new Date(),
                user: admin
            )
            question.user.score += AppConfig.QUESTION_SCORE
            question.addToTags(groovy)
            question.save(failOnError: true)
        
            question = new Question(
                title: "What's the best coutinuous integration solution to deploy a grails application ?",
                content: "<p>Hi,</p>\
                      <p></p>\
                      <p>I would like to know what's the best coutinuous integration solution to deploy a grails application</p>\
                      <p></p>\
                      <p>Thanks.</p>",
                dateCreated: new Date(),
                user: admin
            )
            question.user.score += AppConfig.QUESTION_SCORE
            question.addToTags(jenkins)
            question.addToTags(grails)
            question.save(failOnError: true)
        
            Answer answer = new Answer(
                content: "<p>I would advise <strong>Jenkins</strong> !</p>",
                dateCreated: new Date(),
                user: admin,
                question: question
            )
            answer.vote.changeUserVote(admin, Vote.VOTE_UP)
            answer.vote.changeUserVote(floyd, Vote.VOTE_UP)
            answer.save(failOnError: true)
            question.answer(answer)
            answer.accept();
        
            answer = new Answer(
                content: "<p>Maybe travis-ci would be better <a href='https://travis-ci.org/'>https://travis-ci.org/</a></p>",
                dateCreated: new Date(),
                user: floyd,
                question: question
            )
            answer.save(failOnError: true)
            answer.vote.changeUserVote(floyd, Vote.VOTE_UP)
            question.answer(answer)
            question.save(failOnError: true)
        
            question = new Question(
                title: "Is a template difficult to implement in Grails ?",
                content: "<p>Hi,</p>\
                      <p></p>\
                      <p>I would like to know if it is difficult to implement a template in grails</p>\
                      <p></p>\
                      <p>Thanks.</p>",
                dateCreated: new Date(),
                user: admin
            )
            question.user.score += AppConfig.QUESTION_SCORE
            question.addToTags(grails)
            question.save(failOnError: true)
            break;
        }
    }
    def destroy = {
    }
}
