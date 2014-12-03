package lp.kazuya.mediaview.Activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import lp.kazuya.mediaview.Model.Media;
import lp.kazuya.mediaview.R;
import lp.kazuya.mediaview.Tools.DBManager;
import lp.kazuya.mediaview.Tools.Manager;
import lp.kazuya.mediaview.Tools.Tasks.DataInitializationTask;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBManager db = new DBManager(this);
        Manager.get().setDbManager(db);
        TextView test = (TextView) findViewById(R.id.test);
        new DataInitializationTask();

        String text = "";
        List<Media> medias = Manager.get().getDbManager().getAllMedia();
        for(Media media : medias){
            text+=media.toString() + "\n";
        }
        test.setText(text);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
