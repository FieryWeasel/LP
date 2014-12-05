package lp.kazuya.velit.Tools;

/**
 * Created by Kazuya on 15/10/2014.
 */
public class Constants {

    public final static String URL_STATIC_CONTRACTS = "https://api.jcdecaux.com/vls/v1/contracts";
    public final static String URL_STATIC_STATIONS = "https://api.jcdecaux.com/vls/v1/stations";
    //Need station_number & URL_STATIC_STATION_INFO_CONTRACT_LINK
    public final static String URL_STATIC_STATION_INFO = "https://api.jcdecaux.com/vls/v1/stations/";
    //Need contract
    public final static String URL_STATIC_STATION_INFO_CONTRACT_LINK = "?contract=";
    //Need contract_name
    public final static String URL_STATIC_STATION_LIST_FROM_CONTRACT = "https://api.jcdecaux.com/vls/v1/stations?contract=";
    public final static String JCD_API_KEY = "apiKey=515362b5bfdf1f3eae1c9bf79b3b772702f99223";
    public static final int ADVANCED_OPTIONS = 42;

    public static final int FREE_BIKE = 0;
    public static final int FREE_SPACE = 1;
    /**
     * PREFERENCES
     * */
    public static final String GLOBAL_PREFERENCES = "velit_global_preferences";
    public static final String FILTER_FREE_BIKE = "filter_free_bike";
    public static final String FILTER_FREE_SPACE = "filter_free_space";
    public static final String FILTER_CITY = "filter_city";
    public static final String FILTER_BANKING = "filter_banking";
    public static final String FILTER_OPEN = "filter_open";
    public static final String STATION_LIST = "station_list";
    public static final String CONTRACTS_LIST = "cities_list";
    public static final String CITIES_LIST = "cities_list";
    public static final String EXTRA_ROUTE = "route";
}
