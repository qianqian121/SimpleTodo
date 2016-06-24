package com.codepath.simpletodo;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;

//import android.widget.CursorAdapter;

/**
 * Created by qiming on 6/21/2016.
 */
public class TodoCursorAdapter extends CursorAdapter {
    public static final SimpleDateFormat SDF = new SimpleDateFormat("MM/dd/yyyy");

    public TodoCursorAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView tvBody = (TextView) view.findViewById(R.id.tvItem);
        TextView tvPriority = (TextView) view.findViewById(R.id.tvPriority);
        TextView tvDueDate = (TextView) view.findViewById(R.id.tvDueDate);
        // Extract properties from cursor
        String body = cursor.getString(cursor.getColumnIndexOrThrow("item"));
        int priority = cursor.getInt(cursor.getColumnIndexOrThrow("priority"));
        long duedate = cursor.getLong(cursor.getColumnIndexOrThrow("duedate"));
        // Populate fields with extracted properties
        tvBody.setText(body);
        tvPriority.setText(String.valueOf(priority));
        tvDueDate.setText(SDF.format(duedate));
    }
}