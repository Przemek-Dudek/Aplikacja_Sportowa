package org.springdb.wrapper;

public class EventWrapper {
    private String name;
    private String description;
    private String team1;
    private String team2;

    public EventWrapper(String name, String description, String team1, String team2) {
        this.name = name;
        this.description = description;
        this.team1 = team1;
        this.team2 = team2;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }
}
