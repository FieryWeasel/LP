package lp.kazuya.mediaview.Tools.Tasks;

import android.content.Context;
import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import lp.kazuya.mediaview.Model.Media;
import lp.kazuya.mediaview.Tools.Constants;
import lp.kazuya.mediaview.Tools.DownloadHelper;
import lp.kazuya.mediaview.Tools.Manager;
import lp.kazuya.mediaview.Tools.XmlParser;


public class DataInitializationTask {

    private List<Media> medias;

    public DataInitializationTask() {
        new Task().execute();
    }

    private class Task extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            InputStream stream = DownloadHelper.loadFile(Constants.FILE_URL);
            try {
                if(stream !=null)
                    medias = XmlParser.parse(stream);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            for(Media media : medias)
                Manager.get().getDbManager().createMedia(media);

            return null;
        }


    }
}