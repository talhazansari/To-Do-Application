package com.example.todoapplication;

import android.widget.ImageView;
import android.widget.TextView;

public class List {
    String taskName;
    String item1;

    public List(String taskName, String item1, String item2,  String time) {
        this.taskName = taskName;
        this.item1 = item1;
        this.item2 = item2;

        this.time = time;
    }

    public List(String taskName, String item1, String item2,  String time,String priority) {
        this.taskName = taskName;
        this.item1 = item1;
        this.item2 = item2;
        this.time = time;
        this.priority =priority;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public ImageView getDelete() {
        return delete;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setDelete(ImageView delete) {
        this.delete = delete;
    }




    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    String item2;
    ImageView delete;
    String priority;
    String time;
}
