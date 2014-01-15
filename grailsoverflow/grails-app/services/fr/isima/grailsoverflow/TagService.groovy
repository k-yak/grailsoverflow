package fr.isima.grailsoverflow

class TagService {
    static transactional = true

    def getTagByName(String tagName) {
        Tag.findByName(tagName)
    }

    def createTag(def name) {
        new Tag(name: name).save(failOnError: true)
    }
}
