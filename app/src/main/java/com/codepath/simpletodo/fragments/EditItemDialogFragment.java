package com.codepath.simpletodo.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.codepath.simpletodo.R;
import com.codepath.simpletodo.models.Item;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by qiming on 6/23/2016.
 */
public class EditItemDialogFragment extends DialogFragment{

    private Item mEditItem;
    private EditItemDialogListener mEditItemDialogListener;
//    // 1. Defines the listener interface with a method passing back data result.
//
    public interface EditItemDialogListener {
        void onFinishEditDialog(Item editItem);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mEditItemDialogListener = (EditItemDialogListener) activity;
    }

    public EditItemDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }
    public static EditItemDialogFragment newInstance(Item item) {
        EditItemDialogFragment frag = new EditItemDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable("editItem", item);
        frag.setArguments(args);

        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_edit_item, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEditItem = (Item) getArguments().getSerializable("editItem");
        Date dueDate = mEditItem.dueDate;
        String editItemText = mEditItem.text;
        final EditText mtItemEdit = (EditText)view.findViewById(R.id.mtItemEdit);
        mtItemEdit.setText(editItemText);
        DatePicker dueDatePicker = (DatePicker)view.findViewById(R.id.datePicker2);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(dueDate);
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        dueDatePicker.updateDate(year,month,dayOfMonth);

        Button btnSave = (Button)view.findViewById(R.id.btnSaveEdited);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditItem.text = mtItemEdit.getText().toString();
                mEditItem.dueDate = calendar.getTime();
                mEditItemDialogListener.onFinishEditDialog(mEditItem);
                dismiss();
            }
        });
    }
}
