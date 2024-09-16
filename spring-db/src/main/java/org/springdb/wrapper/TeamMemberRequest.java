package org.springdb.wrapper;

public class TeamMemberRequest {
    private String teamId;
    private String profileId;

    public TeamMemberRequest(String teamId, String profileId) {
        this.teamId = teamId;
        this.profileId = profileId;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getProfileId() {
        return profileId;
    }
}
