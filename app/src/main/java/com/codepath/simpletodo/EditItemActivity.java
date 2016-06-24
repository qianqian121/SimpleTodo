package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class EditItemActivity extends AppCompatActivity {

    public void onSaveItem(View v) {
        EditText mtItemEdit = (EditText)findViewById(R.id.mtItemEdit);
        String editedItem = mtItemEdit.getText().toString();
        long id = getIntent().getLongExtra("editItemId", 0);

        final Calendar calendar = Calendar.getInstance();
        DatePicker dueDatePicker = (DatePicker)findViewById(R.id.datePicker2);
        calendar.set(dueDatePicker.getYear(),
                dueDatePicker.getMonth(),
                dueDatePicker.getDayOfMonth());

        ItemsDatabaseHelper helper = ItemsDatabaseHelper.getsInstance(this);
        helper.updateItem(new Item((int)id, editedItem, calendar.getTime()));
        Intent data = new Intent();

        setResult(RESULT_OK, data);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        long dueDate = getIntent().getLongExtra("editItemDueDate", 0);
        String editItemText = getIntent().getStringExtra("editItemText");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        EditText mtItemEdit = (EditText)findViewById(R.id.mtItemEdit);
        mtItemEdit.setText(editItemText);
        DatePicker dueDatePicker = (DatePicker)findViewById(R.id.datePicker2);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dueDate);
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        dueDatePicker.updateDate(year,month,dayOfMonth);
    }
}
