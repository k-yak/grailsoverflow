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
        
        // Unaccepted
        "/unaccepted/tag/$tag"(controller:"unaccepted", action:"questionsForTag")

        "/"(view:"/index")
        "500"(view:'/error')
    }
}
