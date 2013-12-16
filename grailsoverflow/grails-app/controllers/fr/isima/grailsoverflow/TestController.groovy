package fr.isima.grailsoverflow

import fr.isima.grailsoverflow.People

class TestController {

    def index() {
        People sp = new People(name: "A name")
        People sp2 = new People(name: "A name 2")
        
        People update = People.getAll()[0]
        if (update != null)
            update.name = "${sp.name} UPDATED"
        else
            update = sp;
            
        update.save()
        
        [SomePeople : update, SomePeople2 : sp2]
    }
    
    def list() {
        def list = [];
        
        list << new People(name : "People 1")
        list << new People(name : "People 2")
        
        [list : list]
    }
}
