package lp.kazuya.velit.Tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import lp.kazuya.velit.Model.Station;
import lp.kazuya.velit.R;

/**
 * Created by Kazuya on 02/12/2014.
 * Velit
 */
public class RouteTask  extends AsyncTask<Void, Integer, Boolean> {

    public String mode;
    private LatLng start;
    public final ArrayList<LatLng> lstLatLng = new ArrayList<LatLng>();
    public Station station;
    public AsyncResponse delegate;
    private Context context;

    public RouteTask(final Context context, LatLng start, final String mode, Station station, AsyncResponse delegate) {
        this.context=context;
        this.mode=mode;
        this.start=start;
        this.station=station;
        this.delegate=delegate;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            //Construction de l'url à appeler
            final StringBuilder url = new StringBuilder("http://maps.googleapis.com/maps/api/directions/xml?sensor=false");
            url.append("&language=");
            url.append(context.getResources().getString(R.string.language));
            url.append("&origin=");
            url.append(start.latitude+","+start.longitude);
            Log.d("lat long", start.latitude + ", " + start.longitude);
            url.append("&destination=");
            url.append(station.getPosition().getLat()+"," + station.getPosition().getLng());
            url.append("&mode=");
            url.append(mode.replace(' ', '+'));
            //units=metric/imperial
            url.append("&units=");
            url.append(R.string.distanceSystem);

            //Appel du web service
            final InputStream stream = new URL(url.toString()).openStream();

            //Traitement des données
            final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setIgnoringComments(true);

            final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            final Document document = documentBuilder.parse(stream);
            document.getDocumentElement().normalize();

            //On récupère d'abord le status de la requête
            final String status = document.getElementsByTagName("status").item(0).getTextContent();
            if(!"OK".equals(status)) {

                return false;
            }

            //On récupère les steps
            final Element elementLeg = (Element) document.getElementsByTagName("leg").item(0);
            final NodeList nodeListStep = elementLeg.getElementsByTagName("step");
            final int length = nodeListStep.getLength();

            for(int i=0; i<length; i++) {
                final Node nodeStep = nodeListStep.item(i);

                if(nodeStep.getNodeType() == Node.ELEMENT_NODE) {
                    final Element elementStep = (Element) nodeStep;
                    //On décode les points du XML
                    decodePolylines(elementStep.getElementsByTagName("points").item(0).getTextContent());
                }
            }

            return true;
        }
        catch(final Exception e) {
            Log.e("caresto", e.getMessage());
        }

        return false;
    }

    /**
     * Decode points in latitude and longitudes
     */
    private void decodePolylines(final String encodedPoints) {
        int index = 0;
        int lat = 0, lng = 0;

        while (index < encodedPoints.length()) {
            int b, shift = 0, result = 0;

            do {
                b = encodedPoints.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);

            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;
            shift = 0;
            result = 0;

            do {
                b = encodedPoints.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);

            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            lstLatLng.add(new LatLng((double)lat/1E5, (double)lng/1E5));
        }
    }

    public interface AsyncResponse {
        void processFinish(Boolean output);
    }

}