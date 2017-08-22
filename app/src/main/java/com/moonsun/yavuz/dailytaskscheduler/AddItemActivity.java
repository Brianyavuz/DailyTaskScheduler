package com.moonsun.yavuz.dailytaskscheduler;

/**
 * Created by yavuz on 8/22/2017.
 */

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddItemActivity extends AppCompatActivity {

    private static final String TAG ="AddItemActivity" ;

    Button buttonAddTask;
    EditText editText_taskname;
    TextView textView_duedate;
    EditText editText_tasknote;
    TextView textView_taskpriority;
    TextView textView_taskstatus;

    private int mHour;
    private int mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        editText_taskname = (EditText)findViewById(R.id.et_taskname);
        textView_duedate = (TextView) findViewById(R.id.textView_duetime);
        editText_tasknote = (EditText)findViewById(R.id.et_tasknote);
        textView_taskpriority= (TextView) findViewById(R.id.textView_taskpriority);
        textView_taskstatus = (TextView) findViewById(R.id.textView_taskstatus);

        buttonAddTask = (Button) findViewById(R.id.buttonAdd);
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Task task = new Task();
                task.setTaskName(editText_taskname.getText().toString());
                task.setDueDate(textView_duedate.getText().toString());
                task.setTaskNotes(editText_tasknote.getText().toString());
                task.setPriority(textView_taskpriority.getText().toString());
                task.setStatus(textView_taskstatus.getText().toString());

                Toast.makeText(AddItemActivity.this, "task: " + task.getTaskName() + " added", Toast.LENGTH_LONG).show();


                /*
                * Adding new task and returning the data to MainActivity
                * */
                Intent data = new Intent();
                data.putExtra("newAddedItem", task);
                // Activity finished ok, return the data
                setResult(RESULT_OK, data); // set result code and bundle data for response
                finish(); // closes the activity


            }
        });

        /**
         * Opening a timepicker
         */

        textView_duedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new TimePickerFragment(textView_duedate);
                dialogFragment.show(getFragmentManager(), "timePicker");
            }
        });

        /*
        * Opening a popup menu to choose taskpriority
        * */

        textView_taskpriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(AddItemActivity.this, textView_taskpriority);
                //inflating the Popup using XML file
                popup.getMenuInflater().inflate(R.menu.main, popup.getMenu());
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(android.view.MenuItem item) {

                        textView_taskpriority.setText(item.getTitle());
                        //Toast.makeText(AddItemActivity.this, "You clicked : " + item.getTitle(),
                        //        Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popup.show();
            }
        });

         /*
        * Opening a popup menu to choose taskstatus
        * */
        textView_taskstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(AddItemActivity.this, textView_taskstatus);
                //inflating the Popup using XML file
                popup.getMenuInflater().inflate(R.menu.status, popup.getMenu());
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(android.view.MenuItem item) {

                        textView_taskstatus.setText(item.getTitle());
                        return true;
                    }
                });
                popup.show();
            }
        });
    }

    /*
    * Getting the time from timepicker and writing on the textview
    * */

    public void setTimer(View view) {

        textView_duedate = (TextView) view;
        String time = textView_duedate.getText().toString().trim();
        if (time.length() == 5) {
            String mHour_string = time.substring(0, 2);
            mHour = Integer.parseInt(mHour_string);
            String mMinute_string = time.substring(3, 5);
            mMinute = Integer.parseInt(mMinute_string);
            Log.d(TAG, "" + mHour + " " + "" + mMinute);
        } else {
            String mHour_string = time.substring(0, 1);
            mHour = Integer.parseInt(mHour_string);
            String mMinute_string = time.substring(3, 4);
            mMinute = Integer.parseInt(mMinute_string);
            Log.d(TAG, "" + mHour + " " + "" + mMinute);
        }

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, mHour);
        calendar.set(Calendar.MINUTE, mMinute);
        calendar.set(Calendar.SECOND, 0);

    }
}