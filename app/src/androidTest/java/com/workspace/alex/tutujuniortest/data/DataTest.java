package com.workspace.alex.tutujuniortest.data;

import android.content.Context;

import android.test.InstrumentationTestCase;

import com.workspace.alex.tutujuniortest.models.CityModel;
import com.workspace.alex.tutujuniortest.utils.JSONDataLoad;

/**
 * Created by Alex on 13.09.2016.
 */

//Тестирование заполнения хранилищ данными во время работы приложения
public class DataTest extends InstrumentationTestCase {

    //первоначальная инициализация
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Context con = getInstrumentation().getTargetContext();
        assertNotNull(con);
        JSONDataLoad.initDatas(con);
    }


    //Тестирование на корректную загруженность данных станций отправления
    public void testGetArrivelData() throws Exception {
        assertEquals(ArrivelData.getInstance().getData().size(),439);

    }
    //Тестирование на корректную загруженность данных станций прибытия
    public void testGetDepartureData() throws Exception {
        assertEquals(DepartureData.getInstance().getData().size(),878);
    }

    //Тестирование на корректное добавление новых станций

    public void testAddItemToDepartureData() throws Exception {
        int oldSize = DepartureData.getInstance().getData().size();
        DepartureData.getInstance().addItem(new CityModel());
        assertEquals(DepartureData.getInstance().getData().size(), oldSize+1);

    }

        public void testAddItemToArrivelData() throws Exception {
        int oldSize = ArrivelData.getInstance().getData().size();
        ArrivelData.getInstance().addItem(new CityModel());
        assertEquals(ArrivelData.getInstance().getData().size(), oldSize+1);

    }
}