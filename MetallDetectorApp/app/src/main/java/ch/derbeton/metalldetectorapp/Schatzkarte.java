package ch.derbeton.metalldetectorapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

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
        mapController.setZoom(18);
        map.setMaxZoomLevel(25);
        GeoPoint startPoint = new GeoPoint(47.22666, 8.81833);
        mapController.setCenter(startPoint);
    }


    public void onResume() {
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Configuration.getInstance().save(this, prefs);
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
    }

    public void onClickButton() {
    }


//---------------------------------------------------------------------------------------------------------------------
// Menu Start
//---------------------------------------------------------------------------------------------------------------------
    // Für die ganzen Einträge werden weitere Activities erstellt

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem_start = menu.add("Home");
        MenuItem menuItem_logbuch_scanner = menu.add("Logbuch Scanner");
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

        menuItem_logbuch_scanner.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

                                                                @Override
                                                                public boolean onMenuItemClick(MenuItem item) {

                                                                    log_schatzkarte();

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



    public class ShowLocationActivity extends Activity implements LocationListener {
        private TextView latituteField;
        private TextView longitudeField;
        private LocationManager locationManager;
        private String provider;

        /**
         * Called when the activity is first created.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_schatzkarte);
            latituteField = (TextView) findViewById(R.id.TextView02);
            longitudeField = (TextView) findViewById(R.id.TextView04);

            // Get the location manager
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            // Define the criteria how to select the locatioin provider -> use
            // default
            Criteria criteria = new Criteria();
            provider = locationManager.getBestProvider(criteria, false);
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Location location = locationManager.getLastKnownLocation(provider);

            // Initialize the location fields
            if (location != null) {
                System.out.println("Provider " + provider + " has been selected.");
                onLocationChanged(location);
            } else {
                latituteField.setText("Location not available");
                longitudeField.setText("Location not available");
            }
        }

        /* Request updates at startup */
        @Override
        protected void onResume() {
            super.onResume();
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(provider, 400, 1, this);
        }

        /* Remove the locationlistener updates when Activity is paused */
        @Override
        protected void onPause() {
            super.onPause();
            locationManager.removeUpdates(this);
        }

        @Override
        public void onLocationChanged(Location location) {
            int lat = (int) (location.getLatitude());
            int lng = (int) (location.getLongitude());
            latituteField.setText(String.valueOf(lat));
            longitudeField.setText(String.valueOf(lng));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(this, "Enabled new provider " + provider,
                    Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(this, "Disabled provider " + provider,
                    Toast.LENGTH_SHORT).show();
        }

    }

    // Format für Logbuch erstellen
    private void log_schatzkarte() {
        Intent intent = new Intent("ch.appquest.intent.LOG");


        String Koordinate1 = "{\"lat\": $lat1, \"lon\": $lon1},";
        String Koordinate2 = "{\"lat\": $lat2, \"lon\": $lon2}";
        String content = String.format("\n" + Koordinate1 + "\n" + Koordinate2);
        String newString = String.format(
                "{\"task\": \"Schatzkarte\", " +
                "\"points\": ["
                        + content +
                "]" +
                "}");




        intent.putExtra("ch.appquest.logmessage", newString);

        startActivity(intent);
    }
}
