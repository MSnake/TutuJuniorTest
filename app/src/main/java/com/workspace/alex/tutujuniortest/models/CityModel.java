package com.workspace.alex.tutujuniortest.models;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Модель города
 *
 * Created by Alex on 08.09.2016.
 */
public class CityModel implements Serializable {

    private String country;
    private float[] point;
    private String district;
    private int id;
    private String name;
    private String region;
    private ArrayList<StationModel> stations;

    public CityModel()
    {
        this.point = new float[2];
        this.stations = new ArrayList<>();
    }


    //GET AND SET BLOCK

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getXPoint() {
        return point[0];
    }

    public float getYPoint() {
        return point[1];
    }

    public void setXPoint(float value) {
        this.point[0] = value;
    }

    public void setYPoint(float value) {
        this.point[1] = value;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public ArrayList<StationModel> getStations() {
        return stations;
    }

    public void setStations(ArrayList<StationModel> stations) {
        this.stations = stations;
    }

    public void addStations(StationModel station) {
        this.stations.add(station);
    }
}
