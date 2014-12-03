package lp.kazuya.mediaview.Tools;

import lp.kazuya.mediaview.Model.Media;

/**
 * Created by Kazuya on 14/11/2014.
 * MediaView
 */
public class Manager {

    private static Manager instance = null;
    private DBManager dbManager;

    public static Manager get(){
        if(instance == null)
            return new Manager();
        return instance;
    }

    public String getStringFromEnum(Media.EType enumType){
        switch(enumType){
            case Image :
                return "image";
            case Text :
                return "text";
            case Video :
                return "video";
            case Audio :
                return "audio";
            default :
                return null;
        }
    }

    public Media.EType getEnumFromString(String enumType){
        if(enumType.equalsIgnoreCase("image"))
            return Media.EType.Image;
        if(enumType.equalsIgnoreCase("text"))
            return Media.EType.Text;
        if(enumType.equalsIgnoreCase("video"))
            return Media.EType.Video;
        if(enumType.equalsIgnoreCase("audio"))
            return Media.EType.Audio;
        else
            return null;
    }

    public DBManager getDbManager() {
        return dbManager;
    }

    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }
}
