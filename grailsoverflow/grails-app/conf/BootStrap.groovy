import fr.isima.grailsoverflow.AppConfig
import fr.isima.grailsoverflow.Question
import fr.isima.grailsoverflow.Tag
import fr.isima.grailsoverflow.User
import fr.isima.grailsoverflow.Vote
import fr.isima.grailsoverflow.Answer
import grails.util.GrailsUtil

class BootStrap {
    def questionService
    def voteService
    def answerService
    def userService
    def tagService

    def init = { servletContext ->
        switch (GrailsUtil.environment) {
            case "development":
                User admin = userService.createUser("admin@admin.com", "Admin")
                User floyd = userService.createUser("florian.rotagnon@gmail.com", "florian.rotagnon@gmail.com")
                floyd.admin = true;
                //floyd.ban = true;

                Tag groovy = tagService.createTag("groovy")
                Tag unused = tagService.createTag("unused")
                Tag grails = tagService.createTag("grails")
                Tag jenkins = tagService.createTag("jenkins")

                def question = questionService.addQuestion(
                    "Is Groovy a good way deploy a web app ?",
                    "<p>Hi,</p>\
                     <p></p>\
                     <p>I would like to know how to create a simple Controller in Groovy</p>\
                     <p></p>\
                     <p>Thanks.</p>",
                    "groovy",
                    floyd
                )

                question = questionService.addQuestion(
                    "How to create a simple Controller in Groovy ?",
                    "<p>Hi,</p>\
                     <p></p>\
                     <p>I would like to know if groovy was a good way to deploy a web app</p>\
                     <p></p>\
                     <p>Thanks.</p>",
                    "groovy",
                    admin
                )

                question = questionService.addQuestion(
                        "What's the best coutinuous integration solution to deploy a grails application ?",
                        "<p>Hi,</p>\
                         <p></p>\
                         <p>I would like to know what's the best coutinuous integration solution to deploy a grails application</p>\
                         <p></p>\
                         <p>Thanks.</p>",
                        "jenkins,grails",
                        admin
                )

                def answer = questionService.answerToQuestion(
                    question.id,
                    "<p>I would advise <strong>Jenkins</strong> !</p>",
                    admin
                )

                voteService.changeUserVote(answer, admin, Vote.VOTE_UP)
                voteService.changeUserVote(answer, floyd, Vote.VOTE_UP)
                answerService.acceptAnswer(answer.id)
                questionService.updateStatus(question)

                answer = questionService.answerToQuestion(
                        question.id,
                        "<p>Maybe travis-ci would be better <a href='https://travis-ci.org/'>https://travis-ci.org/</a></p>",
                        floyd
                )

                voteService.changeUserVote(answer, floyd, Vote.VOTE_UP)

                question = questionService.addQuestion(
                        "Is a template difficult to implement in Grails ?",
                        "<p>Hi,</p>\
                         <p></p>\
                         <p>I would like to know if it is difficult to implement a template in grails</p>\
                         <p></p>\
                         <p>Thanks.</p>",
                        "grails",
                        admin
                )
                break;
        }
    }

    def destroy = {
    }
}
