package com.workspace.alex.tutujuniortest.models;

import android.graphics.Point;

import java.io.Serializable;

/**
 * Модель станции города
 * Created by Alex on 08.09.2016.
 *
 */
public class StationModel implements Serializable {
    private String country;
    private float[] point;
    private String district;
    private int id;
    private String city;
    private String region;
    private String station;


    public StationModel(){
        this.point = new float[2];
    }


    //GET AND SET BLOCK

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

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

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }
}
