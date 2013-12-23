class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?"{
                constraints {
                        // apply constraints here
                }
        }

        // Questions
        "/question/tag/$tag"(controller:"question", action:"questionsForTags")
        "/question/show/$question"(controller:"question", action:"showQuestion")

        "/"(view:"/index")
        "500"(view:'/error')
    }
}
