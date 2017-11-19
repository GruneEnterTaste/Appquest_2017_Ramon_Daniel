package ch.derbeton.metalldetectorapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.graphics.Color;
import org.json.JSONObject;

public class metallDetector extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor metallSensor;
    private TextView textView;
    private ProgressBar progressBar;
    // for the camera app
    private static final int CAMERA_REQUEST = 1888;
    // for the Logbuch app
    private static final int SCAN_QR_CODE_REQUEST_CODE = 0;

//---------------------------------------------------------------------------------------------------------------------
    // Menu Start
//---------------------------------------------------------------------------------------------------------------------
    // Für die ganzen Einträge werden weitere Activities erstellt

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem_start = menu.add("Home");
        MenuItem menuItem_logbuch_scanner = menu.add("Logbuch Scanner");
        MenuItem menuItem_dechiffrierer = menu.add("Dechiffrierer");
        MenuItem menuItem_memory = menu.add("Memory");
        MenuItem menuItem_schatzkarte = menu.add("Schatzkarte");
        MenuItem menuItem_pixelmaler = menu.add("Pixelmaler");

        menuItem_logbuch_scanner.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent_barcode = new Intent("com.google.zxing.client.android.SCAN");
                intent_barcode.putExtra("SCAN_MODE", "QR_CODE_MODE");
                startActivityForResult(intent_barcode, SCAN_QR_CODE_REQUEST_CODE);
                return false;
            }
        }

        );

        // Verlinken Dechiffrierer

        menuItem_dechiffrierer.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

         @Override
         public boolean onMenuItemClick(MenuItem item) {
             Intent intent = new Intent(metallDetector.this, Dechiffrierer.class);
             startActivity(intent);
             return false;
         }
        }

        );

        // Verlinken Start

        menuItem_start.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(metallDetector.this, metallDetector.class);
                startActivity(intent);
                return false;
            }
        }
        );

        // Verlinken Memory

        menuItem_memory.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

                                                       @Override
                                                       public boolean onMenuItemClick(MenuItem item) {
                                                           Intent intent = new Intent(metallDetector.this, Memory.class);
                                                           startActivity(intent);
                                                           return false;
                                                       }
                                                   }

        );

        // verlinken Schatzkarte
        menuItem_schatzkarte.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

                                                       @Override
                                                       public boolean onMenuItemClick(MenuItem item) {
                                                           Intent intent = new Intent(metallDetector.this, Schatzkarte.class);
                                                           startActivity(intent);
                                                           return false;
                                                       }
                                                   }

        );

        return super.onCreateOptionsMenu(menu);
    }
//---------------------------------------------------------------------------------------------------------------------
    //Menu Ende
//---------------------------------------------------------------------------------------------------------------------

    // Scannvorgang in String speichern --> Barcodescanner

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == SCAN_QR_CODE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String logMsg = intent.getStringExtra("SCAN_RESULT");
                log(logMsg);
            }
        }
    }


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

    // Format für Logbuch erstellen
    private void log(String qrCode) {
        Intent intent = new Intent("ch.appquest.intent.LOG");

        // Achtung, je nach App wird etwas anderes eingetragen
        String logmessage = (String) qrCode;


        String newString = String.format("{\"task\": \"Metalldetektor\", \"solution\": \"" + logmessage + " \"}");

        intent.putExtra("ch.appquest.logmessage", newString);

        startActivity(intent);
    }

}
