package ch.derbeton.metalldetectorapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class Schatzkarte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context ctx = getApplicationContext();
        //important! set your user agent to prevent getting banned from the osm servers
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        setContentView(R.layout.activity_schatzkarte);

        MapView map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        //Mit fingen Zoomen aktivieren
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);


        // Startpunkt
        IMapController mapController = map.getController();
        mapController.setZoom(17);
        GeoPoint startPoint = new GeoPoint(47.22666, 8.81833);
        mapController.setCenter(startPoint);
    }


    public void onResume(){
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Configuration.getInstance().save(this, prefs);
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
    }


//---------------------------------------------------------------------------------------------------------------------
// Menu Start
//---------------------------------------------------------------------------------------------------------------------
    // Für die ganzen Einträge werden weitere Activities erstellt

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem_start = menu.add("Home");
      //  MenuItem menuItem_logbuch_scanner = menu.add("Logbuch Scanner");
        MenuItem menuItem_dechiffrierer = menu.add("Dechiffrierer");
        MenuItem menuItem_memory = menu.add("Memory");
        MenuItem menuItem_pixelmaler = menu.add("Pixelmaler");


        // Verlinken Start

        menuItem_start.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

                                                      @Override
                                                      public boolean onMenuItemClick(MenuItem item) {
                                                          Intent intent = new Intent(Schatzkarte.this, metallDetector.class);
                                                          startActivity(intent);
                                                          return false;
                                                      }
                                                  }

        );

        // Verlinken Dechiffrierer

        menuItem_dechiffrierer.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

                                                              @Override
                                                              public boolean onMenuItemClick(MenuItem item) {
                                                                  Intent intent = new Intent(Schatzkarte.this, Dechiffrierer.class);
                                                                  startActivity(intent);
                                                                  return false;
                                                              }
                                                          }

        );



        // Verlinken Memory

        menuItem_memory.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

                                                       @Override
                                                       public boolean onMenuItemClick(MenuItem item) {
                                                           Intent intent = new Intent(Schatzkarte.this, Memory.class);
                                                           startActivity(intent);
                                                           return false;
                                                       }
                                                   }
        );

        // verlinken Pixelmaler
        menuItem_pixelmaler.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

                                                            @Override
                                                            public boolean onMenuItemClick(MenuItem item) {
                                                                Intent intent = new Intent(Schatzkarte.this, Pixelmaler.class);
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


}

