package com.lifemanagementapp.lifemanagementapp;

import android.widget.ArrayAdapter;

import java.util.ArrayList;


/**
 * Created by kmweber on 3/27/17.
 */
public class Goal {

    private String goalName;
    private int day, month, year, priority, storagePosition;
    private boolean completed;

    public static int goalCount;

    public String getGoalName(){return goalName;}
    public void setGoalName(String newGoalName) {goalName = newGoalName;}
    public int getday(){return day;}
    public void setday(int newDay){
        day = newDay;
    }
    public int getMonth(){
        return month;
    }
    public void setMonth(int newMonth){
        month = newMonth;
    }
    public int getYear(){
        return year;
    }
    public void setYear(int newYear){
        year = newYear;
    }
    public int getPriority(){
        return priority;
    }
    public void setPriority(int newPriority){
        priority = newPriority;
    }
    public boolean getCompleted() { return completed; }
    public void setCompleted(boolean complete) { completed = complete; }
    public int getStoragePosition(){ return storagePosition; }
    public void setStoragePosition(int storagePosition){ this.storagePosition = storagePosition; }


    public Goal(String newName, int newDay, int newMonth, int newYear, int newPriority, int storagePosition){
        setGoalName(newName);
        setday(newDay);
        setMonth(newMonth);
        setYear(newYear);
        setPriority(newPriority);
        setCompleted(false);
        setStoragePosition(storagePosition);
    }

    @Override
    public String toString(){
        return String.format("GOAL: %s\nDUE DATE: %d/%d/%d\n", getGoalName(), getMonth(), getday(), getYear());
    }

    public String dateToString(){
        return String.format("%d/%d/%d\n", getMonth(), getday(), getYear());
    }

    public String priorityToString(){
        return String.format("Priority %d\n", getPriority());
    }
}
