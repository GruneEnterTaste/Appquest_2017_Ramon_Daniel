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

public class metallDetector extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor metallSensor;
    private TextView textView;
    private ProgressBar progressBar;
    // for the camera app
    private static final int CAMERA_REQUEST = 1888;
    // for the Logbuch app
    private static final int SCAN_QR_CODE_REQUEST_CODE = 0;

    private ImageView imageView;

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

        return super.onCreateOptionsMenu(menu);
    }
//---------------------------------------------------------------------------------------------------------------------
    //Menu Ende
//---------------------------------------------------------------------------------------------------------------------


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metall_detector_layout);
        textView = (TextView) findViewById(R.id.textView1);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        //for the camera app
        this.imageView = (ImageView) this.findViewById(R.id.imageView1);
        Button photoButton = (Button) this.findViewById(R.id.button1);
        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

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

    // main part of the camera apps

    private Bitmap applyFilter(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] data = new int[width * height];

        bitmap.getPixels(data, 0, width, 0, 0, width, height);

        for (int x = 0; x < bitmap.getWidth(); x++) {
            for (int y = 0; y < bitmap.getHeight(); y++) {

                // multiple every pixel with a red one and the amount of green and blue

                int index = y*width+x;

                int p = data[index];

                //get alpha
                int a = 255;

                //get red
                int r = (p>>16) & 0xff;

                //get green
                int g = (p>>8) & 0xff;

                //get blue
                int b = (p & 0xff);


                p = a<<24 | Color.red(r<<16) | (g<<8)*0 | b*0;


                //fill in the color into the data array

                data[index] = p;
            }
        }
        //return the new Bitmap

        return Bitmap.createBitmap(data, width, height, Bitmap.Config.ARGB_8888);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == AppCompatActivity.RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            Bitmap new_bitmap = applyFilter(bitmap);
            imageView.setImageBitmap(new_bitmap);
        }
    }

}
