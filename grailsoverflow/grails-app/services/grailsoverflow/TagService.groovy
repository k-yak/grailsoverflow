package grailsoverflow

import fr.isima.grailsoverflow.Tag

class TagService {
    static transactional = true

    def getTagByName(String tagName) {
        Tag.findByName(tagName)
    }
}
