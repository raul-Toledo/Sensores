package com.example.sir_c.sensores;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity {

    TextView txvSalida;
    Button btnValor;
    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        txvSalida = (TextView) findViewById(R.id.salida);
        btnValor = (Button) findViewById(R.id.btnValores);
        List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_ALL);

        for (Sensor sensor: listaSensores){
            log(sensor.getName());
        }

        btnValor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iValor = new Intent(getApplicationContext(),ValorActivity.class);
                startActivity(iValor);
                finish();
            }
        });
    }

    private void log (String string){
        string += "\n" + txvSalida.getText().toString();
        txvSalida.setText(string);
    }

}
