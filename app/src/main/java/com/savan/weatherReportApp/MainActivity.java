package com.savan.weatherReportApp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnCityId,btnGetWeatherById,btnGetWeatherByName;
    private EditText etDataInput;
    private ListView lvWeatherReport;
    final WeatherDataService weatherDataService = new WeatherDataService(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * assign values to each controls in the layout
         */
        btnCityId = (Button) findViewById(R.id.btnCityId);
        btnGetWeatherById = (Button) findViewById(R.id.useCityId);
        btnGetWeatherByName = (Button) findViewById(R.id.useCityName);
        etDataInput = findViewById(R.id.cityDataInput);
        lvWeatherReport = findViewById(R.id.lView);

        //click listener of the buttons
        btnCityId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weatherDataService.getCityId(etDataInput.getText().toString(), new WeatherDataService.VolleyResponseListener() {
                    @Override
                    public void onError(String response) {
                        Toast.makeText(MainActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();        
                    }

                    @Override
                    public void onResponse(String cityId) {
                        Toast.makeText(MainActivity.this, "Return id : "+ cityId, Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

        btnGetWeatherById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weatherDataService.getCityForecastByID(etDataInput.getText().toString(), new WeatherDataService.ForeCastByIDResponse() {
                    @Override
                    public void onError(String message) {

                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModels) {
                        //put the list in the listview control
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,weatherReportModels);
                        lvWeatherReport.setAdapter(arrayAdapter);

                    }
                });
            }
        });

        btnGetWeatherByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    weatherDataService.getCityForecastByName(etDataInput.getText().toString(), new WeatherDataService.GetCityNameForecastByNameCallBack() {
                    @Override
                    public void onError(String message) {

                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModels) {
                        //put the list in the listview control
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,weatherReportModels);
                        lvWeatherReport.setAdapter(arrayAdapter);

                    }
                });

            }
        });

    }

}













