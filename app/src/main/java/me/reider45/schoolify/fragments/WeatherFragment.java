package me.reider45.schoolify.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import me.reider45.schoolify.R;
import me.reider45.schoolify.WeatherFetcher;

/**
 * Created by Reid.
 */
public class WeatherFragment extends Fragment {

    private Handler handler;
    private int refreshes = 1;
    private String last;

    Button btnRefresh;

    EditText txtLocation;

    public TextView lblWeatherTemperature;
    public TextView lblWeatherForecast;

    public ImageView imgWeather;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Load all fields
        btnRefresh = (Button) getActivity().findViewById(R.id.btnRefresh);

        txtLocation = (EditText) getActivity().findViewById(R.id.txtLocation);

        lblWeatherTemperature = (TextView) getActivity().findViewById(R.id.lblWeatherTemperature);
        lblWeatherForecast = (TextView) getActivity().findViewById(R.id.lblWeatherForecast);

        imgWeather = (ImageView) getActivity().findViewById(R.id.imgWeather);

        last = txtLocation.getText().toString();

        // Query the server
        executeData();

        // Create a handler
        handler = new Handler();

        // Listener to make sure refresh is not spammed for the same location and for refreshing
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(refreshes > 0 && !last.equals(txtLocation.getText().toString())) {
                    refreshes--;
                    executeData();
                } else if(refreshes == 0) {
                    refreshes = -1;

                    handler.postDelayed(new Runnable() {
                        public void run() {
                            refreshes = 1;
                        }
                    }, 5000);
                }
                last = txtLocation.getText().toString();
            }
        });
    }

    public void executeData() {
        // Execute the weather task
        try {
            WeatherFetcher task = new WeatherFetcher(this);
            task.execute(txtLocation.getText().toString().trim());
        } catch(Exception e) {
            e.printStackTrace();
            cantLoad();
        }
    }

    public void cantLoad() {
        // Issue loading weather
        lblWeatherTemperature.setText("Unable to load weather for that location.");
        lblWeatherForecast.setText("");
        imgWeather.setImageDrawable(null);
    }
}
