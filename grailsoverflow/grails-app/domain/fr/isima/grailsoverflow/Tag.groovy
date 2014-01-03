package fr.isima.grailsoverflow

class Tag {
    String name

    String toString() { 
        return name 
    }
    
    static hasMany = [questions: Question, users: User]
    static belongsTo = [Question, User]

    static constraints = {
        name blank: false, unique: true
    }
    
    static List sortByQuestionsCount(int max = Integer.MAX_VALUE, String sortOrder = "desc") {
        return Tag.executeQuery("""
        	SELECT tag
        	FROM Tag tag
        	ORDER BY size(tag.questions) ${sortOrder}
    		""", [max: max])
    }
}
