package com.example.user.tasklist.Views;

/**
 * Created by user on 23/11/2017.
 */

import android.app.Activity;
import android.content.Intent;

import com.example.user.tasklist.R;

public class Utils {

    private static int sTheme;
    public final static int THEME_DPURPLE = 0;
    public final static int THEME_DRED = 1;
    public final static int THEME_DBLUE = 2;


    public static void changeToTheme(Activity activity, int theme)
    {
        sTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }

    public static void onActivityCreateSetTheme(Activity activity)
    {
        switch (sTheme)
        {
            default:
            case THEME_DPURPLE:
                activity.setTheme(R.style.DraculaPurple);
                break;
            case THEME_DRED:
                activity.setTheme(R.style.DraculaRed);
                break;
            case THEME_DBLUE:
                activity.setTheme(R.style.DraculaBlue);
                break;
        }
    }
}