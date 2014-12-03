package lp.kazuya.mediaview.Tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import lp.kazuya.mediaview.Model.Media;

/**
 * Created by Kazuya on 14/11/2014.
 * MediaView
 */
public class DBManager extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MV_DB";
    private static final String FILE_CHARSET = "UTF-8";
    private Context context;

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        if (db == null) {
            db = this.getWritableDatabase();
        }
        if (db == null) {
            Log.e(Constants.CREATION_TAG,"CAN'T OPEN OR CREATE DATA BASE");
        }

        String statement = "CREATE TABLE IF NOT EXISTS medias (" +
                "mediaId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "mediaVersion INTEGER, " +
                "mediaName VARCHAR(32), " +
                "mediaURL VARCHAR(32)" +
                "mediaType VARCHAR(32));";

        // Creation tables
        try {
            db.execSQL(statement);
        }catch  (Exception e){
            Log.d(Constants.CREATION_TAG, "Erreur Sql : " + e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }

    public void createMedia(Media media){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues(3);
        values.put("mediaVersion", media.getVersion());
        values.put("mediaName", media.getName());
        values.put("mediaURL", media.getUrl());
        values.put("mediaType", Manager.get().getStringFromEnum(media.getType()));
        db.insert("medias","mediaId",values);
    }

    public void createMedia(int version, String name, String url, String type){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues(3);
        values.put("mediaVersion", version);
        values.put("mediaName", name);
        values.put("mediaURL", url);
        values.put("mediaType", type);
        db.insert("medias","mediaId",values);
    }

    public Media getMediaById(int id){
        Media media = new Media();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM medias WHERE mediaId = ?",new String[] {String.valueOf(id)} );
            media.setVersion(c.getInt(1));
            media.setName(c.getString(2));
            media.setUrl(c.getString(3));
            media.setType(Manager.get().getEnumFromString(c.getString(4)));
            c.close();
        }
        catch (SQLiteException e) {
            Log.d("getMediaById", "error getting Media : " + e.getMessage());
            media = null;
        }
        db.close();
        return media;
    }

    public ArrayList<Media> getAllMedia(){
        ArrayList<Media> medias = new ArrayList<Media>();
        SQLiteDatabase db = this.getReadableDatabase();
        Media media;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM medias", null);
            boolean eof = c.moveToFirst();
            while(eof){
                media = new Media();
                media.setId(c.getInt(0));
                media.setVersion(c.getInt(1));
                media.setName(c.getString(2));
                media.setUrl(c.getString(3));
                media.setType(Manager.get().getEnumFromString(c.getString(4)));
                eof = c.moveToNext();
                medias.add(media);
            }

            c.close();
        }
        catch (SQLiteException e) {
            Log.d("getMediaById", "error getting Medias : " + e.getMessage());
            medias = null;
        }
        db.close();
        return medias;
    }

    public ArrayList<Media> getMediabyType(String mediaType){
        ArrayList<Media> medias = new ArrayList<Media>();
        SQLiteDatabase db = this.getReadableDatabase();
        Media media;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM medias WHERE mediaType = ?", new String[] {mediaType});
            boolean eof = c.moveToFirst();
            while(eof){
                media = new Media();
                media.setVersion(c.getInt(1));
                media.setName(c.getString(2));
                media.setUrl(c.getString(3));
                media.setType(Manager.get().getEnumFromString(c.getString(4)));
                eof = c.moveToNext();
                medias.add(media);
            }

            c.close();
        }
        catch (SQLiteException e) {
            Log.d("getMediaById", "error getting Media : " + e.getMessage());
            medias = null;
        }
        db.close();
        return medias;
    }
}
