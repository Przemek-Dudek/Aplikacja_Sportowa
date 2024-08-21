package org.springdb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "profiles")
public class Profile {

    @Id
    private String id;
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private Integer trustLevel;
    private Integer elo;

    public static final Integer TRUST_LEVEL_FOR_NEW_USER = 1000;
    public static final Integer ELO_FOR_NEW_USER = 1000;

    public Profile(String login, String firstName, String lastName, String email) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.trustLevel = TRUST_LEVEL_FOR_NEW_USER;
        this.elo = ELO_FOR_NEW_USER;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTrustLevel() {
        return trustLevel;
    }

    public void setTrustLevel(Integer trustLevel) {
        this.trustLevel = trustLevel;
    }

    public Integer getElo() {
        return elo;
    }

    public void setElo(Integer elo) {
        this.elo = elo;
    }

    public void increaseElo(Integer elo) {
        this.elo += elo;
    }

    public void increaseTrustLevel(Integer trustLevel) {
        this.trustLevel += trustLevel;
    }
}
