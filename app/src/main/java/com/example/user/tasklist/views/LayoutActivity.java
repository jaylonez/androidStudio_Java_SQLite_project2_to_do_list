package com.example.user.tasklist.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.user.tasklist.R;


public abstract class LayoutActivity extends AppCompatActivity{

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.all_tasks) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.prioritized_tasks) {
            Intent intent = new Intent(this, PrioritiesActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.themes) {
            Intent intent = new Intent(this, ThemesActivity.class);
            startActivity(intent);
        }
        return true;
    }
}
