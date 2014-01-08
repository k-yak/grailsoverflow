package fr.isima.grailsoverflow

class TagService {
    static transactional = true

    def getTagByName(String tagName) {
        Tag.findByName(tagName)
    }
}
