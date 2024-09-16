package org.springdb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "teams")
public class Team {
    @Id
    private String id;
    private String name;
    private Integer elo;
    private List<Profile> members;

    public Team(String name) {
        this.name = name;
        members = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getElo() {
        return elo;
    }

    public List<Profile> getMembers() {
        return members;
    }

    public Integer getMemberCount() {
        return members.size();
    }

    public void addMember(Profile member) {
        members.add(member);
        calculateElo();
    }

    public void removeMember(Profile member) {
        for(Profile p : members) {
            if(p.getId().equals(member.getId())) {
                members.remove(p);
                break;
            }
        }
        calculateElo();
    }

    public void calculateElo() {
        int totalElo = 0;
        for (Profile member : members) {
            totalElo += member.getElo();
        }

        this.elo = totalElo / members.size();
    }
}
