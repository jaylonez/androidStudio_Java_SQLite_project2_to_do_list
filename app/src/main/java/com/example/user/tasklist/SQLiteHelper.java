package com.example.user.tasklist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by user on 20/11/2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "Tasks.db";

    public static final String TABLE_NAME = "TASKS";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_TASK_NAME = "TASK_NAME";
    public static final String COLUMN_TASK_DESCRIPTION = "TASK_DESCRIPTION";
    public static final String COLUMN_TASK_COMPLETED = "TASK_COMPLETED";

    private SQLiteDatabase database;


    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TASK_NAME + " VARCHAR, " + COLUMN_TASK_DESCRIPTION + " VARCHAR, " + COLUMN_TASK_COMPLETED + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public void insertTask(Task task) {
        database = this.getReadableDatabase();
        database.execSQL("INSERT INTO " + TABLE_NAME + "(" + COLUMN_TASK_NAME + "," + COLUMN_TASK_DESCRIPTION + "," + COLUMN_TASK_COMPLETED + ") VALUES('" + task.getName() + "','" + task.getDescription() + "','" + task.getCompleted() + "')");
        database.close();
    }

    public void updateTask(Task task) {
        database = this.getReadableDatabase();
        database.execSQL("update " + TABLE_NAME + " set " + COLUMN_TASK_NAME + " = '" + task.getName() + "', " + COLUMN_TASK_DESCRIPTION + " = '" + task.getDescription() + "', " + COLUMN_TASK_COMPLETED + " = '" + task.getCompleted() + "' where " + COLUMN_ID + " = '" + task.getId() + "'");
        database.close();
    }

    public void deleteTask(Task task) {
        database = this.getReadableDatabase();
        database.execSQL("delete from " + TABLE_NAME + " where " + COLUMN_ID + " = '" + task.getId() + "'");
        database.close();
    }

    public ArrayList<Task> getAllRecords() {
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);
        ArrayList<Task> tasks = new ArrayList<>();
        Task task;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                task = new Task();
                task.setId(cursor.getInt(0));
                task.setName(cursor.getString(1));
                task.setDescription(cursor.getString(2));
                task.setCompleted(cursor.getInt(3));
                tasks.add(task);
            }
        }
        cursor.close();
        database.close();
        return tasks;
    }


}
