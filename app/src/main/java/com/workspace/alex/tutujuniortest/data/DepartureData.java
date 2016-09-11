package com.workspace.alex.tutujuniortest.data;


import com.workspace.alex.tutujuniortest.models.CityModel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * Хранилище данных о пунктах отправление
 * Created by Alex on 08.09.2016.
 */
public class DepartureData implements TuTuData,Serializable {

    private static DepartureData departureInstance;
    private ArrayList<CityModel> data;

    private DepartureData(){
        this.data = new ArrayList<>();
    }

    public static DepartureData getInstance()
    {
        if (departureInstance == null)
        {
            departureInstance = new DepartureData();
        }
        return departureInstance;
    }

    @Override
    public ArrayList<CityModel> getData()
    {
        return data;
    }

    @Override
    public void addItem(CityModel model){
        data.add(model);
    }

    public void setData(ArrayList<CityModel> data) {
        this.data = data;
    }
}
