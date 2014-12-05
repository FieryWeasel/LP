package lp.kazuya.velit.Model;

import java.io.Serializable;

/**
 * Created by Kazuya on 15/10/2014.
 */
public class Station implements Serializable{

    private int id;
    private boolean banking;
    private boolean bonus;
    private int number;
    private int bike_stands;
    private int available_bike_stands;
    private int available_bikes;
    private String name;
    private String address;
    private String status;
    private String contract_name;
    private Position position;
    private long last_update;

    public Station(){
        id = 0;
        number = 0;
        name = "";
        address = "";
        position = new Position();
        banking = false;
        bonus = false;
        bike_stands = 0;
        available_bike_stands = 0;
        available_bikes = 0;
        status = "";
        contract_name = "";
        last_update = 0;
    }

    public Station(int id, int number, String name,String address, Position position, boolean banking, boolean bonus,
                   int bike_stands, int available_bike_stands, int available_bikes, String status, String contract_name, long last_update){
        this.id = id;
        this.number = number;
        this.name = name;
        this.address = address;
        this.position = position;
        this.banking = banking;
        this.bonus = bonus;
        this.bike_stands = bike_stands;
        this.available_bike_stands = available_bike_stands;
        this.available_bikes = available_bikes;
        this.status = status;
        this.contract_name = contract_name;
        this.last_update = last_update;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContract_name() {
        return contract_name;
    }

    public void setContract_name(String contract_name) {
        this.contract_name = contract_name;
    }

    public long getLast_update() {
        return last_update;
    }

    public void setLast_update(long last_update) {
        this.last_update = last_update;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
