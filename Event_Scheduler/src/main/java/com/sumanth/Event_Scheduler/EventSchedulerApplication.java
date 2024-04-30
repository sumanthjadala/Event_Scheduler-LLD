package com.sumanth.Event_Scheduler;

import com.sumanth.Event_Scheduler.Managers.EventManager;
import com.sumanth.Event_Scheduler.Managers.EventSchedular;
import com.sumanth.Event_Scheduler.Managers.UserManager;
import com.sumanth.Event_Scheduler.Model.User;
import com.sumanth.Event_Scheduler.SegmentTree.SegmentTree;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class EventSchedulerApplication {

	public static void main(String[] args) {
		System.out.println("Welcome to the Event Scheduler App");
		EventSchedular eventSchedular=EventSchedular.getInstance();
		eventSchedular.start();
	}
}
