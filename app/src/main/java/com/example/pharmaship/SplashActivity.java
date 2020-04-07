package com.example.pharmaship;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        initialSetup();
        boolean loginSaved = LocalDatabase.retrieveLoginSaved(); // used to route to different pages
        routeToAppropriatePage(this, loginSaved);
    }
    public static void routeToAppropriatePage(Activity activity, boolean loginSaved) {
        Intent intent;
        if (!loginSaved) {
            intent = new Intent(activity, LoginActivity.class);
        }
        else {
            intent = new Intent(activity, MenuActivity.class);
        }
        activity.startActivity(intent);
        activity.finish();
    }
    private void initialSetup() {
        LocalDatabase.initialSetup(getApplicationContext());
    }
}
