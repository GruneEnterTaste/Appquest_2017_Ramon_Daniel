package ch.derbeton.metalldetectorapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class Dechiffrierer extends AppCompatActivity {

    // for the camera app
    private static final int CAMERA_REQUEST = 1888;
    // for the Logbuch app
    private static final int SCAN_QR_CODE_REQUEST_CODE = 0;

    private String logDechiffriererText;

    private ImageView imageView;
    //---------------------------------------------------------------------------------------------------------------------
    // Menu Start
//---------------------------------------------------------------------------------------------------------------------
    // Für die ganzen Einträge werden weitere Activities erstellt

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem_start = menu.add("Home");
        MenuItem menuItem_logbuch_scanner = menu.add("Logbuch Scanner");
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

        // Verlinken Start

        menuItem_start.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

                                                      @Override
                                                      public boolean onMenuItemClick(MenuItem item) {
                                                          Intent intent = new Intent(Dechiffrierer.this, metallDetector.class);
                                                          startActivity(intent);
                                                          return false;
                                                      }
                                                  }

        );

        // Verlinken Memory

        menuItem_memory.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

                                                       @Override
                                                       public boolean onMenuItemClick(MenuItem item) {
                                                           Intent intent = new Intent(Dechiffrierer.this, Memory.class);
                                                           startActivity(intent);
                                                           return false;
                                                       }
                                                   }
        );

        // verlinken Schatzkarte
        menuItem_schatzkarte.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

                                                            @Override
                                                            public boolean onMenuItemClick(MenuItem item) {
                                                                Intent intent = new Intent(Dechiffrierer.this, Schatzkarte.class);
                                                                startActivity(intent);
                                                                return false;
                                                            }
                                                        }

        );

        // verlinken Pixelmaler
        menuItem_pixelmaler.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

                                                           @Override
                                                           public boolean onMenuItemClick(MenuItem item) {
                                                               Intent intent = new Intent(Dechiffrierer.this, Pixelmaler.class);
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

    // main part of the camera apps

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dechiffrer);
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


        // Make the Log Button to show the input and Submit field

        Button btnLogbuchDechiffrierer = (Button) this.findViewById(R.id.btnLogbuchDechiffrierer);
        final EditText txtLogbuchDechiffrierer = (EditText) this.findViewById(R.id.txtLogbuchDechiffrierer);

        btnLogbuchDechiffrierer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                //code to run
                logDechiffriererText = txtLogbuchDechiffrierer.getText().toString();
                log(logDechiffriererText);


            }
        });
    }

    // Logbuch Classe erstellen

    // Format für Logbuch erstellen
    private void log(String logDechiffriererText) {
        Intent intent = new Intent("ch.appquest.intent.LOG");

        // Achtung, je nach App wird etwas anderes eingetragen
        String logmessage = (String) logDechiffriererText;


        String newString = String.format("{\"task\": \"Dechiffrierer\", \"solution\": \"" + logmessage + " \"}");

        intent.putExtra("ch.appquest.logmessage", newString);

        startActivity(intent);
    }


        /*final Button button = (Button) findViewById(R.id.logVisible);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                View logSend = findViewById(R.id.logSend);
                logSend.setVisibility(View.VISIBLE);

                View textInput = findViewById(R.id.textInput);
                textInput.setVisibility(View.VISIBLE);
            }
        });*/

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
                int a = (p>>24);

                //get red
                int r = (p>>16) & 0xff0000;

                //get green
                int g = (p>>8) & 0xff0000;

                //get blue
                int b = (p & 0xff0000);


                p = (a<<24) | ((r<<16) | (g<<8) | b);


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

