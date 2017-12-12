package ch.derbeton.metalldetectorapp;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.client.android.Intents;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.CaptureActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Memory extends AppCompatActivity implements View.OnClickListener {


    // for the Logbuch app
    private static final int SCAN_QR_CODE_REQUEST_CODE = 0;
    private RecyclerView mRecyclerView;
    private int resID_image;
    private int resID_text;
    private String imageName;
    private String imageText;
    private String info_1;
    private String info_2;
    private String info_3;
    private String info_4;
    private String info_5;
    private String info_6;
    private String info_7;
    private String info_8;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    //---------------------------------------------------------------------------------------------------------------------
    // Menu Start
    //---------------------------------------------------------------------------------------------------------------------
    // Für die ganzen Einträge werden weitere Activities erstellt

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem_start = menu.add("Home");
        MenuItem menuItem_logbuch_scanner = menu.add("Daten ins Logbusch schreiben");
        MenuItem menuItem_dechiffrierer = menu.add("Dechiffrierer");
        MenuItem menuItem_schatzkarte = menu.add("Schatzkarte");
        MenuItem menuItem_pixelmaler = menu.add("Pixelmaler");

        menuItem_logbuch_scanner.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

                                                                @Override
                                                                public boolean onMenuItemClick(MenuItem item) {

                                                                    log_memory();

                                                                    return false;
                                                                }
                                                            }

        );


        // Verlinken Start

        menuItem_start.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

                                                      @Override
                                                      public boolean onMenuItemClick(MenuItem item) {
                                                          Intent intent = new Intent(Memory.this, metallDetector.class);
                                                          startActivity(intent);
                                                          return false;
                                                      }
                                                  }

        );


        // Verlinken Dechiffrierer

        menuItem_dechiffrierer.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

                                                              @Override
                                                              public boolean onMenuItemClick(MenuItem item) {
                                                                  Intent intent = new Intent(Memory.this, Dechiffrierer.class);
                                                                  startActivity(intent);
                                                                  return false;
                                                              }
                                                          }

        );

        // verlinken Schatzkarte
        menuItem_schatzkarte.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

                                                            @Override
                                                            public boolean onMenuItemClick(MenuItem item) {
                                                                Intent intent = new Intent(Memory.this, Schatzkarte.class);
                                                                startActivity(intent);
                                                                return false;
                                                            }
                                                        }

        );

        // verlinken Pixelmaler
        menuItem_pixelmaler.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

                                                           @Override
                                                           public boolean onMenuItemClick(MenuItem item) {
                                                               Intent intent = new Intent(Memory.this, Pixelmaler.class);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        Button one = (Button) findViewById(R.id.photo_button_1);
        one.setOnClickListener(this); // calling onClick() method
        Button two = (Button) findViewById(R.id.photo_button_2);
        two.setOnClickListener(this);
        Button three = (Button) findViewById(R.id.photo_button_3);
        three.setOnClickListener(this);
        Button four = (Button) findViewById(R.id.photo_button_4);
        four.setOnClickListener(this);
        Button five = (Button) findViewById(R.id.photo_button_5);
        five.setOnClickListener(this);
        Button six = (Button) findViewById(R.id.photo_button_6);
        six.setOnClickListener(this);
        Button seven = (Button) findViewById(R.id.photo_button_7);
        seven.setOnClickListener(this);
        Button eight = (Button) findViewById(R.id.photo_button_8);
        eight.setOnClickListener(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onResume() {
        super.onResume();

        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File path = cw.getDir("imageDir", Context.MODE_PRIVATE);
        loadImageFromStorage(path);

    }

    // Listen for Button Clicks

    @Override
    public void onClick(View v) {
        // default method for handling onClick Events..

        switch (v.getId()) {

            case R.id.photo_button_1:
                // do your code

                takeQrCodePicture();

                imageName = "image_1";

                imageText = "info_text_1";

                resID_image = getResources().getIdentifier(imageName, "id", getPackageName());

                resID_text = getResources().getIdentifier(imageText, "id", getPackageName());

                break;

            case R.id.photo_button_2:
                // do your code

                takeQrCodePicture();

                imageName = "image_2";

                imageText = "info_text_2";

                resID_image = getResources().getIdentifier(imageName, "id", getPackageName());

                resID_text = getResources().getIdentifier(imageText, "id", getPackageName());

                break;

            case R.id.photo_button_3:
                // do your code

                takeQrCodePicture();

                imageName = "image_3";

                imageText = "info_text_3";

                resID_image = getResources().getIdentifier(imageName, "id", getPackageName());

                resID_text = getResources().getIdentifier(imageText, "id", getPackageName());

                break;

            case R.id.photo_button_4:
                // do your code

                takeQrCodePicture();

                imageName = "image_4";

                imageText = "info_text_4";

                resID_image = getResources().getIdentifier(imageName, "id", getPackageName());

                resID_text = getResources().getIdentifier(imageText, "id", getPackageName());

                break;

            case R.id.photo_button_5:
                // do your code

                takeQrCodePicture();

                imageName = "image_5";

                imageText = "info_text_5";

                resID_image = getResources().getIdentifier(imageName, "id", getPackageName());

                resID_text = getResources().getIdentifier(imageText, "id", getPackageName());

                break;

            case R.id.photo_button_6:
                // do your code

                takeQrCodePicture();

                imageName = "image_6";

                imageText = "info_text_6";

                resID_image = getResources().getIdentifier(imageName, "id", getPackageName());

                resID_text = getResources().getIdentifier(imageText, "id", getPackageName());

                break;


            case R.id.photo_button_7:
                // do your code

                takeQrCodePicture();

                imageName = "image_7";

                imageText = "info_text_7";

                resID_image = getResources().getIdentifier(imageName, "id", getPackageName());

                resID_text = getResources().getIdentifier(imageText, "id", getPackageName());

                break;


            case R.id.photo_button_8:
                // do your code

                takeQrCodePicture();

                imageName = "image_8";

                imageText = "info_text_8";

                resID_image = getResources().getIdentifier(imageName, "id", getPackageName());

                resID_text = getResources().getIdentifier(imageText, "id", getPackageName());

                break;


            default:

                break;
        }

    }


    // Foto aufnehmen / Code auslesen
    public void takeQrCodePicture() {

        IntentIntegrator integrator = new IntentIntegrator(this);
        //integrator.setCaptureActivity(MyCaptureActivity.class);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setOrientationLocked(false);
        integrator.addExtra(Intents.Scan.BARCODE_IMAGE_ENABLED, true);
        integrator.initiateScan();
    }

    public class MyCaptureActivity extends CaptureActivity {
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IntentIntegrator.REQUEST_CODE
                && resultCode == RESULT_OK) {


            Bundle extras = data.getExtras();
            String path = extras.getString(
                    Intents.Scan.RESULT_BARCODE_IMAGE_PATH);

            // Ein Bitmap zur Darstellung erhalten wir so:
            Bitmap bmp = BitmapFactory.decodeFile(path);

            // Bitmap Abspeichern
            saveToInternalStorage(bmp);


            String code = extras.getString(
                    Intents.Scan.RESULT);

            switch (imageText) {

                case "info_text_1":

                    info_1 = code;

                    break;

                case "info_text_2":

                    info_2 = code;

                    break;

                case "info_text_3":

                    info_3 = code;

                    break;

                case "info_text_4":

                    info_4 = code;

                    break;

                case "info_text_5":

                    info_5 = code;

                    break;

                case "info_text_6":

                    info_6 = code;

                    break;

                case "info_text_7":

                    info_7 = code;

                    break;

                case "info_text_8":

                    info_8 = code;

                    break;

                default:

                    break;

            }


            // Unser Teil der Auswertung
            //Einfügen des QRCODES
            TextView info_text = (TextView) findViewById(resID_text);
            info_text.setText(code);
            // Einfügen des Bildes
            ImageView image = (ImageView) findViewById(resID_image);
            image.setImageBitmap(bmp);
            //Bitmap bitmap_1 = BitmapFactory.decodeFile(path);
            //image_2.setImageBitmap(bitmap_1);

        }
    }

    // Format für Logbuch erstellen
    private void log_memory() {
        Intent intent = new Intent("ch.appquest.intent.LOG");

        String[] bildpaar_1 = {info_1, info_2};
        String[] bildpaar_2 = {info_3, info_4};
        String[] bildpaar_3 = {info_5, info_6};
        String[] bildpaar_4 = {info_7, info_8};

        String Paar1 = "";
        String Paar2 = "";
        String Paar3 = "";
        String Paar4 = "";


        if (bildpaar_4[0] != null && bildpaar_4[1] != null) {
            Paar4 = String.format("[\" " + bildpaar_4[0] + " \", \" " + bildpaar_4[1] + " \"]");
        }

        if (bildpaar_3[0] != null && bildpaar_3[1] != null) {
            if (Paar4 != "") {
                Paar3 = String.format("[\" " + bildpaar_3[0] + " \", \" " + bildpaar_3[1] + " \"],");
            }
            //Prüft ob Komma am Schluss nötig
            else {
                Paar3 = String.format("[\" " + bildpaar_3[0] + " \", \" " + bildpaar_3[1] + " \"]");
            }
        }

        if (bildpaar_2[0] != null && bildpaar_2[1] != null) {
            if (Paar4 != "" || Paar3 != "") {
                Paar2 = String.format("[\" " + bildpaar_2[0] + " \", \" " + bildpaar_2[1] + " \"],");
            }
            //Prüft ob Komma am Schluss nötig
            else {
                Paar2 = String.format("[\" " + bildpaar_2[0] + " \", \" " + bildpaar_2[1] + " \"]");
            }
        }

        if (bildpaar_1[0] != null && bildpaar_1[1] != null) {
            if (Paar4 != "" || Paar3 != "" || Paar2 != "") {
                Paar1 = String.format("[\" " + bildpaar_1[0] + " \", \" " + bildpaar_1[1] + " \"],");
            }
            //Prüft ob Komma am Schluss nötig
            else {
                Paar1 = String.format("[\" " + bildpaar_1[0] + " \", \" " + bildpaar_1[1] + " \"]");
            }
        }

        String content = String.format(Paar1 + Paar2 + Paar3 + Paar4);
        String newString = String.format("{\"task\": \"Memory\", \"solution\": [" + content + "] }");


        intent.putExtra("ch.appquest.logmessage", newString);

        startActivity(intent);
    }

    // BMPs Abspeichern
    private String saveToInternalStorage(Bitmap bmp) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, "profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    // BMPs Ausgeben

    private void loadImageFromStorage(File path) {

        try {
            File f = new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            ImageView img = (ImageView) findViewById(R.id.image_1);
            img.setImageBitmap(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private String saveText(String string) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/textDir
        File directory = cw.getDir("textDir", Context.MODE_PRIVATE);
        // Create textDir
        File mypath = new File(directory, "text.txt");

        FileOutputStream fos = null;
        try (PrintWriter p = new PrintWriter(new FileOutputStream("text.txt", true))) {
            p.println("Textspeichertest");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    private void loadTextFromStorage() {
    }
}
