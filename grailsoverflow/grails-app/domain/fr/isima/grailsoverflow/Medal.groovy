package fr.isima.grailsoverflow

class Medal {
    static final int GOLD = 0;
    static final int SILVER = 1;
    static final int BRONZE = 2;

    int type = BRONZE;
    String title;

    Medal(def type, def title) {
        this.type = type;
        this.title = title;
    }

    static constraints = {
        title blank: false
    }
}
