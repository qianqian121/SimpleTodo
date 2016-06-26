package com.codepath.simpletodo.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;

import com.codepath.simpletodo.R;
import com.codepath.simpletodo.adapters.TodoCursorAdapter;
import com.codepath.simpletodo.fragments.EditItemDialogFragment;
import com.codepath.simpletodo.models.Item;
import com.codepath.simpletodo.utils.ItemsDatabaseHelper;

import java.sql.Date;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements EditItemDialogFragment.EditItemDialogListener{
    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    TodoCursorAdapter todoAdapter;
    RecyclerView rvItems;
    private final int REQUEST_CODE = 20;
    private int editPos;
    private long itemsCnt;
    private ItemsDatabaseHelper mItemsDBHelper;

    public void onAddItem(View v) {
        Intent it = new Intent(MainActivity.this, AddItemActivity.class);
        int cnt = todoAdapter.getItemCount();
        itemsCnt = todoAdapter.getItemId(cnt - 1);
        it.putExtra("itemsCnt", itemsCnt + 1);
        startActivityForResult(it, REQUEST_CODE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find ListView to populate
        rvItems = (RecyclerView) findViewById(R.id.rvItems);
        rvItems.setLayoutManager(new LinearLayoutManager(this));

        // Get singleton instance of database
        mItemsDBHelper = ItemsDatabaseHelper.getsInstance(this);
        ItemsDatabaseHelper helper = mItemsDBHelper;

        SQLiteDatabase db = helper.getWritableDatabase();
        //helper.onUpgrade(db,0,1);
        Cursor todoCursor = db.rawQuery("SELECT * FROM " + "items", null);  // from table

        // Setup cursor adapter using cursor
        todoAdapter = new TodoCursorAdapter(this, todoCursor);
        setupListViewListener();

        // Attach cursor adapter to the ListView
        rvItems.setAdapter(todoAdapter);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Get singleton instance of database
            ItemsDatabaseHelper helper = ItemsDatabaseHelper.getsInstance(this);

            SQLiteDatabase db = helper.getWritableDatabase();
            Cursor todoCursor = db.rawQuery("SELECT * FROM " + "items", null);  // from table

            // Setup cursor adapter using cursor
            todoAdapter.changeCursor(todoCursor);
        }
    }

    private void setDataChanged() {
        ItemsDatabaseHelper helper = ItemsDatabaseHelper.getsInstance(this);

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor todoCursor = db.rawQuery("SELECT * FROM " + "items", null);  // from table

        // Setup cursor adapter using cursor
        todoAdapter.changeCursor(todoCursor);
    }

    private void showEditDialog(Item editItem) {
        FragmentManager fm = getSupportFragmentManager();
        EditItemDialogFragment editNameDialogFragment = EditItemDialogFragment.newInstance(editItem);
        editNameDialogFragment.show(fm, "activity_edit_item");
    }

    private void setupListViewListener() {
        todoAdapter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
//                long id = rvItems.getChildItemId(view);
                int position = rvItems.getChildAdapterPosition(view);
                Cursor cursor = todoAdapter.getCursor(position);
                showEditDialog(new Item(cursor.getInt(0), cursor.getString(3), new Date(cursor.getLong(2))));
            }
        });

        todoAdapter.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                int position = rvItems.getChildAdapterPosition(view);
//                long _id = rvItems.getChildItemId(view);
                long _id = todoAdapter.getItemId(position);
                mItemsDBHelper.deleteItem(_id);
                setDataChanged();
                return true;
            }
        });
//        rvItems.setOnItemClickListener(
//                new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        long id = todoAdapter.getItemId(i);
//                        Cursor cursor = (Cursor)rvItems.get(i);
//                        showEditDialog(new Item((int)id, cursor.getString(3), new Date(cursor.getLong(2))));
//                    }
//                }
//        );
//        rvItems.setOnItemLongClickListener(
//                new AdapterView.OnItemLongClickListener() {
//                    @Override
//                    public boolean onItemLongClick(AdapterView<?> adapter,
//                                                   View item, int pos, long id) {
//                        long _id = todoAdapter.getItemId(pos);
//                        mItemsDBHelper.deleteItem(_id);
//                        setDataChanged();
//                        return true;
//                    }
//                }
//        );
    }

    @Override
    public void onFinishEditDialog(Item editItem) {
        mItemsDBHelper.updateItem(editItem);
        setDataChanged();
    }
}
