package lp.kazuya.mediaview.Tools;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Kazuya on 14/11/2014.
 * MediaView
 */
public class DownloadHelper {

    public static InputStream loadFile(String urlString){
        InputStream inuputStream = null;
        try {
            URL url = new URL(urlString);

            //create the new connection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            //set up some things on the connection
            urlConnection.setRequestMethod("GET");

            //and connect!
            urlConnection.connect();

            //this will be used in reading the data from the internet
            inuputStream = urlConnection.getInputStream();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(inuputStream != null)
            return inuputStream;
        else
            return null;
    }

}
