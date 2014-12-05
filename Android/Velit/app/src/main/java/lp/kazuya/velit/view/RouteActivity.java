package lp.kazuya.velit.view;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

import lp.kazuya.velit.Model.Station;
import lp.kazuya.velit.R;
import lp.kazuya.velit.Tasks.RouteTask;
import lp.kazuya.velit.Tools.Constants;

public class RouteActivity extends Activity implements RouteTask.AsyncResponse{
    private Station station;
    private GoogleMap map;
    private double longitude;
    private double latitude;
    private boolean modeDriving;
    private boolean locationOnce;
    private ProgressBar progressBar;
    private String mode;
    private TextView textViewNoRoute;
    private Button buttonNoRoute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        setTitle(getResources().getString(R.string.title_activity_route));
        station = (Station) getIntent().getSerializableExtra(Constants.EXTRA_ROUTE);

        progressBar =  (ProgressBar)findViewById(R.id.progressBar);
        textViewNoRoute = (TextView) findViewById(R.id.message);
        buttonNoRoute = (Button) findViewById(R.id.retry);

        buttonNoRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retryRoute();
            }
        });

        locationOnce=true;

        modeDriving=false;
        mode="driving";

        initializeMap();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_route, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_gps:
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr=" + latitude + "," + longitude + "&daddr=" + station.getPosition().getLat() + "," + station.getPosition().getLng()));
                startActivity(intent);
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeMap() {
        if (map == null) {
            // Get a handle to the Map Fragment
            MapFragment mapFrag = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.mapRoute));
            map = mapFrag.getMap();

            // check if map is created successfully or not
            if (map == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
                return;
            }

            map.setMyLocationEnabled(true); // false to disable
            map.getUiSettings().setZoomControlsEnabled(false);
            // latitude and longitude
            map.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                @Override
                public void onMyLocationChange(Location location) {
                    if (latitude == 0 && longitude == 0 && locationOnce) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        locationOnce=false;
                        // Position the default map.
                        map.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(new LatLng(latitude, longitude), 10)));

                    }
                }
            });

            map.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                @Override
                public void onMapLoaded() {
                    RouteTask routeTask = new RouteTask(RouteActivity.this,new LatLng(latitude, longitude), "driving", station, RouteActivity.this) {
                        @Override
                        protected void onPostExecute(Boolean result) {
                            super.onPostExecute(result);

                            postExecuteRouteTask(result, super.delegate, super.lstLatLng);
                        }

                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                            preExecuteRouteTask();
                        }
                    };
                    routeTask.execute();

                }
            });

            map.addMarker(new MarkerOptions()
                    .position(new LatLng(station.getPosition().getLat(), station.getPosition().getLng()))
                    .title((station.getName())));
        }
    }

    private void preExecuteRouteTask(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void postExecuteRouteTask(boolean result, RouteTask.AsyncResponse delegate,
                                      final ArrayList<LatLng> lstLatLng){

        progressBar.setVisibility(View.GONE);
        if(!result && delegate != null) {
            delegate.processFinish(result);
            textViewNoRoute.setVisibility(View.GONE);
            buttonNoRoute.setVisibility(View.GONE);
        } else if(!result){
            textViewNoRoute.setVisibility(View.VISIBLE);
            buttonNoRoute.setVisibility(View.VISIBLE);
        } else{
            textViewNoRoute.setVisibility(View.GONE);
            buttonNoRoute.setVisibility(View.GONE);

            setupLine(lstLatLng);

            //LatLng center = getMidPoint(start, end);
            LatLng center = getMidPoint(lstLatLng);

            map.animateCamera(CameraUpdateFactory.newCameraPosition(
                    CameraPosition.fromLatLngZoom(
                            center, calculateZoomLevel(
                                    setupDistance(latitude, longitude, station.getPosition().getLat(), station.getPosition().getLng())))));

        }
    }

    private void setupLine(ArrayList<LatLng> lstLatLng){
        //On déclare le polyline, c'est-à-dire le trait (ici bleu) que l'on ajoute sur la carte pour tracer l'itinéraire
        final PolylineOptions polylines = new PolylineOptions();
        polylines.color(Color.BLUE);

        //On construit le polyline
        for (final LatLng latLng : lstLatLng) {
            polylines.add(latLng);
        }

        map.clear();
        reinitializeMap();
        map.addPolyline(polylines);
    }


    @Override
    public void processFinish(Boolean output) {
        if(!output) {
            RouteTask routeTask = new RouteTask(RouteActivity.this, new LatLng(latitude, longitude), mode, station, null) {
                @Override
                protected void onPostExecute(Boolean result) {
                    super.onPostExecute(result);

                    postExecuteRouteTask(result, super.delegate, super.lstLatLng);
                }

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    preExecuteRouteTask();
                }
            };
            routeTask.execute();
        }
    }

    private void reinitializeMap() {

        map.setMyLocationEnabled(true); // false to disable
        map.getUiSettings().setZoomControlsEnabled(false);

        map.addMarker(new MarkerOptions()
                .position(new LatLng(station.getPosition().getLat(), station.getPosition().getLng()))
                .title((station.getName())));

    }

    private void retryRoute(){
        RouteTask routeTask = new RouteTask(RouteActivity.this, new LatLng(latitude, longitude),mode, station, RouteActivity.this) {
            @Override
            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);

                postExecuteRouteTask(result, super.delegate, super.lstLatLng);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                preExecuteRouteTask();
            }
        };
        routeTask.execute();
    }

    private int calculateZoomLevel(int distance) {

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        double mapDisplay = size.x;

        double zooms[] = new double[]{21282,16355,10064,5540,2909,1485,752,378,190,95,48,24,12,6,3,1.48,0.74,0.37,0.19};
        int i = 18;
        double zoom;
        while( i != 0 ){
            zoom = zooms[i] * mapDisplay;
            if( distance < zoom ){
                break;
            }
            i--;
        }
        return i-1;

    }

    private LatLng getMidPoint(ArrayList<LatLng> listLatLng){
        double lat = 0;
        double lng = 0;

        for(int i = 0 ; i < listLatLng.size() ; i++){
            lat+=listLatLng.get(i).latitude;
            lng+=listLatLng.get(i).longitude;
        }

        double finalLat = lat/ listLatLng.size();
        double finalLong = lng / listLatLng.size();

        return new LatLng(finalLat, finalLong);
    }

    private int setupDistance(double lat1, double lng1, double lat2, double lng2){
        Location start = new Location("start");
        start.setLatitude(lat1);
        start.setLongitude(lng1);
        Location end = new Location("end");
        end.setLatitude(lat2);
        end.setLongitude(lng2);

        return (int)(start.distanceTo(end));
    }
}
