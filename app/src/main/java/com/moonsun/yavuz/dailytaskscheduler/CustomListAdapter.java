package com.moonsun.yavuz.dailytaskscheduler;

/**
 * Created by yavuz on 8/22/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter <Task> {

    TextView taskName;
    TextView taskPriority;
    ImageView taskStatusImage;

    public CustomListAdapter(Context context, ArrayList<Task> tasks) {

        super(context, R.layout.listview_row, tasks);
        // TODO Auto-generated constructor stub
    }

    public View getView(int position, View rowView, ViewGroup parent) {

        Task task= getItem(position);

        if(rowView ==null)
        {
            rowView = LayoutInflater.from(getContext()).inflate(R.layout.listview_row,parent,false);
        }

        taskName = (TextView) rowView.findViewById(R.id.textViewTaskName);
        taskPriority = (TextView) rowView.findViewById(R.id.textViewTaskPriority);
        taskStatusImage = (ImageView) rowView.findViewById(R.id.taskStatusImage);

        taskName.setText(task.getTaskName());
        taskPriority.setText(task.getPriority());
        setPriorityColor(task.getPriority());
        setStatusIcon(task.getStatus());
        return rowView;
    };

    /*
    * Setting color depending on the priority of task
    * */

    public void setPriorityColor(String priority){

        switch (priority){

            case "High" : {
                taskPriority.setTextColor(ContextCompat.getColor(getContext(),R.color.colorRed));
                break;
            }

            case "Medium" : {
                taskPriority.setTextColor(ContextCompat.getColor(getContext(),R.color.colorYellow));
                break;
            }

            case "Low" : {
                taskPriority.setTextColor(ContextCompat.getColor(getContext(),R.color.colorGreen));
                break;
            }
        }
    }

    /*
    * Setting status icon depending on the priority of task
    * */

    public void setStatusIcon (String status){

        switch (status){

            case "Done" : {
                taskStatusImage.setImageResource(R.mipmap.ic_done_white_24dp);
                break;
            }

            case "On Progress" : {
                taskStatusImage.setImageResource(R.mipmap.ic_remove_white_24dp);
                break;
            }


            case "Not started yet" : {
                taskStatusImage.setImageResource(R.mipmap.ic_remove_white_24dp);
                break;
            }
        }
    }
}

