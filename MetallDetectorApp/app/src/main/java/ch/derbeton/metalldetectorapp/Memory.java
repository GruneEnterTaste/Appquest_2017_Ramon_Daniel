package ch.derbeton.metalldetectorapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    public String nummerierung;
    public int resID;
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






        /*Button photo_button_1 = (Button) this.findViewById(R.id.photo_button_1);

        photo_button_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                nummerierung = v.getId();

            }
        }); */
    }

    // Listen for Button Clicks

    @Override
    public void onClick(View v) {
        // default method for handling onClick Events..

        switch (v.getId()) {

            case R.id.photo_button_1:
                // do your code

                takeQrCodePicture();

                nummerierung = "image_1";

                int resID = getResources().getIdentifier(nummerierung, "id", getPackageName());

                break;

            case R.id.photo_button_2:
                // do your code

                takeQrCodePicture();

                nummerierung = "image_2";

                break;


            default:

                break;
        }

    }

    /*public void OnClick_button_1_2 (View view){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }*/


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
            TextView info_text_1 = (TextView) findViewById(R.id.info_text_1);
            info_text_1.setText(code_1);
            // Einfügen des Bildes
            ImageView image_1 = (ImageView) this.findViewById(R.id.image_1);
            image_1.setImageBitmap(bmp_1);
            //Bitmap bitmap_1 = BitmapFactory.decodeFile(path);
            //image_2.setImageBitmap(bitmap_1);

        }
    }


    // Ende

}
