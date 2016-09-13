package com.workspace.alex.tutujuniortest.data;

import android.content.Context;

import android.test.InstrumentationTestCase;

import com.workspace.alex.tutujuniortest.models.CityModel;
import com.workspace.alex.tutujuniortest.utils.JSONDataLoad;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by Alex on 13.09.2016.
 */

//Тестирование заполнения хранилищ данными во время работы приложения
public class DataTest extends InstrumentationTestCase {

    private Context con;
    //первоначальная инициализация
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        con = getInstrumentation().getTargetContext();
        ArrivelData.getInstance().getData().clear();
        DepartureData.getInstance().getData().clear();

        JSONDataLoad.initDatas(con);
    }

    public void creatContext()
    {
        assertNotNull(con);
    }

    //Факт произведения загрузки
    public void testLoadFromJSON()
    {
        boolean loaded=false;
        try {
            JSONDataLoad.initDatas(con);
            loaded=true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        assertEquals(true, loaded);

    }


    //Тестирование на корректную загруженность данных станций отправления
    public void testGetArrivelData() throws Exception {
        assertEquals(439,ArrivelData.getInstance().getData().size());

    }
    //Тестирование на корректную загруженность данных станций прибытия
    public void testGetDepartureData() throws Exception {
        assertEquals(439,DepartureData.getInstance().getData().size());
    }

    //Тестирование на корректное добавление новых станций

    public void testAddItemToDepartureData() throws Exception {
        int oldSize = DepartureData.getInstance().getData().size();
        DepartureData.getInstance().addItem(new CityModel());
        assertEquals(oldSize+1, DepartureData.getInstance().getData().size());

    }

        public void testAddItemToArrivelData() throws Exception {
        int oldSize = ArrivelData.getInstance().getData().size();
        ArrivelData.getInstance().addItem(new CityModel());
        assertEquals(oldSize+1, ArrivelData.getInstance().getData().size());

    }
}