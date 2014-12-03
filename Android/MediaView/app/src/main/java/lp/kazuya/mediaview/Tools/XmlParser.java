package lp.kazuya.mediaview.Tools;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import lp.kazuya.mediaview.Model.Media;

/**
 * Created by Kazuya on 14/11/2014.
 * MediaView
 */
public class XmlParser {

    private static final String ns = null;
    private static final String ELEMENT = "media";
    private static final String ROOT_ELEMENT = "medias";

    public static List<Media> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    private static List<Media> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<Media> medias = new ArrayList<Media>();

        parser.require(XmlPullParser.START_TAG, ns, ROOT_ELEMENT);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals(ELEMENT)) {
                medias.add(readEntry(parser));
            } else {
                skip(parser);
            }
        }
        return medias;
    }

    // Parses the contents of an entry. If it encounters a title, summary, or link tag, hands them off
    // to their respective "read" methods for processing. Otherwise, skips the tag.
    private static Media readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {

            int version = Integer.parseInt(parser.getAttributeValue(1));
            String name = parser.getAttributeValue(0);
            String url = parser.getAttributeValue(2);
            String type = parser.getAttributeValue(3);

        return new Media(version, name, url, Manager.get().getEnumFromString(type));
    }

    private static void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
