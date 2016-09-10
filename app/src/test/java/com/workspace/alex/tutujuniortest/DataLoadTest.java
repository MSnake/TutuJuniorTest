package com.workspace.alex.tutujuniortest;

import android.app.Activity;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

import com.workspace.alex.tutujuniortest.data.ArrivelData;
import com.workspace.alex.tutujuniortest.data.DepartureData;
import com.workspace.alex.tutujuniortest.utils.JSONDataLoad;


import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Alex on 08.09.2016.
 */
public class DataLoadTest extends ActivityInstrumentationTestCase2<MainActivity> {


    private MainActivity mActivity;

    public DataLoadTest(){
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
        JSONDataLoad.initDatas(mActivity.getApplicationContext());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void initDatas() {
        assertEquals(ArrivelData.getInstance().getData().size(),439);
        assertEquals(DepartureData.getInstance().getData().size(),439);
    }




}
