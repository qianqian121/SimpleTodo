package com.codepath.simpletodo.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.codepath.simpletodo.models.Item;

/**
 * Created by qiming on 6/21/2016.
 */
public class ItemsDatabaseHelper extends SQLiteOpenHelper{
    private static ItemsDatabaseHelper sInstance;
    // Database Info
    private static final String DATABASE_NAME = "itemsDataBase";
    private static final int DATABASE_VERSION = 1;
    // items table name
    private static final String TABLE_ITEMS = "items";

    // items table columns names
    private static final String KEY_ITEM_ID = "_id";
    private static final String KEY_ITEM_TEXT = "item";
    private static final String KEY_ITEM_DUEDATE = "duedate";
    private static final String KEY_ITEM_PRIORITY = "priority";

    // log TAG
    private static final String TAG = "itemsDataBase";

    public static synchronized ItemsDatabaseHelper getsInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ItemsDatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private ItemsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_ITEMS + " (" +
                KEY_ITEM_ID + " INTEGER PRIMARY KEY," +
                //KEY_ITEM_PRIORITY + " INTEGER REFERENCES " +
                KEY_ITEM_PRIORITY + " INTEGER," +
                KEY_ITEM_DUEDATE + " INTEGER," +
                KEY_ITEM_TEXT + " TEXT" +
                ")";
        db.execSQL(CREATE_ITEMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
            onCreate(db);
        }
    }

    // Insert an item into the database
    public void addItem(Item item) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_ITEM_ID, item.id);
            values.put(KEY_ITEM_PRIORITY, item.priority);
            values.put(KEY_ITEM_DUEDATE, item.dueDate.getTime());
            values.put(KEY_ITEM_TEXT, item.text);

            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            db.insertOrThrow(TABLE_ITEMS, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add item to database");
        } finally {
            db.endTransaction();
        }
    }

    // Update an item in the database
    public void updateItem(Item item) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            //values.put(KEY_ITEM_ID, item.id);
            values.put(KEY_ITEM_PRIORITY, item.priority);
            values.put(KEY_ITEM_DUEDATE, item.dueDate.getTime());
            values.put(KEY_ITEM_TEXT, item.text);

            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            //db.insertOrThrow(TABLE_ITEMS, null, values);
            db.update(TABLE_ITEMS, values,"_id="+item.id, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add item to database");
        } finally {
            db.endTransaction();
        }
    }

    public void deleteItem(long _id) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {
            db.delete(TABLE_ITEMS, "_id="+_id, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add item to database");
        } finally {
            db.endTransaction();
        }
    }
    /*
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();

        // SELECT * from ITEMS
    }*/
}
