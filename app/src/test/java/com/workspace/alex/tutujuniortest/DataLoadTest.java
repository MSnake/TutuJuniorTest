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
public class DataLoadTest extends Assert {


    private MainActivity mActivity;

    @Before
    public void setUp() throws Exception {
        mActivity = new MainActivity();
        JSONDataLoad.initDatas(mActivity.getApplicationContext());
    }


    @Test
    public void initDatas() {
        assertEquals(ArrivelData.getInstance().getData().size(),439);
        assertEquals(DepartureData.getInstance().getData().size(),439);
    }




}
