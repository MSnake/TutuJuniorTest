package com.workspace.alex.tutujuniortest.models;

import java.util.Calendar;
import java.util.Date;

/**
 * Модель поездки
 * Created by Alex on 12.09.2016.
 */
public class TripModel {
    private static TripModel ourInstance = new TripModel();

    private Date dateOfTrip;  //Дата поездки
    private StationModel fromStation; //Станция отправления
    private StationModel toStation;   //Станция прибытия

    public static TripModel getInstance() {
        return ourInstance;
    }

    private TripModel() {
        dateOfTrip = new Date();
        fromStation = new StationModel();
        toStation = new StationModel();
    }

    public Date getDateOfTrip() {
        return dateOfTrip;
    }

    public void setDateOfTrip(Date dateOfTrip) {
        this.dateOfTrip = dateOfTrip;
    }

    public StationModel getToStation() {
        return toStation;
    }

    public void setToStation(StationModel toStation) {
        this.toStation = toStation;
    }

    public StationModel getFromStation() {
        return fromStation;
    }

    public void setFromStation(StationModel fromStation) {
        this.fromStation = fromStation;
    }
}
