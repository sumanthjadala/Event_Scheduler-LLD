package com.sumanth.Event_Scheduler.Managers;

import com.sumanth.Event_Scheduler.Model.Team;
import com.sumanth.Event_Scheduler.Model.User;

import java.util.List;
import java.util.Scanner;

public class TeamManager {
    private static TeamManager teamManager=null;
    private Scanner sc=new Scanner(System.in);
    private int id=1;
    public  static TeamManager getInstance(){
        if(teamManager==null){
            teamManager=new TeamManager();
        }
        return teamManager;
    }
    private TeamManager(){}

    public Team CreateTeam(){
        System.out.println("Enter Your Team Name");
        String teamName=sc.nextLine();
        return new Team(id++,teamName);
    }


}
