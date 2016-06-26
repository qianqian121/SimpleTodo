package com.codepath.simpletodo.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codepath.simpletodo.R;

import java.text.SimpleDateFormat;

//import android.widget.CursorAdapter;

/**
 * Created by qiming on 6/21/2016.
 */
public class TodoCursorAdapter extends CursorRecyclerViewAdapter<TodoCursorAdapter.ViewHolder> {
    public static final SimpleDateFormat SDF = new SimpleDateFormat("MM/dd/yyyy");
    private View.OnClickListener mOnClickListener;
    private View.OnLongClickListener mOnLongClickListener;

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.mOnLongClickListener = onLongClickListener;
    }

    public Cursor getCursor(int position) {
        Cursor cursor = getCursor();
        cursor.moveToPosition(position);
        return cursor;
    }

    public TodoCursorAdapter(Context context,Cursor cursor){
        super(context,cursor);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvBody;
        public TextView tvPriority;
        public TextView tvDueDate;

        public ViewHolder(View view) {
            super(view);
            tvBody = (TextView) view.findViewById(R.id.tvItem);
            tvPriority = (TextView) view.findViewById(R.id.tvPriority);
            tvDueDate = (TextView) view.findViewById(R.id.tvDueDate);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnClickListener.onClick(view);
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return mOnLongClickListener.onLongClick(view);
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_todo, parent, false);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        // Extract properties from cursor
        String body = cursor.getString(cursor.getColumnIndexOrThrow("item"));
        int priority = cursor.getInt(cursor.getColumnIndexOrThrow("priority"));
        long duedate = cursor.getLong(cursor.getColumnIndexOrThrow("duedate"));
        // Populate fields with extracted properties
        viewHolder.tvBody.setText(body);
        viewHolder.tvPriority.setText(String.valueOf(priority));
        viewHolder.tvDueDate.setText(SDF.format(duedate));
    }
}