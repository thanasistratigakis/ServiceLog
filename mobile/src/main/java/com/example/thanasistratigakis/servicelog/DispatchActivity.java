package com.example.thanasistratigakis.servicelog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.ParseUser;

/**
 * Created by ThanasiStratigakis on 5/18/15.
 */
public class DispatchActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // Check if there is  current user info
        if (ParseUser.getCurrentUser() != null) {
            // Start an intent for th elogged in activity
            startActivity(new Intent(this, MainActivity.class));
        } else {
            // Start and intent for the logged out activity
            startActivity(new Intent(this, SignUpOrLoginActivity.class));
        }
    }

}
