package com.moonsun.yavuz.dailytaskscheduler;

/**
 * Created by yavuz on 8/22/2017.
 */

import java.io.Serializable;


public class Task implements Serializable {
    private int id;
    private String taskName;
    private String dueDate;
    private String taskNotes;
    private String priority;
    private String status;

    public Task(){

    }

    public Task(String taskName, String priority){
        this.taskName = taskName;
        this.priority=priority;
    }

    public Task (String taskName, String dueDate, String taskNotes, String priority, String status){
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.taskNotes = taskNotes;
        this.priority=priority;
        this.status=status;
    }

    public Task (int id, String taskName, String dueDate, String taskNotes, String priority, String status){
        this.id = id;
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.taskNotes = taskNotes;
        this.priority=priority;
        this.status=status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getTaskNotes() {
        return taskNotes;
    }

    public void setTaskNotes(String taskNotes) {
        this.taskNotes = taskNotes;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

