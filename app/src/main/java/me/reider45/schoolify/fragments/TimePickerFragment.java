package me.reider45.schoolify.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import me.reider45.schoolify.R;

/**
 * Created by Reid .
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    String time;
    TextView label;
    TextView sleep;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Get the labels
        label = (TextView) getActivity().findViewById(R.id.lblWakeUp);
        sleep = (TextView) getActivity().findViewById(R.id.lblSleep);

        // Return the timepicker fragment
        return new TimePickerDialog(getActivity(), this, 7, 00,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        int hour = hourOfDay;
        int minutes = minute;

        // Set the time based on 12 hour format
        time = (hour > 12) ? (hour-12) + ":" + minutes +":PM" : (hour) + ":" + minutes + ":AM";

        // Set the display label
        label.setText(getTime());
        sleep.setText("You should try to fall asleep at " + calculateSleep());
    }

    /**
     * Used to set label
     */
    public String getTime() {
        return formatTime(time) + " (Tap to edit)";
    }

    public String calculateSleep() {
        Date date = new Date();
        date.setHours(Integer.parseInt(time.split(":")[0]));

        // Set hours + 12 since PM
        if(time.split(":")[2].equals("PM"))
            date.setHours(date.getHours() + 12);

        // Get the minutes
        date.setMinutes(Integer.parseInt(time.split(":")[1]));

        // Add 1.5 hours to each date
        List<Date> dates = new ArrayList<>();
        for(int i = 0; i < 5; i++)
            dates.add(
                    new Date(date.getTime() - (270 * 60000) - ( (i * 90) * 60000))
            );

        // Print out each date with an arrow on a new line
        final String spacer = "\n      → ";
        String output = "";
        for(Date d : dates)
            output += spacer + formatDate(d);

        return output;
    }

    /**
     * Format a date
     */
    private String formatDate(Date date) {
        // Get AM or PM
        String ampm = (date.getHours() > 12) ? "PM" : "AM";

        // Hours
        String hours = (date.getHours() > 12) ? String.valueOf(date.getHours() - 12) : String.valueOf(date.getHours());
        // Minutes
        int minutes = date.getMinutes();

        // Hour:Minute
        String dateString = generalFormat(hours+":"+minutes);

        // Used to center AM and PM values
        System.out.println("Modifying " + dateString);
        System.out.println(9-dateString.length());
        for(int i = 1; i < (9-dateString.length()); i++) {
            dateString = dateString.concat(" ");
            System.out.println(i);
        }

        // Hour:Minute AM/PM
        dateString = dateString.concat(ampm);

        return dateString;
    }

    /**
     * Format a time
     */
    private String formatTime(String input) {
        // Get hours
        int hours = Integer.parseInt(input.split(":")[0]);
        // Get minutes
        int minutes = Integer.parseInt(input.split(":")[1]);

        // get AM/PM
        String ampm = input.split(":")[2];

        return generalFormat(hours+":"+minutes+" "+ampm);
    }

    /**
     * General formatter, fixes conversions between 24 and 12 hours.
     */
    private String generalFormat(String input) {
        // Used to change ex. 2:30 to 02:30 for formatting purposes.
        String output = input;
        if(output.startsWith("0:"))
            output = output.replace("0:", "12:");
        if(output.contains(":0"))
            output = output.replace(":0", ":00");
        if(output.split(":")[0].length() == 1)
            output = "0"+output;
        return output;
    }

}
