package ch.derbeton.metalldetector;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.FloatMath;
import android.widget.TextView;

public class MainActivityMetallDetector extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor metallSensor;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_metall_detector);
        textView = (TextView) findViewById(R.id.textView1);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        metallSensor = sensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD).get(0);
    }
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, metallSensor, sensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
    public void onSensorChanged(SensorEvent event) {
        float[] mag = event.values;
        double betrag = Math.sqrt(mag[0] * mag[0] + mag[1] * mag[1] + mag[2] * mag[2]);
        textView.setText("MetallStatus: " + betrag);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}