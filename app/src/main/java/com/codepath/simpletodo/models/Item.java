package com.codepath.simpletodo.models;

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

    public Item(int id, String text, Date dueDate) {
        this.id = id;
        this.text = text;
        this.dueDate = dueDate;
    }
}
