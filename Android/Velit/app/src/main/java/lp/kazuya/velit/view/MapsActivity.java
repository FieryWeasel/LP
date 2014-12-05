package lp.kazuya.velit.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;


import java.util.ArrayList;
import java.util.List;

import lp.kazuya.velit.Model.Station;
import lp.kazuya.velit.R;
import lp.kazuya.velit.Tools.Constants;

public class MapsActivity extends FragmentActivity {

    private GoogleMap map; // Might be null if Google Play services APK is not available.
    private ClusterManager<MarkerItem> clusterManager;
    private List<Station> mStations;
    private List<Station> mFilteredStations;
    private double latitude = 0.0;
    private double longitude = 0.0;
    private SharedPreferences mPreferences;
    private MarkerItem clickedClusterItem;
    private BitmapDescriptor icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mStations = new ArrayList<Station>();
        mFilteredStations = new ArrayList<Station>();
        mPreferences = getSharedPreferences(Constants.GLOBAL_PREFERENCES, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String stations = mPreferences.getString(Constants.STATION_LIST, "plop");
        mStations = gson.fromJson(stations, new TypeToken<ArrayList<Station>>(){}.getType());
        icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_bicycle_green);
        setupFilters();
        setUpMapIfNeeded();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if(item.getItemId() == R.id.action_filters){
            Intent intent = new Intent(getApplicationContext(), AdvancedOptionsActivity.class);
            startActivityForResult(intent, 42);
        }
        return super.onMenuItemSelected(featureId, item);
    }

    private void setupFilters() {
        if(mStations != null && mStations.size() >0) {
            mFilteredStations.clear();
            boolean banking = mPreferences.getBoolean(Constants.FILTER_BANKING, false);
            boolean open = mPreferences.getBoolean(Constants.FILTER_OPEN, false);
            int free_bike = mPreferences.getInt(Constants.FILTER_FREE_BIKE, 0);
            int free_space = mPreferences.getInt(Constants.FILTER_FREE_SPACE, 0);
            String city = mPreferences.getString(Constants.FILTER_CITY, "");

            Boolean bankingOk, freeBikeOk, freeSpaceOk, cityOk, openOk;

            for (Station station : mStations) {
                bankingOk = false;
                openOk = false;
                freeBikeOk = false;
                freeSpaceOk = false;
                cityOk = false;

                if (!open || station.getStatus().equalsIgnoreCase("open")) {
                    openOk = true;
                }
                if (!banking || station.isBanking()) {
                    bankingOk = true;
                }
                if (station.getAvailable_bikes() >= free_bike){
                    freeBikeOk = true;
                }
                if (station.getAvailable_bike_stands() >= free_space){
                    freeSpaceOk = true;
                }
                if (city.equalsIgnoreCase("") || station.getContract_name().equalsIgnoreCase(city)){
                    cityOk = true;
                }
                if (bankingOk && openOk && freeBikeOk && freeSpaceOk && cityOk){
                    mFilteredStations.add(station);
                }
            }
            if(mFilteredStations.size() == 0){
                mFilteredStations.addAll(mStations);
            }
        }

    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        setUpMapIfNeeded();
//        setupFilters();
//        map.clear();
//        addItems();
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == 42){
            setUpMapIfNeeded();
            setupFilters();
            map.clear();
            addItems();
        }
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (map == null) {
            // Try to obtain the map from the SupportMapFragment.
            map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (map != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        map.setMyLocationEnabled(true); // false to disable
        map.getUiSettings().setZoomControlsEnabled(false);
        map.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                if(latitude == 0 && longitude == 0) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();

                    LatLng loc = new LatLng(latitude, longitude);
                    // Position the map.
                    map.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(loc, 15)));
                }
            }
        });
        map.setOnMarkerClickListener(clusterManager);
        setupCluster();
        addItems();
    }

    private void setupCluster(){
        // Initialize the manager with the context and the map.
        clusterManager = new ClusterManager<MarkerItem>(MapsActivity.this, map);
        clusterManager.setRenderer(new CustomClusterRenderer(this, map, clusterManager));
        // Point the map's listeners at the listeners implemented by the cluster manager.
        map.setOnCameraChangeListener(clusterManager);
        map.setOnMarkerClickListener(clusterManager);
        map.setInfoWindowAdapter(clusterManager.getMarkerManager());

        clusterManager.getMarkerCollection().setOnInfoWindowAdapter(new CustomAdapterForItems());
        clusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MarkerItem>() {
            @Override
            public boolean onClusterItemClick(MarkerItem item) {
                clickedClusterItem = item;
                return false;
            }
        });

        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                if (clickedClusterItem != null) {
                    for (int i = 0; i < mFilteredStations.size(); i++) {

                        if (mFilteredStations.get(i).getId() == Integer.parseInt(clickedClusterItem.getTag())) {
                            Intent intent = new Intent(getApplicationContext(), RouteActivity.class);
                            intent.putExtra(Constants.EXTRA_ROUTE, mFilteredStations.get(i));
                            startActivity(intent);
                        }
                    }
                }
            }
        });

    }

    private void addItems() {
        clusterManager.clearItems();
        for(int i=0;i< mFilteredStations.size();i++){
            double lat = mFilteredStations.get(i).getPosition().getLat();
            double lon = mFilteredStations.get(i).getPosition().getLng();
            Log.d("LatLng", "Lat : " + lat + " / Long : " + lon);
            if(lat!=0 && lon != 0) {
                MarkerItem offsetItem = new MarkerItem(lat, lon, Integer.toString(mFilteredStations.get(i).getId()));

                clusterManager.addItem(offsetItem);
            }
        }
        clusterManager.cluster();
    }

    private class MarkerItem implements ClusterItem {
        private final LatLng mPosition;
        private String tag;
        public MarkerItem(double lat, double lng, String taG) {
            mPosition = new LatLng(lat, lng);
            tag = taG;
        }

        @Override
        public LatLng getPosition() {
            return mPosition;
        }

        public String getTag(){
            return tag;
        }

        public void setTag(String taG){
            tag=taG;
        }
    }

    private class CustomClusterRenderer extends DefaultClusterRenderer<MarkerItem> {

        public CustomClusterRenderer(Context context, GoogleMap map, ClusterManager clusterManager) {
            super(context, map, clusterManager);
        }

        @Override
        protected void onBeforeClusterItemRendered(MarkerItem item, MarkerOptions markerOptions) {
            super.onBeforeClusterItemRendered(item, markerOptions);
            //markerOptions.title(item.getTag());
            markerOptions.icon(icon);
        }
    }

    private class CustomAdapterForItems implements GoogleMap.InfoWindowAdapter {

        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker) {
            // Getting view from the layout file info_window_layout
            View v = getLayoutInflater().inflate(R.layout.layout_info_window, null);

            if (clickedClusterItem!=null) {
                String tmp;
                int tmpInt;

                // Getting reference to the TextView to set latitude
                TextView address = (TextView) v.findViewById(R.id.address);
                TextView openOrClosed = (TextView) v.findViewById(R.id.openOrClosed);
                TextView freeBike = (TextView) v.findViewById(R.id.freeBike);
                TextView freeSpace = (TextView) v.findViewById(R.id.freeSpace);
                TextView banking = (TextView) v.findViewById(R.id.banking);

                for (int i = 0; i < mFilteredStations.size(); i++) {
                    if (clickedClusterItem.getTag().equals(String.valueOf(mFilteredStations.get(i).getId()))) {

                        if (!(tmp = mFilteredStations.get(i).getAddress()).equals(""))
                            address.setText(tmp);
                        else
                            address.setVisibility(View.GONE);

                        if (mFilteredStations.get(i).getStatus().equalsIgnoreCase("open"))
                            openOrClosed.setText(getResources().getString(R.string.open));
                        else
                            openOrClosed.setText(getResources().getString(R.string.closed));

                        if (!((tmpInt = mFilteredStations.get(i).getAvailable_bikes()) == -1))
                            freeBike.setText(getResources().getString(R.string.freeBike)
                            + " "
                            + tmpInt);
                        else
                            freeBike.setVisibility(View.GONE);

                        if (!((tmpInt = mFilteredStations.get(i).getAvailable_bike_stands()) == -1))
                            freeSpace.setText(getResources().getString(R.string.availableStands)
                                    + " "
                                    + tmpInt);
                        else
                            freeSpace.setVisibility(View.GONE);

                        if (mFilteredStations.get(i).isBanking())
                            banking.setText(getResources().getString(R.string.banking));
                        else
                            banking.setText(getResources().getString(R.string.noBanking));

                        return v;
                    }
                }
            }
            // Returning the view containing InfoWindow contents
            return v;
        }
    }
}
