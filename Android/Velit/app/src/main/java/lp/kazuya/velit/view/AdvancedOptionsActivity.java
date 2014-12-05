package lp.kazuya.velit.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Field;
import java.util.ArrayList;

import lp.kazuya.velit.Model.Station;
import lp.kazuya.velit.R;
import lp.kazuya.velit.Tools.Constants;


public class AdvancedOptionsActivity extends Activity {

    private TextView cityChosen;
    private TextView minFreeBikeChosen;
    private TextView minFreeSpaceChosen;
    private SharedPreferences mPreferences;
    private String[] citiesArray;
    private Switch bankingSwitch;
    private Switch openSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_options);

        mPreferences = getSharedPreferences(Constants.GLOBAL_PREFERENCES, Context.MODE_PRIVATE);

        cityChosen = (TextView) findViewById(R.id.cityChosen);
        minFreeBikeChosen = (TextView) findViewById(R.id.minFreeBikeChosen);
        minFreeSpaceChosen = (TextView) findViewById(R.id.minFreeSpaceChosen);

        Gson gson = new Gson();
        String list = mPreferences.getString(Constants.CITIES_LIST, "");
        ArrayList<String> cities = gson.fromJson(list, new TypeToken<ArrayList<String>>(){}.getType());
        citiesArray = getCitiesArray(cities);

        TextView city = (TextView) findViewById(R.id.city);
        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cityPickerSetup();
            }
        });

        TextView minFreeBike = (TextView) findViewById(R.id.minFreeBike);
        minFreeBike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberPickerSetup(Constants.FREE_BIKE);
            }
        });

        TextView minFreeSpace = (TextView) findViewById(R.id.minFreeSpace);
        minFreeSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberPickerSetup(Constants.FREE_SPACE);
            }
        });

        bankingSwitch = (Switch) findViewById(R.id.bankingSwitch);
        bankingSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                SharedPreferences.Editor mEditor = mPreferences.edit();
                mEditor.putBoolean(Constants.FILTER_BANKING, b);
                mEditor.apply();
            }
        });

        openSwitch = (Switch) findViewById(R.id.openSwitch);
        openSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                SharedPreferences.Editor mEditor = mPreferences.edit();
                mEditor.putBoolean(Constants.FILTER_OPEN, b);
                mEditor.apply();
            }
        });


        boolean banking = mPreferences.getBoolean(Constants.FILTER_BANKING, false);
        bankingSwitch.setChecked(banking);
        boolean open = mPreferences.getBoolean(Constants.FILTER_OPEN, false);
        openSwitch.setChecked(open);
        int free_bike = mPreferences.getInt(Constants.FILTER_FREE_BIKE, 0);
        minFreeBikeChosen.setText(""+free_bike);
        int free_space = mPreferences.getInt(Constants.FILTER_FREE_SPACE, 0);
        minFreeSpaceChosen.setText(""+free_space);
        String cityChosen = mPreferences.getString(Constants.FILTER_CITY, "");
        this.cityChosen.setText(cityChosen);
    }

    private void cityPickerSetup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.chooseCity)
                .setItems(citiesArray, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        SharedPreferences.Editor mEditor = mPreferences.edit();
                        mEditor.putString(Constants.FILTER_CITY, citiesArray[which]);
                        mEditor.apply();
                        cityChosen.setText(citiesArray[which]);
                    }
                });
        builder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    private void numberPickerSetup(final int typeCall) {
        LayoutInflater inflater = (LayoutInflater)
                getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_number_picker, null);
        final NumberPicker numberPicker = (NumberPicker) view.findViewById(R.id.numberPicker);
        numberPicker.setMaxValue(20);
        numberPicker.setMinValue(0);
        numberPicker.setWrapSelectorWheel(true);
        setNumberPickerTextColor(numberPicker, Color.argb(150, 0, 0, 0));

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose");
        builder.setView(view);
        builder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                setNumber(typeCall, numberPicker.getValue());
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    private void setNumber(int typeCall, int numberChosen) {

        SharedPreferences.Editor mEditor = mPreferences.edit();
        switch(typeCall){
            case Constants.FREE_BIKE :
                mEditor.putInt(Constants.FILTER_FREE_BIKE, numberChosen);
                mEditor.apply();
                minFreeBikeChosen.setText(String.valueOf(numberChosen));
                break;
            case Constants.FREE_SPACE :
                mEditor.putInt(Constants.FILTER_FREE_SPACE, numberChosen);
                mEditor.apply();
                minFreeSpaceChosen.setText(String.valueOf(numberChosen));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_advanced_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_validate) {
            setResult(RESULT_OK);
            finish();
            return true;
        }
        if( id == R.id.action_clear){
            SharedPreferences.Editor mEditor = mPreferences.edit();
            mEditor.putInt(Constants.FILTER_FREE_BIKE, 0);
            mEditor.putInt(Constants.FILTER_FREE_SPACE, 0);
            mEditor.putString(Constants.FILTER_CITY, "");
            mEditor.putBoolean(Constants.FILTER_BANKING, false);
            mEditor.putBoolean(Constants.FILTER_OPEN, false);
            mEditor.apply();
            bankingSwitch.setChecked(false);
            openSwitch.setChecked(false);
            minFreeBikeChosen.setText("");
            minFreeSpaceChosen.setText("");
            this.cityChosen.setText("");
        }

        return super.onOptionsItemSelected(item);
    }

    public static boolean setNumberPickerTextColor(NumberPicker numberPicker, int color) {
        final int count = numberPicker.getChildCount();
        for(int i = 0; i < count; i++){
            View child = numberPicker.getChildAt(i);
            if(child instanceof EditText){
                try{
                    Field selectorWheelPaintField = numberPicker.getClass()
                            .getDeclaredField("mSelectorWheelPaint");
                    selectorWheelPaintField.setAccessible(true);
                    ((Paint)selectorWheelPaintField.get(numberPicker)).setColor(color);
                    ((EditText)child).setTextColor(color);
                    numberPicker.invalidate();
                    return true;
                }
                catch(NoSuchFieldException e){
                    Log.w("setNumberPickerTextColor", e);
                }
                catch(IllegalAccessException e){
                    Log.w("setNumberPickerTextColor", e);
                }
                catch(IllegalArgumentException e){
                    Log.w("setNumberPickerTextColor", e);
                }
            }
        }
        return false;
    }

    private String[] getCitiesArray(ArrayList<String> cities){
        String[] citiesArray = new String[cities.size()];
        if(cities.size()>0){
            for (int i = 0; i < cities.size(); i++) {
                citiesArray[i] = cities.get(i);
            }
        }else {
            citiesArray = new String[]{"NO CITY"};

        }
        return citiesArray;
    }
}
