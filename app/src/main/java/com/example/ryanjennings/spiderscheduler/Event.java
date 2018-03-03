package com.example.ryanjennings.spiderscheduler;


import java.util.*;
import java.time.LocalTime;

import java.io.*;
import java.text.*;

import java.time.format.DateTimeFormatter;

/**
 * Created by ryanjennings on 3/3/18.
 */

public class Event {


    public int type;
    public String org_name;
    public String event_name;
    public String location;
    public Date date;
    public LocalTime start_time;
    public LocalTime end_time;

    public Event(int _type, String _org_name, String _event_name, String _location, Date _date, LocalTime _start_time, LocalTime _end_time) {
        type = _type;
        org_name = _org_name;
        event_name = _event_name;
        location = _location;
        date = _date;
        start_time = _start_time;
        end_time = _end_time;
    }

    // Format: Type(int), organization(str), eventName(str)
    // location(str), date(Date),time_start(Time),time_end(Time)
    // @TargetApi(Build.VERSION_CODES.M)
    public static ArrayList<Event> readCSV(String fileName) throws FileNotFoundException, ParseException{
        ArrayList<Event> events = new ArrayList<Event>();
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        sc.nextLine();
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String[] tokens = line.split(",");
            int type = Integer.parseInt(tokens[0]);
            DateFormat format = new SimpleDateFormat("MM-DD-YY", Locale.ENGLISH);
            Date date = format.parse(tokens[4]);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:m a");
            LocalTime time_start = LocalTime.parse(tokens[5], DateTimeFormatter.ofPattern("h:m a"));
            LocalTime time_end = LocalTime.parse(tokens[6], DateTimeFormatter.ofPattern("h:m a"));
            Event event = new Event(type, tokens[1], tokens[2], tokens[3], date, time_start, time_end);
            events.add(event);
        }
        sc.close();
        return events;
    }



}
