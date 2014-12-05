package lp.kazuya.velit.Model;

import java.security.Timestamp;

/**
 * Created by Kazuya on 15/10/2014.
 */
public class StationDatas {

    private boolean banking;
    private boolean bonus;
    private int number;
    private int bike_stands;
    private int available_bike_stands;
    private int available_bikes;
    private float[] position;
    private String contract_name;
    private String name;
    private String address;
    private String status;
    private Timestamp last_update;

    public StationDatas() {
        this.banking = false;
        this.bonus = false;
        this.number = 0;
        this.bike_stands = 0;
        this.available_bike_stands = 0;
        this.available_bikes = 0;
        this.position = new float[2];
        this.contract_name = "";
        this.name = "";
        this.address = "";
        this.status = "";
        this.last_update = null;
    }

    public StationDatas(boolean banking, boolean bonus,
                        int number, int bike_stands, int available_bike_stands, int available_bikes,
                        float[] position,
                        String contract_name, String name, String address, String status, Timestamp last_update) {
        this.banking = banking;
        this.bonus = bonus;
        this.number = number;
        this.bike_stands = bike_stands;
        this.available_bike_stands = available_bike_stands;
        this.available_bikes = available_bikes;
        this.position = position;
        this.contract_name = contract_name;
        this.name = name;
        this.address = address;
        this.status = status;
        this.last_update = last_update;
    }

    public boolean isBanking() {
        return banking;
    }

    public void setBanking(boolean banking) {
        this.banking = banking;
    }

    public boolean isBonus() {
        return bonus;
    }

    public void setBonus(boolean bonus) {
        this.bonus = bonus;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getBike_stands() {
        return bike_stands;
    }

    public void setBike_stands(int bike_stands) {
        this.bike_stands = bike_stands;
    }

    public int getAvailable_bike_stands() {
        return available_bike_stands;
    }

    public void setAvailable_bike_stands(int available_bike_stands) {
        this.available_bike_stands = available_bike_stands;
    }

    public int getAvailable_bikes() {
        return available_bikes;
    }

    public void setAvailable_bikes(int available_bikes) {
        this.available_bikes = available_bikes;
    }

    public float[] getPosition() {
        return position;
    }

    public void setPosition(float[] position) {
        this.position = position;
    }

    public String getContract_name() {
        return contract_name;
    }

    public void setContract_name(String contract_name) {
        this.contract_name = contract_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }
}
