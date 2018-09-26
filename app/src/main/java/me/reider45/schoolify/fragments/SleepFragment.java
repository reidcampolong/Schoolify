package me.reider45.schoolify.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import me.reider45.schoolify.R;

/**
 * Created by Reid.
 */
public class SleepFragment extends Fragment {

    TextView lblWakeUp;
    TimePickerFragment timeFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sleep, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Register fields
        lblWakeUp = (TextView) getActivity().findViewById(R.id.lblWakeUp);

        // To be safe switching between fragments
        if(timeFragment == null || !timeFragment.isAdded())
            timeFragment = new TimePickerFragment();

        // Register button for event
        lblWakeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeFragment.show(getFragmentManager(), "timePicker");
            }
        });
    }

}
