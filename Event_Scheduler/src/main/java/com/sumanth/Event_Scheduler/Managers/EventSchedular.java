package com.sumanth.Event_Scheduler.Managers;

import com.sumanth.Event_Scheduler.Model.Event;
import com.sumanth.Event_Scheduler.Model.Team;
import com.sumanth.Event_Scheduler.Model.User;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EventSchedular {
    private static EventSchedular eventSchedular=null;
    private UserManager userManager=UserManager.getInstance();
    private EventManager eventManager=EventManager.getInstance();
    private TeamManager teamManager=TeamManager.getInstance();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    private Scanner sc=new Scanner(System.in);
    private List<User> userList=new ArrayList<>();
    private List<Team> teamList=new ArrayList<>();
    public  static EventSchedular getInstance(){
        if(eventSchedular==null){
            eventSchedular=new EventSchedular();
        }
        return eventSchedular;
    }
    private EventSchedular(){}
    public void start(){
        createUsers();
        createTeams();
        createEvents();
        displayEvents();
    }

    private void displayEvents() {
        for(User user:userList){
            List<Event> eventList=user.getEventList();
            if(eventList.size()==0){
                System.out.println(user.getUserName()+" has no Meetings");
            }else {
                System.out.println(user.getUserName()+" have below meetings");
                for (Event e : eventList) {
                    System.out.println(e);
                }
            }
        }
    }

    private void createEvents() {

        while(true){
            System.out.println("choose the type you are \n like user or team");
            String typeOfEvent=sc.nextLine();
            if(typeOfEvent.equals("user")){
                userEvent();
                System.out.println("will you add another meeting or not : Y/N");
                String flag=sc.nextLine().strip();
                if(flag.equals("N")){
                    break;
                }
            }else if(typeOfEvent.equals("team")){
                teamEvent();
                System.out.println("will you add another meeting or not : Y/N");
                String flag=sc.nextLine().strip();
                if(flag.equals("N")){
                    break;
                }
            }else{
                System.out.println("Please Enter valid type of you are");
            }
        }
    }

    private Team getTeam(String teamName) {
        for(Team team:teamList){
            if(team.getTeamName().equals(teamName)){
                return team;
            }
        }
        return null;
    }
    private void teamEvent(){
        System.out.println("Enter your team Name");
        while(true) {
            String teamName = sc.nextLine();
            Team cur_Team = getTeam(teamName);
            if(cur_Team==null){
                System.out.println("TeamName doesNot exist , enter valid teamName");
                continue;
            }
            System.out.println("Who are the participant on the meeting either users or Teams");
            System.out.println("Choose option between team or user \n Ex: team");
            String choice=sc.nextLine();
            if(choice.equals("team")){
                displayTeams(cur_Team);
                String[] teamIds=sc.nextLine().split("[ ]");
                Team[] teams=getTeams(teamIds);
                System.out.println("Enter Event Name");
                String e=sc.nextLine();
                eventManager.createEvent(cur_Team,teams,e);
                break;
            }else if(choice.equals("user")){
                displayUsers();
                String[] userIds=sc.nextLine().split("[ ]");
                User[] users=getusers(userIds);
                System.out.println("Enter Event Name");
                String e=sc.nextLine();
                eventManager.createEvent(cur_Team,users,e);
                break;
            }
        }
    }



    private void userEvent(){
        System.out.println("Enter your user Name");
        while(true) {
            String username = sc.nextLine();
            User cur_user = getUser(username);
            if(cur_user==null){
                System.out.println("UserName doesNot exist , enter valid userName");
                continue;
            }
            System.out.println("Who are the participant on the meeting either users or Teams");
            System.out.println("Choose option between team or user \n Ex: team");
            String choice=sc.nextLine();
            if(choice.equals("team")){
                displayTeams();
                String[] teamIds=sc.nextLine().split("[ ]");
                Team[] teams=getTeams(teamIds);
                System.out.println("Enter Event Name");
                String e=sc.nextLine();
                eventManager.createEvent(cur_user,teams,e);
                break;
            }else if(choice.equals("user")){
                displayUsers(cur_user);
                String[] userIds=sc.nextLine().split("[ ]");
                User[] users=getusers(userIds);
                System.out.println("Enter Event Name");
                String e=sc.nextLine();
                eventManager.createEvent(cur_user,users,e);
                break;
            }
        }
    }

    private User[] getusers(String[] userIds) {
        Arrays.sort(userIds,(u1,u2)->{
            return Integer.parseInt(u1)-Integer.parseInt(u2);
        });
        int i=0;
        User[] users=new User[userIds.length];
        for(User user:userList){
            if(i< users.length && user.getId()==Integer.parseInt(userIds[i])){
                users[i]=user;
                i+=1;
            }
        }
        return users;
    }

    private void displayUsers(User curUser) {
        System.out.println("choose the user ids from below");
        for(User user:userList){
            if(user.equals(curUser)){
                continue;
            }
            System.out.print(user.getId()+" ");
        }
        System.out.println();
    }

    private Team[] getTeams(String[] teamIds) {
        Arrays.sort(teamIds,(t1,t2)->{
            return Integer.parseInt(t1)-Integer.parseInt(t2);
        });
        int i=0;
        Team[] teams=new Team[teamIds.length];
        for(Team team:teamList){
            if(i< teamIds.length && team.getTeamId()==Integer.parseInt(teamIds[i])){
                teams[i]=team;
                i+=1;
            }
        }
        return teams;
    }

    private void displayTeams() {
        System.out.println("choose the team ids from below");
        for (Team team:teamList){
            System.out.print(team.getTeamId()+" ");
        }
        System.out.println();
    }
    private void displayTeams(Team curTeam) {
        System.out.println("choose the team ids from below");
        for (Team team:teamList){
            if(team.getTeamName().equals(curTeam)){
                continue;
            }
            System.out.print(team.getTeamId()+" ");
        }
        System.out.println();
    }

    private User getUser(String username) {
        for(User user:userList){
            if(user.getUserName().equals(username)){
                return user;
            }
        }
        return null;
    }

    private void createTeams() {
        System.out.println("Enter How Many Teams You Want");
        int noTeams=sc.nextInt();
        sc.nextLine();
        for(int i=0;i<noTeams;i++){
            Team team=teamManager.CreateTeam();
            System.out.println("Avaliable team Members");
            displayUsers();
            System.out.println("Enter team Members");
            String[] teamMembers=sc.nextLine().split(" +");
            addTeamMembers(team,teamMembers);
            teamList.add(team);
        }
    }

    private void displayUsers() {
        for(User user:userList){
            System.out.print(user.getId()+" ");
        }
        System.out.println();
    }

    void addTeamMembers(Team team,String[] teamMembers){
        Arrays.sort(teamMembers,(t1,t2)->{
            return Integer.parseInt(t1)-Integer.parseInt(t2);
        });
        int i=0;
        for(User user:userList){
            if(i<teamMembers.length &&  Integer.parseInt(teamMembers[i])==user.getId()){
                team.addTeamMembers(user);
                i+=1;
            }
        }
    }
    private void createUsers() {
        System.out.println("Enter How Many users You Want");
        int noUsers=sc.nextInt();
        sc.nextLine();
        for(int i=0;i<noUsers;i++){
            userList.add(userManager.createUser());
        }
    }
}
