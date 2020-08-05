package com.example.kiet_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text

class weather_activity : AppCompatActivity() {
    lateinit var txtSunriseTime: TextView;
    lateinit var txtSunsetTime:TextView;
    lateinit var txtWindTime:TextView;
    lateinit var txtPressure:TextView;
    lateinit var txtHumidity:TextView;
    lateinit var txtInfo:TextView
    lateinit var txtLocation:TextView;
    lateinit var txtLastUpdate:TextView;
    lateinit var txtWeatherType:TextView;
    lateinit var txtTemp:TextView;
    lateinit var txtMaxTemp:TextView;
    lateinit var txtMinTemp:TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        idMapping();
    }

    private fun idMapping(){
        txtSunriseTime=findViewById(R.id.txt_sunriseTime);
        txtSunsetTime=findViewById(R.id.txt_sunsetTime);
        txtWindTime=findViewById(R.id.txt_windTime);
        txtPressure=findViewById(R.id.txt_pressureTine);
        txtHumidity=findViewById(R.id.txt_humidityTime);
        txtInfo=findViewById(R.id.txt_info);
        txtLocation=findViewById(R.id.txt_location);
        txtLastUpdate=findViewById(R.id.txt_lastUpdate);
        txtWeatherType=findViewById(R.id.txt_weatherType);
        txtTemp=findViewById(R.id.txt_temperature);
        txtMaxTemp=findViewById(R.id.txt_maxTemp);
        txtMinTemp=findViewById(R.id.txt_minTemp);

    }


}