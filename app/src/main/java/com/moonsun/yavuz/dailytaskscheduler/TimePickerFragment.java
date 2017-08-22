package com.moonsun.yavuz.dailytaskscheduler;

/**
 * Created by yavuz on 8/22/2017.
 */

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    public String time;
    TextView mResultText;

    public TimePickerFragment(TextView textView) {
        mResultText = textView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it

        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        time = Integer.toString(hourOfDay) + ":" + Integer.toString(minute);/*CONVERT YOUR TIME FROM hourOfDay and minute*/
        mResultText.setText(time);
    }
}
