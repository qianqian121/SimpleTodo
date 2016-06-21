package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    public void onSaveItem(View v) {
        EditText mtItemEdit = (EditText)findViewById(R.id.mtItemEdit);
        String editedItem = mtItemEdit.getText().toString();
        Intent data = new Intent();
        data.putExtra("editedItem", editedItem);
        setResult(RESULT_OK, data);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String editItem = getIntent().getStringExtra("editItem");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        EditText mtItemEdit = (EditText)findViewById(R.id.mtItemEdit);
        mtItemEdit.setText(editItem);
    }
}
