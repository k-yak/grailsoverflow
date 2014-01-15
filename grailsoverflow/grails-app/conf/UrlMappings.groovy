class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?"{
            constraints {
            }
        }

        // QuestionController
        "/question/tag/$tag"(controller:"question", action:"questionsForTag")
        "/question/show/$question"(controller:"question", action:"showQuestion")
        "/question/delete/$question"(controller:"question", action:"delete")
        "/question/edit/$question"(controller:"question", action:"edit")
        
        // Unaccepted
        "/unaccepted/tag/$tag"(controller:"unaccepted", action:"questionsForTag")
        
        // Answer
        "/answer/delete/$answer"(controller:"answer", action:"delete")
        "/answer/edit/$answer"(controller:"answer", action:"edit")

        // User
        "/user/show/$id"(controller:"user", action:"show")
        "/user/edit/$id"(controller:"user", action:"edit")

        "/"(view:"/index")
        "500"(view:'/error')
    }
}
