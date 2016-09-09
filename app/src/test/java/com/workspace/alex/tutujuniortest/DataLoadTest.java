package com.workspace.alex.tutujuniortest;

import android.app.Activity;
import android.content.Context;

import com.workspace.alex.tutujuniortest.utils.JSONDataLoad;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Alex on 08.09.2016.
 */
public class DataLoadTest extends Assert {

    private String inputFrom="";
    private String inputTo="";
    private Context context;
    private MainActivity activity;


    @Before
    public  void setUpApp()
    {
        inputFrom = "fromStations.json";
        inputTo = "toStations.json";
        context = activity.getApplicationContext();
    }

    @Before
    public  void setUpPaths()
    {
        inputFrom = "fromStations.json";
        inputTo = "toStations.json";
        context = activity.getApplicationContext();
    }

    @Test
    public void testLaunchApp()
    {


    }

    @Test
    public void testGettingAssertFromPath() throws IOException {
        String str = JSONDataLoad.AssetJSONFile(inputFrom, context);
        System.out.println(str);

    }

    @Test
    public void testGettingAssertToPath() throws IOException {
        String str = JSONDataLoad.AssetJSONFile(inputTo, context);
        System.out.println(str);

    }



}
