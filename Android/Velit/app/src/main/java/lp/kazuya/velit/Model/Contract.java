package lp.kazuya.velit.Model;

import java.util.ArrayList;

/**
 * Created by Kazuya on 15/10/2014.
 */
public class Contract{
    private String name;
    private String commercial_name;
    private String country_code;
    private ArrayList<String> cities;

    public Contract() {
        this.name = "";
        this.commercial_name = "";
        this.country_code = "";
        this.cities = null;
    }

    public Contract(String name, String commercial_name, String country_code, ArrayList<String> cities) {
        this.name = name;
        this.commercial_name = commercial_name;
        this.country_code = country_code;
        this.cities = cities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommercial_name() {
        return commercial_name;
    }

    public void setCommercial_name(String commercial_name) {
        this.commercial_name = commercial_name;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public ArrayList<String> getCities() {
        return cities;
    }

    public void setCities(ArrayList<String> cities) {
        this.cities = cities;
    }
}
