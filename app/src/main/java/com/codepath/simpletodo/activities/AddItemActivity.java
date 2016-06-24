package com.codepath.simpletodo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.codepath.simpletodo.models.Item;
import com.codepath.simpletodo.utils.ItemsDatabaseHelper;
import com.codepath.simpletodo.R;

import java.util.Calendar;

/**
 * Created by qiming on 6/21/2016.
 */
public class AddItemActivity extends AppCompatActivity {

    public void onAddNewItem(View v) {
        EditText mtItemEdit = (EditText)findViewById(R.id.mtNewItem);
        String editedItem = mtItemEdit.getText().toString();
        long id = getIntent().getLongExtra("itemsCnt", 0);

        final Calendar calendar = Calendar.getInstance();
        DatePicker dueDatePicker = (DatePicker)findViewById(R.id.datePicker);
        calendar.set(dueDatePicker.getYear(),
                dueDatePicker.getMonth(),
                dueDatePicker.getDayOfMonth());

        ItemsDatabaseHelper helper = ItemsDatabaseHelper.getsInstance(this);
        helper.addItem(new Item((int)id, editedItem, calendar.getTime()));
        Intent data = new Intent();

        setResult(RESULT_OK, data);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
    }
}