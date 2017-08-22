package com.moonsun.yavuz.dailytaskscheduler;

/**
 * Created by yavuz on 8/22/2017.
 */

import android.content.Intent;
import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class EditItemActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    //String newItem;
    private final int REQUEST_CODE = 20;
    EditText editText_taskname;
    TextView textView_duedate;
    EditText editText_tasknote;
    TextView textView_taskpriority;
    TextView textView_taskstatus;
    Task newTask;
    Task updatedTask;
    private int mHour;
    private int mMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        editText_taskname = (EditText) findViewById(R.id.et_taskname);
        textView_duedate = (TextView) findViewById(R.id.textView_time);
        editText_tasknote = (EditText) findViewById(R.id.et_tasknote);
        textView_taskpriority = (TextView) findViewById(R.id.textView_edittaskpriority);
        textView_taskstatus = (TextView) findViewById(R.id.textView_edittaskstatus);

        newTask = (Task) getIntent().getSerializableExtra("updatedTask");

        editText_taskname.setText(newTask.getTaskName());
        textView_duedate.setText(newTask.getDueDate());
        editText_tasknote.setText(newTask.getTaskNotes());
        textView_taskpriority.setText(newTask.getPriority());
        textView_taskstatus.setText(newTask.getStatus());

        textView_duedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new TimePickerFragment(textView_duedate);
                dialogFragment.show(getFragmentManager(), "timePicker");

                //Toast.makeText(EditItemActivity.this, "showTimePicker", Toast.LENGTH_SHORT).show();
            }
        });

        textView_taskpriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(EditItemActivity.this, textView_taskpriority);
                //inflating the Popup using XML file
                popup.getMenuInflater().inflate(R.menu.main, popup.getMenu());
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(android.view.MenuItem item) {

                        textView_taskpriority.setText(item.getTitle());
                        //Toast.makeText(EditItemActivity.this, "You clicked : " + item.getTitle(),
                        //        Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popup.show();
            }
        });

        textView_taskstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(EditItemActivity.this, textView_taskstatus);
                //inflating the Popup using XML file
                popup.getMenuInflater().inflate(R.menu.status, popup.getMenu());
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(android.view.MenuItem item) {

                        textView_taskstatus.setText(item.getTitle());
                        //Toast.makeText(EditItemActivity.this, "You clicked : " + item.getTitle(),
                        //        Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popup.show();
            }
        });

    }

    public void updateTask(View view) {
        // Prepare data intent
        Intent data = new Intent();
        // Pass relevant data back as a result

        updatedTask = new Task();
        updatedTask.setTaskName(editText_taskname.getText().toString());
        updatedTask.setDueDate(textView_duedate.getText().toString());
        updatedTask.setTaskNotes(editText_tasknote.getText().toString());
        updatedTask.setPriority(textView_taskpriority.getText().toString());
        updatedTask.setStatus(textView_taskstatus.getText().toString());

        data.putExtra("updatedTask", updatedTask);
        // Activity finished ok, return the data
        setResult(RESULT_OK, data); // set result code and bundle data for response

        Log.d(TAG, updatedTask.getTaskName().toString() + " updated in EditItemActivity");
        finish(); // closes the activity, pass data to MainActivity
    }


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

