package me.reider45.schoolify;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import me.reider45.schoolify.fragments.WeatherFragment;

/**
 * Created by Reid.
 */
public class WeatherFetcher extends AsyncTask<String, Void, String> {

    private final String queryString = "http://api.openweathermap.org/data/2.5/weather?q=%CITY%&APPID=f2b901562ea11c7a366addfadffa0496";

    public WeatherFragment weatherFragment;

    public WeatherFetcher(WeatherFragment wf) {
        this.weatherFragment = wf;
    }
    private String name;

    @Override
    protected String doInBackground(String... urls) {
        // Get the URL used (city name)
        name = urls[0].toLowerCase();
        BufferedReader reader;
        try {
            // Read through the website's data on that city
            URL url = new URL(queryString.replace("%CITY%", name));
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);
            reader.close();
            return buffer.toString();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        // Attempt to view result
        if(result != null) {
            try {
                JSONObject jsonResult = new JSONObject(result);

                // Get the Objects from JSON, and convert them into user-friendly text
                double temperature = jsonResult.getJSONObject("main").getDouble("temp");
                String description = jsonResult.getJSONArray("weather").getJSONObject(0).getString("description");
                String localName = jsonResult.getString("name");

                if(!localName.equalsIgnoreCase(name)) {
                    // Make sure there are no random abbreviations ax AB returns Apple Biscuit County
                    weatherFragment.cantLoad();
                    return;
                }

                temperature = convert(temperature);

                // Set the labels
                weatherFragment.lblWeatherTemperature.setText(temperature + " degrees");
                weatherFragment.lblWeatherForecast.setText(Utilities.properCase(description));
                description = description.toLowerCase();

                // Set the image
                if (description.contains("sun") || description.contains("clear")) {
                    weatherFragment.imgWeather.setImageResource(R.drawable.sun);
                } else if (description.contains("rain")) {
                    weatherFragment.imgWeather.setImageResource(R.drawable.rain);
                } else {
                    weatherFragment.imgWeather.setImageResource(R.drawable.clouds);
                }

            } catch (JSONException e) {
                e.printStackTrace();
                weatherFragment.cantLoad();
            }
        } else {
            weatherFragment.cantLoad();
        }
    }

    // Convert between units (K to F)
    public double convert(double x) {
        return Math.round(((x - 273.15) * 1.8000) + 32.00);
    }
}
