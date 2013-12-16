package fr.isima.grailsoverflow

class SomePeople {
    def name
}

class TestController {

    def index() {
        SomePeople sp = new SomePeople(name: "A name")
        SomePeople sp2 = new SomePeople(name: "A name 2")
        [SomePeople : sp, SomePeople2 : sp2]
    }
    
    def list() {
        def list = [];
        
        list << new SomePeople(name : "People 1")
        list << new SomePeople(name : "People 2")
        
        [list : list]
    }
}
