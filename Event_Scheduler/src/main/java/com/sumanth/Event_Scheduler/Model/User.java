package com.sumanth.Event_Scheduler.Model;


import com.sumanth.Event_Scheduler.SegmentTree.SegmentTree;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String userName;
    private LocalTime workingStartTime;
    private LocalTime workingEndTime;
    private List <Event> eventList=new ArrayList<>();
    private SegmentTree segmentTree=new SegmentTree();

    public User(int id, String userName, LocalTime workingStartTime, LocalTime workingEndTime) {
        this.id = id;
        this.userName = userName;
        this.workingStartTime = workingStartTime;
        this.workingEndTime = workingEndTime;
    }

    public SegmentTree getSegmentTree() {
        return segmentTree;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public LocalTime getWorkingStartTime() {
        return workingStartTime;
    }

    public LocalTime getWorkingEndTime() {
        return workingEndTime;
    }

    public List<Event> getEventList() {
        return eventList;
    }
    public void addEvent(Event event) {
        this.eventList.add(event);
    }
    public void clearEvents(){
        eventList.clear();
    }
}
