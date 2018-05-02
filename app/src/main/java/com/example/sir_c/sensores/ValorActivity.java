package com.example.sir_c.sensores;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ValorActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager = null;
    private Sensor sensorDeTemperatura = null;
    private Sensor sensorDeProximidad = null;
    private Sensor sensorDeLuz = null;
    private Sensor sensorAcelerometro = null;
    private Sensor sensorDeOrientacion = null;
    private Sensor sensorGyroscopio = null;
    private TextView textViewAcelerometro = null;
    private TextView textViewOrientacion = null;
    private LinearLayout llFondo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valor);


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorDeProximidad = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorDeTemperatura = sensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE);
        sensorDeLuz = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorAcelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorDeOrientacion = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        sensorGyroscopio = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        llFondo = (LinearLayout) findViewById(R.id.llSensor);

        if(!(sensorAcelerometro == null)){
            sensorManager.registerListener(this, sensorAcelerometro, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if(!(sensorDeProximidad == null)){
            sensorManager.registerListener(this, sensorDeProximidad, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if(!(sensorDeLuz == null)){
            sensorManager.registerListener(this, sensorDeLuz, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if(!(sensorDeTemperatura == null)){
            sensorManager.registerListener(this, sensorDeTemperatura, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if(!(sensorDeOrientacion == null)){
            sensorManager.registerListener(this, sensorDeOrientacion, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if(!(sensorGyroscopio == null)){
            sensorManager.registerListener(this, sensorGyroscopio, SensorManager.SENSOR_DELAY_NORMAL);
        }


        textViewAcelerometro = (TextView) findViewById(R.id.sensorDeMovimiento);


        textViewOrientacion = (TextView) findViewById(R.id.sensorDeOrientacion);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this){
            float[] masData;
            float x;
            float y;
            float z;

            switch(event.sensor.getType()){
                case Sensor.TYPE_PROXIMITY:
                    masData = event.values;
                    if(masData[0]==0){
                        llFondo.setBackgroundColor(Color.RED);
                    }
                    else{
                        llFondo.setBackgroundColor(Color.BLUE);
                    }
                    break;
                case Sensor.TYPE_ACCELEROMETER:
                    masData = event.values;
                    x = masData[0];
                    y = masData[1];
                    z = masData[2];
                    textViewAcelerometro.setText("x: " + x + "\ny: "+y + "\nz: "+z);
                    break;
                case Sensor.TYPE_ORIENTATION:
                    masData = event.values;
                    x = masData[0];
                    y = masData[1];
                    textViewOrientacion.setText("x: " + x + "\ny: "+y);
                    break;
                case Sensor.TYPE_GYROSCOPE:
                    if(event.values[2] > 0.5f) { // anticlockwise
                        llFondo.setBackgroundColor(Color.GREEN);
                    } else if(event.values[2] < -0.5f) { // clockwise
                        llFondo.setBackgroundColor(Color.YELLOW);
                    }
                    break;
            }
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY),SensorManager.SENSOR_DELAY_NORMAL);
    }
}
