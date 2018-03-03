package com.example.ryanjennings.spiderscheduler;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

public class Dashboard extends AppCompatActivity {

    private TextView mTextMessage;
    private ListView mListView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mListView = (ListView) findViewById(R.id.event_list_view);
// 1
        ArrayList<Event> eventList = new ArrayList<Event>();

        try {
            Scanner sc = new Scanner(getResources().openRawResource(R.raw.events));
            sc.nextLine();
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] tokens = line.split(",");
                int type = Integer.parseInt(tokens[0]);
                DateFormat format = new SimpleDateFormat("MM-dd-yy", Locale.ENGLISH);
                Date date = format.parse(tokens[4]);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:m a");
                LocalTime time_start = LocalTime.parse(tokens[5], DateTimeFormatter.ofPattern("h:m a"));
                LocalTime time_end = LocalTime.parse(tokens[6], DateTimeFormatter.ofPattern("h:m a"));
                Event event = new Event(type, tokens[1], tokens[2], tokens[3], date, time_start, time_end);
                eventList.add(event);
            }
            sc.close();
        } catch(Exception e){
                //
        }


        try {
            eventList = Event.readCSV("events.csv", this);
        }
        catch(Exception E) {
            Log.d("warning","error");
        }
// 2
        String[] listItems = new String[eventList.size()];

// 3
        for(int i = 0; i < eventList.size(); i++){
            Event event = eventList.get(i);
            listItems[i] = event.event_name;
        }
// 4
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);

    }

}
