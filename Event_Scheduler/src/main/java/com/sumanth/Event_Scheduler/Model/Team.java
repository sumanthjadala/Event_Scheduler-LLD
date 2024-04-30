package com.sumanth.Event_Scheduler.Model;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private int teamId;
    private String teamName;
    private List<User> teamMembers=new ArrayList<>();
    private List<Event> events=new ArrayList<>();

    public Team(int teamId, String teamName) {
        this.teamId = teamId;
        this.teamName = teamName;
    }

    public int getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public List<User> getTeamMembers() {
        return teamMembers;
    }

    public void addTeamMembers(User teamMember) {
        this.teamMembers.add(teamMember);
    }

    public List<Event> getEvents() {
        return events;
    }

    public void addEvents(Event event) {
        this.events.add(event);
    }
    public void clearEvents(){
        events.clear();
    }
}
