package com.workspace.alex.tutujuniortest.data;

import com.workspace.alex.tutujuniortest.models.CityModel;

import java.util.ArrayList;

/**
 * Интерфейс хранения данных
 * Created by Alex on 09.09.2016.
 */
public interface TuTuData {

    //Добавление модели в хранилище данных
    void addItem(CityModel model);

    //Получение данных
    ArrayList<CityModel> getData();


}
