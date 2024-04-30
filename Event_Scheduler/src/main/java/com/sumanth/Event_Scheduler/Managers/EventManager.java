package com.sumanth.Event_Scheduler.Managers;

import com.sumanth.Event_Scheduler.Model.Event;
import com.sumanth.Event_Scheduler.Model.Team;
import com.sumanth.Event_Scheduler.Model.User;
import com.sumanth.Event_Scheduler.SegmentTree.SegmentTree;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EventManager {
    private static EventManager eventManager=null;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    private Scanner sc=new Scanner(System.in);
    public  static EventManager getInstance(){
        if(eventManager==null){
            eventManager=new EventManager();
        }
        return eventManager;
    }
    private EventManager(){}

    // one user multiple users
    public void createEvent(User curUser, User[] users,String e) {
        LocalTime start=getEventTime("start");
        LocalTime end=getEventTime("end");
        if(!checkCurrentUser(curUser,start,end)){
            return ;
        }
        if(!checkUsers(start,end, new ArrayList<>(Arrays.asList(users)))){
            return ;
        }
        Event event=new Event(e,start,end);
        updateUsers(start,end,new ArrayList<>(Arrays.asList(users)),event);
        updateCurrentUser(curUser,start,end,event);
        System.out.println(e+" meeting Scheduled  successfully");
        return ;

    }

    // one user multiple teams
    public void createEvent(User curUser, Team[] teams,String e) {
        LocalTime start=getEventTime("start");
        LocalTime end=getEventTime("end");
        if(!checkCurrentUser(curUser,start,end)){
            return ;
        }
        for(Team team:teams) {
            List<User> teamMembers =team.getTeamMembers();
            if(!checkUsers(start,end,teamMembers)){
                return ;
            }
        }
        Event event=new Event(e,start,end);
        for(Team team:teams){
            updateUsers(start,end,team.getTeamMembers(),event);
        }
        updateCurrentUser(curUser,start,end,event);
        System.out.println(e+" meeting Scheduled successfully");
    }
    private boolean checkUsers(LocalTime start,LocalTime end,List<User> users){
        for(User u:users){
            if(!checkWithInWorkingHours(u,start,end)){
                System.out.println(u.getUserName()+" does not work on the specific time meeting");
                return false;
            }
            if(!checkEvent(u,start,end)){
                System.out.println(u.getUserName()+ " has another meeting the given specific time");
                System.out.println("we could not schdule a meeting . soryy for inconvience");
                return false;
            }
        }
        return true;
    }
    private void updateUsers(LocalTime start,LocalTime end,List<User> users,Event event){
        for(User u:users){
            if(!u.getEventList().contains(event)) {
                u.getSegmentTree().update(0, 1440, start.getHour() * 60 + start.getMinute(), end.getHour() * 60 + end.getMinute()-1, 0);
                u.addEvent(event);
            }
        }
    }
    private boolean checkWithInWorkingHours(User u, LocalTime start, LocalTime end) {
        LocalTime userStartTime=u.getWorkingStartTime();
        LocalTime userEndTime=u.getWorkingEndTime();
        if((userStartTime.getHour()*60+userStartTime.getMinute())<=start.getHour()*60+start.getMinute() && (userEndTime.getHour()*60+userEndTime.getMinute()>=end.getHour()*60+end.getMinute())){
            return true;
        }
        return false;
    }

    private boolean checkEvent(User u, LocalTime start, LocalTime end) {
        SegmentTree sg=u.getSegmentTree();
        return sg.checkEventCanHappen(0,1440,start.getHour()*60+ start.getMinute(),end.getHour()*60+end.getMinute()-1,0);
    }

    private LocalTime getEventTime(String text) {
        System.out.println("Please enter your "+text+" time");
        String time=sc.nextLine();
        return LocalTime.parse(time,formatter);
    }
    private boolean checkCurrentUser(User curUser,LocalTime start,LocalTime end){
        if(!checkWithInWorkingHours(curUser,start,end)){
            System.out.println(curUser.getUserName()+" does not work on that specific time meeting");
            return false;
        }
        if(!checkEvent(curUser,start,end)){
            System.out.println("Current User "+curUser.getUserName()+" has another meeting the given specific time");
            return  false;
        }
        return true;
    }
    private void updateCurrentUser(User curUser,LocalTime start, LocalTime end,Event event) {
        curUser.getSegmentTree().update(0,1440,start.getHour()*60+start.getMinute(),end.getHour()*60+end.getMinute()-1,0);
        if(!curUser.getEventList().contains(event)) {
            curUser.addEvent(event);
        }
    }


    public void createEvent(Team curTeam, Team[] teams, String e) {
        LocalTime start=getEventTime("start");
        LocalTime end=getEventTime("end");
        if(!checkCurrentTeam(curTeam,start,end)){
            return ;
        }
        for(Team team:teams) {
            List<User> teamMembers =team.getTeamMembers();
            if(!checkUsers(start,end,teamMembers)){
                return ;
            }
        }
        Event event=new Event(e,start,end);
        for(Team team:teams){
            updateUsers(start,end,team.getTeamMembers(),event);
        }
        updateCurrentTeam(curTeam,start,end,event);
        System.out.println(e+" meeting Schdule successfully");
    }

    public void createEvent(Team curTeam, User[] users, String e) {
        LocalTime start=getEventTime("start");
        LocalTime end=getEventTime("end");
        if(!checkCurrentTeam(curTeam,start,end)){
            return ;
        }
        if(!checkUsers(start,end,new ArrayList<>(Arrays.asList(users)))){
            return;
        }
        Event event=new Event(e,start,end);
        updateUsers(start,end,new ArrayList<>(Arrays.asList(users)),event);
        updateCurrentTeam(curTeam,start,end,event);
        System.out.println(e+" meeting Schedule successfully");
    }

    private void updateCurrentTeam(Team curTeam, LocalTime start, LocalTime end, Event event) {
        List<User> teamMembers=curTeam.getTeamMembers();
        updateUsers(start,end,teamMembers,event);
    }

    private boolean checkCurrentTeam(Team curTeam, LocalTime start, LocalTime end) {
        List<User> teamMembers=curTeam.getTeamMembers();
        if(!checkUsers(start,end,teamMembers)){
            return false;
        }
        return true;
    }
}
