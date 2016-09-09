package com.workspace.alex.tutujuniortest.data;

import com.workspace.alex.tutujuniortest.models.CityModel;

import java.util.ArrayList;

/**
 * Хранилище данных о пунктах прибытия
 * Created by Alex on 08.09.2016.
 */
public class ArrivelData implements TuTuData {

    private static ArrivelData arrivelInstance;
    private static ArrayList<CityModel> data = new ArrayList<>();

    private ArrivelData(){

    }

    public static ArrivelData getInstance()
    {
        if (arrivelInstance == null)
        {
            arrivelInstance = new ArrivelData();
        }
        return arrivelInstance;

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
}
