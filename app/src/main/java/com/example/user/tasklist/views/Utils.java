package com.example.user.tasklist.views;

import android.app.Activity;
import android.content.Intent;

import com.example.user.tasklist.R;

class Utils {

    private static int sTheme;
    final static int THEME_DPURPLE = 0;
    final static int THEME_DRED = 1;
    final static int THEME_DYELLOW = 2;
    final static int THEME_LPURPLE = 3;
    final static int THEME_DONOTCLICK = 4;
    final static int THEME_PINK = 5;


    static void changeToTheme(Activity activity, int theme)
    {
        sTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }

    static void onActivityCreateSetTheme(Activity activity)
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
            case THEME_DYELLOW:
                activity.setTheme(R.style.DraculaYellow);
                break;
            case THEME_LPURPLE:
                activity.setTheme(R.style.LightPurple);
                break;
            case THEME_DONOTCLICK:
                activity.setTheme(R.style.DoNotClick);
                break;
            case THEME_PINK:
                activity.setTheme(R.style.FabulousPink);
                break;
        }
    }
}