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

//    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
////        mclicklistener = onItemClickListener;
//    }

//    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvBody;
        public TextView tvPriority;
        public TextView tvDueDate;
//        public TodoCursorAdapterViewHolderClicks mListener;


        //public ViewHolder(View view, TodoCursorAdapterViewHolderClicks todoCursorAdapterViewHolderClicks) {
        public ViewHolder(View view) {
            super(view);
            tvBody = (TextView) view.findViewById(R.id.tvItem);
            tvPriority = (TextView) view.findViewById(R.id.tvPriority);
            tvDueDate = (TextView) view.findViewById(R.id.tvDueDate);
//            mListener = todoCursorAdapterViewHolderClicks;
//
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

//        @Override
//        public void onClick(View view) {
//            mOnClickListener.onClick(view);
//        }
//
//        @Override
//        public boolean onLongClick(View view) {
//            return mOnLongClickListener.onLongClick(view);
//        }

//        public static interface TodoCursorAdapterViewHolderClicks {
//            public void onClick(View caller);
//            public boolean onLongClick(View caller);
//        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_todo, parent, false);
        ViewHolder vh = new ViewHolder(itemView);
//        ViewHolder vh = new ViewHolder(itemView, new TodoCursorAdapter.ViewHolder.TodoCursorAdapterViewHolderClicks() {
//
//            @Override
//            public void onClick(View caller) {
//                Log.d("VEGETABLES", "Poh-tah-tos");
//            }
//
//            @Override
//            public boolean onLongClick(View caller) {
//                Log.d("VEGETABLES", "Poh-tah-tos");
//                return true;
//            }
//        } );
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
//        MyListItem myListItem = MyListItem.fromCursor(cursor);
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
//public class TodoCursorAdapter extends CursorAdapter {
//    public static final SimpleDateFormat SDF = new SimpleDateFormat("MM/dd/yyyy");
//
//    public TodoCursorAdapter(Context context, Cursor cursor, int flags) {
//        super(context, cursor, 0);
//    }
//
//    // The newView method is used to inflate a new view and return it,
//    // you don't bind any data to the view at this point.
//    @Override
//    public View newView(Context context, Cursor cursor, ViewGroup parent) {
//        return LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false);
//    }
//
//    // The bindView method is used to bind all data to a given view
//    // such as setting the text on a TextView.
//    @Override
//    public void bindView(View view, Context context, Cursor cursor) {
//        // Find fields to populate in inflated template
//        TextView tvBody = (TextView) view.findViewById(R.id.tvItem);
//        TextView tvPriority = (TextView) view.findViewById(R.id.tvPriority);
//        TextView tvDueDate = (TextView) view.findViewById(R.id.tvDueDate);
//        // Extract properties from cursor
//        String body = cursor.getString(cursor.getColumnIndexOrThrow("item"));
//        int priority = cursor.getInt(cursor.getColumnIndexOrThrow("priority"));
//        long duedate = cursor.getLong(cursor.getColumnIndexOrThrow("duedate"));
//        // Populate fields with extracted properties
//        tvBody.setText(body);
//        tvPriority.setText(String.valueOf(priority));
//        tvDueDate.setText(SDF.format(duedate));
//    }
//}