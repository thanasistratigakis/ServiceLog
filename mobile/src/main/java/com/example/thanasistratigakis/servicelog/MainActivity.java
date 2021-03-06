package com.example.thanasistratigakis.servicelog;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import com.parse.Parse;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import static com.example.thanasistratigakis.servicelog.R.id.tabLog;


public class MainActivity extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Enable Local Datastore.

        Parse.initialize(this, "bFlfAKQNEWDRvaNX7ikcJ0ZaSKbcKc6W98InH9Ie", "Idlc9KjBgLFvLakk2jUMBpUEfvUNZemux6tT10p9");

        setContentView(R.layout.activity_main);

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);

        tabHost.setup();
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("logTab");
        tabSpec.setContent(tabLog);
        tabSpec.setIndicator("Log");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("messengerTab");
        tabSpec.setContent(R.id.tabMessenger);
        tabSpec.setIndicator("Msg");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("discoverTab");
        tabSpec.setContent(R.id.tabDiscover);
        tabSpec.setIndicator("Disc");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("myAccountTab");
        tabSpec.setContent(R.id.tabMyAccount);
        tabSpec.setIndicator("MyAcc");
        tabHost.addTab(tabSpec);


        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //add the content for logTab container
        TabContentLogFragment logFragment = new TabContentLogFragment();
        fragmentTransaction.add(R.id.tabLog, logFragment);//(R.id.tabLogContainer, logFragment);
        // add the content for messengerTab container
        TabContentMessengerFragment msgFragment = new TabContentMessengerFragment();
        fragmentTransaction.add(R.id.tabMessenger, msgFragment);
        // add the content for discoverTab container
        TabContentDiscoverFragment discFragment = new TabContentDiscoverFragment();
        fragmentTransaction.add(R.id.tabDiscover, discFragment);
        // add the content for myAccountTab container
        TabContentMyAccountFragment myAccFragment = new TabContentMyAccountFragment();
        fragmentTransaction.add(R.id.tabMyAccount, myAccFragment);
        // only write this line once (or else it will crash)
        fragmentTransaction.commit();



        final Location location = new Location("CitySquashBrooklyn", "140 74th Street, Brooklyn, NY 11209", "We help kids learn squash!", ParseUser.getCurrentUser().getObjectId());
        location.saveInBackground(new SaveCallback() {
            @Override
            public void done(com.parse.ParseException e) {
                if (e == null){
                    Organization organization = new Organization("City Squash", ParseUser.getCurrentUser().getObjectId(), location.getObjectId());

                }
            }
        });

        ServiceRole role = new ServiceRole("Coach", "Coach squash", "Knowledge of squash and good with kids", true);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
