package ch.derbeton.metalldetectorapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class metallDetector extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor metallSensor;
    private TextView textView;
    private ProgressBar progressBar;

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metall_detector_layout);
        textView = (TextView) findViewById(R.id.textView1);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);

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
        progressBar.setMax(150);
        progressBar.setProgress((int) betrag);
    }
}
