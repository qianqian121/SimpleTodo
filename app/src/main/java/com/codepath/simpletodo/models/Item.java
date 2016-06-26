package com.codepath.simpletodo.models;

import android.graphics.Color;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by qiming on 6/21/2016.
 */
public class Item implements Serializable {
    public long id;
    public Date dueDate;
    public int priority;
    public String text;
//    public static final String[] PRIORITY_LEVELS = {"Low", "Low", "Normal", "High"};
    public static final int[] PRIORITY_LEVELS = {Color.GREEN, Color.GREEN, Color.YELLOW, android.graphics.Color.RED};

    public Item(int id, String text, Date dueDate, int priority) {
        this.id = id;
        this.text = text;
        this.dueDate = dueDate;
        this.priority = priority;
    }
}
