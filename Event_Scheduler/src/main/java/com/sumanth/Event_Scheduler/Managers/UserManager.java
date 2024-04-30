package com.sumanth.Event_Scheduler.Managers;

import com.sumanth.Event_Scheduler.Model.User;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UserManager {
    private static UserManager userManager=null;
    private Scanner sc=new Scanner(System.in);
    private int id=1;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    public  static UserManager getInstance(){
        if(userManager==null){
            userManager=new UserManager();
        }
        return userManager;
    }
    private UserManager(){}

    public User createUser(){
        System.out.println("Enter userName");
        String name=sc.nextLine();
        System.out.println("Enter working Timings");
        String[] time=sc.nextLine().split("[-]");
        return new User(id++,name, LocalTime.parse(time[0],formatter),LocalTime.parse(time[1],formatter));
    }
}
