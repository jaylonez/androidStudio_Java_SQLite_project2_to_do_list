package com.example.user.tasklist.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.user.tasklist.Models.Task;

import java.util.ArrayList;

/**
 * Created by user on 20/11/2017.
 */

public class TaskRepo extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 17;
    public static final String DATABASE_NAME = "Tasks.db";

    public static final String TABLE_NAME = "TASKS";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_TASK_NAME = "TASK_NAME";
    public static final String COLUMN_TASK_DESCRIPTION = "TASK_DESCRIPTION";
    public static final String COLUMN_TASK_COMPLETED = "TASK_COMPLETED";
    public static final String COLUMN_TASK_PRIORITY = "TASK_PRIORITY";

    private SQLiteDatabase database;


    public TaskRepo(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABLE_NAME + "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TASK_NAME + " VARCHAR, " + COLUMN_TASK_DESCRIPTION + " VARCHAR, " + COLUMN_TASK_COMPLETED + " BOOLEAN, " + COLUMN_TASK_PRIORITY + " BOOLEAN)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.execute();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        SQLiteStatement statement = db.compileStatement(sql);
        statement.execute();

        onCreate(db);
    }


    public void insertTask(Task task) {
        database = this.getReadableDatabase();
        String sql = "INSERT INTO " + TABLE_NAME + " ( " + COLUMN_TASK_NAME + " , " + COLUMN_TASK_DESCRIPTION + " , " + COLUMN_TASK_COMPLETED + " , " + COLUMN_TASK_PRIORITY + ") VALUES( ? , ? , '" + task.getCompletedInt() + "' , '" + task.getPriorityInt() + "' )";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, task.getName());
        statement.bindString(2, task.getDescription());

        statement.executeInsert();
        database.close();
    }

//    public void updateTask(Task task) {
//
//        database = this.getReadableDatabase();
//        String sql = "update " + TABLE_NAME + " set " + COLUMN_TASK_NAME + " = '" + task.getName() + "', " + COLUMN_TASK_DESCRIPTION + " = '" + task.getDescription() + "', " + COLUMN_TASK_COMPLETED + " = '" + task.getCompleted() + "' where " + COLUMN_ID + " = '" + task.getId() + "'";
//        SQLiteStatement statement = database.compileStatement(sql);
//
//        statement.bindString(1, task.getName());
//        statement.bindString(2, task.getDescription());
//
//        statement.executeUpdateDelete();
//        database.close();
//    }

    public void deleteTask(Task task) {

        database = this.getReadableDatabase();
        String sql = "delete from " + TABLE_NAME + " where " + COLUMN_ID + " = '" + task.getId() + "'";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.executeUpdateDelete();
        database.close();
    }

    public ArrayList<Task> getAllTasks() {
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
                task.setCompleted(cursor.getInt(3) > 0);
                task.setPriority(cursor.getInt(4) > 0);
                tasks.add(task);
            }
        }
        cursor.close();
        database.close();
        return tasks;
    }

    public Task getTaskByID(int id) {
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE ID = " + id, null );
        cursor.moveToNext();

        Task task = new Task();
        task.setId(cursor.getInt(0));
        task.setName(cursor.getString(1));
        task.setDescription(cursor.getString(2));
        task.setCompleted(cursor.getInt(3) > 0);
        task.setPriority(cursor.getInt(4) > 0);

        cursor.close();
        database.close();
        return task;
    }

    public ArrayList<Task> getAllPriorityTasks() {
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, COLUMN_TASK_PRIORITY + " = ?", new String[] { "1" }, null, null, null);
        ArrayList<Task> tasks = new ArrayList<>();
        Task task;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                task = new Task();
                task.setId(cursor.getInt(0));
                task.setName(cursor.getString(1));
                task.setDescription(cursor.getString(2));
                task.setCompleted(cursor.getInt(3) > 0);
                task.setPriority(cursor.getInt(4) > 0);
                tasks.add(task);
            }
        }
        cursor.close();
        database.close();
        return tasks;
    }

    public void setTaskBoolean(Task task, String column, int bool) {

        database = this.getReadableDatabase();
        String sql = "update " + TABLE_NAME + " set " + column + " = " + bool + " where " + COLUMN_ID + " = '" + task.getId() + "'";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.executeUpdateDelete();
        database.close();
    }

    public void setTaskString(Task task, String column, String value) {

        database = this.getReadableDatabase();
        String sql = "update " + TABLE_NAME + " set " + column + " = '" + value + "' where " + COLUMN_ID + " = '" + task.getId() + "'";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.executeUpdateDelete();
        database.close();
    }

}
