package com.menu.androidcourseproject.ui.register;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.EditText;

import com.menu.androidcourseproject.R;

import java.util.Calendar;

public class DateAndTime {
    private Calendar dateTime = Calendar.getInstance();
    private Context context;

    public DateAndTime(Context context) {
        this.context = context;
    }

    public void updateDate(final EditText text) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, R.style.MySpinnerDatePickerStyle, (view, year, month, dayOfMonth) -> {
            month = month + 1;
            text.setText(dayOfMonth + "/" + month + "/" + year);

        }, dateTime.get(Calendar.YEAR), dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}