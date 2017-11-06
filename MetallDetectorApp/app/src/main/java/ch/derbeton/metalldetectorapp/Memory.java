package ch.derbeton.metalldetectorapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.GridLayoutManager;
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
import java.util.ArrayList;
import java.util.List;

public class Memory extends AppCompatActivity implements View.OnClickListener {


    // for the Logbuch app
    private static final int SCAN_QR_CODE_REQUEST_CODE = 0;
    private RecyclerView mRecyclerView;
    public int resID_image;
    public int resID_text;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


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


        // Verlinken Memory

        menuItem_memory.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

                                                       @Override
                                                       public boolean onMenuItemClick(MenuItem item) {
                                                           Intent intent = new Intent(Memory.this, Memory.class);
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

    // Listen for Button Clicks

    @Override
    public void onClick(View v) {
        // default method for handling onClick Events..

        switch (v.getId()) {

            case R.id.photo_button_1:
                // do your code

                takeQrCodePicture();

                String imageName = "image_1";

                String imageText = "info_text_1";

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

    public class MyCaptureActivity extends CaptureActivity { }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IntentIntegrator.REQUEST_CODE
                && resultCode == RESULT_OK) {


            Bundle extras = data.getExtras();
            String path = extras.getString(
                    Intents.Scan.RESULT_BARCODE_IMAGE_PATH);

            // Ein Bitmap zur Darstellung erhalten wir so:
            Bitmap bmp_1 = BitmapFactory.decodeFile(path);




            String code_1 = extras.getString(
                    Intents.Scan.RESULT);



            // Unser Teil der Auswertung
            //Einfügen des QRCODES
            TextView info_text_1 = (TextView) findViewById(resID_text);
            info_text_1.setText(code_1);
            // Einfügen des Bildes
            ImageView image_1 = (ImageView) findViewById(resID_image);
            image_1.setImageBitmap(bmp_1);
            //Bitmap bitmap_1 = BitmapFactory.decodeFile(path);
            //image_2.setImageBitmap(bitmap_1);

        }
    }


    // Ende

}
