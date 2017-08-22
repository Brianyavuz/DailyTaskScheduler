package com.moonsun.yavuz.dailytaskscheduler;

/**
 * Created by yavuz on 8/22/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TaskDatabaseHelper extends SQLiteOpenHelper {


    // Tasks table name
    private static final String TABLE_TASK = "task";

    // Tasks Table Columns names

    private static final String KEY_ID = "_id";
    private static final String KEY_TASKNAME = "taskname";
    private static final String KEY_DATE = "date";
    private static final String KEY_TASKNOTE = "tasknote";
    private static final String KEY_PRIORITY = "priority";
    private static final String KEY_STATUS = "status";

    private static final String[] COLUMNS = {KEY_ID, KEY_TASKNAME, KEY_DATE, KEY_TASKNOTE, KEY_PRIORITY, KEY_STATUS};


    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "TaskDB";
    private static final String TAG = "tag";

    private static TaskDatabaseHelper sInstance;

    public TaskDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized TaskDatabaseHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new TaskDatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    // Called when the database is created for the FIRST time.
    // If a database already exists on disk with the same DATABASE_NAME, this method will NOT be called.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TASK_TABLE = "CREATE TABLE " + TABLE_TASK +
                "(" +
                KEY_ID + " INTEGER PRIMARY KEY," + // Define a primary key
                KEY_TASKNAME + " TEXT," + KEY_DATE + " TEXT," +
                KEY_TASKNOTE + " TEXT," + KEY_PRIORITY + " TEXT," +
                KEY_STATUS + " TEXT" +
                ")";

        db.execSQL(CREATE_TASK_TABLE);
    }

    // Called when the database needs to be upgraded.
    // This method will only be called if a database already exists on disk with the same DATABASE_NAME,
    // but the DATABASE_VERSION is different than the version of the database that exists on disk.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
            onCreate(db);
        }
    }

    public void addTask(Task task) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {
            // The user might already exist in the database (i.e. the same user created multiple posts).
            //long userId = addOrUpdateUser(post.user);

            ContentValues values = new ContentValues();
            values.put(KEY_TASKNAME, task.getTaskName());
            values.put(KEY_DATE, task.getDueDate());
            values.put(KEY_TASKNOTE, task.getTaskNotes());
            values.put(KEY_PRIORITY, task.getPriority());
            values.put(KEY_STATUS, task.getStatus());


            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            db.insert(TABLE_TASK, null, values);
            Log.d(TAG, task.getTaskName() + " added");
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add task to database");
        } finally {
            db.endTransaction();
        }
    }

    // Updating single task
    public int updateTask(Task task) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_TASKNAME, task.getTaskName()); // get taskName
        values.put(KEY_DATE, task.getDueDate()); // get dueDate
        values.put(KEY_TASKNOTE, task.getTaskNotes());
        values.put(KEY_PRIORITY, task.getPriority());
        values.put(KEY_STATUS, task.getStatus());

        // 3. updating row
        int row = db.update(TABLE_TASK, //table
                values, // column/value
                KEY_ID + " = ?", //where clause selections
                new String[]{
                        String.valueOf(task.getId())
                }); //selection args

        Log.d(TAG, task.getTaskName()+ " updated");

        // 4. close
        db.close();

        return row;

    }

    public void deleteTask(Task task) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_TASK, //table name
                KEY_ID + " = ?",  // selections
                new String[]{String.valueOf(task.getId())}); //selections args

        //log
        Log.d(TAG, task.getTaskName() + " deleted");

        // 3. close
        db.close();

    }

    public Task getTask(int id) {
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        //
        ArrayList<Task> tasks = new ArrayList<>();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_TASK, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[]{String.valueOf(id)}, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build task object
        Task task = new Task();
        task.setId(Integer.parseInt(cursor.getString(0)));
        task.setTaskName(cursor.getString(1));
        task.setDueDate(cursor.getString(2));
        task.setTaskNotes(cursor.getString(3));
        task.setPriority(cursor.getString(4));
        task.setStatus(cursor.getString(5));

        // 5. return book
        return task;

    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> tasks = new ArrayList<>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_TASK;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Task task = null;
        if (cursor.moveToFirst()) {
            do {
                task = new Task();
                task.setId(Integer.parseInt(cursor.getString(0)));
                task.setTaskName(cursor.getString(1));
                task.setDueDate(cursor.getString(2));
                task.setTaskNotes(cursor.getString(3));
                task.setPriority(cursor.getString(4));
                task.setStatus(cursor.getString(5));

                // Add task to tasks
                tasks.add(task);
            } while (cursor.moveToNext());
        }

        // return tasks
        return tasks;
    }
}
