package ch.derbeton.metalldetectorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Memory extends AppCompatActivity {


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
    }
}
