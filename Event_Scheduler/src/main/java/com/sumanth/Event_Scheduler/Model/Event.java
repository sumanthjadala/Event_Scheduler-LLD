package com.sumanth.Event_Scheduler.Model;


import java.time.LocalTime;

public class Event {
    private String eventName;
    private LocalTime eventStartTime;
    private LocalTime eventEndTime;

    public Event(String eventName, LocalTime eventStartTime, LocalTime eventEndTime) {
        this.eventName = eventName;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
    }
    public String getEventName() {
        return eventName;
    }

    public LocalTime getEventStartTime() {
        return eventStartTime;
    }

    public LocalTime getEventEndTime() {
        return eventEndTime;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventName='" + eventName + '\'' +
                ", eventStartTime=" + eventStartTime +
                ", eventEndTime=" + eventEndTime +
                '}';
    }
}
