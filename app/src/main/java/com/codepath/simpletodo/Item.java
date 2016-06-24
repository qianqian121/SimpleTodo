package com.codepath.simpletodo;

import java.util.Date;

/**
 * Created by qiming on 6/21/2016.
 */
public class Item {
    public long id;
    public Date dueDate;
    public int priority;
    public String text;

    public Item(int id, String text, Date dueDate) {
        this.id = id;
        this.text = text;
        this.dueDate = dueDate;
    }
}
