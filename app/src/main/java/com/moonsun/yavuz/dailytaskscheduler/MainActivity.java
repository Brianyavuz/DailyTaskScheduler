package com.moonsun.yavuz.dailytaskscheduler;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Task> arrayOfTasks;
    CustomListAdapter itemsAdapter;
    ListView lvItems;
    Task task;
    Task newTask;
    Task deletedTask;
    int index;
    TaskDatabaseHelper databaseHelper;

    private final int REQUEST_CODE = 20;
    private final int REQUEST_CODE_2 = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = (ListView) findViewById(R.id.lvItems);

        databaseHelper = TaskDatabaseHelper.getInstance(this);
        arrayOfTasks = databaseHelper.getAllTasks();

        itemsAdapter = new CustomListAdapter(this,arrayOfTasks);
        lvItems.setAdapter(itemsAdapter);

        setupListViewListener();

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                index=parent.getPositionForView(view);
                task = (Task) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, EditItemActivity.class);
                intent.putExtra("updatedTask", task);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // REQUEST_CODE is defined above
        //Update Task
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            newTask = (Task) intent.getSerializableExtra("updatedTask");
            itemsAdapter.remove(task);
            itemsAdapter.insert(newTask,index);
            databaseHelper.updateTask(newTask);
            Toast.makeText(this, newTask.getTaskName() + " updated", Toast.LENGTH_LONG).show();
        }

        // Add task
        if(resultCode==RESULT_OK && requestCode == REQUEST_CODE_2) {

            Task newAddedTask;
            newAddedTask = (Task) intent.getSerializableExtra("newAddedItem");
            itemsAdapter.add(newAddedTask);
            databaseHelper.addTask(newAddedTask);
        }
    }

    private void setupListViewListener() {

        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final AdapterView<?> newparent = parent;
                final int newposition=position;
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Deleting Task")
                        .setMessage("Are you sure you want to delete this task?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deletedTask = (Task) newparent.getItemAtPosition(newposition);
                                databaseHelper.deleteTask(deletedTask);
                                arrayOfTasks.remove(newposition);
                                itemsAdapter.notifyDataSetChanged();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });
    }

    public void onAddItem(View view) {

        Intent intent = new Intent(this, AddItemActivity.class);
        startActivityForResult(intent,REQUEST_CODE_2);
    }
}
