package com.workspace.alex.tutujuniortest.utils;

import android.content.Context;
import android.util.Log;

import com.workspace.alex.tutujuniortest.R;
import com.workspace.alex.tutujuniortest.data.ArrivelData;
import com.workspace.alex.tutujuniortest.data.DepartureData;
import com.workspace.alex.tutujuniortest.data.TuTuData;
import com.workspace.alex.tutujuniortest.models.CityModel;
import com.workspace.alex.tutujuniortest.models.StationModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Загрузка данных из файла JSON
 * Created by Alex on 08.09.2016.
 */
public class JSONDataLoad {

    private static final String TAG = "JSONDataLoad";

    //Поля для парсинга города
    private static final String CONTRY_FIELD = "countryTitle";
    private static final String POINT_FIELD = "point";
    private static final String DISTRICT_FIELD = "districtTitle";
    private static final String CITY_ID_FIELD = "cityId";
    private static final String CITY_NAME_FIELD = "cityTitle";
    private static final String REGION_FIELD = "regionTitle";
    private static final String STATIONS_FIELD = "stations";

    //поля для парсинга точек
    private static final String LAT_POINT_FIELD = "latitude";
    private static final String LONG_POINT_FIELD = "longitude";

    //поля для парсинга станции
    private static final String STATION_ID_FIELD = "stationId";
    private static final String STATION_NAME_FIELD = "stationTitle";
    private static final String STATION_CITY_NAME_FIELD = "cityTitle";


    private static String getJSONFile (int id, Context context) throws IOException {
        String json = null;
        InputStream file = context.getResources().openRawResource(id);
        int size = file.available();
        byte[] formArray = new byte[size];
        file.read(formArray);
        file.close();
        json = new String(formArray, "UTF-8");
        return json;
    }


    /**
     * Парсинг файлов настроек пунктов отправления и прибытия для работы приложения
     * @param context
     * @throws IOException
     * @throws JSONException
     */
    public static void initDatas(Context context) throws IOException, JSONException {
        JSONObject fromStationJSON = new JSONObject(getJSONFile(R.raw.fromstations, context));
        JSONArray fromArray = (JSONArray) fromStationJSON.getJSONArray("citiesFrom");
        //Парсинг городов отправления
        for (int i = 0; i < fromArray.length(); i++) {

            JSONObject inside = fromArray.getJSONObject(i);
            DepartureData.getInstance().addItem(createModel(inside));
        }

        Log.d(TAG, "Данные о городах отправления успешно обработаны и загружены");

        JSONObject toStationJSON = new JSONObject(getJSONFile(R.raw.tostations, context));
        JSONArray toArray = (JSONArray) toStationJSON.getJSONArray("citiesTo");

        //Парсинг городов отправления
        for (int i = 0; i < fromArray.length(); i++) {

            JSONObject inside = fromArray.getJSONObject(i);
            ArrivelData.getInstance().addItem(createModel(inside));
        }



        Log.d(TAG, "Данные о городах назначения успешно обработаны и загружены");
    }

    private static CityModel createModel(JSONObject inside) throws JSONException
    {
        CityModel city = new CityModel();
        city.setCountry(inside.getString(CONTRY_FIELD));
        city.setDistrict(inside.getString(DISTRICT_FIELD));
        city.setId(Integer.parseInt(inside.getString(CITY_ID_FIELD))); //TODO слабое место: может случиться так что в поле будет не числовое значение
        city.setName(inside.getString(CITY_NAME_FIELD));
        city.setRegion(inside.getString(REGION_FIELD));

        //Запись координат города
        JSONObject points = inside.getJSONObject(POINT_FIELD);
        city.setXPoint(Float.parseFloat(points.getString(LONG_POINT_FIELD)));//TODO слабое место: может случиться так что в поле будет не числовое значение
        city.setYPoint(Float.parseFloat(points.getString(LAT_POINT_FIELD)));//TODO слабое место: может случиться так что в поле будет не числовое значение

        //Парсинг станций города
        JSONArray stationsArray = (JSONArray) inside.getJSONArray(STATIONS_FIELD);
        for (int j=0;j<stationsArray.length();j++)
        {
            JSONObject insideStationArr = stationsArray.getJSONObject(j);

            StationModel station = new StationModel();
            station.setCountry(insideStationArr.getString(CONTRY_FIELD));
            station.setDistrict(insideStationArr.getString(DISTRICT_FIELD));
            station.setId(Integer.parseInt(insideStationArr.getString(CITY_ID_FIELD))); //TODO слабое место: может случиться так что в поле будет не числовое значение
            station.setCity(insideStationArr.getString(STATION_CITY_NAME_FIELD));
            station.setRegion(insideStationArr.getString(REGION_FIELD));
            station.setId(Integer.parseInt(insideStationArr.getString(STATION_ID_FIELD))); //TODO слабое место: может случиться так что в поле будет не числовое значение
            station.setStation(insideStationArr.getString(STATION_NAME_FIELD));

            //Запись координат станции
            JSONObject stationPoints = inside.getJSONObject(POINT_FIELD);
            station.setXPoint(Float.parseFloat(stationPoints.getString(LONG_POINT_FIELD)));//TODO слабое место: может случиться так что в поле будет не числовое значение
            station.setYPoint(Float.parseFloat(stationPoints.getString(LAT_POINT_FIELD)));//TODO слабое место: может случиться так что в поле будет не числовое значение

            city.addStations(station);
        }

        return city;
    }

}
