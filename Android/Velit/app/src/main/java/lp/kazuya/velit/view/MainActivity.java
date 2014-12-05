package lp.kazuya.velit.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lp.kazuya.velit.Model.Contract;
import lp.kazuya.velit.Model.Station;
import lp.kazuya.velit.R;
import lp.kazuya.velit.Tools.Constants;
import lp.kazuya.velit.Tools.JSONArrayRequest;


public class MainActivity extends Activity {

    private boolean mStationsListReady = false;
    private boolean mContractsListReady = false;
    private ArrayList<Contract> mContractList = new ArrayList<Contract>();
    private ArrayList<Station> mStationList;
    private SharedPreferences mPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getSharedPreferences(Constants.GLOBAL_PREFERENCES, Context.MODE_PRIVATE);
        initializeData();
    }

    private void initializeData(){
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);

        JSONArrayRequest contractsArrayRequest = new JSONArrayRequest(Constants.URL_STATIC_CONTRACTS + "?" + Constants.JCD_API_KEY, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Log.d("Request", "Response: " + response.toString());
                Gson gson = new Gson();
                mContractList = gson.fromJson(response.toString(), new TypeToken<ArrayList<Contract>>(){}.getType());
                mContractsListReady = true;
                isDone();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        JsonArrayRequest stationsArrayRequest = new JsonArrayRequest(Constants.URL_STATIC_STATIONS + "?" + Constants.JCD_API_KEY, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
//                Log.d("Request", "Response: " + response.toString());
                Gson gson = new Gson();
                mStationList = gson.fromJson(response.toString(), new TypeToken<ArrayList<Station>>(){}.getType());
                int i = 0 ;
                for(Station temps : mStationList)
                    temps.setId(++i);
                mStationsListReady = true;
                isDone();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        requestQueue.add(contractsArrayRequest);
        requestQueue.add(stationsArrayRequest);
    }

    private void isDone(){
        if(mContractsListReady && mStationsListReady){

            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            Gson gson = new Gson();
            String stations = gson.toJson(mStationList);
            String cities = gson.toJson(getListCities());
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putString(Constants.STATION_LIST, stations);
            editor.putString(Constants.CITIES_LIST, cities);
            editor.commit();
//            intent.putExtra(Constants.STATION_LIST, stations);
//            intent.putExtra(Constants.CITIES_LIST, cities);
            startActivity(intent);
            finish();
        }
    }

    private ArrayList<String> getListCities() {
        ArrayList<String> cities = new ArrayList<String>();
        if(mContractList != null && mContractList.size()>0) {
            for (Contract contract : mContractList) {
                for (String city : contract.getCities()) {
                    if (!cities.contains(city)) {
                        cities.add(city);
                    }
                }
            }
            Collections.sort(cities);
        }else {
            cities.add("NO CITY");
        }
        return cities;
    }
}
